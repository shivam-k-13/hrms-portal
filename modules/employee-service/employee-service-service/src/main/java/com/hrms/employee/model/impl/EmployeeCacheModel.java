/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.model.impl;

import com.hrms.employee.model.Employee;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmployeeCacheModel
	implements CacheModel<Employee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeCacheModel)) {
			return false;
		}

		EmployeeCacheModel employeeCacheModel = (EmployeeCacheModel)object;

		if (employeeId == employeeCacheModel.employeeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, employeeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{employeeId=");
		sb.append(employeeId);
		sb.append(", employeeCode=");
		sb.append(employeeCode);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", department=");
		sb.append(department);
		sb.append(", designation=");
		sb.append(designation);
		sb.append(", joiningDate=");
		sb.append(joiningDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		employeeImpl.setEmployeeId(employeeId);

		if (employeeCode == null) {
			employeeImpl.setEmployeeCode("");
		}
		else {
			employeeImpl.setEmployeeCode(employeeCode);
		}

		if (firstName == null) {
			employeeImpl.setFirstName("");
		}
		else {
			employeeImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			employeeImpl.setLastName("");
		}
		else {
			employeeImpl.setLastName(lastName);
		}

		if (email == null) {
			employeeImpl.setEmail("");
		}
		else {
			employeeImpl.setEmail(email);
		}

		if (phoneNumber == null) {
			employeeImpl.setPhoneNumber("");
		}
		else {
			employeeImpl.setPhoneNumber(phoneNumber);
		}

		if (department == null) {
			employeeImpl.setDepartment("");
		}
		else {
			employeeImpl.setDepartment(department);
		}

		if (designation == null) {
			employeeImpl.setDesignation("");
		}
		else {
			employeeImpl.setDesignation(designation);
		}

		if (joiningDate == Long.MIN_VALUE) {
			employeeImpl.setJoiningDate(null);
		}
		else {
			employeeImpl.setJoiningDate(new Date(joiningDate));
		}

		if (status == null) {
			employeeImpl.setStatus("");
		}
		else {
			employeeImpl.setStatus(status);
		}

		employeeImpl.resetOriginalValues();

		return employeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		employeeId = objectInput.readLong();
		employeeCode = objectInput.readUTF();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		email = objectInput.readUTF();
		phoneNumber = objectInput.readUTF();
		department = objectInput.readUTF();
		designation = objectInput.readUTF();
		joiningDate = objectInput.readLong();
		status = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(employeeId);

		if (employeeCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeCode);
		}

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (phoneNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneNumber);
		}

		if (department == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(department);
		}

		if (designation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(designation);
		}

		objectOutput.writeLong(joiningDate);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long employeeId;
	public String employeeCode;
	public String firstName;
	public String lastName;
	public String email;
	public String phoneNumber;
	public String department;
	public String designation;
	public long joiningDate;
	public String status;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1124511467