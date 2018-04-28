package com.spfood.oms.orderinfosyn.intf;

import java.util.List;

/**
 * 订单状态实时更新同步
 * @author Administrator
 * @param orderno 订单编号
 */

public interface OrderStatusSynService {

	/**
	 *null--待付款(null--1)
	 */
	boolean turnToUnPaied(List<String> orderNos);
	boolean turnToUnPaied(String orderNo);
	/**
	 * 待付款--已付款(1--2)
	 */
	boolean tunrToHasPaied(List<String> orderNos);
	boolean tunrToHasPaied(String orderNo);
	/**
	 * 已付款--待发货(2--3)
	 */
	boolean turnToForSorting(List<String> orderNos);
	boolean turnToForSorting(String orderNo);
	/**
	 * 待发货--已发货(3--4)
	 */
	boolean turnToPackaged(List<String> orderNos);
	boolean turnToPackaged(String orderNo);
	
	/**
	 * 已发货--待自提(4--5)
	 */
	boolean turnToWaitSite(List<String> orderNos);
	boolean turnToWaitSite(String orderNo);
	
	/**
	 * 待自提--已完成(5--6)
	 */
	boolean turnToFinished(List<String> orderNos);
	boolean turnToFinished(String orderNo);
	
	/**
	 * 变成丢弃状态
	 * @param orderNo
	 * @return
	 */
	boolean turnThrowAway(List<String> orderNos);
	boolean turnThrowAway(String orderNo);
	
	/**
	 * 待付款--已取消(1--0)
	 * @param orderNo
	 * @param operator
	 * @param operatorCode
	 * @return
	 */
//	boolean turnToCancel(String orderNo,String operator,String operatorCode);
}
