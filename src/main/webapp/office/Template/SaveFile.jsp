<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.sql.*,com.ftp.*,com.entity.ftp.*,java.io.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    
		//�˴���ò��ô˷���pstmt.setBytes(1,fs.getFileBytes());��ȡWord�ĵ��е��������ݣ���Ϊ��Java�д����Ķ���������ĳ����������Ƶ�
	//String name=(String)request.getSession().getAttribute("name");
	String path = getServletContext().getRealPath("/")+"taohong";
    FileSaver fs = new FileSaver(request, response);
    fs.saveToFile(path+ "/" + fs.getFileName());
   
	//fs.showPage(300,300);
   fs.close();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'SaveFile.jsp' starting page</title>

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