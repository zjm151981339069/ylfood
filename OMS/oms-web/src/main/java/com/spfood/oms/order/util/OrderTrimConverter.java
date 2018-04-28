package com.spfood.oms.order.util;

import org.springframework.core.convert.converter.Converter;

/**
 * 传递的字符串转换
 * @author lizekun
 * 
 */

public class OrderTrimConverter implements Converter<String, String>{

	@Override
	public String convert(String source) {
		try {
			if(null != source){
				//去掉前后空格
				source = source.trim();
				//本身是空串
				if(!"".equals(source)){
					return source;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}
}
