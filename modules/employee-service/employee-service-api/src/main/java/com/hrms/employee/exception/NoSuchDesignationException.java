/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package com.hrms.employee.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchDesignationException extends NoSuchModelException {

	public NoSuchDesignationException() {
	}

	public NoSuchDesignationException(String msg) {
		super(msg);
	}

	public NoSuchDesignationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchDesignationException(Throwable throwable) {
		super(throwable);
	}

}