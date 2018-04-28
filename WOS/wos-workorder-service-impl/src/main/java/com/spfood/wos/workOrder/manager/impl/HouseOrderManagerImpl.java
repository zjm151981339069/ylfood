package com.spfood.wos.workOrder.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.dao.HouseOrderDao;
import com.spfood.wos.workOrder.dao.HouseOrderTempDao;
import com.spfood.wos.workOrder.dao.HouseOrderCommodityDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.intf.searchCriteria.HouseOrderSearchCriteria;
import com.spfood.wos.workOrder.manager.HouseOrderManager;
import com.spfood.wos.workOrder.utils.Constant;
import com.spfood.wos.workOrder.utils.MybatisList;
/**
 * 队列接收订单保存管理
 * @author Administrator
 *
 */
@Service
@Transactional
public class HouseOrderManagerImpl implements HouseOrderManager {

	@Autowired
	private HouseOrderDao houseOrderDao;
	@Autowired
	private HouseOrderCommodityDao houseOrderCommodityDao;
	@Autowired
	private HouseOrderTempDao houseOrderTempDao;
	
	private static final Logger logger = Logger.getLogger(HouseOrderManagerImpl.class);
	
	@Override
	public void saveOrder(HouseOrder houseOrder) {
		Long num = houseOrderDao.selectNumByorderNo(houseOrder.getOrderNo());

		if (num == 0) {
			logger.info("入库订单信息" + houseOrder.toString());
			// 入库订单表，商品表
			System.out.println(houseOrder.getOrderCommList());
			houseOrderDao.insert(houseOrder);
			// 增加临时表
			houseOrderTempDao.insert(new HouseOrderTemp(
					houseOrder.getOrderNo(), houseOrder.getStatus(), houseOrder
							.getCreateTime(), houseOrder.getDeliverTime()));
		} else {
			// 写logger
			logger.info("已入库相同订单" + houseOrder.toString());
		}
	}
    @Override
    public List<HouseOrderTemp> getTempOrder() {
        return houseOrderTempDao.getTempOrder();
    }
    @Override
    public HouseOrder getOrder(String orderNo) {
        return houseOrderDao.selectById(orderNo);
    }
	@Override
	public PageInfo<HouseOrder> getHouseOrderListByCriteria(
			HouseOrderSearchCriteria houseOrderCriteria) {
		if (houseOrderCriteria.getPageNum() == null) {
			houseOrderCriteria.setPageNum(Constant.DEFAULT_PAGE_NUM);
		}
		if (houseOrderCriteria.getPageSize() == null) {
			houseOrderCriteria.setPageSize(Constant.DEFAULT_PAGE_SIZE);
		}
		if (houseOrderCriteria.getSort() == null) {
			houseOrderCriteria.setSort(Constant.DEFAULT_CREATETIME_SORT);
		}
		//设置分页参数
		PageInfo<HouseOrder> pageInfo = new PageInfo<HouseOrder>(houseOrderCriteria.getPageNum(), houseOrderCriteria.getPageSize());
		//查询订单
		houseOrderDao.selectListByPage(houseOrderCriteria, MybatisList.GET_HOUSEORDER_BYPAGE, pageInfo);
		return pageInfo;
	}
	
	//修改订单状态
	@Override
	public int updateHouseOrderStatus(HouseOrder houseOrder) {
		return houseOrderDao.updateByHouseOrderNoSelective(houseOrder);
	}
	
	//根据订单号批量查询
	@Override
	public List<HouseOrder> queryHouseOrderListByOrderNos(List<String> orderNos) {
		if (orderNos != null && orderNos.size() > 0) {
			return houseOrderDao.selectByOrderNos(orderNos);
		}
		return null;
	}
	
	//批量修改订单状态
	@Override
	public void updateHouseOrderStatus(List<HouseOrder> houseOrderList) {
		houseOrderDao.updateBatch(houseOrderList);
		/*List<HouseOrderTemp> houseOrderTempList = new ArrayList<HouseOrderTemp>();
		HouseOrderTemp houseOrderTemp = new HouseOrderTemp();
		for (HouseOrder houseOrder : houseOrderList) {
			
			try {
				String json = JSON.json(houseOrder);
				Object object = JSON.parse(json);
				((Object) object).getObject("houseOrder", HouseOrderTemp.class);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HouseOrderTemp orderTemp = houseOrderTemp.getClass().cast(houseOrder);
			houseOrderTemp.setOrderNo(houseOrder.getOrderNo());
			houseOrderTemp.setStatus(houseOrder.getStatus());
			houseOrderTempList.add(houseOrderTemp);
		}
		
		houseOrderTempDao.updateBatch(houseOrderTempList );*/
	}
	
	//批量修改临时表状态
	@Override
	public void updateHouseOrderTempStatus(List<HouseOrderTemp> houseOrderTempList) {
		houseOrderTempDao.updateBatch(houseOrderTempList);
	}
	
	//根据状态修改临时表
	@Override
	public void updateHouseOrderTempByStatus(List<String> orderNos,Integer status) {
		houseOrderTempDao.updateStatusBatch(orderNos,status);
		
	}
	
	//批量查询订单及关联商品
	@Override
	public List<HouseOrder> getOrderListByOrderNos(List<String> orderNos) {
		return houseOrderDao.selectOrderList(orderNos);
	}
	
	//批量删除临时表
	@Override
	public void deleteOrderTempByOrderNo(List<String> orderNos) {
		if (orderNos != null && orderNos.size() > 0) {
			houseOrderTempDao.deleteByOrderNoInBatch(orderNos);
		}
		
	}

}
