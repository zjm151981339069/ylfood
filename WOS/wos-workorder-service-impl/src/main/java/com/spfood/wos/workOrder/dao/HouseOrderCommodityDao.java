package com.spfood.wos.workOrder.dao;



import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
public interface HouseOrderCommodityDao extends BaseDao<HouseOrderCommodity> {

	int updateTaskCodeInBatch(List<HouseOrderCommodity> houseOrderCommList);

	//void saveOrderCommodity(List<OrderCommodity> orderCommList);

}
