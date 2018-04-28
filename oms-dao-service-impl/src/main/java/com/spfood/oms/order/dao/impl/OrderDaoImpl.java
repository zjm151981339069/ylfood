package com.spfood.oms.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.order.utils.MybatisList;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao{

	@Override
	public List<Order> selectByOrderNos(List<String> orderNos) {
		try {
			return sqlSessionTemplate.selectList(MybatisList.SELECT_ORDERLIST_ORDERNOS, orderNos);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.selectByOrderNos", e, new Object[]{this.getSqlNamespace(),getSqlName(MybatisList.SELECT_ORDERLIST_ORDERNOS), orderNos});
		}
	}

	@Override
	public int updateByOrderNoSelective(Order order) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_ORDER_ORDERNO_SELECTIVE, order);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.updateByOrderNoSelective", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_ORDER_ORDERNO_SELECTIVE), order.toString()});
		}
	}

	@Override
	public void updateBatchCancelOrder(List<Order> orderList) {
		try {
			sqlSessionTemplate.update(MybatisList.UPDATE_BATCH_CANCEL_ORDER, orderList);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.updateBatchCancelOrder", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_BATCH_CANCEL_ORDER), orderList});
		}
	}

	@Override
	public Order selectOrderDetailByOrderNo(String orderNo) {
		try {
			return sqlSessionTemplate.selectOne(MybatisList.SELECT_ORDER_DETAIL_ORDERNO, orderNo);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.selectOrderDetailByOrderNo", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.SELECT_ORDER_DETAIL_ORDERNO), orderNo});
		}
	}

	@Override
	public List<Order> selectOrderDetailListByOrderNo(List<String> orderNoList) {
		try {
			return sqlSessionTemplate.selectList(MybatisList.SELECT_ORDER_DETAILLIST_ORDERNO, orderNoList);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.selectOrderDetailListByOrderNo", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.SELECT_ORDER_DETAILLIST_ORDERNO), orderNoList});
		}
	} 

	@Override
	public int cancelOrderDao(String orderNo) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_ORDER_CANCEL, orderNo);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.cancelOrderDao", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_ORDER_CANCEL), orderNo});
		}
	}

	@Override
	public int updateOrderStatus(List<Order> orderList) {
		try {
			return sqlSessionTemplate.update(MybatisList.UPDATE_ORDER_STATUS, orderList);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.updateOrderStatus", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.UPDATE_ORDER_CANCEL), orderList});
		}
	}

	@Override
	public Order selectOne(OrderSearchCriteria orderSearchCriteria) {
		try {
			return sqlSessionTemplate.selectOne(MybatisList.SELECT_ORDER_ORDERNO, orderSearchCriteria);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.selectOne", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.SELECT_ORDER_ORDERNO), orderSearchCriteria});
		}
	}

	@Override
	public Order selectOrderAndCommodity(String orderNo) {
		try {
			return sqlSessionTemplate.selectOne(MybatisList.SELECT_ORDER_AND_COMMODITY, orderNo);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.selectOrderAndCommodity", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.SELECT_ORDER_AND_COMMODITY), orderNo});
		}
	}
	
	@Override
	public List<Order> selectOrderAndCommodity(List<String> orderNoList) {
		try {
			return sqlSessionTemplate.selectList(MybatisList.SELECT_ORDER_AND_COMMODITY_LIST, orderNoList);
		} catch (Exception e) {
			throw new BizException("oms.order.dao.selectOrderAndCommodity", e, new Object[]{this.getSqlNamespace(), getSqlName(MybatisList.SELECT_ORDER_AND_COMMODITY_LIST), orderNoList});
		}
	}

}
