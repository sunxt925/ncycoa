<%@ page language="java"
	import="java.sql.*,java.io.*,com.entity.ftp.*,javax.servlet.*,javax.servlet.http.*,java.text.SimpleDateFormat,java.util.Date,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="gb2312"%>
<%@page import="com.ftp.FtpUse"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	//���屣�����
	FileSaver fs = new FileSaver(request, response);
	//FileSubject���ļ�����
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
	file.setCode("��׼�ĵ�");//��Ҫ������һ�ű��л�á�
	String new_id= ftp.FtpUpload(file,fs.getFileStream());
	//System.out.println("new_id:"+new_id);
	//���ñ����� 
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
