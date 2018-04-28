
//此详情页对应的换货ID
var exId;
//是点击审核通过时弹出框的确定按钮还是点击审核不通过时弹出框的确定按钮
var qdType;

var orderNo;

$(function(){
	$("#content").css('width',$("#content").width()-6);
	exId = $("#exId").val();
	orderNo = $("#orderNo").val();
});


//审核通过按钮
function showAuditView(type){
	qdType = type;
	$("#msgOrderExchange").html(exId);
	$("#auditviewText").val($("#auditView").val());
	showPopup('wq',true);
}


//返回按钮
function back(){
	$("#searchConditionForm").attr("action","oms/exchange/list");
	$("#searchConditionForm").submit();
}

//审核弹出框中的确定
function confirmAuditView(){
	
	var searchStr = $("#searchConditionForm").serialize();
	
	var exCode = $("#exCode").val();
	
	//换货商品列表的条数
	var exchangeGoodNum = $("#exchangeGoodNum").val();
	
	//如果点击的是审核通过，且有换货商品
	if(qdType == "auditTg"){
		if(parseInt(exchangeGoodNum)<=0){
			exitPop('wq');
			alert("没有要换货的商品");
			return;
		}else{
			var auditTgView = $("#auditviewText").val();
			if(!auditTgView){
				exitPop('wq');
				$("#message").html("请输入审核意见！")
				showPopHint('popHint');
				return;
			}
			var dataTg = {
					'exId':exId,
					'exCode':exCode,
					'auditview':auditTgView,
					'orderNo':orderNo
			}
			$.ajax({
				url:'oms/exchange/passOrderExchange',
				data:dataTg,
				dataType:'text',
				type : 'post',
				success: function(result){
					if(result == "SUCCESS"){
						exitPop('wq');
						alertC("操作成功",function(){
							window.location.href = "oms/exchange/list?"+encodeURI(searchStr);
						});
					}else {
						exitPop('wq');
						alert("操作失败");
					}
				}
			});
		}
	}else if(qdType == "auditBtg"){
		var auditBtgView = $("#auditviewText").val();
		if(!auditBtgView){
			exitPop('wq');
			$("#message").html("请输入审核意见！")
			showPopHint('popHint');
			return;
		}
		var dataBtg = {
				'exId':exId,
				'exCode':exCode,
				'auditview':auditBtgView
		}
		$.ajax({
			url:'oms/exchange/cancleOrderExchange',
			data:dataBtg,
			dataType:'text',
			type : 'post',
			success: function(result){
				if(result == "SUCCESS"){
					window.location.href = "oms/exchange/list?"+encodeURI(searchStr);
				}else {
					exitPop('wq');
					alert("操作失败");
				}
			}
		});
	}
}
