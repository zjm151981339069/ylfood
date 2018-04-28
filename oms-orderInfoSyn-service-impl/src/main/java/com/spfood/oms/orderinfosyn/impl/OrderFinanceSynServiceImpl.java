package com.spfood.oms.orderinfosyn.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.orderinfosyn.intf.OrderFinanceSynService;
import com.spfood.oms.orderinfosyn.manager.OrderBillSynFms;
import com.spfood.oms.orderinfosyn.manager.OrderPaySynFms;
import com.spfood.oms.orderinfosyn.manager.SendOrderMessageQueueManager;
import com.spfood.oms.orderinfosyn.manager.SendOrderRelativeInfoToFmsManager;
import com.spfood.oms.orderinfosyn.utils.OrderDefaultStatus;
@Service
public class OrderFinanceSynServiceImpl implements OrderFinanceSynService{
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private SendOrderMessageQueueManager sendOrderMessageManage;
    @Autowired
    private OrderFinanceSynService orderFinanceSynService;
    
    @Autowired
    private SendOrderRelativeInfoToFmsManager sendOrderRelativeInfoToFmsManager;
    
    
    
    
    private static final Logger logger = Logger.getLogger(OrderFinanceSynServiceImpl.class);
    @Override
    public boolean orderPaySyn(OrderPay orderPay) {
//        return orderPaySynFms.orderPaySynFms(orderPay);
    	return false;
    }
    
    //批量同步
  	@Override
  	public boolean orderPaySyn(List<OrderPay> orderPayList) {
  		 return sendOrderMessageManage.sendOrderPayInfoToFMS(orderPayList);
  	}

  	//发票同步
    @Override
    public boolean orderBillSyn(String orderNo, Integer billType,
            Integer billTitle, String billContent,BigDecimal orderAmount) {
    	
        try {
        	//z20190329
        	OrderBill orderBill = new OrderBill();
        	orderBill.setOrderNo(orderNo);
        	orderBill.setBillType(billType);
        	orderBill.setBillTitle(billTitle);
        	orderBill.setBillContent(billContent);
        	orderBill.setOrderAmount(orderAmount);
        	return sendOrderRelativeInfoToFmsManager.sendOrderBillInfoToFms(orderBill);
        	
		} catch (Exception e) {
			
			logger.error("order bill info synchronization failed", e);
			
			throw new BizException("oms.orderBillSyn.Impl.orderBillSyn", e);
		}
       
    }
  
    
    /**
     * 订单支付信息同步到FMS
     */
    @Override
	public void sendOrderPayToFms() {

    	sendOrderRelativeInfoToFmsManager.sendOrderPayInfoToFms();
	}
}
