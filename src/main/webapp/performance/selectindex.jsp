<%@ page contentType="text/html;charset=gb2312" language="java" errorPage=""%>
<%@ page language="java" import="java.util.*"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.performance.ReviewDate"%>
<%@page import="com.performance.Review"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<%
String type = request.getParameter("objType");
String year = request.getParameter("year");
String period = request.getParameter("period");

UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
ReviewDate date = new ReviewDate(year, period);
Review review = new Review(user);
String content = review.getjson(date, type);
out.write(content);
%>
<script type="text/javascript">
    function ret(){
    	var api = frameElement.api, W = api.opener;
    	if($('#index_sel_dg').length){
    		var row = $('#index_sel_dg').datagrid('getSelected');
        	(api.data)({code:(row.indexcode), name:(row.indexname)});
    	}
    }
</script>
<input type="button" id="btn_ok" style="display: none" onclick="ret()">
</body>
</html>
