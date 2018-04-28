$(function(){
	$("[id$=sec]").find("li").click(function(){
		window.location.href = $(this).find("a").attr("href");
	});
	
})


function getEvent(){
    if(window.event){
        return window.event;
    }
    var f = arguments.callee.caller;
    do{
        var e = f.arguments[0];
        if(e && (e.constructor === Event || e.constructor===MouseEvent || e.constructor===KeyboardEvent)){
        	return e;
        }
    }while(f=f.caller);
}
	
function loadSecMenu(menu)
 {	 
	 var e = getEvent(); 
	 var path = document.getElementById('sta-path').value;
	 var obj = e.srcElement ? e.srcElement : e.target; 	
	 var imgObj = obj.getElementsByTagName("img")[0];	 	
	 var menuLi    = document.getElementById(menu + '-sec'); 	 
	 if(menuLi.style.display!="block") 
	 {
		 menuLi.style.display= "block";
		 imgObj.src= path + '/img/menu-open.png';
	 }
	 else
	 {
         menuLi.style.display= "none";
		 imgObj.src= path + '/img/menu-close.png';
	 }

 }
function getUrlPath(){
	var pathName = window.location.pathname;
    var index = pathName.indexOf("?");
    if(pathName.indexOf("/") != 0) return pathName;
    if(index == -1){
    	return pathName.substr(1);
    }else{
    	return pathName.substr(1,index-1);
    }
}
function setMenuSelected(){
	var str =  getUrlPath();
	$("ul.sp-menu-sec").find("li").find("a").each(function(index,ele){
		var fnName = ele.attributes["href"];
		if(fnName){
			fnName = fnName.nodeValue;
			fnName = fnName.substring(fnName.indexOf('/')+1).trim();
		}
		else{
			return;
		}
		if(str.length >= fnName.length && (str.indexOf(fnName) == str.length-fnName.length)){
			$(ele.parentNode).css('background-color','#99c731');
			if($(ele.parentNode.parentNode).hasClass('sp-menu-sec')){
				$(ele.parentNode.parentNode).show();
			}
		}
	});
}
setMenuSelected();
