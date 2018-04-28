<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String path = application.getInitParameter("static-file"); %> 
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="shiros" uri="/WEB-INF/shiro.tld"%>


  <div class="row">
   <input type="hidden" name="sta-path" id="sta-path" value="<%=path %>"/>
   <div class="sp-menu-left">
	 <div class="sp-menu">
	  <ul class="sp-ul-menu">
	 
		<shiros:hasAnyPermission name="oms:order:show,oms:deliver:show">
	    	<li onclick='loadSecMenu("orderList")'><img src='<%=path %>/img/menu-close.png'>订单管理</li>
			<ul id='orderList-sec' class="sp-menu-sec">
				<shiro:hasPermission name="oms:order:show">
					<li><a  href='oms/order/list' >订单列表</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="oms:deliver:show">
				    <li><a  href='oms/deliver/list' >发货单列表</a></li>
				</shiro:hasPermission>
			</ul>
		</shiros:hasAnyPermission>
		<shiros:hasAnyPermission name="oms:exchange:show">
			<li onclick='loadSecMenu("exchangeList")'><img src='<%=path %>/img/menu-close.png'>换货单管理</li>
				<ul id='exchangeList-sec' class="sp-menu-sec">
						<li><a href='oms/exchange/list' >换货单列表</a></li>
				</ul>
	  	</shiros:hasAnyPermission>
	  </ul>
	 </div> 
	</div>


<script src="<%=path %>/js/lib/jquery.min.js" type="text/javascript" ></script>
<script src="<%=path %>/js/lib/angular-1.5.9.js" type="text/javascript" ></script>
<script src="<%=path %>/js/lib/datetimepicker.js" type="text/javascript" ></script>
<script src="<%=path %>/js/pagination.js" type="text/javascript" ></script>
<script src="<%=path %>/js/popup.js" type="text/javascript" ></script>
<script src="<%=path %>/js/lib/angular-sanitize-1.5.9.js" type="text/javascript" ></script>
<script src="js/common/common.js" type="text/javascript" ></script>	
<script src="js/common/menu.js" type="text/javascript" ></script>