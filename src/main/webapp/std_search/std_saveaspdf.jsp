<%@ page language="java" 
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.util.*,java.io.*,com.ftp.*,com.entity.ftp.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
       String path = getServletContext().getRealPath("/")+"doc";
	FileSaver fs = new FileSaver(request, response);
	//�����ļ������ش���
	fs.saveToFile(getServletContext().getRealPath("/")+"doc"+"\\"+fs.getFileName());
		fs.close();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��PDF�ļ�</title>
		<!--**************   ׿�� PageOffice �ͻ��˴��뿪ʼ    ************************-->
		<script language="javascript" type="text/javascript">

</script>
		<!--**************   ׿�� PageOffice �ͻ��˴������    ************************-->
	</head>
	<body>
		<form id="form1">
		</form>
	</body>
</html>

