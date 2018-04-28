package com.spfood.oms.order.utils;

public enum OrderType {
	ORDINARY(0,"普通订单"),
	CHANGE(1,"换或订单"),
	OTHER(2,"其他订单");
	
	private int value;
	private String name;
	private OrderType(int value, String name) {
		this.value = value;
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
}
