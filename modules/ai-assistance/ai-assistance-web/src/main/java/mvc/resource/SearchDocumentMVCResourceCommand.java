package mvc.resource;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.PrintWriter;

import java.util.List;

import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import service.EmbeddingSearchService;
import service.EmbeddingSearchService.SearchResult;

@Component(
    property = {
        "jakarta.portlet.name=ai_assistance_web_AiAssistanceWebPortlet",
        "mvc.command.name=/ai/search_documents"
    },
    service = MVCResourceCommand.class
)
public class SearchDocumentMVCResourceCommand implements MVCResourceCommand {

    private static final Log _log = LogFactoryUtil.getLog(
        SearchDocumentMVCResourceCommand.class);

    @Reference
    private EmbeddingSearchService embeddingSearchService;

    @Override
    public boolean serveResource(
        ResourceRequest resourceRequest,
        ResourceResponse resourceResponse) {

        JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject();

        try {
            String query = ParamUtil.getString(resourceRequest, "query");

            List<SearchResult> searchResults =
                embeddingSearchService.searchSimilarChunks(query, 5);

            JSONArray resultsJSONArray = JSONFactoryUtil.createJSONArray();

            for (SearchResult searchResult : searchResults) {
                JSONObject resultJSONObject =
                    JSONFactoryUtil.createJSONObject();

                resultJSONObject.put("chunkId", searchResult.getChunkId());
                resultJSONObject.put("documentId", searchResult.getDocumentId());
                resultJSONObject.put(
                    "documentTitle", searchResult.getDocumentTitle());
                resultJSONObject.put("pageNumber", searchResult.getPageNumber());
                resultJSONObject.put("score", searchResult.getScore());
                resultJSONObject.put(
                    "chunkText", shorten(searchResult.getChunkText(), 900));

                resultsJSONArray.put(resultJSONObject);
            }

            responseJSONObject.put("success", true);
            responseJSONObject.put("query", query);
            responseJSONObject.put("results", resultsJSONArray);

            _log.info(
                "Search resource completed. Query=" + query +
                    ", Results=" + searchResults.size());
        }
        catch (Exception exception) {
            _log.error("Error searching document chunks", exception);

            responseJSONObject.put("success", false);
            responseJSONObject.put(
                "error", "Unable to search document chunks");
        }

        try {
            resourceResponse.setContentType("application/json");

            PrintWriter printWriter = resourceResponse.getWriter();

            printWriter.write(responseJSONObject.toString());
            printWriter.flush();
        }
        catch (Exception exception) {
            _log.error("Error writing search response", exception);
        }

        return false;
    }

    private String shorten(String text, int maxLength) {
        if (text == null) {
            return "";
        }

        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength) + "...";
    }

}