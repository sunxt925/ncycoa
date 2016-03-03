<%@page import="com.entity.index.Collectentity"%>
<%@page import="com.entity.index.AllMeritCollection"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.entity.index.MeritQueryHelper"%>
<%@page import="com.entity.system.Staff"%>
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
   List<String> res=MeritQueryHelper.queryMutilAllMeritRelation();
%>
<body>
   <div style="width: 100%">
    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'staffcode',width:25">员工编码</th>
    <th data-options="field:'staffname',width:25">姓名</th>
    <th data-options="field:'orgname',width:25">单位</th>
    <th data-options="field:'indexcode',width:25">综合绩效分组</th>
    </tr>
    </thead>
    <tbody>
	<%for(String s:res){ 
	   String[] tmps = s.split(",");
	%>							
			<tr>
			    <td><%=tmps[0] %></td>
			    <td><%=tmps[1] %></td>
			    <td><%=tmps[2] %></td>
			    <td><%=tmps[3] %></td>
			  <tr>
			  <%} %>
    </tbody>
    </table>
    
    <input type="button" id="kkk" onClick="sAlert('测试弹出层并锁屏效果');" style="display:none"/>
    </div>
 <script type="text/javascript">
   
 </script>
</body>
</html>
