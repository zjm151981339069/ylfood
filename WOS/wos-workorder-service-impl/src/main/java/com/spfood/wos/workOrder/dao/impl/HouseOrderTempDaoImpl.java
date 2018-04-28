package com.spfood.wos.workOrder.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.dao.HouseOrderTempDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.utils.MybatisList;

@Component
public class HouseOrderTempDaoImpl extends BaseDaoImpl<HouseOrderTemp> implements HouseOrderTempDao {

	@Value("#{configProperties['order.time.transitTime']}")
	public Integer transitTime;
	@Override
	public List<HouseOrderTemp> getTempOrder() {
		
		return sqlSessionTemplate.selectList(MybatisList.GET_ORDERTEMP_LIST,transitTime);
	}

	//批量修改临时表
	@Override
	public void updateBatch(List<HouseOrderTemp> houseOrderTempList) {
		try {
			sqlSessionTemplate.update(MybatisList.UPDATE_HOUSEORDERTEMP_BATCH, houseOrderTempList);
		} catch (Exception e) {
			throw new BizException("wos.houseOrderTemp.dao.updateBatch", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_HOUSEORDERTEMP_BATCH), houseOrderTempList});
		}
	}

	@Override
	public void updateStatusBatch(List<String> orderNos,Integer status) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("orderNos", orderNos);
		sqlSessionTemplate.update(MybatisList.update_houseordertemp_status, map);
		
	}

	//批量删除临时表
	@Override
	public void deleteByOrderNoInBatch(List<String> orderNos) {
		try {
			sqlSessionTemplate.delete(MybatisList.DELETE_ORDERTEMP_ORDERNOS, orderNos);
		} catch (Exception e) {
			throw new BizException("wos.houseOrderTemp.dao.deleteByOrderNoInBatch", e, new Object[] { this.getSqlNamespace(), getSqlName(MybatisList.DELETE_ORDERTEMP_ORDERNOS), orderNos });
		}
		
	}
	
	


}
