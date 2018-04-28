package com.spfood.wos.workOrder.manager;

import java.util.List;
import java.util.Map;

import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
/**
 * 工单管理：        包括：单线程拆分订单，多线程拆分订单，创建出库领料分拣工单
 * @author Administrator
 *
 */
public interface OperateOrderSiteManager {
    
    /**
     * 多线程执行订单拆分操作,每一个线程都处理一批订单，并生成对应的任务工单
     * @param tempOrderCodes 订单编号列表
     */
    public void operateOrderByMulitThread(List<String> orderTempCodes);
    /**
     * 单线程执行订单拆分操作
     * @param tempOrderCodes 订单编号列表
     */
    public void operateOrderBySingleThread(List<String> orderTempCodes)throws Exception;
    /**
     * 生成出库单、领料单、分拣单
     * @param map 出库单数据Map
     * @param SortMapByOrder 分拣单数据Map
     */
    public void createDeliveSortRequWorkOrder(Map<String, List<HouseOrderCommodity>> map,
            Map<HouseOrder, Map<String, List<HouseOrderCommodity>>> SortMapByOrder,Map<String, Integer> commdiCountMap)throws Exception;
}
