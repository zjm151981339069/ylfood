package com.spfood.wos.workOrder.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.constants.SendOrderAmount;
import com.spfood.wos.workOrder.manager.CreateWorkOrderManager;
import com.spfood.wos.workOrder.manager.OperateOrderManager;
import com.spfood.wos.workOrder.manager.OperateOrderSiteManager;
import com.spfood.wos.workOrder.manager.WorkOrderExtractManager;
@Service
public class CreateWorkOrderManagerImpl implements CreateWorkOrderManager {
    @Autowired
    private OperateOrderManager operateOrderManager;
    @Autowired
    private WorkOrderExtractManager workOrderExtractManager;
    @Autowired
    private OperateOrderSiteManager operateOrderManagerWithMultiWarehouseManager;
    @Autowired
    private SendOrderAmount sendOrderAmount;
    Logger logger = Logger.getLogger(CreateWorkOrderManagerImpl.class);
    @Override
    public void createWorkOrder() throws Exception {
        try {
            // 获取临时订单表的临时订单列表
            List<String> tempOrderCodes = workOrderExtractManager.getOrderTempCodeStrings();
            int orderTotal = tempOrderCodes.size();
            logger.info("查询到的临时订单数量为"+orderTotal);
            // 判断查询的条数大于0执行信息同步方法
            if (orderTotal > 0) {
                // 查询已订单数量小于或等于单线程最大提交数量执行单线程方法
                if (orderTotal <= sendOrderAmount.getMaxOrderAmount()) { 
                    logger.info("使用单线程拆解订单");
                    // 使用单线程方式发生订单信息
                    operateOrderManagerWithMultiWarehouseManager.operateOrderBySingleThread(tempOrderCodes);
                } else {// 使用多线程模式发送订单
                    logger.info("使用多线程拆解订单");
                    operateOrderManager.operateOrderByMulitThread(tempOrderCodes);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException("wos.service.quartz", e.getCause(),"quartz create workorder exception");
        }
        
    }

}
