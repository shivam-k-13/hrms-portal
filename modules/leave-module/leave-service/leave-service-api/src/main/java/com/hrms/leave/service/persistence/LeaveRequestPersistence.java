/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.service.persistence;

import com.hrms.leave.exception.NoSuchLeaveRequestException;
import com.hrms.leave.model.LeaveRequest;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the leave request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LeaveRequestUtil
 * @generated
 */
@ProviderType
public interface LeaveRequestPersistence extends BasePersistence<LeaveRequest> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LeaveRequestUtil} to access the leave request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the leave requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid(String uuid);

	/**
	 * Returns a range of all the leave requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public LeaveRequest findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
				orderByComparator)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public LeaveRequest fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Removes all the leave requests where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of leave requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching leave requests
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the leave request where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLeaveRequestException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public LeaveRequest findByUUID_G(String uuid, long groupId)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the leave request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public LeaveRequest fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the leave request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public LeaveRequest fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the leave request where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the leave request that was removed
	 */
	public LeaveRequest removeByUUID_G(String uuid, long groupId)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the number of leave requests where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching leave requests
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public LeaveRequest findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
				orderByComparator)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public LeaveRequest fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Removes all the leave requests where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching leave requests
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the leave requests where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId(long employeeId);

	/**
	 * Returns a range of all the leave requests where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId(
		long employeeId, int start, int end);

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId(
		long employeeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId(
		long employeeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public LeaveRequest findByEmployeeId_First(
			long employeeId,
			com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
				orderByComparator)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public LeaveRequest fetchByEmployeeId_First(
		long employeeId,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Removes all the leave requests where employeeId = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 */
	public void removeByEmployeeId(long employeeId);

	/**
	 * Returns the number of leave requests where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the number of matching leave requests
	 */
	public int countByEmployeeId(long employeeId);

	/**
	 * Returns all the leave requests where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching leave requests
	 */
	public java.util.List<LeaveRequest> findByStatus(String status);

	/**
	 * Returns a range of all the leave requests where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByStatus(
		String status, int start, int end);

	/**
	 * Returns an ordered range of all the leave requests where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the leave requests where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first leave request in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public LeaveRequest findByStatus_First(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
				orderByComparator)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the first leave request in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public LeaveRequest fetchByStatus_First(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Removes all the leave requests where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public void removeByStatus(String status);

	/**
	 * Returns the number of leave requests where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching leave requests
	 */
	public int countByStatus(String status);

	/**
	 * Returns all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @return the matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status);

	/**
	 * Returns a range of all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status, int start, int end);

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public java.util.List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public LeaveRequest findByEmployeeId_Status_First(
			long employeeId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
				orderByComparator)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public LeaveRequest fetchByEmployeeId_Status_First(
		long employeeId, String status,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Removes all the leave requests where employeeId = &#63; and status = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 */
	public void removeByEmployeeId_Status(long employeeId, String status);

	/**
	 * Returns the number of leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @return the number of matching leave requests
	 */
	public int countByEmployeeId_Status(long employeeId, String status);

	/**
	 * Caches the leave request in the entity cache if it is enabled.
	 *
	 * @param leaveRequest the leave request
	 */
	public void cacheResult(LeaveRequest leaveRequest);

	/**
	 * Caches the leave requests in the entity cache if it is enabled.
	 *
	 * @param leaveRequests the leave requests
	 */
	public void cacheResult(java.util.List<LeaveRequest> leaveRequests);

	/**
	 * Creates a new leave request with the primary key. Does not add the leave request to the database.
	 *
	 * @param leaveRequestId the primary key for the new leave request
	 * @return the new leave request
	 */
	public LeaveRequest create(long leaveRequestId);

	/**
	 * Removes the leave request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request that was removed
	 * @throws NoSuchLeaveRequestException if a leave request with the primary key could not be found
	 */
	public LeaveRequest remove(long leaveRequestId)
		throws NoSuchLeaveRequestException;

	public LeaveRequest updateImpl(LeaveRequest leaveRequest);

	/**
	 * Returns the leave request with the primary key or throws a <code>NoSuchLeaveRequestException</code> if it could not be found.
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request
	 * @throws NoSuchLeaveRequestException if a leave request with the primary key could not be found
	 */
	public LeaveRequest findByPrimaryKey(long leaveRequestId)
		throws NoSuchLeaveRequestException;

	/**
	 * Returns the leave request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request, or <code>null</code> if a leave request with the primary key could not be found
	 */
	public LeaveRequest fetchByPrimaryKey(long leaveRequestId);

	/**
	 * Returns all the leave requests.
	 *
	 * @return the leave requests
	 */
	public java.util.List<LeaveRequest> findAll();

	/**
	 * Returns a range of all the leave requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of leave requests
	 */
	public java.util.List<LeaveRequest> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the leave requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of leave requests
	 */
	public java.util.List<LeaveRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the leave requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of leave requests
	 */
	public java.util.List<LeaveRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LeaveRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the leave requests from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of leave requests.
	 *
	 * @return the number of leave requests
	 */
	public int countAll();

}
// LIFERAY-SERVICE-BUILDER-HASH:-229586901