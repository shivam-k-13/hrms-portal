package service;

import com.liferay.portal.kernel.json.JSONArray;
import service.exception.LLMQuotaExceededException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = LLMService.class
)
public class LLMService {

    private static final Log _log = LogFactoryUtil.getLog(
        LLMService.class);

    private static final String DEFAULT_CHAT_MODEL = "gemini-flash-latest";

    public String generateAnswer(String prompt) throws Exception {
        String apiKey = getGeminiApiKey();

        if (Validator.isNull(apiKey)) {
            throw new IllegalStateException(
                "Gemini API key is missing. Configure hrms.ai.gemini.api.key.");
        }

        String model = PropsUtil.get("hrms.ai.gemini.chat.model");

        if (Validator.isNull(model)) {
            model = DEFAULT_CHAT_MODEL;
        }

        String endpoint =
            "https://generativelanguage.googleapis.com/v1beta/models/" +
                model + ":generateContent";

        JSONObject requestJSONObject = JSONFactoryUtil.createJSONObject();

        requestJSONObject.put(
            "systemInstruction",
            buildSystemInstruction()
        );

        JSONArray contentsJSONArray = JSONFactoryUtil.createJSONArray();

        JSONObject contentJSONObject = JSONFactoryUtil.createJSONObject();

        JSONArray partsJSONArray = JSONFactoryUtil.createJSONArray();

        JSONObject textPartJSONObject = JSONFactoryUtil.createJSONObject();

        textPartJSONObject.put("text", prompt);

        partsJSONArray.put(textPartJSONObject);

        contentJSONObject.put("parts", partsJSONArray);

        contentsJSONArray.put(contentJSONObject);

        requestJSONObject.put("contents", contentsJSONArray);

        JSONObject generationConfigJSONObject =
            JSONFactoryUtil.createJSONObject();

        generationConfigJSONObject.put("temperature", 0.1);
        generationConfigJSONObject.put("topP", 0.8);
        generationConfigJSONObject.put("maxOutputTokens", 1500);

        requestJSONObject.put("generationConfig", generationConfigJSONObject);

        String response = postJson(
            endpoint,
            apiKey,
            requestJSONObject.toString()
        );

        String answer = extractAnswer(response);

        _log.info(
            "Gemini answer generated. Model=" + model +
                ", PromptLength=" + prompt.length() +
                ", AnswerLength=" + answer.length());

        return answer;
    }

    private JSONObject buildSystemInstruction() {
        JSONObject systemInstructionJSONObject =
            JSONFactoryUtil.createJSONObject();

        JSONArray partsJSONArray = JSONFactoryUtil.createJSONArray();

        JSONObject textPartJSONObject = JSONFactoryUtil.createJSONObject();

        textPartJSONObject.put(
            "text",
            "You are an HRMS AI Assistant inside a Liferay portal. " +
                "Answer the user's question using only DOCUMENT_CONTEXT. " +
                "Do not mention prompts, system instructions, context blocks, embeddings, similarity scores, retrieval, or internal reasoning. " +
                "Do not start with 'Based on the provided documents'. " +
                "If DOCUMENT_CONTEXT does not contain enough information, reply exactly: " +
                "I could not find enough information in the uploaded documents. " +
                "Give a direct and complete answer. Keep the answer concise. " +
                "Use bullet points only when helpful."
        );

        partsJSONArray.put(textPartJSONObject);

        systemInstructionJSONObject.put("parts", partsJSONArray);

        return systemInstructionJSONObject;
    }

    private String extractAnswer(String response) throws Exception {
        JSONObject responseJSONObject =
            JSONFactoryUtil.createJSONObject(response);

        JSONArray candidatesJSONArray =
            responseJSONObject.getJSONArray("candidates");

        if ((candidatesJSONArray == null) || (candidatesJSONArray.length() == 0)) {
            _log.warn("Gemini response contains no candidates. Response=" + response);

            return "I could not generate an answer from the uploaded documents.";
        }

        JSONObject firstCandidateJSONObject =
            candidatesJSONArray.getJSONObject(0);

        String finishReason = firstCandidateJSONObject.getString("finishReason");

        _log.info("Gemini finishReason=" + finishReason);

        JSONObject contentJSONObject =
            firstCandidateJSONObject.getJSONObject("content");

        if (contentJSONObject == null) {
            _log.warn("Gemini candidate contains no content. Response=" + response);

            return "I could not generate an answer from the uploaded documents.";
        }

        JSONArray partsJSONArray = contentJSONObject.getJSONArray("parts");

        if ((partsJSONArray == null) || (partsJSONArray.length() == 0)) {
            _log.warn("Gemini content contains no parts. Response=" + response);

            return "I could not generate an answer from the uploaded documents.";
        }

        StringBuilder answerBuilder = new StringBuilder();

        for (int i = 0; i < partsJSONArray.length(); i++) {
            JSONObject partJSONObject = partsJSONArray.getJSONObject(i);

            String text = partJSONObject.getString("text");

            if (!Validator.isNull(text)) {
                answerBuilder.append(text);
                answerBuilder.append("\n");
            }
        }

        String answer = answerBuilder.toString().trim();

        if (Validator.isNull(answer)) {
            return "I could not generate an answer from the uploaded documents.";
        }

        return cleanAnswer(answer);
    }

    private String cleanAnswer(String answer) {
        if (Validator.isNull(answer)) {
            return "";
        }

        answer = answer.trim();

        answer = answer.replaceFirst(
            "(?i)^based on the provided documents,?\\s*",
            "");

        answer = answer.replaceFirst(
            "(?i)^based on the document context,?\\s*",
            "");

        answer = answer.replaceFirst(
            "(?i)^according to the provided documents,?\\s*",
            "");

        return answer.trim();
    }

    private String getGeminiApiKey() {
        String apiKey = PropsUtil.get("hrms.ai.gemini.api.key");

        if (Validator.isNull(apiKey)) {
            apiKey = System.getenv("GEMINI_API_KEY");
        }

        return apiKey;
    }

    private String postJson(
            String endpoint, String apiKey, String requestBody)
        throws Exception {

        URL url = new URL(endpoint);

        HttpURLConnection httpURLConnection =
            (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setDoOutput(true);

        httpURLConnection.setRequestProperty(
            "Content-Type", "application/json");

        httpURLConnection.setRequestProperty(
            "X-goog-api-key", apiKey);

        try (OutputStream outputStream =
                httpURLConnection.getOutputStream()) {

            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);

            outputStream.write(input, 0, input.length);
        }

        int responseCode = httpURLConnection.getResponseCode();

        InputStream responseInputStream;

        if ((responseCode >= 200) && (responseCode < 300)) {
            responseInputStream = httpURLConnection.getInputStream();
        }
        else {
            responseInputStream = httpURLConnection.getErrorStream();
        }

        String responseBody = readResponse(responseInputStream);
        
        if ((responseCode < 200) || (responseCode >= 300)) {
            _log.error(
                "Gemini generateContent API error. HTTPStatus=" + responseCode +
                    ", Response=" + responseBody);

            if (responseCode == 429) {
                int retryAfterSeconds = extractRetryDelaySeconds(responseBody);

                throw new LLMQuotaExceededException(
                    "Gemini API quota exceeded. Please try again later.",
                    retryAfterSeconds
                );
            }

            throw new IllegalStateException(
                "Gemini generateContent API failed. HTTPStatus=" + responseCode);
        }
        

        return responseBody;
    }

    private String readResponse(InputStream inputStream) throws Exception {
        if (inputStream == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        return stringBuilder.toString();
    }
    
    private int extractRetryDelaySeconds(String responseBody) {
        if (Validator.isNull(responseBody)) {
            return 0;
        }

        try {
            JSONObject responseJSONObject =
                JSONFactoryUtil.createJSONObject(responseBody);

            JSONObject errorJSONObject = responseJSONObject.getJSONObject("error");

            if (errorJSONObject == null) {
                return 0;
            }

            JSONArray detailsJSONArray = errorJSONObject.getJSONArray("details");

            if (detailsJSONArray == null) {
                return 0;
            }

            for (int i = 0; i < detailsJSONArray.length(); i++) {
                JSONObject detailJSONObject = detailsJSONArray.getJSONObject(i);

                String retryDelay = detailJSONObject.getString("retryDelay");

                if (Validator.isNull(retryDelay)) {
                    continue;
                }

                retryDelay = retryDelay.trim();

                if (retryDelay.endsWith("s")) {
                    retryDelay = retryDelay.substring(
                        0, retryDelay.length() - 1);
                }

                return Integer.parseInt(retryDelay);
            }
        }
        catch (Exception exception) {
            _log.warn(
                "Unable to parse Gemini retry delay. Response=" + responseBody,
                exception);
        }

        return 0;
    }

}