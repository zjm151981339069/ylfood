/**

 */
package com.spfood.oms.orderinfosyn.manager;


import com.spfood.oms.order.intf.domain.OrderBill;

/**
 * 
 * 订单相关信息同步到FMS 比如：订单支付信息、发票信息等 z20170329
 * 
 */
public interface SendOrderRelativeInfoToFmsManager {

	/**
     * 订单支付信息同步到FMS
     */
    public void sendOrderPayInfoToFms();
    
    /**
     * 订单发票信息同步到FMS
     */
    public boolean sendOrderBillInfoToFms(OrderBill orderBill);
}
