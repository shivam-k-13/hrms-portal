/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Attendance}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Attendance
 * @generated
 */
public class AttendanceWrapper
	extends BaseModelWrapper<Attendance>
	implements Attendance, ModelWrapper<Attendance> {

	public AttendanceWrapper(Attendance attendance) {
		super(attendance);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("attendanceId", getAttendanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("attendanceDate", getAttendanceDate());
		attributes.put("checkInTime", getCheckInTime());
		attributes.put("checkOutTime", getCheckOutTime());
		attributes.put("checkInIP", getCheckInIP());
		attributes.put("checkOutIP", getCheckOutIP());
		attributes.put("checkInLatitude", getCheckInLatitude());
		attributes.put("checkInLongitude", getCheckInLongitude());
		attributes.put("checkOutLatitude", getCheckOutLatitude());
		attributes.put("checkOutLongitude", getCheckOutLongitude());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long attendanceId = (Long)attributes.get("attendanceId");

		if (attendanceId != null) {
			setAttendanceId(attendanceId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		Date attendanceDate = (Date)attributes.get("attendanceDate");

		if (attendanceDate != null) {
			setAttendanceDate(attendanceDate);
		}

		Date checkInTime = (Date)attributes.get("checkInTime");

		if (checkInTime != null) {
			setCheckInTime(checkInTime);
		}

		Date checkOutTime = (Date)attributes.get("checkOutTime");

		if (checkOutTime != null) {
			setCheckOutTime(checkOutTime);
		}

		String checkInIP = (String)attributes.get("checkInIP");

		if (checkInIP != null) {
			setCheckInIP(checkInIP);
		}

		String checkOutIP = (String)attributes.get("checkOutIP");

		if (checkOutIP != null) {
			setCheckOutIP(checkOutIP);
		}

		Double checkInLatitude = (Double)attributes.get("checkInLatitude");

		if (checkInLatitude != null) {
			setCheckInLatitude(checkInLatitude);
		}

		Double checkInLongitude = (Double)attributes.get("checkInLongitude");

		if (checkInLongitude != null) {
			setCheckInLongitude(checkInLongitude);
		}

		Double checkOutLatitude = (Double)attributes.get("checkOutLatitude");

		if (checkOutLatitude != null) {
			setCheckOutLatitude(checkOutLatitude);
		}

		Double checkOutLongitude = (Double)attributes.get("checkOutLongitude");

		if (checkOutLongitude != null) {
			setCheckOutLongitude(checkOutLongitude);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Attendance cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the attendance date of this attendance.
	 *
	 * @return the attendance date of this attendance
	 */
	@Override
	public Date getAttendanceDate() {
		return model.getAttendanceDate();
	}

	/**
	 * Returns the attendance ID of this attendance.
	 *
	 * @return the attendance ID of this attendance
	 */
	@Override
	public long getAttendanceId() {
		return model.getAttendanceId();
	}

	/**
	 * Returns the check in ip of this attendance.
	 *
	 * @return the check in ip of this attendance
	 */
	@Override
	public String getCheckInIP() {
		return model.getCheckInIP();
	}

	/**
	 * Returns the check in latitude of this attendance.
	 *
	 * @return the check in latitude of this attendance
	 */
	@Override
	public double getCheckInLatitude() {
		return model.getCheckInLatitude();
	}

	/**
	 * Returns the check in longitude of this attendance.
	 *
	 * @return the check in longitude of this attendance
	 */
	@Override
	public double getCheckInLongitude() {
		return model.getCheckInLongitude();
	}

	/**
	 * Returns the check in time of this attendance.
	 *
	 * @return the check in time of this attendance
	 */
	@Override
	public Date getCheckInTime() {
		return model.getCheckInTime();
	}

	/**
	 * Returns the check out ip of this attendance.
	 *
	 * @return the check out ip of this attendance
	 */
	@Override
	public String getCheckOutIP() {
		return model.getCheckOutIP();
	}

	/**
	 * Returns the check out latitude of this attendance.
	 *
	 * @return the check out latitude of this attendance
	 */
	@Override
	public double getCheckOutLatitude() {
		return model.getCheckOutLatitude();
	}

	/**
	 * Returns the check out longitude of this attendance.
	 *
	 * @return the check out longitude of this attendance
	 */
	@Override
	public double getCheckOutLongitude() {
		return model.getCheckOutLongitude();
	}

	/**
	 * Returns the check out time of this attendance.
	 *
	 * @return the check out time of this attendance
	 */
	@Override
	public Date getCheckOutTime() {
		return model.getCheckOutTime();
	}

	/**
	 * Returns the company ID of this attendance.
	 *
	 * @return the company ID of this attendance
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this attendance.
	 *
	 * @return the create date of this attendance
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the employee ID of this attendance.
	 *
	 * @return the employee ID of this attendance
	 */
	@Override
	public long getEmployeeId() {
		return model.getEmployeeId();
	}

	/**
	 * Returns the group ID of this attendance.
	 *
	 * @return the group ID of this attendance
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this attendance.
	 *
	 * @return the modified date of this attendance
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this attendance.
	 *
	 * @return the primary key of this attendance
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this attendance.
	 *
	 * @return the status of this attendance
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this attendance.
	 *
	 * @return the user ID of this attendance
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this attendance.
	 *
	 * @return the user uuid of this attendance
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the attendance date of this attendance.
	 *
	 * @param attendanceDate the attendance date of this attendance
	 */
	@Override
	public void setAttendanceDate(Date attendanceDate) {
		model.setAttendanceDate(attendanceDate);
	}

	/**
	 * Sets the attendance ID of this attendance.
	 *
	 * @param attendanceId the attendance ID of this attendance
	 */
	@Override
	public void setAttendanceId(long attendanceId) {
		model.setAttendanceId(attendanceId);
	}

	/**
	 * Sets the check in ip of this attendance.
	 *
	 * @param checkInIP the check in ip of this attendance
	 */
	@Override
	public void setCheckInIP(String checkInIP) {
		model.setCheckInIP(checkInIP);
	}

	/**
	 * Sets the check in latitude of this attendance.
	 *
	 * @param checkInLatitude the check in latitude of this attendance
	 */
	@Override
	public void setCheckInLatitude(double checkInLatitude) {
		model.setCheckInLatitude(checkInLatitude);
	}

	/**
	 * Sets the check in longitude of this attendance.
	 *
	 * @param checkInLongitude the check in longitude of this attendance
	 */
	@Override
	public void setCheckInLongitude(double checkInLongitude) {
		model.setCheckInLongitude(checkInLongitude);
	}

	/**
	 * Sets the check in time of this attendance.
	 *
	 * @param checkInTime the check in time of this attendance
	 */
	@Override
	public void setCheckInTime(Date checkInTime) {
		model.setCheckInTime(checkInTime);
	}

	/**
	 * Sets the check out ip of this attendance.
	 *
	 * @param checkOutIP the check out ip of this attendance
	 */
	@Override
	public void setCheckOutIP(String checkOutIP) {
		model.setCheckOutIP(checkOutIP);
	}

	/**
	 * Sets the check out latitude of this attendance.
	 *
	 * @param checkOutLatitude the check out latitude of this attendance
	 */
	@Override
	public void setCheckOutLatitude(double checkOutLatitude) {
		model.setCheckOutLatitude(checkOutLatitude);
	}

	/**
	 * Sets the check out longitude of this attendance.
	 *
	 * @param checkOutLongitude the check out longitude of this attendance
	 */
	@Override
	public void setCheckOutLongitude(double checkOutLongitude) {
		model.setCheckOutLongitude(checkOutLongitude);
	}

	/**
	 * Sets the check out time of this attendance.
	 *
	 * @param checkOutTime the check out time of this attendance
	 */
	@Override
	public void setCheckOutTime(Date checkOutTime) {
		model.setCheckOutTime(checkOutTime);
	}

	/**
	 * Sets the company ID of this attendance.
	 *
	 * @param companyId the company ID of this attendance
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this attendance.
	 *
	 * @param createDate the create date of this attendance
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the employee ID of this attendance.
	 *
	 * @param employeeId the employee ID of this attendance
	 */
	@Override
	public void setEmployeeId(long employeeId) {
		model.setEmployeeId(employeeId);
	}

	/**
	 * Sets the group ID of this attendance.
	 *
	 * @param groupId the group ID of this attendance
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this attendance.
	 *
	 * @param modifiedDate the modified date of this attendance
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this attendance.
	 *
	 * @param primaryKey the primary key of this attendance
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this attendance.
	 *
	 * @param status the status of this attendance
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this attendance.
	 *
	 * @param userId the user ID of this attendance
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this attendance.
	 *
	 * @param userUuid the user uuid of this attendance
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected AttendanceWrapper wrap(Attendance attendance) {
		return new AttendanceWrapper(attendance);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-799221253