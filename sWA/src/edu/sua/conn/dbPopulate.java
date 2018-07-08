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
		ddl.add("create table PRODUCT ( CODE VARCHAR(20) not null, NAME VARCHAR(128) not null, PRICE FLOAT not null, primary key (CODE));");
		
		dml = new ArrayList<>();
		dml.add("insert into user_account (USER_NAME, GENDER, PASSWORD) values ('tom', 'M', 'tom001');");
		dml.add("insert into user_account (USER_NAME, GENDER, PASSWORD) values ('jerry', 'M', 'jerry001');");
		dml.add("insert into product (CODE, NAME, PRICE) values ('P001', 'Java Core', 100);");
		dml.add("insert into product (CODE, NAME, PRICE)	values ('P002', 'C# Core', 90);");	
	}

	public static List<String> getDdl() {
		return ddl;
	}

	public static List<String> getDml() {
		return dml;
	}

}
