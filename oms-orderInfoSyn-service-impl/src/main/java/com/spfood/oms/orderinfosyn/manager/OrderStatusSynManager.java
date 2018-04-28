package com.spfood.oms.orderinfosyn.manager;

import java.util.List;

public interface OrderStatusSynManager {

		//由null修改为待付款
		public boolean updateStausToUnPaied(List<String> orderNos);
		public boolean updateStausToUnPaied(String orderNo);

		//由待付款修改为已付款
		public boolean updateStausToHasPaied(List<String> orderNos);
		public boolean updateStausToHasPaied(String orderNo);

		//由已付款修改为待发货
		public boolean updateStausToForSorting(List<String> orderNos);
		public boolean updateStausToForSorting(String orderNo);

		//由待发货修改为已发货
		public boolean updateStausToPackaged(List<String> orderNos);
		public boolean updateStausToPackaged(String orderNo);

		//由已发货修改为待自提
		public boolean updateStausToWaitSite(List<String> orderNos);
		public boolean updateStausToWaitSite(String orderNo);

		//由待提货修改为已完成
		public boolean updateStatusToFinished(List<String> orderNos);
		public boolean updateStatusToFinished(String orderNo);

		//由待付款修改为取消订单
		//public boolean updateStatusToCancel(List<String> orderNos);
		public boolean updateStatusToCancel(String orderNo,String operator,String operatorCode);
		
		//改为丢弃状态
		public boolean updateStatusToThrowAway(String orderNo);
		public boolean updateStatusToThrowAway(List<String> orderNos);
		
}
