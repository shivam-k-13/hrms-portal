/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link AttendanceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AttendanceLocalService
 * @generated
 */
public class AttendanceLocalServiceWrapper
	implements AttendanceLocalService, ServiceWrapper<AttendanceLocalService> {

	public AttendanceLocalServiceWrapper() {
		this(null);
	}

	public AttendanceLocalServiceWrapper(
		AttendanceLocalService attendanceLocalService) {

		_attendanceLocalService = attendanceLocalService;
	}

	/**
	 * Adds the attendance to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AttendanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param attendance the attendance
	 * @return the attendance that was added
	 */
	@Override
	public com.hrms.attendance.model.Attendance addAttendance(
		com.hrms.attendance.model.Attendance attendance) {

		return _attendanceLocalService.addAttendance(attendance);
	}

	/**
	 * Creates a new attendance with the primary key. Does not add the attendance to the database.
	 *
	 * @param attendanceId the primary key for the new attendance
	 * @return the new attendance
	 */
	@Override
	public com.hrms.attendance.model.Attendance createAttendance(
		long attendanceId) {

		return _attendanceLocalService.createAttendance(attendanceId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _attendanceLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the attendance from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AttendanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param attendance the attendance
	 * @return the attendance that was removed
	 */
	@Override
	public com.hrms.attendance.model.Attendance deleteAttendance(
		com.hrms.attendance.model.Attendance attendance) {

		return _attendanceLocalService.deleteAttendance(attendance);
	}

	/**
	 * Deletes the attendance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AttendanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance that was removed
	 * @throws PortalException if a attendance with the primary key could not be found
	 */
	@Override
	public com.hrms.attendance.model.Attendance deleteAttendance(
			long attendanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _attendanceLocalService.deleteAttendance(attendanceId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _attendanceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _attendanceLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _attendanceLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _attendanceLocalService.dynamicQuery();
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

		return _attendanceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.attendance.model.impl.AttendanceModelImpl</code>.
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

		return _attendanceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.attendance.model.impl.AttendanceModelImpl</code>.
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

		return _attendanceLocalService.dynamicQuery(
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

		return _attendanceLocalService.dynamicQueryCount(dynamicQuery);
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

		return _attendanceLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.hrms.attendance.model.Attendance fetchAttendance(
		long attendanceId) {

		return _attendanceLocalService.fetchAttendance(attendanceId);
	}

	/**
	 * Exposes the finder method to the public AttendanceLocalService interface.
	 */
	@Override
	public com.hrms.attendance.model.Attendance fetchEmployeeAndDate(
		long employeeId, java.util.Date attendanceDate) {

		return _attendanceLocalService.fetchEmployeeAndDate(
			employeeId, attendanceDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _attendanceLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the attendance with the primary key.
	 *
	 * @param attendanceId the primary key of the attendance
	 * @return the attendance
	 * @throws PortalException if a attendance with the primary key could not be found
	 */
	@Override
	public com.hrms.attendance.model.Attendance getAttendance(long attendanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _attendanceLocalService.getAttendance(attendanceId);
	}

	/**
	 * Returns a range of all the attendances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.attendance.model.impl.AttendanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of attendances
	 * @param end the upper bound of the range of attendances (not inclusive)
	 * @return the range of attendances
	 */
	@Override
	public java.util.List<com.hrms.attendance.model.Attendance> getAttendances(
		int start, int end) {

		return _attendanceLocalService.getAttendances(start, end);
	}

	/**
	 * Returns the number of attendances.
	 *
	 * @return the number of attendances
	 */
	@Override
	public int getAttendancesCount() {
		return _attendanceLocalService.getAttendancesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _attendanceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _attendanceLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _attendanceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the attendance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AttendanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param attendance the attendance
	 * @return the attendance that was updated
	 */
	@Override
	public com.hrms.attendance.model.Attendance updateAttendance(
		com.hrms.attendance.model.Attendance attendance) {

		return _attendanceLocalService.updateAttendance(attendance);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _attendanceLocalService.getBasePersistence();
	}

	@Override
	public AttendanceLocalService getWrappedService() {
		return _attendanceLocalService;
	}

	@Override
	public void setWrappedService(
		AttendanceLocalService attendanceLocalService) {

		_attendanceLocalService = attendanceLocalService;
	}

	private AttendanceLocalService _attendanceLocalService;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1190602591