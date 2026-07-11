/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.service.persistence.impl;

import com.hrms.employee.exception.NoSuchDesignationException;
import com.hrms.employee.model.Designation;
import com.hrms.employee.model.DesignationTable;
import com.hrms.employee.model.impl.DesignationImpl;
import com.hrms.employee.model.impl.DesignationModelImpl;
import com.hrms.employee.service.persistence.DesignationPersistence;
import com.hrms.employee.service.persistence.DesignationUtil;
import com.hrms.employee.service.persistence.impl.constants.HRMSPersistenceConstants;

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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the designation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DesignationPersistence.class)
public class DesignationPersistenceImpl
	extends BasePersistenceImpl<Designation> implements DesignationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DesignationUtil</code> to access the designation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DesignationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DesignationPersistenceImpl() {
		setModelClass(Designation.class);

		setModelImplClass(DesignationImpl.class);
		setModelPKClass(long.class);

		setTable(DesignationTable.INSTANCE);
	}

	/**
	 * Caches the designation in the entity cache if it is enabled.
	 *
	 * @param designation the designation
	 */
	@Override
	public void cacheResult(Designation designation) {
		entityCache.putResult(
			DesignationImpl.class, designation.getPrimaryKey(), designation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the designations in the entity cache if it is enabled.
	 *
	 * @param designations the designations
	 */
	@Override
	public void cacheResult(List<Designation> designations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (designations.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Designation designation : designations) {
			if (entityCache.getResult(
					DesignationImpl.class, designation.getPrimaryKey()) ==
						null) {

				cacheResult(designation);
			}
		}
	}

	/**
	 * Clears the cache for all designations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DesignationImpl.class);

		finderCache.clearCache(DesignationImpl.class);
	}

	/**
	 * Clears the cache for the designation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Designation designation) {
		entityCache.removeResult(DesignationImpl.class, designation);
	}

	@Override
	public void clearCache(List<Designation> designations) {
		for (Designation designation : designations) {
			entityCache.removeResult(DesignationImpl.class, designation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DesignationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DesignationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new designation with the primary key. Does not add the designation to the database.
	 *
	 * @param designationId the primary key for the new designation
	 * @return the new designation
	 */
	@Override
	public Designation create(long designationId) {
		Designation designation = new DesignationImpl();

		designation.setNew(true);
		designation.setPrimaryKey(designationId);

		designation.setCompanyId(CompanyThreadLocal.getCompanyId());

		return designation;
	}

	/**
	 * Removes the designation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param designationId the primary key of the designation
	 * @return the designation that was removed
	 * @throws NoSuchDesignationException if a designation with the primary key could not be found
	 */
	@Override
	public Designation remove(long designationId)
		throws NoSuchDesignationException {

		return remove((Serializable)designationId);
	}

	/**
	 * Removes the designation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the designation
	 * @return the designation that was removed
	 * @throws NoSuchDesignationException if a designation with the primary key could not be found
	 */
	@Override
	public Designation remove(Serializable primaryKey)
		throws NoSuchDesignationException {

		Session session = null;

		try {
			session = openSession();

			Designation designation = (Designation)session.get(
				DesignationImpl.class, primaryKey);

			if (designation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDesignationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(designation);
		}
		catch (NoSuchDesignationException noSuchEntityException) {
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
	protected Designation removeImpl(Designation designation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(designation)) {
				designation = (Designation)session.get(
					DesignationImpl.class, designation.getPrimaryKeyObj());
			}

			if (designation != null) {
				session.delete(designation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (designation != null) {
			clearCache(designation);
		}

		return designation;
	}

	@Override
	public Designation updateImpl(Designation designation) {
		boolean isNew = designation.isNew();

		if (!(designation instanceof DesignationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(designation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(designation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in designation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Designation implementation " +
					designation.getClass());
		}

		DesignationModelImpl designationModelImpl =
			(DesignationModelImpl)designation;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (designation.getCreateDate() == null)) {
			if (serviceContext == null) {
				designation.setCreateDate(date);
			}
			else {
				designation.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!designationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				designation.setModifiedDate(date);
			}
			else {
				designation.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(designation);
			}
			else {
				designation = (Designation)session.merge(designation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(DesignationImpl.class, designation, false, true);

		if (isNew) {
			designation.setNew(false);
		}

		designation.resetOriginalValues();

		return designation;
	}

	/**
	 * Returns the designation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the designation
	 * @return the designation
	 * @throws NoSuchDesignationException if a designation with the primary key could not be found
	 */
	@Override
	public Designation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDesignationException {

		Designation designation = fetchByPrimaryKey(primaryKey);

		if (designation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDesignationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return designation;
	}

	/**
	 * Returns the designation with the primary key or throws a <code>NoSuchDesignationException</code> if it could not be found.
	 *
	 * @param designationId the primary key of the designation
	 * @return the designation
	 * @throws NoSuchDesignationException if a designation with the primary key could not be found
	 */
	@Override
	public Designation findByPrimaryKey(long designationId)
		throws NoSuchDesignationException {

		return findByPrimaryKey((Serializable)designationId);
	}

	/**
	 * Returns the designation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param designationId the primary key of the designation
	 * @return the designation, or <code>null</code> if a designation with the primary key could not be found
	 */
	@Override
	public Designation fetchByPrimaryKey(long designationId) {
		return fetchByPrimaryKey((Serializable)designationId);
	}

	/**
	 * Returns all the designations.
	 *
	 * @return the designations
	 */
	@Override
	public List<Designation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of designations
	 * @param end the upper bound of the range of designations (not inclusive)
	 * @return the range of designations
	 */
	@Override
	public List<Designation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of designations
	 * @param end the upper bound of the range of designations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of designations
	 */
	@Override
	public List<Designation> findAll(
		int start, int end, OrderByComparator<Designation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of designations
	 * @param end the upper bound of the range of designations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of designations
	 */
	@Override
	public List<Designation> findAll(
		int start, int end, OrderByComparator<Designation> orderByComparator,
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

		List<Designation> list = null;

		if (useFinderCache) {
			list = (List<Designation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DESIGNATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DESIGNATION;

				sql = sql.concat(DesignationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Designation>)QueryUtil.list(
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
	 * Removes all the designations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Designation designation : findAll()) {
			remove(designation);
		}
	}

	/**
	 * Returns the number of designations.
	 *
	 * @return the number of designations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DESIGNATION);

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
		return "designationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DESIGNATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DesignationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the designation persistence.
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

		DesignationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DesignationUtil.setPersistence(null);

		entityCache.removeCache(DesignationImpl.class.getName());
	}

	@Override
	@Reference(
		target = HRMSPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = HRMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = HRMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DESIGNATION =
		"SELECT designation FROM Designation designation";

	private static final String _SQL_COUNT_DESIGNATION =
		"SELECT COUNT(designation) FROM Designation designation";

	private static final String _ORDER_BY_ENTITY_ALIAS = "designation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Designation exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DesignationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1580501478