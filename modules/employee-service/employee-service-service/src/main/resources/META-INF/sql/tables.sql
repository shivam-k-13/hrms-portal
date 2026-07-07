create table HRMS_Employee (
	employeeId LONG not null primary key,
	employeeCode VARCHAR(75) null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	email VARCHAR(75) null,
	phoneNumber VARCHAR(75) null,
	department VARCHAR(75) null,
	designation VARCHAR(75) null,
	joiningDate DATE null,
	status VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);