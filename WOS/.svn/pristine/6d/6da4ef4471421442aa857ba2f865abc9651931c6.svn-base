package com.spfood.wos.workOrder.dao.impl;


import java.util.List;

import org.springframework.stereotype.Component;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.dao.HouseOrderDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.utils.MybatisList;

@Component
public class HouseOrderDaoImpl extends BaseDaoImpl<HouseOrder> implements HouseOrderDao {

	@Override
	public int updateByHouseOrderNoSelective(HouseOrder houseOrder) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_HOUSEORDER_SELECTIVE, houseOrder);
		} catch (Exception e) {
			throw new BizException("wos.houseOrder.dao.updateByHouseOrderNoSelective", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_HOUSEORDER_SELECTIVE), houseOrder.toString()});
		}
	}

	//批量查询
	@Override
	public List<HouseOrder> selectByOrderNos(List<String> orderNos) {
		try {
			return sqlSessionTemplate.selectList(MybatisList.SELECT_HOUSEORDERLIST, orderNos);
		} catch (Exception e) {
			throw new BizException("wos.houseOrder.dao.selectByOrderNos", e, new Object[]{this.getSqlNamespace(),getSqlName(MybatisList.SELECT_HOUSEORDERLIST), orderNos});
		}
	}

	//批量修改
	@Override
	public void updateBatch(List<HouseOrder> houseOrderList) {
		try {
			sqlSessionTemplate.update(MybatisList.UPDATE_HOUSEORDER_BATCH, houseOrderList);
		} catch (Exception e) {
			throw new BizException("wos.houseOrder.dao.updateBatch", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_HOUSEORDER_BATCH), houseOrderList});
		}
	}
	
	//根据订单号查询是否存在数据
	@Override
	public Long selectNumByorderNo(String orderNo) {
		
		return (Long)sqlSessionTemplate.selectOne(MybatisList.SELECT_NUM_BY_ORDERNO, orderNo);
	}

	//批量查询订单及商品
	@Override
	public List<HouseOrder> selectOrderList(List<String> orderNos) {
		return sqlSessionTemplate.selectList(MybatisList.SELECT_LIST, orderNos);
	}
}
