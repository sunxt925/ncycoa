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

function F1(org,tittle){
	  var positioncode='05.400X.21';
	  var positioncodezhuren='05.400X.20';
	  var stdupnewurl='std_orgstruct/std_orgmember.jsp?orgcode='+org+'&positioncode='+positioncode+'&positioncodezhuren='+positioncodezhuren;
	  createwindowNoButton(tittle,stdupnewurl,'450px','400px');
}
function F2(org,tittle){
	  var positioncode='05.410X.21';
	  var positioncodezhuren='05.410X.20';
	  var stdupnewurl='std_orgstruct/std_orgmember.jsp?orgcode='+org+'&positioncode='+positioncode+'&positioncodezhuren='+positioncodezhuren;
	  createwindowNoButton(tittle,stdupnewurl,'450px','400px');
}
</script>
<body>
	<div>
 		<img src="stdorg.jpg" style="text-align: center;position:absolute; left:100px; top:100px;" usemap="#chinaMap">
 		<!-- 给执行的节点加框 -->
 		<map name="chinaMap" id="chinaMap">  
    		<area shape="rect" coords="137,2,291,37" href="#" onClick="F1('NC.05','企业标准化管理委员会成员')" onFocus="this.blur()"/>  
    		<area shape="rect" coords="232,82,367,122" href="#" onClick="F2('NC.05.41','管理委员会办公室成员')"/>  
    		<area shape="rect" coords="54,82,189,122" href="#" onClick="F1('NC.05.40','标准化技术委员会成员')"/>  
    		<area shape="rect" coords="3,167,34,335" href="#" onClick="F1('NC.05.40.01','营销分技术委员会成员')"/>  
    		<area shape="rect" coords="55,168,86,334" href="#" onClick="F1('NC.05.40.02','专卖分技术委员会成员')"/>  
    		<area shape="rect" coords="104,167,135,335" href="#" onClick="F1('NC.05.40.03','安全分技术委员会成员')"/>  
    		<area shape="rect" coords="159,167,192,335" href="#" onClick="F1('NC.05.40.04','物流分技术委员会成员')"/>  
    		<area shape="rect" coords="212,168,245,335" href="#" onClick="F1('NC.05.40.05','基础管理分技术委员会成员')"/>  
    	 </map>  
 	</div>
</body>
</html>