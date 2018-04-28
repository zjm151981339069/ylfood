<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<!-- 是从订单列表表格中的详情按钮进来，还是从发货单列表的详情按钮进来 -->
	<input type="hidden" id="isDorF" value="${searchCriteria.isDorF}"/>
<!--查询条件 -->
<form id="searchConditionForm" method="post">
	
	<input type="hidden" name="startDate"  value="<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy-MM-dd'/>">
	<input type="hidden" name="endDate" value="<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy-MM-dd'/>">
	<input type="hidden" name="status"  value="${searchCriteria.status}">
	<input type="hidden" name="type"  value="${searchCriteria.type}">
	<input type="hidden" name="receiver"  value="${searchCriteria.receiver}">
	<input type="hidden" id="cancelOrderNo"  value="${searchCriteria.orderNo}">
	<input type="hidden" name="page"  value="${searchCriteria.page}">
	<input type="hidden" name="flagSearch"  value="${searchCriteria.flagSearch}">
</form>
<div class="sp-menu-right">
	<div id="content" class="sp-content" >
			<div class="sp-page-title">
				<span class="sp-icon"><img src="<%=path%>/img/oms/home.png"></span>
				<span class="sp-color-gray">首页</span> <span class="sp-color-gray">&gt;</span>
				<span class="sp-color-green">订单管理</span> <span class="sp-color-gray">&gt;</span>
				<span class="sp-color-green"><font id="send" style="display: none">发货</font>订单管理</span> <span class="sp-color-gray">&gt;</span>
				<span class="sp-color-green">订单列表</span>
			</div>
			<div class="sp-content-layout">
                <font class="titleT">订单详情</font>
                <div class="detail-content">
                	<div style="padding-right: 20%;">
                		<hr>
                		<!-- 基本信息 -->
						<div class="title">
							<font class="title-text">基本信息</font>
						</div>
						<div style="padding-left: 25px;">
							<table class="text2">
								<tr>
									<td>订单号：<font>${order.orderNo }</font></td>
									<td>订单状态：
										<font>
											<c:if test="${order.status==0}">已取消</c:if>
											<c:if test="${order.status==1}">待付款</c:if>
											<c:if test="${order.status==2}">已付款</c:if>
											<c:if test="${order.status==3}">待发货</c:if>
											<c:if test="${order.status==4}">已发货</c:if>
											<c:if test="${order.status==5}">待提货</c:if>
											<c:if test="${order.status==6}">已完成</c:if>
										</font>
									</td>
									<td>购货人：<font>${order.customer }</font></td>
								</tr>
								<tr>
									<td>下单时间：<font><fmt:formatDate value='${order.createTime }' pattern='yyyy-MM-dd HH:mm'/></font></td>
									<td>订单类型：
										<font>
											<c:if test="${order.type==0}">普通销售订单</c:if>
											<c:if test="${order.type==1}">售后订单</c:if>
										</font>
									</td>
								</tr>
							</table>
						</div>

					    <hr/>
                
                		<!-- 收货人信息 -->
						<div class="title">
							<font class="title-text">收货人信息</font>
						</div>
						<div style="padding-left: 25px;">
							<table class="text2">
								<tr>
									<td>收货人：<font>${order.receiver }</font></td>
									<td>手机号：<font>${order.phone }</font></td>
								</tr>
								<tr>
									<td>所在地区：<font>${order.zone }</font></td>
									<td>家庭地址：<font>${order.addr }</font></td>
								</tr>
							</table>
						</div>
					    <hr/>

				       <!-- 配送信息 -->
						<div class="title">
							<font class="title-text">配送信息</font>
						</div>
						<div style="padding-left: 25px;">
							<table class="text2">
								<tr>
									<td>运费：<font>
									<fmt:formatNumber type="number" value="${order.carriage }" pattern="0.00" maxFractionDigits="2" />&nbsp;元</font></td>
									<td>配送时间：<font id="delivertime"><fmt:formatDate value='${order.deliverTime }' pattern='yyyy-MM-dd HH:mm'/></font>
										<c:if
											test="${order.status ==1 || order.status ==2 }">
											<button onclick="openWin();" class="sp-btn sp-btn-black-sm">
												<span style="color: #000000;">设置</span>
											</button>
										</c:if>
									</td>
									<div id="setDateDiv" style="position: absolute; left: 50%; top: 20%; display: none;">
										<div ng-controller="ng-controller-datetime" id="setDate" ng-cloak style="height: 100%;">
											<span><input  class="sp-input sp-input-md" placeholder="点击设置配送时间" ng-model="setDate" datetimepicker="datetime" readonly="readonly"/></span>
											<button class="sp-btn sp-btn-black-sm"
												onclick="setTime('${order.orderNo}','${order.status}')">确定</button>
											<button class="sp-btn sp-btn-black-sm" onclick="colseWin()">关闭</button>
										</div>
									</div>
									<td>自提点：<font>${order.site }</font></td>
								</tr>
							</table>
						</div>
						<div id="setDateDiv"
							style="position: absolute; left: 50%; top: 20%; display: none;">
							<input type="text" class="sp-input sp-input-md" id="setDate"
								data-enable-time=true data-time_24hr=true />
							<button class="sp-btn sp-btn-black-sm"
								onclick="setTime('${order.orderNo }','${order.status}');">确定</button>
							<button class="sp-btn sp-btn-black-sm" onclick="colseWin()">关闭</button>
						</div>
				        <hr/>
                        <!-- 商品信息 -->
						<div class="title">
							<font class="title-text">商品信息</font>
						</div>
						<div style="padding-left: 25px;">
							<table class="sp-table-nohead" style="width: 100%;">
								<tr>
									<th>商品编号</th>
									<th>商品名称</th>
									<th>商品价格</th>
									<th>执行价</th>
									<th>数量</th>
									<th>小计金额</th>
									<th>是否组合装</th>
								</tr>
								<c:forEach items="${order.orderCommList }"
									var="orderCommodity">
									<tr>
										<td>${orderCommodity.code }</td>
										<td>${orderCommodity.name }</td>
										<td>￥
										<fmt:formatNumber type="number" value="${orderCommodity.price }" pattern="0.00" maxFractionDigits="2" />
										</td>
										<td>￥
										<fmt:formatNumber type="number" value="${orderCommodity.actPrice }" pattern="0.00" maxFractionDigits="2" />
										</td>
										<td>${orderCommodity.count }</td>
										<td>￥
										<fmt:formatNumber type="number" value="${orderCommodity.subTotal }" pattern="0.00" maxFractionDigits="2" />
										</td>
										<td>
											<c:if test="${orderCommodity.isPackage==0}">是</c:if>
											<c:if test="${orderCommodity.isPackage==1}">否</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div align="right">
							<table class="text" width="600" style="height: 200px;">
								<tr>
									<td>商品总金额：
									<font>￥<fmt:formatNumber type="number" value="${order.comAmount }" pattern="0.00" maxFractionDigits="2" /></font>&nbsp;&nbsp;-&nbsp;&nbsp;
									折扣：<font>￥<fmt:formatNumber type="number" value="${order.discount }" pattern="0.00" maxFractionDigits="2" />元</font>&nbsp;&nbsp;+&nbsp;&nbsp;
									配送费用：</td>
									<td align='right'><font>￥<fmt:formatNumber type="number" value="${order.carriage }" pattern="0.00" maxFractionDigits="2" /></font></td>
								</tr>
								<tr>
									<td align="right">订单总金额：</td>
									<td><font>￥<fmt:formatNumber type="number" value="${order.orderAmount}" pattern="0.00" maxFractionDigits="2" /></font></td>
								</tr>
								<tr>
									<td align='right'>应付款金额：</td>
									<td><font>￥<fmt:formatNumber type="number" value="${order.orderAmount}" pattern="0.00" maxFractionDigits="2" /></font></td>
								</tr>
								<tr>
									<td>订单实际到账金额：</td>
									<td><font style="font-size: 18px; color: red">￥ 
									<c:if test="${empty order.orderPay or order.orderPay.isArrived == 1}">0</c:if>
									<c:if test="${not empty order.orderPay and order.orderPay.isArrived == 0}"><fmt:formatNumber type="number" value="${order.orderPay.paySum }" pattern="0.00" maxFractionDigits="2" /></c:if>
									</font></td>
								</tr>
							</table>
						</div>

						<c:if test="${not empty order.orderPay}">
					    <hr/>
						<!-- 支付信息 -->
						<div class="title">
							<font class="title-text">支付信息</font>
						</div>
						<div style="padding-left: 25px;">
							<table class="sp-table-nohead" style="width: 100%;">
								<tr>
									<th>金额</th>
									<th>支付时间</th>
									<th>支付方式</th>
									<th>交易号</th>
									<th>付款人</th>
									<th>付款账号</th>
									<th>是否到账</th>
								</tr>
								<c:if test="${order.orderPay.isPay == 1 }">
									<tr>
										<td>￥<fmt:formatNumber type="number" value="${order.orderPay.paySum }" pattern="0.00" maxFractionDigits="2" /></td>
										<td><fmt:formatDate value='${order.orderPay.payTime}' pattern='yyyy-MM-dd HH:mm'/></td>
										<td>未支付</td>
										<td>${order.orderPay.transcation }</td>
										<td>${order.orderPay.payee }</td>
										<td>${order.orderPay.account }</td>
										<td>
											<c:if test="${order.orderPay.isArrived==0}">是</c:if>
											<c:if test="${order.orderPay.isArrived==1}">否</c:if>
										</td>
									</tr>
								</c:if>
								<c:if test="${order.orderPay.isPay != 1 }">
									<tr>
										<td>￥<fmt:formatNumber type="number" value="${order.orderPay.paySum }" pattern="0.00" maxFractionDigits="2" /></td>
										<td><fmt:formatDate value='${order.orderPay.payTime}' pattern='yyyy-MM-dd HH:mm'/></td>
										<td>
											<c:if test="${order.orderPay.payType==0}">微信</c:if>
											<c:if test="${order.orderPay.payType==1}">支付宝</c:if>
											<c:if test="${order.orderPay.payType==2}">银联</c:if>
											<c:if test="${order.orderPay.payType==3}">其他</c:if>
										</td>
										<td>${order.orderPay.transcation }</td>
										<td>${order.orderPay.payee }</td>
										<td>${order.orderPay.account }</td>
										<td>
											<c:if test="${order.orderPay.isArrived==0}">是</c:if>
											<c:if test="${order.orderPay.isArrived==1}">否</c:if>
										</td>
									</tr>
								</c:if>

							</table>
						</div>
						</c:if>
						<hr/>
						<!-- 其他信息 -->
						<div class="title">
							<font class="title-text">其他信息</font>
						</div>
						<div style="padding-left: 25px;">
						<c:if test="${order.type != 1 }">
						<table class="text2">
							<tr>
								<td>发票类型：<font><c:if test="${order.billType==-1}">无发票</c:if><c:if test="${order.billType==0}">普通发票</c:if><c:if test="${order.billType==1}">增值税发票</c:if><c:if test="${order.billType==2}">其他发票</c:if></font></td>
								<td>发票抬头：<font><c:if test="${order.billTitle==0}">企业</c:if><c:if test="${order.billTitle==1}">个人</c:if></font></td>
							</tr>
							<tr>
								<td>发票内容：<font>${order.billContent }</font></td>
								<td>用户留言：<font>${order.userComments }</font></td>
							</tr>
						</table>
						</c:if>
					</div>


						<center><c:if test="${order.status == 1 }"><button onclick="cancelOrder('${order.orderNo}')" class="sp-btn sp-btn-red-sm" style="height: 33px;width: 90px;"><span style="color: #ff0000;">取消订单</span></button></c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                	<button onClick="back();" class="sp-btn sp-btn-red-sm" style="height: 33px;width: 90px;"><span style="color: #000000;">返回</span></button></center>
	                 	<hr/>
                
                  		<!-- 订单日志 -->
						<div class="title">
							<font class="title-text">订单日志</font>
						</div>
						<div style="padding-left: 25px;">
							<table class="text3">
								<tr>
									<td width="25%" style="font-size: 16px; font-weight: 600;">时间</td>
									<td width="50%" style="font-size: 16px; font-weight: 600;">处理信息</td>
									<td width="25%" style="font-size: 16px; font-weight: 600;">操作人</td>
								</tr>
								<c:forEach items="${order.orderLogList }" var="orderLog" varStatus="status2">
									<c:if test="${order.status == 6 && status2.last}">
										<tr>
											<td style="color: #99c731"><fmt:formatDate value='${orderLog.oprTime }' pattern='yyyy-MM-dd HH:mm'/></td>
											<td style="color: #99c731">${orderLog.oprContent }</td>
											<td style="color: #99c731">${orderLog.oprator }</td>
										</tr>
									</c:if>
									<c:if test="${order.status != 6 || !status2.last }">
										<tr>
											<td><fmt:formatDate value='${orderLog.oprTime }' pattern='yyyy-MM-dd HH:mm'/></td>
											<td>${orderLog.oprContent }</td>
											<td>${orderLog.oprator }</td>
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</div>
					</div>
                </div>
             </div>
         </div>
</div>
  <!---取消订单确认弹出框---->
  <div id='popDialogue'class='sp-popup sp-popup-md'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>取消订单</span></div>
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
<script src="js/order/orderDetail.js" type="text/javascript" ></script>
</html>