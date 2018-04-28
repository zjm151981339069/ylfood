package com.spfood.wos.workOrder.manager;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.intf.searchCriteria.HouseOrderSearchCriteria;
/**
 * 
 * @author Administrator
 *
 */
public interface HouseOrderManager {

	//保存订单到wos
	void saveOrder(HouseOrder houseOrder);
	
	//根据状态得到临时表
	public List<HouseOrderTemp> getTempOrder();
	
	//获取订单
	public HouseOrder getOrder(String orderNo);
	
	//分页展示
	PageInfo<HouseOrder> getHouseOrderListByCriteria(HouseOrderSearchCriteria houseOrderCriteria);
	
	//修改订单状态
	int updateHouseOrderStatus(HouseOrder houseOrder);
	
	//根据订单号批量查询
	public List<HouseOrder> queryHouseOrderListByOrderNos(List<String> orderNos);

	//批量修改订单状态
	void updateHouseOrderStatus(List<HouseOrder> houseOrderList);
	
	//批量修改临时表状态
	void updateHouseOrderTempStatus(List<HouseOrderTemp> houseOrderTempList);

	//根据状态批量修改临时表
	void updateHouseOrderTempByStatus(List<String> orderNos,Integer status);

	//批量查询订单
	List<HouseOrder> getOrderListByOrderNos(List<String> orderNos);

	//批量删除临时表
	void deleteOrderTempByOrderNo(List<String> orderNos);
}
