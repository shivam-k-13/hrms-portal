/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.leave.service.persistence.test;

import com.hrms.leave.exception.NoSuchLeaveRequestException;
import com.hrms.leave.model.LeaveRequest;
import com.hrms.leave.service.LeaveRequestLocalServiceUtil;
import com.hrms.leave.service.persistence.LeaveRequestPersistence;
import com.hrms.leave.service.persistence.LeaveRequestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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
public class LeaveRequestPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.hrms.leave.service"));

	@Before
	public void setUp() {
		_persistence = LeaveRequestUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LeaveRequest> iterator = _leaveRequests.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LeaveRequest leaveRequest = _persistence.create(pk);

		Assert.assertNotNull(leaveRequest);

		Assert.assertEquals(leaveRequest.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LeaveRequest newLeaveRequest = addLeaveRequest();

		_persistence.remove(newLeaveRequest);

		LeaveRequest existingLeaveRequest = _persistence.fetchByPrimaryKey(
			newLeaveRequest.getPrimaryKey());

		Assert.assertNull(existingLeaveRequest);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLeaveRequest();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LeaveRequest newLeaveRequest = _persistence.create(pk);

		newLeaveRequest.setUuid(RandomTestUtil.randomString());

		newLeaveRequest.setGroupId(RandomTestUtil.nextLong());

		newLeaveRequest.setCompanyId(RandomTestUtil.nextLong());

		newLeaveRequest.setUserId(RandomTestUtil.nextLong());

		newLeaveRequest.setUserName(RandomTestUtil.randomString());

		newLeaveRequest.setEmployeeId(RandomTestUtil.nextLong());

		newLeaveRequest.setLeaveType(RandomTestUtil.randomString());

		newLeaveRequest.setFromDate(RandomTestUtil.nextDate());

		newLeaveRequest.setToDate(RandomTestUtil.nextDate());

		newLeaveRequest.setReason(RandomTestUtil.randomString());

		newLeaveRequest.setStatus(RandomTestUtil.randomString());

		newLeaveRequest.setApproverUserId(RandomTestUtil.nextLong());

		newLeaveRequest.setApproverComments(RandomTestUtil.randomString());

		newLeaveRequest.setCreateDate(RandomTestUtil.nextDate());

		newLeaveRequest.setModifiedDate(RandomTestUtil.nextDate());

		_leaveRequests.add(_persistence.update(newLeaveRequest));

		LeaveRequest existingLeaveRequest = _persistence.findByPrimaryKey(
			newLeaveRequest.getPrimaryKey());

		Assert.assertEquals(
			existingLeaveRequest.getUuid(), newLeaveRequest.getUuid());
		Assert.assertEquals(
			existingLeaveRequest.getLeaveRequestId(),
			newLeaveRequest.getLeaveRequestId());
		Assert.assertEquals(
			existingLeaveRequest.getGroupId(), newLeaveRequest.getGroupId());
		Assert.assertEquals(
			existingLeaveRequest.getCompanyId(),
			newLeaveRequest.getCompanyId());
		Assert.assertEquals(
			existingLeaveRequest.getUserId(), newLeaveRequest.getUserId());
		Assert.assertEquals(
			existingLeaveRequest.getUserName(), newLeaveRequest.getUserName());
		Assert.assertEquals(
			existingLeaveRequest.getEmployeeId(),
			newLeaveRequest.getEmployeeId());
		Assert.assertEquals(
			existingLeaveRequest.getLeaveType(),
			newLeaveRequest.getLeaveType());
		Assert.assertEquals(
			Time.getShortTimestamp(existingLeaveRequest.getFromDate()),
			Time.getShortTimestamp(newLeaveRequest.getFromDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingLeaveRequest.getToDate()),
			Time.getShortTimestamp(newLeaveRequest.getToDate()));
		Assert.assertEquals(
			existingLeaveRequest.getReason(), newLeaveRequest.getReason());
		Assert.assertEquals(
			existingLeaveRequest.getStatus(), newLeaveRequest.getStatus());
		Assert.assertEquals(
			existingLeaveRequest.getApproverUserId(),
			newLeaveRequest.getApproverUserId());
		Assert.assertEquals(
			existingLeaveRequest.getApproverComments(),
			newLeaveRequest.getApproverComments());
		Assert.assertEquals(
			Time.getShortTimestamp(existingLeaveRequest.getCreateDate()),
			Time.getShortTimestamp(newLeaveRequest.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingLeaveRequest.getModifiedDate()),
			Time.getShortTimestamp(newLeaveRequest.getModifiedDate()));
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByEmployeeId() throws Exception {
		_persistence.countByEmployeeId(RandomTestUtil.nextLong());

		_persistence.countByEmployeeId(0L);
	}

	@Test
	public void testCountByStatus() throws Exception {
		_persistence.countByStatus("");

		_persistence.countByStatus("null");

		_persistence.countByStatus((String)null);
	}

	@Test
	public void testCountByEmployeeId_Status() throws Exception {
		_persistence.countByEmployeeId_Status(RandomTestUtil.nextLong(), "");

		_persistence.countByEmployeeId_Status(0L, "null");

		_persistence.countByEmployeeId_Status(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LeaveRequest newLeaveRequest = addLeaveRequest();

		LeaveRequest existingLeaveRequest = _persistence.findByPrimaryKey(
			newLeaveRequest.getPrimaryKey());

		Assert.assertEquals(existingLeaveRequest, newLeaveRequest);
	}

	@Test(expected = NoSuchLeaveRequestException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<LeaveRequest> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"HRMS_LeaveRequest", "uuid", true, "leaveRequestId", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "employeeId", true, "leaveType", true, "fromDate", true,
			"toDate", true, "reason", true, "status", true, "approverUserId",
			true, "approverComments", true, "createDate", true, "modifiedDate",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LeaveRequest newLeaveRequest = addLeaveRequest();

		LeaveRequest existingLeaveRequest = _persistence.fetchByPrimaryKey(
			newLeaveRequest.getPrimaryKey());

		Assert.assertEquals(existingLeaveRequest, newLeaveRequest);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LeaveRequest missingLeaveRequest = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLeaveRequest);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		LeaveRequest newLeaveRequest1 = addLeaveRequest();
		LeaveRequest newLeaveRequest2 = addLeaveRequest();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLeaveRequest1.getPrimaryKey());
		primaryKeys.add(newLeaveRequest2.getPrimaryKey());

		Map<Serializable, LeaveRequest> leaveRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, leaveRequests.size());
		Assert.assertEquals(
			newLeaveRequest1,
			leaveRequests.get(newLeaveRequest1.getPrimaryKey()));
		Assert.assertEquals(
			newLeaveRequest2,
			leaveRequests.get(newLeaveRequest2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LeaveRequest> leaveRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(leaveRequests.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		LeaveRequest newLeaveRequest = addLeaveRequest();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLeaveRequest.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LeaveRequest> leaveRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, leaveRequests.size());
		Assert.assertEquals(
			newLeaveRequest,
			leaveRequests.get(newLeaveRequest.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LeaveRequest> leaveRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(leaveRequests.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		LeaveRequest newLeaveRequest = addLeaveRequest();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLeaveRequest.getPrimaryKey());

		Map<Serializable, LeaveRequest> leaveRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, leaveRequests.size());
		Assert.assertEquals(
			newLeaveRequest,
			leaveRequests.get(newLeaveRequest.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			LeaveRequestLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<LeaveRequest>() {

				@Override
				public void performAction(LeaveRequest leaveRequest) {
					Assert.assertNotNull(leaveRequest);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		LeaveRequest newLeaveRequest = addLeaveRequest();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LeaveRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"leaveRequestId", newLeaveRequest.getLeaveRequestId()));

		List<LeaveRequest> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		LeaveRequest existingLeaveRequest = result.get(0);

		Assert.assertEquals(existingLeaveRequest, newLeaveRequest);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LeaveRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"leaveRequestId", RandomTestUtil.nextLong()));

		List<LeaveRequest> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		LeaveRequest newLeaveRequest = addLeaveRequest();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LeaveRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("leaveRequestId"));

		Object newLeaveRequestId = newLeaveRequest.getLeaveRequestId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"leaveRequestId", new Object[] {newLeaveRequestId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLeaveRequestId = result.get(0);

		Assert.assertEquals(existingLeaveRequestId, newLeaveRequestId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LeaveRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("leaveRequestId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"leaveRequestId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LeaveRequest newLeaveRequest = addLeaveRequest();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newLeaveRequest.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		LeaveRequest newLeaveRequest = addLeaveRequest();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LeaveRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"leaveRequestId", newLeaveRequest.getLeaveRequestId()));

		List<LeaveRequest> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(LeaveRequest leaveRequest) {
		Assert.assertEquals(
			leaveRequest.getUuid(),
			ReflectionTestUtil.invoke(
				leaveRequest, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(leaveRequest.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				leaveRequest, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected LeaveRequest addLeaveRequest() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LeaveRequest leaveRequest = _persistence.create(pk);

		leaveRequest.setUuid(RandomTestUtil.randomString());

		leaveRequest.setGroupId(RandomTestUtil.nextLong());

		leaveRequest.setCompanyId(RandomTestUtil.nextLong());

		leaveRequest.setUserId(RandomTestUtil.nextLong());

		leaveRequest.setUserName(RandomTestUtil.randomString());

		leaveRequest.setEmployeeId(RandomTestUtil.nextLong());

		leaveRequest.setLeaveType(RandomTestUtil.randomString());

		leaveRequest.setFromDate(RandomTestUtil.nextDate());

		leaveRequest.setToDate(RandomTestUtil.nextDate());

		leaveRequest.setReason(RandomTestUtil.randomString());

		leaveRequest.setStatus(RandomTestUtil.randomString());

		leaveRequest.setApproverUserId(RandomTestUtil.nextLong());

		leaveRequest.setApproverComments(RandomTestUtil.randomString());

		leaveRequest.setCreateDate(RandomTestUtil.nextDate());

		leaveRequest.setModifiedDate(RandomTestUtil.nextDate());

		_leaveRequests.add(_persistence.update(leaveRequest));

		return leaveRequest;
	}

	private List<LeaveRequest> _leaveRequests = new ArrayList<LeaveRequest>();
	private LeaveRequestPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:1687086218