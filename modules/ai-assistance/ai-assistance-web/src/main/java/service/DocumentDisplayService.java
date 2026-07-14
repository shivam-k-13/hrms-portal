package service;

import ai.assistance.service.model.Document;
import ai.assistance.service.service.DocumentLocalService;

import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    service = DocumentDisplayService.class
)
public class DocumentDisplayService {

    @Reference
    private DocumentLocalService documentLocalService;

    public List<Document> getAllDocuments() {
        return documentLocalService.getDocuments(
            QueryUtil.ALL_POS,
            QueryUtil.ALL_POS
        );
    }

}