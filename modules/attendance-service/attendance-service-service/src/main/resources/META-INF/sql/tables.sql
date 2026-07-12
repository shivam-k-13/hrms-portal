create table HRMS_Attendance (
	attendanceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	employeeId LONG,
	attendanceDate DATE null,
	checkInTime DATE null,
	checkOutTime DATE null,
	checkInIP VARCHAR(75) null,
	checkOutIP VARCHAR(75) null,
	checkInLatitude DOUBLE,
	checkInLongitude DOUBLE,
	checkOutLatitude DOUBLE,
	checkOutLongitude DOUBLE,
	status VARCHAR(75) null
);