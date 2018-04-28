package com.spfood.wos.workOrder.manager;

import java.util.List;

public interface WorkOrderServiceManager {
    /**
     * 指定订单列表拆分生成对应的工单
     * @param houseOrderCodes  订单编号列表
     */
    public void specificHouseOrdersOpearation(List<String> houseOrderCodes)throws Exception;
}
