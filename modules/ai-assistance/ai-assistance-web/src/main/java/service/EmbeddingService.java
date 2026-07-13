package service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = EmbeddingService.class
)
public class EmbeddingService {

    private static final Log _log = LogFactoryUtil.getLog(
        EmbeddingService.class);

    private static final String DEFAULT_MODEL = "gemini-embedding-001";

    private static final int DEFAULT_DIMENSION = 768;

    public String generateEmbeddingAsString(String text) throws Exception {
        double[] embedding = generateEmbedding(text);

        return vectorToString(embedding);
    }

    public double[] generateEmbedding(String text) throws Exception {
        if (Validator.isNull(text)) {
            return new double[0];
        }

        String apiKey = getGeminiApiKey();

        if (Validator.isNull(apiKey)) {
            throw new IllegalStateException(
                "Gemini API key is missing. Configure " +
                    "hrms.ai.gemini.api.key in portal-ext.properties.");
        }

        String model = PropsUtil.get("hrms.ai.gemini.embedding.model");

        if (Validator.isNull(model)) {
            model = DEFAULT_MODEL;
        }

        int outputDimension = GetterUtil.getInteger(
            PropsUtil.get("hrms.ai.gemini.embedding.dimension"),
            DEFAULT_DIMENSION
        );

        JSONObject requestJSONObject = JSONFactoryUtil.createJSONObject();

        JSONObject contentJSONObject = JSONFactoryUtil.createJSONObject();

        JSONArray partsJSONArray = JSONFactoryUtil.createJSONArray();

        JSONObject textPartJSONObject = JSONFactoryUtil.createJSONObject();

        textPartJSONObject.put("text", text);

        partsJSONArray.put(textPartJSONObject);

        contentJSONObject.put("parts", partsJSONArray);

        requestJSONObject.put("content", contentJSONObject);
        requestJSONObject.put("taskType", "RETRIEVAL_DOCUMENT");
        requestJSONObject.put("outputDimensionality", outputDimension);

        String endpoint = buildGeminiEmbeddingEndpoint(model, apiKey);

        String response = postJson(endpoint, requestJSONObject.toString());

        JSONObject responseJSONObject =
            JSONFactoryUtil.createJSONObject(response);

        JSONObject embeddingJSONObject =
            responseJSONObject.getJSONObject("embedding");

        if (embeddingJSONObject == null) {
            throw new IllegalStateException(
                "Gemini embedding response does not contain embedding.");
        }

        JSONArray valuesJSONArray = embeddingJSONObject.getJSONArray("values");

        if (valuesJSONArray == null) {
            throw new IllegalStateException(
                "Gemini embedding response does not contain values.");
        }

        double[] embedding = new double[valuesJSONArray.length()];

        for (int i = 0; i < valuesJSONArray.length(); i++) {
            embedding[i] = valuesJSONArray.getDouble(i);
        }

        _log.info(
            "Gemini embedding generated. Model=" + model +
                ", Dimension=" + embedding.length +
                ", TextLength=" + text.length());

        return embedding;
    }

    public double[] generateQueryEmbedding(String query) throws Exception {
        if (Validator.isNull(query)) {
            return new double[0];
        }

        String apiKey = getGeminiApiKey();

        if (Validator.isNull(apiKey)) {
            throw new IllegalStateException(
                "Gemini API key is missing. Configure " +
                    "hrms.ai.gemini.api.key in portal-ext.properties.");
        }

        String model = PropsUtil.get("hrms.ai.gemini.embedding.model");

        if (Validator.isNull(model)) {
            model = DEFAULT_MODEL;
        }

        int outputDimension = GetterUtil.getInteger(
            PropsUtil.get("hrms.ai.gemini.embedding.dimension"),
            DEFAULT_DIMENSION
        );

        JSONObject requestJSONObject = JSONFactoryUtil.createJSONObject();

        JSONObject contentJSONObject = JSONFactoryUtil.createJSONObject();

        JSONArray partsJSONArray = JSONFactoryUtil.createJSONArray();

        JSONObject textPartJSONObject = JSONFactoryUtil.createJSONObject();

        textPartJSONObject.put("text", query);

        partsJSONArray.put(textPartJSONObject);

        contentJSONObject.put("parts", partsJSONArray);

        requestJSONObject.put("content", contentJSONObject);
        requestJSONObject.put("taskType", "RETRIEVAL_QUERY");
        requestJSONObject.put("outputDimensionality", outputDimension);

        String endpoint = buildGeminiEmbeddingEndpoint(model, apiKey);

        String response = postJson(endpoint, requestJSONObject.toString());

        JSONObject responseJSONObject =
            JSONFactoryUtil.createJSONObject(response);

        JSONObject embeddingJSONObject =
            responseJSONObject.getJSONObject("embedding");

        if (embeddingJSONObject == null) {
            throw new IllegalStateException(
                "Gemini query embedding response does not contain embedding.");
        }

        JSONArray valuesJSONArray = embeddingJSONObject.getJSONArray("values");

        if (valuesJSONArray == null) {
            throw new IllegalStateException(
                "Gemini query embedding response does not contain values.");
        }

        double[] embedding = new double[valuesJSONArray.length()];

        for (int i = 0; i < valuesJSONArray.length(); i++) {
            embedding[i] = valuesJSONArray.getDouble(i);
        }

        _log.info(
            "Gemini query embedding generated. Model=" + model +
                ", Dimension=" + embedding.length +
                ", QueryLength=" + query.length());

        return embedding;
    }

    public double[] parseEmbedding(String embeddingString) {
        if (Validator.isNull(embeddingString)) {
            return new double[0];
        }

        String[] values = embeddingString.split(",");

        double[] vector = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            try {
                vector[i] = Double.parseDouble(values[i]);
            }
            catch (NumberFormatException numberFormatException) {
                vector[i] = 0.0;
            }
        }

        return vector;
    }

    public double cosineSimilarity(double[] firstVector, double[] secondVector) {
        if ((firstVector == null) || (secondVector == null)) {
            return 0.0;
        }

        if ((firstVector.length == 0) || (secondVector.length == 0)) {
            return 0.0;
        }

        if (firstVector.length != secondVector.length) {
            return 0.0;
        }

        double dotProduct = 0.0;
        double firstNorm = 0.0;
        double secondNorm = 0.0;

        for (int i = 0; i < firstVector.length; i++) {
            dotProduct += firstVector[i] * secondVector[i];
            firstNorm += firstVector[i] * firstVector[i];
            secondNorm += secondVector[i] * secondVector[i];
        }

        if ((firstNorm == 0.0) || (secondNorm == 0.0)) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(firstNorm) * Math.sqrt(secondNorm));
    }

    private String getGeminiApiKey() {
        String apiKey = PropsUtil.get("hrms.ai.gemini.api.key");

        if (Validator.isNull(apiKey)) {
            apiKey = System.getenv("GEMINI_API_KEY");
        }

        return apiKey;
    }

    private String buildGeminiEmbeddingEndpoint(String model, String apiKey)
        throws Exception {

        return "https://generativelanguage.googleapis.com/v1beta/models/" +
            model + ":embedContent?key=" +
                URLEncoder.encode(apiKey, StandardCharsets.UTF_8.name());
    }

    private String postJson(String endpoint, String requestBody)
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
                "Gemini embedding API error. HTTPStatus=" + responseCode +
                    ", Response=" + responseBody);

            throw new IllegalStateException(
                "Gemini embedding API failed. HTTPStatus=" + responseCode);
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

    private String vectorToString(double[] vector) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < vector.length; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }

            stringBuilder.append(String.format(Locale.US, "%.8f", vector[i]));
        }

        return stringBuilder.toString();
    }

}