package com.spfood.oms.orderpay.manage;

import java.awt.image.BufferedImage;
import java.util.Map;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.AlipayResult;

public interface OrderPayByAlipayManager {
    
    public String orderPayByAlipay(Order order);
  
    public String QRCodePay(Order order);
    
    public AlipayResult orderPayByAlipayReturn(Map alipayReturnParms);
    
    public AlipayResult orderPayByAlipayNotify(Map alipayNotifyParms);
    
}
