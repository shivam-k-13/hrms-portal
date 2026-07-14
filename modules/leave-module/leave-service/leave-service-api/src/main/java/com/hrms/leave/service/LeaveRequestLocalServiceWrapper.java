/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link LeaveRequestLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LeaveRequestLocalService
 * @generated
 */
public class LeaveRequestLocalServiceWrapper
	implements LeaveRequestLocalService,
			   ServiceWrapper<LeaveRequestLocalService> {

	public LeaveRequestLocalServiceWrapper() {
		this(null);
	}

	public LeaveRequestLocalServiceWrapper(
		LeaveRequestLocalService leaveRequestLocalService) {

		_leaveRequestLocalService = leaveRequestLocalService;
	}

	/**
	 * Adds the leave request to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LeaveRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param leaveRequest the leave request
	 * @return the leave request that was added
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest addLeaveRequest(
		com.hrms.leave.model.LeaveRequest leaveRequest) {

		return _leaveRequestLocalService.addLeaveRequest(leaveRequest);
	}

	@Override
	public com.hrms.leave.model.LeaveRequest applyLeave(
			long employeeId, String leaveType, java.util.Date fromDate,
			java.util.Date toDate, String reason,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.applyLeave(
			employeeId, leaveType, fromDate, toDate, reason, serviceContext);
	}

	@Override
	public com.hrms.leave.model.LeaveRequest approveLeave(
			long leaveRequestId, long approverUserId, String approverComments)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.approveLeave(
			leaveRequestId, approverUserId, approverComments);
	}

	/**
	 * Creates a new leave request with the primary key. Does not add the leave request to the database.
	 *
	 * @param leaveRequestId the primary key for the new leave request
	 * @return the new leave request
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest createLeaveRequest(
		long leaveRequestId) {

		return _leaveRequestLocalService.createLeaveRequest(leaveRequestId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the leave request from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LeaveRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param leaveRequest the leave request
	 * @return the leave request that was removed
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest deleteLeaveRequest(
		com.hrms.leave.model.LeaveRequest leaveRequest) {

		return _leaveRequestLocalService.deleteLeaveRequest(leaveRequest);
	}

	/**
	 * Deletes the leave request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LeaveRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request that was removed
	 * @throws PortalException if a leave request with the primary key could not be found
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest deleteLeaveRequest(
			long leaveRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.deleteLeaveRequest(leaveRequestId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _leaveRequestLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _leaveRequestLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _leaveRequestLocalService.dynamicQuery();
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

		return _leaveRequestLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
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

		return _leaveRequestLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.leave.model.impl.LeaveRequestModelImpl</code>.
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

		return _leaveRequestLocalService.dynamicQuery(
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

		return _leaveRequestLocalService.dynamicQueryCount(dynamicQuery);
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

		return _leaveRequestLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.hrms.leave.model.LeaveRequest fetchLeaveRequest(
		long leaveRequestId) {

		return _leaveRequestLocalService.fetchLeaveRequest(leaveRequestId);
	}

	/**
	 * Returns the leave request matching the UUID and group.
	 *
	 * @param uuid the leave request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching leave request, or <code>null</code> if a matching leave request could not be found
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest fetchLeaveRequestByUuidAndGroupId(
		String uuid, long groupId) {

		return _leaveRequestLocalService.fetchLeaveRequestByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _leaveRequestLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _leaveRequestLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _leaveRequestLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the leave request with the primary key.
	 *
	 * @param leaveRequestId the primary key of the leave request
	 * @return the leave request
	 * @throws PortalException if a leave request with the primary key could not be found
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest getLeaveRequest(
			long leaveRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.getLeaveRequest(leaveRequestId);
	}

	/**
	 * Returns the leave request matching the UUID and group.
	 *
	 * @param uuid the leave request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching leave request
	 * @throws PortalException if a matching leave request could not be found
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest getLeaveRequestByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.getLeaveRequestByUuidAndGroupId(
			uuid, groupId);
	}

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
	@Override
	public java.util.List<com.hrms.leave.model.LeaveRequest> getLeaveRequests(
		int start, int end) {

		return _leaveRequestLocalService.getLeaveRequests(start, end);
	}

	@Override
	public java.util.List<com.hrms.leave.model.LeaveRequest>
		getLeaveRequestsByEmployeeId(long employeeId, int start, int end) {

		return _leaveRequestLocalService.getLeaveRequestsByEmployeeId(
			employeeId, start, end);
	}

	/**
	 * Returns all the leave requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the leave requests
	 * @param companyId the primary key of the company
	 * @return the matching leave requests, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.hrms.leave.model.LeaveRequest>
		getLeaveRequestsByUuidAndCompanyId(String uuid, long companyId) {

		return _leaveRequestLocalService.getLeaveRequestsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of leave requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the leave requests
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of leave requests
	 * @param end the upper bound of the range of leave requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching leave requests, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.hrms.leave.model.LeaveRequest>
		getLeaveRequestsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.hrms.leave.model.LeaveRequest> orderByComparator) {

		return _leaveRequestLocalService.getLeaveRequestsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of leave requests.
	 *
	 * @return the number of leave requests
	 */
	@Override
	public int getLeaveRequestsCount() {
		return _leaveRequestLocalService.getLeaveRequestsCount();
	}

	@Override
	public int getLeaveRequestsCountByEmployeeId(long employeeId) {
		return _leaveRequestLocalService.getLeaveRequestsCountByEmployeeId(
			employeeId);
	}

	@Override
	public java.util.List<com.hrms.leave.model.LeaveRequest>
		getLeavesByEmployeeId(long employeeId) {

		return _leaveRequestLocalService.getLeavesByEmployeeId(employeeId);
	}

	@Override
	public java.util.List<com.hrms.leave.model.LeaveRequest> getLeavesByStatus(
		String status) {

		return _leaveRequestLocalService.getLeavesByStatus(status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _leaveRequestLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.hrms.leave.model.LeaveRequest rejectLeave(
			long leaveRequestId, long approverUserId, String approverComments)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _leaveRequestLocalService.rejectLeave(
			leaveRequestId, approverUserId, approverComments);
	}

	/**
	 * Updates the leave request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LeaveRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param leaveRequest the leave request
	 * @return the leave request that was updated
	 */
	@Override
	public com.hrms.leave.model.LeaveRequest updateLeaveRequest(
		com.hrms.leave.model.LeaveRequest leaveRequest) {

		return _leaveRequestLocalService.updateLeaveRequest(leaveRequest);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _leaveRequestLocalService.getBasePersistence();
	}

	@Override
	public LeaveRequestLocalService getWrappedService() {
		return _leaveRequestLocalService;
	}

	@Override
	public void setWrappedService(
		LeaveRequestLocalService leaveRequestLocalService) {

		_leaveRequestLocalService = leaveRequestLocalService;
	}

	private LeaveRequestLocalService _leaveRequestLocalService;

}
// LIFERAY-SERVICE-BUILDER-HASH:1427945110