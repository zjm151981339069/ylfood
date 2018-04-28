package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;

/**
 * 换货商品dao层
 * @author Administrator
 *
 */
public interface OrderExchangeCommodityDao extends BaseDao<OrderExchangeCommodity>{

	//查询多个换货商品
	List<OrderExchangeCommodity> getOrderExchangeListByExCodes(String exCode);

	List<OrderExchangeCommodity> getOrderExchangeListByExCodes(List<String> exCodes);

}
