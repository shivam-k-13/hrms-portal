/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link DocumentChunkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentChunkLocalService
 * @generated
 */
public class DocumentChunkLocalServiceWrapper
	implements DocumentChunkLocalService,
			   ServiceWrapper<DocumentChunkLocalService> {

	public DocumentChunkLocalServiceWrapper() {
		this(null);
	}

	public DocumentChunkLocalServiceWrapper(
		DocumentChunkLocalService documentChunkLocalService) {

		_documentChunkLocalService = documentChunkLocalService;
	}

	/**
	 * Adds the document chunk to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentChunkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param documentChunk the document chunk
	 * @return the document chunk that was added
	 */
	@Override
	public ai.assistance.service.model.DocumentChunk addDocumentChunk(
		ai.assistance.service.model.DocumentChunk documentChunk) {

		return _documentChunkLocalService.addDocumentChunk(documentChunk);
	}

	/**
	 * Creates a new document chunk with the primary key. Does not add the document chunk to the database.
	 *
	 * @param chunkId the primary key for the new document chunk
	 * @return the new document chunk
	 */
	@Override
	public ai.assistance.service.model.DocumentChunk createDocumentChunk(
		long chunkId) {

		return _documentChunkLocalService.createDocumentChunk(chunkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentChunkLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the document chunk from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentChunkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param documentChunk the document chunk
	 * @return the document chunk that was removed
	 */
	@Override
	public ai.assistance.service.model.DocumentChunk deleteDocumentChunk(
		ai.assistance.service.model.DocumentChunk documentChunk) {

		return _documentChunkLocalService.deleteDocumentChunk(documentChunk);
	}

	/**
	 * Deletes the document chunk with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentChunkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk that was removed
	 * @throws PortalException if a document chunk with the primary key could not be found
	 */
	@Override
	public ai.assistance.service.model.DocumentChunk deleteDocumentChunk(
			long chunkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentChunkLocalService.deleteDocumentChunk(chunkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentChunkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _documentChunkLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _documentChunkLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _documentChunkLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _documentChunkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ai.assistance.service.model.impl.DocumentChunkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _documentChunkLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ai.assistance.service.model.impl.DocumentChunkModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _documentChunkLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _documentChunkLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _documentChunkLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public ai.assistance.service.model.DocumentChunk fetchDocumentChunk(
		long chunkId) {

		return _documentChunkLocalService.fetchDocumentChunk(chunkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _documentChunkLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the document chunk with the primary key.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk
	 * @throws PortalException if a document chunk with the primary key could not be found
	 */
	@Override
	public ai.assistance.service.model.DocumentChunk getDocumentChunk(
			long chunkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentChunkLocalService.getDocumentChunk(chunkId);
	}

	/**
	 * Returns a range of all the document chunks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ai.assistance.service.model.impl.DocumentChunkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of document chunks
	 * @param end the upper bound of the range of document chunks (not inclusive)
	 * @return the range of document chunks
	 */
	@Override
	public java.util.List<ai.assistance.service.model.DocumentChunk>
		getDocumentChunks(int start, int end) {

		return _documentChunkLocalService.getDocumentChunks(start, end);
	}

	/**
	 * Returns the number of document chunks.
	 *
	 * @return the number of document chunks
	 */
	@Override
	public int getDocumentChunksCount() {
		return _documentChunkLocalService.getDocumentChunksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _documentChunkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _documentChunkLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentChunkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the document chunk in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DocumentChunkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param documentChunk the document chunk
	 * @return the document chunk that was updated
	 */
	@Override
	public ai.assistance.service.model.DocumentChunk updateDocumentChunk(
		ai.assistance.service.model.DocumentChunk documentChunk) {

		return _documentChunkLocalService.updateDocumentChunk(documentChunk);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _documentChunkLocalService.getBasePersistence();
	}

	@Override
	public DocumentChunkLocalService getWrappedService() {
		return _documentChunkLocalService;
	}

	@Override
	public void setWrappedService(
		DocumentChunkLocalService documentChunkLocalService) {

		_documentChunkLocalService = documentChunkLocalService;
	}

	private DocumentChunkLocalService _documentChunkLocalService;

}
// LIFERAY-SERVICE-BUILDER-HASH:-284127935