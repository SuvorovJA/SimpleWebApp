package edu.sua.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.sua.entities.InsuranceOrg;

public class UtilsDAO {
	
	public static List<InsuranceOrg> queryInsuranceOrgs(Connection conn, String filter) throws SQLException {
		String sql = "SELECT a.Inn, a.Ogrn, a.Name, a.Address FROM Insurance_Orgs a ";
		if (filter != "") {
			sql += " WHERE " + filter; 
		} 

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		List<InsuranceOrg> list = new ArrayList<InsuranceOrg>();
		while (rs.next()) {
			long inn = rs.getLong("Inn");
			long ogrn = rs.getLong("Ogrn");
			String address = rs.getString("Address");
			String name = rs.getString("Name");
			//
			InsuranceOrg insuranceOrg = new InsuranceOrg();
			insuranceOrg.setInn(inn);
			insuranceOrg.setName(name);
			insuranceOrg.setAddress(address);
			insuranceOrg.setOgrn(ogrn);
			list.add(insuranceOrg);
		}
		return list;
	}

	public static InsuranceOrg findInsuranceOrg(Connection conn, long inn) throws SQLException {
		String sql = "SELECT a.Inn, a.Ogrn, a.Name, a.Address FROM Insurance_Orgs a where a.Inn=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, inn);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			long ogrn = rs.getLong("Ogrn");
			String address = rs.getString("Address");
			String name = rs.getString("Name");
			//
			InsuranceOrg insuranceOrg = new InsuranceOrg(inn, ogrn, name, address);
			return insuranceOrg;
		}
		return null;
	}

	public static void updateInsuranceOrg(Connection conn, InsuranceOrg insuranceOrg) throws SQLException {
		String sql = "UPDATE Insurance_Orgs set Ogrn = ?, Name =?, Address=? WHERE Inn=? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, insuranceOrg.getOgrn());
		pstm.setString(2, insuranceOrg.getName());
		pstm.setString(3, insuranceOrg.getAddress());
		pstm.setLong(4, insuranceOrg.getInn());
		pstm.executeUpdate();
	}

	public static void insertInsuranceOrg(Connection conn, InsuranceOrg insuranceOrg) throws SQLException {
		String sql = "INSERT INTO Insurance_Orgs(Inn, Ogrn, Name, Address) VALUES (?,?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, insuranceOrg.getInn());
		pstm.setLong(2, insuranceOrg.getOgrn());
		pstm.setString(3, insuranceOrg.getName());
		pstm.setString(4, insuranceOrg.getAddress());
		pstm.executeUpdate();
	}

	public static void deleteInsuranceOrg(Connection conn, long inn) throws SQLException {
		String sql = "DELETE FROM Insurance_Orgs WHERE Inn= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, inn);
		pstm.executeUpdate();
	}

}
