<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,java.io.*,com.entity.std.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<base target="_self">
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String DocNo="task-0";
	String storetype = "任务";
	String taskno = request.getParameter("taskno");
	

	

%>
<script language="javascript">
				//function Cancel() {
		            //window.open("PDFManage.jsp","_self");
		       // }
		
		        function getFocus() {
		            var str = document.getElementById("LocalFile").value;
		            if (str == "请输入文档名称") {
		                document.getElementById("LocalFile").value = "";
		            }
		        }
		        function lostFocus() {
		            var str = document.getElementById("LocalFile").value;
		            if (str.length <= 0) {
		                document.getElementById("LocalFile").value = "请输入文档名称";
		            }
		        }
		        function back(){
		        window.open('std_list.jsp','stdlist');
		        }
		        function upweb(){
		                var docclass=document.form1.storetype.value;
		                var DocNo=document.form1.DocNo.value;
		                var url='std_upweb.jsp?docclass='+docclass+'&DocNo='+DocNo;
		        		document.getElementById("iframe1").src =url;
		        }
		        
		        function uploadBegin(){
 theFeats = "height=200,width=320,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no";
 strAppVersion = navigator.appVersion;
 if (document.form1.pic.value != "")
 {
     if (strAppVersion.indexOf('MSIE') != -1 && strAppVersion.substr(strAppVersion.indexOf('MSIE')+5,1) > 4)
     { 
   winstyle = "dialogWidth=300px; dialogHeight:150px";
   window.showModelessDialog('WaitBar.jsp',window,winstyle);
     }
 }
}
</script>
<body > 
<form name="form1" id="form1" method="post" action="upweb.jsp" enctype="multipart/form-data" onsubmit="uploadBegin();">
<!--<form name="form1" id="form1" method="post" action="std_upweb.jsp" enctype="multipart/form-data">-->
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="10%"></td>


</tr>
<tr>
<td width="10%"></td>
<td width="40%" class="table_td_jb_iframe">&nbsp; &nbsp;                
    路  &nbsp;径：</td>
<td width="50%">
    <input type="file" name="pic" style="width:200px , height:100px"><br>
</td>	
</tr>
<tr>
    <td width="10%"></td>
	<td width="40%"></td>
    <td width="50%">
	    <input type="submit" value="上传"  > 
	    &nbsp; 
	    <input type="reset" value="重置"><input type="hidden" name="DocNo" value="<%=DocNo %>">
	    <input type="hidden" name="storetype" value="<%=storetype %>">
	    <input type="hidden" name="taskno" value="<%=taskno %>">
	    <input name="act" type="hidden" id="act" value="up">
	 </td>
</tr>

</table>

     <div style="width: 0px; height: 0px; overflow: hidden;">
        <iframe id="iframe1" name="iframe1" src="" height="0"></iframe>
    </div>
</form>
</body>
</HTML>