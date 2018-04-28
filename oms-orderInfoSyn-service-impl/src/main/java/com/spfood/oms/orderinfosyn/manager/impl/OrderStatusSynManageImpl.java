package com.spfood.oms.orderinfosyn.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.dao.OrderTempDao;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.orderinfosyn.LogThread.AddFinishedLog;
import com.spfood.oms.orderinfosyn.LogThread.AddForSortingLog;
import com.spfood.oms.orderinfosyn.LogThread.AddHasPaiedLog;
import com.spfood.oms.orderinfosyn.LogThread.AddPackagedLog;
import com.spfood.oms.orderinfosyn.LogThread.AddThrowAway;
import com.spfood.oms.orderinfosyn.LogThread.AddUnpaied;
import com.spfood.oms.orderinfosyn.LogThread.AddWaitSiteLog;
import com.spfood.oms.orderinfosyn.LogThread.Context;
import com.spfood.oms.orderinfosyn.manager.OrderStatusSynManager;
import com.spfood.oms.orderinfosyn.manager.OrderSynToNextSystemsManager;
import com.spfood.oms.orderinfosyn.utils.OrderDefaultStatus;
import com.spfood.oms.orderinfosyn.utils.OrderLogThread;

//import com.spfood.smsservice.intf.SMSSendService;

@Service("orderStatusSynManage")
@Transactional
public class OrderStatusSynManageImpl implements OrderStatusSynManager {
	@Autowired
	private OrderSynToNextSystemsManager orderSynToNextSystemsManager;
	@Autowired
	private OrderManagerService orderManagerService;
	@Autowired
	private OrderLogThread orderLogThread;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderTempDao orderTempDao;
	@Autowired
	private OrderLogDao orderLogDao;
	private List<Order> orderList;
	private Order order;
	// private static List<String> orderNos;
	// @Autowired
	// private SMSSendService smsSendService;
	private static final Logger logger = Logger
			.getLogger(OrderStatusSynManageImpl.class);

	// 批量未付款
	@Override
	public boolean updateStausToUnPaied(List<String> orderNos) {
		boolean flag = false;
		try {
			orderList = orderDao.selectByOrderNos(orderNos);
			for (Order order : orderList) {
				order.setStatus(OrderDefaultStatus.UNPAIED_STATUS.getValue());
			}
			orderDao.updateOrderStatus(orderList);
			// 修改临时表状态
			this.updateOrderTempList(orderList,
					OrderDefaultStatus.UNPAIED_STATUS.getValue());
			// 线程写日志
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddUnpaied());
					context.executeStrategy(orderList, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException("updateStausToUnPaied is fail", e,orderNos);
		}
		
		return flag;
	}

	// 未付款
	@Override
	public boolean updateStausToUnPaied(String orderNo) {
		boolean flag = false;
		try {
			order = orderDao.selectOne(new OrderSearchCriteria(orderNo));
			
			order.setStatus(OrderDefaultStatus.UNPAIED_STATUS.getValue());
			orderDao.updateByOrderNoSelective(order);

			// 修改临时表状态
			this.updateOrderTemp(order,
					OrderDefaultStatus.UNPAIED_STATUS.getValue());
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddUnpaied());
					context.executeStrategy(order, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException("oms.orderinfosyn.updateStausToUnPaied", e,orderNo);
		}
		
		return flag;
	}

	// 已付款
	@Override
	public boolean updateStausToHasPaied(final String orderNo) {
		boolean flag = false;
		logger.info("开始时间" + System.currentTimeMillis());
		try {
			OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria();
			orderSearchCriteria.setOrderNo(orderNo);
			order = orderDao.selectOne(orderSearchCriteria);
			// 订单状态大于未付款则返回提示
			if (order.getStatus() > OrderDefaultStatus.UNPAIED_STATUS
					.getValue()) {
				logger.info("WARN:order.status is error:"+order.getStatus());
				throw new BizException("订单状态不正确", order);
			}
			order.setStatus(OrderDefaultStatus.HASPAIED_STATUS.getValue());
			// 修改临时表为已付款
			this.updateOrderTemp(order);
			// 修改订单表为已付款
			orderDao.updateByOrderNoSelective(order);
			// 异步生成日志
			new Thread() {
				@Override
				public void run() {
						Context context = new Context(new AddHasPaiedLog());
						context.executeStrategy(order, orderLogDao);
						super.run();

				}
			}.start();
			flag = true;
		} catch (Exception e) {
			logger.info("修改订单状态异常:" + e);
			throw new BizException("修改订单状态异常", e,order);
		}
		

		logger.info("结束时间" + System.currentTimeMillis());
		return flag;
	}

	// 批量已付款
	@Override
	public boolean updateStausToHasPaied(List<String> orderNos) {

		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		boolean flag = false;
		try {
			logger.info("开始时间：" + sdf.format(new Date()));
			orderList = orderDao.selectOrderAndCommodity(orderNos);
			// 修改为已付款状态
			for (Order order : orderList) {
				// 订单状态大于未付款则返回提示
				if (order.getStatus() > OrderDefaultStatus.UNPAIED_STATUS
						.getValue()) {
					logger.info("WARN:order.status is error:"+order.getStatus());
					throw new BizException("订单状态不正确", order);
				}
				order.setStatus(OrderDefaultStatus.HASPAIED_STATUS.getValue());
			}
			orderDao.updateOrderStatus(orderList);
			logger.info("修改已付款状态状态时间：" + System.currentTimeMillis()
					+ "   当前线程名：" + Thread.currentThread().getName());

			this.updateOrderTempList(orderList,
					OrderDefaultStatus.HASPAIED_STATUS.getValue());
			logger.info("修改临时表状态时间：" + sdf.format(new Date()) + "   当前线程名："
					+ Thread.currentThread().getName());
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddHasPaiedLog());
					context.executeStrategy(orderList, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException("oms.orderinfosyn.updateStausToHasPaied", e);
		}
		
		logger.info("结束时间：" + sdf.format(new Date()));
		return flag;
	}

	@Override
	public boolean updateStausToForSorting(List<String> orderNos) {
		boolean flag = false;
		try {
			orderList = orderDao.selectByOrderNos(orderNos);
			for (Order order : orderList) {
				// 判断订单状态
				if (order.getStatus() > OrderDefaultStatus.HASPAIED_STATUS
						.getValue()) {
					throw new BizException("order's status is error", order);
				}
				order.setStatus(OrderDefaultStatus.FORSORTING_STATUS.getValue());
			}
			// 修改订单状态
			orderDao.updateOrderStatus(orderList);
			// 修改临时表状态
			this.updateOrderTempList(orderList,
					OrderDefaultStatus.FORSORTING_STATUS.getValue());
			// 写日志
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddForSortingLog());
					context.executeStrategy(orderList, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException("updateStausToForSorting is fail", e);
		}
		
		return flag;
	}

	@Override
	public boolean updateStausToForSorting(String orderNo) {

		boolean flag = false;
		try {
			order = orderDao.selectOne(new OrderSearchCriteria(orderNo));
			// 判断订单状态
			if (order.getStatus() > OrderDefaultStatus.HASPAIED_STATUS
					.getValue()) {
				throw new BizException("order's status is error", order);
			}
			order.setStatus(OrderDefaultStatus.FORSORTING_STATUS.getValue());
			// 修改订单状态
			orderDao.updateByOrderNoSelective(order);
			// 修改临时表状态
			this.updateOrderTemp(order,
					OrderDefaultStatus.FORSORTING_STATUS.getValue());
			// 写日志
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddForSortingLog());
					context.executeStrategy(order, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"com.spfood.oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStausToForSorting",
					e);
		}
		
		return flag;
	}

	@Override
	public boolean updateStausToPackaged(List<String> orderNos) {
		boolean flag = false;
		try {
			orderList = orderDao.selectByOrderNos(orderNos);
			for (Order order : orderList) {
				// 判断订单状态
				if (order.getStatus() > OrderDefaultStatus.FORSORTING_STATUS
						.getValue()) {
					throw new BizException("order's status is error", order);
				}
				order.setStatus(OrderDefaultStatus.PACKAGED_STATUS.getValue());
			}

			orderDao.updateOrderStatus(orderList);
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddPackagedLog());
					context.executeStrategy(orderList, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStausToPackaged",
					e);
		}
		
		return flag;
	}

	@Override
	public boolean updateStausToPackaged(String orderNo) {
		logger.info("开始时间：" + System.currentTimeMillis());
		boolean flag = false;
		try {
			order = orderDao.selectOne(new OrderSearchCriteria(orderNo));
			// 判断订单状态
			if (order.getStatus() > OrderDefaultStatus.FORSORTING_STATUS
					.getValue()) {
				throw new BizException("order's status is error", order);
			}
			order.setStatus(OrderDefaultStatus.PACKAGED_STATUS.getValue());
			// 修改为已发货状态
			orderDao.updateByOrderNoSelective(order);
			// 添加日志
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddPackagedLog());
					context.executeStrategy(order, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStausToPackaged",
					e);
		}
		
		logger.info("结束时间：" + System.currentTimeMillis());
		return flag;
	}

	@Override
	public boolean updateStausToWaitSite(List<String> orderNos) {
		boolean flag = false;
		try {
			orderList = orderDao.selectByOrderNos(orderNos);
			for (Order order : orderList) {
				// 判断订单状态
				if (order.getStatus() > OrderDefaultStatus.PACKAGED_STATUS
						.getValue()) {
					throw new BizException("order's status is error", order);
				}
				order.setStatus(OrderDefaultStatus.WAITSITE_STATUS.getValue());
			}
			// 修改为待自提
			orderDao.updateOrderStatus(orderList);
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddWaitSiteLog());
					context.executeStrategy(orderList, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStausToWaitSite",
					e);
		}
		
		return flag;
	}

	@Override
	public boolean updateStausToWaitSite(String orderNo) {
		boolean flag = false;
		try {
			order = orderDao.selectOne(new OrderSearchCriteria(orderNo));
			// 判断订单状态
			if (order.getStatus() > OrderDefaultStatus.PACKAGED_STATUS
					.getValue()) {
				throw new BizException("order's status is error", order);
			}
			order.setStatus(OrderDefaultStatus.WAITSITE_STATUS.getValue());
			// 修改为待自提
			orderDao.updateByOrderNoSelective(order);
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddWaitSiteLog());
					context.executeStrategy(order, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStausToWaitSite",
					e);
		}
		
		return flag;
	}

	@Override
	public boolean updateStatusToFinished(List<String> orderNos) {
		boolean flag = false;
		try {
			orderList = orderDao.selectByOrderNos(orderNos);
			for (Order order : orderList) {
				// 判断订单状态
				if (order.getStatus() > OrderDefaultStatus.WAITSITE_STATUS
						.getValue()) {
					throw new BizException("order's status is error", order);
				}
				order.setStatus(OrderDefaultStatus.FINISHED_STATUS.getValue());
			}
			// 修改为已完成
			orderDao.updateOrderStatus(orderList);
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddFinishedLog());
					context.executeStrategy(orderList, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStatusToFinished",
					e);
		}
		
		return flag;
	}

	@Override
	public boolean updateStatusToFinished(String orderNo) {
		boolean flag = false;
		try {
			order = orderDao.selectOne(new OrderSearchCriteria(orderNo));
			// 判断订单状态
			if (order.getStatus() > OrderDefaultStatus.WAITSITE_STATUS
					.getValue()) {
				throw new BizException("order's status is error", order);
			}
			order.setStatus(OrderDefaultStatus.FINISHED_STATUS.getValue());
			// 修改为已完成
			orderDao.updateByOrderNoSelective(order);
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddFinishedLog());
					context.executeStrategy(order, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStatusToFinished",
					e);
		}
		
		return flag;
	}

	// 单个丢弃
	@Override
	public boolean updateStatusToThrowAway(String orderNo) {
		boolean flag = false;
		try {
			order.setStatus(OrderDefaultStatus.THROWAWAY_STATUS.getValue());
			// 修改为丢弃状态
			orderDao.updateByOrderNoSelective(order);
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddThrowAway());
					context.executeStrategy(order, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStatusToThrowAway",
					e);
		}
		
		return flag;
	}

	// 批量丢弃
	@Override
	public boolean updateStatusToThrowAway(List<String> orderNos) {
		boolean flag = false;
		try {
			orderList = orderManagerService.getOrderListByOrderNoList(orderNos);
			for (Order order : orderList) {
				order.setStatus(OrderDefaultStatus.THROWAWAY_STATUS.getValue());
			}
			// 修改为丢弃状态
			orderDao.updateOrderStatus(orderList);
			new Thread() {
				@Override
				public void run() {
					Context context = new Context(new AddThrowAway());
					context.executeStrategy(orderList, orderLogDao);
					super.run();
				}
			}.start();
			flag = true;
		} catch (Exception e) {
			throw new BizException(
					"oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl.updateStatusToThrowAway",
					e);
		}
		
		return flag;
	}

	@Override
	public boolean updateStatusToCancel(String orderNo, String operator,
			String operatorCode) {
		// 只有待付款的才能取消订单
		return orderManagerService.cancelOrder(orderNo, operator, operatorCode);
	}

	// 批量修改临时表状态
	public void updateOrderTempList(List<Order> orderList, Integer status) {
		List<OrderTemp> tempList = new ArrayList<OrderTemp>();
		for (Order order : orderList) {
			OrderTemp orderTemp = new OrderTemp();
			orderTemp.setOrderNo(order.getOrderNo());
			orderTemp.setStatus(status);
			tempList.add(orderTemp);
		}
		orderTempDao.updateOrderStatus(tempList);
	}

	// 单个修改临时表状态
	public void updateOrderTemp(Order order, Integer status) {
		OrderTemp orderTemp = new OrderTemp();
		orderTemp.setOrderNo(order.getOrderNo());
		orderTemp.setStatus(status);
		orderTempDao.updateByOrderNoSelective(orderTemp);
	}

	// 单个修改临时表状态
	public void updateOrderTemp(Order order) {
		OrderTemp orderTemp = new OrderTemp();
		orderTemp.setOrderNo(order.getOrderNo());
		orderTemp.setStatus(order.getStatus());
		orderTempDao.updateByOrderNoSelective(orderTemp);
	}
}
