package com.itwill.cafe.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.cafe.model.OrderCheck;

import oracle.jdbc.OracleDriver;

import static com.itwill.cafe.OracleJdbc.*;
import static com.itwill.cafe.model.OrderCheck.CheckEntity.*;

public class OrderCheckDao {
	
	private static OrderCheckDao instance = null;
	
	private OrderCheckDao() {
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static OrderCheckDao getInstance() {
		if (instance == null) {
			instance = new OrderCheckDao();
		}
		
		return instance;
	}
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();		
		}
	}
	
	private void closeResources(Connection conn, Statement stmt) {
		closeResources(conn, stmt, null);
	}
	
	private OrderCheck makeOrderCheckFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt(COL_ID);
		LocalDateTime orderTime = rs.getTimestamp(COL_ORDER_TIME).toLocalDateTime();
		String orderBeverages = rs.getString(COL_ORDER_BEVERAGES);
		String orderPayment = rs.getString(COL_ORDER_PAYMENT);
		String orderPrice = rs.getString(COL_ORDER_PRICE);
		
		OrderCheck check = new OrderCheck(id, orderTime, orderBeverages, orderPayment, orderPrice);
		
		return check;
	}
	
	
	
// search()
	// 날짜
	private static final String SQL_SELECT_BY_TIME = String.format(
			"select * from %s where lower(%s) like ? order by %s desc", 
			TBL_ORDER_CHECK, COL_ORDER_TIME, COL_ORDER_TIME);
		
	// 음료
	private static final String SQL_SELECT_BY_BEVERAGE = String.format(
			"select * from %s where lower(%s) like ? order by %s desc", 
			TBL_ORDER_CHECK, COL_ORDER_BEVERAGES, COL_ORDER_TIME);
	
	// 결제방법
	private static final String SQL_SELECT_BY_PAY = String.format(
			"select * from %s where lower(%s) like ? order by %s desc", 
			TBL_ORDER_CHECK, COL_ORDER_PAYMENT, COL_ORDER_TIME);
	
	public List<OrderCheck> search(int type, String keyword) {
		List<OrderCheck> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String searchKeyword = "%" + keyword.toLowerCase() + "%";
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			switch (type) {
			case 0:
				stmt = conn.prepareStatement(SQL_SELECT_BY_TIME);
				stmt.setString(1, searchKeyword);
				break;
			case 1:
				stmt = conn.prepareStatement(SQL_SELECT_BY_BEVERAGE);
				stmt.setString(1, searchKeyword);
				break;
			case 2:
				stmt = conn.prepareStatement(SQL_SELECT_BY_PAY);
				stmt.setString(1, searchKeyword);
				break;
		}
		
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderCheck check = makeOrderCheckFromResultSet(rs);
				result.add(check);
			}
		
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				closeResources(conn, stmt, rs);
		} 
		
		return result;
	}
	
	
	
// read()
	private static final String SQL_SELECT_ORDER_CHECK = String.format(
			"select * from %s order by %s desc",
			TBL_ORDER_CHECK, COL_ORDER_TIME);
	
	public List<OrderCheck> read() {
		List<OrderCheck> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_ORDER_CHECK);
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderCheck check = makeOrderCheckFromResultSet(rs);
				result.add(check);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return result;
	}
	
// save
	// insert into order_check (orderBeverage, orderPayment, orderPrice) values (?, ?, ?)
	private static final String SQL_INSERT = String.format(
			"insert into %s (%s, %s, %s) values (?, ?, ?)", 
			TBL_ORDER_CHECK, COL_ORDER_BEVERAGES, COL_ORDER_PAYMENT, COL_ORDER_PRICE);
	
	public int save(OrderCheck check) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, check.getOrderBeverages());
			stmt.setString(2, check.getOrderPayment());
			stmt.setString(3, check.getOrderPrice());
			result = stmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		
		return result;
	}


}
