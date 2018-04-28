package com.spfood.oms.orderRetrieve.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.manager.impl.BaseManagerImpl;
import com.spfood.oms.order.dao.OrderCommodityDao;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.order.utils.MybatisList;
import com.spfood.oms.orderRetrieve.manager.OrderRetrieveManager;
import com.spfood.oms.orderRetrieve.utils.Constant;

/**
 * 
 * @author huangcj
 * @date 2017年1月16日 Des:订单检索业务层实现类
 *
 */
@Service
public class OrderRetrieveManagerImpl extends BaseManagerImpl<Order> implements
		OrderRetrieveManager {

	@Autowired
	private OrderDao orderDao;


	@Autowired
	private OrderCommodityDao orderCommodityDao;
	
	private final static Logger logger = Logger.getLogger(OrderRetrieveManagerImpl.class); 

	@Override
	protected BaseDao<Order> getBaseDao() {
		return orderDao;
	}

	@Override
	public PageInfo<Order> findOrderPageByParam(OrderSearchCriteria orderSearchCriteria) {
		
		if(orderSearchCriteria == null || orderSearchCriteria.getCustomerCode() ==null){
			logger.error("oms.orderRetrieve.manager.impl.findOrderPageByParam error ,CustomerCode is null");
			return null;
		}

		// 设置分页参数
		PageInfo<Order> orderPageInfo = this.initPageInfo(orderSearchCriteria);

		// 查询订单信息
		orderPageInfo = orderDao.selectListByPage(orderSearchCriteria,MybatisList.GETORDERPAGEBYPARAM, orderPageInfo);

		if (orderPageInfo.getResult() != null && orderPageInfo.getResult().size() > 0) {
			List<Order> orderList = orderPageInfo.getResult();
			List<String> orderNos = new ArrayList<String>();
			for (int i = 0; i < orderList.size(); i++) {
				orderNos.add(orderList.get(i).getOrderNo());
			}

			// 查询商品信息
			List<OrderCommodity> orderDetailList = this.findOrderCommodityByOrderNos(orderNos);
			// 合并订单商品
			Map<String, Order> orderMap = new HashMap<String, Order>();
			for (Order order : orderList) {
				order.setOrderCommList(new ArrayList<OrderCommodity>());
				orderMap.put(order.getOrderNo(), order);
			}
			for (OrderCommodity orderCommodity : orderDetailList) {
				if (orderMap.containsKey(orderCommodity.getOrderNo())) {
					Order order = (Order) orderMap.get(orderCommodity.getOrderNo());
					order.getOrderCommList().add(orderCommodity);
				}
			}

		}
		return orderPageInfo;
	}

	@Override
	public Order findOrderDetailByOrderNo(String orderNo) {

		if (StringUtils.isEmpty(orderNo)) {
			logger.error("oms.orderRetrieve.manager.impl.findOrderDetailByOrderNo error ,orderNo is null");
			return null;
		}

		return orderDao.selectOrderDetailByOrderNo(orderNo);
	}

	public List<OrderCommodity> findOrderCommodityByOrderNos(List<String> orderNos) {
		return orderCommodityDao.selectByOrderNoList(orderNos);
	}

	@Override
	public Long findOrderCountByParam(OrderSearchCriteria orderSearchCriteria) {
		if (orderSearchCriteria == null || orderSearchCriteria.getStartDate() == null || orderSearchCriteria.getEndDate() == null) {
			logger.error("oms.orderRetrieve.manager.impl.findOrderCountByParam error ,orderSearchCriteria is null");
			return 0L;
		}
		return orderDao.selectCount(orderSearchCriteria);
	}

	private PageInfo<Order> initPageInfo(OrderSearchCriteria orderSearchCriteria) {
		// 初始分页参数
		if (orderSearchCriteria.getPageNum() == 0 || orderSearchCriteria.getPageNum() == null) {
			orderSearchCriteria.setPageNum(Constant.PageNum);
		}
		if (orderSearchCriteria.getPageSize() == 0 || orderSearchCriteria.getPageSize() == null) {
			orderSearchCriteria.setPageSize(Constant.PageSize);
		}
		return new PageInfo<Order>(orderSearchCriteria.getPageNum(),orderSearchCriteria.getPageSize());
	}

}
