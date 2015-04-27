<%@ page contentType="text/html; charset=gb2312" language="java" pageEncoding="gb2312"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
	<title>JBPM4-行政处罚流程</title>
</head>
 <script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script language="javascript">

function F1(org){
	  var url='../std_search/std_orgposttab.jsp?unitccm='+org;
	  //addTab('个人指标定义查询','std_search/std_orgposttab.jsp?unitccm='+org+'&isIframe','folder')
	  window.open(url);
}

function F113(type){
	var url='policylist.jsp?type='+type;
	  window.open(url);
}
function Fbase(){
	var url='../std_base/searchpolicylist.jsp';
	  window.open(url);
}
function std(type){
		window.open('std_tab.jsp?stdclass='+type);
}
</script>
<body>
	<div >
 		<img src="stdstructure.jpg" style="text-align: center;position:absolute; left:100px; top:100px;" usemap="#chinaMap">
 		<!-- 给执行的节点加框 -->
 		<map name="chinaMap" id="chinaMap">  
    		<area shape="rect" coords="117,3,262,31" href="#" onClick="F113('1')" onFocus="this.blur()"/>  
    		<area shape="rect" coords="361,5,506,33" href="#" onClick="F12('')"/>  
    		<area shape="rect" coords="603,2,750,33" href="#" onClick="Fbase()"/>  
    		<area shape="rect" coords="33,247,66,357" href="#" onClick="F1('NC.01.12')"/>   
    		<area shape="rect" coords="154,246,186,357" href="#" onClick="F1('NC.01.30')"/>  
    		<area shape="rect" coords="252,251,285,359" href="#" onClick="F1('NC.01.03')"/>  
    		<area shape="rect" coords="313,250,346,358" href="#" onClick="F1('NC.01.11')"/>  
    		<area shape="rect" coords="419,250,452,358" href="#" onClick="F1('NC.01.10')"/>  
    		<area shape="rect" coords="502,250,535,358" href="#" onClick="F1('NC.01.05')"/>  
    		<area shape="rect" coords="551,250,583,358" href="#" onClick="F1('NC.01.08')"/>  
    		<area shape="rect" coords="600,250,632,358" href="#" onClick="F1('NC.01.09')"/>  
    		<area shape="rect" coords="650,250,680,358" href="#" onClick="F1('NC.01.01')"/>  
    		<area shape="rect" coords="698,250,730,358" href="#" onClick="F1('NC.01.07')"/>  
    		<area shape="rect" coords="754,250,784,358" href="#" onClick="F1('NC.01.13')"/>  
    		<area shape="rect" coords="801,250,834,358" href="#" onClick="F1('NC.01.06')"/>  
    		<area shape="rect" coords="852,250,882,358" href="#" onClick="F1('NC.01.04')"/>  
    		<area shape="rect" coords="901,250,933,358" href="#" onClick="F1('NC.01.02')"/>  
    		<area shape="rect" coords="362,455,507,485" href="#" onClick="std('js')"/>  
    		<area shape="rect" coords="591,455,739,486" href="#" onClick="std('gl')"/>  
    		<area shape="rect" coords="482,559,629,588" href="#" onClick="std('gz')"/>  
    	 </map>  
 	</div>
</body>
</html>