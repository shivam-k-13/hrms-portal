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
 * The extended model interface for the Document service. Represents a row in the &quot;AI_Document&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentModel
 * @generated
 */
@ImplementationClassName("ai.assistance.service.model.impl.DocumentImpl")
@ProviderType
public interface Document extends DocumentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>ai.assistance.service.model.impl.DocumentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Document, Long> DOCUMENT_ID_ACCESSOR =
		new Accessor<Document, Long>() {

			@Override
			public Long get(Document document) {
				return document.getDocumentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Document> getTypeClass() {
				return Document.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:1925686880