/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.persistence;

import ai.assistance.service.model.DocumentChunk;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the document chunk service. This utility wraps <code>ai.assistance.service.service.persistence.impl.DocumentChunkPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentChunkPersistence
 * @generated
 */
public class DocumentChunkUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(DocumentChunk documentChunk) {
		getPersistence().clearCache(documentChunk);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, DocumentChunk> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DocumentChunk> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DocumentChunk> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DocumentChunk> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DocumentChunk> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DocumentChunk update(DocumentChunk documentChunk) {
		return getPersistence().update(documentChunk);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DocumentChunk update(
		DocumentChunk documentChunk, ServiceContext serviceContext) {

		return getPersistence().update(documentChunk, serviceContext);
	}

	/**
	 * Caches the document chunk in the entity cache if it is enabled.
	 *
	 * @param documentChunk the document chunk
	 */
	public static void cacheResult(DocumentChunk documentChunk) {
		getPersistence().cacheResult(documentChunk);
	}

	/**
	 * Caches the document chunks in the entity cache if it is enabled.
	 *
	 * @param documentChunks the document chunks
	 */
	public static void cacheResult(List<DocumentChunk> documentChunks) {
		getPersistence().cacheResult(documentChunks);
	}

	/**
	 * Creates a new document chunk with the primary key. Does not add the document chunk to the database.
	 *
	 * @param chunkId the primary key for the new document chunk
	 * @return the new document chunk
	 */
	public static DocumentChunk create(long chunkId) {
		return getPersistence().create(chunkId);
	}

	/**
	 * Removes the document chunk with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk that was removed
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	public static DocumentChunk remove(long chunkId)
		throws ai.assistance.service.exception.NoSuchDocumentChunkException {

		return getPersistence().remove(chunkId);
	}

	public static DocumentChunk updateImpl(DocumentChunk documentChunk) {
		return getPersistence().updateImpl(documentChunk);
	}

	/**
	 * Returns the document chunk with the primary key or throws a <code>NoSuchDocumentChunkException</code> if it could not be found.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	public static DocumentChunk findByPrimaryKey(long chunkId)
		throws ai.assistance.service.exception.NoSuchDocumentChunkException {

		return getPersistence().findByPrimaryKey(chunkId);
	}

	/**
	 * Returns the document chunk with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk, or <code>null</code> if a document chunk with the primary key could not be found
	 */
	public static DocumentChunk fetchByPrimaryKey(long chunkId) {
		return getPersistence().fetchByPrimaryKey(chunkId);
	}

	/**
	 * Returns all the document chunks.
	 *
	 * @return the document chunks
	 */
	public static List<DocumentChunk> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<DocumentChunk> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<DocumentChunk> findAll(
		int start, int end,
		OrderByComparator<DocumentChunk> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<DocumentChunk> findAll(
		int start, int end, OrderByComparator<DocumentChunk> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the document chunks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of document chunks.
	 *
	 * @return the number of document chunks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DocumentChunkPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(DocumentChunkPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile DocumentChunkPersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:-229013894