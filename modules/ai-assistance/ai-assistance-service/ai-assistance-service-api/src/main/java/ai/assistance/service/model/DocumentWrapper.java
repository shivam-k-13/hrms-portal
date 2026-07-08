/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Document}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Document
 * @generated
 */
public class DocumentWrapper
	extends BaseModelWrapper<Document>
	implements Document, ModelWrapper<Document> {

	public DocumentWrapper(Document document) {
		super(document);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("documentId", getDocumentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long documentId = (Long)attributes.get("documentId");

		if (documentId != null) {
			setDocumentId(documentId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Document cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this document.
	 *
	 * @return the company ID of this document
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this document.
	 *
	 * @return the create date of this document
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the document ID of this document.
	 *
	 * @return the document ID of this document
	 */
	@Override
	public long getDocumentId() {
		return model.getDocumentId();
	}

	/**
	 * Returns the file entry ID of this document.
	 *
	 * @return the file entry ID of this document
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the group ID of this document.
	 *
	 * @return the group ID of this document
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this document.
	 *
	 * @return the modified date of this document
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this document.
	 *
	 * @return the primary key of this document
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this document.
	 *
	 * @return the status of this document
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the title of this document.
	 *
	 * @return the title of this document
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the user ID of this document.
	 *
	 * @return the user ID of this document
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this document.
	 *
	 * @return the user name of this document
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this document.
	 *
	 * @return the user uuid of this document
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this document.
	 *
	 * @param companyId the company ID of this document
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this document.
	 *
	 * @param createDate the create date of this document
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the document ID of this document.
	 *
	 * @param documentId the document ID of this document
	 */
	@Override
	public void setDocumentId(long documentId) {
		model.setDocumentId(documentId);
	}

	/**
	 * Sets the file entry ID of this document.
	 *
	 * @param fileEntryId the file entry ID of this document
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets the group ID of this document.
	 *
	 * @param groupId the group ID of this document
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this document.
	 *
	 * @param modifiedDate the modified date of this document
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this document.
	 *
	 * @param primaryKey the primary key of this document
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this document.
	 *
	 * @param status the status of this document
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the title of this document.
	 *
	 * @param title the title of this document
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the user ID of this document.
	 *
	 * @param userId the user ID of this document
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this document.
	 *
	 * @param userName the user name of this document
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this document.
	 *
	 * @param userUuid the user uuid of this document
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DocumentWrapper wrap(Document document) {
		return new DocumentWrapper(document);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1861560636