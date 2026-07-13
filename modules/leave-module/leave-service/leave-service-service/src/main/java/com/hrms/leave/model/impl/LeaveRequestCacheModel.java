/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.model.impl;

import com.hrms.leave.model.LeaveRequest;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LeaveRequest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LeaveRequestCacheModel
	implements CacheModel<LeaveRequest>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LeaveRequestCacheModel)) {
			return false;
		}

		LeaveRequestCacheModel leaveRequestCacheModel =
			(LeaveRequestCacheModel)object;

		if (leaveRequestId == leaveRequestCacheModel.leaveRequestId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, leaveRequestId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", leaveRequestId=");
		sb.append(leaveRequestId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", leaveType=");
		sb.append(leaveType);
		sb.append(", fromDate=");
		sb.append(fromDate);
		sb.append(", toDate=");
		sb.append(toDate);
		sb.append(", reason=");
		sb.append(reason);
		sb.append(", status=");
		sb.append(status);
		sb.append(", approverUserId=");
		sb.append(approverUserId);
		sb.append(", approverComments=");
		sb.append(approverComments);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LeaveRequest toEntityModel() {
		LeaveRequestImpl leaveRequestImpl = new LeaveRequestImpl();

		if (uuid == null) {
			leaveRequestImpl.setUuid("");
		}
		else {
			leaveRequestImpl.setUuid(uuid);
		}

		leaveRequestImpl.setLeaveRequestId(leaveRequestId);
		leaveRequestImpl.setGroupId(groupId);
		leaveRequestImpl.setCompanyId(companyId);
		leaveRequestImpl.setUserId(userId);

		if (userName == null) {
			leaveRequestImpl.setUserName("");
		}
		else {
			leaveRequestImpl.setUserName(userName);
		}

		leaveRequestImpl.setEmployeeId(employeeId);

		if (leaveType == null) {
			leaveRequestImpl.setLeaveType("");
		}
		else {
			leaveRequestImpl.setLeaveType(leaveType);
		}

		if (fromDate == Long.MIN_VALUE) {
			leaveRequestImpl.setFromDate(null);
		}
		else {
			leaveRequestImpl.setFromDate(new Date(fromDate));
		}

		if (toDate == Long.MIN_VALUE) {
			leaveRequestImpl.setToDate(null);
		}
		else {
			leaveRequestImpl.setToDate(new Date(toDate));
		}

		if (reason == null) {
			leaveRequestImpl.setReason("");
		}
		else {
			leaveRequestImpl.setReason(reason);
		}

		if (status == null) {
			leaveRequestImpl.setStatus("");
		}
		else {
			leaveRequestImpl.setStatus(status);
		}

		leaveRequestImpl.setApproverUserId(approverUserId);

		if (approverComments == null) {
			leaveRequestImpl.setApproverComments("");
		}
		else {
			leaveRequestImpl.setApproverComments(approverComments);
		}

		if (createDate == Long.MIN_VALUE) {
			leaveRequestImpl.setCreateDate(null);
		}
		else {
			leaveRequestImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			leaveRequestImpl.setModifiedDate(null);
		}
		else {
			leaveRequestImpl.setModifiedDate(new Date(modifiedDate));
		}

		leaveRequestImpl.resetOriginalValues();

		return leaveRequestImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		leaveRequestId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();

		employeeId = objectInput.readLong();
		leaveType = objectInput.readUTF();
		fromDate = objectInput.readLong();
		toDate = objectInput.readLong();
		reason = objectInput.readUTF();
		status = objectInput.readUTF();

		approverUserId = objectInput.readLong();
		approverComments = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(leaveRequestId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(employeeId);

		if (leaveType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leaveType);
		}

		objectOutput.writeLong(fromDate);
		objectOutput.writeLong(toDate);

		if (reason == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reason);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeLong(approverUserId);

		if (approverComments == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(approverComments);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
	}

	public String uuid;
	public long leaveRequestId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long employeeId;
	public String leaveType;
	public long fromDate;
	public long toDate;
	public String reason;
	public String status;
	public long approverUserId;
	public String approverComments;
	public long createDate;
	public long modifiedDate;

}
// LIFERAY-SERVICE-BUILDER-HASH:2145261618