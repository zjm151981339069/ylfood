package com.spfood.oms.orderpay.manage;

import com.spfood.oms.orderpay.intf.domain.ACPPayBackgroundResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayQueryResult;

public interface ACPQueryManager {

    /**
     * 网关类交易查询
     * @param orderId 订单Id
     * @param txnTime 订单产生时间
     * @param queryId 查询流水ID
     * @param txnType 交易类型
     * @return   查询结果
     * 同时输入：orderId+txnTime，或者queryId，或者三者同时输入
     */
    public ACPPayQueryResult orderPayByACPGateQuery(String orderId,String txnTime,String queryId);
}
