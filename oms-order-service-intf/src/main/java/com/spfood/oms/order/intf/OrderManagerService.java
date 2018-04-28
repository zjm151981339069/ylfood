package com.spfood.oms.order.intf;


import java.util.Date;
import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;

/*
 * @Author:Isidore Han
 * @Date:2016/12
 */
public interface OrderManagerService {
	
	/**
	 * 查看订单列表
	 * @param orderSearchCriteria 订单查询对象
	 * @return 订单列表信息
	 */
	public PageInfo<Order>	getOrderByParas(OrderSearchCriteria orderSearchCriteria);
	
	/**
	 * 查看订单详情（包括订单中的商品、支付、日志等信息）
	 * @param orderNo 订单编号
	 * @return 订单详情信息
	 */
	public Order getOrderDetail(String orderNo);
	
	/**
	 * 查看订单详情List（包括订单中的商品、支付、日志等信息）
	 * @param orderNo 订单编号
	 * @return 订单详情信息
	 */
	public List<Order> getOrderDetailList(List<String> orderNoList);
	
	/**
	 * 查看订单信息（不包含商品、支付、日志）
	 * @param orderNo 订单编号
	 * @return 订单信息
	 */
	public Order getOrderByOrderNo(String orderNo);
	
	/**
	 * 多个订单编号查询多个订单（不包含商品、支付、日志）
	 * @param orderNoList 订单编号集合
	 * @return 订单集合
	 */
	public List<Order> getOrderListByOrderNoList(List<String> orderNoList);

	/**
	 * 取消订单
	 * @param orderNos 订单编号
	 * @param operator 操作人
	 * @param operatorCode 操作人编号
	 * @return true/false
	 */
	public boolean cancelOrder(String orderNo,String operator,String operatorCode);
	
	/**
	 * 添加或修改标记信息
	 * @param orderNo 订单编号
	 * @param sign 标记的内容
	 * @param oprator 操作人
	 * @param opratorCode 操作人编号
	 * @return 用户提示信息
	 */
	public String addOrUpdateSign(String orderNo, String sign,String oprator,String opratorCode);
	
	/**
	 * 设置配送时间
	 * @param orderNo 订单编号
	 * @param deliverTime 配送时间
	 * @param username	操作人
	 * @param usernameCode	操作人编号
	 * @return 用户提示信息
	 */
	public String setDeliverTime(String orderNo, Date deliverTime,String username,String usernameCode);

	/**
	 * 删除临时订单
	 * @param orderNo 订单编号
	 * @return true/false
	 */
	public boolean deleteOrderTempByOrderNo(String orderNo);
	
	/**
	 * 删除临时订单	
	 * @param orderNo 订单编号
	 */
	public void deleteOrderTempByOrderNo(List<String> orderNoList);

	/**
	 * 查询指定状态的临时订单
	 * @param status 状态
	 * @return 临时订单对象集合
	 */
	public List<OrderTemp> getOrderTempByStatus(int status);
	
	/**
	 * 查询指定状态的临时订单编号
	 * @param status 状态
	 * @return 临时订单编号集合
	 */
	public List<String> getOrderTempOrderNosByStatus(int status);
	
	/**
	 * 查询指定状态的临时订单总数
	 * @param status
	 * @return
	 */
	public Long getOrderTempCountByStatus(int status);
	
	/**
	 * 修改指定临时订单表的状态
	 * @param orderNo 临时订单编号
	 * @param status 修改后的状态
	 * @return true/false
	 */
	public boolean updateOrderTempStatusByOrderNo(String orderNo,int status);
	
	/**
	 * 修改指定订单编号的验证码
	 * @param orderNo	订单编号
	 * @param verificationCode	修改后的验证码
	 * @return true/false
	 */
	public boolean updateOrderVerification(String orderNo,String verificationCode);
	
	/**
	 * 查询支付信息
	 * @param orderNo 订单编号
	 * @return 订单支付对象
	 */
	public OrderPay getOrderPayByOrderNo(String orderNo);
	
	/**
	 * 查询支付信息
	 * @param orderNoList 订单编号集合
	 * @return 订单支付对象集合
	 */
	public List<OrderPay> getOrderPayByOrderNos(List<String> orderNoList);
	
    /**
     * 修改订单发票信息
     * @param orderBill 
	 * @return true/false
     */
    public boolean updateOrderBill(OrderBill orderBill);
    
    /**
     * 修改订单支付
     * @param orderPay
     * @return
     */
    public boolean updateOrderPay(OrderPay orderPay);
    
    /**
     * 批量修改订单状态
     * @param orderList
     * @return
     */
    public void updateOrderStatus(List<Order> orderList);
    
    /**
     * 修改订单状态
     * @param orderList
     * @return
     */
    public boolean updateOrderStatus(Order order);
    
    /**
     * 修改支付为已发送fms
     * @param orderNo 订单编号
	 * @return true/false
     */
    public boolean updateOrderPayIsSendFms(String orderNo);
    
    /**
     * 修改支付为已发送fms
     * @param orderNo 订单编号
     */
    public void updateOrderPayIsSendFms(List<String> orderNoList);

    /**
     * 批量修改临时表状态
     * @param orderList
     * @return
     */
	public boolean updateOrderTempStatus(List<OrderTemp> orderTempList);
	
	/**
	 * 查询 订单和订单关联商品
	 * @param orderNo
	 * @return 
	 */
	public Order getOrderAndCommodity(String orderNo);
	
	/**
	 * 查询订单和订单关联商品
	 * @param orderNoList
	 * @return 
	 */
	public List<Order> getOrderAndCommodity(List<String> orderNoList);
	
	/**
	 * 定时取消订单
	 */
	public void cancelOrderQuartz();
	
	
	

	/**
	 * 查询要同步到FMS的订单支付信息(查询订单表中订单状态为3的数据) z20170329
	 */
	public List<OrderPay> getUnSynOrderPayInfo();


}
