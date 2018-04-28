<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>OMS-销售订单管理</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/left.jsp"></jsp:include>
<jsp:include page="../common/popup.jsp"></jsp:include>
	

</head>
<body>
	<div class="sp-menu-right" >
		<div id="content" class="sp-content" style=" padding-bottom: 50px;width:99%;">
			<div  class="sp-page-title" >
				<span class="sp-icon"><img src="<%=path %>/img/oms/home.png"></span>
					<span class="sp-color-gray">首页</span>
					<span class="sp-color-gray">&gt;</span>
					<span class="sp-color-green">订单管理</span>
					<span class="sp-color-gray">&gt;</span>
					<span class="sp-color-green">订单列表</span>
			</div>
			<div class="sp-content-layout" style="height: 100% ;margin-top:0">
				<div style="float: right;height:50px;line-height: 50px;">
					<form id="searchConditionForm" method="post">
						<!-- 回显下拉框数据时使用-->
						<input type="hidden" id="hxOrderStatus" value = "${searchCriteria.status}"/>
						<input type="hidden" id="hxOrderType" value = "${searchCriteria.type}"/>
						<input type="hidden" id="page" name="page" value = "${searchCriteria.page}"/>
						<input type="hidden" id="isDorF" name="isDorF"/>
						<input type="hidden" id="orderNo" name="orderNo"/>
						<input type="hidden" id="hxStartDate" value="<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy-MM-dd'/>"/>
						<input type="hidden" id="hxEndDate" value="<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy-MM-dd'/>"/>
						<!-- 是否点击过查询，来决定返回到此页面时列表结果是否根据选中的条件查询 -->
						<input type="hidden" id="flagSearch" name="flagSearch" value="${searchCriteria.flagSearch}"/>
						<div ng-controller="ng-controller-datetime" ng-cloak style="height: 100%;float:right;padding-left:10px;">
								<span>
									<label>下单时间:</label>
									<input name="startDate"  class="sp-input sp-input-md" placeholder="开始时间" ng-model="startDate" datetimepicker/>-<input name="endDate" class="sp-input sp-input-md" placeholder="截止时间"  ng-model="endDate" datetimepicker/>
								</span>
								<span>
									<label>订单状态:</label>
									<select name="status" id="orderStatus" style="height:28px" value="${searchCriteria.status}"></select>
								</span>
								<span>
									<label>订单类型:</label>
									<select name="type" id="orderType"  style="height:28px" value="${searchCriteria.type}"></select>
								</span>
								<span>
									<label> 收货人:</label>
									<input type="text" name="receiver" id="searchReceiver" class="sp-input sp-input-search-md" placeholder="请输入收货人姓名" value="${searchCriteria.receiver}">
								</span>
								<a class="sp-btn sp-btn-search-md"
									style="cursor: pointer"
									onclick="searchByCondition();">查询
								</a>
						</div>
					</form>
				</div>
				<div class="sp-content-interval row" ng-controller="ng-controller-table" style="height: 100%;">
					<div id="orderListTable"></div>
				</div>
			</div>
		</div>
	</div>
 <!---弹出框-大---->
<div id='popUpLg' class='sp-popup sp-popup-lg' style="min-width: 350px;min-height:160px;">
    <div class='sp-pop-header'>
   		<div id="pop-logo1" class="sp-pop-logo"><span>订单号：<span id="msgOrder"></span></span></div>
		<div class="sp-pop-exit"><a href="javascript:exitPop('popUpLg');"><img src="<%=path %>/img/cross.png"></a></div>
	</div>
	<div class='sp-pop-content-lg'>
	    <center><textarea id="signText" rows="4" cols="45" style="height:62px;margin-top: 10px;border:1px solid gainsboro;"></textarea></center>
	    <center style="margin-top: 10px;"><input type="button" onclick="setSign()" style="cursor: pointer;margin-right: 50px;" class="sp-btn sp-btn-gray-sm" value="确定">
	 	<input type="button"style="cursor: pointer" onclick="javascript:exitPop('popUpLg');" class="sp-btn sp-btn-gray-sm" value="关闭"></center>
	</div>
</div>
<!---取消订单确认弹出框---->
 <div id='popDialogue'class='sp-popup sp-popup-md'>
	 <div class='sp-pop-header'>
		<div id="pop-logo" class="sp-pop-logo"><span>对话弹出框</span></div>
		<div class="sp-pop-exit"><a href="javascript:exitPop('popDialogue');"><img src="<%=path %>/img/cross.png"></a></div>
	</div>
	<div class='sp-pop-content'>
	      <div class="sp-pop-title" id="message1">
	             是否添加请购单它兼容电影、戏剧、 文学造型等现代艺术诸元素?
	      </div>
	      <div class="sp-pop-btn">
	          <input type="button" class="sp-btn sp-btn-gray-md "onclick="cancelOrderConfirm()" value="确认">
	          <input type="button" class="sp-btn sp-btn-gray-md " onclick="javascript:exitPop('popDialogue');"  value="取消">
	      </div>
	</div>
 </div>
</body>
<script src="<%=path %>/js/table.js" type="text/javascript" ></script>
<script src="js/order/orderManage.js" type="text/javascript" ></script>	
</html>