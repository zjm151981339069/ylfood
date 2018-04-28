package com.spfood.wos.workOrder.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.wos.workOrder.intf.WorkOrderService;
import com.spfood.wos.workOrder.manager.CreateWorkOrderManager;
import com.spfood.wos.workOrder.manager.WorkOrderServiceManager;


public class WorkOrderServiceImpl implements WorkOrderService  {
	
	@Autowired
    private WorkOrderServiceManager workOrderServiceManager;
	@Autowired
	private CreateWorkOrderManager createWorkOrderManager;
    @Override
    public void specificHouseOrdersOpearation(List<String> houseOrderCodes) throws Exception {
        if(houseOrderCodes!=null&&houseOrderCodes.size()>0){
            workOrderServiceManager.specificHouseOrdersOpearation(houseOrderCodes);          
        }    
    }
    @Override
    public void createWorkOrderByHouseOrder() throws Exception {
        createWorkOrderManager.createWorkOrder();       
    }


}

