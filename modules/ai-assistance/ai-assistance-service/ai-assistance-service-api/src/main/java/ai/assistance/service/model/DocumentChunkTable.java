/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;AI_DocumentChunk&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentChunk
 * @generated
 */
public class DocumentChunkTable extends BaseTable<DocumentChunkTable> {

	public static final DocumentChunkTable INSTANCE = new DocumentChunkTable();

	public final Column<DocumentChunkTable, Long> chunkId = createColumn(
		"chunkId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DocumentChunkTable, Long> documentId = createColumn(
		"documentId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DocumentChunkTable, String> chunkText = createColumn(
		"chunkText", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DocumentChunkTable, Integer> pageNumber = createColumn(
		"pageNumber", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DocumentChunkTable, String> embedding = createColumn(
		"embedding", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private DocumentChunkTable() {
		super("AI_DocumentChunk", DocumentChunkTable::new);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-73280321