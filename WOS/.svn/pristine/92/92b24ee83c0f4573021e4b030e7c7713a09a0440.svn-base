package com.spfood.wos.workOrder.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.constants.ResolveOrderTaskExecuteCount;
import com.spfood.wos.workOrder.constants.SendOrderAmount;
import com.spfood.wos.workOrder.intf.HouseOrderService;
import com.spfood.wos.workOrder.intf.WorkOrderService;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
import com.spfood.wos.workOrder.intf.domain.OutTask;
import com.spfood.wos.workOrder.intf.domain.OutTaskGoods;
import com.spfood.wos.workOrder.intf.domain.ReceiveTask;
import com.spfood.wos.workOrder.intf.domain.ReceiveTaskGoods;
import com.spfood.wos.workOrder.intf.domain.SortingTask;
import com.spfood.wos.workOrder.intf.domain.SortingTaskCommodity;
import com.spfood.wos.workOrder.manager.CreateTaskManager;
import com.spfood.wos.workOrder.manager.OperateOrderManager;
import com.spfood.wos.workOrder.manager.SendInfoToSCSManager;
import com.spfood.wos.workOrder.manager.SendInfoToWMSManager;
import com.spfood.wos.workOrder.manager.WorkOrderExtractManager;
import com.spfood.wos.workOrder.thread.ResolveOrderTask;
import com.spfood.wos.workOrder.thread.ResolveOrderThread;
import com.spfood.wos.workOrder.utils.RandomCodeUitls;
@Service
@Transactional
public class OperateOrderManagerImpl implements OperateOrderManager{
    @Autowired
    private HouseOrderService houseOrderService;   
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;  
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private WorkOrderExtractManager workOrderExtractManager;
    @Autowired
    private SendInfoToSCSManager sendInfoToSCS;
    @Autowired
    private SendInfoToWMSManager sendInfoToWMS;
    @Autowired
    private CreateTaskManager createTaskManager;
    @Autowired
    private SendOrderAmount sendOrderAmount;
    Logger logger = Logger.getLogger(OperateOrderManagerImpl.class);
    @Override
    public void operateOrderByMulitThread(List<String> orderTempCodes) {
        Integer threadOrderAmount = sendOrderAmount.getThreadOrderAmount();
        try {
            //拿到订单的总数量
            int orderTotal = orderTempCodes.size();
            //通过总订单数量得到执行订单线程的数量
            int threadAmount = orderTotal%threadOrderAmount==0?
                    orderTotal/threadOrderAmount: 
                        orderTotal/threadOrderAmount+1;   
            //设置正在执行的任务数量
            ResolveOrderTaskExecuteCount.setCount(threadAmount);
            //订单同步信息线程列表
            List<ResolveOrderThread> threads = new ArrayList<ResolveOrderThread>();
            //创建订单同步信息线程列表
            for (int currentPage = 1; currentPage <= threadAmount; currentPage++) {             
                List<String> orderCodes=new ArrayList<String>();              
                for (int i = 0; i < threadOrderAmount; i++) {
                    try {
                        String orderCode = orderTempCodes.get((currentPage-1)*threadOrderAmount+i);
                        orderCodes.add(orderCode);
                    } catch (Exception e) {
                        //溢出结束循环
                        break;
                    }
                }
                //创建任务对象
                ResolveOrderTask orderSynTask = new ResolveOrderTask(orderCodes,currentPage);
                //根据任务对象创建任务线程
                ResolveOrderThread thread = new ResolveOrderThread(orderSynTask);
                threads.add(thread);
            }
            int i=1;
            //依次启动订单同步信息线程       
            for (ResolveOrderThread orderSynWorkThread : threads) {
                Thread thread = new Thread(orderSynWorkThread);
                taskExecutor.execute(thread);
                logger.info("启动线程数"+i++);          
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BizException("wos.service.quartz.createWorkOrder", e.getCause(),"mutlthread create worksOrder exception");

        }
        
    }

    @Override
    public void operateOrderBySingleThread(List<String> orderTempCodes) {
        Integer orderAmount = sendOrderAmount.getOrderAmount();
        try {
            // 循环计数器
            int count = 0;
            // 循环次数
            int cycleIndex = orderTempCodes.size() % orderAmount == 0 ? orderTempCodes
                    .size() / orderAmount: orderTempCodes.size() / orderAmount + 1;
            // 各个温区对应的商品列表
            Map<String, List<HouseOrderCommodity>> map = new HashMap<String, List<HouseOrderCommodity>>();
            //分拣工单对应的数据  ，订单，温区，商品列表
            Map<HouseOrder, Map<String, List<HouseOrderCommodity>>> SortMapByOrder = new HashMap<HouseOrder, Map<String, List<HouseOrderCommodity>>>();            
            //订单商品数量
            Map<String, Integer> commdiCountMap = new HashMap<String, Integer>();
            // 循环执行，如果当前的查询条数为设置的查询条数则再次循环查询
            do {
                // 订单列表设置为null
                List<HouseOrder> orders = new ArrayList<HouseOrder>();
                List<HouseOrder> orders2 = new ArrayList<HouseOrder>();
                //获取此次循环处理的订单列表
                workOrderExtractManager.getOrdersByCodes(orderTempCodes, count, orders);
                workOrderExtractManager.getOrdersByCodes(orderTempCodes, count, orders2);
                for (HouseOrder houseOrder : orders) {
                    List<HouseOrderCommodity> orderCommList = houseOrder.getOrderCommList();
                    for (HouseOrderCommodity houseOrderCommodity : orderCommList) {
                        commdiCountMap.put(houseOrderCommodity.getCode()+houseOrder.getOrderNo(),houseOrderCommodity.getCount());
                    }
                }
                /**
                 * 根据定单生成分拣单
                 */
                workOrderExtractManager.getSortOrderDataByOrderNo(SortMapByOrder, orders2);                   
                /**
                 * 遍历每个订单中的商品，并放到对应的温区中
                 */
                workOrderExtractManager.traverseOrderCommodities(map, orders);
                count++;
                logger.info("执行循环次数=========" + count);
            } while (count < cycleIndex);
            //调用生成出库，领料，分拣工单
            if(map.keySet().size()>0&&SortMapByOrder.keySet().size()>0){
                createDeliveSortRequWorkOrder(map, SortMapByOrder,commdiCountMap);
            }else {
                logger.info("******该批次定时生成工单无有效数据 ：无订单或订单不包含商品*****");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BizException("wos.service.quartz.createWorkOrder", e.getCause(),"singleThread create worksOrder exception");
        }
        
    }
    /**
     * 生成出库单、领料单、分拣单
     * @throws Exception 
     */
    @Override
    public void createDeliveSortRequWorkOrder(Map<String, List<HouseOrderCommodity>> map,Map<HouseOrder, Map<String, List<HouseOrderCommodity>>> SortMapByOrder,Map<String, Integer> commdiCountMap) throws Exception {
        //领料工单编号Map    温区：编号
        Map<String,String> requisitionCodeMap = new HashMap<String, String>();
        //出库工单编号Map    温区：编号
        Map<String,String> deliveCodeMap = new HashMap<String, String>();      
        //出库工任务工单列表
        List<OutTask> outTaskList = new ArrayList<OutTask>();
        //领料任务工单列表
        List<ReceiveTask> receiveTaskList = new ArrayList<ReceiveTask>();
        //分拣任务工单列表
        List<SortingTask> sortingTaskList = new ArrayList<SortingTask>();
        //实际发生分拣任务工单列表
        List<SortingTask> realSortingTaskList = new ArrayList<SortingTask>();
        //订单工单商品关联表
        List<HouseOrderCommodity> houseOrderCommodityList = new ArrayList<HouseOrderCommodity>();
        //自提点区域、温区、分拣任务的Map
        Map<String, Map<String,SortingTask>> sortTaskMap = new HashMap<String, Map<String,SortingTask>>();
        //温区，温区名称Map
        Map<String, String> areaAreaNameMap= new HashMap<String, String>();
        Set<String> keySet = map.keySet();
        Set<HouseOrder> keySet3 = SortMapByOrder.keySet();
        //时间格式
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyMMdd");
        //工单生成时间
        Date workOrderTaskTime = new Date();
        /**
         * 获取所有温区的商品，查询商品的温区，类型，单位，标准等
         */
        //所有的订单商品列表
        List<HouseOrderCommodity> allHouseOrderCommodityList = new ArrayList<HouseOrderCommodity>();
        //保存所有商品到订单商品列表
        for (String temperature : keySet) {
            List<HouseOrderCommodity> list = map.get(temperature);
            for (HouseOrderCommodity houseOrderCommodity : list) {
                areaAreaNameMap.put(temperature, houseOrderCommodity.getAreaName());
                allHouseOrderCommodityList.add(houseOrderCommodity);
            }
            
        }
        /**
         * 生成出库和领料工单数据
         */
        for (String temperature : keySet) {
            //当前温区的商品
            List<HouseOrderCommodity> list = map.get(temperature);
            //当前温区包含的订单
            List<HouseOrder> currentTempraturnOrders = new ArrayList<HouseOrder>();
            //出库物品列表
            List<OutTaskGoods> goodsList = new ArrayList<OutTaskGoods>();
            //领料物品列表
            List<ReceiveTaskGoods> receiveGoodsList = new ArrayList<ReceiveTaskGoods>();
            //当前温区的总任务数量
            Integer currentTempraturnTaskNum=0  ;
            //获取当前温区包含的订单
            for (HouseOrder order : keySet3) {
                Map<String, List<HouseOrderCommodity>> map2 = SortMapByOrder.get(order);
                Set<String> tempset = map2.keySet();
                //如果该订单存在该温区商品添加订单到出库和分拣任务单中
                if(tempset.contains(temperature)){
                    currentTempraturnOrders.add(order);
                }
            }
            //出库工单编号
            String deliveCode = "CK"+dateFormater.format(new Date())+RandomCodeUitls.getVerificationCode(6,false);
            //领料工单编号
            String requisitionCode = "LL"+dateFormater.format(new Date())+RandomCodeUitls.getVerificationCode(6,false);
            //生成任务数量和物品列表
            for (HouseOrderCommodity orderCommodity : list) {
                currentTempraturnTaskNum+=1;
                /**
                 * 调用wms的接口通过物品编号获取物品信息并将outTaskGoods添加到出库物品列表中
                 */
                OutTaskGoods outTaskGoods = new OutTaskGoods();
                outTaskGoods.setCode(orderCommodity.getCode());
                outTaskGoods.setName(orderCommodity.getName());
                outTaskGoods.setOutTotal(orderCommodity.getCount());
                outTaskGoods.setOutCode(deliveCode);
                goodsList.add(outTaskGoods);
                /**
                 * 设置领料物品列表
                 * 
                 */
                ReceiveTaskGoods receiveTaskGoods = new ReceiveTaskGoods();
                receiveTaskGoods.setCode(orderCommodity.getCode());
                receiveTaskGoods.setReceiveTotal(orderCommodity.getCount());
                receiveTaskGoods.setRecCode(requisitionCode);
                receiveTaskGoods.setName(orderCommodity.getName());
                receiveGoodsList.add(receiveTaskGoods);
            }
            /**
             * 生成出库任务单
             */
            OutTask outTask = new OutTask();
            outTask.setArea(temperature);
            outTask.setAreaName(areaAreaNameMap.get(temperature));
            outTask.setOutCode(deliveCode);
            outTask.setTaskNum(currentTempraturnTaskNum);
            outTask.setGoodsList(goodsList);
            outTask.setRecCode(requisitionCode);
            outTask.setTaskTime(workOrderTaskTime);
            //添加到出库工单列表中
            outTaskList.add(outTask);
            //将出库任务单编号保存到出库任务工单编号Map中
            deliveCodeMap.put(temperature, deliveCode);
            /**
             * 生成领料任务单
             */
            ReceiveTask receiveTask = new ReceiveTask();

            //添加领料工单数据
            receiveTask.setArea(temperature);
            receiveTask.setTaskNum(currentTempraturnTaskNum);
            receiveTask.setRecCode(requisitionCode);
            receiveTask.setGoodsList(receiveGoodsList);
            receiveTask.setAreaName(areaAreaNameMap.get(temperature));
            receiveTask.setTaskTime(workOrderTaskTime);
            receiveTaskList.add(receiveTask);
            //将领料工单编号存入领料工单编号Map中
            requisitionCodeMap.put(temperature, requisitionCode);                       
        }
        logger.info("出库工单数量： "+outTaskList.size());
        logger.info("领料工单数量： "+receiveTaskList.size());
        /**
         * 生成对应的：自提点、温区对应分拣工单
         */
        //得到定单列表中的自提点名称列表
        List<String> extractaddrList = new ArrayList<String>();
        for (HouseOrder HouseOrder : keySet3) {
            if(HouseOrder.getSiteCode()!=null){
                if(!extractaddrList.contains(HouseOrder.getSiteCode())){
                    extractaddrList.add(HouseOrder.getSiteCode());
                }   
            }

        }
        //依次生成自提点、温区对应的分拣工单列表
        for (String area : extractaddrList) {
            for (String temp : requisitionCodeMap.keySet()) {
                logger.info("自提点         "+area+"温区         "+temp);
                SortingTask sortingTask = new SortingTask();
                sortingTask.setArea(temp);
                String sortCode ="FJ"+dateFormater.format(new Date())+RandomCodeUitls.getVerificationCode(6,false);
                sortingTask.setSortCode(sortCode);
                sortingTask.setRecCode(requisitionCodeMap.get(temp));
                sortingTask.setTaskNum(0);
                sortingTask.setAreaName(areaAreaNameMap.get(temp));
                sortingTask.setSiteCode(area);
                sortingTask.setTaskTime(workOrderTaskTime);
                List<SortingTaskCommodity> sortingTaskCommList = new ArrayList<SortingTaskCommodity>();
                sortingTask.setSortingTaskCommList(sortingTaskCommList);
                sortingTaskList.add(sortingTask);
                //将分拣工单保存到分拣sortTaskMap中
                Map<String, SortingTask> areaMap = sortTaskMap.get(area);
                //如果自提点中没有对应的map
                if(areaMap==null){
                    Map<String, SortingTask> tempMap= new HashMap<String, SortingTask>();
                    tempMap.put(temp, sortingTask);
                    sortTaskMap.put(area, tempMap);
                }else{
                    areaMap.put(temp, sortingTask);
                    sortTaskMap.put(area, areaMap);
                }
            }
        }
        logger.info("初始构建分拣任务单列表"+sortingTaskList.size());
        /**
         * 生成：订单，温区，商品列表 分拣工单数据,保存到对应的分拣工单Map中
         */
        for (HouseOrder order : keySet3) {
            logger.info("当前 执行操作生成分拣工单的订单编号"+order.getOrderNo());
            Map<String, List<HouseOrderCommodity>> map2 = SortMapByOrder.get(order);
            Set<String> tempset = map2.keySet();
            for (String string : tempset) {
                logger.info("原始构建Map订单包含的温区"+string);
            }
            for (String temp : tempset) {
                logger.info("遍历温区"+temp);
                List<HouseOrderCommodity> list = map2.get(temp);
                //得到分拣工单列表的迭代器
                Iterator<SortingTask> iterator = sortingTaskList.iterator();
                //保存分拣工单数据到对应的分拣工单            
                while (iterator.hasNext()) {
                    SortingTask sortingTasklist = iterator.next();
                    Map<String, SortingTask> areaMap = sortTaskMap.get(sortingTasklist.getSiteCode());
                    //得到sortTaskMap中的
                    SortingTask sortingTask = areaMap.get(sortingTasklist.getArea().toString());
                    Integer taskNum = 0;
                    Boolean tBoolean = true;
                    if(!sortingTask.getSiteCode().equals(order.getSiteCode())){
                        logger.info("自提点区域不一致");
                        tBoolean=false;
                    }
                    if(!sortingTask.getArea().toString().equals(temp)){
                        logger.info("温区不一致");
                        logger.info("分拣任务单温区********"+sortingTask.getArea().toString()+"    订单温区*********"+temp);
                        tBoolean=false;
                    }
                    if(tBoolean){
                        logger.info("自提点和温区匹配========分拣任务单温区********"+sortingTask.getArea().toString()+"    订单温区*********"+temp);
                        //设置分拣单中分拣订单的分拣商品
                        List<SortingTaskCommodity> sortingTaskCommList=sortingTask.getSortingTaskCommList();
                        for (HouseOrderCommodity orderCommodity : list) {
                            SortingTaskCommodity sortingTaskCommodity = new SortingTaskCommodity(); 
                            sortingTaskCommodity.setCode(orderCommodity.getCode());
                            sortingTaskCommodity.setAddr(order.getAddr());
                            sortingTaskCommodity.setName(orderCommodity.getName());
                            sortingTaskCommodity.setOrderNo(order.getOrderNo());
                            sortingTaskCommodity.setPhone(order.getPhone());
                            sortingTaskCommodity.setReceiver(order.getReceiver());
                            sortingTaskCommodity.setSortCode(sortingTask.getSortCode());
                            sortingTaskCommodity.setCount(commdiCountMap.get(orderCommodity.getCode()+order.getOrderNo()));
                            sortingTaskCommodity.setUnit("unit固定");
                            sortingTaskCommodity.setStandard("standard固定");
                            sortingTaskCommodity.setDeliverTime(order.getDeliverTime());
                            sortingTaskCommodity.setType(orderCommodity.getType());
                            sortingTaskCommList.add(sortingTaskCommodity);
                            
                            taskNum+=1;
                            logger.info("=======*******taskNum "+taskNum+"*******=======");
                            
                            //添加订单工单商品关联对象HouseOrderCommodity到订单工单关联对象list中
                            HouseOrderCommodity houseOrderCommodity = new HouseOrderCommodity();
                            houseOrderCommodity.setCode(orderCommodity.getCode());
                            houseOrderCommodity.setOrderNo(order.getOrderNo());
                            houseOrderCommodity.setOutCode(deliveCodeMap.get(temp));
                            houseOrderCommodity.setRecCode(sortingTask.getRecCode());
                            houseOrderCommodity.setSortCode(sortingTask.getSortCode());
                            houseOrderCommodityList.add(houseOrderCommodity);
                        }
                        sortingTask.setSortingTaskCommList(sortingTaskCommList);
                        sortingTask.setTaskNum(taskNum+sortingTask.getTaskNum());
                        sortingTask.setSite(order.getSite());
                        areaMap.put(sortingTasklist.getArea().toString(), sortingTask);
                        sortTaskMap.put(sortingTasklist.getSiteCode(), areaMap);
                    }
                }

            }
        }
        /**
         * sortTaskMap中的分拣工单保存到realSortingTaskList中
         */
        Set<String> keySet2 = sortTaskMap.keySet();
        for (String string : keySet2) {
            Map<String, SortingTask> map2 = sortTaskMap.get(string);
            Set<String> keySet4 = map2.keySet();
            logger.info("原始分拣工单的列表"+keySet4.size());
            for (String string2 : keySet4) {
                SortingTask sortingTask = map2.get(string2); 
                logger.info("分拣温区==="+sortingTask.getArea()+"  任务数量  "+sortingTask.getTaskNum());
                if(sortingTask.getTaskNum()>0){
                    realSortingTaskList.add(sortingTask);
                }             
            }
        }
        logger.info("分拣工单列表： "+realSortingTaskList.size());
        /**
         * 调用保存个工单list的方法，保存工单
         */
        boolean createBoolean=false; 
        if(outTaskList.size()>0){
            createBoolean= createTaskManager.createTask(outTaskList, receiveTaskList, realSortingTaskList, houseOrderCommodityList);
        }
        if(createBoolean==false){
            logger.info("该批次工单保存失败");
        }
        //如果保存成功，同时执行更改订单状态和发送工单列表到消息队列
        if(createBoolean){
            try {
                /**
                 * 生成的出库、领料、分拣工单分别发送到各指定接收工单系统
                 */
                sendInfoToSCS.sendSortWorkOrderToSCS(realSortingTaskList);
                sendInfoToSCS.sendRequisitionWorkOrderToSCS(receiveTaskList);
                sendInfoToWMS.sendDeliveWorkOrderToWMS(outTaskList);                
                logger.info("*****************执行工单信息发送   地址：scs_sort,scs_requisition,wms_delive**********************");
            } catch (Exception e) {
                e.printStackTrace();
                createBoolean=false;
                logger.info("*****************工单信息发送失败   **********************");
                logger.error(e.getMessage());
                throw new BizException("wos.service.messageQueue", e.getCause(),"send workOrder message exception");

            }
        }
        //如果工单保存和发送成功，更改订单为生成工单完成
        if(createBoolean){
            List<String> houseOrderList= new ArrayList<String>();
            for (HouseOrder houseOrder : keySet3) {
                houseOrderList.add(houseOrder.getOrderNo());
            }
            houseOrderService.updateToHasSorting(houseOrderList);
            logger.info("更改订单状态为完成工单生成       订单数量"+houseOrderList.size());
        }
       
    }

}
