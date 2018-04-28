package com.spfood.oms.order.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderExchangeCommodityDao;
import com.spfood.oms.order.dao.OrderExchangeDao;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;
import com.spfood.oms.order.manager.OrderExchangeQueryManager;
import com.spfood.oms.order.utils.Constant;
import com.spfood.oms.order.utils.MybatisList;

@Service
@Transactional
public class OrderExchangeQueryManagerImpl implements OrderExchangeQueryManager {

	@Autowired
	private OrderExchangeDao orderExchangeDao;
	@Autowired
	private OrderExchangeCommodityDao orderExchangeCommodityDao;
	
	/**
	 * 列表展示
	 */
	@Override
	public PageInfo<OrderExchange> queryOrderExchangeByParas(OrderExchangeCriteria orderExchangeCriteria) {

		PageInfo<OrderExchange> pageInfo = this.pageJudge(orderExchangeCriteria);
		pageInfo = orderExchangeDao.selectListByPage(orderExchangeCriteria, MybatisList.GET_EXCHANGELIST_BYPARAM, pageInfo);
		return pageInfo;
	}
	
	/**
	 * 用户中心列表展示
	 */
	@Override
	public PageInfo<OrderExchange> getExchangeByCriteria(OrderExchangeCriteria orderExchangeCriteria) {
		
		
		if (orderExchangeCriteria.getUsernameCode() == null) {
			throw new BizException("orderExchangeCriteria.usernameCode is null", orderExchangeCriteria);
		}
		if ("".equals(orderExchangeCriteria.getSearch())) {
			orderExchangeCriteria.setSearch(null);
		}
		PageInfo<OrderExchange> orderPageInfo = this.pageJudge(orderExchangeCriteria);
		//查询换货单信息
		orderPageInfo= orderExchangeDao.selectListByPage(orderExchangeCriteria,  MybatisList.GET_GU_EXCHANGELIST_BYPARAM,orderPageInfo);
		List<OrderExchange> exchangeList = orderPageInfo.getResult();
		if (exchangeList == null || exchangeList.isEmpty()) {
			throw new BizException("usernameCode does not exist in exchangeList is empty ", exchangeList);
		}
		//根据订单号查到商品进行封装
		List<String> exCodes = new ArrayList<String>();
		Map<String, OrderExchange> map = new HashMap<String, OrderExchange>();
		for (OrderExchange orderExchange : exchangeList) {
			orderExchange.getExchangeCommodityLists().clear();
			exCodes.add(orderExchange.getExCode());
			map.put(orderExchange.getExCode(), orderExchange);
		}
		
		List<OrderExchangeCommodity> exchangeCommoditiesByExCodes = this.getOrderExchangeByOrderExCodes(exCodes);
		for (OrderExchangeCommodity orderExchangeCommodity : exchangeCommoditiesByExCodes) {
			if (map.containsKey(orderExchangeCommodity.getExCode())) {
				OrderExchange orderExchange = map.get(orderExchangeCommodity.getExCode());
				orderExchange.getExchangeCommodityLists().add(orderExchangeCommodity);
			}
		}

		return orderPageInfo;
	}

	@Override
	public List<OrderExchangeCommodity> getOrderExchangeByOrderExCode(
			String[] exCodes) {
		String exCode = exCodes[0];
		return orderExchangeCommodityDao.getOrderExchangeListByExCode(exCode);
	}

	@Override
	public List<OrderExchangeCommodity> getOrderExchangeByOrderExCodes(
			List<String> exCodes) {
		return orderExchangeCommodityDao.getOrderExchangeListByExCodes(exCodes);
	}
	
	//页码判断
	public PageInfo<OrderExchange> pageJudge(OrderExchangeCriteria orderExchangeCriteria){
		if (orderExchangeCriteria.getPageNum() == null) {
			orderExchangeCriteria.setPageNum(Constant.DEFAULT_PAGE_NUM);
		}
		if (orderExchangeCriteria.getPageSize() == null) {
			orderExchangeCriteria.setPageSize(Constant.DEFAULT_PAGE_SIZE);
		}
		if (orderExchangeCriteria.getSort() == null) {
			orderExchangeCriteria.setSort(Constant.DEFAULT_APPTIME_SORT);
		}
		return new PageInfo<OrderExchange>(orderExchangeCriteria.getPageNum(),orderExchangeCriteria.getPageSize());
	}

	
}
