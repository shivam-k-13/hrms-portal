package mvc.action;

import ai.assistance.service.model.Document;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import service.ChunkService;
import service.DocumentLibraryService;
import service.DocumentRecordService;
import service.PdfParserService;
import service.PdfParserService.PdfTextExtractionResult;

@Component(
    property = {
        "jakarta.portlet.name=ai_assistance_web_AiAssistanceWebPortlet",
        "mvc.command.name=/ai/upload_pdf"
    },
    service = MVCActionCommand.class
)
public class UploadPDFMVCActionCommand implements MVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(
        UploadPDFMVCActionCommand.class);

    private static final long MAX_UPLOAD_SIZE_BYTES = 10 * 1024 * 1024;

    @Reference
    private Portal portal;

    @Reference
    private DocumentLibraryService documentLibraryService;

    @Reference
    private DocumentRecordService documentRecordService;

    @Reference
    private PdfParserService pdfParserService;

    @Reference
    private ChunkService chunkService;

    @Override
    public boolean processAction(
        ActionRequest actionRequest, ActionResponse actionResponse) {

        Document document = null;

        try {
            _log.info("PDF Upload Action Called");

            ThemeDisplay themeDisplay =
                (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            UploadPortletRequest uploadPortletRequest =
                portal.getUploadPortletRequest(actionRequest);

            File file = uploadPortletRequest.getFile("pdfFile");

            String fileName = uploadPortletRequest.getFileName("pdfFile");

            if ((file == null) || !file.exists() || (file.length() == 0)) {
                _log.error("Uploaded file is empty or missing");
                return true;
            }

            if (file.length() > MAX_UPLOAD_SIZE_BYTES) {
                _log.error(
                    "Uploaded PDF is too large. FileName=" + fileName +
                        ", Size=" + file.length() +
                        " bytes, MaxAllowed=" + MAX_UPLOAD_SIZE_BYTES +
                        " bytes");

                return true;
            }

            if (Validator.isNull(fileName)) {
                _log.error("Uploaded file name is empty");
                return true;
            }

            if (!fileName.toLowerCase().endsWith(".pdf")) {
                _log.error("Only PDF files are allowed. Uploaded file: " + fileName);
                return true;
            }

            FileEntry fileEntry = documentLibraryService.uploadPDF(
                themeDisplay.getUserId(),
                themeDisplay.getScopeGroupId(),
                fileName,
                file
            );

            _log.info(
                "PDF Uploaded Successfully. FileEntryId=" +
                    fileEntry.getFileEntryId());

            document = documentRecordService.saveUploadedDocument(
                fileEntry.getTitle(),
                fileEntry.getFileEntryId(),
                themeDisplay.getScopeGroupId(),
                themeDisplay.getCompanyId(),
                themeDisplay.getUserId(),
                themeDisplay.getUser().getFullName()
            );

            PdfTextExtractionResult extractionResult =
                pdfParserService.extractText(fileEntry.getFileEntryId());

            if (extractionResult.hasText()) {
                documentRecordService.updateStatus(
                    document.getDocumentId(),
                    "TEXT_EXTRACTED"
                );

                _log.info(
                    "Extracted PDF text successfully. DocumentId=" +
                        document.getDocumentId() +
                        ", Pages=" + extractionResult.getPageCount() +
                        ", Characters=" + extractionResult.getCharacterCount());

                int chunkCount = chunkService.createChunks(
                    document.getDocumentId(),
                    extractionResult
                );

                if (chunkCount > 0) {
                    documentRecordService.updateStatus(
                        document.getDocumentId(),
                        "EMBEDDED"
                    );

                    _log.info(
                        "Document chunks and embeddings created successfully. " +
                            "DocumentId=" + document.getDocumentId() +
                            ", ChunkCount=" + chunkCount);
                }
                else {
                    documentRecordService.updateStatus(
                        document.getDocumentId(),
                        "NO_CHUNKS_CREATED"
                    );
                }

                String previewText = extractionResult.getText();

                if (previewText.length() > 500) {
                    previewText = previewText.substring(0, 500);
                }

                _log.info("PDF Text Preview: " + previewText);
            }
            else {
                documentRecordService.updateStatus(
                    document.getDocumentId(),
                    "NO_TEXT_FOUND"
                );

                _log.warn(
                    "No text found in uploaded PDF. DocumentId=" +
                        document.getDocumentId());
            }
        }
        catch (Exception exception) {
            _log.error("Error uploading, extracting, chunking, or embedding PDF", exception);

            if (document != null) {
                try {
                    documentRecordService.updateStatus(
                        document.getDocumentId(),
                        "PROCESSING_ERROR"
                    );
                }
                catch (Exception statusException) {
                    _log.error(
                        "Unable to update document status after processing error",
                        statusException);
                }
            }
        }

        return true;
    }

}