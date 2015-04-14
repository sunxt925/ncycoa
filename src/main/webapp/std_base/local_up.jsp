<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,java.io.*,com.entity.std.*" errorPage="" %>
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
	String DocNo=request.getParameter("docNo");
	//request.getSession().setAttribute("DocNo",DocNo);
	//DocMetaVersionInfo versioninfo=new DocMetaVersionInfo(DocNo);
	//String res=versioninfo.getDocVersionName();
%>


 <script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>
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
		        function callback(){
		        window.close();
		        }
		        function upweb(){
		                var docclass=document.form1.storetype.value;
		                var DocNo=document.form1.DocNo.value;
		                var url='std_upweb.jsp?docclass='+docclass+'&DocNo='+DocNo;
		        		document.getElementById("iframe1").src =url;
		        }
		        
function F8(){
document.all("Submit").click();
}
</script>
<body > 
<form name="form1" id="form1" method="post" action="upweb.jsp" enctype="multipart/form-data" >
<!--<form name="form1" id="form1" method="post" action="std_upweb.jsp" enctype="multipart/form-data">-->
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="30%" >&nbsp; &nbsp;               
    类型:</td>
    <td width="70%">
    <select name="type"  id="type" style="width:140px">
            <option value="2">基础标准</option>
    </select>

</td>
</tr>
<tr>
<td width="30%" >&nbsp; &nbsp;                
    路 径:</td>
<td width="70%">
    <input type="file" name="pic" style="width:90px , height:100px"><br>
</td>	
</tr>

<tr>
<td><a id="F8" style="display:none" href="#" onClick="F8()">上传</a></td>
<td><input type="submit"  name="Submit" value="上传"  style="display:none" ></td>

</tr>

</table>
</form>
</body>
</HTML>