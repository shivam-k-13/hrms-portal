create table HRMS_LeaveRequest (
	uuid_ VARCHAR(75) null,
	leaveRequestId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	employeeId LONG,
	leaveType VARCHAR(75) null,
	fromDate DATE null,
	toDate DATE null,
	reason VARCHAR(75) null,
	status VARCHAR(75) null,
	approverUserId LONG,
	approverComments VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);