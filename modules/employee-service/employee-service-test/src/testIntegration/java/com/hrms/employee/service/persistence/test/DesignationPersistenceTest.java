/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.service.persistence.test;

import com.hrms.employee.exception.NoSuchDesignationException;
import com.hrms.employee.model.Designation;
import com.hrms.employee.service.DesignationLocalServiceUtil;
import com.hrms.employee.service.persistence.DesignationPersistence;
import com.hrms.employee.service.persistence.DesignationUtil;

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
public class DesignationPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.hrms.employee.service"));

	@Before
	public void setUp() {
		_persistence = DesignationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Designation> iterator = _designations.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Designation designation = _persistence.create(pk);

		Assert.assertNotNull(designation);

		Assert.assertEquals(designation.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Designation newDesignation = addDesignation();

		_persistence.remove(newDesignation);

		Designation existingDesignation = _persistence.fetchByPrimaryKey(
			newDesignation.getPrimaryKey());

		Assert.assertNull(existingDesignation);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDesignation();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Designation newDesignation = _persistence.create(pk);

		newDesignation.setDesignationCode(RandomTestUtil.randomString());

		newDesignation.setDesignationName(RandomTestUtil.randomString());

		newDesignation.setDescription(RandomTestUtil.randomString());

		newDesignation.setStatus(RandomTestUtil.randomString());

		newDesignation.setGroupId(RandomTestUtil.nextLong());

		newDesignation.setCompanyId(RandomTestUtil.nextLong());

		newDesignation.setUserId(RandomTestUtil.nextLong());

		newDesignation.setUserName(RandomTestUtil.randomString());

		newDesignation.setCreateDate(RandomTestUtil.nextDate());

		newDesignation.setModifiedDate(RandomTestUtil.nextDate());

		_designations.add(_persistence.update(newDesignation));

		Designation existingDesignation = _persistence.findByPrimaryKey(
			newDesignation.getPrimaryKey());

		Assert.assertEquals(
			existingDesignation.getDesignationId(),
			newDesignation.getDesignationId());
		Assert.assertEquals(
			existingDesignation.getDesignationCode(),
			newDesignation.getDesignationCode());
		Assert.assertEquals(
			existingDesignation.getDesignationName(),
			newDesignation.getDesignationName());
		Assert.assertEquals(
			existingDesignation.getDescription(),
			newDesignation.getDescription());
		Assert.assertEquals(
			existingDesignation.getStatus(), newDesignation.getStatus());
		Assert.assertEquals(
			existingDesignation.getGroupId(), newDesignation.getGroupId());
		Assert.assertEquals(
			existingDesignation.getCompanyId(), newDesignation.getCompanyId());
		Assert.assertEquals(
			existingDesignation.getUserId(), newDesignation.getUserId());
		Assert.assertEquals(
			existingDesignation.getUserName(), newDesignation.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingDesignation.getCreateDate()),
			Time.getShortTimestamp(newDesignation.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingDesignation.getModifiedDate()),
			Time.getShortTimestamp(newDesignation.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Designation newDesignation = addDesignation();

		Designation existingDesignation = _persistence.findByPrimaryKey(
			newDesignation.getPrimaryKey());

		Assert.assertEquals(existingDesignation, newDesignation);
	}

	@Test(expected = NoSuchDesignationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Designation> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"HRMS_Designation", "designationId", true, "designationCode", true,
			"designationName", true, "description", true, "status", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Designation newDesignation = addDesignation();

		Designation existingDesignation = _persistence.fetchByPrimaryKey(
			newDesignation.getPrimaryKey());

		Assert.assertEquals(existingDesignation, newDesignation);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Designation missingDesignation = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDesignation);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Designation newDesignation1 = addDesignation();
		Designation newDesignation2 = addDesignation();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDesignation1.getPrimaryKey());
		primaryKeys.add(newDesignation2.getPrimaryKey());

		Map<Serializable, Designation> designations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, designations.size());
		Assert.assertEquals(
			newDesignation1, designations.get(newDesignation1.getPrimaryKey()));
		Assert.assertEquals(
			newDesignation2, designations.get(newDesignation2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Designation> designations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(designations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Designation newDesignation = addDesignation();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDesignation.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Designation> designations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, designations.size());
		Assert.assertEquals(
			newDesignation, designations.get(newDesignation.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Designation> designations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(designations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Designation newDesignation = addDesignation();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDesignation.getPrimaryKey());

		Map<Serializable, Designation> designations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, designations.size());
		Assert.assertEquals(
			newDesignation, designations.get(newDesignation.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DesignationLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Designation>() {

				@Override
				public void performAction(Designation designation) {
					Assert.assertNotNull(designation);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Designation newDesignation = addDesignation();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Designation.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"designationId", newDesignation.getDesignationId()));

		List<Designation> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		Designation existingDesignation = result.get(0);

		Assert.assertEquals(existingDesignation, newDesignation);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Designation.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"designationId", RandomTestUtil.nextLong()));

		List<Designation> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Designation newDesignation = addDesignation();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Designation.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("designationId"));

		Object newDesignationId = newDesignation.getDesignationId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"designationId", new Object[] {newDesignationId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDesignationId = result.get(0);

		Assert.assertEquals(existingDesignationId, newDesignationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Designation.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("designationId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"designationId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Designation addDesignation() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Designation designation = _persistence.create(pk);

		designation.setDesignationCode(RandomTestUtil.randomString());

		designation.setDesignationName(RandomTestUtil.randomString());

		designation.setDescription(RandomTestUtil.randomString());

		designation.setStatus(RandomTestUtil.randomString());

		designation.setGroupId(RandomTestUtil.nextLong());

		designation.setCompanyId(RandomTestUtil.nextLong());

		designation.setUserId(RandomTestUtil.nextLong());

		designation.setUserName(RandomTestUtil.randomString());

		designation.setCreateDate(RandomTestUtil.nextDate());

		designation.setModifiedDate(RandomTestUtil.nextDate());

		_designations.add(_persistence.update(designation));

		return designation;
	}

	private List<Designation> _designations = new ArrayList<Designation>();
	private DesignationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:1844649927