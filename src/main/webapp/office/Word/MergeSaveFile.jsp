<%@ page language="java"
	import="java.sql.*,java.io.*,com.entity.ftp.*,javax.servlet.*,javax.servlet.http.*,java.text.SimpleDateFormat,java.util.Date,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="gb2312"%>
<%@page import="com.ftp.FtpUse"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	//定义保存对象
	FileSaver fs = new FileSaver(request, response);
	//FileSubject是文件主题
	String fileName = (String)request.getSession().getAttribute("mergename"); 
	String fileUrl = (String)request.getSession().getAttribute("FM_url"); 
	//System.out.println("FileSubject:"+fileName);
	
	FtpUse ftp = new FtpUse();
	//String url=(String)request.getSession().getAttribute("new_url");
	//System.out.println("ssssssssssssss      "+url);
	FtpFile file=new FtpFile();
	file.setFilename(fileName);
	file.setPath(fileUrl);
	file.setContenttpye("0");
	file.setCode("标准文档");//需要从另外一张表中获得。
	String new_id= ftp.FtpUpload(file,fs.getFileStream());
	//System.out.println("new_id:"+new_id);
	//设置保存结果 
	fs.setCustomSaveResult("ok");
	//fs.showPage(300,300);
	fs.close();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'SaveNewFile.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<br>
	</body>
</html>
