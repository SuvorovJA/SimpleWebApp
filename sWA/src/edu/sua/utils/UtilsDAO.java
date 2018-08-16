package edu.sua.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.sua.entities.InsuranceOrg;
import edu.sua.entities.UserAccount;

public class UtilsDAO {
	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {

		String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "
				+ " where a.User_Name = ? and a.password= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String gender = rs.getString("Gender");
			//
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}

	public static UserAccount findUser(Connection conn, String userName) throws SQLException {

		String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
				+ " where a.User_Name = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			//
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}

	public static List<InsuranceOrg> queryInsuranceOrg(Connection conn) throws SQLException {
		String sql = "Select a.Code, a.Name, a.Price from Product a ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		List<InsuranceOrg> list = new ArrayList<InsuranceOrg>();
		while (rs.next()) {
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			//
			InsuranceOrg insuranceOrg = new InsuranceOrg();
			insuranceOrg.setCode(code);
			insuranceOrg.setName(name);
			insuranceOrg.setPrice(price);
			list.add(insuranceOrg);
		}
		return list;
	}

	public static InsuranceOrg findInsuranceOrg(Connection conn, String code) throws SQLException {
		String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			//
			InsuranceOrg insuranceOrg = new InsuranceOrg(code, name, price);
			return insuranceOrg;
		}
		return null;
	}

	public static void updateInsuranceOrg(Connection conn, InsuranceOrg insuranceOrg) throws SQLException {
		String sql = "Update Product set Name =?, Price=? where Code=? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, insuranceOrg.getName());
		pstm.setFloat(2, insuranceOrg.getPrice());
		pstm.setString(3, insuranceOrg.getCode());
		pstm.executeUpdate();
	}

	public static void insertInsuranceOrg(Connection conn, InsuranceOrg insuranceOrg) throws SQLException {
		String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, insuranceOrg.getCode());
		pstm.setString(2, insuranceOrg.getName());
		pstm.setFloat(3, insuranceOrg.getPrice());
		pstm.executeUpdate();
	}

	public static void deleteInsuranceOrg(Connection conn, String code) throws SQLException {
		String sql = "Delete From Product where Code= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		pstm.executeUpdate();
	}

}
