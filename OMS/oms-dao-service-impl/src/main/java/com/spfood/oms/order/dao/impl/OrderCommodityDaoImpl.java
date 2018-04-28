package com.spfood.oms.order.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderCommodityDao;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.utils.MybatisList;

@Repository
public class OrderCommodityDaoImpl extends BaseDaoImpl<OrderCommodity> implements OrderCommodityDao{

	private final static Logger logger = Logger.getLogger(OrderCommodityDaoImpl.class);
	
	@Override
	public List<OrderCommodity> selectByOrderNoList(List<String> orderNoList) {
		
		try {
			return sqlSessionTemplate.selectList(MybatisList.SELECT_ORDER_COMMODITY_ORDERNOS, orderNoList);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.selectByOrderNoList", e, new Object[]{this.getSqlNamespace(),getSqlName(MybatisList.SELECT_ORDER_COMMODITY_ORDERNOS), orderNoList});
		}
	}
	
	
}
