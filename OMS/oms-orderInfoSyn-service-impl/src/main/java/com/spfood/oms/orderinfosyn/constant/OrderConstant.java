package com.spfood.oms.orderinfosyn.constant;


/**
 * 
* Des:常量类
*
 */
public class OrderConstant {
	
	//商品最大数量
	public final static int AMOUNT = 99;
	
	//最大数量查询数
	public static int AMOUNT2 = 2;
	
	//已取消订单状态
	public final static int CANCEL_STATUS = 0;
	
	//待付款订单状态
	public final static int PENDING_PAYMENT_STATUS = 1;
	
	
	//待发货订单状态
	public  static int PENGDING_DELIVER_STATUS = 3;
	
	//订单定时取消时间
	public final static Long CANCEL_ORDER_TIME = 1*60*60*1000L;
	
	//发货订单查询状态
	public final static Integer[] DELIVERORDER_STATUS={0,3,4,5};
	
	//FMS系统mq的消息地址常量
	public static String  FMS_DESTINATION="FMS";
	
	//已到账状态
	public static int  ORDERPAY_ISARRIVED=0;
	
	//查询从0开始查询
	public static int INITIAL_ROW = 0;
	
}
