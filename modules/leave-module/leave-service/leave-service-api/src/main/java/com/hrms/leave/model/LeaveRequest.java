/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the LeaveRequest service. Represents a row in the &quot;HRMS_LeaveRequest&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LeaveRequestModel
 * @generated
 */
@ImplementationClassName("com.hrms.leave.model.impl.LeaveRequestImpl")
@ProviderType
public interface LeaveRequest extends LeaveRequestModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.hrms.leave.model.impl.LeaveRequestImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LeaveRequest, Long> LEAVE_REQUEST_ID_ACCESSOR =
		new Accessor<LeaveRequest, Long>() {

			@Override
			public Long get(LeaveRequest leaveRequest) {
				return leaveRequest.getLeaveRequestId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LeaveRequest> getTypeClass() {
				return LeaveRequest.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:-1072233362