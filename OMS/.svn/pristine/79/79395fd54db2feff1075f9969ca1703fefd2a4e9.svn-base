package com.spfood.oms.orderinfosyn.intf;

import java.math.BigDecimal;
import java.util.List;

import com.spfood.oms.order.intf.domain.OrderPay;

public interface OrderFinanceSynService {
    
    /**
     * 支付信息到activemq
     * @param orderPay
     * @return 1为成功 ,-1为失败
     */
    public boolean orderPaySyn(OrderPay orderPay);
    
    /**
     * 批量同步支付信息到activemq
     * @return 1为成功 ,-1为失败
     */
	public boolean orderPaySyn(List<OrderPay> orderPayList);
    
    /**
     * 更新订单发票信息并同步发票信息到activemq
     * @param orderno
     * @param billtype
     * @param billtitle
     * @param billcontent
     * @param orderAmount
     * @return 
     */
    public boolean orderBillSyn(String orderNo, Integer billType,
            Integer billTitle, String billContent,BigDecimal orderAmount);


    /**
     * 财务信息定时调度接口
     */
    public void sendOrderPayToFms();
    
 
    
}
