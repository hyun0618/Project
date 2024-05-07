package com.itwill.cafe.model;



import java.time.LocalDateTime;

public class OrderHistory {
	
	public static final class HistoryEntity {
		
		public static final String TBL_ORDERS = "ORDERS";
		
		public static final String COL_ID = "id";
		public static final String COL_BEVERAGE = "beverage";
		public static final String COL_BEVERAGE_OPTION = "beverage_option";
		public static final String COL_BEVERAGE_PRICE = "beverage_price";
		
	}
	
	private int id;
	private String beverage;
	private String beverageOption;
	private int beveragePrice;
	
	public OrderHistory() {}
	
	public OrderHistory(int id, String beverage, String beverageOption, int beveragePrice ) {
		this.id = id;
		this.beverage = beverage;
		this.beverageOption = beverageOption;
		this.beveragePrice = beveragePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getBeveragePrice() {
		return beveragePrice;
	}

	public void setBeveragePrice(int beveragePrice) {
		this.beveragePrice = beveragePrice;
	}

}
