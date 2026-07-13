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
 * The table class for the &quot;HRMS_Designation&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Designation
 * @generated
 */
public class DesignationTable extends BaseTable<DesignationTable> {

	public static final DesignationTable INSTANCE = new DesignationTable();

	public final Column<DesignationTable, Long> designationId = createColumn(
		"designationId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DesignationTable, String> designationCode =
		createColumn(
			"designationCode", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<DesignationTable, String> designationName =
		createColumn(
			"designationName", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<DesignationTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DesignationTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DesignationTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DesignationTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DesignationTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DesignationTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DesignationTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DesignationTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private DesignationTable() {
		super("HRMS_Designation", DesignationTable::new);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-1189406102