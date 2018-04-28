package com.spfood.oms.order.impl;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.exception.BizException;
import com.spfood.kernel.utils.MessageHelper;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.order.manager.OrderCreateManager;
import com.spfood.oms.order.manager.OrderManager;
import com.spfood.oms.order.manager.OrderQueryManager;
import com.spfood.oms.orderpay.intf.OrderPayByACPService;
import com.spfood.oms.orderpay.intf.OrderPayByAliPayService;
import com.spfood.oms.orderpay.intf.OrderPayByWeiChatService;


/*
 * @Author:Isidore Han
 * @Date:2016/12
 */
@Service
public class OrderManagerServiceImpl implements OrderManagerService  {
	private static final Logger logger = Logger.getLogger(OrderManagerServiceImpl.class);

	@Autowired
	private OrderManager orderManager;
	@Autowired
	private OrderCreateManager orderCreateManager;
	@Autowired
	private OrderQueryManager orderQueryManager;
	@Autowired
	private OrderPayByACPService orderPayByACPService;
	@Autowired
	private OrderPayByAliPayService orderPayByAliPayService;
	@Autowired
	private OrderPayByWeiChatService orderPayByWeiChatService;

	@Override
	public Order getOrderDetail(String orderNo) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderDetail"
					+ MessageHelper.getMessage("parameter") + orderNo);
			Order queryOrderDetail = orderQueryManager.queryOrderDetail(orderNo);
			logger.info(MessageHelper.getMessage("return")+queryOrderDetail);
			return queryOrderDetail;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderDetail", e,orderNo);
		}
	}

	@Override
	public List<Order> getOrderDetailList(List<String> orderNoList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderDetailList"
					+ MessageHelper.getMessage("parameter") + orderNoList);
			List<Order> queryOrderDetailList = orderQueryManager.queryOrderDetailList(orderNoList);
			logger.info(MessageHelper.getMessage("return")+queryOrderDetailList);
			return queryOrderDetailList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderDetailList", e,orderNoList);
		}
	}
	
	@Override
	public Order getOrderByOrderNo(String orderNo){
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderByOrderNo"
					+ MessageHelper.getMessage("parameter") + orderNo);
			Order queryOrderByOrderNo = orderQueryManager.queryOrderByOrderNo(orderNo);
			logger.info(MessageHelper.getMessage("return")+queryOrderByOrderNo);
			return queryOrderByOrderNo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderByOrderNo", e,orderNo);
		}
	}
	@Override
	public List<Order> getOrderListByOrderNoList(List<String> orderNos){
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderListByOrderNoList"
					+ MessageHelper.getMessage("parameter") + orderNos);
			List<Order> queryOrderListByOrderNoList = orderQueryManager.queryOrderListByOrderNoList(orderNos);
			logger.info(MessageHelper.getMessage("return")+queryOrderListByOrderNoList);
			return queryOrderListByOrderNoList;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderListByOrderNoList", e,orderNos);
		}
	}
	
	@Override
	public boolean cancelOrder(String orderNo,String operator,String operatorCode){
		try {
			logger.info(MessageHelper.getMessage("function") + "cancelOrder"
					+ MessageHelper.getMessage("parameter") + orderNo + ","
					+ operator + "," + operatorCode);
			boolean abondonOrder = orderManager.abondonOrder(orderNo, operator, operatorCode);
			logger.info(MessageHelper.getMessage("return")+abondonOrder);
			return abondonOrder;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.cancelOrder", e,new Object[]{Arrays.toString(new Object[]{orderNo,operator,operatorCode})});
		}
	}
	@Override
	public PageInfo<Order> getOrderByParas(OrderSearchCriteria orderSearchCriteria) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderByParas"
					+ MessageHelper.getMessage("parameter") + orderSearchCriteria);
			PageInfo<Order> queryOrderByParas = orderQueryManager.queryOrderByParas(orderSearchCriteria);
			logger.info(MessageHelper.getMessage("return")+queryOrderByParas);
			return queryOrderByParas;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderByParas", e,orderSearchCriteria);
		}
	}
	@Override
	public String addOrUpdateSign(String orderNo, String sign,String username,String usernameCode) {
		try {
			logger.info(MessageHelper.getMessage("function") + "addOrUpdateSign"
					+ MessageHelper.getMessage("parameter") + orderNo + ","
					+ sign + "," + username + "," + usernameCode);
			String setSign = orderManager.setSign(orderNo,sign,username,usernameCode);
			logger.info(MessageHelper.getMessage("return")+setSign);
			return setSign;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageHelper.getMessage("error");
		}
	}

	@Override
	public String setDeliverTime(String orderNo, Date deliverTime,String username,String usernameCode) {
		try {
			logger.info(MessageHelper.getMessage("function") + "setDeliverTime"
					+ MessageHelper.getMessage("parameter") + orderNo + ","
					+ deliverTime + "," + username + "," + usernameCode);
			String updateDeliverTime = orderManager.updateDeliverTime(orderNo,deliverTime,username,usernameCode);
			logger.info(MessageHelper.getMessage("return")+updateDeliverTime);
			return updateDeliverTime;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageHelper.getMessage("error");
		}
	}
	

	@Override
	public boolean deleteOrderTempByOrderNo(String orderNo) {
		try {
			logger.info(MessageHelper.getMessage("function") + "deleteOrderTempByOrderNo"
					+ MessageHelper.getMessage("parameter") + orderNo);
			return orderManager.deleteOrderTempByOrderNo(orderNo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.deleteOrderTempByOrderNo", e,orderNo);
		}
	}
	
	@Override
	public void deleteOrderTempByOrderNo(List<String> orderNoList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "deleteOrderTempByOrderNo"
					+ MessageHelper.getMessage("parameter") + orderNoList);
			orderManager.deleteOrderTempByOrderNo(orderNoList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.deleteOrderTempByOrderNo", e,orderNoList);
		}
	}

	@Override
	public List<OrderTemp> getOrderTempByStatus(int status) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderTempByStatus"
					+ MessageHelper.getMessage("parameter") + status);
			List<OrderTemp> orderTempByStatus = orderQueryManager.getOrderTempByStatus(status);
			logger.info(MessageHelper.getMessage("return")+orderTempByStatus);
			return orderTempByStatus;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderTempByStatus", e,status);
		}
	}

	@Override
	public boolean updateOrderVerification(String orderNo, String verificationCode) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderVerification"
					+ MessageHelper.getMessage("parameter") + orderNo + "," + verificationCode);
			boolean updateOrderVerification = orderManager.updateOrderVerification(orderNo,verificationCode);
			logger.info(MessageHelper.getMessage("return")+updateOrderVerification);
			return updateOrderVerification;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderVerification", e,new Object[]{Arrays.toString(new Object[]{orderNo,verificationCode})});
		}
	}
	@Override
	public boolean updateOrderTempStatusByOrderNo(String orderNo, int status) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderTempStatusByOrderNo"
					+ MessageHelper.getMessage("parameter") + orderNo + "," + status);
			boolean updateOrderTempStatusByOrderNo = orderManager.updateOrderTempStatusByOrderNo(orderNo,status);
			logger.info(MessageHelper.getMessage("return")+updateOrderTempStatusByOrderNo);
			return updateOrderTempStatusByOrderNo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderTempStatusByOrderNo", e,new Object[]{Arrays.toString(new Object[]{orderNo,status})});
		}
	}
	
	@Override
	public OrderPay getOrderPayByOrderNo(String orderNo) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderPayByOrderNo"
					+ MessageHelper.getMessage("parameter") + orderNo);
			OrderPay orderPayByOrderNo = orderQueryManager.getOrderPayByOrderNo(orderNo);
			logger.info(MessageHelper.getMessage("return")+orderPayByOrderNo);
			return orderPayByOrderNo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderPayByOrderNo", e,orderNo);
		}
	}
	
	@Override
	public List<OrderPay> getOrderPayByOrderNos(List<String> orderNoList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderPayByOrderNos"
					+ MessageHelper.getMessage("parameter") + orderNoList);
			List<OrderPay> orderPayByOrderNos = orderQueryManager.getOrderPayByOrderNos(orderNoList);
			logger.info(MessageHelper.getMessage("return")+orderPayByOrderNos);
			return orderPayByOrderNos;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderPayByOrderNos", e,orderNoList);
		}
	}
	@Override
	public boolean updateOrderBill(OrderBill orderBill) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderBill"
					+ MessageHelper.getMessage("parameter") + orderBill);
			boolean updateOrderBill = orderManager.updateOrderBill(orderBill);
			logger.info(MessageHelper.getMessage("return")+updateOrderBill);
			return updateOrderBill;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderBill", e,orderBill);
		}
	}
	@Override
	public List<String> getOrderTempOrderNosByStatus(int status) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderTempOrderNosByStatus"
					+ MessageHelper.getMessage("parameter") + status);
			List<String> orderTempOrderNosByStatus = orderQueryManager.getOrderTempOrderNosByStatus(status);
			logger.info(MessageHelper.getMessage("return")+orderTempOrderNosByStatus);
			return orderTempOrderNosByStatus;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderTempOrderNosByStatus", e,status);
		}
	}
	@Override
	public Long getOrderTempCountByStatus(int status) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderTempCountByStatus"
					+ MessageHelper.getMessage("parameter") + status);
			Long orderTempCountByStatus = orderQueryManager.getOrderTempCountByStatus(status);
			logger.info(MessageHelper.getMessage("return")+orderTempCountByStatus);
			return orderTempCountByStatus;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderTempCountByStatus", e,status);
		}
	}

	@Override
	public boolean updateOrderPay(OrderPay orderPay) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderPay"
					+ MessageHelper.getMessage("parameter") + orderPay);
			boolean updateOrderPay = orderManager.updateOrderPay(orderPay);
			logger.info(MessageHelper.getMessage("return")+updateOrderPay);
			return updateOrderPay;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderPay", e,orderPay);
		}
	}

	@Override
	public void updateOrderStatus(List<Order> orderList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderStatus"
					+ MessageHelper.getMessage("parameter") + orderList);
			orderManager.updateOrderStatus(orderList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderStatus", e,orderList);
		}
	}
	
	@Override
	public boolean updateOrderStatus(Order order) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderStatus"
					+ MessageHelper.getMessage("parameter") + order);
			boolean updateOrderStatus = orderManager.updateOrderStatus(order);
			logger.info(MessageHelper.getMessage("return")+updateOrderStatus);
			return updateOrderStatus;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderStatus", e,order);
		}
	}

	@Override
	public boolean updateOrderTempStatus(List<OrderTemp> orderTempList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderTempStatus"
					+ MessageHelper.getMessage("parameter") + orderTempList);
			boolean updateOrderTempStatus = orderManager.updateOrderTempStatus(orderTempList);
			logger.info(MessageHelper.getMessage("return")+updateOrderTempStatus);
			return updateOrderTempStatus;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderTempStatus", e,orderTempList);
		}
	}

	@Override
	public boolean updateOrderPayIsSendFms(String orderNo) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderPayIsSendFms"
					+ MessageHelper.getMessage("parameter") + orderNo);
			boolean updateOrderPayIsSendFms = orderManager.updateOrderPayIsSendFms(orderNo);
			logger.info(MessageHelper.getMessage("return")+updateOrderPayIsSendFms);
			return updateOrderPayIsSendFms;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderPayIsSendFms", e,orderNo);
		}
	}
	
	@Override
	public void updateOrderPayIsSendFms(List<String> orderNoList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "updateOrderPayIsSendFms"
					+ MessageHelper.getMessage("parameter") + orderNoList);
			orderManager.updateOrderPayIsSendFms(orderNoList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.updateOrderPayIsSendFms", e,orderNoList);
		}
	}
	
	@Override
	public Order getOrderAndCommodity(String orderNo) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderAndCommodity"
					+ MessageHelper.getMessage("parameter") + orderNo);
			Order orderAndCommodity = orderManager.getOrderAndCommodity(orderNo);
			logger.info(MessageHelper.getMessage("return")+orderAndCommodity);
			return orderAndCommodity;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderAndCommodity", e,orderNo);
		}
	}
	
	@Override
	public List<Order> getOrderAndCommodity(List<String> orderNoList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "getOrderAndCommodity"
					+ MessageHelper.getMessage("parameter") + orderNoList);
			List<Order> orderAndCommodity = orderManager.getOrderAndCommodity(orderNoList);
			logger.info(MessageHelper.getMessage("return")+orderAndCommodity);
			return orderAndCommodity;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderAndCommodity", e,orderNoList);
		}
	}
	
	@Override
	public void cancelOrderQuartz() {
		try {
			logger.info(MessageHelper.getMessage("function") + "cancelOrderQuartz");
			orderManager.cancelOrderQuartz();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.cancelOrderQuartz", e);
		}
	}

	
	/**
	 * 查询要同步到FMS的订单支付信息(查询订单表中订单状态为3已支付状态的数据) z20170329
	 */
	@Override
	public List<OrderPay> getUnSynOrderPayInfo() {
		
		return orderQueryManager.getUnSynOrderPayInfo();
	}


}
