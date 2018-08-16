package edu.sua.conn;

import java.util.ArrayList;
import java.util.List;

/*that class contain only SQL script statements*/

public class dbPopulate {

	private static List<String> ddl;
	private static List<String> dml;

	static {
		ddl = new ArrayList<>();
		ddl.add("create table USER_ACCOUNT ( USER_NAME VARCHAR(30) not null, GENDER VARCHAR(1) not null, PASSWORD  VARCHAR(30) not null, primary key (USER_NAME));");
		ddl.add("create table INSURANCE_ORGS ( INN BIGINT not null, OGRN BIGINT not null, NAME VARCHAR(128) not null, ADDRESS VARCHAR(128) not null, primary key (INN), unique (OGRN));");
		
		dml = new ArrayList<>();
		dml.add("insert into USER_ACCOUNT (USER_NAME, GENDER, PASSWORD) values ('tom', 'M', 'tom001');");
		dml.add("insert into USER_ACCOUNT (USER_NAME, GENDER, PASSWORD) values ('jerry', 'M', 'jerry001');");
		dml.add("insert into INSURANCE_ORGS (INN, OGRN, NAME, ADDRESS) values (111111111111, 1234567890123, 'Общее Страхование', 'Москва, Тверская 1' );");
		dml.add("insert into INSURANCE_ORGS (INN, OGRN, NAME, ADDRESS) values (222222222222, 2345678901234, 'Частное страховое общество', 'Томск, Тверская 2' );");	
	}

	public static List<String> getDdl() {
		return ddl;
	}

	public static List<String> getDml() {
		return dml;
	}

}
