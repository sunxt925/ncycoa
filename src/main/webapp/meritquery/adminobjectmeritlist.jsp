<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.common.Format"%>
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
String objectclass=request.getParameter("objectclass");
String year=request.getParameter("year");
String start_month=request.getParameter("start_month");
String end_month=request.getParameter("end_month");
String objectcode=request.getParameter("objectcode");
String companycode=request.getParameter("companycode");
String dataurl="meritjson.jsp?class="+objectclass+"&year="+year+"&start_month="+start_month+"&end_month="+end_month+"&objectcode="+objectcode+"&companycode="+companycode;
%>
<body>
   <div style="width: 100%">
   <table id="dg" class="easyui-datagrid" "
    data-options="url:'<%=dataurl %>',fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
    <%if(objectclass.equals("admin_s")){ %>
	    <th data-options="field:'mon',width:100">月份</th>
	    <th data-options="field:'keyindexscore',width:100">关键绩效得分</th>
	    <th data-options="field:'baseindexscore',width:100">基础管理/工作得分</th>
	    <th data-options="field:'commindexscore',width:100">通用指标</th>
	    <th data-options="field:'companymerit',width:100">公司绩效</th>
	    <th data-options="field:'departmerit',width:100">部门绩效</th>
	    <th data-options="field:'othermerit',width:100">其他</th>
	    <th data-options="field:'addscore',width:100">直接加减分</th>
	    <th data-options="field:'staffallmerit',width:100">综合得分</th>
	    <%}else{ %>
	    <th data-options="field:'mon',width:100">月份</th>
	    <th data-options="field:'depart',width:100">部门</th>
	    <th data-options="field:'keyindexscore',width:100">关键绩效得分</th>
	    <th data-options="field:'baseindexscore',width:100">基础管理/工作得分</th>
	    <th data-options="field:'commindexscore',width:100">通用指标</th>
	    <th data-options="field:'otherindexscore',width:100">其他</th>
	    <th data-options="field:'addscore',width:100">直接加减分</th>
	    <th data-options="field:'staffallmerit',width:100">综合得分</th>
	    <%} %>
    </tr>
    </thead>
    </table>
   
    </div>
     <div align="right">
   <input id="btnPrint" type="button" value="打印" onclick="javascript:window.print();"/>
  </div>
 <script type="text/javascript">
   
 </script>
</body>
</html>
