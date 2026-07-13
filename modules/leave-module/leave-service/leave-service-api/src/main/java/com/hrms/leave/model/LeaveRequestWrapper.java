/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LeaveRequest}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LeaveRequest
 * @generated
 */
public class LeaveRequestWrapper
	extends BaseModelWrapper<LeaveRequest>
	implements LeaveRequest, ModelWrapper<LeaveRequest> {

	public LeaveRequestWrapper(LeaveRequest leaveRequest) {
		super(leaveRequest);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("leaveRequestId", getLeaveRequestId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("leaveType", getLeaveType());
		attributes.put("fromDate", getFromDate());
		attributes.put("toDate", getToDate());
		attributes.put("reason", getReason());
		attributes.put("status", getStatus());
		attributes.put("approverUserId", getApproverUserId());
		attributes.put("approverComments", getApproverComments());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long leaveRequestId = (Long)attributes.get("leaveRequestId");

		if (leaveRequestId != null) {
			setLeaveRequestId(leaveRequestId);
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

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		String leaveType = (String)attributes.get("leaveType");

		if (leaveType != null) {
			setLeaveType(leaveType);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Date toDate = (Date)attributes.get("toDate");

		if (toDate != null) {
			setToDate(toDate);
		}

		String reason = (String)attributes.get("reason");

		if (reason != null) {
			setReason(reason);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long approverUserId = (Long)attributes.get("approverUserId");

		if (approverUserId != null) {
			setApproverUserId(approverUserId);
		}

		String approverComments = (String)attributes.get("approverComments");

		if (approverComments != null) {
			setApproverComments(approverComments);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public LeaveRequest cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the approver comments of this leave request.
	 *
	 * @return the approver comments of this leave request
	 */
	@Override
	public String getApproverComments() {
		return model.getApproverComments();
	}

	/**
	 * Returns the approver user ID of this leave request.
	 *
	 * @return the approver user ID of this leave request
	 */
	@Override
	public long getApproverUserId() {
		return model.getApproverUserId();
	}

	/**
	 * Returns the approver user uuid of this leave request.
	 *
	 * @return the approver user uuid of this leave request
	 */
	@Override
	public String getApproverUserUuid() {
		return model.getApproverUserUuid();
	}

	/**
	 * Returns the company ID of this leave request.
	 *
	 * @return the company ID of this leave request
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this leave request.
	 *
	 * @return the create date of this leave request
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the employee ID of this leave request.
	 *
	 * @return the employee ID of this leave request
	 */
	@Override
	public long getEmployeeId() {
		return model.getEmployeeId();
	}

	/**
	 * Returns the from date of this leave request.
	 *
	 * @return the from date of this leave request
	 */
	@Override
	public Date getFromDate() {
		return model.getFromDate();
	}

	/**
	 * Returns the group ID of this leave request.
	 *
	 * @return the group ID of this leave request
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the leave request ID of this leave request.
	 *
	 * @return the leave request ID of this leave request
	 */
	@Override
	public long getLeaveRequestId() {
		return model.getLeaveRequestId();
	}

	/**
	 * Returns the leave type of this leave request.
	 *
	 * @return the leave type of this leave request
	 */
	@Override
	public String getLeaveType() {
		return model.getLeaveType();
	}

	/**
	 * Returns the modified date of this leave request.
	 *
	 * @return the modified date of this leave request
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this leave request.
	 *
	 * @return the primary key of this leave request
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the reason of this leave request.
	 *
	 * @return the reason of this leave request
	 */
	@Override
	public String getReason() {
		return model.getReason();
	}

	/**
	 * Returns the status of this leave request.
	 *
	 * @return the status of this leave request
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the to date of this leave request.
	 *
	 * @return the to date of this leave request
	 */
	@Override
	public Date getToDate() {
		return model.getToDate();
	}

	/**
	 * Returns the user ID of this leave request.
	 *
	 * @return the user ID of this leave request
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this leave request.
	 *
	 * @return the user name of this leave request
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this leave request.
	 *
	 * @return the user uuid of this leave request
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this leave request.
	 *
	 * @return the uuid of this leave request
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the approver comments of this leave request.
	 *
	 * @param approverComments the approver comments of this leave request
	 */
	@Override
	public void setApproverComments(String approverComments) {
		model.setApproverComments(approverComments);
	}

	/**
	 * Sets the approver user ID of this leave request.
	 *
	 * @param approverUserId the approver user ID of this leave request
	 */
	@Override
	public void setApproverUserId(long approverUserId) {
		model.setApproverUserId(approverUserId);
	}

	/**
	 * Sets the approver user uuid of this leave request.
	 *
	 * @param approverUserUuid the approver user uuid of this leave request
	 */
	@Override
	public void setApproverUserUuid(String approverUserUuid) {
		model.setApproverUserUuid(approverUserUuid);
	}

	/**
	 * Sets the company ID of this leave request.
	 *
	 * @param companyId the company ID of this leave request
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this leave request.
	 *
	 * @param createDate the create date of this leave request
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the employee ID of this leave request.
	 *
	 * @param employeeId the employee ID of this leave request
	 */
	@Override
	public void setEmployeeId(long employeeId) {
		model.setEmployeeId(employeeId);
	}

	/**
	 * Sets the from date of this leave request.
	 *
	 * @param fromDate the from date of this leave request
	 */
	@Override
	public void setFromDate(Date fromDate) {
		model.setFromDate(fromDate);
	}

	/**
	 * Sets the group ID of this leave request.
	 *
	 * @param groupId the group ID of this leave request
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the leave request ID of this leave request.
	 *
	 * @param leaveRequestId the leave request ID of this leave request
	 */
	@Override
	public void setLeaveRequestId(long leaveRequestId) {
		model.setLeaveRequestId(leaveRequestId);
	}

	/**
	 * Sets the leave type of this leave request.
	 *
	 * @param leaveType the leave type of this leave request
	 */
	@Override
	public void setLeaveType(String leaveType) {
		model.setLeaveType(leaveType);
	}

	/**
	 * Sets the modified date of this leave request.
	 *
	 * @param modifiedDate the modified date of this leave request
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this leave request.
	 *
	 * @param primaryKey the primary key of this leave request
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the reason of this leave request.
	 *
	 * @param reason the reason of this leave request
	 */
	@Override
	public void setReason(String reason) {
		model.setReason(reason);
	}

	/**
	 * Sets the status of this leave request.
	 *
	 * @param status the status of this leave request
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the to date of this leave request.
	 *
	 * @param toDate the to date of this leave request
	 */
	@Override
	public void setToDate(Date toDate) {
		model.setToDate(toDate);
	}

	/**
	 * Sets the user ID of this leave request.
	 *
	 * @param userId the user ID of this leave request
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this leave request.
	 *
	 * @param userName the user name of this leave request
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this leave request.
	 *
	 * @param userUuid the user uuid of this leave request
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this leave request.
	 *
	 * @param uuid the uuid of this leave request
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected LeaveRequestWrapper wrap(LeaveRequest leaveRequest) {
		return new LeaveRequestWrapper(leaveRequest);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-2005398682