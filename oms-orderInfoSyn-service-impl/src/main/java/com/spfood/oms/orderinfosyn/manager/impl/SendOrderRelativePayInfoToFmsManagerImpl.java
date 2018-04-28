package com.spfood.oms.orderinfosyn.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.orderinfosyn.manager.SendOrderMessageQueueManager;
import com.spfood.oms.orderinfosyn.manager.SendOrderRelativeInfoToFmsManager;


/**
 * 
 * 订单相关信息同步到FMS 比如：订单支付信息、发票信息等 z20170329
 * 
 */
@Service
@Transactional
public class SendOrderRelativePayInfoToFmsManagerImpl implements SendOrderRelativeInfoToFmsManager{

	private static final Logger logger = Logger.getLogger(SendOrderRelativePayInfoToFmsManagerImpl.class);
	
	@Autowired
	private SendOrderMessageQueueManager sendOrderMessageQueueManager;
	
	@Autowired
	private OrderManagerService orderManagerService;

	@Override
	public void sendOrderPayInfoToFms() {
		
		logger.info("sendOrderPayInfoToFms同步订单支付信息方法开始执行...........");
		
		//查询要同步的支付信息。逻辑：oms_order_temp和oms_order_pay关联查询订单状态为3的支付信息，关联条件orderNo
    	List<OrderPay> orderPays = orderManagerService.getUnSynOrderPayInfo();
    	
    	logger.info("本次任务要同步的订单支付信息集合是否为空："+orderPays==null?false:true);
    	//如果有要同步的支付信息
    	if( orderPays != null && orderPays.size() > 0){
    		
    		logger.info("本次任务要同步的订单支付信息条数："+orderPays.size());
    		
    		//将查询结果同步到FMS
    		boolean flag = sendOrderMessageQueueManager.sendOrderPayInfoToFMS(orderPays);
    		
    		logger.info("本次任务要同步的订单支付信息成功与否："+flag);
    		
    		//如果同步成功，将删除临时表数据，并将oms_order_pay表中的issendfms字段更改
    		//如果同步失败，定时任务下次会继续处理
    		if(flag){
    			
    			List<String> orderNos = new ArrayList<String>();
    			
    			for(OrderPay orderPay:orderPays){
    				
    				if(orderPay != null){
    					
    					orderNos.add(orderPay.getOrderNo());
    				}
    			
    			}
    			
    			//删除临时表数据
    			orderManagerService.deleteOrderTempByOrderNo(orderNos);
    			
    			//将oms_order_pay表中的issendfms字段更改
    			orderManagerService.updateOrderPayIsSendFms(orderNos);
    			
    		}
    		
    		logger.info("本次任务同步订单支付信息完成............");
    	}
	}

	@Override
	public boolean sendOrderBillInfoToFms(OrderBill orderBill) {
		
		logger.info("sendOrderBillInfoToFms同步订单发票信息方法开始执行...........");

		//发送发票信息到FMS
		try {
			
			boolean flag = sendOrderMessageQueueManager.sendOrderBillToFMS(orderBill);
			
			logger.info("sendOrderBillInfoToFms发送发票信息到FMS结果："+flag);
			if(flag){
				logger.info("sendOrderBillInfoToFms更新order表中的发票信息开始..........");
				//将发票信息存入oms_order表中
				boolean updateFlag = orderManagerService.updateOrderBill(orderBill);
				
				logger.info("sendOrderBillInfoToFms发送发票信息到FMS结果："+updateFlag);
				return true;
			}
		} catch (Exception e) {
			
			logger.error("order bill info send to fms failure",e);
		}
		return false;
	}
}
