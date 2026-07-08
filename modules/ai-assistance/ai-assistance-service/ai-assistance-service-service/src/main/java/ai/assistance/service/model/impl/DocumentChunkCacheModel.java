/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.model.impl;

import ai.assistance.service.model.DocumentChunk;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DocumentChunk in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DocumentChunkCacheModel
	implements CacheModel<DocumentChunk>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DocumentChunkCacheModel)) {
			return false;
		}

		DocumentChunkCacheModel documentChunkCacheModel =
			(DocumentChunkCacheModel)object;

		if (chunkId == documentChunkCacheModel.chunkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, chunkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{chunkId=");
		sb.append(chunkId);
		sb.append(", documentId=");
		sb.append(documentId);
		sb.append(", chunkText=");
		sb.append(chunkText);
		sb.append(", pageNumber=");
		sb.append(pageNumber);
		sb.append(", embedding=");
		sb.append(embedding);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DocumentChunk toEntityModel() {
		DocumentChunkImpl documentChunkImpl = new DocumentChunkImpl();

		documentChunkImpl.setChunkId(chunkId);
		documentChunkImpl.setDocumentId(documentId);

		if (chunkText == null) {
			documentChunkImpl.setChunkText("");
		}
		else {
			documentChunkImpl.setChunkText(chunkText);
		}

		documentChunkImpl.setPageNumber(pageNumber);

		if (embedding == null) {
			documentChunkImpl.setEmbedding("");
		}
		else {
			documentChunkImpl.setEmbedding(embedding);
		}

		documentChunkImpl.resetOriginalValues();

		return documentChunkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		chunkId = objectInput.readLong();

		documentId = objectInput.readLong();
		chunkText = objectInput.readUTF();

		pageNumber = objectInput.readInt();
		embedding = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(chunkId);

		objectOutput.writeLong(documentId);

		if (chunkText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(chunkText);
		}

		objectOutput.writeInt(pageNumber);

		if (embedding == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(embedding);
		}
	}

	public long chunkId;
	public long documentId;
	public String chunkText;
	public int pageNumber;
	public String embedding;

}
// LIFERAY-SERVICE-BUILDER-HASH:3691251