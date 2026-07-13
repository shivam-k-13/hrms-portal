create index IX_9434CBB0 on HRMS_LeaveRequest (employeeId, status[$COLUMN_LENGTH:75$]);
create index IX_7E151B93 on HRMS_LeaveRequest (status[$COLUMN_LENGTH:75$]);
create unique index IX_285CD523 on HRMS_LeaveRequest (uuid_[$COLUMN_LENGTH:75$], groupId);