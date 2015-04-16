<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.sql.*,com.ftp.*,com.entity.ftp.*,java.io.*,javax.swing.JOptionPane"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    //String path = request.getContextPath();
    //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	FileSaver fs = new FileSaver(request, response);
		//此处最好不用此方法pstmt.setBytes(1,fs.getFileBytes());获取Word文档中的内容数据，因为在Java中创建的二进制数组的长度是有限制的
//	FtpUse ftp=new FtpUse();
	String url=(String)request.getSession().getAttribute("url");
	String[] urls=url.split("\\\\");
	String name=urls[urls.length-1];
	fs.saveToFile(request.getSession().getServletContext().getRealPath("UploadTemp/")+ "/" + name);
//fs.showPage(300,300);
//	String id=(String)request.getSession().getAttribute("id");
/*	String filename=fs.getFileName();
	//String saveChoose=(String)request.getSession().getAttribute("savekind");
	//System.out.println("      qqqqqqqqqqqqqqqqkkkkkkk          "+filename);
	String type=filename.substring( filename.length() - 4,filename.length());
//	System.out.println("                  "+type);
	if(type.equals(".pdf")){//如果是保存pdf文件
	      String Name=name.substring(0, name.length() - 4) + ".pdf";//生成pdf文件的真实名字.
	      FtpFile file=new FtpFile();
	      file.setFilename(Name);
	  //    file.setPath("test");
	      file.setCode("标准类");
	      file.setContenttpye("pdf");//3  代表pdf文档，后面还要改的更标准一点
	      String PdfId=ftp.FtpUpload(file,fs.getFileStream());//上传另存的pdf文件并返回其id 
	      //System.out.println("转成pdf："+PdfId);
	      if(PdfId==null){
	            System.out.println("转换pdf失败");
	      }else{
	      		request.getSession().setAttribute("pdf_id",PdfId);
	      		request.getSession().setAttribute("pdf_name",Name);
	      }
	}else{
		
	      		FtpFile file=new FtpFile();
	      		file.setId(id);
	      		file.setPath(url);
	      	//	System.out.println("wenjinxiugai         "+ftp.FtpUpload(file,fs.getFileStream()));
 		  		if(ftp.FtpUpload(file,fs.getFileStream())==null){//修改文件后的上传。
 		        	System.out.println("修改失败");
 		         }
	    
	}*/
	fs.setCustomSaveResult("ok");
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