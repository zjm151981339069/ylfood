package com.spfood.oms.orderpay.pay.acppay.sdk;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.jboss.logging.Logger;

/**
 * 重要：联调接入的时候请务必仔细阅读注释！！！
 * 
 * 功能：从应用的classpath下加载acp_sdk.properties属性文件并将该属性文件中的键值对赋值到SDKConfig类中 <br>
 * 
 */
public class AutoLoadServlet extends HttpServlet {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(AutoLoadServlet.class);
	@Override
	public void init() throws ServletException {		
	    logger.info("启动加载银联支付相关相关配置");
		SDKConfig.getConfig().loadPropertiesFromSrc();
		
		super.init();
	}
}
