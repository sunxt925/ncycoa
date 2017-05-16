<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.net.ftp.FTP"%>
<%@page import="com.ftp.*"%>
<%@page import="com.entity.ftp.FtpFile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
   FtpUse ftpUse = new FtpUse();
   FtpFile file = new FtpFile();
   String f_url= request.getParameter("f_url");
   String f_name= request.getParameter("f_name");
   String f_id= request.getParameter("f_id"); 
   file.setId(f_id);
   file.setFilename(f_name);
   file.setPath(f_url);
   file.setCode("标准文档");
   file.setContenttpye("1");
   if(ftpUse.deleteFile(file))
   {
      System.out.println(" 删除成功");
   }
   request.getRequestDispatcher("ExcelManage.jsp").forward(request,response);
   
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  </body>
</html>
