package com.spfood.oms.orderpay.impl;

import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.OrderPayByAliPayService;
import com.spfood.oms.orderpay.intf.domain.AlipayQueryResult;
import com.spfood.oms.orderpay.intf.domain.AlipayResult;
import com.spfood.oms.orderpay.manage.AlipayQueryManager;
import com.spfood.oms.orderpay.manage.OrderPayByAlipayManager;

@Service
public class OrderPayByAliPayServiceImpl extends HttpServlet implements
        OrderPayByAliPayService {

    private static final long serialVersionUID = 1L;
    @Autowired
    private OrderPayByAlipayManager orderPayByAlipayManager;
    @Autowired
    private AlipayQueryManager alipayQueryManager;
    Logger logger = Logger.getLogger(OrderPayByAliPayServiceImpl.class); 
    /**
     * 手机网页 网站支付
     */
    @Override
    public String orderPayByAliPayYdAndPc_Pay(Order order) {
        if (order != null) {
           return orderPayByAlipayManager.orderPayByAlipay(order);
        } else { // 参数异常则跳转到错误页面
            return null;
        }

    }

    /**
     * 支付宝二维码支付
     */
    @Override
    public String orderPayByAliPayQRCodePay(Order order) {
        if (order != null) {
          return  orderPayByAlipayManager.QRCodePay(order);
        } else { 
            return null;
        }
    }
    
    /**
     * 支付宝回转页面数据获取
     * @param alipayReturnParms
     * @return
     */
    @Override
    public AlipayResult orderPayByAlipayReturn(Map alipayReturnParms){

        if (alipayReturnParms != null) {
          return  orderPayByAlipayManager.orderPayByAlipayReturn(alipayReturnParms);
        }
        return null;
    }
    /**
     * 支付宝通知验证
     * @param alipayNotifyParms
     * @return 支付宝支付结果
     */
    @Override
    public AlipayResult orderPayByAlipayNotify(Map alipayNotifyParms){

        if (alipayNotifyParms != null) {
           return orderPayByAlipayManager.orderPayByAlipayNotify(alipayNotifyParms);
        }
        return null;
    }

    @Override
    public AlipayQueryResult orderPayQuery(String out_trade_no, String trade_no) {
        if((out_trade_no!=null&&!"".equals(out_trade_no))||(trade_no!=null&&!"".equals(trade_no))){
            logger.info("********************支付宝支付查询****************************");
            return alipayQueryManager.orderPayQuery(out_trade_no, trade_no);
        }
        return null;
    }

}
