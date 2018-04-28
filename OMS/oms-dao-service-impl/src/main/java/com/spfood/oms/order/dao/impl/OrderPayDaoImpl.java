package com.spfood.oms.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderPayDao;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.utils.MybatisList;

@Repository
public class OrderPayDaoImpl extends BaseDaoImpl<OrderPay> implements OrderPayDao{

	@Override
	public List<OrderPay> selectByOrderNos(List<String> orderNos) {
		try {
			return sqlSessionTemplate.selectList(MybatisList.SELECT_ORDERPAY_ORDERNOS, orderNos);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("oms.orderPay.dao.selectByOrderNos", e, new Object[] { this.getSqlNamespace(), getSqlName(MybatisList.SELECT_ORDERPAY_ORDERNOS), orderNos });
		}
	}

	@Override
	public int updateByOrderNoSelective(OrderPay orderPay) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_ORDERPAY_ORDERNO_SELECTIVE, orderPay);
		} catch (Exception e) {
			throw new BizException("oms.orderPay.dao.updateByOrderNoSelective", e, new Object[] { this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_ORDERPAY_ORDERNO_SELECTIVE), orderPay.toString() });
		}
	}

	@Override
	public int updateOrderPayIsPay(List<OrderPay> orderPayList) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_ORDERPAY_ISPAY, orderPayList);
		} catch (Exception e) {
			throw new BizException("oms.orderPay.dao.updateOrderPayIsPay", e, orderPayList);
		}
	}

	@Override
	public void updateBatchIsSendFms(List<OrderPay> orderPayList) {
		try {
			sqlSessionTemplate.update(MybatisList.UPDATE_ORDERPAY_ISSENDFMS, orderPayList);
		} catch (Exception e) {
			throw new BizException("oms.orderPay.dao.updateBatchIsSendFms", e, orderPayList);
		}
	}

	
	/**
	 * 查询要同步到FMS的订单支付信息(查询订单表中订单状态为3的数据) z20170329
	 */
	@Override
	public List<OrderPay> getUnSynOrderPayInfo() {
		
		return sqlSessionTemplate.selectList(MybatisList.GET_UNSYN_ORDERPAY_INFO);
	}

	
}
