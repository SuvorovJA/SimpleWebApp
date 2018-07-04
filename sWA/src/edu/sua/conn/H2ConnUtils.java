package edu.sua.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 
public class H2ConnUtils {

	// Connect to H2
	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		String hostName = "localhost";
		String dbName = "./sWA"; 		// full path for TCP & Embed modes; only NAME for MEM mode
										// "./" --> folder where h2*.jar running
										// "~/" --> home holder of user
		String userName = "sa";
		String password = "";

		return getConnection(hostName, dbName, userName, password);
	}

	public static Connection getConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {

		// TCP mode connection to H2
		String connectionURL = "jdbc:h2:tcp://" + hostName + "/" + dbName;

		// Embedded mode
		// String connectionURL = "jdbc:h2:/" + dbName;

		// MEM mode
		// String connectionURL = "jdbc:h2:mem:/" + dbName;

		// no work without this line (Driver not found. Jar placed in WEB-INF/lib/h2*.jar) 
		Class.forName("org.h2.Driver");
		
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}