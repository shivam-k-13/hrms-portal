package service;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import service.EmbeddingSearchService.SearchResult;

@Component(
    immediate = true,
    service = PromptBuilderService.class
)
public class PromptBuilderService {

    public String buildAnswerPrompt(
        String question, List<SearchResult> searchResults) {

        StringBuilder promptBuilder = new StringBuilder();

        promptBuilder.append("USER_QUESTION:\n");
        promptBuilder.append(question);
        promptBuilder.append("\n\n");

        promptBuilder.append("DOCUMENT_CONTEXT:\n");

        int index = 1;

        for (SearchResult searchResult : searchResults) {
            promptBuilder.append("\n[SOURCE ");
            promptBuilder.append(index);
            promptBuilder.append("]\n");

            promptBuilder.append("Document: ");
            promptBuilder.append(searchResult.getDocumentTitle());
            promptBuilder.append("\n");

            promptBuilder.append("Page: ");
            promptBuilder.append(searchResult.getPageNumber());
            promptBuilder.append("\n");

            promptBuilder.append("Content:\n");
            promptBuilder.append(searchResult.getChunkText());
            promptBuilder.append("\n");

            index++;
        }

        promptBuilder.append("\nANSWER_INSTRUCTIONS:\n");
        promptBuilder.append("Answer USER_QUESTION directly using only DOCUMENT_CONTEXT.\n");
        promptBuilder.append("Do not start with phrases like 'Based on the provided documents'.\n");
        promptBuilder.append("Do not mention the prompt, context block, retrieval, embeddings, or similarity scores.\n");
        promptBuilder.append("If the answer is available, provide a complete but concise final answer.\n");
        promptBuilder.append("Use bullet points only when helpful.\n");
        promptBuilder.append("If the answer is not available, say exactly:\n");
        promptBuilder.append("I could not find enough information in the uploaded documents.\n");

        return promptBuilder.toString();
    }

}