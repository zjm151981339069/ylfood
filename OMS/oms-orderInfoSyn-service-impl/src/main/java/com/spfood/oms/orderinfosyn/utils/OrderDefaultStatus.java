package com.spfood.oms.orderinfosyn.utils;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum OrderDefaultStatus {

	UNPAIED_STATUS(1,"待付款"),
	HASPAIED_STATUS(2,"已付款"),
	FORSORTING_STATUS(3,"待发货"),
	PACKAGED_STATUS(4,"已发货"),
	WAITSITE_STATUS(5,"待自提"),
	FINISHED_STATUS(6,"已完成"),
	THROWAWAY_STATUS(7,"丢弃"),
	CANCEL_STATUS(0,"已取消");
	
	private Integer value;
	
	private String name;
	
	private OrderDefaultStatus(Integer value,String name){
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
}
