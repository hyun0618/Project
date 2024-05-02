package com.itwill.cafe.model;

import java.time.LocalDateTime;

public class OrderHistory {
	
	public static final class HistoryEntity {
		
		public static final String TBL_ORDERS = "ORDERS";
		
		public static final String COL_ID = "id";
		public static final String COL_ORDER_TIME = "order_time";
		public static final String COL_BEVERAGE = "beverage";
		public static final String COL_BEVERAGE_OPTION = "beverage_option";
		
	}
	
	private int id;
	private LocalDateTime orderTime;
	private String beverage;
	private String beverageOption;
	
	public OrderHistory() {}
	
	public OrderHistory(int id, LocalDateTime orderTime, String beverage, String beverageOption) {
		this.id = id;
		this.orderTime = orderTime;
		this.beverage = beverage;
		this.beverageOption = beverageOption;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public String getBeverage() {
		return beverage;
	}

	public void setBeverage(String beverage) {
		this.beverage = beverage;
	}

	public String getBeverageOption() {
		return beverageOption;
	}

	public void setBeverageOption(String beverageOption) {
		this.beverageOption = beverageOption;
	}

}
