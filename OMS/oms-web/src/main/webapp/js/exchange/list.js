 


//页面的表格对象
var table;

var $scopeDateCopy;

var app = angular.module('exchangeApp',['ngSanitize','dTable','datetimepicker']);

app.controller('ng-controller-datetime',['$scope',function($scope) {
	$scopeDateCopy = $scope;
}]);




app.controller('ng-controller-table',['$scope','$http','$compile','$dTable',function($scope,$http,$compile,$dTable){
	var config = { 
	    id : "exchangeOrderListTable", //table id 属性 
	    pageSize : "15",  //table一页记录数
		url : 'oms/exchange/exchangeOrderList',  //请求url地址,返回格式{"obj":{"pageNum":1,"pageSize":10,"startRow":0,"endRow":10,"total":470,"pages":47,"result":[{"id":1,....},{"id":2,....}]}}
	    select : "false" //是否显示选择框
	    
	};
	var columns = [ 
	                { id : "exCode", name : "换货单号", width : "5%"}, 
	                { id : "orderNo", name : "原订单号", width : "9%",innerHtml:"<a ng-click='clickOrderNo(row.orderNo)'>{{row.orderNo}}</a>"}, 
	                { id : "appTime",name : "换货单申请时间",width : "6%",innerHtml:"{{row.appTime|date:'yyyy-MM-dd HH:mm:ss'}}"/*innerHtml:"<a ng-click='exchangeApplyTime(row.apptime)'><img src='/img/oms/down.png'></a>"*/}, 
	                { id : "createTime",name : "订单下单时间",width : "6%",innerHtml:"{{row.createTime|date:'yyyy-MM-dd HH:mm:ss'}}"/*,innerHtml:"<a ng-click='createOrderTime(row.createtime)'><img src='/img/oms/down.png'></a>"*/}, 
	                { id : "receiver",name : "收货人",width : "10%"}, 
	                { id : "username",name : "用户名",width : "5%"}, 
	                {id : "site",name : "自提点",width : "6%"} ,
	                {id : "auditstatus",name : "审核状态",width : "9%",innerHtml:"<span ng-if='row.auditstatus == 0'><span style='color: rgb(255,198,0);'>待审核</span></span>" +
	                														   "<span ng-if='row.auditstatus == 1'><span style='color: #99c731;;'>审核通过</span></span>" +
	                														   "<span ng-if='row.auditstatus == 2'><span style='color: red;'>审核未通过</span></span>"
	                	
	                
	                } ,
	                { id : "operationHtml",name : "操作",width : "9%",
	                	innerHtml:"<span ng-if='row.auditstatus == 0'><button ng-click='audit(row.exId)' class='sp-btn sp-btn-black-sm'>审核</button></span>" +
	                			"<span ng-if='row.auditstatus != 0'><button ng-click='auditDetail(row.exCode,row.auditstatus,row.exId)' class='sp-btn sp-btn-gray-sm'>审核详情</button></span>" 
	                }, 
		        ];
	var para = {};
	
	table = $dTable.init(config, columns).setData(para, $scope, $compile, $http);
	
	//审核
	$scope.audit = function(exId){
		
		auditCopy(exId);
	}
	//审核详情
	$scope.auditDetail = function(exCode,status,exId){
		
		auditDetailCopy(exCode,status,exId);
	}
}]);




$(function(){
	
	//是否点击过查询，点击过为2，未点击过为1
	var flagSearch = $("#flagSearch").val();
	
	var auditStatus = "";
	if(flagSearch == "2"){
		//将查询条件带回赋值给开始时间和截止时间
		$scopeDateCopy.startDate = $("#hxStartDate").val();
		
		$scopeDateCopy.endDate = $("#hxEndDate").val();
		
		auditStatus = $("#hxAuditStatus").val();
		
	}else if(flagSearch == "1"||!flagSearch){
		
		$("#searchReceiver").val('');
	}
	//初始化订单状态下拉框
	createSelect("auditStatus","oms/common/configOptions",auditStatus,"oms_audit_status",true,"sp-select sp-select-md");
	
	var searchConditionParams = {};
	if(flagSearch == "2"){
		//获取查询条件
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



//审核
function auditCopy(exId){
	if(flagz){
		$("#flagSearch").val('2');
	}
	var page = getPage();
	$("#page").val(page);
	$("#exId").val(exId);
	$("#searchConditionForm").attr("action","oms/exchange/toEdit");
	$("#searchConditionForm").submit();
}

function auditDetailCopy(exCode,status,exId){
	if(flagz){
		$("#flagSearch").val('2');
	}
	var page = getPage();
	$("#page").val(page);
	$("#exId").val(exId);
	$("#searchConditionForm").attr("action","oms/exchange/toEdit");
	$("#searchConditionForm").submit();
}















	


