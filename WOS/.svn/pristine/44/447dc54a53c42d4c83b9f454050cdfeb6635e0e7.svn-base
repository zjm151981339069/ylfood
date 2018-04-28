package com.spfood.wos.workOrder.manager;

import java.util.List;

import com.spfood.wos.workOrder.intf.domain.ReceiveTask;
import com.spfood.wos.workOrder.intf.domain.SortingTask;


public interface SendInfoToSCSManager {
    /**
     * 发送分拣任务工单到scs
     * @param sortingTasks 分拣任务单列表
     */
    public void sendSortWorkOrderToSCS(List<SortingTask> sortingTasks)throws Exception;
    /**
     * 发送领料任务工单到scs
     * @param receiveTask 领料任务单列表
     */
    public void sendRequisitionWorkOrderToSCS(List<ReceiveTask> receiveTask)throws Exception;
}
