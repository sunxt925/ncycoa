<%@ page contentType="text/html; charset=gb2312" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>JBPM4-行政处罚流程</title>
</head>
 <script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscomponent/tools/outwindow.js"></script>
<script language="javascript">

function F1(org){
	  var positioncode='05.400X.21';
	  var positioncodezhuren='05.400X.20';
	  var stdupnewurl='std_orgstruct/std_orgmember.jsp?orgcode='+org+'&positioncode='+positioncode+'&positioncodezhuren='+positioncodezhuren;
	  createwindowNoButton('标准组织机构成员',stdupnewurl,'450px','400px');
}
function F2(org){
	  var positioncode='05.410X.21';
	  var positioncodezhuren='05.410X.20';
	  var stdupnewurl='std_orgstruct/std_orgmember.jsp?orgcode='+org+'&positioncode='+positioncode+'&positioncodezhuren='+positioncodezhuren;
	  createwindowNoButton('标准组织机构成员',stdupnewurl,'450px','400px');
}
</script>
<body>
	<div>
 		<img src="stdorg.jpg" style="text-align: center;position:absolute; left:100px; top:100px;" usemap="#chinaMap">
 		<!-- 给执行的节点加框 -->
 		<map name="chinaMap" id="chinaMap">  
    		<area shape="rect" coords="150,10,400,50" href="#" onClick="F1('NC.05')" onFocus="this.blur()"/>  
    		<area shape="rect" coords="0,115,160,165" href="#" onClick="F2('NC.05.41')"/>  
    		<area shape="rect" coords="372,108,540,160" href="#" onClick="F1('NC.05.40')"/>  
    		<area shape="rect" coords="264,213,294,400" href="#" onClick="F1('NC.05.40.01')"/>  
    		<area shape="rect" coords="354,215,384,400" href="#" onClick="F1('NC.05.40.02')"/>  
    		<area shape="rect" coords="445,216,477,400" href="#" onClick="F1('NC.05.40.03')"/>  
    		<area shape="rect" coords="535,216,566,401" href="#" onClick="F1('NC.05.40.04')"/>  
    		<area shape="rect" coords="626,214,654,399" href="#" onClick="F1('NC.05.40.05')"/>  
    	 </map>  
 	</div>
</body>
</html>