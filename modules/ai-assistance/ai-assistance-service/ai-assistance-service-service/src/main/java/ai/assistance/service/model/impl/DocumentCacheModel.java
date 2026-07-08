/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.model.impl;

import ai.assistance.service.model.Document;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Document in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DocumentCacheModel
	implements CacheModel<Document>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DocumentCacheModel)) {
			return false;
		}

		DocumentCacheModel documentCacheModel = (DocumentCacheModel)object;

		if (documentId == documentCacheModel.documentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, documentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{documentId=");
		sb.append(documentId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Document toEntityModel() {
		DocumentImpl documentImpl = new DocumentImpl();

		documentImpl.setDocumentId(documentId);
		documentImpl.setGroupId(groupId);
		documentImpl.setCompanyId(companyId);
		documentImpl.setUserId(userId);

		if (userName == null) {
			documentImpl.setUserName("");
		}
		else {
			documentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			documentImpl.setCreateDate(null);
		}
		else {
			documentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			documentImpl.setModifiedDate(null);
		}
		else {
			documentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			documentImpl.setTitle("");
		}
		else {
			documentImpl.setTitle(title);
		}

		documentImpl.setFileEntryId(fileEntryId);

		if (status == null) {
			documentImpl.setStatus("");
		}
		else {
			documentImpl.setStatus(status);
		}

		documentImpl.resetOriginalValues();

		return documentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		documentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();

		fileEntryId = objectInput.readLong();
		status = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(documentId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(fileEntryId);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long documentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public long fileEntryId;
	public String status;

}
// LIFERAY-SERVICE-BUILDER-HASH:-66031809