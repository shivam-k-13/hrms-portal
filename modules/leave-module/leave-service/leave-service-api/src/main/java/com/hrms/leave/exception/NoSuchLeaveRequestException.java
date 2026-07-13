/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package com.hrms.leave.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchLeaveRequestException extends NoSuchModelException {

	public NoSuchLeaveRequestException() {
	}

	public NoSuchLeaveRequestException(String msg) {
		super(msg);
	}

	public NoSuchLeaveRequestException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchLeaveRequestException(Throwable throwable) {
		super(throwable);
	}

}