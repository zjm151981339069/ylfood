
package com.spfood.wos.workOrder.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.spfood.kernel.exception.BizException;


/**
 * 
 * properties配置文件操作工具类
 *
 */
public class PropertiesUtils {
		
	private static final Logger logger = Logger.getLogger(PropertiesUtils.class);
	
	public static String getProperties(String filePath,String key){
		
		Properties prop = new Properties();
		InputStream is = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);  
		try {
			
			prop.load(is);
			return prop.getProperty(key);
			
		} catch (IOException e) {
			logger.error("读取properties文件失败",e);
			throw new BizException("wos.PropertiesUtils.getProperties", e.getCause(),"");
		}
	}
	
	public static void setProperties(String filePath,String key,String value){
		
		Properties prop = new Properties();
		InputStream is = null;
		OutputStream os = null;
		try {
			
			is = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);  
			prop.load(is);
			os = new FileOutputStream(new File(PropertiesUtils.class.getClassLoader().getResource(filePath).toURI()));
			prop.setProperty(key, value);
			prop.store(os, "modify collectCount to lastest value");
		} catch (Exception e) {
			logger.error("设置properties文件失败",e);
			throw new BizException("wos.PropertiesUtils.setProperties", e.getCause(),"");
		}
	}
	
}
