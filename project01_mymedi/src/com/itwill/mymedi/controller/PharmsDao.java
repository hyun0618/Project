package com.itwill.mymedi.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itwill.mymedi.model.Pharms;

import oracle.jdbc.OracleDriver;

import static com.itwill.mymedi.OracleJdbc.*;
import static com.itwill.mymedi.model.Pharms.Entity.*;

public class PharmsDao {
	
private static PharmsDao instance = null;
	
	private PharmsDao() {
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PharmsDao getInstance() {
		if (instance == null) {
			instance = new PharmsDao();
		}
		
		return instance;
	}
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs ) {	
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();				
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private Pharms makePharmsFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt(COL_ID);
		String name = rs.getString(COL_NAME);
		String address = rs.getString(COL_ADDRESS);
		
		Pharms pharm = new Pharms(id, name, address);
		return pharm;
	}
	
	
	// READ
		private static final String SQL_SELECT_ALL = String.format(
				"select * from %s order by %s",
				TBL_PHARMS, COL_ADDRESS);
		
		/**
		 * DB 테이블 PHARMS에서 모든 레코드를 검색해서 
		 * 주소의 오름차순으로 정렬된 리스트를 반환.
		 * 테이블에 행이 없는 경우에는 빈 리스트를 리턴.
		 * @return Medi를 원소로 갖는 ArrayList.
		 */
		public List<Pharms> read() {
			List<Pharms> result = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_ALL);
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					Pharms pharm = makePharmsFromResultSet(rs);
					result.add(pharm);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}	
			
			return result;
		}
		
	// SEARCH	
		// SQL_SEARCH_BY_NAME
		private static final String SQL_SEARCH_BY_NAME = String.format(
				"select * from %s where lower(%s) like ? order by %s", 
				TBL_PHARMS, COL_NAME, COL_ADDRESS);
		
		// SQL_SEARCH_BY_ADDRESS
		private static final String SQL_SEARCH_BY_ADDRESS = String.format(
				"select * from %s where lower(%s) like ? order by %s", 
				TBL_PHARMS, COL_ADDRESS, COL_ADDRESS);

		public List<Pharms> search(int type, String keyword) {
			List<Pharms> result = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String searchKeyword = "%" + keyword.toLowerCase() + "%";
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				switch (type) {
				case 0:
					stmt = conn.prepareStatement(SQL_SEARCH_BY_ADDRESS);
					stmt.setString(1, searchKeyword);
					break;
				case 1:
					stmt = conn.prepareStatement(SQL_SEARCH_BY_NAME);
					stmt.setString(1, searchKeyword);
					break;
				}
				
				rs = stmt.executeQuery();
				while (rs.next()) {
					Pharms pharm = makePharmsFromResultSet(rs);
					result.add(pharm);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			
			return result;
		}
		
		// SQL_SELECT_BY_ID
		private static final String SQL_SELECT_BY_ID = String.format(
				"select * from %s where %s = ?", 
				TBL_PHARMS, COL_ID);
		
		public Pharms read(int id) {
			Pharms pharm = null;
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				if (rs.next()) {
					pharm = makePharmsFromResultSet(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			
			return pharm;
		}

}
