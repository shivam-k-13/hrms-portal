/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AttendanceService}.
 *
 * @author Brian Wing Shun Chan
 * @see AttendanceService
 * @generated
 */
public class AttendanceServiceWrapper
	implements AttendanceService, ServiceWrapper<AttendanceService> {

	public AttendanceServiceWrapper() {
		this(null);
	}

	public AttendanceServiceWrapper(AttendanceService attendanceService) {
		_attendanceService = attendanceService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _attendanceService.getOSGiServiceIdentifier();
	}

	@Override
	public AttendanceService getWrappedService() {
		return _attendanceService;
	}

	@Override
	public void setWrappedService(AttendanceService attendanceService) {
		_attendanceService = attendanceService;
	}

	private AttendanceService _attendanceService;

}
// LIFERAY-SERVICE-BUILDER-HASH:1774659690