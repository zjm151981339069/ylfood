<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>OMS-销售订单管理</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
	
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/left.jsp"></jsp:include>	
<jsp:include page="../common/popup.jsp"></jsp:include>


<script src="../../js/exchange/afterSalesOrder.js" type="text/javascript" ></script>	

</head>
<body id="main">
	<!--查询条件 -->
	<form id="searchConditionForm" >
		<input type="hidden" name="startDate"  value="<fmt:formatDate value='${exchangeCriteria.startDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
		<input type="hidden" name="endDate" value="<fmt:formatDate value='${exchangeCriteria.endDate}' pattern='yyyy-MM-dd HH:mm'/>">
		<input type="hidden" name="auditstatus"  value="${exchangeCriteria.auditstatus}">
		<input type="hidden" name="receiver"  value="${exchangeCriteria.receiver}">
		<input type="hidden" name="page"  value="${exchangeCriteria.page}">
	</form>
	<div class="sp-menu-right">
		<div id="content" class="sp-content" style="padding-bottom: 50px;" >
			
			<div class="sp-page-title">
				<span class="sp-icon"><img
					src="<%=path %>/img/oms/home.png"></span><span
					class="sp-color-gray">首页</span><span class="sp-color-gray">&gt;</span><span
					class="sp-color-green">换货单管理</span>&gt;<span class="sp-color-green">售后单详情</span>
			</div>
	
			<div class="sp-content-layout">
	
					<font class="titleT">订单详情</font>
					
					<hr>
					
					<div class="detail-content">
	
						 <div style="padding-right: 20%;">
	
						<!-- 基本信息 -->
						<div class="title">
						<font class="title-text">收货人基本信息</font></div>
						<div style="padding-left: 25px;">
						<table class="text2">
							<tr>
								<td>购货人：<font>${orderDetail.customer }</font></td>
								<td>收货人：<font>${orderDetail.receiver }</font></td>
								<td>手机号：<font>${orderDetail.phone }</font></td>
							</tr>
							<tr>
								<td>所在地区：<font>${orderDetail.zone }</font></td>
								<td>详细地址：<font>${orderDetail.addr }</font></td>
								<td>提货点：<font>${orderDetail.site }</font></td>
							</tr>
						</table>
						</div>
	
						<div style="display: none;"><hr /></div>
	
						<hr>
						<!-- 配送信息 -->
						<div class="title">
						<font class="title-text">配送信息</font></div>
						<div style="padding-left: 25px;">
						<table class="text2">
							<tr>
								<td>运费：<fmt:formatNumber type="number" value="${orderDetail.carriage }" pattern="0.00" maxFractionDigits="2" /></td>
								<td>配送方式：<font>${orderDetail.site}</font></td>
								<td>配送时间：<font><fmt:formatDate value='${orderDetail.deliverTime }' pattern='yyyy-MM-dd HH:mm'/></font></td>
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
							<th>单价</th>
							<th>数量</th>
							<th>金额</th>
						</tr>
						<tbody>
						<c:forEach items="${orderDetail.orderCommList }" var="orderCommList"
							varStatus="status2">
							<tr <c:if test="${status2.count%2==0}"> class="sp-td-bg"</c:if>>
								<td>${orderCommList.code }</td>
								<td>${orderCommList.name }</td>
								<td><fmt:formatNumber type="number" value="${orderCommList.price }" pattern="0.00" maxFractionDigits="2" /></td>
								<td>${orderCommList.count }</td>
								<td><fmt:formatNumber type="number" value="${orderCommList.subTotal }" pattern="0.00" maxFractionDigits="2" /></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					</div>
					</div>
				<div style="display: none;"><hr /></div>
				<div style="padding-left: 25px;" align="right">
				<table class="text" width="300" style="height:100px;" >
					<tr><td>商品总金额：</td><td><font>￥ <fmt:formatNumber type="number" value="${orderDetail.orderAmount}" pattern="0.00" maxFractionDigits="2" /></font></td></tr>
					<tr><td>
					运费：</td><td><font>￥<fmt:formatNumber type="number" value="${orderDetail.carriage }" pattern="0.00" maxFractionDigits="2" /></font>
					</td></tr>
					<tr>
						<td>
							应付金额：</td><td><font style="font-size:18px;color:red">￥<fmt:formatNumber type="number" value="${orderDetail.orderAmount + orderDetail.carriage }" pattern="0.00" maxFractionDigits="2" /></font>
						</td>
					</tr>
				</table>
				</div>
				<div style="display: none;"><hr /></div>
				<center>
					<button  class="sp-btn sp-btn-red-sm" style="height: 33px;width: 90px;"  onclick="back();">
						<span style="color: #000000;">返回</span>
					</button>
				</center>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>


</html>