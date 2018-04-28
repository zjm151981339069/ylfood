package com.spfood.wos.workOrder.intf;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.intf.searchCriteria.HouseOrderSearchCriteria;
/**
 * 保存订单
 * @author Administrator
 *
 */
public interface HouseOrderService {
    
	/**
	 * 从队列中取得订单进行入库操作
	 * @param houseOrder
	 */
	public void saveOrder(HouseOrder houseOrder);
	
	/**
	 * 查询全部订单
	 * @param HouseOrderCriteria
	 * @return
	 */
	public PageInfo<HouseOrder> getHouseOrderListByCriteria(HouseOrderSearchCriteria houseOrderCriteria);
	
	/**
	 * 获取临时订单表的临时订单列表并且5小时内的临时表
	 * @return 临时订单列表
	 */
	public List<HouseOrderTemp> getTempOrder();
	
	/**
	 * 根据订单编号获取订单对象
	 * @param orderNo 订单编号
	 * @return 订单对象
	 */
	public HouseOrder getOrder(String orderNo);
	
	/**
	 * 修改订单已分拣
	 * @param orderNo
	 * @return
	 */
	public	boolean updateToHasSorting(List<String> orderNos);

	/**
	 * 修改订单状态为分拣失败
	 * @param orderno
	 * @return
	 */
	public	boolean updateToAbnormalStatus(List<String> orderNos);	
	
	/***
	 *批量查询订单
	 * @param orderNos
	 * @return
	 */
	public List<HouseOrder> getOrderList(List<String> orderNos);
	
	/**
	 * 批量删除临时表
	 * @param orderNoList
	 */
	public void deleteOrderTempByOrderNo(List<String> orderNos);
}
