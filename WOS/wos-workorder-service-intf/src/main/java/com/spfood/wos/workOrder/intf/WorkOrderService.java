package com.spfood.wos.workOrder.intf;

import java.util.List;

import com.spfood.wos.workOrder.intf.domain.Goods;

public interface WorkOrderService {
    /**
     * 指定订单列表拆分生成对应的工单
     * @param houseOrderCodes  订单编号列表
     * @throws Exception 
     */
    public void specificHouseOrdersOpearation(List<String> houseOrderCodes) throws Exception;
    /**
     * 拆分houseOrder
     * @throws Exception
     */
    public void createWorkOrderByHouseOrder() throws Exception;
}
