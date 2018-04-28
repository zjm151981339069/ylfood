<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WOS-银犁食品</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
	
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/left.jsp"></jsp:include>	



</head>
<body id="main">
	<!--查询条件 -->
<form id="searchConditionForm" method="post">
	
	<input type="hidden" name="startDate"  value="<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy-MM-dd'/>">
	<input type="hidden" name="endDate" value="<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy-MM-dd'/>">
	<input type="hidden" name="wishStartDate"  value="<fmt:formatDate value='${searchCriteria.wishStartDate}' pattern='yyyy-MM-dd'/>">
	<input type="hidden" name="wishEndDate" value="<fmt:formatDate value='${searchCriteria.wishEndDate}' pattern='yyyy-MM-dd'/>">
	<input type="hidden" name="status"  value="${searchCriteria.status}">
	<input type="hidden" name="site"  value="${searchCriteria.site}">
	<input type="hidden" name="zone"  value="${searchCriteria.zone}">
	<input type="hidden" name="receiver"  value="${searchCriteria.receiver}">
	<input type="hidden" name="page"  value="${searchCriteria.page}">
	<input type="hidden"  name="searchOrderNo" value="${searchCriteria.searchOrderNo}"/>
</form>
	<div class="sp-menu-right">
	<div id="content" class="sp-content" style="padding-bottom: 50px;" >
		
		<div class="sp-page-title">
			<span class="sp-icon"><img
				src="<%=path %>/img/oms/home.png"></span><span
				class="sp-color-gray">首页</span><span class="sp-color-gray">&gt;</span><span
				class="sp-color-green">订单任务</span>&gt;<span class="sp-color-green">订单详情</span>
		</div>

		<div class="sp-content-layout">
				<hr>
				<div class="detail-content">
					 <div style="padding-right: 20%;">
					 <!-- 任务生成信息 -->
					 <c:if test="${houseOrder.status == 1 }">
						<div class="title">
							<font class="title-text">任务生成信息</font></div>
						<div style="padding-left: 25px;">
							<table class="text2">
								<tr>
									<td>生成任务时间：<font><fmt:formatDate value='${houseOrder.taskTime}' pattern='yyyy-MM-dd HH:mm'/></font></td>
								</tr>
								<tr>
									<td>领料任务编号：<font><c:forEach items="${houseOrder.orderCommList}" var="task"><br/>${task.recCode }</c:forEach></font></td>
									<td>出库任务编号：<font><c:forEach items="${houseOrder.orderCommList }" var="task"><br/>${task.outCode }</c:forEach></font></td>
									<td>分拣任务编号：<font><c:forEach items="${houseOrder.orderCommList }" var="task"><br/>${task.sortCode }</c:forEach></font></td>
								</tr>
							</table>
						</div>
					</c:if>
					<div style="display: none;"><hr /></div>
					<hr>
					<!-- 基本信息 -->
					<div class="title">
						<font class="title-text">基本信息</font>
					</div>
					<div style="padding-left: 25px;">
						<table class="text2">
							<tr>
								<td>订单号：<font>${houseOrder.orderNo}</font></td>
								<td>购货人：<font>${houseOrder.customer}</font></td>
							</tr>
							<tr>
								<td>下单时间：<font><fmt:formatDate value='${houseOrder.createTime}' pattern='yyyy-MM-dd HH:mm'/></font></td>
								<td>订单类型：
									<font>
										<c:if test="${houseOrder.type==0}">普通订单</c:if>
										<c:if test="${houseOrder.type==1}">换货订单</c:if>
										<c:if test="${houseOrder.type==2}">其他订单</c:if>
									</font>
								</td>
							</tr>
						</table>
					</div>
					<div style="display: none;"><hr /></div>
					<hr>
					<!-- 收货人信息 -->
					<div class="title">
					<font class="title-text">收货人信息</font></div>
					<div style="padding-left: 25px;">
					<table class="text2">
						<tr>
							<td>收货人：<font>${houseOrder.receiver }</font></td>
							<td>手机号：<font>${houseOrder.phone}</font></td>
							
						</tr>
						<tr>
							<td>所在地区：<font>${houseOrder.zone }</font></td>
							<td>家庭地址：<font>${houseOrder.addr }</font></td>
						</tr>
					</table>
					</div>
				<div style="display: none;"><hr /></div>
				<!-- 配送信息 -->
					<div class="title">
					<font class="title-text">配送信息</font></div>
					<div style="padding-left: 25px;">
						<table class="text2">
							<tr>
								<td>期望到货时间：<font><fmt:formatDate value='${houseOrder.deliverTime}' pattern='yyyy-MM-dd HH:mm'/></font></td>
								<td>自提点：<font>${houseOrder.site}</font></td>
							</tr>
						</table>
					</div>
				<div style="display: none;"><hr /></div>
				<hr>
				<!-- 商品信息 -->
				<div class="title">
					<font class="title-text">商品信息</font></div>
					<div style="padding-left: 25px;">
						<div class="ng-scope">
							<table class="sp-table">
								<tr>
									<th>商品编号</th>
									<th>商品名称</th>
									<th>数量</th>
									<th>领料任务</th>
									<th>出库任务</th>
									<th>分拣任务</th>
								</tr>
								<tbody>
								<c:forEach items="${houseOrder.orderCommList }" var="product"
									varStatus="status2">
									<tr <c:if test="${status2.count%2==0}"> class="sp-td-bg"</c:if>>
										<td>${product.code }</td>
										<td>${product.name }</td>
										<td>${product.count }</td>
										<td>${product.recCode }</td>
										<td>${product.outCode }</td>
										<td>${product.sortCode }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
				</div>
				<div style="display: none;"><hr /></div>
				<div style="display: none;"><hr /></div>
				<center>
					<button onClick="back();" class="sp-btn sp-btn-red-sm" style="height: 33px;width: 90px;"><span style="color: #000000;">返回</span></button>
				</center>
			</div>
		</div>
	</div>
	</div>
</div>
</body>
<script src="js/houseOrder/houseOrderDetail.js" type="text/javascript" ></script>
</html>