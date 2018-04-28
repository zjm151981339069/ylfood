package com.spfood.oms.order.dao.impl;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderExchangeDao;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;
import com.spfood.oms.order.utils.MybatisList;

@Repository
public class OrderExchangeDaoImpl extends BaseDaoImpl<OrderExchange> implements OrderExchangeDao{

	@Override
	public long selectExchangeCount(OrderExchangeCriteria orderExchangeCriteria) {
		try {
			return sqlSessionTemplate.selectOne(MybatisList.GET_CU_EXCHANGELIST, orderExchangeCriteria);
		} catch (Exception e) {
			throw new BizException("oms.dao.getOrderExchangeListByOrderNos", e,orderExchangeCriteria);
		}
		
	}
	
}
