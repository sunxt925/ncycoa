<%@ page language="java" import="java.util.*,java.io.*,com.entity.ftp.*,com.ftp.*,com.zhuozhengsoft.pageoffice.FileMakerCtrl,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*" pageEncoding="utf-8"%>
<%@page import="javax.swing.JPanel"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@ page import="java.net.*" %>
<% 
request.setCharacterEncoding("UTF-8");
String path0 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path0+"/";
%>
<%
String docclass=URLDecoder.decode(request.getParameter("docclass"),"utf-8");
String filename=URLDecoder.decode(request.getParameter("filename"),"utf-8");
String pdfname=URLDecoder.decode(request.getParameter("pdfname"),"utf-8");
String docno=request.getParameter("docno");
String pathtemp = getServletContext().getRealPath("/")+"UploadTemp";
FtpStore ftp=new FtpStore();
String temp=pathtemp+"\\"+filename;
String temp1=pathtemp+"\\"+pdfname;
FileInputStream in=new FileInputStream(temp);
FileInputStream in1=new FileInputStream(temp1);
//String docno=smart.getRequest().getParameter("DocNo");
            FtpStoreFile file=new FtpStoreFile();
         		Calendar c = Calendar.getInstance();
				String year = "" + c.get(c.YEAR);
				String month = "" + (c.get(c.MONTH) + 1);
				String day = "" + c.get(c.DATE);
				String date=year+"-"+month+"-"+day;
				file.setCreatedate(date);
				file.setLastupdatedate(date);
         file.setDocno(docno);
         //String docclass=smart.getRequest().getParameter("storetype");
	      file.setDocclass(docclass);
	     String type=filename.substring(filename.length()-4,filename.length());
	     //另存pdf///////////////////////////////////
			//禁用右击事件
			
			    FtpStoreFile file1=new FtpStoreFile();
				file1.setCreatedate(date);
				file1.setLastupdatedate(date);
         		file1.setDocno(docno);
	      		file1.setDocclass(docclass);
    		///////////////////////////////////////////
	     
	     if(type.equals(".doc")){
	        file.setFilecontenttpye("doc");
	        file.setFilename(filename.substring(0,filename.length()-4)+type);
	        file1.setFilecontenttpye("pdf");
	        file1.setFilename(pdfname);
	     }else if(type.equals("docx")){
	        file.setFilecontenttpye("doc");
	        file.setFilename(filename.substring(0,filename.length()-4)+"doc");
	        file1.setFilecontenttpye("pdf");
	        file1.setFilename(pdfname);
	     }else if(type.equals(".dot")){
	        file.setFilecontenttpye("doc");
	        file.setFilename(filename.substring(0,filename.length()-4)+".doc");
	        file1.setFilecontenttpye("pdf");
	        file1.setFilename(pdfname);
	     }else if(type.equals(".ppt")){
	         file.setFilecontenttpye("ppt");
	         file.setFilename(filename.substring(0,filename.length()-4)+type);
	        file1.setFilecontenttpye("pdf");
	        file1.setFilename(pdfname);
	     }else if(type.equals("xlsx")){
	         file.setFilecontenttpye("xls");
	         file.setFilename(filename.substring(0,filename.length()-4)+"xls");
	        file1.setFilecontenttpye("pdf");
	        file1.setFilename(pdfname);
	     }else if(type.equals(".xls")){
	         file.setFilecontenttpye("xls");
	         file.setFilename(filename.substring(0,filename.length()-4)+type);
	        file1.setFilecontenttpye("pdf");
	        file1.setFilename(pdfname);
	     }
	      String storefileno=ftp.FtpUpload(file,in);
	      String storefileno1=ftp.FtpUpload(file1,in1);
	      
	      
	      
	     if(storefileno!=""&&storefileno!=null&storefileno1!=""&&storefileno1!=null){
	     		String res=""; 
	     				res += "MessageBox.Show(null,'上传成功！',null,'LogOK',null,1,'上传成功！');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
	     PrintWriter Out = response.getWriter();
	     			Out.print("<HTML><head>");
			Out.print("<META http-equiv=\"Content-Type\" content=\"text/html; charset=GB18030\">");
			Out.print("<TITLE>pagehandler</TITLE></head>");
			Out.print("<script src=\"../js/public/MessageBox.js\"></script>");
			Out.print("<script src=\"../js/public/Dialog.js\"></script>");
			Out.print("<body><script language='javascript'>");
			Out.print(res);
			Out.print("</script></body></html>");
	     				    File f=new File(temp);
		    				f.delete();		
		    				 File f1=new File(temp1);
		    				f1.delete();	
	     }else{
	     		String res=""; 
	     				res += "MessageBox.Show(null,'上传失败！',null,'LogOK','Error',1,'上传失败');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
	     PrintWriter Out = response.getWriter();
	     			Out.print("<HTML><head>");
			Out.print("<META http-equiv=\"Content-Type\" content=\"text/html; charset=GB18030\">");
			Out.print("<TITLE>pagehandler</TITLE></head>");
			Out.print("<script src=\"../js/public/MessageBox.js\"></script>");
			Out.print("<script src=\"../js/public/Dialog.js\"></script>");
			Out.print("<body><script language='javascript'>");
			Out.print(res);
			Out.print("</script></body></html>");
	     				    File f=new File(temp);
		    				f.delete();	
		    				File f1=new File(temp1);
		    				f1.delete();
	     }
 %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

	</head>		
	<script language="javascript" type="text/javascript">
	
</script>
	<body>


	</body>
</html>

