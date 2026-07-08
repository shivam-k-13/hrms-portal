package service;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    service = PdfParserService.class
)
public class PdfParserService {

    private static final Log _log = LogFactoryUtil.getLog(
        PdfParserService.class);

    @Reference
    private DLAppLocalService dlAppLocalService;

    public PdfTextExtractionResult extractText(long fileEntryId)
        throws Exception {

        FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

        try (
            InputStream inputStream = fileEntry.getContentStream();
            PDDocument pdDocument = PDDocument.load(inputStream)
        ) {
            int pageCount = pdDocument.getNumberOfPages();

            List<PdfPageText> pages = new ArrayList<>();

            StringBuilder fullTextBuilder = new StringBuilder();

            for (int pageNumber = 1; pageNumber <= pageCount; pageNumber++) {
                PDFTextStripper pageTextStripper = new PDFTextStripper();

                pageTextStripper.setStartPage(pageNumber);
                pageTextStripper.setEndPage(pageNumber);

                String pageText = pageTextStripper.getText(pdDocument);

                pageText = normalizeText(pageText);

                if ((pageText != null) && !pageText.isEmpty()) {
                    pages.add(new PdfPageText(pageNumber, pageText));

                    fullTextBuilder.append(pageText);
                    fullTextBuilder.append("\n\n");
                }
            }

            String extractedText = fullTextBuilder.toString().trim();

            int characterCount = extractedText.length();

            _log.info(
                "PDF text extraction completed. FileEntryId=" + fileEntryId +
                    ", Pages=" + pageCount +
                    ", Characters=" + characterCount);

            return new PdfTextExtractionResult(
                extractedText,
                pageCount,
                characterCount,
                pages
            );
        }
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

    public static class PdfTextExtractionResult {

        private final String text;
        private final int pageCount;
        private final int characterCount;
        private final List<PdfPageText> pages;

        public PdfTextExtractionResult(
            String text, int pageCount, int characterCount,
            List<PdfPageText> pages) {

            this.text = text;
            this.pageCount = pageCount;
            this.characterCount = characterCount;
            this.pages = pages;
        }

        public String getText() {
            return text;
        }

        public int getPageCount() {
            return pageCount;
        }

        public int getCharacterCount() {
            return characterCount;
        }

        public List<PdfPageText> getPages() {
            return pages;
        }

        public boolean hasText() {
            return (text != null) && !text.trim().isEmpty();
        }

    }

    public static class PdfPageText {

        private final int pageNumber;
        private final String text;

        public PdfPageText(int pageNumber, String text) {
            this.pageNumber = pageNumber;
            this.text = text;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public String getText() {
            return text;
        }

    }

}