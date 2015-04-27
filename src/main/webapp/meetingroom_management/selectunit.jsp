<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
                  request.getServerName()+":"+
		          request.getServerPort()+path+"/";
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
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
</head>
<%
String url = path+"/meritquery/unitjson.jsp?class=d";
%>
    <body class="easyui-layout">
  
   <div data-options="region:'center',split:true" style="width:200px;">
        <ul id="tt" class="easyui-tree" data-options="url:'<%=url %>',onClick:change">
        </ul>
       
    </div>
   <input type="button" id="btn_ok" style="display: none" onclick="ret()">
    </body>
    <script type="text/javascript">
    var roomNo='';
    function change(){
    	$('#tt').tree({
 			onClick: function(node){
 				roomNo = node.id;
 			}
 		});
    }
    function ret(){
     	var api = frameElement.api;
     	if(roomNo == ""){
     		alert("请选择单位");
     	}else{
     		(api.data)({code:(roomNo)});
     	}
     	
     	
     }
    </script>
</html>
