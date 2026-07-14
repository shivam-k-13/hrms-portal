/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;HRMS_LeaveRequest&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see LeaveRequest
 * @generated
 */
public class LeaveRequestTable extends BaseTable<LeaveRequestTable> {

	public static final LeaveRequestTable INSTANCE = new LeaveRequestTable();

	public final Column<LeaveRequestTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Long> leaveRequestId = createColumn(
		"leaveRequestId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<LeaveRequestTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Long> employeeId = createColumn(
		"employeeId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, String> leaveType = createColumn(
		"leaveType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Date> fromDate = createColumn(
		"fromDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Date> toDate = createColumn(
		"toDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, String> reason = createColumn(
		"reason", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Long> approverUserId = createColumn(
		"approverUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, String> approverComments =
		createColumn(
			"approverComments", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<LeaveRequestTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private LeaveRequestTable() {
		super("HRMS_LeaveRequest", LeaveRequestTable::new);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-329330534