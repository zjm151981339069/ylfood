package com.spfood.oms.order.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderPayDao;
import com.spfood.oms.order.dao.OrderTempDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.order.manager.OrderQueryManager;
import com.spfood.oms.order.utils.Constant;
import com.spfood.oms.order.utils.MybatisList;


/**
* @author huangcj
* @date 2017年1月3日
* <p>Description: 订单管理查询实现类</p>
*/
@Service
public class OrderQueryManagerImpl implements OrderQueryManager{
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private OrderTempDao orderTempDao;


	@Override
	public Order queryOrderDetail(String orderNo) {
		if (!StringUtils.hasText(orderNo)) {
			return null;
		}
		Order order = orderDao.selectOrderDetailByOrderNo(orderNo);
		if (order.getOrderPay() != null &&order.getOrderPay().getIsPay() == Constant.ORDERPAY_NOT_PAY) {
			order.setOrderPay(null);//订单未付款不展示
		}
		return order;
	}
	
	@Override
	public List<Order> queryOrderDetailList(List<String> orderNoList) {
		if (orderNoList != null && orderNoList.size() > 0) {
			return orderDao.selectOrderDetailListByOrderNo(orderNoList);
		}
		return null;
	}

	@Override
	public Order queryOrderByOrderNo(String orderNo) {
		if(!StringUtils.hasText(orderNo)){
			return null;
		}
		return orderDao.selectOne(new OrderSearchCriteria(orderNo));
	}

	@Override
	public List<Order> queryOrderListByOrderNoList(List<String> orderNos){
		if (orderNos != null && orderNos.size() > 0) {
			return orderDao.selectByOrderNos(orderNos);
		}
		return null;
	}

	@Override
	public PageInfo<Order> queryOrderByParas(OrderSearchCriteria orderSearchCriteria) {
		if (orderSearchCriteria.getPageNum() == null) {
			orderSearchCriteria.setPageNum(Constant.DEFAULT_PAGE_NUM);
		}
		if (orderSearchCriteria.getPageSize() == null) {
			orderSearchCriteria.setPageSize(Constant.DEFAULT_PAGE_SIZE);
		}
		if (orderSearchCriteria.getSort() == null) {
			orderSearchCriteria.setSort(Constant.DEFAULT_SORT);
		}
		PageInfo<Order> pageInfo = new PageInfo<Order>(orderSearchCriteria.getPageNum(), orderSearchCriteria.getPageSize());
		orderDao.selectListByPage(orderSearchCriteria, MybatisList.SELECT_ORDERLIST_PAGE, pageInfo);
		return pageInfo;
	}

	@Override
	public List<OrderTemp> getOrderTempByStatus(Integer status) {
		if (status == null) {
			return null;
		}
		OrderTemp orderTemp = new OrderTemp();
		orderTemp.setStatus(status);
		return orderTempDao.selectList(orderTemp);
	}

	@Override
	public OrderPay getOrderPayByOrderNo(String orderNo) {
		if (!StringUtils.hasText(orderNo)) {
			return null;
		}
		return orderPayDao.selectOne(new OrderPay(orderNo));
	}

	@Override
	public List<OrderPay> getOrderPayByOrderNos(List<String> orderNoList) {
		if (orderNoList == null || orderNoList.size() == 0) {
			return null;
		}
		return orderPayDao.selectByOrderNos(orderNoList);
	}

	@Override
	public List<String> getOrderTempOrderNosByStatus(Integer status) {
		if (status == null) {
			return null;
		}
		OrderTemp orderTemp = new OrderTemp();
		orderTemp.setStatus(status);
		List<OrderTemp> selectList = orderTempDao.selectList(orderTemp);
		List<String> orderNoList = new ArrayList<String>();
		for (OrderTemp temp : selectList) {
			orderNoList.add(temp.getOrderNo());
		}
		return orderNoList;
	}

	@Override
	public Long getOrderTempCountByStatus(Integer status) {
		if (status == null) {
			return 0L;
		}
		OrderTemp orderTemp = new OrderTemp();
		orderTemp.setStatus(status);
		return orderTempDao.selectCount(orderTemp);
	}

	@Override
	public OrderPay queryOrderPayByOrderNo(String orderNo) {
		if (!StringUtils.hasText(orderNo)) {
			return null;
		}
		return orderPayDao.selectOne(new OrderPay(orderNo));
	}

	/**
	 * 查询要同步到FMS的订单支付信息(查询订单表中订单状态为3的数据) z20170329
	 */
	@Override
	public List<OrderPay> getUnSynOrderPayInfo() {
		
		try {
			
			return orderPayDao.getUnSynOrderPayInfo();
			
		} catch (Exception e) {
			
			throw new BizException("oms.service.getUnSynOrderPayInfo", e);
		}
	}
}
