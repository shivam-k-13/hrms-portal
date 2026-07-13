/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.persistence.impl;

import ai.assistance.service.exception.NoSuchDocumentChunkException;
import ai.assistance.service.model.DocumentChunk;
import ai.assistance.service.model.DocumentChunkTable;
import ai.assistance.service.model.impl.DocumentChunkImpl;
import ai.assistance.service.model.impl.DocumentChunkModelImpl;
import ai.assistance.service.service.persistence.DocumentChunkPersistence;
import ai.assistance.service.service.persistence.DocumentChunkUtil;
import ai.assistance.service.service.persistence.impl.constants.AIPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the document chunk service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DocumentChunkPersistence.class)
public class DocumentChunkPersistenceImpl
	extends BasePersistenceImpl<DocumentChunk>
	implements DocumentChunkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DocumentChunkUtil</code> to access the document chunk persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DocumentChunkImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DocumentChunkPersistenceImpl() {
		setModelClass(DocumentChunk.class);

		setModelImplClass(DocumentChunkImpl.class);
		setModelPKClass(long.class);

		setTable(DocumentChunkTable.INSTANCE);
	}

	/**
	 * Caches the document chunk in the entity cache if it is enabled.
	 *
	 * @param documentChunk the document chunk
	 */
	@Override
	public void cacheResult(DocumentChunk documentChunk) {
		entityCache.putResult(
			DocumentChunkImpl.class, documentChunk.getPrimaryKey(),
			documentChunk);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the document chunks in the entity cache if it is enabled.
	 *
	 * @param documentChunks the document chunks
	 */
	@Override
	public void cacheResult(List<DocumentChunk> documentChunks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (documentChunks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DocumentChunk documentChunk : documentChunks) {
			if (entityCache.getResult(
					DocumentChunkImpl.class, documentChunk.getPrimaryKey()) ==
						null) {

				cacheResult(documentChunk);
			}
		}
	}

	/**
	 * Clears the cache for all document chunks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DocumentChunkImpl.class);

		finderCache.clearCache(DocumentChunkImpl.class);
	}

	/**
	 * Clears the cache for the document chunk.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DocumentChunk documentChunk) {
		entityCache.removeResult(DocumentChunkImpl.class, documentChunk);
	}

	@Override
	public void clearCache(List<DocumentChunk> documentChunks) {
		for (DocumentChunk documentChunk : documentChunks) {
			entityCache.removeResult(DocumentChunkImpl.class, documentChunk);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DocumentChunkImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DocumentChunkImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new document chunk with the primary key. Does not add the document chunk to the database.
	 *
	 * @param chunkId the primary key for the new document chunk
	 * @return the new document chunk
	 */
	@Override
	public DocumentChunk create(long chunkId) {
		DocumentChunk documentChunk = new DocumentChunkImpl();

		documentChunk.setNew(true);
		documentChunk.setPrimaryKey(chunkId);

		return documentChunk;
	}

	/**
	 * Removes the document chunk with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk that was removed
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	@Override
	public DocumentChunk remove(long chunkId)
		throws NoSuchDocumentChunkException {

		return remove((Serializable)chunkId);
	}

	/**
	 * Removes the document chunk with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the document chunk
	 * @return the document chunk that was removed
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	@Override
	public DocumentChunk remove(Serializable primaryKey)
		throws NoSuchDocumentChunkException {

		Session session = null;

		try {
			session = openSession();

			DocumentChunk documentChunk = (DocumentChunk)session.get(
				DocumentChunkImpl.class, primaryKey);

			if (documentChunk == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocumentChunkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(documentChunk);
		}
		catch (NoSuchDocumentChunkException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DocumentChunk removeImpl(DocumentChunk documentChunk) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(documentChunk)) {
				documentChunk = (DocumentChunk)session.get(
					DocumentChunkImpl.class, documentChunk.getPrimaryKeyObj());
			}

			if (documentChunk != null) {
				session.delete(documentChunk);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (documentChunk != null) {
			clearCache(documentChunk);
		}

		return documentChunk;
	}

	@Override
	public DocumentChunk updateImpl(DocumentChunk documentChunk) {
		boolean isNew = documentChunk.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(documentChunk);
			}
			else {
				documentChunk = (DocumentChunk)session.merge(documentChunk);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DocumentChunkImpl.class, documentChunk, false, true);

		if (isNew) {
			documentChunk.setNew(false);
		}

		documentChunk.resetOriginalValues();

		return documentChunk;
	}

	/**
	 * Returns the document chunk with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the document chunk
	 * @return the document chunk
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	@Override
	public DocumentChunk findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocumentChunkException {

		DocumentChunk documentChunk = fetchByPrimaryKey(primaryKey);

		if (documentChunk == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocumentChunkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return documentChunk;
	}

	/**
	 * Returns the document chunk with the primary key or throws a <code>NoSuchDocumentChunkException</code> if it could not be found.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk
	 * @throws NoSuchDocumentChunkException if a document chunk with the primary key could not be found
	 */
	@Override
	public DocumentChunk findByPrimaryKey(long chunkId)
		throws NoSuchDocumentChunkException {

		return findByPrimaryKey((Serializable)chunkId);
	}

	/**
	 * Returns the document chunk with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chunkId the primary key of the document chunk
	 * @return the document chunk, or <code>null</code> if a document chunk with the primary key could not be found
	 */
	@Override
	public DocumentChunk fetchByPrimaryKey(long chunkId) {
		return fetchByPrimaryKey((Serializable)chunkId);
	}

	/**
	 * Returns all the document chunks.
	 *
	 * @return the document chunks
	 */
	@Override
	public List<DocumentChunk> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<DocumentChunk> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<DocumentChunk> findAll(
		int start, int end,
		OrderByComparator<DocumentChunk> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<DocumentChunk> findAll(
		int start, int end, OrderByComparator<DocumentChunk> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<DocumentChunk> list = null;

		if (useFinderCache) {
			list = (List<DocumentChunk>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DOCUMENTCHUNK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DOCUMENTCHUNK;

				sql = sql.concat(DocumentChunkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DocumentChunk>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the document chunks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DocumentChunk documentChunk : findAll()) {
			remove(documentChunk);
		}
	}

	/**
	 * Returns the number of document chunks.
	 *
	 * @return the number of document chunks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DOCUMENTCHUNK);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "chunkId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DOCUMENTCHUNK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DocumentChunkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the document chunk persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		DocumentChunkUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DocumentChunkUtil.setPersistence(null);

		entityCache.removeCache(DocumentChunkImpl.class.getName());
	}

	@Override
	@Reference(
		target = AIPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AIPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AIPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DOCUMENTCHUNK =
		"SELECT documentChunk FROM DocumentChunk documentChunk";

	private static final String _SQL_COUNT_DOCUMENTCHUNK =
		"SELECT COUNT(documentChunk) FROM DocumentChunk documentChunk";

	private static final String _ORDER_BY_ENTITY_ALIAS = "documentChunk.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DocumentChunk exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DocumentChunkPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:919055172