/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package ai.assistance.service.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchDocumentException extends NoSuchModelException {

	public NoSuchDocumentException() {
	}

	public NoSuchDocumentException(String msg) {
		super(msg);
	}

	public NoSuchDocumentException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchDocumentException(Throwable throwable) {
		super(throwable);
	}

}