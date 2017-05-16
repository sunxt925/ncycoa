<%@page contentType="text/html;charset=gb2312" language="java" errorPage=""%>
<%@ page import="com.entity.system.UserInfo"%>
<%@ page import="com.performance.*"%>
<%@ page language="java" import="java.util.*"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body style="width:100%;height:100%;margin:0;padding:0;overflow:hidden">
<table id="tt"></table>
<!-- 中 -->
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<%
		String type = request.getParameter("objType");
		String year = request.getParameter("relateyear");
		String period = request.getParameter("periodcode");
		String indexcode = request.getParameter("indexcode");
		
		ReviewDate date = new ReviewDate(year, period);
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		
		Review review = new Review(user);
		ReviewTask task = review.getReviewTask(date, indexcode, type);
		if (request.getParameter("indexcode") != null) {
			out.write(IndexDataHelper.getResultsTreeGrid(task));
		}
%>
</body>
</html>