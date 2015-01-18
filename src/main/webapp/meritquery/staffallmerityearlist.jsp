<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
String year=request.getParameter("year");
String staffname=request.getParameter("staffname");
String orgcode=request.getParameter("orgcode");
String dataurl="allmeritjson.jsp?year="+year+"&staffname="+staffname+"&orgcode="+orgcode;
%>
<body>

    <div style="width: 1200px">
    <table id="dg" class="easyui-datagrid" style="height:600px"
    data-options="url:'<%=dataurl %>',fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
       <th data-options="field:'orgname',width:100">部门</th>
       <th data-options="field:'positionname',width:130">岗位</th>
       <th data-options="field:'name',width:70">姓名</th>
	   <th data-options="field:'m01',width:100">一月</th>
	   <th data-options="field:'m02',width:100">二月</th>
	   <th data-options="field:'m03',width:100">三月</th>
	   <th data-options="field:'m04',width:100">四月</th>
	   <th data-options="field:'m05',width:100">五月</th>
	   <th data-options="field:'m06',width:100">六月</th>
	   <th data-options="field:'m07',width:100">七月</th>
	   <th data-options="field:'m08',width:100">八月</th>
	   <th data-options="field:'m09',width:100">九月</th>
	   <th data-options="field:'m10',width:100">十月</th>
	   <th data-options="field:'m11',width:100">十一月</th>
	   <th data-options="field:'m12',width:100">十二月</th>
    </tr>
    </thead>
    </table>
   
    </div>
    <div align="right">
   <input id="btnPrint" type="button" value="打印" onclick="javascript:window.print();"/>
  </div>

</body>
</html>
