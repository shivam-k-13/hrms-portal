/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.impl;

import ai.assistance.service.service.base.DocumentChunkLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=ai.assistance.service.model.DocumentChunk",
	service = AopService.class
)
public class DocumentChunkLocalServiceImpl
	extends DocumentChunkLocalServiceBaseImpl {
}
// LIFERAY-SERVICE-BUILDER-HASH:193030360