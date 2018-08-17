create table INSURANCE_ORGS ( 
	INN BIGINT not null, 
	OGRN BIGINT not null, 
	NAME VARCHAR(128) not null, 
	ADDRESS VARCHAR(128) not null, 
	primary key (INN), 
	unique (OGRN)
	);
	