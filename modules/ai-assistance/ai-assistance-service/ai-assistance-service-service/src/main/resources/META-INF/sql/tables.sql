create table AI_Document (
	documentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	fileEntryId LONG,
	status VARCHAR(75) null
);

create table AI_DocumentChunk (
	chunkId LONG not null primary key,
	documentId LONG,
	chunkText VARCHAR(75) null,
	pageNumber INTEGER,
	embedding VARCHAR(75) null
);