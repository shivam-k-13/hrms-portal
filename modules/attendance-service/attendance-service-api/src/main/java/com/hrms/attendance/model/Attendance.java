/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Attendance service. Represents a row in the &quot;HRMS_Attendance&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AttendanceModel
 * @generated
 */
@ImplementationClassName("com.hrms.attendance.model.impl.AttendanceImpl")
@ProviderType
public interface Attendance extends AttendanceModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.hrms.attendance.model.impl.AttendanceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Attendance, Long> ATTENDANCE_ID_ACCESSOR =
		new Accessor<Attendance, Long>() {

			@Override
			public Long get(Attendance attendance) {
				return attendance.getAttendanceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Attendance> getTypeClass() {
				return Attendance.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:277761361