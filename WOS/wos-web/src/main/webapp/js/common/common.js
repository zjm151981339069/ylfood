
/**
 * @param selectId 要将下拉框构建到<select>标签的ID  必须
 * @param url 请求数据的url              必须
 * @param code  basic_configure_options表中Configure_Item_name对应的值 必须
 * @param selectedVal  默认被选中的值
 * @param isHavePleSel 是否有"请选择" （true or false）  不必须                        
 * @param classVal 下拉框的样式名称，比如：sp-select sp-select-md  不必须
 */
function createSelect(selectId,url,selectedVal,code,isHavePleSel,classVal){
	var  optionHtml = "";//最后构建好的optionHtml，要放到<select>标签中的内容
	if(classVal){
		$("#"+selectId).addClass(classVal);
	}
	if(isHavePleSel){
		optionHtml += '<option value="">--请选择--</option>';
	}
	$.ajax({
		url:url,
		data:{"code":code},
		dataType:'json',
		async:false,
		type : 'post',
		success: function(result){
			$.each(result, function(i, n){
				if(n.optionValue==selectedVal){
					optionHtml += '<option value="'+n.optionValue+'" selected="selected">'+n.optionName+'</option>';
				}
				else{
					optionHtml += '<option value="'+n.optionValue+'">'+n.optionName+'</option>';
				}
			});
			//将生成的optionHtml添加到页面的select标签中。
			$("#"+selectId).html(optionHtml);
		}
	});
	
}

function createSelectCity(selectId,url,selectedVal,code,isHavePleSel,classVal){
	var  optionHtml = "";//最后构建好的optionHtml，要放到<select>标签中的内容
	if(classVal){
		$("#"+selectId).addClass(classVal);
	}
	if(isHavePleSel){
		optionHtml += '<option value="">--请选择--</option>';
	}
	$.ajax({
		url:url,
		data:{"code":code},
		dataType:'json',
		async:false,
		type : 'post',
		success: function(result){
			$.each(result, function(i, n){
				$.each(n.children,function(index,ele){
					if(ele.code==selectedVal){
						optionHtml += '<option value="'+ele.code+'" selected="selected">'+ele.name+'</option>';
					}
					else{
						optionHtml += '<option value="'+ele.code+'">'+ele.name+'</option>';
					}
				});
			});
			//将生成的optionHtml添加到页面的select标签中。
			$("#"+selectId).html(optionHtml);
		}
	});
	
}
function createSelectSite(selectId,url,selectedVal,code,isHavePleSel,classVal){
	var  optionHtml = "";//最后构建好的optionHtml，要放到<select>标签中的内容
	if(classVal){
		$("#"+selectId).addClass(classVal);
	}
	if(isHavePleSel){
		optionHtml += '<option value="">--请选择--</option>';
	}
	$.ajax({
		url:url,
		data:{"code":code},
		dataType:'json',
		async:false,
		type : 'post',
		success: function(result){
			if(result.length > 0){
				$.each(result, function(i, n){
					if(n.sCode==selectedVal){
						optionHtml += '<option value="'+n.sCode+'" selected="selected">'+n.name+'</option>';
					}
					else{
						optionHtml += '<option value="'+n.sCode+'">'+n.name+'</option>';
					}
				});
			}
			//将生成的optionHtml添加到页面的select标签中。
			$("#"+selectId).html(optionHtml);
		}
	});
	
}
//获取当前页码值
function getPage(){
	var li = $("#pagination li");
	for(var i = 0;i<li.length;i++){
		var a = li.eq(i).find("a");
		if(a.attr("style").indexOf("color") != -1){
			var ngClick = a.attr("ng-click");
			var text= getNum(ngClick);
			return text;
		}
	}
}
function getNum(text){
	var value = text.replace(/[^0-9]/ig,""); 
	return value;
}


function parseParams(urlParams){
	var string = urlParams.split('&');
	var res = {};
	for(var i = 0;i<string.length;i++){
	    var str = string[i].split('=');
	    res[str[0]]=str[1];
	}
	return res;
}
function getDateYYR(strDate){
	if(strDate){
		var st = strDate;
		var a = st.split("-");
		var date = new Date(a[0],a[1]-1,a[2]);
		return date;
	}else{
		return strDate;
	}
	
}