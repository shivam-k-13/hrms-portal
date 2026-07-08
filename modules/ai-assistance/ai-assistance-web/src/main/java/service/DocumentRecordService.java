package service;

import ai.assistance.service.model.Document;
import ai.assistance.service.service.DocumentLocalService;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    service = DocumentRecordService.class
)
public class DocumentRecordService {

    private static final Log _log = LogFactoryUtil.getLog(
        DocumentRecordService.class);

    @Reference
    private CounterLocalService counterLocalService;

    @Reference
    private DocumentLocalService documentLocalService;

    public Document saveUploadedDocument(
            String title,
            long fileEntryId,
            long groupId,
            long companyId,
            long userId,
            String userName)
        throws Exception {

        long documentId = counterLocalService.increment(
            Document.class.getName());

        Date now = new Date();

        Document document = documentLocalService.createDocument(documentId);

        document.setTitle(title);
        document.setFileEntryId(fileEntryId);
        document.setStatus("UPLOADED");

        document.setGroupId(groupId);
        document.setCompanyId(companyId);
        document.setUserId(userId);
        document.setUserName(userName);

        document.setCreateDate(now);
        document.setModifiedDate(now);

        document = documentLocalService.addDocument(document);

        _log.info(
            "Document Record Saved Successfully. DocumentId=" +
                document.getDocumentId());

        return document;
    }

    public Document updateStatus(long documentId, String status)
        throws Exception {

        Document document = documentLocalService.getDocument(documentId);

        document.setStatus(status);
        document.setModifiedDate(new Date());

        document = documentLocalService.updateDocument(document);

        _log.info(
            "Document status updated. DocumentId=" + documentId +
                ", Status=" + status);

        return document;
    }

}