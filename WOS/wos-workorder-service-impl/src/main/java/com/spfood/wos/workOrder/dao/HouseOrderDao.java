package com.spfood.wos.workOrder.dao;



import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;

public interface HouseOrderDao extends BaseDao<HouseOrder>{

	int updateByHouseOrderNoSelective(HouseOrder houseOrder);

	//批量查询
	List<HouseOrder> selectByOrderNos(List<String> orderNos);
	
	//批量修改
	void updateBatch(List<HouseOrder> houseOrderList);
	
	//根据订单号查询是否存在数据
	Long selectNumByorderNo(String orderNo);

	//批量查询订单及关联商品
	List<HouseOrder> selectOrderList(List<String> orderNos);
}
