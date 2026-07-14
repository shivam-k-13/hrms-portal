/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.persistence;

import ai.assistance.service.exception.NoSuchDocumentChunkException;
import ai.assistance.service.model.DocumentChunk;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the document chunk service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentChunkUtil
 * @generated
 */
@ProviderType
public interface DocumentChunkPersistence
	extends BasePersistence<DocumentChunk> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentChunkUtil} to access the document chunk persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the document chunk in the entity cache if it is enabled.
	 *
	 * @param documentChunk the document chunk
	 */
	public void cacheResult(DocumentChunk documentChunk);

	/**
	 * Caches the document chunks in the entity cache if it is enabled.
	 *
	 * @param documentChunks the document chunks
	 */
	public void cacheResult(java.util.List<DocumentChunk> documentChunks);

	/**
	 * Creates a new document chunk with the primary key. Does not add the document chunk to the database.
	 *
	 * @param chunkId the primary key for the new document chunk
	 * @return the new document chunk
	 */
	public DocumentChunk create(long chunkId);

	/**
	 * Removes the document chunk with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk that was removed
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	public DocumentChunk remove(long chunkId)
		throws NoSuchDocumentChunkException;

	public DocumentChunk updateImpl(DocumentChunk documentChunk);

	/**
	 * Returns the document chunk with the primary key or throws a <code>NoSuchDocumentChunkException</code> if it could not be found.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	public DocumentChunk findByPrimaryKey(long chunkId)
		throws NoSuchDocumentChunkException;

	/**
	 * Returns the document chunk with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk, or <code>null</code> if a document chunk with the primary key could not be found
	 */
	public DocumentChunk fetchByPrimaryKey(long chunkId);

	/**
	 * Returns all the document chunks.
	 *
	 * @return the document chunks
	 */
	public java.util.List<DocumentChunk> findAll();

	/**
	 * Returns a range of all the document chunks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentChunkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of document chunks
	 * @param end the upper bound of the range of document chunks (not inclusive)
	 * @return the range of document chunks
	 */
	public java.util.List<DocumentChunk> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the document chunks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentChunkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of document chunks
	 * @param end the upper bound of the range of document chunks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of document chunks
	 */
	public java.util.List<DocumentChunk> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentChunk>
			orderByComparator);

	/**
	 * Returns an ordered range of all the document chunks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentChunkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of document chunks
	 * @param end the upper bound of the range of document chunks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of document chunks
	 */
	public java.util.List<DocumentChunk> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentChunk>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the document chunks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of document chunks.
	 *
	 * @return the number of document chunks
	 */
	public int countAll();

}
// LIFERAY-SERVICE-BUILDER-HASH:-718395222