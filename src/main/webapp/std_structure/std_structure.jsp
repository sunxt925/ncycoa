<%@ page contentType="text/html; charset=gb2312" language="java" pageEncoding="gb2312"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
	<title>�ϳ��̲�ר����</title>
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
	window.parent.addTab("��ҵ����Ŀ��",url);
}
function Fbase(){
	var url='std_base/searchpolicylist.jsp?isIframe';
	window.parent.addTab("������׼",url);
}
function std(type,tittle){
	var url='std_structure/std_tab.jsp?stdclass='+type+"&isIframe";
	window.parent.addTab(tittle,url);
}
</script>
<body>
	<div >
 		<img src="stdstructure.jpg" style="text-align: center;position:absolute; left:100px; top:100px;" usemap="#chinaMap">
 		<!-- ��ִ�еĽڵ�ӿ� -->
 		<map name="chinaMap" id="chinaMap">  
    		<area shape="rect" coords="113,1,222,33" href="#" onClick="F113('1')" onFocus="this.blur()"/>  
    		<area shape="rect" coords="245,1,356,33" href="#" onClick="F12('')"/>  
    		<area shape="rect" coords="378,3,486,34" href="#" onClick="Fbase()"/>  
    		<area shape="rect" coords="26,188,50,299" href="#" onClick="F1('NC.01.12','����Ӫ��')"/>   
    		<area shape="rect" coords="111,189,136,299" href="#" onClick="F1('NC.01.30','��������')"/>  
    		<area shape="rect" coords="171,190,196,300" href="#" onClick="F1('NC.01.03','ר��ִ��')"/>  
    		<area shape="rect" coords="221,190,245,300" href="#" onClick="F1('NC.01.11','ר���ڹ�')"/>  
    		<area shape="rect" coords="288,188,313,300" href="#" onClick="F1('NC.01.10','��ȫ����')"/>  
    		<area shape="rect" coords="336,190,360,300" href="#" onClick="F1('NC.01.05','�������')"/>  
    		<area shape="rect" coords="371,190,395,300" href="#" onClick="F1('NC.01.08','��������')"/>  
    		<area shape="rect" coords="403,190,428,300" href="#" onClick="F1('NC.01.09','�ͼ���')"/>  
    		<area shape="rect" coords="437,190,461,300" href="#" onClick="F1('NC.01.01','��������')"/>  
    		<area shape="rect" coords="471,190,495,300" href="#" onClick="F1('NC.01.07','������Դ')"/>  
    		<area shape="rect" coords="505,190,530,300" href="#" onClick="F1('NC.01.13','��Ϣ����')"/>  
    		<area shape="rect" coords="538,190,564,300" href="#" onClick="F1('NC.01.06','�ڲ����')"/>  
    		<area shape="rect" coords="573,190,597,300" href="#" onClick="F1('NC.01.04','�������')"/>  
    		<area shape="rect" coords="607,190,630,300" href="#" onClick="F1('NC.01.02','��ҵ����')"/>  
    		<area shape="rect" coords="207,359,286,396" href="#" onClick="std('js','������׼')"/>  
    		<area shape="rect" coords="288,359,462,393" href="#" onClick="std('gl','�����׼')"/>  
    		<area shape="rect" coords="297,419,369,453" href="#" onClick="std('gz','������׼')"/>  
    	 </map>  
 	</div>
</body>
</html>