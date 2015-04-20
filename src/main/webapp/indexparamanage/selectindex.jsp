<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.performance.Review"%>
<%@page import="com.entity.system.UserInfo"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
</head>
 
<body style="width:100%;height:100%;margin:0;padding:0;overflow:hidden">
<table id="dg"></table>
<input type="button" id="btn_ok" style="display: none" onclick="ret()">
<script type="text/javascript">
	function onClickRow(){
		var row = $('#dg').datagrid('getSelected');
		if(row.indexcode=='company'||row.indexcode=='depart'||row.indexcode=='staff'){
			alert('选择数据无效，请重新选择');
		}
	}
	
    function ret(){
    	var api = frameElement.api;
    	var row = $('#dg').datagrid('getSelected');
    	(api.data)({code:(row.indexcode), name:(row.indexname)});
    }
</script>
<%
UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
Review review = new Review(user);
out.write(review.getindexjson());
%>
</body>
</html>
