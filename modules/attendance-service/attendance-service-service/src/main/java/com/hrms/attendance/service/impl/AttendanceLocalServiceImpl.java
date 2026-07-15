/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.service.impl;

import com.hrms.attendance.service.base.AttendanceLocalServiceBaseImpl;


import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.hrms.attendance.model.Attendance",
	service = AopService.class
)
public class AttendanceLocalServiceImpl extends AttendanceLocalServiceBaseImpl {

	/**
	 * Exposes the finder method to the public AttendanceLocalService interface.
	 */
	public com.hrms.attendance.model.Attendance fetchEmployeeAndDate(long employeeId, java.util.Date attendanceDate) {
		return attendancePersistence.fetchByEmployeeAndDate(employeeId, attendanceDate);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1083775447