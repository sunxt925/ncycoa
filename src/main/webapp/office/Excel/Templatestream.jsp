<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.sql.*,java.io.*,com.ftp.*;;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
		//******读取磁盘文件，输出文件流 开始*******************************
		byte[] imageBytes = new byte[1024];
		byte[] dest = new byte[0];
		String templateName=(String)request.getSession().getAttribute("templateName");
		String fileString=getServletContext().getRealPath("/")+"template"+"\\"+templateName;
		FileInputStream in= new FileInputStream(fileString);
		int count;
        while((count=in.read(imageBytes))!=-1){
            byte[] tmp = new byte[dest.length + count]; 
             System.arraycopy(dest, 0, tmp, 0, dest.length); 
             System.arraycopy(imageBytes, 0, tmp, dest.length, count); 
          dest = tmp; 
		}
		int fileSize = dest.length;
		response.reset();
		response.setContentType("application/msword"); // application/x-excel, application/ms-powerpoint, application/pdf
		response.setHeader("Content-Disposition",
						"attachment; filename=down.doc"); //fileN应该是编码后的(utf-8)
		response.setContentLength(fileSize);
        OutputStream outputStream = response.getOutputStream();
		outputStream.write(dest);
		outputStream.flush();
		outputStream.close();
		outputStream = null;

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'Openstream.jsp' starting page</title>

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
		<po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
	</body>
</html>
