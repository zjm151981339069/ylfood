//package com.spfood.oms.orderinfosyn.manager.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.stereotype.Service;
//
//import com.spfood.oms.order.intf.OrderManagerService;
//import com.spfood.oms.order.intf.domain.OrderPay;
//import com.spfood.oms.orderinfosyn.constant.MqDestinationList;
//import com.spfood.oms.orderinfosyn.constant.OrderConstant;
//import com.spfood.oms.orderinfosyn.constant.SendOrderAmount;
//import com.spfood.oms.orderinfosyn.intf.domain.OrderSynNode;
//import com.spfood.oms.orderinfosyn.manager.SendOrderFms;
//import com.spfood.oms.orderinfosyn.thread.OrderPaySynTask;
//import com.spfood.oms.orderinfosyn.thread.OrderPaySynWorkThread;
//
//@Service
//public class OrderPaysSynImpl {
//
//        @Autowired
//        private TaskExecutor taskExecutor;
//        @Autowired
//        private OrderManagerService orderManagerService;
//        @Autowired
//        private  SendOrderFms sendOrderFms;
//        List<String> ordernos = null;
//        private static final OrderSynNode node = new OrderSynNode(1,"first");
//        public void sendOrderPaysToFms() {
//            try{
//                //System.out.println("有权限进行信息同步123");
//            //查询未支付订单数量
//            Long orderTotal = orderManagerService.getOrderTempCountByStatus(OrderConstant.PENGDING_DELIVER_STATUS);
//            //判断查询的条数大于0执行信息同步方法
//            if(orderTotal>0){
//                if(orderTotal<=SendOrderAmount.MAX_ORDER_AMOUNT_INTEGER){ //查询已支付订单数量小于或等于最大提交数量执行方法
//                     //设置单线程模式下订单查询的条件
//                    sendOrderBySingleThread(orderTotal);
//                }else {
//                	//使用多线程模式发送订单
//                    sendOrderByMulitThread(orderTotal);
//                    } 
//                }
//            }catch(Exception e) {
//                e.printStackTrace();
//            }
//           
//        }
//        
//         // 使用zk节点唯一的方式确保只有一台服务器调用改定时任务
//       /* @Override
//        public void  sendOrderPaysToFms() {
//            
//            String ZKServers ="10.8.48.230:2181,10.8.48.230:2182,10.8.48.230:2183";
//            ZkClient zkClient = ZkUtils.createZkClient(ZKServers);      
//            //有创建节点的权限就执行信息同步的方法
//            try {
//                  String path = zkClient.create("/firstNode", node, CreateMode.PERSISTENT);       
//                  sendOrderPaysToFmsByZK();
//                zkClient.delete("/firstNode");
//                
//            } catch (Exception e2) {
//                System.out.println("没有执行权限或调度异常");
//            }      
//        }*/
//        
//         //已支付订单数量，小于设置的最大订单发送数量，使用该单线程方法发送订单
//        private void sendOrderBySingleThread(Long orderTotal) {
//            List<OrderPay> orderPays = new ArrayList<OrderPay>();
//            //循环计数器
//            int count=0;
//            //循环次数
//            int cycleIndex = SendOrderAmount.MAX_ORDER_AMOUNT_INTEGER/SendOrderAmount.ORDER_AMOUNT;
//            //循环执行，如果当前的查询条数为设置的查询条数则再次循环查询
//            while ((orderPays.size()<=SendOrderAmount.ORDER_AMOUNT)&&count<=cycleIndex){
//                orderPays = new ArrayList<OrderPay>();
//                List<String> ordernos = orderManagerService.getOrderTempOrderNosByStatus(OrderConstant.PENGDING_DELIVER_STATUS);
//                //将每次查询量的数据添加给orderpays,并更改支付信息的是否已发送fms状态,且删除掉对应订单编码的临时表数据
//                for (int i=0 ;i<SendOrderAmount.ORDER_AMOUNT ;i++ ) {
//                        if(ordernos !=null &&ordernos.size()>0) {
//                            String orderno = ordernos.remove(0);
//                            // 设置支付信息是否已发送状态为已发送状态
//                            OrderPay orderPay = orderManagerService.getOrderPayByOrderNo(orderno);
//                            orderPays.add(orderPay);
//                            //并更改支付信息的是否已发送fms状态
//                            orderManagerService.updateOrderPayIsSendFms(orderno);
//                            //删除掉对应订单编码的临时表数据
//                            orderManagerService.deleteOrderTempByOrderNo(orderno);
//                        }
//                     }
//                    //发送订单信息到wms消息队列
//                    sendOrderFms.sendOrderPaysToFMS(orderPays, MqDestinationList.FMS_DESTINATION);
//                    count++;
//            }   
//        }
//        
//        
//        
//         //线程执行订单信息同步
//         //@param orderTotal 订单总数量
//        private void sendOrderByMulitThread(Long orderTotal){
//            //通过总订单数量得到执行订单线程的数量
//            int threadAmount = orderTotal.intValue()%SendOrderAmount.THREAD_ORDER_AMOUNT==0?
//                    orderTotal.intValue()/SendOrderAmount.THREAD_ORDER_AMOUNT: 
//                        orderTotal.intValue()/SendOrderAmount.THREAD_ORDER_AMOUNT+1;
//            
//            List<String> taskOrdernos=new ArrayList<String>();
//            //订单同步信息线程列表
//            List<OrderPaySynWorkThread> threads = new ArrayList<OrderPaySynWorkThread>();
//            List<String> ordernos = orderManagerService.getOrderTempOrderNosByStatus(OrderConstant.PENGDING_DELIVER_STATUS);
//            for (int currentPage = 1; currentPage <= threadAmount; currentPage++) {      
//                taskOrdernos = new ArrayList<String>();
//                for (int i = 0; i <SendOrderAmount.THREAD_ORDER_AMOUNT; i++) {
//                    if(ordernos !=null &&ordernos.size()>0) {
//                        taskOrdernos.add(ordernos.remove(0));
//                    }
//                }
//                //创建任务对象
//                OrderPaySynTask orderPaySynTask = new OrderPaySynTask(taskOrdernos,currentPage);
//                //根据任务对象创建任务线程
//                OrderPaySynWorkThread thread = new OrderPaySynWorkThread(orderPaySynTask);
//                threads.add(thread);
//            }
//            int i =1;
//            //依次启动订单同步信息线程
//            for (OrderPaySynWorkThread orderPaySynWorkThread : threads) {
//                Thread thread = new Thread(orderPaySynWorkThread);
//                taskExecutor.execute(thread);
//               // System.out.println("启动线程数"+i++);
//            }
//                
//            
//        }
//            
//    }
//
