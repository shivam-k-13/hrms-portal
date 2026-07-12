/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.attendance.service.persistence.test;

import com.hrms.attendance.exception.NoSuchAttendanceException;
import com.hrms.attendance.model.Attendance;
import com.hrms.attendance.service.AttendanceLocalServiceUtil;
import com.hrms.attendance.service.persistence.AttendancePersistence;
import com.hrms.attendance.service.persistence.AttendanceUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.AssertUtils;
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
public class AttendancePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.hrms.attendance.service"));

	@Before
	public void setUp() {
		_persistence = AttendanceUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Attendance> iterator = _attendances.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Attendance attendance = _persistence.create(pk);

		Assert.assertNotNull(attendance);

		Assert.assertEquals(attendance.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Attendance newAttendance = addAttendance();

		_persistence.remove(newAttendance);

		Attendance existingAttendance = _persistence.fetchByPrimaryKey(
			newAttendance.getPrimaryKey());

		Assert.assertNull(existingAttendance);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAttendance();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Attendance newAttendance = _persistence.create(pk);

		newAttendance.setGroupId(RandomTestUtil.nextLong());

		newAttendance.setCompanyId(RandomTestUtil.nextLong());

		newAttendance.setUserId(RandomTestUtil.nextLong());

		newAttendance.setCreateDate(RandomTestUtil.nextDate());

		newAttendance.setModifiedDate(RandomTestUtil.nextDate());

		newAttendance.setEmployeeId(RandomTestUtil.nextLong());

		newAttendance.setAttendanceDate(RandomTestUtil.nextDate());

		newAttendance.setCheckInTime(RandomTestUtil.nextDate());

		newAttendance.setCheckOutTime(RandomTestUtil.nextDate());

		newAttendance.setCheckInIP(RandomTestUtil.randomString());

		newAttendance.setCheckOutIP(RandomTestUtil.randomString());

		newAttendance.setCheckInLatitude(RandomTestUtil.nextDouble());

		newAttendance.setCheckInLongitude(RandomTestUtil.nextDouble());

		newAttendance.setCheckOutLatitude(RandomTestUtil.nextDouble());

		newAttendance.setCheckOutLongitude(RandomTestUtil.nextDouble());

		newAttendance.setStatus(RandomTestUtil.randomString());

		_attendances.add(_persistence.update(newAttendance));

		Attendance existingAttendance = _persistence.findByPrimaryKey(
			newAttendance.getPrimaryKey());

		Assert.assertEquals(
			existingAttendance.getAttendanceId(),
			newAttendance.getAttendanceId());
		Assert.assertEquals(
			existingAttendance.getGroupId(), newAttendance.getGroupId());
		Assert.assertEquals(
			existingAttendance.getCompanyId(), newAttendance.getCompanyId());
		Assert.assertEquals(
			existingAttendance.getUserId(), newAttendance.getUserId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingAttendance.getCreateDate()),
			Time.getShortTimestamp(newAttendance.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingAttendance.getModifiedDate()),
			Time.getShortTimestamp(newAttendance.getModifiedDate()));
		Assert.assertEquals(
			existingAttendance.getEmployeeId(), newAttendance.getEmployeeId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingAttendance.getAttendanceDate()),
			Time.getShortTimestamp(newAttendance.getAttendanceDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingAttendance.getCheckInTime()),
			Time.getShortTimestamp(newAttendance.getCheckInTime()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingAttendance.getCheckOutTime()),
			Time.getShortTimestamp(newAttendance.getCheckOutTime()));
		Assert.assertEquals(
			existingAttendance.getCheckInIP(), newAttendance.getCheckInIP());
		Assert.assertEquals(
			existingAttendance.getCheckOutIP(), newAttendance.getCheckOutIP());
		AssertUtils.assertEquals(
			existingAttendance.getCheckInLatitude(),
			newAttendance.getCheckInLatitude());
		AssertUtils.assertEquals(
			existingAttendance.getCheckInLongitude(),
			newAttendance.getCheckInLongitude());
		AssertUtils.assertEquals(
			existingAttendance.getCheckOutLatitude(),
			newAttendance.getCheckOutLatitude());
		AssertUtils.assertEquals(
			existingAttendance.getCheckOutLongitude(),
			newAttendance.getCheckOutLongitude());
		Assert.assertEquals(
			existingAttendance.getStatus(), newAttendance.getStatus());
	}

	@Test
	public void testCountByEmployeeId() throws Exception {
		_persistence.countByEmployeeId(RandomTestUtil.nextLong());

		_persistence.countByEmployeeId(0L);
	}

	@Test
	public void testCountByEmployeeAndDate() throws Exception {
		_persistence.countByEmployeeAndDate(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate());

		_persistence.countByEmployeeAndDate(0L, RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Attendance newAttendance = addAttendance();

		Attendance existingAttendance = _persistence.findByPrimaryKey(
			newAttendance.getPrimaryKey());

		Assert.assertEquals(existingAttendance, newAttendance);
	}

	@Test(expected = NoSuchAttendanceException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Attendance> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"HRMS_Attendance", "attendanceId", true, "groupId", true,
			"companyId", true, "userId", true, "createDate", true,
			"modifiedDate", true, "employeeId", true, "attendanceDate", true,
			"checkInTime", true, "checkOutTime", true, "checkInIP", true,
			"checkOutIP", true, "checkInLatitude", true, "checkInLongitude",
			true, "checkOutLatitude", true, "checkOutLongitude", true, "status",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Attendance newAttendance = addAttendance();

		Attendance existingAttendance = _persistence.fetchByPrimaryKey(
			newAttendance.getPrimaryKey());

		Assert.assertEquals(existingAttendance, newAttendance);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Attendance missingAttendance = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAttendance);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Attendance newAttendance1 = addAttendance();
		Attendance newAttendance2 = addAttendance();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAttendance1.getPrimaryKey());
		primaryKeys.add(newAttendance2.getPrimaryKey());

		Map<Serializable, Attendance> attendances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, attendances.size());
		Assert.assertEquals(
			newAttendance1, attendances.get(newAttendance1.getPrimaryKey()));
		Assert.assertEquals(
			newAttendance2, attendances.get(newAttendance2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Attendance> attendances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(attendances.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Attendance newAttendance = addAttendance();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAttendance.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Attendance> attendances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, attendances.size());
		Assert.assertEquals(
			newAttendance, attendances.get(newAttendance.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Attendance> attendances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(attendances.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Attendance newAttendance = addAttendance();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAttendance.getPrimaryKey());

		Map<Serializable, Attendance> attendances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, attendances.size());
		Assert.assertEquals(
			newAttendance, attendances.get(newAttendance.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			AttendanceLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Attendance>() {

				@Override
				public void performAction(Attendance attendance) {
					Assert.assertNotNull(attendance);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Attendance newAttendance = addAttendance();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Attendance.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"attendanceId", newAttendance.getAttendanceId()));

		List<Attendance> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		Attendance existingAttendance = result.get(0);

		Assert.assertEquals(existingAttendance, newAttendance);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Attendance.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"attendanceId", RandomTestUtil.nextLong()));

		List<Attendance> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Attendance newAttendance = addAttendance();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Attendance.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("attendanceId"));

		Object newAttendanceId = newAttendance.getAttendanceId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"attendanceId", new Object[] {newAttendanceId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAttendanceId = result.get(0);

		Assert.assertEquals(existingAttendanceId, newAttendanceId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Attendance.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("attendanceId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"attendanceId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Attendance newAttendance = addAttendance();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newAttendance.getPrimaryKey()));
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

		Attendance newAttendance = addAttendance();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Attendance.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"attendanceId", newAttendance.getAttendanceId()));

		List<Attendance> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Attendance attendance) {
		Assert.assertEquals(
			Long.valueOf(attendance.getEmployeeId()),
			ReflectionTestUtil.<Long>invoke(
				attendance, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "employeeId"));
		Assert.assertEquals(
			attendance.getAttendanceDate(),
			ReflectionTestUtil.invoke(
				attendance, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "attendanceDate"));
	}

	protected Attendance addAttendance() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Attendance attendance = _persistence.create(pk);

		attendance.setGroupId(RandomTestUtil.nextLong());

		attendance.setCompanyId(RandomTestUtil.nextLong());

		attendance.setUserId(RandomTestUtil.nextLong());

		attendance.setCreateDate(RandomTestUtil.nextDate());

		attendance.setModifiedDate(RandomTestUtil.nextDate());

		attendance.setEmployeeId(RandomTestUtil.nextLong());

		attendance.setAttendanceDate(RandomTestUtil.nextDate());

		attendance.setCheckInTime(RandomTestUtil.nextDate());

		attendance.setCheckOutTime(RandomTestUtil.nextDate());

		attendance.setCheckInIP(RandomTestUtil.randomString());

		attendance.setCheckOutIP(RandomTestUtil.randomString());

		attendance.setCheckInLatitude(RandomTestUtil.nextDouble());

		attendance.setCheckInLongitude(RandomTestUtil.nextDouble());

		attendance.setCheckOutLatitude(RandomTestUtil.nextDouble());

		attendance.setCheckOutLongitude(RandomTestUtil.nextDouble());

		attendance.setStatus(RandomTestUtil.randomString());

		_attendances.add(_persistence.update(attendance));

		return attendance;
	}

	private List<Attendance> _attendances = new ArrayList<Attendance>();
	private AttendancePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:1731123068