package com.spfood.wos.workOrder.dao.impl;


import java.util.List;

import org.springframework.stereotype.Component;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.dao.HouseOrderCommodityDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
import com.spfood.wos.workOrder.utils.MybatisList;

@Component
public class HouseOrderCommodityDaoImpl extends BaseDaoImpl<HouseOrderCommodity> implements
		HouseOrderCommodityDao {

	@Override
	public int updateTaskCodeInBatch(
			List<HouseOrderCommodity> houseOrderCommList) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_COMMODITY_TASKCODE_CODE, houseOrderCommList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("wos.houseOrderCommodity.dao.updateTaskCodeInBatch", e, new Object[]{this.getSqlNamespace(),getSqlName(MybatisList.UPDATE_COMMODITY_TASKCODE_CODE), houseOrderCommList});
		}
	}

}
