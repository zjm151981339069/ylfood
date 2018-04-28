package com.spfood.wos.workOrder.dao;

import java.util.List;






import com.spfood.kernel.dao.BaseDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
public interface HouseOrderTempDao extends BaseDao<HouseOrderTemp> {

	//void saveHouseOrderTemp(HouseOrder houseOrder);

	List<HouseOrderTemp> getTempOrder();
	
	//批量修改临时表状态
	void updateBatch(List<HouseOrderTemp> houseOrderTempList);

	void updateStatusBatch(List<String> orderNos, Integer status);

	//批量删除临时表
	void deleteByOrderNoInBatch(List<String> orderNos);

}
