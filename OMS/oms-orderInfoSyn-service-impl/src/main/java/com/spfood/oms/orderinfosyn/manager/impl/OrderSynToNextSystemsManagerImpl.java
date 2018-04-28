package com.spfood.oms.orderinfosyn.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderTempDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.orderinfosyn.constant.OrderSynToNextSystemsOneTimeAmount;
import com.spfood.oms.orderinfosyn.manager.OrderStatusSynManager;
import com.spfood.oms.orderinfosyn.manager.OrderSynToNextSystemsManager;
import com.spfood.oms.orderinfosyn.manager.SendOrderMessageQueueManager;
import com.spfood.oms.orderinfosyn.utils.OrderDefaultStatus;
@Service
@Transactional
public class OrderSynToNextSystemsManagerImpl implements OrderSynToNextSystemsManager{
    @Autowired
    private SendOrderMessageQueueManager sendOrderMessageQueueManager;
    @Autowired
    private OrderSynToNextSystemsOneTimeAmount orderSynToNextSystemsOneTimeAmount;
    @Autowired
    private OrderTempDao orderTempDao; 
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderStatusSynManager orderStatusSynManager;
    Logger logger = Logger.getLogger(OrderSynToNextSystemsManagerImpl.class);
    @Override
    public Boolean orderSynToNextSystems(List<Order> orders) {
        Boolean sendSuccess=true;
        try {
            sendOrderMessageQueueManager.sendOrderImformToFMS(orders);
            sendOrderMessageQueueManager.sendOrderImformToTS(orders);
            sendOrderMessageQueueManager.sendOrderImformToWOS(orders); 
        } catch (Exception e) {
            sendSuccess=false;
        }
        return sendSuccess;
    }

    @Override
    public Boolean orderSynToNextSystems(Order order) {
        List<Order> orders = new ArrayList<Order>();
        orders.add(order);
        Boolean sendSuccess=true;
        try {
            sendOrderMessageQueueManager.sendOrderImformToFMS(orders);
            sendOrderMessageQueueManager.sendOrderImformToTS(orders);
            sendOrderMessageQueueManager.sendOrderImformToWOS(orders);  
        } catch (Exception e) {
            sendSuccess=false;
        }
        return sendSuccess;
    }

    @Override
    public void quartzOrderSynToNextSystems() {
        logger.info("*******************  quartzOrderSynToNextSystems    Start     *******************************");
        try {
    		OrderTemp orderTemps = new OrderTemp();
    		orderTemps.setStatus((OrderDefaultStatus.HASPAIED_STATUS.getValue()));
            //获取临时订单编号列表
            List<String> orderTempCodes = new ArrayList<String>();
            List<OrderTemp> selectList = orderTempDao.selectList(orderTemps);
    		for (OrderTemp temp : selectList) {
    			orderTempCodes.add(temp.getOrderNo());
    		}
            Integer oneTimeAmount = orderSynToNextSystemsOneTimeAmount.getOneTimeAmount();
            int orderTempSize = orderTempCodes.size();
            logger.info("该次定时调度临时订单表数量****"+orderTempSize);
            int index=0;   
            if(orderTempSize>0){
                logger.info("****执行发送订单****");
                //获取循环次数
                Integer cyclicNum = orderTempSize%oneTimeAmount==0?orderTempSize/oneTimeAmount:orderTempSize/oneTimeAmount+1;         
                do {
                    logger.info("当前定时发送订单循环次数***"+(index+1));
                    List<String> orderCodes = new ArrayList<String>(); 
                    //创建订单编号列表
                    for (int i = 0; i < oneTimeAmount; i++) {
                        try {
                            String code = orderTempCodes.get(index*oneTimeAmount+i);
                            orderCodes.add(code);
                        } catch (Exception e) {
                            //溢出结束循环
                            break;
                        }
                    }
                    //获取订单列表
                    List<Order> orderList = orderDao.selectOrderAndCommodity(orderCodes);
                    logger.info("查询到订单主表的订单数量****"+(orderList==null?0:orderList.size()));
                    //发送订单到下级系统
                    Boolean isSuccessSendMessage = orderSynToNextSystems(orderList);
                    //如果订单发送到下级系统更改订单的状态为待发货
                    if(isSuccessSendMessage&&orderList!=null&&orderList.size()>0){
                        logger.info("更改订单主表和临时表的状态为待发货");
                        List<String> orderCodeList = new ArrayList<String>();
                        List<OrderTemp> orderTempss = new ArrayList<OrderTemp>();
                        for (Order order : orderList) {
                            orderCodeList.add(order.getOrderNo());
                            //临时订单表信息
                            OrderTemp orderTemp = new OrderTemp();
                            orderTemp.setOrderNo(order.getOrderNo());
                            orderTemp.setStatus(OrderDefaultStatus.FORSORTING_STATUS.getValue());
                            orderTempss.add(orderTemp);
                        }
                        //更改主表和临时表的状态为待发货
            			for (Order order : orderList) {
            				order.setStatus(OrderDefaultStatus.FORSORTING_STATUS.getValue());
            			}
                        orderStatusSynManager.updateStausToForSorting(orderCodeList);
                        orderTempDao.updateOrderStatus(orderTempss);
                    }
                } while (++index<cyclicNum);            
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException("oms.orderInfoSyn.sendOrderInformToNextSystems",e,"order informSyn encounter exception");
        }
        logger.info("*******************  quartzOrderSynToNextSystems    end     *******************************");
    }

}
