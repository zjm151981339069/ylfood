package com.spfood.oms.orderinfosyn.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.orderinfosyn.intf.OrderInfoSynService;
import com.spfood.oms.orderinfosyn.intf.OrderStatusSynService;
import com.spfood.oms.orderinfosyn.manager.OrderStatusSynManager;

@Service
public class OrderStatusSynServiceImpl implements OrderStatusSynService {
	
	@Autowired
	private OrderStatusSynManager orderStatusSynManager;
	
	@Autowired
	private OrderManagerService orderManagerService;
	
	@Autowired
	private OrderInfoSynService orderInfoSynService;
	
	@Override
	public boolean turnToUnPaied(List<String> orderNos) {

		if (orderStatusSynManager.updateStausToUnPaied(orderNos)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean turnToUnPaied(String orderNo) {
		if (orderStatusSynManager.updateStausToUnPaied(orderNo)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean tunrToHasPaied(List<String> orderNos) {

		if (orderStatusSynManager.updateStausToHasPaied(orderNos)) {
			return true;
		}
		return false;
		
	}
	@Override
	public boolean tunrToHasPaied(String orderNo) {
		
		if (orderStatusSynManager.updateStausToHasPaied(orderNo)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean turnToForSorting(List<String> orderNos) {
		if (orderStatusSynManager.updateStausToForSorting(orderNos)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean turnToForSorting(String orderNo) {
		if (orderStatusSynManager.updateStausToForSorting(orderNo)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean turnToPackaged(List<String> orderNos) {
		if (orderStatusSynManager.updateStausToPackaged(orderNos)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean turnToPackaged(String orderNo) {
		if (orderStatusSynManager.updateStausToPackaged(orderNo)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean turnToWaitSite(List<String> orderNos) {
		if (orderStatusSynManager.updateStausToWaitSite(orderNos)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean turnToWaitSite(String orderNo) {
		if (orderStatusSynManager.updateStausToWaitSite(orderNo)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean turnToFinished(List<String> orderNos) {

		if (orderStatusSynManager.updateStatusToFinished(orderNos)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean turnToFinished(String orderNo) {
		if (orderStatusSynManager.updateStatusToFinished(orderNo)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean turnThrowAway(List<String> orderNos) {
		return orderStatusSynManager.updateStatusToThrowAway(orderNos);
	}
	
	@Override
	public boolean turnThrowAway(String orderNo) {
		return orderStatusSynManager.updateStatusToThrowAway(orderNo);
	}
	
//	@Override
//	public boolean turnToCancel(String orderNo,String operator,String operatorCode) {
//		
//		return orderStatusSynManager.updateStatusToCancel(orderNo, operator, operatorCode);
//	}
	
}
