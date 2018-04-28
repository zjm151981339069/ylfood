package com.spfood.oms.order.utils;

/**
 * 
 * @author huangcj
 * @date 2017年1月6日 Des:mybatis sql id
 *
 */
public class MybatisList {
		//条件查询订单列表
		public final static String SELECT_ORDERLIST_PAGE = "selectOrderListByCriteriaPage";
		//查询多个订单
		public static final String SELECT_ORDERLIST_ORDERNOS = "selectOrderListByOrderNos";
		//查询多个订单支付
		public static final String SELECT_ORDERPAY_ORDERNOS = "selectOrderPayListByOrderNo";
		//修改临时订单
		public static final String UPDATE_ORDERTEMP_ORDERNO_SELECTIVE = "updateOrderTempByOrderNoSelective";
		//修改订单
	    public static final String UPDATE_ORDER_ORDERNO_SELECTIVE = "updateOrderByOrderNoSelective";
	    //修改订单支付
		public static final String UPDATE_ORDERPAY_ORDERNO_SELECTIVE = "updateOrderPayByOrderNoSelective";
		//批量修改订单支付为已支付
		public static final String UPDATE_ORDERPAY_ISPAY = "updateBatchOrderPayIsPay";
		//批量修改订单支付为已同步
		public static final String UPDATE_ORDERPAY_ISSENDFMS = "updateBatchOrderPayIsSendFMS";
		//查询订单详情
		public static final String SELECT_ORDER_DETAIL_ORDERNO = "selectOrderDetailByOrderNo";
		//查询订单(不包括商品,日志,支付)
		public static final String SELECT_ORDER_ORDERNO = "selectOrderByOrderNo";
		//查询订单详情List
		public static final String SELECT_ORDER_DETAILLIST_ORDERNO = "selectOrderDetailListByOrderNo";
		//多个换货商品查询
		public static final String GETECHANGELISTBYORDERNOS = "getOrderExchangeListByOrderExchangeNos";
		//换货分页列表
		public static final String GET_EXCHANGELIST_BYPARAM = "getExchangePage";
		//订单编号集合查询一批订单商品
		public static final String SELECT_ORDER_COMMODITY_ORDERNOS = "selectOrderCommodityByOrderNoList";
		//用户中心换货数量查询
		public static final String GET_CU_EXCHANGELIST = "getExchangeListTotalByCriteria";
		//用户中心换货列表
		public static final String GET_GU_EXCHANGELIST_BYPARAM = "select";
		//取消订单
		public static final String UPDATE_ORDER_CANCEL = "updateOrderCancel";
		//批量取消订单状态
		public static final String UPDATE_BATCH_CANCEL_ORDER = "updateBatchCancelOrder";
		//删除多个临时订单
		public static final String DELETE_ORDERTEMP_ORDERNOS = "deleteOrderTempListByOrderNo";
		//批量修改订单状态
		public static final String UPDATE_ORDER_STATUS = "updateBatchOrderStatus";
		//批量修改临时表状态
		public static final String UPDATE_ORDERTEMP_BY_ORDERTEMPLIST = "updateBatchOrderTempStatus";
		/**/
		public static final String GETECHANGECOMMODITYLISTBYORDERNOS = "getExchangeCommodityList";
		
		//查询订单
		public final static String GETORDERPAGEBYPARAM = "getOrderPageByParam"; 
		
		
		//查询要同步的订单支付数据
		public static final String GET_UNSYN_ORDERPAY_INFO = "getUnSynOrderPayInfo";
		//查询订单和订单关联商品
		public static final String SELECT_ORDER_AND_COMMODITY = "selectOrderAndCommodityByOrderNo";
		//查询订单和订单关联商品
		public static final String SELECT_ORDER_AND_COMMODITY_LIST = "selectOrderAndCommodityByOrderNoList";
		
		
    
}