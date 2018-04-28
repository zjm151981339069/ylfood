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

	/**
	 * 通过换货单编号查询订单对应商品
	 * @param exCode
	 * @return
	 */
	List<OrderExchangeCommodity> getOrderExchangeListByExCode(String exCode);

	/**
	 * 通过换货单编号查询订单对应商品(批量)
	 * @param exCodes
	 * @return
	 */
	List<OrderExchangeCommodity> getOrderExchangeListByExCodes(List<String> exCodes);

}
