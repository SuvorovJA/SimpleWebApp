create table USER_ACCOUNT ( 
	USER_NAME VARCHAR(30) not null, 
	GENDER VARCHAR(1) not null, 
	PASSWORD  VARCHAR(30) not null, 
	primary key (USER_NAME)
	);


create table INSURANCE_ORGS ( 
	INN BIGINT not null, 
	OGRN BIGINT not null, 
	NAME VARCHAR(128) not null, 
	ADDRESS VARCHAR(128) not null, 
	primary key (INN), 
	unique (OGRN)
	);
	