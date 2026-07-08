/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;HRMS_Employee&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeeTable extends BaseTable<EmployeeTable> {

	public static final EmployeeTable INSTANCE = new EmployeeTable();

	public final Column<EmployeeTable, Long> employeeId = createColumn(
		"employeeId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EmployeeTable, String> employeeCode = createColumn(
		"employeeCode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> firstName = createColumn(
		"firstName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> lastName = createColumn(
		"lastName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> email = createColumn(
		"email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> phoneNumber = createColumn(
		"phoneNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> department = createColumn(
		"department", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> designation = createColumn(
		"designation", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, Date> joiningDate = createColumn(
		"joiningDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EmployeeTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private EmployeeTable() {
		super("HRMS_Employee", EmployeeTable::new);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:199741961