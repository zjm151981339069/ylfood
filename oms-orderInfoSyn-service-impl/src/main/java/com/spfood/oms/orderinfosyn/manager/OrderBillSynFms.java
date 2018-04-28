package com.spfood.oms.orderinfosyn.manager;

import java.math.BigDecimal;


public interface OrderBillSynFms {

    /**
     * 更新订单发票信息并同步发票信息到activemq
     * @param orderno
     * @param billtype
     * @param billtitle
     * @param billcontent
     * @return 1为成功 ,-1为失败
     */
    public boolean orderBillSyn(String orderNo, Integer billType,
            Integer billTitle, String billContent,BigDecimal orderAmount);
}
