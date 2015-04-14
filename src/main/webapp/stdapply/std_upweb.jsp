<%@ page language="java" import="java.util.*,java.io.*,com.entity.stdapply.*,com.entity.ftp.*,com.ftp.*,com.zhuozhengsoft.pageoffice.FileMakerCtrl,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*" pageEncoding="utf-8"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="javax.swing.JPanel"%>
<%@page import="com.entity.stdapply.DocApplyStore"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<% 
request.setCharacterEncoding("UTF-8");
String path0 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path0+"/";
%>
<%
String docno="";
String docclass="";
String pdfname="";
String temp="";
String type="";
byte[] imageBytes = new byte[1024];
byte[] dest = new byte[0];
SmartUpload smart=new SmartUpload(); 
smart.initialize(pageContext);
smart.upload();
//String FtpSaveName=smart.getRequest().getParameter("LocalFile");
String filename = smart.getFiles().getFile(0).getFileName();
	         	 java.util.Random r=new java.util.Random(); 
	         	 String s=String.valueOf(r.nextInt());
if(filename.equals("")||filename==null){
	     				String res=""; 
	     				res += "alert('上传失败,没有选择上传的文件');";
	     				res +="var api = frameElement.api;api.close();";
						//res +="window.close();";
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
}else{
smart.getFiles().getFile(0).saveAs("UploadTemp"+"\\"+s+filename);
//String Url=(String)request.getSession().getAttribute("URL");
String pathtemp = getServletContext().getRealPath("/")+"UploadTemp";
temp=pathtemp+"\\"+filename;
//FileInputStream in=new FileInputStream(temp);
docno=smart.getRequest().getParameter("DocNo");

         docclass=smart.getRequest().getParameter("storetype");
         if(docclass.equals("1")){
				docclass="标准类";
			}else if(docclass.equals("2")){
				docclass="个人类";
			}else{docclass="机构类";}
         //System.out.println("filename    :"+filename+"dcoclass    :"+docclass);
	     type=filename.substring(filename.length()-4,filename.length());


    		///////////////////////////////////////////
	     if(type.equals(".doc")||type.equals("docx")||type.equals(".dot")){
	     	type="doc";
	     }else if(type.equals(".ppt")||type.equals("pptx")){
			type="ppt";
	     }else if(type.equals("xlsx")||type.equals(".xls")){
	     	type="xls";
	     }else if(type.equals(".pdf")){
	     	type="pdf";
	     }else if(type.equals(".vsd")||type.equals(".vss")||type.equals(".vst")||type.equals(".vdx")||type.equals(".vsx")){
	     	type="vsd";
		 }
	      		DocApplyStore file=new DocApplyStore();
         		Calendar c = Calendar.getInstance();
				String year = "" + c.get(c.YEAR);
				String month = "" + (c.get(c.MONTH) + 1);
				String day = "" + c.get(c.DATE);
				String date=year+"-"+month+"-"+day;
				file.setCreatedate(date);
				file.setLastUpdatedate(date);
				file.setDocNo(docno);
         		//String docclass=smart.getRequest().getParameter("storetype");
	      		file.setDocClass(docclass);
	      		file.setFileContentType(type);//3  代表pdf文档，后面还要改的更标准一点
	         	file.setFileName(filename);
	         	 file.setStoreDirURL(pathtemp+"\\"+s+filename);
	         	boolean flag=false;
	         	flag=file.Insert();
	         	 if(flag==true){
	     				String res=""; 
	     				res += "alert('上传成功！');";
	     				res +="var api = frameElement.api;api.close();";
						//res +="window.close();";
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
	     		}else{
	     				String res=""; 
	     				res += "alert('上传失败');";
	     				res +="var api = frameElement.api;api.close();";
						//res +="window.close();";
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
	     		}
	     		}
	      
	      
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
 function upftp(){
 var docno=document.getElementById('docno').value;
  var docclass=document.getElementById('docclass').value;
   var filename=document.getElementById('filename').value;
   var pdfname=document.getElementById('pdfname').value;
 var url='std_upftp.jsp?docno='+docno+'&docclass='+encodeURI(encodeURI(docclass))+'&filename='+encodeURI(encodeURI(filename))+'&pdfname='+encodeURI(encodeURI(pdfname));
//location.href="../servlet/FileUpFtp?docno="+docno+"&docclass="+docclass+"&filename="+filename+"&pdfname="+pdfname; 
 window.open(url,'iframe1');
 //document.getElementById("iframe1").src =url;
 //alert(document.getElementById("iframe1").src);
   // alert(pdfname+filename+docclass+docno);
 }
</script>
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
		
  </body>
</html>
