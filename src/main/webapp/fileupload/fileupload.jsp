<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<style type="text/css">
    span{
        font-size: 12px;
    }
    table{
        border: 1px solid #f2f2f2;
    }
</style>
</head>
<%
String recno=request.getParameter("recno");
String staffcode=request.getParameter("staffcode");
%>
<body>
    
     
     <br>
     <br>
	      <form id="form1" action="upweb.jsp" method="post" enctype="multipart/form-data"  name="form">  
	        <input type="file" name="file" id="file"> 
	        <input type="hidden" id="filename" name="filename" value="das">
	        <input type="button" onclick="formSubmit()" value="提交" style="display: none">
	        
	    </form>  
             <span id="span"></span>  
            <table width="300px;" border="0"><tr><td>  
              <table id="table" height="20px;" style="background-color: gray;"><tr><td></td></tr></table>
             </td>  
             </tr>  
            </table>  
	       <input type="button" id="btn_ok" style="display: " onclick="ret()" value="上传">
	       
</body>
<script type="text/javascript">
   function ret(){
	 
    var date = new Date();
	    var api = frameElement.api;
	   var val=$("#file").val();
	   var v=new Array();
	   v=val.split("\\");
	   var timestamp = Date.parse( new Date());
	   var va = v[v.length-1].split(".");
	   var filename = date.getFullYear()+"\\"+date.getMonth()+"-"+date.getDay()+"\\"+timestamp+"."+va[va.length-1];
	   $("#filename").val(filename);
	   (api.data)({code:filename});
	   formSubmit();  
	  // window.close();
    }
   function callback(){  
       $.ajax({  
           type:"post",
           url:"uploadstatus.jsp",//响应文件上传进度的servlet  
           success:function(msg){
           document.getElementById("span").innerHTML="已上传："+msg;//显示读取百分比  
           document.getElementById("table").width=msg;//通过表格宽度 实现进度条  
           }  
       });  
   }  
   function formSubmit(){  
	   
       window.setInterval("callback()", 100);//每隔100毫秒执行callback  
       $('#form1').submit();
   }     
</script>
</html>