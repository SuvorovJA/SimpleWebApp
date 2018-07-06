package edu.sua.conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 
public class ConnUtils {
	
	private static  DataSource ds;
	
	static {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sWA");		
			if (ds == null) throw new SQLException("Unknown DataSource 'jdbc/sWA'");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Connect to any DB configured in META-INF/context.xml
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		return ds.getConnection();
	}

}