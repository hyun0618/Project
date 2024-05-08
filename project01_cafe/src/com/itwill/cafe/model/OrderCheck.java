package com.itwill.cafe.model;

import java.time.LocalDateTime;

public class OrderCheck {
	
	public static final class CheckEntity {
	
		public static final String TBL_ORDER_CHECK = "order_check";
		
		public static final String COL_ID = "id";
		public static final String COL_ORDER_TIME = "order_time";
		public static final String COL_ORDER_BEVERAGES = "order_beverages";
		public static final String COL_ORDER_PAYMENT = "order_payment";
		public static final String COL_ORDER_PRICE = "order_price";
		
	}
	
	private int id;
	private LocalDateTime orderTime;
	private String orderBeverages;
	private String orderPayment;
	private String orderPrice;
	
	public OrderCheck() {}
	
	public OrderCheck(int id, LocalDateTime orderTime, 
			String orderBeverages, String orderPayment, String orderPrice) {
		this.id = id;
		this.orderTime = orderTime;
		this.orderBeverages = orderBeverages;
		this.orderPayment = orderPayment;
		this.orderPrice = orderPrice;
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

	public String getOrderBeverages() {
		return orderBeverages;
	}

	public void setOrderBeverage(String orderBeverages) {
		this.orderBeverages = orderBeverages;
	}

	public String getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	
}

