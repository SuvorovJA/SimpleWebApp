package edu.sua.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		return H2ConnUtils.getConnection();
	}
	
	public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
 
    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
    
    
	//
	// Test Connection ...
	//
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Get connection ... ");
		// Get a Connection object
		Connection conn = ConnectionUtils.getConnection();
		System.out.println("Get connection " + conn);
		System.out.println("Done!");
	}

	
	
}
