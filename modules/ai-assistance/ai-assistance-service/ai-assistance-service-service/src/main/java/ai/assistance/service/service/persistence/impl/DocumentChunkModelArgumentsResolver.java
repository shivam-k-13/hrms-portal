/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.service.persistence.impl;

import ai.assistance.service.model.DocumentChunkTable;
import ai.assistance.service.model.impl.DocumentChunkImpl;
import ai.assistance.service.model.impl.DocumentChunkModelImpl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from DocumentChunk.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=ai.assistance.service.model.impl.DocumentChunkImpl",
		"table.name=AI_DocumentChunk"
	},
	service = ArgumentsResolver.class
)
public class DocumentChunkModelArgumentsResolver implements ArgumentsResolver {

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

		DocumentChunkModelImpl documentChunkModelImpl =
			(DocumentChunkModelImpl)baseModel;

		long columnBitmask = documentChunkModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(documentChunkModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					documentChunkModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(documentChunkModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return DocumentChunkImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return DocumentChunkTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		DocumentChunkModelImpl documentChunkModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = documentChunkModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = documentChunkModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}
// LIFERAY-SERVICE-BUILDER-HASH:-671144198