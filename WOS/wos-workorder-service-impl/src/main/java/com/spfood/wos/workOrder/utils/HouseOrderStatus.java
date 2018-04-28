package com.spfood.wos.workOrder.utils;

/**
 * 订单是否分拣状态
 * 
 * @author Administrator
 *
 */
public enum HouseOrderStatus {

	NOT_SORTING(2, "未分拣订单"), 
	HAS_SORTING(1, "已分拣订单"), 
	ABNORMAL_STATUS(0, "分拣失败");
	
	private Integer value;

	private String name;

	private HouseOrderStatus(Integer value, String name) {
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
