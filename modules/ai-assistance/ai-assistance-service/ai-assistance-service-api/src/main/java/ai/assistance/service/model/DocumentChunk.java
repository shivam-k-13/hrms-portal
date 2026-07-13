/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package ai.assistance.service.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the DocumentChunk service. Represents a row in the &quot;AI_DocumentChunk&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentChunkModel
 * @generated
 */
@ImplementationClassName("ai.assistance.service.model.impl.DocumentChunkImpl")
@ProviderType
public interface DocumentChunk extends DocumentChunkModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>ai.assistance.service.model.impl.DocumentChunkImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DocumentChunk, Long> CHUNK_ID_ACCESSOR =
		new Accessor<DocumentChunk, Long>() {

			@Override
			public Long get(DocumentChunk documentChunk) {
				return documentChunk.getChunkId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DocumentChunk> getTypeClass() {
				return DocumentChunk.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:-1366838708