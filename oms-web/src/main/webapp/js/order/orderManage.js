//页面的表格对象
var table;

var $scopeDateCopy;


//表格使用
var app = angular.module('app',['ngSanitize','dTable','datetimepicker']);

app.controller('ng-controller-datetime',['$scope',function($scope) {
	$scopeDateCopy = $scope;
}]);

app.controller('ng-controller-table',['$scope','$http','$compile','$dTable',function($scope,$http,$compile,$dTable){
	var config = { 
	    id : "orderListTable", //table id 属性 
	    pageSize : "15",  //table一页记录数
		url : 'oms/order/orderList',  //请求url地址,返回格式{"obj":{"pageNum":1,"pageSize":10,"startRow":0,"endRow":10,"total":470,"pages":47,"result":[{"id":1,....},{"id":2,....}]}}
	    select : "false" //是否显示选择框
	    
	};
	var columns = [
	                { id : "orderNo", name : "订单号", width : "5%"}, 
	                { id : "createTime", name : "下单时间", width : "9%",innerHtml:"{{row.createTime|date:'yyyy-MM-dd HH:mm:ss'}}"}, 
	                { id : "receiver",name : "收货人",width : "4%"}, 
	                { id : "customer",name : "用户名",width : "4%"}, 
	                { id : "addr",name : "自提点",width : "10%"}, 
	                { id : "orderAmount",name : "订单总金额",width : "5%",innerHtml:"{{row.orderAmount|currency:'￥'}}"}, 
	                {id : "orderPay.payType",name : "付款方式",width : "6%",innerHtml:"<span ng-if='row.orderPay.payType == 0'>微信</span><span ng-if='row.orderPay.payType == 1'>支付宝</span><span ng-if='row.orderPay.payType == 2'>银联</span><span ng-if='row.orderPay.payType == 3'>其它</span>"} ,
	                {id : "deliverTime",name : "期望到货时间",width : "9%",innerHtml:"{{row.deliverTime|date:'yyyy-MM-dd HH:mm:ss'}}"} ,
	                { id : "status",name : "订单状态",width : "5%",innerHtml:"<span ng-if='row.status == 0'>已取消</span><span ng-if='row.status == 1'>待付款</span><span ng-if='row.status == 2'>已付款</span><span ng-if='row.status == 3'>待发货</span><span ng-if='row.status == 4'>已发货</span><span ng-if='row.status == 5'>待提货</span><span ng-if='row.status == 6'>已完成</span>"}, 
	                { id : "operationHtml",name : "操作",width : "15%",
	                	innerHtml:"<button ng-click='showSign(row.orderNo,row.sign)' class='sp-btn sp-btn-black-sm'>查看备注</button>" +
	                			"<button ng-click='showOrderDetail(row.orderNo)' class='sp-btn sp-btn-gray-sm'>详情</button>" +
	                			"<span ng-if='row.status == 1'><button ng-click='cancelOrderOne(row.orderNo,row.status)' class='sp-btn sp-btn-red-sm'>取消订单</button></span>"
	                }, 
		        ];
	var para = {};
	
	table = $dTable.init(config, columns).setData(para, $scope, $compile, $http);
	
	$scope.showSign = function(orderCode,sign){
		orderNo = orderCode;
		$("#msgOrder").html(orderCode);
		$("#signText").val(sign);
		showPopup('popUpLg',true);
	}
	//单个取消订单
	$scope.cancelOrderOne = function(orderno,orderStatus) {
		//将当前的订单编号放入全局变量，后面取消确认时要用此参数
		cancelOrderNo = orderno;
		if (orderStatus == 0) {
			alert("订单已取消")
			return;
		}
		if (orderStatus > 1) {
			alert("待付款的订单才可以取消")
			return;
		}
		$("#message1").html("确定取消订单？");
		$("#pop-logo span").html("取消订单");
		showPopup('popDialogue',true);
	}
	
	$scope.showOrderDetail = function(orderNo){
		
		showOrderDetailCopy(orderNo);
	}
}]);




$(function(){
	//是否点击过查询，点击过为2，未点击过为1
	var flagSearch = $("#flagSearch").val();
	
	var status =  "" ;
	
	var type = "";
	
	if(flagSearch == "2"){
		
		//将查询条件带回赋值给开始时间和截止时间
		$scopeDateCopy.startDate = $("#hxStartDate").val();
		
		$scopeDateCopy.endDate = $("#hxEndDate").val();
		
		status = $("#hxOrderStatus").val();
		
		type = $("#hxOrderType").val();
	}else if(flagSearch == "1"||!flagSearch){
		
		$("#searchReceiver").val('');
	}
	
	//初始化订单状态下拉框
	createSelect("orderStatus","oms/common/configOptions",status,"oms_order_status",true,"sp-select sp-select-md");
	//初始化订单类型下拉框
	createSelect("orderType","oms/common/configOptions",type,"oms_order_type",true,"sp-select sp-select-md");
	
	//获取查询条件
	
	var searchConditionParams = {};
	if(flagSearch == "2"){
		searchConditionParams = parseParams($("#searchConditionForm").serialize());
		//这里是为了处理参数中receiver有中文乱码问题。
		searchConditionParams.receiver = $("#searchReceiver").val();
		searchConditionParams.startDate = $("#hxStartDate").val();
		searchConditionParams.endDate = $("#hxEndDate").val();
	}else if(flagSearch == "1"||!flagSearch){
		searchConditionParams.flagSearch = '1';
	}
	var page = $("#page").val();
	if(!page){
		page = 1;
	}
	searchConditionParams.page=page;
	
	//查询表格数据
	table.query(searchConditionParams);
	
	
	//取消订单时用的全局变量
	var cancelOrderNo;
	
	//查看备注时使用
	var orderNo;
	
	
	
	
});
var flag = true;//当点击查询按钮调用时设置为true，其它调用设置为false

var flagz = false;
//查询按钮
function searchByCondition(){
	//判断开始时间不能小于结束时间
	if(getDateYYR($scopeDateCopy.startDate) > getDateYYR($scopeDateCopy.endDate)){
		alert("开始时间不能大于结束时间");
		return;
	}
	flagz = true;
	
	if(flag){
		$("#flagSearch").val('2');
	}
	var receiver = $("#searchReceiver").val();
	
	var searchParams = parseParams($("#searchConditionForm").serialize());
	//这里是为了处理参数中receiver有中文乱码问题。
	searchParams.receiver = receiver;
	
	if(flag){
		searchParams.page = 1;
	}
	table.query(searchParams);
}

//查看备注时的确定
function setSign(){
	flag = false;
	//将新添加的备注保存
	var url = "oms/order/setSign"
	var sign = $("#signText").val();
	
	$.ajax({
		url:url,
		data:{"orderCode":orderNo,"sign":sign},
		dataType:'text',
		type : 'post',
		success: function(result){
			exitPop('popUpLg');
			alert(result);
			if(result == "操作成功"){
				searchByCondition();
				flag = true;
				flagz = false;
			}
		}
	});
}


function showOrderDetailCopy(orderNo){
	if(flagz){
		$("#flagSearch").val('2');
	}
	var page = getPage();
	$("#isDorF").val(1);
	$("#page").val(page);
	$("#orderNo").val(orderNo);
	$("#searchConditionForm").attr("action","oms/order/orderDetail");
	$("#searchConditionForm").submit();
}

//点击表格中取消订单时，弹出框中确认取消按钮
function cancelOrderConfirm(){
	flag = false;
	exitPop('popDialogue');
	$.ajax({
		url:"oms/order/cancelOrder",
		data:{"orderNo":cancelOrderNo},
		dataType:'text',
		type : 'post',
		success: function(result){
			if(result == "SUCCESS"){
				alert("操作成功");
				searchByCondition();
				flag = true;
				flagz = false;
			}else{
				alert("操作失败");
			}
		}
	});
}



	
	

	
