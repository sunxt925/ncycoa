<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
   String p=request.getParameter("file_path");
   String taohongPath = getServletContext().getRealPath("/")+p;//套红模板的路径
   String name= request.getParameter("filename");
  
   File file = new File(taohongPath);
   File[] files = file.listFiles();
     
     for(int i= 0;i<files.length;i++){
    
        if(files[i].getName().equals(name)){
           File file2 = new File(files[i].getAbsolutePath());
           file2.delete();
        }
     }
   request.getRequestDispatcher("TemplateManage.jsp").forward(request,response);
   
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
