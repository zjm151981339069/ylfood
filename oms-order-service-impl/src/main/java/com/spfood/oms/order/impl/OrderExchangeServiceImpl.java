package com.spfood.oms.order.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.intf.OrderExchangeService;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;
import com.spfood.oms.order.manager.OrderExchangeCreateManager;
import com.spfood.oms.order.manager.OrderExchangeManage;
import com.spfood.oms.order.manager.OrderExchangeQueryManager;
import com.spfood.oms.order.utils.OrderExchangeStatus;

@Service
public class OrderExchangeServiceImpl implements OrderExchangeService {
	
	private static final Logger logger = Logger.getLogger(OrderExchangeServiceImpl.class);

	@Autowired
	private OrderExchangeQueryManager orderExchangeQueryManager;
	@Autowired
	private OrderExchangeManage orderExchangeManage;
	@Autowired
	private OrderExchangeCreateManager orderExchangeCreateManager;

	@Override
	public PageInfo<OrderExchange> getOrderExchangeByParas(OrderExchangeCriteria orderExchangeCriteria) {
		
		try {
			return orderExchangeQueryManager.queryOrderExchangeByParas(orderExchangeCriteria);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderExchangeByParas", e, orderExchangeCriteria);
		}
		
	}

	//查询换货单详情
	@Override
	public OrderExchange selectOrderExchangeByExid(Long exId) {
		try {
			return orderExchangeManage.selectOrderExchangeByExId(exId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.selectOrderExchangeByExid", e, exId);
		}
		
	}

	//审核通过操作
	@Override
	public boolean passOrderExchangeByExid(OrderExchange orderExchange) {
		try {
			
			orderExchange.setAuditstatus(OrderExchangeStatus.PASS_AUDITSTATUS.getValue());
			if ( orderExchangeManage.passNewOrderExchange(orderExchange)) {
				//修改订单状态
				orderExchangeManage.updateOrderExchangeByExId(orderExchange);
				return true;
			}
			return false;
		} catch (UnexpectedRollbackException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//审核不通过操作
	@Override
	public boolean cancelOrderExchangeByExid(OrderExchange orderExchange) {
	    try {
			orderExchange.setAuditstatus(OrderExchangeStatus.NO_PASS_AUDITSTATUS.getValue());
			orderExchangeManage.cancelOrderExchangeByExid(orderExchange);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	//用户中心
	@Override
	public PageInfo<OrderExchange> getOrderExchangeListByCriteria(OrderExchangeCriteria orderExchangeCriteria) {
		
		try {
			PageInfo<OrderExchange> pageOrder = orderExchangeQueryManager.getExchangeByCriteria(orderExchangeCriteria);
			return pageOrder;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.getOrderExchangeListByCriteria", e, orderExchangeCriteria);
		}
		
	}

	/**
	 * 换货单生成
	 */
	@Override
	public boolean OrderExchangeCreate(String orderNo,OrderExchange orderExchange) {
		
		try {
			if (orderExchangeCreateManager.createOrderExchange(orderNo, orderExchange)) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.OrderExchangeCreate", e, orderExchange);
		}
		
		return false;
	}

}
