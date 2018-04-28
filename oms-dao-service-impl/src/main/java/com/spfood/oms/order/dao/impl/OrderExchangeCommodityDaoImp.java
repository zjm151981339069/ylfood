package com.spfood.oms.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderExchangeCommodityDao;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;
import com.spfood.oms.order.utils.MybatisList;

@Repository
public class OrderExchangeCommodityDaoImp extends BaseDaoImpl<OrderExchangeCommodity> implements
		OrderExchangeCommodityDao {

	//用户中心
	@Override
	public List<OrderExchangeCommodity> getOrderExchangeListByExCode(String exCode) {
		try {
			return sqlSessionTemplate.selectList(MybatisList.GETECHANGELISTBYORDERNOS, exCode);
		} catch (Exception e) {
			throw new BizException(
					"oms.order.dao.impl.OrderExchangeCommodityDaoImp.getOrderExchangeListByExCode",
					e, exCode);
		}
		
	}

	@Override
	public List<OrderExchangeCommodity> getOrderExchangeListByExCodes(
			List<String> exCodes) {
		try {
			return sqlSessionTemplate.selectList(MybatisList.GETECHANGECOMMODITYLISTBYORDERNOS, exCodes);
		} catch (Exception e) {
			throw new BizException(
					"oms.order.dao.impl.OrderExchangeCommodityDaoImp.getOrderExchangeListByExCodes",
					e, exCodes);
		}
	}
	
}
