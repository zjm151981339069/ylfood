package com.spfood.wos.workOrder.util;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;


/**
 * 传递的参数String转Date
 * @author lizekun
 * 
 */
public class OrderDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		
		//将字符串转成日期类型
		return DateUtils.StringToDate(source);
	}

}
