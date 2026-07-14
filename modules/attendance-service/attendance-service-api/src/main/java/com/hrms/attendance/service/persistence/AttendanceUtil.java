/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.service.persistence;

import com.hrms.attendance.model.Attendance;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the attendance service. This utility wraps <code>com.hrms.attendance.service.persistence.impl.AttendancePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AttendancePersistence
 * @generated
 */
public class AttendanceUtil {

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
	public static void clearCache(Attendance attendance) {
		getPersistence().clearCache(attendance);
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
	public static Map<Serializable, Attendance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Attendance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Attendance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Attendance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Attendance> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Attendance update(Attendance attendance) {
		return getPersistence().update(attendance);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Attendance update(
		Attendance attendance, ServiceContext serviceContext) {

		return getPersistence().update(attendance, serviceContext);
	}

	/**
	 * Returns all the attendances where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the matching attendances
	 */
	public static List<Attendance> findByEmployeeId(long employeeId) {
		return getPersistence().findByEmployeeId(employeeId);
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
	public static List<Attendance> findByEmployeeId(
		long employeeId, int start, int end) {

		return getPersistence().findByEmployeeId(employeeId, start, end);
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
	public static List<Attendance> findByEmployeeId(
		long employeeId, int start, int end,
		OrderByComparator<Attendance> orderByComparator) {

		return getPersistence().findByEmployeeId(
			employeeId, start, end, orderByComparator);
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
	public static List<Attendance> findByEmployeeId(
		long employeeId, int start, int end,
		OrderByComparator<Attendance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByEmployeeId(
			employeeId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first attendance in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attendance
	 * @throws NoSuchAttendanceException if a matching attendance could not be found
	 */
	public static Attendance findByEmployeeId_First(
			long employeeId, OrderByComparator<Attendance> orderByComparator)
		throws com.hrms.attendance.exception.NoSuchAttendanceException {

		return getPersistence().findByEmployeeId_First(
			employeeId, orderByComparator);
	}

	/**
	 * Returns the first attendance in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	public static Attendance fetchByEmployeeId_First(
		long employeeId, OrderByComparator<Attendance> orderByComparator) {

		return getPersistence().fetchByEmployeeId_First(
			employeeId, orderByComparator);
	}

	/**
	 * Removes all the attendances where employeeId = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 */
	public static void removeByEmployeeId(long employeeId) {
		getPersistence().removeByEmployeeId(employeeId);
	}

	/**
	 * Returns the number of attendances where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the number of matching attendances
	 */
	public static int countByEmployeeId(long employeeId) {
		return getPersistence().countByEmployeeId(employeeId);
	}

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or throws a <code>NoSuchAttendanceException</code> if it could not be found.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the matching attendance
	 * @throws NoSuchAttendanceException if a matching attendance could not be found
	 */
	public static Attendance findByEmployeeAndDate(
			long employeeId, Date attendanceDate)
		throws com.hrms.attendance.exception.NoSuchAttendanceException {

		return getPersistence().findByEmployeeAndDate(
			employeeId, attendanceDate);
	}

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	public static Attendance fetchByEmployeeAndDate(
		long employeeId, Date attendanceDate) {

		return getPersistence().fetchByEmployeeAndDate(
			employeeId, attendanceDate);
	}

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	public static Attendance fetchByEmployeeAndDate(
		long employeeId, Date attendanceDate, boolean useFinderCache) {

		return getPersistence().fetchByEmployeeAndDate(
			employeeId, attendanceDate, useFinderCache);
	}

	/**
	 * Removes the attendance where employeeId = &#63; and attendanceDate = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the attendance that was removed
	 */
	public static Attendance removeByEmployeeAndDate(
			long employeeId, Date attendanceDate)
		throws com.hrms.attendance.exception.NoSuchAttendanceException {

		return getPersistence().removeByEmployeeAndDate(
			employeeId, attendanceDate);
	}

	/**
	 * Returns the number of attendances where employeeId = &#63; and attendanceDate = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the number of matching attendances
	 */
	public static int countByEmployeeAndDate(
		long employeeId, Date attendanceDate) {

		return getPersistence().countByEmployeeAndDate(
			employeeId, attendanceDate);
	}

	/**
	 * Caches the attendance in the entity cache if it is enabled.
	 *
	 * @param attendance the attendance
	 */
	public static void cacheResult(Attendance attendance) {
		getPersistence().cacheResult(attendance);
	}

	/**
	 * Caches the attendances in the entity cache if it is enabled.
	 *
	 * @param attendances the attendances
	 */
	public static void cacheResult(List<Attendance> attendances) {
		getPersistence().cacheResult(attendances);
	}

	/**
	 * Creates a new attendance with the primary key. Does not add the attendance to the database.
	 *
	 * @param attendanceId the primary key for the new attendance
	 * @return the new attendance
	 */
	public static Attendance create(long attendanceId) {
		return getPersistence().create(attendanceId);
	}

	/**
	 * Removes the attendance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance that was removed
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	public static Attendance remove(long attendanceId)
		throws com.hrms.attendance.exception.NoSuchAttendanceException {

		return getPersistence().remove(attendanceId);
	}

	public static Attendance updateImpl(Attendance attendance) {
		return getPersistence().updateImpl(attendance);
	}

	/**
	 * Returns the attendance with the primary key or throws a <code>NoSuchAttendanceException</code> if it could not be found.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	public static Attendance findByPrimaryKey(long attendanceId)
		throws com.hrms.attendance.exception.NoSuchAttendanceException {

		return getPersistence().findByPrimaryKey(attendanceId);
	}

	/**
	 * Returns the attendance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance, or <code>null</code> if a attendance with the primary key could not be found
	 */
	public static Attendance fetchByPrimaryKey(long attendanceId) {
		return getPersistence().fetchByPrimaryKey(attendanceId);
	}

	/**
	 * Returns all the attendances.
	 *
	 * @return the attendances
	 */
	public static List<Attendance> findAll() {
		return getPersistence().findAll();
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
	public static List<Attendance> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<Attendance> findAll(
		int start, int end, OrderByComparator<Attendance> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<Attendance> findAll(
		int start, int end, OrderByComparator<Attendance> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the attendances from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of attendances.
	 *
	 * @return the number of attendances
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AttendancePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AttendancePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AttendancePersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:280041543