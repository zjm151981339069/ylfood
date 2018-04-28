package com.spfood.oms.order.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.oms.order.dao.ExchangePictureDao;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderExchangeCommodityDao;
import com.spfood.oms.order.dao.OrderExchangeDao;
import com.spfood.oms.order.intf.domain.ExchangePicture;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;
import com.spfood.oms.order.manager.OrderExchangeCreateManager;
import com.spfood.oms.order.utils.OrderExchangeStatus;
import com.spfood.oms.order.utils.UUIDGenerator;
/**
 * 换货单生成
 * @author Administrator
 *
 */
@Service
@Transactional
public class OrderExchangeCreateManagerImpl implements OrderExchangeCreateManager {

	@Autowired
	private OrderExchangeDao orderExchangeDao;
	@Autowired
	private OrderExchangeCommodityDao orderExchangeCommodityDao;
	@Autowired
	private ExchangePictureDao exchangePictureDao;
	@Autowired
	private OrderDao orderDao;
	private static final Logger logger = Logger.getLogger(OrderExchangeCreateManagerImpl.class);
	// 生成换货入库单
	@Override
	public boolean createOrderExchange(String orderNo,OrderExchange orderExchange) {

		Order order = orderDao.selectOrderDetailByOrderNo(orderNo);
		
		if (order == null) {
			return false;// 如果原订单为空，则返回为空生成失败
		} else {

			// 换货商品数量需要少于原商品数量
			if (!this.passCommdity(orderExchange, order)) {
				return false;
			}
			// 设置换货单基本信息
			this.setExchange(order, orderExchange);

			// 得到换货单号
			String exCode = orderExchange.getExCode();
			// 商品及图片关联信息
			try {
				List<OrderExchangeCommodity> exchangeList = orderExchange.getExchangeCommodityLists();
				List<OrderExchangeCommodity> commodities = new ArrayList<OrderExchangeCommodity>();
				List<ExchangePicture> pictures = new ArrayList<ExchangePicture>();
				for (OrderExchangeCommodity orderExchangeProduct : exchangeList) {
					String code = orderExchangeProduct.getCode();
					orderExchangeProduct.setExCode(exCode);
					commodities.add(orderExchangeProduct);
					// 获取换货审核图片
					List<ExchangePicture> exchangePictures = orderExchangeProduct
							.getPictureUrl();
					if (exchangePictures != null) {
						for (ExchangePicture exchangePicture : exchangePictures) {
							exchangePicture.setExCode(exCode); // 关联换货单号
							exchangePicture.setCode(code); // 关联商品号
							pictures.add(exchangePicture);
						}
					}
				}
				// 批量保存图片
				exchangePictureDao.insertInBatch(pictures);
//				// 批量保存商品
//				orderExchangeCommodityDao.insertInBatch(commodities);
				// 审核意见为空进行保存
				if (orderExchange.getAuditview() == null ) {
					// 保存换货单与商品
					orderExchangeDao.insert(orderExchange);
					return true;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 设置换货单默认信息
	 * 
	 * @param oldOrderDetail
	 * @param orderExchange
	 */
	public void setExchange(Order order,OrderExchange orderExchange) {
		String exCode = null;
		exCode = UUIDGenerator.getUUID();
		orderExchange.setExCode(exCode);
		// 设置换货单关联编号
		orderExchange.setOrderNo(order.getOrderNo());
		// 设置换货单申请时间
		orderExchange.setAppTime(new Date());
		// 设置下单时间，需要从关联订单获得
		Date createTime = order.getCreateTime();
		orderExchange.setCreateTime(createTime);
		// 收货人
		if (orderExchange.getReceiver() == null) {
			String receiver = order.getReceiver();
			orderExchange.setReceiver(receiver);
			orderExchange.setReceiverCode(order.getReceiverCode());
		}
		// 收货人电话
		if (orderExchange.getPhone() == null) {
			orderExchange.setPhone(order.getPhone());
		}
		// 用户名
		String username = order.getCustomer();
		if (username != null) {
			orderExchange.setUsername(username);
			orderExchange.setUsernameCode(order.getCustomerCode());
		}
		// 自提点
		if (orderExchange.getSite() == null) {
			orderExchange.setSite(order.getSite());
		}
		// 审核状态设置为待审核
		orderExchange.setAuditstatus(OrderExchangeStatus.CHANGE_ORDER_AUTITSTATUS.getValue());
	}

	/**
	 * 比较换货商品数量是否合适
	 * 
	 * @param orderExchange
	 * @param oldOrderDetail
	 * @return
	 */
	public boolean passCommdity(OrderExchange orderExchange,Order oldOrder) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		// 得到原订单商品
		List<OrderCommodity> orderCommList = oldOrder.getOrderCommList();
		for (OrderCommodity commodity : orderCommList) {
			String OldCode = commodity.getCode();
			Integer OldCount = commodity.getCount();
			map.put(OldCode, OldCount);
		}
		// 得到换货商品
		List<OrderExchangeCommodity> exchangeList = orderExchange.getExchangeCommodityLists();
		for (OrderExchangeCommodity orderExchangeProduct : exchangeList) {
			String newCode = orderExchangeProduct.getCode();
			Integer newCount = orderExchangeProduct.getCount();
			//比较订单商品数量是否合适
			if (map.containsKey(newCode) && newCount <= map.get(newCode)) {
				continue;
			} else {
				logger.error("oms.order.manager.createOrderExchange is error,orderExchangeProduct.count > commodity.count");
				return false;
			}
		}
		return true;
	}
}
