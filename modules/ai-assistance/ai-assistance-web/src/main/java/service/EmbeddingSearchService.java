package service;

import ai.assistance.service.model.Document;
import ai.assistance.service.model.DocumentChunk;
import ai.assistance.service.service.DocumentChunkLocalService;
import ai.assistance.service.service.DocumentLocalService;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    service = EmbeddingSearchService.class
)
public class EmbeddingSearchService {

    private static final Log _log = LogFactoryUtil.getLog(
        EmbeddingSearchService.class);

    private static final double MIN_SIMILARITY_SCORE = 0.50;

    @Reference
    private DocumentChunkLocalService documentChunkLocalService;

    @Reference
    private DocumentLocalService documentLocalService;

    @Reference
    private EmbeddingService embeddingService;

    public List<SearchResult> searchSimilarChunks(String query, int topK)
        throws Exception {

        List<SearchResult> searchResults = new ArrayList<>();

        if (Validator.isNull(query)) {
            return searchResults;
        }

        double[] queryEmbedding = embeddingService.generateQueryEmbedding(query);

        List<DocumentChunk> documentChunks =
            documentChunkLocalService.getDocumentChunks(
                QueryUtil.ALL_POS,
                QueryUtil.ALL_POS
            );

        int skippedDimensionMismatch = 0;
        int skippedEmptyEmbedding = 0;
        int skippedLowScore = 0;

        for (DocumentChunk documentChunk : documentChunks) {
            String embeddingString = documentChunk.getEmbedding();

            if (Validator.isNull(embeddingString)) {
                skippedEmptyEmbedding++;

                continue;
            }

            double[] chunkEmbedding =
                embeddingService.parseEmbedding(embeddingString);

            if (chunkEmbedding.length != queryEmbedding.length) {
                skippedDimensionMismatch++;

                continue;
            }

            double score = embeddingService.cosineSimilarity(
                queryEmbedding,
                chunkEmbedding
            );

            if (score < MIN_SIMILARITY_SCORE) {
                skippedLowScore++;

                continue;
            }

            String documentTitle = "";

            try {
                Document document = documentLocalService.getDocument(
                    documentChunk.getDocumentId());

                documentTitle = document.getTitle();
            }
            catch (Exception exception) {
                _log.warn(
                    "Unable to load document title. DocumentId=" +
                        documentChunk.getDocumentId());
            }

            searchResults.add(
                new SearchResult(
                    documentChunk.getChunkId(),
                    documentChunk.getDocumentId(),
                    documentTitle,
                    documentChunk.getPageNumber(),
                    documentChunk.getChunkText(),
                    score
                )
            );
        }

        List<SearchResult> topResults = searchResults.stream()
            .sorted(Comparator.comparingDouble(SearchResult::getScore).reversed())
            .limit(topK)
            .collect(Collectors.toList());

        _log.info(
            "Semantic embedding search completed. Query=" + query +
                ", TotalChunks=" + documentChunks.size() +
                ", Results=" + topResults.size() +
                ", SkippedEmptyEmbedding=" + skippedEmptyEmbedding +
                ", SkippedDimensionMismatch=" + skippedDimensionMismatch +
                ", SkippedLowScore=" + skippedLowScore);

        return topResults;
    }

    public static class SearchResult {

        private final long chunkId;
        private final long documentId;
        private final String documentTitle;
        private final int pageNumber;
        private final String chunkText;
        private final double score;

        public SearchResult(
            long chunkId,
            long documentId,
            String documentTitle,
            int pageNumber,
            String chunkText,
            double score) {

            this.chunkId = chunkId;
            this.documentId = documentId;
            this.documentTitle = documentTitle;
            this.pageNumber = pageNumber;
            this.chunkText = chunkText;
            this.score = score;
        }

        public long getChunkId() {
            return chunkId;
        }

        public long getDocumentId() {
            return documentId;
        }

        public String getDocumentTitle() {
            return documentTitle;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public String getChunkText() {
            return chunkText;
        }

        public double getScore() {
            return score;
        }

    }

}