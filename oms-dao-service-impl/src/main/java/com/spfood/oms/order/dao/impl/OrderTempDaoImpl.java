package com.spfood.oms.order.dao.impl;

import java.util.List;




import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderTempDao;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.utils.MybatisList;

@Repository
public class OrderTempDaoImpl extends BaseDaoImpl<OrderTemp> implements OrderTempDao{

	@Override
	public void deleteByOrderNoInBatch(List<String> orderNoList) {
		try {
			sqlSessionTemplate.delete(MybatisList.DELETE_ORDERTEMP_ORDERNOS, orderNoList);
		} catch (Exception e) {
			throw new BizException("oms.orderTemp.dao.deleteByOrderNoInBatch", e, new Object[] { this.getSqlNamespace(), getSqlName(MybatisList.DELETE_ORDERTEMP_ORDERNOS), orderNoList });
		}
	}

	@Override
	public int updateByOrderNoSelective(OrderTemp orderTemp) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_ORDERTEMP_ORDERNO_SELECTIVE, orderTemp);
		} catch (Exception e) {
			throw new BizException("oms.orderTemp.dao.updateByOrderNoSelective", e, new Object[] { this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_ORDERTEMP_ORDERNO_SELECTIVE), orderTemp.toString() });
		}
	}

	@Override
	public int updateOrderStatus(List<OrderTemp> orderTempList) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_ORDERTEMP_BY_ORDERTEMPLIST, orderTempList);
		} catch (Exception e) {
			throw new BizException("oms.orderTemp.dao.updateOrderStatus", e, new Object[] { this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_ORDERTEMP_BY_ORDERTEMPLIST), orderTempList });
		}
		
	}
}
