/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DocumentChunk}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentChunk
 * @generated
 */
public class DocumentChunkWrapper
	extends BaseModelWrapper<DocumentChunk>
	implements DocumentChunk, ModelWrapper<DocumentChunk> {

	public DocumentChunkWrapper(DocumentChunk documentChunk) {
		super(documentChunk);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("chunkId", getChunkId());
		attributes.put("documentId", getDocumentId());
		attributes.put("chunkText", getChunkText());
		attributes.put("pageNumber", getPageNumber());
		attributes.put("embedding", getEmbedding());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long chunkId = (Long)attributes.get("chunkId");

		if (chunkId != null) {
			setChunkId(chunkId);
		}

		Long documentId = (Long)attributes.get("documentId");

		if (documentId != null) {
			setDocumentId(documentId);
		}

		String chunkText = (String)attributes.get("chunkText");

		if (chunkText != null) {
			setChunkText(chunkText);
		}

		Integer pageNumber = (Integer)attributes.get("pageNumber");

		if (pageNumber != null) {
			setPageNumber(pageNumber);
		}

		String embedding = (String)attributes.get("embedding");

		if (embedding != null) {
			setEmbedding(embedding);
		}
	}

	@Override
	public DocumentChunk cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the chunk ID of this document chunk.
	 *
	 * @return the chunk ID of this document chunk
	 */
	@Override
	public long getChunkId() {
		return model.getChunkId();
	}

	/**
	 * Returns the chunk text of this document chunk.
	 *
	 * @return the chunk text of this document chunk
	 */
	@Override
	public String getChunkText() {
		return model.getChunkText();
	}

	/**
	 * Returns the document ID of this document chunk.
	 *
	 * @return the document ID of this document chunk
	 */
	@Override
	public long getDocumentId() {
		return model.getDocumentId();
	}

	/**
	 * Returns the embedding of this document chunk.
	 *
	 * @return the embedding of this document chunk
	 */
	@Override
	public String getEmbedding() {
		return model.getEmbedding();
	}

	/**
	 * Returns the page number of this document chunk.
	 *
	 * @return the page number of this document chunk
	 */
	@Override
	public int getPageNumber() {
		return model.getPageNumber();
	}

	/**
	 * Returns the primary key of this document chunk.
	 *
	 * @return the primary key of this document chunk
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the chunk ID of this document chunk.
	 *
	 * @param chunkId the chunk ID of this document chunk
	 */
	@Override
	public void setChunkId(long chunkId) {
		model.setChunkId(chunkId);
	}

	/**
	 * Sets the chunk text of this document chunk.
	 *
	 * @param chunkText the chunk text of this document chunk
	 */
	@Override
	public void setChunkText(String chunkText) {
		model.setChunkText(chunkText);
	}

	/**
	 * Sets the document ID of this document chunk.
	 *
	 * @param documentId the document ID of this document chunk
	 */
	@Override
	public void setDocumentId(long documentId) {
		model.setDocumentId(documentId);
	}

	/**
	 * Sets the embedding of this document chunk.
	 *
	 * @param embedding the embedding of this document chunk
	 */
	@Override
	public void setEmbedding(String embedding) {
		model.setEmbedding(embedding);
	}

	/**
	 * Sets the page number of this document chunk.
	 *
	 * @param pageNumber the page number of this document chunk
	 */
	@Override
	public void setPageNumber(int pageNumber) {
		model.setPageNumber(pageNumber);
	}

	/**
	 * Sets the primary key of this document chunk.
	 *
	 * @param primaryKey the primary key of this document chunk
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DocumentChunkWrapper wrap(DocumentChunk documentChunk) {
		return new DocumentChunkWrapper(documentChunk);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1494538617