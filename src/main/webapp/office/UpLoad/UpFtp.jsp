<%@ page language="java" import="java.util.*,java.io.*,com.entity.ftp.*,com.ftp.*" pageEncoding="utf-8"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="javax.swing.JPanel"%>
<% 
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript">
        var xmlHttp;
		function CallBack(){
			if(xmlHttp.status==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
		function AfterDocumentUpload() {
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UpFtp.jsp' starting page</title>
    
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
                       正在上传，请稍后。。
                       <%

byte[] imageBytes = new byte[1024];
byte[] dest = new byte[0];
SmartUpload smart=new SmartUpload(); 
smart.initialize(pageContext);
smart.upload();
String FtpSaveName=smart.getRequest().getParameter("LocalFile");
String filename = smart.getFiles().getFile(0).getFileName();
//System.out.println("pppppppppppppp           "+filename);
if ( FtpSaveName== "请输入文档名称"){		
%>
<script type="text/javascript">
		alert("请输入文档名称");
		window.open("Localup.jsp","_self");
		</script>
<% 
	}
smart.save("UploadTemp");
//String Url=(String)request.getSession().getAttribute("URL");
String pathtemp = getServletContext().getRealPath("/")+"UploadTemp";
FtpUse ftp=new FtpUse();
String temp=pathtemp+"\\"+filename;
FileInputStream in=new FileInputStream(temp);
//request.getSession().setAttribute("delpath",temp);
         FtpFile file=new FtpFile();
	      file.setPath("test");
	     file.setCode("标准类");
	     String type=filename.substring(filename.length()-4,filename.length());
	     if(type.equals(".doc")||type.equals("docx")||type.equals(".dot")){
	        file.setContenttpye("0");
	        file.setFilename(FtpSaveName+".doc");
	     }else if(type.equals(".pdf")){
	         file.setContenttpye("3");//3  代表pdf文档，后面还要改的更标准一点
	         file.setFilename(FtpSaveName+".pdf");
	     }else if(type.equals(".ppt")){
	         file.setContenttpye("2");
	         file.setFilename(FtpSaveName+".ppt");
	     }else if(type.equals("xlsx")||type.equals(".xls")){
	         file.setContenttpye("1");
	         file.setFilename(FtpSaveName+".xls");
	     }
	      String PdfId=ftp.FtpUpload(file,in);//上传另存的pdf文件并返回其id 
	     if(PdfId!=""&&PdfId!=null){
	     %>
	     <script type="text/javascript">
	     		alert("上传成功");
	     		</script>
	     		<%
	     				    File f=new File(temp);
		    				f.delete();
	     		 %>
	     			     <script type="text/javascript">
	     		window.open("Localup.jsp","_self");
	     		</script>
	     <% 		
	     }else{
	     %>
	     <script type="text/javascript">
	     		alert("上传失败");
	     		</script>
	     		<%
	     				    File f=new File(temp);
		    				f.delete();
	     		 %>
	     			     <script type="text/javascript">
	     		window.open("Localup.jsp","_self");
	     		</script>
	     <% 
	     }
%>
  </body>
</html>
