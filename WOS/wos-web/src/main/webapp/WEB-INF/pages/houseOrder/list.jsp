<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>wos-银犁食品</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
	
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/left.jsp"></jsp:include>



</head>
<body>

<div class="sp-menu-right" >
	<div id="content" class="sp-content" style=" padding-bottom: 50px;width:99%;">
		<div  class="sp-page-title" >
			<span class="sp-icon"><img src="<%=path %>/img/oms/home.png"></span>
				<span class="sp-color-gray">仓库订单</span>
				<span class="sp-color-gray">&gt;</span>
				<span class="sp-color-green">订单任务</span>
		</div>
		<div class="sp-content-layout" style="height: 100%;margin-top:10PX">
			<div style="float: right;height:80px;">
				<form id="searchConditionForm" method="post">
					<input type="hidden" id="hxStatus" value = "${searchCriteria.status}"/>
					<input type="hidden" id="hxZone" value = "${searchCriteria.zone}"/>
					<input type="hidden" id="hxSite" value = "${searchCriteria.site}"/>
					<input type="hidden" id="hxStartDate" value="<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy-MM-dd'/>"/>
					<input type="hidden" id="hxEndDate" value="<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy-MM-dd'/>"/>
					<input type="hidden" id="hxWishStartDate" value="<fmt:formatDate value='${searchCriteria.wishStartDate}' pattern='yyyy-MM-dd'/>"/>
					<input type="hidden" id="hxWishEndDate" value="<fmt:formatDate value='${searchCriteria.wishEndDate}' pattern='yyyy-MM-dd'/>"/>
					<input type="hidden" id="page" name="page" value = "${searchCriteria.page}"/>
					<input type="hidden" id="orderNo" name="orderNo"/>
					<table>
						<tr>
							<td style="text-align: right">
								<span>
									<label style=" text-align: right;width: 73px;display: inline-block;">
										 城 市：
									</label>
									<select style="width: 141px;position: relative;height:29px;" name="zone" id="searchZone" onchange="selectZone();" value="${searchCriteria.zone}"></select>
								</span>
							</td>
							<td >
								<span>
									<label style=" text-align: right;width: 62px;display: inline-block;">
										自提点：
									</label>
									<select name="site" id="searchSite" style="width: 141px;position: relative;height:29px;" value="${searchCriteria.site}"></select>
								</span>
								
							</td>
							<td style="text-align: right">
								<div ng-controller="ng-controller-datetime" ng-cloak style="height: 100%;">
									<span>
										<label>下单时间:</label>
										<input name="startDate" id="startDate" class="sp-input sp-input-md" placeholder="开始时间" ng-model="startDate" datetimepicker />-<input name="endDate" id="endDate" class="sp-input sp-input-md" placeholder="截止时间" ng-model="endDate" datetimepicker />
									</span>
								</div>
							<td>
							<td>
								<span>
									<label>
										是否生成任务:
									</label>
									<select id="status" name="status" style="height:29px;" onchange="selectTaskStatus();" value="${searchCriteria.status}"></select>
								</span>
							</td>
						</tr>
						<tr>
							<td>
								<span>
									<label>
										订单编号：
									</label>
									<input type="text" name="searchOrderNo" placeholder="请输入订单编号" class="sp-input sp-input-search-md"  value="${searchCriteria.searchOrderNo }">
								</span>
							</td>
							
							<td>
								<span>
									<label>
										收货人：
									</label>
									<input type="text" name="receiver" id="searchReceiver" class="sp-input sp-input-search-md" placeholder="请输入收货人姓名" value="${searchCriteria.receiver }">
								</span>
							</td>
							<td>
								<div ng-controller="ng-controller-wishdatetime" ng-cloak style="height: 100%;">
									<span>
										<label>期望到货时间:</label>
										<input name="wishStartDate" id="wishStartDate" class="sp-input sp-input-md" placeholder="开始时间" ng-model="wishStartDate" datetimepicker />-<input name="wishEndDate" id="wishEndDate" class="sp-input sp-input-md" placeholder="截止时间" ng-model="wishEndDate" datetimepicker />
									</span>
								</div>
							<td>
							<td>
								<a id="searchBtn" class="sp-btn sp-btn-search-md"
													style="cursor: pointer"
													onclick="searchByCondition();">查询
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="scfjTask" style="display:none;">
				<a class="sp-btn sp-btn-search-md" style="cursor: pointer;" onclick="scfjTask();" >生成分拣任务</a>
			</div>
			<div class="sp-content-interval row" ng-controller="ng-controller-table" style="height: 100%;">
						<div id="houseOrderListTable"></div>
			</div>
		</div>
	</div>
</div>
<!---弹出框-超级大---->
  <div id='popDialogue'class='sp-popup sp-popup-md'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>生成分拣任务</span></div>
	<div class="sp-pop-exit"><a href="javascript:exitPop('popDialogue');"><img src="<%=path %>/img/cross.png"></a></div>
	</div>
	<div class='sp-pop-content'>
       <div class="sp-pop-title" id="message1">
                确定要生成分拣任务吗？
       </div>
       <div class="sp-pop-btn">
           <input type="button" class="sp-btn sp-btn-gray-md "onclick="confirmScfjTask()" value="确认">
           <input type="button" class="sp-btn sp-btn-gray-md " onclick="javascript:exitPop('popDialogue');"  value="取消">
       </div>
	</div>
  </div>
<!--提示框-->
<div id='popHint'class='sp-popup sp-popup-sm'>
  <div class='sp-pop-content'>
        <div id="message" class="sp-pop-alert">
          ""
        </div>
	</div>
</div>
</body>
<script src="<%=path %>/js/table.js" type="text/javascript" ></script>
<script src="js/houseOrder/list.js" type="text/javascript" ></script>
</html>