<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.sql.*,com.ftp.*,com.entity.ftp.*,java.io.*,javax.swing.JOptionPane"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    //String path = request.getContextPath();
    //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	FileSaver fs = new FileSaver(request, response);
		//�˴���ò��ô˷���pstmt.setBytes(1,fs.getFileBytes());��ȡWord�ĵ��е��������ݣ���Ϊ��Java�д����Ķ���������ĳ����������Ƶ�
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
	if(type.equals(".pdf")){//����Ǳ���pdf�ļ�
	      String Name=name.substring(0, name.length() - 4) + ".pdf";//����pdf�ļ�����ʵ����.
	      FtpFile file=new FtpFile();
	      file.setFilename(Name);
	  //    file.setPath("test");
	      file.setCode("��׼��");
	      file.setContenttpye("pdf");//3  ����pdf�ĵ������滹Ҫ�ĵĸ���׼һ��
	      String PdfId=ftp.FtpUpload(file,fs.getFileStream());//�ϴ�����pdf�ļ���������id 
	      //System.out.println("ת��pdf��"+PdfId);
	      if(PdfId==null){
	            System.out.println("ת��pdfʧ��");
	      }else{
	      		request.getSession().setAttribute("pdf_id",PdfId);
	      		request.getSession().setAttribute("pdf_name",Name);
	      }
	}else{
		
	      		FtpFile file=new FtpFile();
	      		file.setId(id);
	      		file.setPath(url);
	      	//	System.out.println("wenjinxiugai         "+ftp.FtpUpload(file,fs.getFileStream()));
 		  		if(ftp.FtpUpload(file,fs.getFileStream())==null){//�޸��ļ�����ϴ���
 		        	System.out.println("�޸�ʧ��");
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