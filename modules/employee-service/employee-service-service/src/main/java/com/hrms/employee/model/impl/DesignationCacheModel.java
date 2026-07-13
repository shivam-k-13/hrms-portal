/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.model.impl;

import com.hrms.employee.model.Designation;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Designation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DesignationCacheModel
	implements CacheModel<Designation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DesignationCacheModel)) {
			return false;
		}

		DesignationCacheModel designationCacheModel =
			(DesignationCacheModel)object;

		if (designationId == designationCacheModel.designationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, designationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{designationId=");
		sb.append(designationId);
		sb.append(", designationCode=");
		sb.append(designationCode);
		sb.append(", designationName=");
		sb.append(designationName);
		sb.append(", description=");
		sb.append(description);
		sb.append(", status=");
		sb.append(status);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Designation toEntityModel() {
		DesignationImpl designationImpl = new DesignationImpl();

		designationImpl.setDesignationId(designationId);

		if (designationCode == null) {
			designationImpl.setDesignationCode("");
		}
		else {
			designationImpl.setDesignationCode(designationCode);
		}

		if (designationName == null) {
			designationImpl.setDesignationName("");
		}
		else {
			designationImpl.setDesignationName(designationName);
		}

		if (description == null) {
			designationImpl.setDescription("");
		}
		else {
			designationImpl.setDescription(description);
		}

		if (status == null) {
			designationImpl.setStatus("");
		}
		else {
			designationImpl.setStatus(status);
		}

		designationImpl.setGroupId(groupId);
		designationImpl.setCompanyId(companyId);
		designationImpl.setUserId(userId);

		if (userName == null) {
			designationImpl.setUserName("");
		}
		else {
			designationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			designationImpl.setCreateDate(null);
		}
		else {
			designationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			designationImpl.setModifiedDate(null);
		}
		else {
			designationImpl.setModifiedDate(new Date(modifiedDate));
		}

		designationImpl.resetOriginalValues();

		return designationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		designationId = objectInput.readLong();
		designationCode = objectInput.readUTF();
		designationName = objectInput.readUTF();
		description = objectInput.readUTF();
		status = objectInput.readUTF();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(designationId);

		if (designationCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(designationCode);
		}

		if (designationName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(designationName);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
	}

	public long designationId;
	public String designationCode;
	public String designationName;
	public String description;
	public String status;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;

}
// LIFERAY-SERVICE-BUILDER-HASH:1813488331