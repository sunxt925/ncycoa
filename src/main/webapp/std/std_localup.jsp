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
		        function back(){
		        window.open('std_list.jsp','stdlist');
		        }
		        function upweb(){
		                var docclass=document.form1.storetype.value;
		                var DocNo=document.form1.DocNo.value;
		                var url='std_upweb.jsp?docclass='+docclass+'&DocNo='+DocNo;
		        		document.getElementById("iframe1").src =url;
		        }
		        
/*		        function uploadBegin(){
 strAppVersion = navigator.appVersion;
 if (document.form1.pic.value != "")
 {
  //   if (strAppVersion.indexOf('MSIE') != -1 && strAppVersion.substr(strAppVersion.indexOf('MSIE')+5,1) > 4)
 //    { 
  // winstyle = "dialogWidth=300px; dialogHeight:150px";
  // window.showModelessDialog('WaitBar.jsp',window,winstyle);
  var api = frameElement.api;
 // $.dialog.tips('文件上传中','1000000');
  api.content('hello').title('请耐心等待');
 // createwindowNoButton('请耐心等待','std/WaitBar.jsp','300px','150px');
 //    }
 }
}*/
function F8(){
document.all("Submit").click();
}
</script>
<body > 
<form name="form1" id="form1" method="post" action="std_upweb.jsp" enctype="multipart/form-data" >
<!--<form name="form1" id="form1" method="post" action="std_upweb.jsp" enctype="multipart/form-data">-->
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="30%" >&nbsp; &nbsp;               
    资源类：</td>
<td width="70%">
    <select name="storetype"  id="storetype" style="width:140px">
            <option value="标准类">标准类</option>
            <option value="个人类">个人类</option>
            <option value="机构类">机构类</option>
    </select>

</td>
</tr>
<tr>
<td width="30%" >&nbsp; &nbsp;                
    路  &nbsp;径：</td>
<td width="70%">
    <input type="file" name="pic" style="width:90px , height:100px"><br>
</td>	
</tr>
<!--<tr>-->
<!--    <td align="right" width="50%">-->
<!--	    <input type="submit"  name="Submit" value="上传"  style="display:none" > </td>-->
<!--	    <td align="center" width="50%"><input type="reset" value="重置"></td>-->
<!--	 </td>-->
<!--</tr>-->
<tr>
<td><a id="F8" style="display:none" href="#" onClick="F8()">上传</a><input type="submit"  name="Submit" value="上传"  style="display:none" ><input type="hidden" name="DocNo" value="<%=DocNo %>"><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdManageAction">
	    <input name="act" type="hidden" id="act" value="up"></td>
</tr>

</table>

     <div style="width: 0px; height: 0px; overflow: hidden;">
        <iframe id="iframe1" name="iframe1" src="" height="0"></iframe>
    </div>
</form>
</body>
</HTML>