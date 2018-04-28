package com.spfood.wos.workOrder.impl;
/**
 * 入库订单实现类
 */

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.intf.HouseOrderService;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.intf.searchCriteria.HouseOrderSearchCriteria;
import com.spfood.wos.workOrder.manager.HouseOrderManager;
import com.spfood.wos.workOrder.utils.HouseOrderStatus;
public class HouseOrderServiceImpl implements HouseOrderService {

	@Autowired
	private HouseOrderManager houseOrderManager;
	private static final Logger logger = Logger.getLogger(HouseOrderServiceImpl.class);
	@Override
	public void saveOrder(HouseOrder houseOrder) {
		houseOrderManager.saveOrder(houseOrder);		
	}
    @Override
    public List<HouseOrderTemp> getTempOrder() {
        return houseOrderManager.getTempOrder();
    }
    @Override
    public HouseOrder getOrder(String orderno) {
        return houseOrderManager.getOrder(orderno);
    }
	@Override
	public PageInfo<HouseOrder> getHouseOrderListByCriteria(
			HouseOrderSearchCriteria houseOrderCriteria) {
		return houseOrderManager.getHouseOrderListByCriteria(houseOrderCriteria);
	}
	/**修改订单状态已拆分*/
	@Override
	public boolean updateToHasSorting(List<String> orderNos) {
		
		List<HouseOrder> houseOrderList = houseOrderManager.queryHouseOrderListByOrderNos(orderNos);
		for (HouseOrder houseOrder : houseOrderList) {
			houseOrder.setStatus(HouseOrderStatus.HAS_SORTING.getValue());
		}
		houseOrderManager.updateHouseOrderStatus(houseOrderList);
		houseOrderManager.deleteOrderTempByOrderNo(orderNos);
		return true;
	}
	
	/**修改订单状态拆分失败*/
	@Override
	public boolean updateToAbnormalStatus(List<String> orderNos) {
		List<HouseOrder> houseOrderList = houseOrderManager.queryHouseOrderListByOrderNos(orderNos);
		for (HouseOrder houseOrder : houseOrderList) {
			houseOrder.setStatus(HouseOrderStatus.ABNORMAL_STATUS.getValue());
		}
		houseOrderManager.updateHouseOrderStatus(houseOrderList);
		return true;
	}
	@Override
	public List<HouseOrder> getOrderList(List<String> orderNos) {
		return houseOrderManager.getOrderListByOrderNos(orderNos);
	}
	@Override
	public void deleteOrderTempByOrderNo(List<String> orderNos) {
		try {
			houseOrderManager.deleteOrderTempByOrderNo(orderNos);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("wos.service.deleteOrderTempByOrderNo", e,orderNos);
		}
		
	}
}
