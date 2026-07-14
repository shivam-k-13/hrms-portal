/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Designation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Designation
 * @generated
 */
public class DesignationWrapper
	extends BaseModelWrapper<Designation>
	implements Designation, ModelWrapper<Designation> {

	public DesignationWrapper(Designation designation) {
		super(designation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("designationId", getDesignationId());
		attributes.put("designationCode", getDesignationCode());
		attributes.put("designationName", getDesignationName());
		attributes.put("description", getDescription());
		attributes.put("status", getStatus());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long designationId = (Long)attributes.get("designationId");

		if (designationId != null) {
			setDesignationId(designationId);
		}

		String designationCode = (String)attributes.get("designationCode");

		if (designationCode != null) {
			setDesignationCode(designationCode);
		}

		String designationName = (String)attributes.get("designationName");

		if (designationName != null) {
			setDesignationName(designationName);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public Designation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this designation.
	 *
	 * @return the company ID of this designation
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this designation.
	 *
	 * @return the create date of this designation
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this designation.
	 *
	 * @return the description of this designation
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the designation code of this designation.
	 *
	 * @return the designation code of this designation
	 */
	@Override
	public String getDesignationCode() {
		return model.getDesignationCode();
	}

	/**
	 * Returns the designation ID of this designation.
	 *
	 * @return the designation ID of this designation
	 */
	@Override
	public long getDesignationId() {
		return model.getDesignationId();
	}

	/**
	 * Returns the designation name of this designation.
	 *
	 * @return the designation name of this designation
	 */
	@Override
	public String getDesignationName() {
		return model.getDesignationName();
	}

	/**
	 * Returns the group ID of this designation.
	 *
	 * @return the group ID of this designation
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this designation.
	 *
	 * @return the modified date of this designation
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this designation.
	 *
	 * @return the primary key of this designation
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this designation.
	 *
	 * @return the status of this designation
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this designation.
	 *
	 * @return the user ID of this designation
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this designation.
	 *
	 * @return the user name of this designation
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this designation.
	 *
	 * @return the user uuid of this designation
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this designation.
	 *
	 * @param companyId the company ID of this designation
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this designation.
	 *
	 * @param createDate the create date of this designation
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this designation.
	 *
	 * @param description the description of this designation
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the designation code of this designation.
	 *
	 * @param designationCode the designation code of this designation
	 */
	@Override
	public void setDesignationCode(String designationCode) {
		model.setDesignationCode(designationCode);
	}

	/**
	 * Sets the designation ID of this designation.
	 *
	 * @param designationId the designation ID of this designation
	 */
	@Override
	public void setDesignationId(long designationId) {
		model.setDesignationId(designationId);
	}

	/**
	 * Sets the designation name of this designation.
	 *
	 * @param designationName the designation name of this designation
	 */
	@Override
	public void setDesignationName(String designationName) {
		model.setDesignationName(designationName);
	}

	/**
	 * Sets the group ID of this designation.
	 *
	 * @param groupId the group ID of this designation
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this designation.
	 *
	 * @param modifiedDate the modified date of this designation
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this designation.
	 *
	 * @param primaryKey the primary key of this designation
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this designation.
	 *
	 * @param status the status of this designation
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this designation.
	 *
	 * @param userId the user ID of this designation
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this designation.
	 *
	 * @param userName the user name of this designation
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this designation.
	 *
	 * @param userUuid the user uuid of this designation
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DesignationWrapper wrap(Designation designation) {
		return new DesignationWrapper(designation);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-379701040