<%@ page contentType="text/html; charset=gb2312" language="java" pageEncoding="gb2312"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
	<title>南充烟草专卖局</title>
</head>

 <script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script language="javascript">
function F1(org,tittle){
	  var url='std_search/std_orgposttab.jsp?unitccm='+org+"&isIframe";
	  window.parent.addTab(tittle,url);
}

function F113(type){
	var url='std_structure/policylist.jsp?type='+type+"&isIframe";
	window.parent.addTab("企业方针目标",url);
}
function Fbase(){
	var url='std_base/searchpolicylist.jsp?isIframe';
	window.parent.addTab("基础标准",url);
}
function std(type,tittle){
	var url='std_structure/std_tab.jsp?stdclass='+type+"&isIframe";
	window.parent.addTab(tittle,url);
}
</script>
<body>
	<div >
 		<img src="stdstructure.jpg" style="text-align: center;position:absolute; left:100px; top:100px;" usemap="#chinaMap">
 		<!-- 给执行的节点加框 -->
 		<map name="chinaMap" id="chinaMap">  
    		<area shape="rect" coords="113,1,222,33" href="#" onClick="F113('1')" onFocus="this.blur()"/>  
    		<area shape="rect" coords="245,1,356,33" href="#" onClick="F12('')"/>  
    		<area shape="rect" coords="378,3,486,34" href="#" onClick="Fbase()"/>  
    		<area shape="rect" coords="26,188,50,299" href="#" onClick="F1('NC.01.12','卷烟营销')"/>   
    		<area shape="rect" coords="111,189,136,299" href="#" onClick="F1('NC.01.30','物流配送')"/>  
    		<area shape="rect" coords="171,190,196,300" href="#" onClick="F1('NC.01.03','专卖执法')"/>  
    		<area shape="rect" coords="221,190,245,300" href="#" onClick="F1('NC.01.11','专卖内管')"/>  
    		<area shape="rect" coords="288,188,313,300" href="#" onClick="F1('NC.01.10','安全管理')"/>  
    		<area shape="rect" coords="336,190,360,300" href="#" onClick="F1('NC.01.05','财务管理')"/>  
    		<area shape="rect" coords="371,190,395,300" href="#" onClick="F1('NC.01.08','政党工团')"/>  
    		<area shape="rect" coords="403,190,428,300" href="#" onClick="F1('NC.01.09','纪检监察')"/>  
    		<area shape="rect" coords="437,190,461,300" href="#" onClick="F1('NC.01.01','行政管理')"/>  
    		<area shape="rect" coords="471,190,495,300" href="#" onClick="F1('NC.01.07','人资资源')"/>  
    		<area shape="rect" coords="505,190,530,300" href="#" onClick="F1('NC.01.13','信息管理')"/>  
    		<area shape="rect" coords="538,190,564,300" href="#" onClick="F1('NC.01.06','内部审计')"/>  
    		<area shape="rect" coords="573,190,597,300" href="#" onClick="F1('NC.01.04','法规管理')"/>  
    		<area shape="rect" coords="607,190,630,300" href="#" onClick="F1('NC.01.02','企业管理')"/>  
    		<area shape="rect" coords="207,359,286,396" href="#" onClick="std('js','技术标准')"/>  
    		<area shape="rect" coords="288,359,462,393" href="#" onClick="std('gl','管理标准')"/>  
    		<area shape="rect" coords="297,419,369,453" href="#" onClick="std('gz','工作标准')"/>  
    	 </map>  
 	</div>
</body>
</html>