/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.persistence.test;

import ai.assistance.service.exception.NoSuchDocumentChunkException;
import ai.assistance.service.model.DocumentChunk;
import ai.assistance.service.service.DocumentChunkLocalServiceUtil;
import ai.assistance.service.service.persistence.DocumentChunkPersistence;
import ai.assistance.service.service.persistence.DocumentChunkUtil;

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
public class DocumentChunkPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "ai.assistance.service.service"));

	@Before
	public void setUp() {
		_persistence = DocumentChunkUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DocumentChunk> iterator = _documentChunks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentChunk documentChunk = _persistence.create(pk);

		Assert.assertNotNull(documentChunk);

		Assert.assertEquals(documentChunk.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DocumentChunk newDocumentChunk = addDocumentChunk();

		_persistence.remove(newDocumentChunk);

		DocumentChunk existingDocumentChunk = _persistence.fetchByPrimaryKey(
			newDocumentChunk.getPrimaryKey());

		Assert.assertNull(existingDocumentChunk);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDocumentChunk();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentChunk newDocumentChunk = _persistence.create(pk);

		newDocumentChunk.setDocumentId(RandomTestUtil.nextLong());

		newDocumentChunk.setChunkText(RandomTestUtil.randomString());

		newDocumentChunk.setPageNumber(RandomTestUtil.nextInt());

		newDocumentChunk.setEmbedding(RandomTestUtil.randomString());

		_documentChunks.add(_persistence.update(newDocumentChunk));

		DocumentChunk existingDocumentChunk = _persistence.findByPrimaryKey(
			newDocumentChunk.getPrimaryKey());

		Assert.assertEquals(
			existingDocumentChunk.getChunkId(), newDocumentChunk.getChunkId());
		Assert.assertEquals(
			existingDocumentChunk.getDocumentId(),
			newDocumentChunk.getDocumentId());
		Assert.assertEquals(
			existingDocumentChunk.getChunkText(),
			newDocumentChunk.getChunkText());
		Assert.assertEquals(
			existingDocumentChunk.getPageNumber(),
			newDocumentChunk.getPageNumber());
		Assert.assertEquals(
			existingDocumentChunk.getEmbedding(),
			newDocumentChunk.getEmbedding());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DocumentChunk newDocumentChunk = addDocumentChunk();

		DocumentChunk existingDocumentChunk = _persistence.findByPrimaryKey(
			newDocumentChunk.getPrimaryKey());

		Assert.assertEquals(existingDocumentChunk, newDocumentChunk);
	}

	@Test(expected = NoSuchDocumentChunkException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DocumentChunk> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"AI_DocumentChunk", "chunkId", true, "documentId", true,
			"chunkText", true, "pageNumber", true, "embedding", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DocumentChunk newDocumentChunk = addDocumentChunk();

		DocumentChunk existingDocumentChunk = _persistence.fetchByPrimaryKey(
			newDocumentChunk.getPrimaryKey());

		Assert.assertEquals(existingDocumentChunk, newDocumentChunk);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentChunk missingDocumentChunk = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDocumentChunk);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DocumentChunk newDocumentChunk1 = addDocumentChunk();
		DocumentChunk newDocumentChunk2 = addDocumentChunk();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocumentChunk1.getPrimaryKey());
		primaryKeys.add(newDocumentChunk2.getPrimaryKey());

		Map<Serializable, DocumentChunk> documentChunks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, documentChunks.size());
		Assert.assertEquals(
			newDocumentChunk1,
			documentChunks.get(newDocumentChunk1.getPrimaryKey()));
		Assert.assertEquals(
			newDocumentChunk2,
			documentChunks.get(newDocumentChunk2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DocumentChunk> documentChunks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(documentChunks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DocumentChunk newDocumentChunk = addDocumentChunk();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocumentChunk.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DocumentChunk> documentChunks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, documentChunks.size());
		Assert.assertEquals(
			newDocumentChunk,
			documentChunks.get(newDocumentChunk.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DocumentChunk> documentChunks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(documentChunks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DocumentChunk newDocumentChunk = addDocumentChunk();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocumentChunk.getPrimaryKey());

		Map<Serializable, DocumentChunk> documentChunks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, documentChunks.size());
		Assert.assertEquals(
			newDocumentChunk,
			documentChunks.get(newDocumentChunk.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DocumentChunkLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DocumentChunk>() {

				@Override
				public void performAction(DocumentChunk documentChunk) {
					Assert.assertNotNull(documentChunk);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DocumentChunk newDocumentChunk = addDocumentChunk();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocumentChunk.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"chunkId", newDocumentChunk.getChunkId()));

		List<DocumentChunk> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DocumentChunk existingDocumentChunk = result.get(0);

		Assert.assertEquals(existingDocumentChunk, newDocumentChunk);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocumentChunk.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("chunkId", RandomTestUtil.nextLong()));

		List<DocumentChunk> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DocumentChunk newDocumentChunk = addDocumentChunk();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocumentChunk.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("chunkId"));

		Object newChunkId = newDocumentChunk.getChunkId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("chunkId", new Object[] {newChunkId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingChunkId = result.get(0);

		Assert.assertEquals(existingChunkId, newChunkId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DocumentChunk.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("chunkId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"chunkId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DocumentChunk addDocumentChunk() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentChunk documentChunk = _persistence.create(pk);

		documentChunk.setDocumentId(RandomTestUtil.nextLong());

		documentChunk.setChunkText(RandomTestUtil.randomString());

		documentChunk.setPageNumber(RandomTestUtil.nextInt());

		documentChunk.setEmbedding(RandomTestUtil.randomString());

		_documentChunks.add(_persistence.update(documentChunk));

		return documentChunk;
	}

	private List<DocumentChunk> _documentChunks =
		new ArrayList<DocumentChunk>();
	private DocumentChunkPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:-964444369