/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;HRMS_Attendance&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Attendance
 * @generated
 */
public class AttendanceTable extends BaseTable<AttendanceTable> {

	public static final AttendanceTable INSTANCE = new AttendanceTable();

	public final Column<AttendanceTable, Long> attendanceId = createColumn(
		"attendanceId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AttendanceTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Long> employeeId = createColumn(
		"employeeId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Date> attendanceDate = createColumn(
		"attendanceDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Date> checkInTime = createColumn(
		"checkInTime", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Date> checkOutTime = createColumn(
		"checkOutTime", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, String> checkInIP = createColumn(
		"checkInIP", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, String> checkOutIP = createColumn(
		"checkOutIP", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Double> checkInLatitude = createColumn(
		"checkInLatitude", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Double> checkInLongitude =
		createColumn(
			"checkInLongitude", Double.class, Types.DOUBLE,
			Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Double> checkOutLatitude =
		createColumn(
			"checkOutLatitude", Double.class, Types.DOUBLE,
			Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, Double> checkOutLongitude =
		createColumn(
			"checkOutLongitude", Double.class, Types.DOUBLE,
			Column.FLAG_DEFAULT);
	public final Column<AttendanceTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private AttendanceTable() {
		super("HRMS_Attendance", AttendanceTable::new);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-1887381956