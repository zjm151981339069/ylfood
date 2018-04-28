var cancelOrderNo ;

var $scopeDate ;
var app = angular.module('app',['datetimepicker'])
.controller('ng-controller-datetime',['$scope',
	function($scope) {
	$scopeDate = $scope;
}]);


$(function(){
	
	$("#content").css('width',$("#content").width()-6);
	
	if(document.referrer.charAt(document.referrer.length - 8) == 'v'){
		 $("#send").css('display',''); 
	};
	
	cancelOrderNo = $("#cancelOrderNo").val();
	
});


function back(){
	//是从哪个页面进入到订单详情页
	var isDorF = $("#isDorF").val();
	if(isDorF == "1"){
		$("#searchConditionForm").attr("action","oms/order/list");
	}else if(isDorF == "2"){
		$("#searchConditionForm").attr("action","oms/deliver/list");
	}
	$("#searchConditionForm").submit();
}
function setTime(orderNo,status){
	
	var setDeliverTime = $scopeDate.setDate;
	var setDeliverTimeDate = getDate(setDeliverTime);
	if(setDeliverTimeDate < new Date()){
		alert("配送时间应大于当前时间");
		return;
	}
	if(setDeliverTime){
		var url = "oms/order/setDeliverTime";
		$.ajax({
			url:url,
			data:{"orderCode":orderNo,"deliverTime":setDeliverTime,"status":status},
			dataType:'text',
			type : 'post',
			success: function(result){
				if(result == "操作成功"){
					alert("操作成功");
					$("#delivertime").html(setDeliverTime);
					$("#setDateDiv").css('display','none');
				}else{
					alert("操作失败");
				}
			}
		});
	}else{
		alert("请选择时间");
	}
}

function openWin(){
	var top = $("#delivertime").offset().top;
	var left = $("#delivertime").offset().left;
	$("#setDateDiv").css('top',top+25);
	$("#setDateDiv").css('left',left-5);
	$("#setDateDiv").css('display','block');
	$("#setDate").focus();
}

function colseWin(){
	$("#setDateDiv").css('display','none');
}

//取消订单 
function cancelOrder() {
	$("#message1").html("确定取消订单？")
	showPopup('popDialogue',true);
}

//点击表格中取消订单时，弹出框中确认取消按钮
function cancelOrderConfirm(){
	exitPop('popDialogue');
	$.ajax({
		url:"oms/order/cancelOrder",
		data:{"orderNo":cancelOrderNo},
		dataType:'text',
		type : 'post',
		success: function(result){
			if(result == "SUCCESS"){
				alert("操作成功");
				back();
			}else{
				alert("操作失败");
			}
		}
	});
}
