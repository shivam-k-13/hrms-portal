/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.service.persistence.test;

import com.hrms.employee.exception.NoSuchDepartmentException;
import com.hrms.employee.model.Department;
import com.hrms.employee.service.DepartmentLocalServiceUtil;
import com.hrms.employee.service.persistence.DepartmentPersistence;
import com.hrms.employee.service.persistence.DepartmentUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class DepartmentPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.hrms.employee.service"));

	@Before
	public void setUp() {
		_persistence = DepartmentUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Department> iterator = _departments.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department department = _persistence.create(pk);

		Assert.assertNotNull(department);

		Assert.assertEquals(department.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Department newDepartment = addDepartment();

		_persistence.remove(newDepartment);

		Department existingDepartment = _persistence.fetchByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertNull(existingDepartment);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDepartment();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department newDepartment = _persistence.create(pk);

		newDepartment.setDepartmentCode(RandomTestUtil.randomString());

		newDepartment.setDepartmentName(RandomTestUtil.randomString());

		newDepartment.setDescription(RandomTestUtil.randomString());

		newDepartment.setStatus(RandomTestUtil.randomString());

		newDepartment.setGroupId(RandomTestUtil.nextLong());

		newDepartment.setCompanyId(RandomTestUtil.nextLong());

		newDepartment.setUserId(RandomTestUtil.nextLong());

		newDepartment.setUserName(RandomTestUtil.randomString());

		newDepartment.setCreateDate(RandomTestUtil.nextDate());

		newDepartment.setModifiedDate(RandomTestUtil.nextDate());

		_departments.add(_persistence.update(newDepartment));

		Department existingDepartment = _persistence.findByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertEquals(
			existingDepartment.getDepartmentId(),
			newDepartment.getDepartmentId());
		Assert.assertEquals(
			existingDepartment.getDepartmentCode(),
			newDepartment.getDepartmentCode());
		Assert.assertEquals(
			existingDepartment.getDepartmentName(),
			newDepartment.getDepartmentName());
		Assert.assertEquals(
			existingDepartment.getDescription(),
			newDepartment.getDescription());
		Assert.assertEquals(
			existingDepartment.getStatus(), newDepartment.getStatus());
		Assert.assertEquals(
			existingDepartment.getGroupId(), newDepartment.getGroupId());
		Assert.assertEquals(
			existingDepartment.getCompanyId(), newDepartment.getCompanyId());
		Assert.assertEquals(
			existingDepartment.getUserId(), newDepartment.getUserId());
		Assert.assertEquals(
			existingDepartment.getUserName(), newDepartment.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingDepartment.getCreateDate()),
			Time.getShortTimestamp(newDepartment.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingDepartment.getModifiedDate()),
			Time.getShortTimestamp(newDepartment.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Department newDepartment = addDepartment();

		Department existingDepartment = _persistence.findByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertEquals(existingDepartment, newDepartment);
	}

	@Test(expected = NoSuchDepartmentException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Department> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"HRMS_Department", "departmentId", true, "departmentCode", true,
			"departmentName", true, "description", true, "status", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Department newDepartment = addDepartment();

		Department existingDepartment = _persistence.fetchByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertEquals(existingDepartment, newDepartment);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department missingDepartment = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDepartment);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Department newDepartment1 = addDepartment();
		Department newDepartment2 = addDepartment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDepartment1.getPrimaryKey());
		primaryKeys.add(newDepartment2.getPrimaryKey());

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, departments.size());
		Assert.assertEquals(
			newDepartment1, departments.get(newDepartment1.getPrimaryKey()));
		Assert.assertEquals(
			newDepartment2, departments.get(newDepartment2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(departments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Department newDepartment = addDepartment();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDepartment.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, departments.size());
		Assert.assertEquals(
			newDepartment, departments.get(newDepartment.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(departments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Department newDepartment = addDepartment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDepartment.getPrimaryKey());

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, departments.size());
		Assert.assertEquals(
			newDepartment, departments.get(newDepartment.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DepartmentLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Department>() {

				@Override
				public void performAction(Department department) {
					Assert.assertNotNull(department);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Department newDepartment = addDepartment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"departmentId", newDepartment.getDepartmentId()));

		List<Department> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		Department existingDepartment = result.get(0);

		Assert.assertEquals(existingDepartment, newDepartment);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"departmentId", RandomTestUtil.nextLong()));

		List<Department> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Department newDepartment = addDepartment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("departmentId"));

		Object newDepartmentId = newDepartment.getDepartmentId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"departmentId", new Object[] {newDepartmentId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDepartmentId = result.get(0);

		Assert.assertEquals(existingDepartmentId, newDepartmentId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("departmentId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"departmentId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Department addDepartment() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department department = _persistence.create(pk);

		department.setDepartmentCode(RandomTestUtil.randomString());

		department.setDepartmentName(RandomTestUtil.randomString());

		department.setDescription(RandomTestUtil.randomString());

		department.setStatus(RandomTestUtil.randomString());

		department.setGroupId(RandomTestUtil.nextLong());

		department.setCompanyId(RandomTestUtil.nextLong());

		department.setUserId(RandomTestUtil.nextLong());

		department.setUserName(RandomTestUtil.randomString());

		department.setCreateDate(RandomTestUtil.nextDate());

		department.setModifiedDate(RandomTestUtil.nextDate());

		_departments.add(_persistence.update(department));

		return department;
	}

	private List<Department> _departments = new ArrayList<Department>();
	private DepartmentPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:-446894963