package service;

import ai.assistance.service.model.DocumentChunk;
import ai.assistance.service.service.DocumentChunkLocalService;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import service.PdfParserService.PdfPageText;
import service.PdfParserService.PdfTextExtractionResult;

@Component(
    immediate = true,
    service = ChunkService.class
)
public class ChunkService {

    private static final Log _log = LogFactoryUtil.getLog(
        ChunkService.class);

    private static final int CHUNK_SIZE = 1000;

    private static final int CHUNK_OVERLAP = 150;

    @Reference
    private CounterLocalService counterLocalService;

    @Reference
    private DocumentChunkLocalService documentChunkLocalService;

    @Reference
    private EmbeddingService embeddingService;

    public int createChunks(
            long documentId, PdfTextExtractionResult extractionResult)
        throws Exception {

        if ((extractionResult == null) || !extractionResult.hasText()) {
            return 0;
        }

        DocumentText documentText = buildDocumentText(
            extractionResult.getPages());

        List<ChunkCandidate> chunkCandidates = splitDocumentIntoChunks(
            documentText);

        int savedChunkCount = 0;

        for (ChunkCandidate chunkCandidate : chunkCandidates) {
            if ((chunkCandidate.getText() == null) ||
                chunkCandidate.getText().trim().isEmpty()) {

                continue;
            }

            saveChunkWithEmbedding(
                documentId,
                chunkCandidate.getText().trim(),
                chunkCandidate.getPageNumber()
            );

            savedChunkCount++;
        }

        _log.info(
            "Saved document chunks with embeddings. DocumentId=" + documentId +
                ", ChunkCount=" + savedChunkCount);

        return savedChunkCount;
    }

    private DocumentText buildDocumentText(List<PdfPageText> pages) {
        StringBuilder fullTextBuilder = new StringBuilder();

        List<PageOffset> pageOffsets = new ArrayList<>();

        if (pages == null) {
            return new DocumentText("", pageOffsets);
        }

        for (PdfPageText page : pages) {
            String pageText = page.getText();

            if ((pageText == null) || pageText.trim().isEmpty()) {
                continue;
            }

            int startOffset = fullTextBuilder.length();

            fullTextBuilder.append("\n\n");
            fullTextBuilder.append("[Page ");
            fullTextBuilder.append(page.getPageNumber());
            fullTextBuilder.append("]\n");
            fullTextBuilder.append(pageText.trim());

            int endOffset = fullTextBuilder.length();

            pageOffsets.add(
                new PageOffset(
                    page.getPageNumber(),
                    startOffset,
                    endOffset
                )
            );
        }

        String fullText = normalizeText(fullTextBuilder.toString());

        return new DocumentText(fullText, pageOffsets);
    }

    private List<ChunkCandidate> splitDocumentIntoChunks(
        DocumentText documentText) {

        List<ChunkCandidate> chunks = new ArrayList<>();

        String text = documentText.getText();

        if ((text == null) || text.trim().isEmpty()) {
            return chunks;
        }

        int textLength = text.length();

        int start = 0;

        while (start < textLength) {
            int end = Math.min(start + CHUNK_SIZE, textLength);

            if (end < textLength) {
                int sentenceBoundary = findLastSentenceBoundary(
                    text,
                    start,
                    end
                );

                if (sentenceBoundary > start) {
                    end = sentenceBoundary + 1;
                }
            }

            String chunkText = text.substring(start, end).trim();

            if (!chunkText.isEmpty()) {
                int pageNumber = findPageNumberForOffset(
                    start,
                    documentText.getPageOffsets()
                );

                chunks.add(
                    new ChunkCandidate(
                        chunkText,
                        pageNumber
                    )
                );
            }

            if (end >= textLength) {
                break;
            }

            start = Math.max(end - CHUNK_OVERLAP, start + 1);
        }

        return chunks;
    }

    private void saveChunkWithEmbedding(
            long documentId, String chunkText, int pageNumber)
        throws Exception {

        long chunkId = counterLocalService.increment(
            DocumentChunk.class.getName());

        String embedding = embeddingService.generateEmbeddingAsString(
            chunkText);

        DocumentChunk documentChunk =
            documentChunkLocalService.createDocumentChunk(chunkId);

        documentChunk.setDocumentId(documentId);
        documentChunk.setChunkText(chunkText);
        documentChunk.setPageNumber(pageNumber);
        documentChunk.setEmbedding(embedding);

        documentChunkLocalService.addDocumentChunk(documentChunk);

        _log.debug(
            "Saved chunk with embedding. ChunkId=" + chunkId +
                ", DocumentId=" + documentId +
                ", PageNumber=" + pageNumber +
                ", ChunkLength=" + chunkText.length() +
                ", EmbeddingLength=" + embedding.length());
    }

    private String normalizeText(String text) {
        if (text == null) {
            return "";
        }

        return text
            .replace("\u0000", "")
            .replace("\r", "\n")
            .replaceAll("[ \\t]+", " ")
            .replaceAll("\\n{3,}", "\n\n")
            .trim();
    }

    private int findLastSentenceBoundary(
        String text, int start, int end) {

        int lastPeriod = text.lastIndexOf('.', end);
        int lastQuestion = text.lastIndexOf('?', end);
        int lastExclamation = text.lastIndexOf('!', end);
        int lastNewLine = text.lastIndexOf('\n', end);

        int boundary = Math.max(
            Math.max(lastPeriod, lastQuestion),
            Math.max(lastExclamation, lastNewLine)
        );

        if (boundary <= start + 200) {
            return -1;
        }

        return boundary;
    }

    private int findPageNumberForOffset(
        int offset, List<PageOffset> pageOffsets) {

        if ((pageOffsets == null) || pageOffsets.isEmpty()) {
            return 0;
        }

        for (PageOffset pageOffset : pageOffsets) {
            if ((offset >= pageOffset.getStartOffset()) &&
                (offset <= pageOffset.getEndOffset())) {

                return pageOffset.getPageNumber();
            }
        }

        return pageOffsets.get(pageOffsets.size() - 1).getPageNumber();
    }

    private static class DocumentText {

        private final String text;
        private final List<PageOffset> pageOffsets;

        private DocumentText(String text, List<PageOffset> pageOffsets) {
            this.text = text;
            this.pageOffsets = pageOffsets;
        }

        private String getText() {
            return text;
        }

        private List<PageOffset> getPageOffsets() {
            return pageOffsets;
        }

    }

    private static class PageOffset {

        private final int pageNumber;
        private final int startOffset;
        private final int endOffset;

        private PageOffset(
            int pageNumber, int startOffset, int endOffset) {

            this.pageNumber = pageNumber;
            this.startOffset = startOffset;
            this.endOffset = endOffset;
        }

        private int getPageNumber() {
            return pageNumber;
        }

        private int getStartOffset() {
            return startOffset;
        }

        private int getEndOffset() {
            return endOffset;
        }

    }

    private static class ChunkCandidate {

        private final String text;
        private final int pageNumber;

        private ChunkCandidate(String text, int pageNumber) {
            this.text = text;
            this.pageNumber = pageNumber;
        }

        private String getText() {
            return text;
        }

        private int getPageNumber() {
            return pageNumber;
        }

    }

}