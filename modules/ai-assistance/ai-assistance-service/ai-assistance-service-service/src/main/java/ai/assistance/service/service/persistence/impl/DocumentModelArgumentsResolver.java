/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.persistence.impl;

import ai.assistance.service.model.DocumentTable;
import ai.assistance.service.model.impl.DocumentImpl;
import ai.assistance.service.model.impl.DocumentModelImpl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from Document.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=ai.assistance.service.model.impl.DocumentImpl",
		"table.name=AI_Document"
	},
	service = ArgumentsResolver.class
)
public class DocumentModelArgumentsResolver implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		DocumentModelImpl documentModelImpl = (DocumentModelImpl)baseModel;

		long columnBitmask = documentModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(documentModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |= documentModelImpl.getColumnBitmask(
					columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(documentModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return DocumentImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return DocumentTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		DocumentModelImpl documentModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = documentModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = documentModelImpl.getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}
// LIFERAY-SERVICE-BUILDER-HASH:1924087424