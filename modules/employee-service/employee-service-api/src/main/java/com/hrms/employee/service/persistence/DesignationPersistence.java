/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hrms.employee.service.persistence;

import com.hrms.employee.exception.NoSuchDesignationException;
import com.hrms.employee.model.Designation;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the designation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DesignationUtil
 * @generated
 */
@ProviderType
public interface DesignationPersistence extends BasePersistence<Designation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DesignationUtil} to access the designation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the designation in the entity cache if it is enabled.
	 *
	 * @param designation the designation
	 */
	public void cacheResult(Designation designation);

	/**
	 * Caches the designations in the entity cache if it is enabled.
	 *
	 * @param designations the designations
	 */
	public void cacheResult(java.util.List<Designation> designations);

	/**
	 * Creates a new designation with the primary key. Does not add the designation to the database.
	 *
	 * @param designationId the primary key for the new designation
	 * @return the new designation
	 */
	public Designation create(long designationId);

	/**
	 * Removes the designation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param designationId the primary key of the designation
	 * @return the designation that was removed
	 * @throws NoSuchDesignationException if a designation with the primary key could not be found
	 */
	public Designation remove(long designationId)
		throws NoSuchDesignationException;

	public Designation updateImpl(Designation designation);

	/**
	 * Returns the designation with the primary key or throws a <code>NoSuchDesignationException</code> if it could not be found.
	 *
	 * @param designationId the primary key of the designation
	 * @return the designation
	 * @throws NoSuchDesignationException if a designation with the primary key could not be found
	 */
	public Designation findByPrimaryKey(long designationId)
		throws NoSuchDesignationException;

	/**
	 * Returns the designation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param designationId the primary key of the designation
	 * @return the designation, or <code>null</code> if a designation with the primary key could not be found
	 */
	public Designation fetchByPrimaryKey(long designationId);

	/**
	 * Returns all the designations.
	 *
	 * @return the designations
	 */
	public java.util.List<Designation> findAll();

	/**
	 * Returns a range of all the designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.employee.model.impl.DesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of designations
	 * @param end the upper bound of the range of designations (not inclusive)
	 * @return the range of designations
	 */
	public java.util.List<Designation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.employee.model.impl.DesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of designations
	 * @param end the upper bound of the range of designations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of designations
	 */
	public java.util.List<Designation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Designation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hrms.employee.model.impl.DesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of designations
	 * @param end the upper bound of the range of designations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of designations
	 */
	public java.util.List<Designation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Designation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the designations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of designations.
	 *
	 * @return the number of designations
	 */
	public int countAll();

}
// LIFERAY-SERVICE-BUILDER-HASH:-305014420