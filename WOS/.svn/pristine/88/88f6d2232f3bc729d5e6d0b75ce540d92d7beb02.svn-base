<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = application.getInitParameter("static-file"); %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="shiros" uri="/WEB-INF/shiro.tld"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">


  <div class="row">
	<input type="hidden" name="sta-path" id="sta-path" value="<%=path %>"/>
   <div class="sp-menu-left">
	 <div class="sp-menu">
	  <ul class="sp-ul-menu">
	 	 <shiros:hasAnyPermission name="wos:houseOrder:show">
		<li onclick='loadSecMenu("houseOrder")'><img src='<%=path %>/img/icon_2.png'>仓库订单</li>
		
			<ul id='houseOrder-sec' class="sp-menu-sec">
				<shiro:hasPermission name="wos:houseOrder:show">
				<li><a href='wos/houseOrder/list'>订单任务</a></li>
				</shiro:hasPermission>
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
<script src="<%=path %>/js/lib/hovertreeshow.js" type="text/javascript" ></script>
<script src="<%=path %>/js/lib/angular-sanitize-1.5.9.js" type="text/javascript" ></script>
<script src="js/common/common.js" type="text/javascript" ></script>
<script src="js/common/menu.js" type="text/javascript" ></script>