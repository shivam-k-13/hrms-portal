package service;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.File;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    service = DocumentLibraryService.class
)
public class DocumentLibraryService {

    @Reference
    private DLAppLocalService dlAppLocalService;

    public FileEntry uploadPDF(
            long userId,
            long groupId,
            String originalFileName,
            File file)
        throws Exception {

        byte[] bytes = FileUtil.getBytes(file);

        String uniqueFileName = createUniqueFileName(originalFileName);

        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setScopeGroupId(groupId);
        serviceContext.setUserId(userId);

        return dlAppLocalService.addFileEntry(
            null,
            userId,
            groupId,
            DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
            uniqueFileName,
            MimeTypesUtil.getContentType(uniqueFileName),
            uniqueFileName,
            "",
            "Uploaded from HRMS AI Assistant",
            "",
            bytes,
            null,
            null,
            null,
            serviceContext
        );
    }

    private String createUniqueFileName(String originalFileName) {

        int dotIndex = originalFileName.lastIndexOf(".");

        String baseName;
        String extension;

        if (dotIndex > 0) {
            baseName = originalFileName.substring(0, dotIndex);
            extension = originalFileName.substring(dotIndex);
        }
        else {
            baseName = originalFileName;
            extension = "";
        }

        return baseName + "_" + System.currentTimeMillis() + extension;
    }

}