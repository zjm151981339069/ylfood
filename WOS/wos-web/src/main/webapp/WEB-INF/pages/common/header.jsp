<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = application.getInitParameter("static-file"); %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %> 

<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  

<link href="<%=path %>/css/oms/magula.css" rel="stylesheet"/>
<link href="<%=path %>/css/datetimepicker.css" rel="stylesheet" id=cal_style type="text/css" >
<link href="<%=path %>/css/oms/oms.css" rel="stylesheet" />
<link href="<%=path %>/css/oms/hovertreeshow.css" rel="stylesheet" />
<link href="<%=path %>/css/css.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/popup.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/form.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/head.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/table.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/pagination.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/menu.css" rel="stylesheet" type="text/css"> 
<link href="<%=path %>/css/footer.css" rel="stylesheet" type="text/css"> 


<div id="tou" class="row sp-head">
  <div class="sp-head-logo">
    <img alt="" src="<%=path %>/img/wos/wos-logo.png" class="sp-head-img"/>
    </div>
    <div class="sp-head-right">
    	<span class="sp-head-user"><img src="<%=path %>/img/user.png"><shiro:principal property="account"/></span>
	    <span class="sp-head-exit"><img src="<%=path %>/img/exit.png"><a href="<%=basePath%>/uias-web/login/loginOut" >安全退出</a></span>
    </div>
  </div>