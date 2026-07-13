/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.service.persistence;

import com.hrms.leave.model.LeaveRequest;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the leave request service. This utility wraps <code>com.hrms.leave.service.persistence.impl.LeaveRequestPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LeaveRequestPersistence
 * @generated
 */
public class LeaveRequestUtil {

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
	public static void clearCache(LeaveRequest leaveRequest) {
		getPersistence().clearCache(leaveRequest);
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
	public static Map<Serializable, LeaveRequest> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LeaveRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LeaveRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LeaveRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LeaveRequest update(LeaveRequest leaveRequest) {
		return getPersistence().update(leaveRequest);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LeaveRequest update(
		LeaveRequest leaveRequest, ServiceContext serviceContext) {

		return getPersistence().update(leaveRequest, serviceContext);
	}

	/**
	 * Returns all the leave requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching leave requests
	 */
	public static List<LeaveRequest> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the leave requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public static List<LeaveRequest> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public static LeaveRequest findByUuid_First(
			String uuid, OrderByComparator<LeaveRequest> orderByComparator)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public static LeaveRequest fetchByUuid_First(
		String uuid, OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Removes all the leave requests where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of leave requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching leave requests
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the leave request where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLeaveRequestException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public static LeaveRequest findByUUID_G(String uuid, long groupId)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the leave request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public static LeaveRequest fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the leave request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public static LeaveRequest fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the leave request where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the leave request that was removed
	 */
	public static LeaveRequest removeByUUID_G(String uuid, long groupId)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of leave requests where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching leave requests
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching leave requests
	 */
	public static List<LeaveRequest> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public static List<LeaveRequest> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
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
	public static List<LeaveRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public static LeaveRequest findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LeaveRequest> orderByComparator)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first leave request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public static LeaveRequest fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the leave requests where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of leave requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching leave requests
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the leave requests where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the matching leave requests
	 */
	public static List<LeaveRequest> findByEmployeeId(long employeeId) {
		return getPersistence().findByEmployeeId(employeeId);
	}

	/**
	 * Returns a range of all the leave requests where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public static List<LeaveRequest> findByEmployeeId(
		long employeeId, int start, int end) {

		return getPersistence().findByEmployeeId(employeeId, start, end);
	}

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByEmployeeId(
		long employeeId, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().findByEmployeeId(
			employeeId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByEmployeeId(
		long employeeId, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByEmployeeId(
			employeeId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public static LeaveRequest findByEmployeeId_First(
			long employeeId, OrderByComparator<LeaveRequest> orderByComparator)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().findByEmployeeId_First(
			employeeId, orderByComparator);
	}

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public static LeaveRequest fetchByEmployeeId_First(
		long employeeId, OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().fetchByEmployeeId_First(
			employeeId, orderByComparator);
	}

	/**
	 * Removes all the leave requests where employeeId = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 */
	public static void removeByEmployeeId(long employeeId) {
		getPersistence().removeByEmployeeId(employeeId);
	}

	/**
	 * Returns the number of leave requests where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the number of matching leave requests
	 */
	public static int countByEmployeeId(long employeeId) {
		return getPersistence().countByEmployeeId(employeeId);
	}

	/**
	 * Returns all the leave requests where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching leave requests
	 */
	public static List<LeaveRequest> findByStatus(String status) {
		return getPersistence().findByStatus(status);
	}

	/**
	 * Returns a range of all the leave requests where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public static List<LeaveRequest> findByStatus(
		String status, int start, int end) {

		return getPersistence().findByStatus(status, start, end);
	}

	/**
	 * Returns an ordered range of all the leave requests where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByStatus(
		String status, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the leave requests where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByStatus(
		String status, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first leave request in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public static LeaveRequest findByStatus_First(
			String status, OrderByComparator<LeaveRequest> orderByComparator)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the first leave request in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public static LeaveRequest fetchByStatus_First(
		String status, OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	 * Removes all the leave requests where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public static void removeByStatus(String status) {
		getPersistence().removeByStatus(status);
	}

	/**
	 * Returns the number of leave requests where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching leave requests
	 */
	public static int countByStatus(String status) {
		return getPersistence().countByStatus(status);
	}

	/**
	 * Returns all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @return the matching leave requests
	 */
	public static List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status) {

		return getPersistence().findByEmployeeId_Status(employeeId, status);
	}

	/**
	 * Returns a range of all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of matching leave requests
	 */
	public static List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status, int start, int end) {

		return getPersistence().findByEmployeeId_Status(
			employeeId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching leave requests
	 */
	public static List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().findByEmployeeId_Status(
			employeeId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
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
	public static List<LeaveRequest> findByEmployeeId_Status(
		long employeeId, String status, int start, int end,
		OrderByComparator<LeaveRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByEmployeeId_Status(
			employeeId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request
	 * @throws NoSuchLeaveRequestException if a matching leave request could not be found
	 */
	public static LeaveRequest findByEmployeeId_Status_First(
			long employeeId, String status,
			OrderByComparator<LeaveRequest> orderByComparator)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().findByEmployeeId_Status_First(
			employeeId, status, orderByComparator);
	}

	/**
	 * Returns the first leave request in the ordered set where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	public static LeaveRequest fetchByEmployeeId_Status_First(
		long employeeId, String status,
		OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().fetchByEmployeeId_Status_First(
			employeeId, status, orderByComparator);
	}

	/**
	 * Removes all the leave requests where employeeId = &#63; and status = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 */
	public static void removeByEmployeeId_Status(
		long employeeId, String status) {

		getPersistence().removeByEmployeeId_Status(employeeId, status);
	}

	/**
	 * Returns the number of leave requests where employeeId = &#63; and status = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param status the status
	 * @return the number of matching leave requests
	 */
	public static int countByEmployeeId_Status(long employeeId, String status) {
		return getPersistence().countByEmployeeId_Status(employeeId, status);
	}

	/**
	 * Caches the leave request in the entity cache if it is enabled.
	 *
	 * @param leaveRequest the leave request
	 */
	public static void cacheResult(LeaveRequest leaveRequest) {
		getPersistence().cacheResult(leaveRequest);
	}

	/**
	 * Caches the leave requests in the entity cache if it is enabled.
	 *
	 * @param leaveRequests the leave requests
	 */
	public static void cacheResult(List<LeaveRequest> leaveRequests) {
		getPersistence().cacheResult(leaveRequests);
	}

	/**
	 * Creates a new leave request with the primary key. Does not add the leave request to the database.
	 *
	 * @param leaveRequestId the primary key for the new leave request
	 * @return the new leave request
	 */
	public static LeaveRequest create(long leaveRequestId) {
		return getPersistence().create(leaveRequestId);
	}

	/**
	 * Removes the leave request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request that was removed
	 * @throws NoSuchLeaveRequestException if a leave request with the primary key could not be found
	 */
	public static LeaveRequest remove(long leaveRequestId)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().remove(leaveRequestId);
	}

	public static LeaveRequest updateImpl(LeaveRequest leaveRequest) {
		return getPersistence().updateImpl(leaveRequest);
	}

	/**
	 * Returns the leave request with the primary key or throws a <code>NoSuchLeaveRequestException</code> if it could not be found.
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request
	 * @throws NoSuchLeaveRequestException if a leave request with the primary key could not be found
	 */
	public static LeaveRequest findByPrimaryKey(long leaveRequestId)
		throws com.hrms.leave.exception.NoSuchLeaveRequestException {

		return getPersistence().findByPrimaryKey(leaveRequestId);
	}

	/**
	 * Returns the leave request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request, or <code>null</code> if a leave request with the primary key could not be found
	 */
	public static LeaveRequest fetchByPrimaryKey(long leaveRequestId) {
		return getPersistence().fetchByPrimaryKey(leaveRequestId);
	}

	/**
	 * Returns all the leave requests.
	 *
	 * @return the leave requests
	 */
	public static List<LeaveRequest> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the leave requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @return the range of leave requests
	 */
	public static List<LeaveRequest> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the leave requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of leave requests
	 */
	public static List<LeaveRequest> findAll(
		int start, int end, OrderByComparator<LeaveRequest> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the leave requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LeaveRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of leave requests
	 */
	public static List<LeaveRequest> findAll(
		int start, int end, OrderByComparator<LeaveRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the leave requests from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of leave requests.
	 *
	 * @return the number of leave requests
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LeaveRequestPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(LeaveRequestPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile LeaveRequestPersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:184462879