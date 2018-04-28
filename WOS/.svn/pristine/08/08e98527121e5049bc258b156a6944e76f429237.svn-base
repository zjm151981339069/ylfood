package com.spfood.wos.workOrder.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.constants.ResolveOrderTaskExecuteCount;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
import com.spfood.wos.workOrder.manager.OperateOrderManager;
import com.spfood.wos.workOrder.manager.WorkOrderExtractManager;
import com.spfood.wos.workOrder.utils.SpringBeanUtils;
/**
 * 拆分订单任务线程
 * @author Administrator
 *
 */
@Component
public class ResolveOrderTask {
    @Autowired
    private WorkOrderExtractManager workOrderExtractManager;
    @Autowired
    private OperateOrderManager operateOrderManager;
    //订单编号列表
    private List<String> orderCodes = new ArrayList<String>();  
    //任务对象的id
    private Integer taskId; 
    Logger logger = Logger.getLogger(ResolveOrderTask.class);
    public ResolveOrderTask() {
        super();
    }
    public ResolveOrderTask(List<String> orderCodes, Integer taskId) {
        super();
        workOrderExtractManager=SpringBeanUtils.getBean(WorkOrderExtractManager.class);
        operateOrderManager=SpringBeanUtils.getBean(OperateOrderManager.class);
        this.orderCodes = orderCodes;
        this.taskId = taskId;
    }
    /** 
     * 执行任务的方法 
     * @throws Exception 
     */  
    public void execute() throws Exception {
        logger.info("======执行线程  "+this.taskId+"==========");
        List<HouseOrder> orders = new  ArrayList<HouseOrder>();
        List<HouseOrder> orders1 = new  ArrayList<HouseOrder>();
        //订单商品数量
        Map<String, Integer> commdiCountMap = new HashMap<String, Integer>();
        try {
            //通过订单编号列表查询同步的订单列表
            workOrderExtractManager.getOrdersByCodesInMultiThread(orderCodes, orders);
            // 各个温区对应的商品列表
            Map<String, List<HouseOrderCommodity>> map = new HashMap<String, List<HouseOrderCommodity>>();            
            //分拣工单对应的数据  ，订单，温区，商品列表
            Map<HouseOrder, Map<String, List<HouseOrderCommodity>>> SortMapByOrder = new HashMap<HouseOrder, Map<String, List<HouseOrderCommodity>>>();            
            /**
             * 根据定单生成分拣单：
             */
            workOrderExtractManager.getSortOrderDataByOrderNo(SortMapByOrder, orders1);
            /**
             * 遍历定单中的商品到指定温区Map中 
             */
            workOrderExtractManager.traverseOrderCommodities(map, orders);
            for (HouseOrder houseOrder : orders) {
                List<HouseOrderCommodity> orderCommList = houseOrder.getOrderCommList();
                for (HouseOrderCommodity houseOrderCommodity : orderCommList) {
                    commdiCountMap.put(houseOrderCommodity.getCode()+houseOrder.getOrderNo(),houseOrderCommodity.getCount());
                }
            }
            /**
             * 生成出货，领料，分拣工单
             */
            if(map.keySet().size()>0&&SortMapByOrder.keySet().size()>0){
                operateOrderManager.createDeliveSortRequWorkOrder(map, SortMapByOrder,commdiCountMap);
            }else {
                logger.info("******该批次定时生成工单无有效数据 ：无订单或订单不包含商品*****");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BizException("wos.service.quartz.createWorkOrder", e.getCause(),"mutlthread create worksOrder exception");

        }
        //任务数减1
        ResolveOrderTaskExecuteCount.setCount(ResolveOrderTaskExecuteCount.getCount()-1);
    }  
}

