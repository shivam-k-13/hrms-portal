/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.model.impl;

import com.hrms.attendance.model.Attendance;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Attendance in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AttendanceCacheModel
	implements CacheModel<Attendance>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AttendanceCacheModel)) {
			return false;
		}

		AttendanceCacheModel attendanceCacheModel =
			(AttendanceCacheModel)object;

		if (attendanceId == attendanceCacheModel.attendanceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, attendanceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{attendanceId=");
		sb.append(attendanceId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", attendanceDate=");
		sb.append(attendanceDate);
		sb.append(", checkInTime=");
		sb.append(checkInTime);
		sb.append(", checkOutTime=");
		sb.append(checkOutTime);
		sb.append(", checkInIP=");
		sb.append(checkInIP);
		sb.append(", checkOutIP=");
		sb.append(checkOutIP);
		sb.append(", checkInLatitude=");
		sb.append(checkInLatitude);
		sb.append(", checkInLongitude=");
		sb.append(checkInLongitude);
		sb.append(", checkOutLatitude=");
		sb.append(checkOutLatitude);
		sb.append(", checkOutLongitude=");
		sb.append(checkOutLongitude);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Attendance toEntityModel() {
		AttendanceImpl attendanceImpl = new AttendanceImpl();

		attendanceImpl.setAttendanceId(attendanceId);
		attendanceImpl.setGroupId(groupId);
		attendanceImpl.setCompanyId(companyId);
		attendanceImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			attendanceImpl.setCreateDate(null);
		}
		else {
			attendanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			attendanceImpl.setModifiedDate(null);
		}
		else {
			attendanceImpl.setModifiedDate(new Date(modifiedDate));
		}

		attendanceImpl.setEmployeeId(employeeId);

		if (attendanceDate == Long.MIN_VALUE) {
			attendanceImpl.setAttendanceDate(null);
		}
		else {
			attendanceImpl.setAttendanceDate(new Date(attendanceDate));
		}

		if (checkInTime == Long.MIN_VALUE) {
			attendanceImpl.setCheckInTime(null);
		}
		else {
			attendanceImpl.setCheckInTime(new Date(checkInTime));
		}

		if (checkOutTime == Long.MIN_VALUE) {
			attendanceImpl.setCheckOutTime(null);
		}
		else {
			attendanceImpl.setCheckOutTime(new Date(checkOutTime));
		}

		if (checkInIP == null) {
			attendanceImpl.setCheckInIP("");
		}
		else {
			attendanceImpl.setCheckInIP(checkInIP);
		}

		if (checkOutIP == null) {
			attendanceImpl.setCheckOutIP("");
		}
		else {
			attendanceImpl.setCheckOutIP(checkOutIP);
		}

		attendanceImpl.setCheckInLatitude(checkInLatitude);
		attendanceImpl.setCheckInLongitude(checkInLongitude);
		attendanceImpl.setCheckOutLatitude(checkOutLatitude);
		attendanceImpl.setCheckOutLongitude(checkOutLongitude);

		if (status == null) {
			attendanceImpl.setStatus("");
		}
		else {
			attendanceImpl.setStatus(status);
		}

		attendanceImpl.resetOriginalValues();

		return attendanceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		attendanceId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		employeeId = objectInput.readLong();
		attendanceDate = objectInput.readLong();
		checkInTime = objectInput.readLong();
		checkOutTime = objectInput.readLong();
		checkInIP = objectInput.readUTF();
		checkOutIP = objectInput.readUTF();

		checkInLatitude = objectInput.readDouble();

		checkInLongitude = objectInput.readDouble();

		checkOutLatitude = objectInput.readDouble();

		checkOutLongitude = objectInput.readDouble();
		status = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(attendanceId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(employeeId);
		objectOutput.writeLong(attendanceDate);
		objectOutput.writeLong(checkInTime);
		objectOutput.writeLong(checkOutTime);

		if (checkInIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(checkInIP);
		}

		if (checkOutIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(checkOutIP);
		}

		objectOutput.writeDouble(checkInLatitude);

		objectOutput.writeDouble(checkInLongitude);

		objectOutput.writeDouble(checkOutLatitude);

		objectOutput.writeDouble(checkOutLongitude);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long attendanceId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long employeeId;
	public long attendanceDate;
	public long checkInTime;
	public long checkOutTime;
	public String checkInIP;
	public String checkOutIP;
	public double checkInLatitude;
	public double checkInLongitude;
	public double checkOutLatitude;
	public double checkOutLongitude;
	public String status;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1595240583