package com.spfood.wos.workOrder.manager;

import java.util.List;
import java.util.Map;

import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;

/**
 * 订单和工单数据提取管理
 * @author Administrator
 *
 */
public interface WorkOrderExtractManager {

    /**
     * 获取临时订单表中的订单状态为2的订单列表
     * @return 订单列表
     */
    public List<String> getOrderTempCodeStrings();
    
    /**
     * 遍历定单中的商品到指定温区Map中
     * @param map 温区和商品列表Map
     * @param orders 订单列表
     */
    public void traverseOrderCommodities( Map<String, List<HouseOrderCommodity>> map, List<HouseOrder> orders);
    
    /**
     * 通过订单编号得到订单列表
     * @param orderTempCodes  订单编号列表
     * @param count  当前循环的次数
     * @param orders 订单列表
     */
    public void getOrdersByCodes(List<String> orderTempCodes, int count,
            List<HouseOrder> orders); 
    
    
    /**
     * 根据订单，温区，商品列表生成分拣单的数据
     * @param SortMapByOrder
     * @param orders
     */
    public void getSortOrderDataByOrderNo( Map<HouseOrder, Map<String, List<HouseOrderCommodity>>> SortMapByOrder,List<HouseOrder> orders); 
    
    /**
     * 多线程条件下通过订单编号获取订单
     * @param orderTempCodes  订单编号列表
     * @param orders 订单列表
     */
    public void getOrdersByCodesInMultiThread(List<String> orderTempCodes,List<HouseOrder> orders);
    /**
     * 订单所有商品放入指定的温区和自提点编码
     * 
     * 
     * @param map
     * @param orders
     */
    void getSiteTempAreaCommMap(Map<String, List<HouseOrderCommodity>> map,
            List<HouseOrder> orders);
}
