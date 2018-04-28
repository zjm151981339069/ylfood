package com.spfood.oms.order.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;


/*
 * @Author:Isidore Han
 * @Date:2016/12
 */

public interface OrderManager {
	
	public boolean abondonOrder(String orderNo,String operator,String peratorCode);
	
	public String setSign(String orderNo, String sign,String user,String usernameCode);

	public String updateDeliverTime(String orderNo, Date deliverTime,String user,String usernameCode);

	public boolean deleteOrderTempByOrderNo(String orderNo);
	
	public void deleteOrderTempByOrderNo(List<String> orderNoList);

	public boolean updateOrderVerification(String orderNo, String verificationCode);

	public boolean updateOrderTempStatusByOrderNo(String orderNo, Integer status);

	public boolean updateOrderBill(OrderBill orderBill);

	public void updateOrderStatus(List<Order> orderList);
	
	public boolean updateOrderStatus(Order order);

	public boolean updateOrderPay(OrderPay orderPay);

	public boolean updateOrderTempStatus(List<OrderTemp> orderTempList);
	
	public boolean updateOrderPayIsSendFms(String orderNo);
	
	public void updateOrderPayIsSendFms(List<String> orderNoList);
	
	public Order getOrderAndCommodity(String orderNo);
	
	public List<Order> getOrderAndCommodity(List<String> orderNoList);

	public void cancelOrderQuartz();


}
