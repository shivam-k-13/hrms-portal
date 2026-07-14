/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.service.persistence.impl;

import com.hrms.attendance.exception.NoSuchAttendanceException;
import com.hrms.attendance.model.Attendance;
import com.hrms.attendance.model.AttendanceTable;
import com.hrms.attendance.model.impl.AttendanceImpl;
import com.hrms.attendance.model.impl.AttendanceModelImpl;
import com.hrms.attendance.service.persistence.AttendancePersistence;
import com.hrms.attendance.service.persistence.AttendanceUtil;
import com.hrms.attendance.service.persistence.impl.constants.HRMSPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the attendance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AttendancePersistence.class)
public class AttendancePersistenceImpl
	extends BasePersistenceImpl<Attendance> implements AttendancePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AttendanceUtil</code> to access the attendance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AttendanceImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByEmployeeId;
	private FinderPath _finderPathWithoutPaginationFindByEmployeeId;
	private FinderPath _finderPathCountByEmployeeId;

	/**
	 * Returns all the attendances where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the matching attendances
	 */
	@Override
	public List<Attendance> findByEmployeeId(long employeeId) {
		return findByEmployeeId(
			employeeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the attendances where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AttendanceModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of attendances
	 * @param end the upper bound of the range of attendances (not inclusive)
	 * @return the range of matching attendances
	 */
	@Override
	public List<Attendance> findByEmployeeId(
		long employeeId, int start, int end) {

		return findByEmployeeId(employeeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the attendances where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AttendanceModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of attendances
	 * @param end the upper bound of the range of attendances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching attendances
	 */
	@Override
	public List<Attendance> findByEmployeeId(
		long employeeId, int start, int end,
		OrderByComparator<Attendance> orderByComparator) {

		return findByEmployeeId(
			employeeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the attendances where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AttendanceModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of attendances
	 * @param end the upper bound of the range of attendances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching attendances
	 */
	@Override
	public List<Attendance> findByEmployeeId(
		long employeeId, int start, int end,
		OrderByComparator<Attendance> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByEmployeeId;
				finderArgs = new Object[] {employeeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByEmployeeId;
			finderArgs = new Object[] {
				employeeId, start, end, orderByComparator
			};
		}

		List<Attendance> list = null;

		if (useFinderCache) {
			list = (List<Attendance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Attendance attendance : list) {
					if (employeeId != attendance.getEmployeeId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_ATTENDANCE_WHERE);

			sb.append(_FINDER_COLUMN_EMPLOYEEID_EMPLOYEEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AttendanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(employeeId);

				list = (List<Attendance>)QueryUtil.list(
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
	 * Returns the first attendance in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attendance
	 * @throws NoSuchAttendanceException if a matching attendance could not be found
	 */
	@Override
	public Attendance findByEmployeeId_First(
			long employeeId, OrderByComparator<Attendance> orderByComparator)
		throws NoSuchAttendanceException {

		Attendance attendance = fetchByEmployeeId_First(
			employeeId, orderByComparator);

		if (attendance != null) {
			return attendance;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("employeeId=");
		sb.append(employeeId);

		sb.append("}");

		throw new NoSuchAttendanceException(sb.toString());
	}

	/**
	 * Returns the first attendance in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	@Override
	public Attendance fetchByEmployeeId_First(
		long employeeId, OrderByComparator<Attendance> orderByComparator) {

		List<Attendance> list = findByEmployeeId(
			employeeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the attendances where employeeId = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 */
	@Override
	public void removeByEmployeeId(long employeeId) {
		for (Attendance attendance :
				findByEmployeeId(
					employeeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(attendance);
		}
	}

	/**
	 * Returns the number of attendances where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the number of matching attendances
	 */
	@Override
	public int countByEmployeeId(long employeeId) {
		FinderPath finderPath = _finderPathCountByEmployeeId;

		Object[] finderArgs = new Object[] {employeeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ATTENDANCE_WHERE);

			sb.append(_FINDER_COLUMN_EMPLOYEEID_EMPLOYEEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(employeeId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_EMPLOYEEID_EMPLOYEEID_2 =
		"attendance.employeeId = ?";

	private FinderPath _finderPathFetchByEmployeeAndDate;

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or throws a <code>NoSuchAttendanceException</code> if it could not be found.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the matching attendance
	 * @throws NoSuchAttendanceException if a matching attendance could not be found
	 */
	@Override
	public Attendance findByEmployeeAndDate(
			long employeeId, Date attendanceDate)
		throws NoSuchAttendanceException {

		Attendance attendance = fetchByEmployeeAndDate(
			employeeId, attendanceDate);

		if (attendance == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("employeeId=");
			sb.append(employeeId);

			sb.append(", attendanceDate=");
			sb.append(attendanceDate);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAttendanceException(sb.toString());
		}

		return attendance;
	}

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	@Override
	public Attendance fetchByEmployeeAndDate(
		long employeeId, Date attendanceDate) {

		return fetchByEmployeeAndDate(employeeId, attendanceDate, true);
	}

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	@Override
	public Attendance fetchByEmployeeAndDate(
		long employeeId, Date attendanceDate, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {employeeId, _getTime(attendanceDate)};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByEmployeeAndDate, finderArgs, this);
		}

		if (result instanceof Attendance) {
			Attendance attendance = (Attendance)result;

			if ((employeeId != attendance.getEmployeeId()) ||
				!Objects.equals(
					attendanceDate, attendance.getAttendanceDate())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ATTENDANCE_WHERE);

			sb.append(_FINDER_COLUMN_EMPLOYEEANDDATE_EMPLOYEEID_2);

			boolean bindAttendanceDate = false;

			if (attendanceDate == null) {
				sb.append(_FINDER_COLUMN_EMPLOYEEANDDATE_ATTENDANCEDATE_1);
			}
			else {
				bindAttendanceDate = true;

				sb.append(_FINDER_COLUMN_EMPLOYEEANDDATE_ATTENDANCEDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(employeeId);

				if (bindAttendanceDate) {
					queryPos.add(new Timestamp(attendanceDate.getTime()));
				}

				List<Attendance> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByEmployeeAndDate, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									employeeId, _getTime(attendanceDate)
								};
							}

							_log.warn(
								"AttendancePersistenceImpl.fetchByEmployeeAndDate(long, Date, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Attendance attendance = list.get(0);

					result = attendance;

					cacheResult(attendance);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Attendance)result;
		}
	}

	/**
	 * Removes the attendance where employeeId = &#63; and attendanceDate = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the attendance that was removed
	 */
	@Override
	public Attendance removeByEmployeeAndDate(
			long employeeId, Date attendanceDate)
		throws NoSuchAttendanceException {

		Attendance attendance = findByEmployeeAndDate(
			employeeId, attendanceDate);

		return remove(attendance);
	}

	/**
	 * Returns the number of attendances where employeeId = &#63; and attendanceDate = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the number of matching attendances
	 */
	@Override
	public int countByEmployeeAndDate(long employeeId, Date attendanceDate) {
		Attendance attendance = fetchByEmployeeAndDate(
			employeeId, attendanceDate);

		if (attendance == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_EMPLOYEEANDDATE_EMPLOYEEID_2 =
		"attendance.employeeId = ? AND ";

	private static final String
		_FINDER_COLUMN_EMPLOYEEANDDATE_ATTENDANCEDATE_1 =
			"attendance.attendanceDate IS NULL";

	private static final String
		_FINDER_COLUMN_EMPLOYEEANDDATE_ATTENDANCEDATE_2 =
			"attendance.attendanceDate = ?";

	public AttendancePersistenceImpl() {
		setModelClass(Attendance.class);

		setModelImplClass(AttendanceImpl.class);
		setModelPKClass(long.class);

		setTable(AttendanceTable.INSTANCE);
	}

	/**
	 * Caches the attendance in the entity cache if it is enabled.
	 *
	 * @param attendance the attendance
	 */
	@Override
	public void cacheResult(Attendance attendance) {
		entityCache.putResult(
			AttendanceImpl.class, attendance.getPrimaryKey(), attendance);

		finderCache.putResult(
			_finderPathFetchByEmployeeAndDate,
			new Object[] {
				attendance.getEmployeeId(), attendance.getAttendanceDate()
			},
			attendance);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the attendances in the entity cache if it is enabled.
	 *
	 * @param attendances the attendances
	 */
	@Override
	public void cacheResult(List<Attendance> attendances) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (attendances.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Attendance attendance : attendances) {
			if (entityCache.getResult(
					AttendanceImpl.class, attendance.getPrimaryKey()) == null) {

				cacheResult(attendance);
			}
		}
	}

	/**
	 * Clears the cache for all attendances.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AttendanceImpl.class);

		finderCache.clearCache(AttendanceImpl.class);
	}

	/**
	 * Clears the cache for the attendance.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Attendance attendance) {
		entityCache.removeResult(AttendanceImpl.class, attendance);
	}

	@Override
	public void clearCache(List<Attendance> attendances) {
		for (Attendance attendance : attendances) {
			entityCache.removeResult(AttendanceImpl.class, attendance);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AttendanceImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AttendanceImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AttendanceModelImpl attendanceModelImpl) {

		Object[] args = new Object[] {
			attendanceModelImpl.getEmployeeId(),
			_getTime(attendanceModelImpl.getAttendanceDate())
		};

		finderCache.putResult(
			_finderPathFetchByEmployeeAndDate, args, attendanceModelImpl);
	}

	/**
	 * Creates a new attendance with the primary key. Does not add the attendance to the database.
	 *
	 * @param attendanceId the primary key for the new attendance
	 * @return the new attendance
	 */
	@Override
	public Attendance create(long attendanceId) {
		Attendance attendance = new AttendanceImpl();

		attendance.setNew(true);
		attendance.setPrimaryKey(attendanceId);

		attendance.setCompanyId(CompanyThreadLocal.getCompanyId());

		return attendance;
	}

	/**
	 * Removes the attendance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance that was removed
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	@Override
	public Attendance remove(long attendanceId)
		throws NoSuchAttendanceException {

		return remove((Serializable)attendanceId);
	}

	/**
	 * Removes the attendance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the attendance
	 * @return the attendance that was removed
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	@Override
	public Attendance remove(Serializable primaryKey)
		throws NoSuchAttendanceException {

		Session session = null;

		try {
			session = openSession();

			Attendance attendance = (Attendance)session.get(
				AttendanceImpl.class, primaryKey);

			if (attendance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAttendanceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(attendance);
		}
		catch (NoSuchAttendanceException noSuchEntityException) {
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
	protected Attendance removeImpl(Attendance attendance) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(attendance)) {
				attendance = (Attendance)session.get(
					AttendanceImpl.class, attendance.getPrimaryKeyObj());
			}

			if (attendance != null) {
				session.delete(attendance);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (attendance != null) {
			clearCache(attendance);
		}

		return attendance;
	}

	@Override
	public Attendance updateImpl(Attendance attendance) {
		boolean isNew = attendance.isNew();

		if (!(attendance instanceof AttendanceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(attendance.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(attendance);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in attendance proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Attendance implementation " +
					attendance.getClass());
		}

		AttendanceModelImpl attendanceModelImpl =
			(AttendanceModelImpl)attendance;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (attendance.getCreateDate() == null)) {
			if (serviceContext == null) {
				attendance.setCreateDate(date);
			}
			else {
				attendance.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!attendanceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				attendance.setModifiedDate(date);
			}
			else {
				attendance.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(attendance);
			}
			else {
				attendance = (Attendance)session.merge(attendance);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AttendanceImpl.class, attendanceModelImpl, false, true);

		cacheUniqueFindersCache(attendanceModelImpl);

		if (isNew) {
			attendance.setNew(false);
		}

		attendance.resetOriginalValues();

		return attendance;
	}

	/**
	 * Returns the attendance with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the attendance
	 * @return the attendance
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	@Override
	public Attendance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAttendanceException {

		Attendance attendance = fetchByPrimaryKey(primaryKey);

		if (attendance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAttendanceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return attendance;
	}

	/**
	 * Returns the attendance with the primary key or throws a <code>NoSuchAttendanceException</code> if it could not be found.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	@Override
	public Attendance findByPrimaryKey(long attendanceId)
		throws NoSuchAttendanceException {

		return findByPrimaryKey((Serializable)attendanceId);
	}

	/**
	 * Returns the attendance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance, or <code>null</code> if a attendance with the primary key could not be found
	 */
	@Override
	public Attendance fetchByPrimaryKey(long attendanceId) {
		return fetchByPrimaryKey((Serializable)attendanceId);
	}

	/**
	 * Returns all the attendances.
	 *
	 * @return the attendances
	 */
	@Override
	public List<Attendance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the attendances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AttendanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of attendances
	 * @param end the upper bound of the range of attendances (not inclusive)
	 * @return the range of attendances
	 */
	@Override
	public List<Attendance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the attendances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AttendanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of attendances
	 * @param end the upper bound of the range of attendances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of attendances
	 */
	@Override
	public List<Attendance> findAll(
		int start, int end, OrderByComparator<Attendance> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the attendances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AttendanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of attendances
	 * @param end the upper bound of the range of attendances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of attendances
	 */
	@Override
	public List<Attendance> findAll(
		int start, int end, OrderByComparator<Attendance> orderByComparator,
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

		List<Attendance> list = null;

		if (useFinderCache) {
			list = (List<Attendance>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ATTENDANCE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ATTENDANCE;

				sql = sql.concat(AttendanceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Attendance>)QueryUtil.list(
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
	 * Removes all the attendances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Attendance attendance : findAll()) {
			remove(attendance);
		}
	}

	/**
	 * Returns the number of attendances.
	 *
	 * @return the number of attendances
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ATTENDANCE);

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
		return "attendanceId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ATTENDANCE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AttendanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the attendance persistence.
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

		_finderPathWithPaginationFindByEmployeeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEmployeeId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"employeeId"}, true);

		_finderPathWithoutPaginationFindByEmployeeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEmployeeId",
			new String[] {Long.class.getName()}, new String[] {"employeeId"},
			true);

		_finderPathCountByEmployeeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmployeeId",
			new String[] {Long.class.getName()}, new String[] {"employeeId"},
			false);

		_finderPathFetchByEmployeeAndDate = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByEmployeeAndDate",
			new String[] {Long.class.getName(), Date.class.getName()},
			new String[] {"employeeId", "attendanceDate"}, true);

		AttendanceUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AttendanceUtil.setPersistence(null);

		entityCache.removeCache(AttendanceImpl.class.getName());
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

	private static Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_ATTENDANCE =
		"SELECT attendance FROM Attendance attendance";

	private static final String _SQL_SELECT_ATTENDANCE_WHERE =
		"SELECT attendance FROM Attendance attendance WHERE ";

	private static final String _SQL_COUNT_ATTENDANCE =
		"SELECT COUNT(attendance) FROM Attendance attendance";

	private static final String _SQL_COUNT_ATTENDANCE_WHERE =
		"SELECT COUNT(attendance) FROM Attendance attendance WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "attendance.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Attendance exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Attendance exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AttendancePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:430915203