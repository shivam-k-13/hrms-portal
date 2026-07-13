/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Department service. Represents a row in the &quot;HRMS_Department&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DepartmentModel
 * @generated
 */
@ImplementationClassName("com.hrms.employee.model.impl.DepartmentImpl")
@ProviderType
public interface Department extends DepartmentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.hrms.employee.model.impl.DepartmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Department, Long> DEPARTMENT_ID_ACCESSOR =
		new Accessor<Department, Long>() {

			@Override
			public Long get(Department department) {
				return department.getDepartmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Department> getTypeClass() {
				return Department.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:-1953250300