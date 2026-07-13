/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.persistence;

import ai.assistance.service.exception.NoSuchDocumentException;
import ai.assistance.service.model.Document;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the document service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentUtil
 * @generated
 */
@ProviderType
public interface DocumentPersistence extends BasePersistence<Document> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentUtil} to access the document persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the document in the entity cache if it is enabled.
	 *
	 * @param document the document
	 */
	public void cacheResult(Document document);

	/**
	 * Caches the documents in the entity cache if it is enabled.
	 *
	 * @param documents the documents
	 */
	public void cacheResult(java.util.List<Document> documents);

	/**
	 * Creates a new document with the primary key. Does not add the document to the database.
	 *
	 * @param documentId the primary key for the new document
	 * @return the new document
	 */
	public Document create(long documentId);

	/**
	 * Removes the document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param documentId the primary key of the document
	 * @return the document that was removed
	 * @throws NoSuchDocumentException if a document with the primary key could not be found
	 */
	public Document remove(long documentId) throws NoSuchDocumentException;

	public Document updateImpl(Document document);

	/**
	 * Returns the document with the primary key or throws a <code>NoSuchDocumentException</code> if it could not be found.
	 *
	 * @param documentId the primary key of the document
	 * @return the document
	 * @throws NoSuchDocumentException if a document with the primary key could not be found
	 */
	public Document findByPrimaryKey(long documentId)
		throws NoSuchDocumentException;

	/**
	 * Returns the document with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param documentId the primary key of the document
	 * @return the document, or <code>null</code> if a document with the primary key could not be found
	 */
	public Document fetchByPrimaryKey(long documentId);

	/**
	 * Returns all the documents.
	 *
	 * @return the documents
	 */
	public java.util.List<Document> findAll();

	/**
	 * Returns a range of all the documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documents
	 * @param end the upper bound of the range of documents (not inclusive)
	 * @return the range of documents
	 */
	public java.util.List<Document> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documents
	 * @param end the upper bound of the range of documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of documents
	 */
	public java.util.List<Document> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Document>
			orderByComparator);

	/**
	 * Returns an ordered range of all the documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocumentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of documents
	 * @param end the upper bound of the range of documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of documents
	 */
	public java.util.List<Document> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Document>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the documents from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of documents.
	 *
	 * @return the number of documents
	 */
	public int countAll();

}
// LIFERAY-SERVICE-BUILDER-HASH:-727109862