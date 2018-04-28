package com.spfood.oms.order.utils;

public enum OrderStatus {
	
	UNPAIED(1,"待付款"),
	HASPAIED(2,"已付款"),
	UNDELIVERED(3,"待发货"),
	DELIVERED(4,"已发货"),
	WAITGOODS(5,"待提货"),
	FINISHED(6,"已完成"),
	CANCEL(0,"已取消"),
	DISCARDED(7,"已丢弃");
	private int value;
	private String name;
	private OrderStatus(int value,String name){
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
