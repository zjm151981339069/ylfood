package com.spfood.wos.workOrder.manager;

import java.util.List;

import com.spfood.wos.workOrder.intf.domain.OutTask;

public interface SendInfoToWMSManager {
    /**
     * 发送出库工单到wms
     * @param outTasks 分拣任务列表
     */
    public void sendDeliveWorkOrderToWMS(List<OutTask> outTasks)throws Exception;
}
