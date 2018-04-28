var table;

var $scopeDateCopy;

var $scopeWishDateCopy;

var $scopeTableCopy;

var app = angular.module('app',['ngSanitize','dTable','datetimepicker']);

app.controller('ng-controller-datetime',['$scope',function($scope) {
	
	$scopeDateCopy = $scope;
	
}]);



app.controller('ng-controller-wishdatetime',['$scope',function($scope) {
	
	$scopeWishDateCopy = $scope;
	
}]);


app.controller('ng-controller-table',['$scope','$http','$compile','$dTable',function($scope,$http,$compile,$dTable){
	var config = { 
	    id : "houseOrderListTable", //table id 属性 
	    pageSize : "15",  //table一页记录数
		url : 'wos/houseOrder/houseOrderList',  //请求url地址,返回格式{"obj":{"pageNum":1,"pageSize":10,"startRow":0,"endRow":10,"total":470,"pages":47,"result":[{"id":1,....},{"id":2,....}]}}
	    select : "true" //是否显示选择框
	    
	};
	var columns = [ 
	                { id : "zone", name : "城市", width : "5%"}, 
	                { id : "orderNo", name : "订单号", width : "9%"}, 
	                { id : "createTime",name : "下单时间",width : "6%",innerHtml:"{{row.createTime|date:'yyyy-MM-dd HH:mm:ss'}}"}, 
	                { id : "receiver",name : "收货人",width : "4%"}, 
	                { id : "customer",name : "用户名",width : "4%"}, 
	                { id : "site",name : "自提点",width : "10%"}, 
	                {id : "deliverTime",name : "期望到货时间",width : "6%",innerHtml:"{{row.deliverTime|date:'yyyy-MM-dd HH:mm:ss'}}"} ,
	                {id : "taskTime",name : "任务生成时间",width : "9%",innerHtml:"{{row.taskTime|date:'yyyy-MM-dd HH:mm:ss'}}"} ,
	                { id : "status",name : "是否生成任务",width : "5%",innerHtml:"<span ng-if='row.status == 1'>已生成分拣任务</span><span ng-if='row.status == 2'>未生成分拣任务</span>"}, 
	                { id : "operationHtml",name : "操作",width : "5%",
	                	innerHtml:"<button ng-click='showDetail(row.orderNo)' class='sp-btn sp-btn-black-sm'>查看订单详情</button>" 
	                }, 
		        ];
	var para = {};
	
	table = $dTable.init(config, columns).setData(para, $scope, $compile, $http);
	
	hideOrShowCol("任务生成时间",true);
	
	hideOrShowCheckBox("sp-chk",true);
	
	$scopeTableCopy = $scope;
	
	$scope.showDetail = function(orderNo){
		
		showDetailCopy(orderNo);
	}
	
}]);


$(function(){
	
	//是否点击过查询，点击过为2，未点击过为1
	var flagSearch = $("#flagSearch").val();
	var status = "";
	var searchZone = "";
	var searchSite ="";
	if(flagSearch == "2"){
		//获取隐藏框的值，当返回到页面时将条件带回来
		status = $("#hxStatus").val();
		
		searchZone = $("#hxZone").val();
		
		searchSite = $("#hxSite").val();
		
		$scopeDateCopy.startDate = $("#hxStartDate").val();
		
		$scopeDateCopy.endDate = $("#hxEndDate").val();
		
		$scopeWishDateCopy.wishStartDate = $("#hxWishStartDate").val();
		
		$scopeWishDateCopy.wishEndDate = $("#hxWishEndDate").val();
	}else if(flagSearch == "1"||!flagSearch){
		
		$("#searchReceiver").val('');
		$("#searchOrderNo").val('');
	}
	
	//初始化"是否生成任务"下拉框
	createSelect("status","wos/common/configOptions",status,"wos_issctask_status",true,"sp-select sp-select-md");
	
	//初始化"城市"下拉框
	createSelectCity("searchZone","wos/common/getCityHasBusiness",searchZone,"",true,"sp-select sp-select-md");
	
	//初始化"自提点"下拉框 
	createSelectSite("searchSite","wos/common/getServiceSite",searchSite,searchZone,true,"sp-select sp-select-md");
	var searchConditionParams ={};
	if(flagSearch == "2"){
		//获取查询条件
		searchConditionParams = parseParams($("#searchConditionForm").serialize());
		//这里是为了处理参数中receiver有中文乱码问题。通过$("#searchConditionForm").serialize()获取到的中文乱码
		searchConditionParams.receiver = $("#searchReceiver").val();
		searchConditionParams.startDate = $("#hxStartDate").val();
		searchConditionParams.endDate = $("#hxEndDate").val();
		searchConditionParams.wishStartDate = $("#hxWishStartDate").val();
		searchConditionParams.wishEndDate = $("#hxWishEndDate").val();
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
//	hideOrShowCol("任务生成时间",true);
	hideOrShowCheckBox("sp-chk",true);
	
	document.onclick = function(e){
		var e = e||event;
		var className;
		var idName;
		if(e.originalTarget){
			className = e.originalTarget.className ;
		}else{
			className =e.srcElement.className;
		}
		if(className == 'sp-a-normal'){
			selectTaskStatus();
		}
	}
});

var flag = true;//当点击查询按钮调用时设置为true，其它调用设置为false

var flagz = false;
//查询按钮
function searchByCondition(){
	if($scopeDateCopy.startDate && $scopeDateCopy.endDate){
		//判断开始时间不能小于结束时间
		if(getDateYYR($scopeDateCopy.startDate) > getDateYYR($scopeDateCopy.endDate)){
			alert("下单开始时间不能大于下单结束时间");
			return;
		}
	}
	
	if($scopeWishDateCopy.wishStartDate && $scopeWishDateCopy.wishEndDate){
		
		if(getDateYYR($scopeWishDateCopy.wishStartDate) > getDateYYR($scopeWishDateCopy.wishEndDate)){
			alert("下单开始时间不能大于下单结束时间");
			return;
		}
	}
	flagz = true;
	if(flag){
		$("#flagSearch").val('2');
	}
	var searchParams = parseParams($("#searchConditionForm").serialize());
	//这里是为了处理参数中receiver有中文乱码问题。通过$("#searchConditionForm").serialize()获取到的中文乱码
	searchParams.receiver = $("#searchReceiver").val();
	if(flag){
		searchParams.page = 1;
	}
	table.query(searchParams);
	setTimeout(function(){
		selectTaskStatus();
	},100);
}

//查看详情
function showDetailCopy(orderNo){
	if(flagz){
		$("#flagSearch").val('2');
	}
	var page = getPage();
	
	$("#orderNo").val(orderNo);
	$("#page").val(page);
	$("#searchConditionForm").attr("action","wos/houseOrder/houseOrderDetail");
	$("#searchConditionForm").submit();
}

function scfjTask() {
	showPopup('popDialogue',true);
}

function confirmScfjTask(){
	flag = false;
	exitPop('popDialogue');
	var scfjTaskArr = $scopeTableCopy.tableDataSelected;
	if(scfjTaskArr.length <=0){
		alert("请勾选要操作的数据行");
		return;
	}
	var orderNos = [];
	for(var i = 0;i<scfjTaskArr.length;i++){
		orderNos.push(scfjTaskArr[i].orderNo);
	}
	$.ajax({
		url:"wos/houseOrder/createTask",
		data:{"orderNos":orderNos.join(',')},
		dataType:'text',
		type : 'post',
		success: function(result){
			if(result == "SUCCESS"){
				alert("操作成功");
				//成功之后刷新表格
				searchByCondition();
				flag = true;
				flagz = false;
			}else{
				alert("操作失败");
			}
		}
	});
}

//查询条件中"是否生成分拣任务"下拉框的选择事件
function selectTaskStatus(){
	
	var status = $("#status").val();
	if(status == '2'){//如果选择的是"未生成分拣任务选项"，则让"生成分拣任务"按钮显示
		$("#scfjTask").css("display","block");
		hideOrShowCol("任务生成时间",true);
		hideOrShowCheckBox("sp-chk",false);
	}else if(status =='1'){
		hideOrShowCol("任务生成时间",false);
		hideOrShowCheckBox("sp-chk",true);
		$("#scfjTask").css("display","none");
	}else if(status ==""){
		hideOrShowCol("任务生成时间",true);
		hideOrShowCheckBox("sp-chk",true);
		$("#scfjTask").css("display","none");
	}
	
}

function hideOrShowCheckBox(classVal,flag){
	if(flag){
		$("."+classVal).parent().hide();
	}else{
		$("."+classVal).parent().show();
	}
	
}
function hideOrShowCol(title,flag){
	var indexC;
	$("#houseOrderListTable table tbody tr th").each(function(index,ele){
		if(	$(ele).find("div").text()==title){
			if(flag){
				$(ele).hide();
			}else{
				$(ele).show();
			}
			indexC = index;
		}
	});
	$("#houseOrderListTable table tr").each(function(index,ele){
		$(ele).find("th").each(function(i,e){
			if(i == indexC){
				if(flag){
					$(e).hide();
				}else{
					$(e).show();
				}
			}
		});
		$(ele).find("td").each(function(i,e){
			if(i == indexC){
				if(flag){
					$(e).hide();
				}else{
					$(e).show();
				}
			}
		});
	});
}

//查询条件中"城市"选择后，自提点联动
function selectZone(){
	//获取所选的zone
	var zone = $("#searchZone").val();
	//每次选了城市之后，都让自提点显示"请选择",故第三个参数为"".
	createSelectSite("searchSite","wos/common/getServiceSite","",zone,true,"sp-select sp-select-md");
}

function alert(msg){
	$("#message").html(msg)
	showPopHint('popHint');
}
