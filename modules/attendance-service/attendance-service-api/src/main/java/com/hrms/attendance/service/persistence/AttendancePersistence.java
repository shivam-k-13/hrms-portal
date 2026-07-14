/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.service.persistence;

import com.hrms.attendance.exception.NoSuchAttendanceException;
import com.hrms.attendance.model.Attendance;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the attendance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AttendanceUtil
 * @generated
 */
@ProviderType
public interface AttendancePersistence extends BasePersistence<Attendance> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AttendanceUtil} to access the attendance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the attendances where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the matching attendances
	 */
	public java.util.List<Attendance> findByEmployeeId(long employeeId);

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
	public java.util.List<Attendance> findByEmployeeId(
		long employeeId, int start, int end);

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
	public java.util.List<Attendance> findByEmployeeId(
		long employeeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Attendance>
			orderByComparator);

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
	public java.util.List<Attendance> findByEmployeeId(
		long employeeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Attendance>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first attendance in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attendance
	 * @throws NoSuchAttendanceException if a matching attendance could not be found
	 */
	public Attendance findByEmployeeId_First(
			long employeeId,
			com.liferay.portal.kernel.util.OrderByComparator<Attendance>
				orderByComparator)
		throws NoSuchAttendanceException;

	/**
	 * Returns the first attendance in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	public Attendance fetchByEmployeeId_First(
		long employeeId,
		com.liferay.portal.kernel.util.OrderByComparator<Attendance>
			orderByComparator);

	/**
	 * Removes all the attendances where employeeId = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 */
	public void removeByEmployeeId(long employeeId);

	/**
	 * Returns the number of attendances where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the number of matching attendances
	 */
	public int countByEmployeeId(long employeeId);

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or throws a <code>NoSuchAttendanceException</code> if it could not be found.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the matching attendance
	 * @throws NoSuchAttendanceException if a matching attendance could not be found
	 */
	public Attendance findByEmployeeAndDate(
			long employeeId, Date attendanceDate)
		throws NoSuchAttendanceException;

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	public Attendance fetchByEmployeeAndDate(
		long employeeId, Date attendanceDate);

	/**
	 * Returns the attendance where employeeId = &#63; and attendanceDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching attendance, or <code>null</code> if a matching attendance could not be found
	 */
	public Attendance fetchByEmployeeAndDate(
		long employeeId, Date attendanceDate, boolean useFinderCache);

	/**
	 * Removes the attendance where employeeId = &#63; and attendanceDate = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the attendance that was removed
	 */
	public Attendance removeByEmployeeAndDate(
			long employeeId, Date attendanceDate)
		throws NoSuchAttendanceException;

	/**
	 * Returns the number of attendances where employeeId = &#63; and attendanceDate = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param attendanceDate the attendance date
	 * @return the number of matching attendances
	 */
	public int countByEmployeeAndDate(long employeeId, Date attendanceDate);

	/**
	 * Caches the attendance in the entity cache if it is enabled.
	 *
	 * @param attendance the attendance
	 */
	public void cacheResult(Attendance attendance);

	/**
	 * Caches the attendances in the entity cache if it is enabled.
	 *
	 * @param attendances the attendances
	 */
	public void cacheResult(java.util.List<Attendance> attendances);

	/**
	 * Creates a new attendance with the primary key. Does not add the attendance to the database.
	 *
	 * @param attendanceId the primary key for the new attendance
	 * @return the new attendance
	 */
	public Attendance create(long attendanceId);

	/**
	 * Removes the attendance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance that was removed
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	public Attendance remove(long attendanceId)
		throws NoSuchAttendanceException;

	public Attendance updateImpl(Attendance attendance);

	/**
	 * Returns the attendance with the primary key or throws a <code>NoSuchAttendanceException</code> if it could not be found.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance
	 * @throws NoSuchAttendanceException if a attendance with the primary key could not be found
	 */
	public Attendance findByPrimaryKey(long attendanceId)
		throws NoSuchAttendanceException;

	/**
	 * Returns the attendance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance, or <code>null</code> if a attendance with the primary key could not be found
	 */
	public Attendance fetchByPrimaryKey(long attendanceId);

	/**
	 * Returns all the attendances.
	 *
	 * @return the attendances
	 */
	public java.util.List<Attendance> findAll();

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
	public java.util.List<Attendance> findAll(int start, int end);

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
	public java.util.List<Attendance> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Attendance>
			orderByComparator);

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
	public java.util.List<Attendance> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Attendance>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the attendances from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of attendances.
	 *
	 * @return the number of attendances
	 */
	public int countAll();

}
// LIFERAY-SERVICE-BUILDER-HASH:503994869