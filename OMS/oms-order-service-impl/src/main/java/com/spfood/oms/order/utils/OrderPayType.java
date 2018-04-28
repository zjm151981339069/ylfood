package com.spfood.oms.order.utils;

public enum OrderPayType {
	
	WECHAT(0,"微信"),
	ALIPAY(1,"支付宝"),
	UNIONPAY(2,"银联"),
	OTHER(3,"其他");
	
	private int value;
	private String name;
	
	private OrderPayType(int value,String name){
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
