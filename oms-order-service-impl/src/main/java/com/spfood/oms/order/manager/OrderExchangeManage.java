package com.spfood.oms.order.manager;

import com.spfood.oms.order.intf.domain.OrderExchange;

/**
 * 换货单生成入库管理接口
 * @author Administrator
 *
 */
public interface OrderExchangeManage {
	
	//审核通过操作
	boolean passNewOrderExchange(OrderExchange orderExchange);

	//审核不通过操作
	void cancelOrderExchangeByExid(OrderExchange orderExchange);

	//根据ID查询换货单详情
	OrderExchange selectOrderExchangeByExId(Long exId);

	void updateOrderExchangeByExId(OrderExchange orderExchange);
	
}
