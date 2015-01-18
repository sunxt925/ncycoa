<%@page import="java.io.*"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page contentType="application/json;charset=utf-8" language="java"  errorPage="" %>
<%
response.setContentType("text/html;charset=utf-8");  
String status=(String) session.getAttribute("read");//获取上传进度百分比  
//System.out.println(status+"FileUploadStatus");  
out.println(status);//响应  
out.print("<script>window.close();</script>"); 
 %>
