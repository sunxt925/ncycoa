<%@page import="com.common.CodeDictionary"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
    
     <div >
         <label for="name" style="font-size: 12px">操作时间：<%=request.getParameter("year") %>/<%=request.getParameter("month").substring(1, 3) %></label>
     </div>
     <br>
     <br>
     <div >
         <label for="name" style="font-size: 12px">加减分对象：<%=CodeDictionary.syscode_traslate("base_staff", "staffcode","staffname" , staffcode) %></label>
     </div>
     <br>
     <br>
	 <div >
         <label for="name" style="font-size: 12px">直接加减分:</label>
         <input id="changescore" class="easyui-validatebox" type="text" name="changescore"  value="0" data-options="required:true" />
     </div>
	<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		
</body>
<script type="text/javascript">
   function ret(){
	   var api = frameElement.api;
 	   var score=$("#changescore").val();
 	   var recno="<%=recno%>";
 	   var path="<%=path%>";
 	   var staffcode="<%=staffcode%>";
 	   $.post(path+"/allmeritchangescore/changescoredo.jsp",
			    {
			      recno:recno,
			      changescore:score,
			      staffcode:staffcode
			    },
				 function(data,status){
			    	if(data=="success")
			    		(api.data)({code:"refresh"});
			    	else
			    		alert("加减分失败");
			    }
			    ); 
 	 
    }
</script>
</html>