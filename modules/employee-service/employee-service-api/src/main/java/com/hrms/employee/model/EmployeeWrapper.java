/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Employee}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeeWrapper implements Employee, ModelWrapper<Employee> {

	public EmployeeWrapper(Employee employee) {
		_employee = employee;
	}

	@Override
	public Class<?> getModelClass() {
		return Employee.class;
	}

	@Override
	public String getModelClassName() {
		return Employee.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("employeeId", getEmployeeId());
		attributes.put("employeeCode", getEmployeeCode());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("email", getEmail());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("department", getDepartment());
		attributes.put("designation", getDesignation());
		attributes.put("joiningDate", getJoiningDate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		String employeeCode = (String)attributes.get("employeeCode");

		if (employeeCode != null) {
			setEmployeeCode(employeeCode);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		String department = (String)attributes.get("department");

		if (department != null) {
			setDepartment(department);
		}

		String designation = (String)attributes.get("designation");

		if (designation != null) {
			setDesignation(designation);
		}

		Date joiningDate = (Date)attributes.get("joiningDate");

		if (joiningDate != null) {
			setJoiningDate(joiningDate);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new EmployeeWrapper((Employee)_employee.clone());
	}

	@Override
	public int compareTo(Employee employee) {
		return _employee.compareTo(employee);
	}

	/**
	 * Returns the department of this employee.
	 *
	 * @return the department of this employee
	 */
	@Override
	public String getDepartment() {
		return _employee.getDepartment();
	}

	/**
	 * Returns the designation of this employee.
	 *
	 * @return the designation of this employee
	 */
	@Override
	public String getDesignation() {
		return _employee.getDesignation();
	}

	/**
	 * Returns the email of this employee.
	 *
	 * @return the email of this employee
	 */
	@Override
	public String getEmail() {
		return _employee.getEmail();
	}

	/**
	 * Returns the employee code of this employee.
	 *
	 * @return the employee code of this employee
	 */
	@Override
	public String getEmployeeCode() {
		return _employee.getEmployeeCode();
	}

	/**
	 * Returns the employee ID of this employee.
	 *
	 * @return the employee ID of this employee
	 */
	@Override
	public long getEmployeeId() {
		return _employee.getEmployeeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _employee.getExpandoBridge();
	}

	/**
	 * Returns the first name of this employee.
	 *
	 * @return the first name of this employee
	 */
	@Override
	public String getFirstName() {
		return _employee.getFirstName();
	}

	/**
	 * Returns the joining date of this employee.
	 *
	 * @return the joining date of this employee
	 */
	@Override
	public Date getJoiningDate() {
		return _employee.getJoiningDate();
	}

	/**
	 * Returns the last name of this employee.
	 *
	 * @return the last name of this employee
	 */
	@Override
	public String getLastName() {
		return _employee.getLastName();
	}

	/**
	 * Returns the phone number of this employee.
	 *
	 * @return the phone number of this employee
	 */
	@Override
	public String getPhoneNumber() {
		return _employee.getPhoneNumber();
	}

	/**
	 * Returns the primary key of this employee.
	 *
	 * @return the primary key of this employee
	 */
	@Override
	public long getPrimaryKey() {
		return _employee.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _employee.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this employee.
	 *
	 * @return the status of this employee
	 */
	@Override
	public String getStatus() {
		return _employee.getStatus();
	}

	@Override
	public int hashCode() {
		return _employee.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _employee.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _employee.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _employee.isNew();
	}

	@Override
	public void persist() {
		_employee.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_employee.setCachedModel(cachedModel);
	}

	/**
	 * Sets the department of this employee.
	 *
	 * @param department the department of this employee
	 */
	@Override
	public void setDepartment(String department) {
		_employee.setDepartment(department);
	}

	/**
	 * Sets the designation of this employee.
	 *
	 * @param designation the designation of this employee
	 */
	@Override
	public void setDesignation(String designation) {
		_employee.setDesignation(designation);
	}

	/**
	 * Sets the email of this employee.
	 *
	 * @param email the email of this employee
	 */
	@Override
	public void setEmail(String email) {
		_employee.setEmail(email);
	}

	/**
	 * Sets the employee code of this employee.
	 *
	 * @param employeeCode the employee code of this employee
	 */
	@Override
	public void setEmployeeCode(String employeeCode) {
		_employee.setEmployeeCode(employeeCode);
	}

	/**
	 * Sets the employee ID of this employee.
	 *
	 * @param employeeId the employee ID of this employee
	 */
	@Override
	public void setEmployeeId(long employeeId) {
		_employee.setEmployeeId(employeeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_employee.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_employee.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_employee.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the first name of this employee.
	 *
	 * @param firstName the first name of this employee
	 */
	@Override
	public void setFirstName(String firstName) {
		_employee.setFirstName(firstName);
	}

	/**
	 * Sets the joining date of this employee.
	 *
	 * @param joiningDate the joining date of this employee
	 */
	@Override
	public void setJoiningDate(Date joiningDate) {
		_employee.setJoiningDate(joiningDate);
	}

	/**
	 * Sets the last name of this employee.
	 *
	 * @param lastName the last name of this employee
	 */
	@Override
	public void setLastName(String lastName) {
		_employee.setLastName(lastName);
	}

	@Override
	public void setNew(boolean n) {
		_employee.setNew(n);
	}

	/**
	 * Sets the phone number of this employee.
	 *
	 * @param phoneNumber the phone number of this employee
	 */
	@Override
	public void setPhoneNumber(String phoneNumber) {
		_employee.setPhoneNumber(phoneNumber);
	}

	/**
	 * Sets the primary key of this employee.
	 *
	 * @param primaryKey the primary key of this employee
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_employee.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_employee.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this employee.
	 *
	 * @param status the status of this employee
	 */
	@Override
	public void setStatus(String status) {
		_employee.setStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Employee> toCacheModel() {
		return _employee.toCacheModel();
	}

	@Override
	public Employee toEscapedModel() {
		return new EmployeeWrapper(_employee.toEscapedModel());
	}

	@Override
	public String toString() {
		return _employee.toString();
	}

	@Override
	public Employee toUnescapedModel() {
		return new EmployeeWrapper(_employee.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _employee.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeWrapper)) {
			return false;
		}

		EmployeeWrapper employeeWrapper = (EmployeeWrapper)object;

		if (Objects.equals(_employee, employeeWrapper._employee)) {
			return true;
		}

		return false;
	}

	@Override
	public Employee getWrappedModel() {
		return _employee;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _employee.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _employee.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_employee.resetOriginalValues();
	}

	private final Employee _employee;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1618233724