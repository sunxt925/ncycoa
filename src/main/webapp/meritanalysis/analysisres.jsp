<%@page import="com.dao.system.MeritAnalysisDao"%>
<%@page import="com.entity.system.OrgPosition"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
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
</head>
<%
  UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
  String indexcode = request.getParameter("indexcode");
  String year = request.getParameter("year");
  String month = request.getParameter("month");
 %>
 <body>
   <%
      if(request.getParameter("class").equals("compare")){
    	  String objs = request.getParameter("objs");
    	  if(objs!=null&&!objs.equals("")){
    		  String[] obj = objs.split(",");
    		  out.write(new MeritAnalysisDao().getCompareRes(indexcode, year, month,obj));
    	  }else{
    		  out.write(new MeritAnalysisDao().getCompareRes(indexcode, year, month));
    	  }
    	  
      }else{
    	  out.write(new MeritAnalysisDao().getDeductRes(indexcode, year, month));
      }
      
      
   %>
</body>
</html>
