package com.itwill.mymedi.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itwill.mymedi.model.Meds;
import com.itwill.mymedi.model.Pharms;

import oracle.jdbc.OracleDriver;

import static com.itwill.mymedi.OracleJdbc.PASSWORD;
import static com.itwill.mymedi.OracleJdbc.URL;
import static com.itwill.mymedi.OracleJdbc.USER;
import static com.itwill.mymedi.model.Meds.Entity.*;

public class MedsDao {
	

	private static MedsDao instance = null; 
	
	private MedsDao() {
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static MedsDao getInstance() {
		if (instance == null) {
			instance = new MedsDao();
		}
		return instance;
	}
	
	public void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		
			try {
				if (rs!= null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private Meds makeMedsFromResultSet(ResultSet rs) throws SQLException {
		String name = rs.getString(COL_MED);
		int stock = rs.getInt(COL_STOCK);
		
		Meds meds = new Meds(name, stock);
		return meds;
	}
	
	// READ	
		private static final String SQL_SELECT_MED = String.format(
				"select * from %s order by %s", 
				TBL_MEDS, COL_MED);
		
		public List<Meds> read() {
			List<Meds> result = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_MED);
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					Meds med = makeMedsFromResultSet(rs);
					result.add(med);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}	
			
			return result;
		}

}
