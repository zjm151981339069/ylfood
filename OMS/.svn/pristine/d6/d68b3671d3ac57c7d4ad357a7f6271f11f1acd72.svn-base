<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% String path = application.getInitParameter("static-file"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>OMS-销售订单管理</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<jsp:include page="../common/header.jsp" ></jsp:include>
<jsp:include page="../common/left.jsp"></jsp:include>
<jsp:include page="../common/popup.jsp"></jsp:include>

	
</head>
<body id="main">
	<input type="hidden" id="exId" value="${orderExchange.exId}"/>
	<input type="hidden" id="exCode" value="${orderExchange.exCode}"/>
	<input type="hidden" id="orderNo" value="${orderExchange.orderNo}"/>
	<!-- 换货商品列表的长度 -->
	<input type="hidden" id = "exchangeGoodNum" value="${fn:length(orderExchange.exchangeCommodityLists)}"/>
	
	<!--查询条件 -->
	<form id="searchConditionForm" method="post">
		<input type="hidden" name="startDate"  value="<fmt:formatDate value='${exchangeCriteria.startDate}' pattern='yyyy-MM-dd'/>">
		<input type="hidden" name="endDate" value="<fmt:formatDate value='${exchangeCriteria.endDate}' pattern='yyyy-MM-dd'/>">
		<input type="hidden" name="auditstatus" id="auditstatus" value="${exchangeCriteria.auditstatus}">
		<input type="hidden" name="receiver"  value="${exchangeCriteria.receiver}">
		<input type="hidden" name="page"  value="${exchangeCriteria.page}">
		<input type="hidden" name="flagSearch"  value="${exchangeCriteria.flagSearch}">
	</form>
	<div class="sp-menu-right">
		<div id="content" class="sp-content" style="padding-bottom: 50px;" > 
			<div class="sp-page-title">
				<span class="sp-icon"><img src="<%=path %>/img/oms/home.png"/></span><span class="sp-color-gray">首页</span><span class="sp-color-gray">&gt;</span><span class="sp-color-green">换货单列表</span>&gt;<span class="sp-color-green">换货单审核</span>
			</div>
		
		<div class="sp-content-layout">
			<font class="titleT">售后换货详情</font>
			<hr>
			<div class="detail-content" >
				<div style="padding-left:10px;">
					<!-- 售后服务单详情 -->
					<div class="title">
						<font class="title-text">售后服务单详情</font>
					</div>
					<div style="padding-left: 10px;">
						<table class="text2">
							<tr>
								<td>换货单号：<font>${orderExchange.exCode }</font></td>
								<!-- 商品出来方式暂时定位：换货商品 -->
	<%-- 							<td>商品处理方式：<font>${orderExchange.dealStyle }</font></td> --%>
								<td>商品处理方式：<font>换货商品</font></td>
							</tr>
							<tr>
								<td>换货地址：<font>${orderExchange.addr }</font></td>
								<td>收货人：<font>${orderExchange.receiver }</font></td>
							</tr>
						</table>
					</div>
					<div style="display: none;">
							<hr/>
					</div>
					<!-- 商品信息 -->
					<div class="title">
						<font class="title-text">换货商品信息</font>
					</div>
					<div style="padding-left: 10px;">
							<div class="ng-scope">
								<table class="sp-table">
									<tr>
										<th>序号</th>
										<th>换货商品</th>
										<th>换货数量</th>
										<th>问题描述</th>
										<th>照片</th>
									</tr>
									<tbody>
										<c:forEach items="${orderExchange.exchangeCommodityLists }" var="product"
											varStatus="status2">
											<tr <c:if test="${status2.count%2==0}"> class="sp-td-bg"</c:if>>
												<td>${status2.index + 1}</td>
												<td>${product.name }</td>
												<td>${product.count}</td>
												<td>${product.issuedes }</td>
												<td>
													<div id="hovertreeshow" class="hovertreeshow">
														<c:forEach items="${product.pictureUrl }" var="p">
															<img src="${p.url }" height="46" width="46" />
														</c:forEach>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					<div style="display: none;"><hr /></div>
					<!-- 处理意见-->
					<div class="title" >
						<font class="title-text">处理意见</font>
					</div>
					<div style="padding-left: 25px;">
						<table class="text2">
							<tr>
								<td>审核状态：<font>
								<c:if test="${orderExchange.auditstatus  == 0 }">待审核</c:if>
								<c:if test="${orderExchange.auditstatus  == 1 }">审核通过</c:if>
								<c:if test="${orderExchange.auditstatus  == 2 }">审核未通过</c:if>
								</font></td>
								<td >审核意见：<c:if test="${orderExchange.orderNo != null}"><font>${orderExchange.auditview }</font></c:if></td>
							</tr>
							<tr>
								<c:if test="${orderExchange.auditstatus == 1 }">
								<td>新订单号：<font>${orderExchange.exCode }</font></td>
								</c:if>
							</tr>
						</table>
					</div>
					<div id='wq' class='sp-popup sp-popup-lg' style="min-width: 350px;min-height:160px;">
						<div class='sp-pop-header'>
							<div id="pop-logo" class="sp-pop-logo"><span>审核意见</span></div>
							<div class="sp-pop-exit"><a href="javascript:exitPop('wq');"><img src="<%=path %>/img/cross.png"></a></div>
						</div>
						<div class='sp-pop-content-lg'>
								<center>
									<textarea id="auditviewText" name="auditview"  rows="4" cols="45" style="height:62px;margin-top: 10px;border:1px solid gainsboro;" placeholder="请输入审核意见（80字以内）"  maxlength="80" >
									</textarea>
								</center>
								<center style="margin-top: 10px;"><input type="button" onclick="confirmAuditView();" style="cursor: pointer;margin-right: 50px;" class="sp-btn sp-btn-gray-sm" value="确定">
								   <input type="button"style="cursor: pointer" onclick="javascript:exitPop('wq');" class="sp-btn sp-btn-gray-sm" value="关闭">
								</center>
						</div>
					 </div>
					<br>
					<center>
						<button  class="sp-btn sp-btn-red-sm" style="height: 33px;width: 90px;" onclick="back();" >
							<span style="color: #000000;">返回</span>
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${orderExchange.auditstatus == 0 }">
						<button  class="sp-btn sp-btn-red-sm" style="height: 33px;width: 90px;"  onclick="showAuditView('auditTg');" >
							<span class="sp-color-green">审核通过</span>
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						<c:if test="${orderExchange.auditstatus == 0 }">
						<button  class="sp-btn sp-btn-red-sm" style="height: 33px;width: 93px;"   onclick="showAuditView('auditBtg');">
							<span style="color:#ff0000;">审核不通过</span>
						</button>
						</c:if>
					</center>
				</div>
			</div>
		</div>
		</div>
	</div>			
</body>
<script src="<%=path %>/js/table.js" type="text/javascript" ></script>
<script src="js/exchange/orderExchangeDetail.js" type="text/javascript" ></script>

</html>