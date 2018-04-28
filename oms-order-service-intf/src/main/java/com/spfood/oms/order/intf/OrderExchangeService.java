package com.spfood.oms.order.intf;
/**
 * 换货单业务借口
 */

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;

public interface OrderExchangeService {
	
	//生成换货单
	public boolean OrderExchangeCreate(String orderNo,OrderExchange orderExchange);

	//换货列表展示
	public PageInfo<OrderExchange> getOrderExchangeByParas(OrderExchangeCriteria orderExchangeCriteria);

	//查询换货单详情
	public OrderExchange selectOrderExchangeByExid(Long exId);
	
	//换货单审核通过操作
	public boolean passOrderExchangeByExid(OrderExchange orderExchange);

	//换货单审核不通过
	public boolean cancelOrderExchangeByExid(OrderExchange orderExchange);
	
	//用户中心
	public PageInfo<OrderExchange> getOrderExchangeListByCriteria(OrderExchangeCriteria orderExchangeCriteria);
}
