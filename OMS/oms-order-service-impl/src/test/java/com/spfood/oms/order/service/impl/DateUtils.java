package com.spfood.oms.order.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换的工具类
 * @author lizekun
 *
 */
public class DateUtils {
	
	public static String dateToString(Date date){
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	public static Date stringToDate(String source){
		if (source == null) {
			return null;
		}
		if (source.contains("-")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return format.parse(source);
			} catch (ParseException e) {
			}
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				return format.parse(source);
			} catch (ParseException e) {
			}
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format.parse(source);
			} catch (ParseException e) {
			}
			return null;
		}else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				return format.parse(source);
			} catch (ParseException e) {
			}
			format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			try {
				return format.parse(source);
			} catch (ParseException e) {
			}
			format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				return format.parse(source);
			} catch (ParseException e) {
			}
			return null;
		}
	}
	
	
}
