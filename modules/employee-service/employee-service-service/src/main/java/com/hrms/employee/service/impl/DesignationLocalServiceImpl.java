/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.service.impl;

import com.hrms.employee.service.base.DesignationLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.hrms.employee.model.Designation",
	service = AopService.class
)
public class DesignationLocalServiceImpl
	extends DesignationLocalServiceBaseImpl {
}
// LIFERAY-SERVICE-BUILDER-HASH:380093583