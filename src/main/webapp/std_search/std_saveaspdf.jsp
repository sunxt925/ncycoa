<%@ page language="java" 
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.util.*,java.io.*,com.ftp.*,com.entity.ftp.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
       String path = getServletContext().getRealPath("/")+"doc";
	FileSaver fs = new FileSaver(request, response);
	//保存文件到本地磁盘
	fs.saveToFile(getServletContext().getRealPath("/")+"doc"+"\\"+fs.getFileName());
		fs.close();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>打开PDF文件</title>
		<!--**************   卓正 PageOffice 客户端代码开始    ************************-->
		<script language="javascript" type="text/javascript">

</script>
		<!--**************   卓正 PageOffice 客户端代码结束    ************************-->
	</head>
	<body>
		<form id="form1">
		</form>
	</body>
</html>

