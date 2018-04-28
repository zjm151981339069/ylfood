<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="exchangeApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>OMS-销售订单管理</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
	
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/left.jsp"></jsp:include>
<jsp:include page="../common/popup.jsp"></jsp:include>



</head>
<body id="main">

	<div class="sp-menu-right">
		<div id="content" class="sp-content" style="padding-bottom: 50px;width:99%;">
		<div class="sp-page-title">
			<span class="sp-icon"><img src="<%=path %>/img/oms/home.png"></span>
			<span class="sp-color-gray">首页</span>
			<span class="sp-color-gray">&gt;</span>
			<span class="sp-color-green">换货单列表</span>
		</div>
		

		<div class="sp-content-layout" style="height: 100%;margin-top:0">
			<!-- Default panel contents -->
			<div style="float: right;height:50px;line-height: 50px;">
				<form id="searchConditionForm" method="post">
							<input type="hidden" id="exId" name="exId"/>
						  <input type="hidden" id="hxAuditStatus" value="${orderExchangeCriteria.auditstatus}"/>
						  <input type="hidden" id="page" name="page" value = "${orderExchangeCriteria.page}"/>
						  <input type="hidden" id="hxStartDate" value="<fmt:formatDate value='${orderExchangeCriteria.startDate}' pattern='yyyy-MM-dd'/>"/>
						<input type="hidden" id="hxEndDate" value="<fmt:formatDate value='${orderExchangeCriteria.endDate}' pattern='yyyy-MM-dd'/>"/>
						<!-- 是否点击过查询，来决定返回到此页面时列表结果是否根据选中的条件查询 -->
						<input type="hidden" id="flagSearch" name="flagSearch" value="${orderExchangeCriteria.flagSearch}"/>
						 <div ng-controller="ng-controller-datetime" ng-cloak style="height: 100%;">
									<span><label>换货时间:</label><input name="startDate" class="sp-input sp-input-md" placeholder="开始时间" ng-model="startDate" datetimepicker/>-<input name="endDate" class="sp-input sp-input-md" placeholder="截止时间" ng-model="endDate" datetimepicker/></span>
									<span>
										<label>审核状态:</label>
										<select name="auditstatus" style="height:28px" id="auditStatus" ></select> 
									</span>
									<span>
										<label>收货人:</label>
										<input type="text" name="receiver" id="searchReceiver" class="sp-input sp-input-search-md" placeholder="请输入收货人姓名" value="${orderExchangeCriteria.receiver}"/>
									</span>
									<a class="sp-btn sp-btn-search-md"
											style="cursor: pointer"
											onclick="searchByCondition();">查询
									</a>
						</div> 
				</form>
			</div>
			<div class="sp-content-interval row" ng-controller="ng-controller-table" style="height: 100%;">
				<div id="exchangeOrderListTable"></div>
			</div>
		</div>	
		</div>
	</div>
</body>
<script src="<%=path %>/js/table.js" type="text/javascript" ></script>
<script src="js/exchange/list.js" type="text/javascript" ></script>	
</html>