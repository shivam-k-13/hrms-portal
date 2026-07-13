package mvc.resource;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.PrintWriter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import service.EmbeddingSearchService;
import service.EmbeddingSearchService.SearchResult;
import service.LLMService;
import service.PromptBuilderService;
import service.exception.LLMQuotaExceededException;

@Component(
    property = {
        "jakarta.portlet.name=ai_assistance_web_AiAssistanceWebPortlet",
        "mvc.command.name=/ai/chat"
    },
    service = MVCResourceCommand.class
)
public class ChatMVCResourceCommand implements MVCResourceCommand {

    private static final Log _log = LogFactoryUtil.getLog(
        ChatMVCResourceCommand.class);

    @Reference
    private EmbeddingSearchService embeddingSearchService;

    @Reference
    private PromptBuilderService promptBuilderService;

    @Reference
    private LLMService llmService;

    @Override
    public boolean serveResource(
        ResourceRequest resourceRequest,
        ResourceResponse resourceResponse) {

        JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject();

        try {
            String question = ParamUtil.getString(resourceRequest, "question");

            if (Validator.isNull(question)) {
                responseJSONObject.put("success", false);
                responseJSONObject.put("error", "Question is required.");

                writeJSON(resourceResponse, responseJSONObject);

                return false;
            }

            List<SearchResult> searchResults =
                embeddingSearchService.searchSimilarChunks(question, 3);

            if (searchResults.isEmpty()) {
                responseJSONObject.put("success", true);
                responseJSONObject.put(
                    "answer",
                    "I could not find enough information in the uploaded documents.");

                responseJSONObject.put(
                    "sources",
                    JSONFactoryUtil.createJSONArray());

                writeJSON(resourceResponse, responseJSONObject);

                return false;
            }

            String prompt = promptBuilderService.buildAnswerPrompt(
                question,
                searchResults
            );

            String answer;

            try {
                answer = llmService.generateAnswer(prompt);
            }
            catch (LLMQuotaExceededException exception) {
              _log.warn("Gemini quota exceeded. Returning retrieval fallback. RetryAfterSeconds=" +
                	        exception.getRetryAfterSeconds());

                JSONArray sourcesJSONArray =
                    buildDeduplicatedSourcesJSONArray(searchResults);

                responseJSONObject.put("success", true);
                responseJSONObject.put("llmAvailable", false);
                responseJSONObject.put("errorCode", "LLM_QUOTA_EXCEEDED");

                if (exception.getRetryAfterSeconds() > 0) {
                    responseJSONObject.put(
                        "retryAfterSeconds",
                        exception.getRetryAfterSeconds());
                }

                responseJSONObject.put(
                    "answer",
                    "The AI answer generator is temporarily unavailable due to quota limits. " +
                        "However, I found relevant information in the uploaded documents. " +
                        "Please check the listed source documents.");

                responseJSONObject.put("sources", sourcesJSONArray);

                writeJSON(resourceResponse, responseJSONObject);

                return false;
            }

            JSONArray sourcesJSONArray;

            if (isNotEnoughInformationAnswer(answer)) {
                sourcesJSONArray = JSONFactoryUtil.createJSONArray();
            }
            else {
                sourcesJSONArray = buildDeduplicatedSourcesJSONArray(searchResults);
            }

            responseJSONObject.put("success", true);
            responseJSONObject.put("answer", answer);
            responseJSONObject.put("sources", sourcesJSONArray);

            _log.info(
                "Chat response completed. Question=" + question +
                    ", Sources=" + sourcesJSONArray.length());
        }
        catch (LLMQuotaExceededException exception) {
            _log.warn("Gemini quota exceeded", exception);

            responseJSONObject.put("success", false);
            responseJSONObject.put("errorCode", "LLM_QUOTA_EXCEEDED");

            if (exception.getRetryAfterSeconds() > 0) {
                responseJSONObject.put(
                    "error",
                    "The AI service quota has been exceeded. Please try again after " +
                        exception.getRetryAfterSeconds() + " seconds.");

                responseJSONObject.put(
                    "retryAfterSeconds",
                    exception.getRetryAfterSeconds());
            }
            else {
                responseJSONObject.put(
                    "error",
                    "The AI service quota has been exceeded. Please try again later.");
            }
        }
        catch (Exception exception) {
            _log.error("Error generating chat response", exception);

            responseJSONObject.put("success", false);
            responseJSONObject.put("errorCode", "CHAT_RESPONSE_FAILED");
            responseJSONObject.put(
                "error", "Unable to generate answer right now. Please try again later.");
        }

        writeJSON(resourceResponse, responseJSONObject);

        return false;
    }

    private JSONArray buildDeduplicatedSourcesJSONArray(
        List<SearchResult> searchResults) {

        JSONArray sourcesJSONArray = JSONFactoryUtil.createJSONArray();

        Set<String> sourceKeys = new HashSet<>();

        for (SearchResult searchResult : searchResults) {
            String sourceKey =
                searchResult.getDocumentTitle() + "#" +
                    searchResult.getPageNumber();

            if (sourceKeys.contains(sourceKey)) {
                continue;
            }

            sourceKeys.add(sourceKey);

            JSONObject sourceJSONObject =
                JSONFactoryUtil.createJSONObject();

            sourceJSONObject.put(
                "documentTitle", searchResult.getDocumentTitle());
            sourceJSONObject.put("pageNumber", searchResult.getPageNumber());
            sourceJSONObject.put("score", searchResult.getScore());

            sourcesJSONArray.put(sourceJSONObject);
        }

        return sourcesJSONArray;
    }

    private void writeJSON(
        ResourceResponse resourceResponse, JSONObject responseJSONObject) {

        try {
            resourceResponse.setContentType("application/json");

            PrintWriter printWriter = resourceResponse.getWriter();

            printWriter.write(responseJSONObject.toString());
            printWriter.flush();
        }
        catch (Exception exception) {
            _log.error("Error writing chat response", exception);
        }
    }
    
    private boolean isNotEnoughInformationAnswer(String answer) {
        if (Validator.isNull(answer)) {
            return true;
        }

        return answer.trim().equalsIgnoreCase(
            "I could not find enough information in the uploaded documents.");
    }

}