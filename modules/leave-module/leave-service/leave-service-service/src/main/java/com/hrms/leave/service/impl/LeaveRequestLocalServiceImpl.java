package com.hrms.leave.service.impl;

import com.hrms.leave.model.LeaveRequest;
import com.hrms.leave.service.base.LeaveRequestLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=com.hrms.leave.model.LeaveRequest",
	service = AopService.class
)
public class LeaveRequestLocalServiceImpl extends LeaveRequestLocalServiceBaseImpl {

	public LeaveRequest applyLeave(
			long employeeId, String leaveType, Date fromDate, Date toDate,
			String reason, ServiceContext serviceContext)
		throws PortalException {

		_validateLeaveRequest(employeeId, leaveType, fromDate, toDate, reason);

		long leaveRequestId = counterLocalService.increment();

		LeaveRequest leaveRequest = leaveRequestPersistence.create(leaveRequestId);

		Date now = new Date();

		leaveRequest.setGroupId(serviceContext.getScopeGroupId());
		leaveRequest.setCompanyId(serviceContext.getCompanyId());
		leaveRequest.setUserId(serviceContext.getUserId());

		leaveRequest.setUserName(
			userLocalService.getUser(serviceContext.getUserId()).getFullName());

		leaveRequest.setEmployeeId(employeeId);
		leaveRequest.setLeaveType(leaveType);
		leaveRequest.setFromDate(fromDate);
		leaveRequest.setToDate(toDate);
		leaveRequest.setReason(reason.trim());
        
        // UPGRADED: Set to Tier 1 Approval automatically
		leaveRequest.setStatus("PENDING_MANAGER");

		leaveRequest.setApproverUserId(0);
		leaveRequest.setApproverComments("");

		leaveRequest.setCreateDate(now);
		leaveRequest.setModifiedDate(now);

		return leaveRequestPersistence.update(leaveRequest);
	}

	public LeaveRequest approveLeave(
			long leaveRequestId, long approverUserId,
			String approverComments)
		throws PortalException {

		LeaveRequest leaveRequest = leaveRequestPersistence.findByPrimaryKey(leaveRequestId);

		_validatePendingStatus(leaveRequest);

		if (approverUserId <= 0) {
			throw new IllegalArgumentException("Approver user ID is required");
		}

        // UPGRADED: Multi-Tier Approval Logic
        String currentStatus = leaveRequest.getStatus();
        if ("PENDING_MANAGER".equals(currentStatus) || "PENDING".equals(currentStatus)) {
            leaveRequest.setStatus("PENDING_HR");
        } else if ("PENDING_HR".equals(currentStatus)) {
            leaveRequest.setStatus("APPROVED");
        }

		leaveRequest.setApproverUserId(approverUserId);
		leaveRequest.setApproverComments(Validator.isNull(approverComments) ? "" : approverComments.trim());
		leaveRequest.setModifiedDate(new Date());

		return leaveRequestPersistence.update(leaveRequest);
	}

	public LeaveRequest rejectLeave(
			long leaveRequestId, long approverUserId,
			String approverComments)
		throws PortalException {

		LeaveRequest leaveRequest = leaveRequestPersistence.findByPrimaryKey(leaveRequestId);

		_validatePendingStatus(leaveRequest);

		if (approverUserId <= 0) {
			throw new IllegalArgumentException("Approver user ID is required");
		}

		if (Validator.isNull(approverComments)) {
			throw new IllegalArgumentException("Rejection comment is required");
		}

		leaveRequest.setStatus("REJECTED");
		leaveRequest.setApproverUserId(approverUserId);
		leaveRequest.setApproverComments(approverComments.trim());
		leaveRequest.setModifiedDate(new Date());

		return leaveRequestPersistence.update(leaveRequest);
	}

    // FIXED: Renamed to match exactly what the Dashboard expects!
	public List<LeaveRequest> getLeaveRequestsByEmployeeId(long employeeId) {
		return leaveRequestPersistence.findByEmployeeId(employeeId);
	}

    // FIXED: Renamed to match exactly what the Dashboard expects!
	public List<LeaveRequest> getLeaveRequestsByStatus(String status) {
		return leaveRequestPersistence.findByStatus(status);
	}

    // FIXED: Cleaned up the invisible characters causing syntax errors
	public List<LeaveRequest> getLeaveRequestsByEmployeeId(long employeeId, int start, int end) {
		return leaveRequestPersistence.findByEmployeeId(employeeId, start, end);
	}

	public int getLeaveRequestsCountByEmployeeId(long employeeId) {
		return leaveRequestPersistence.countByEmployeeId(employeeId);
	}

	private void _validateLeaveRequest(
		long employeeId, String leaveType, Date fromDate, Date toDate,
		String reason) {

		if (employeeId <= 0) {
			throw new IllegalArgumentException("Employee ID must be greater than zero");
		}

		if (!_allowedLeaveTypes.contains(leaveType)) {
			throw new IllegalArgumentException("Invalid leave type");
		}

		if ((fromDate == null) || (toDate == null)) {
			throw new IllegalArgumentException("From date and to date are required");
		}

		if (toDate.before(fromDate)) {
			throw new IllegalArgumentException("To date cannot be earlier than from date");
		}

		if (Validator.isNull(reason)) {
			throw new IllegalArgumentException("Reason is required");
		}

		if (reason.trim().length() > 500) {
			throw new IllegalArgumentException("Reason cannot exceed 500 characters");
		}
	}

	private void _validatePendingStatus(LeaveRequest leaveRequest) {
        String status = leaveRequest.getStatus();
        // UPGRADED: Now accepts the multi-tier workflow statuses
		if (!"PENDING".equals(status) && !"PENDING_MANAGER".equals(status) && !"PENDING_HR".equals(status)) {
			throw new IllegalStateException("Only pending leave requests can be processed");
		}
	}

	private static final Set<String> _allowedLeaveTypes = Set.of(
		"CASUAL", "EARNED", "LOP", "SICK");

}