<%@ page language="java" import="java.util.*,java.io.*,jp.ne.so_net.ga2.no_ji.jcom.*,com.entity.ftp.*,com.entity.stdapply.*,com.ftp.*,com.zhuozhengsoft.pageoffice.FileMakerCtrl,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*" pageEncoding="utf-8"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="javax.swing.JPanel"%>
<%@page import="org.apache.commons.net.ftp.FTP"%>

<% 
request.setCharacterEncoding("UTF-8");
String path0 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path0+"/";
%>
<%!


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
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



<%
		String docno="";
		String docclass="";
		String pdfname="";
		String pdfPath="";
		String swfname="";
		String swfPath="";
		String officePath="";
		String type="";
		byte[] imageBytes = new byte[1024];
		byte[] dest = new byte[0];
		SmartUpload smart=new SmartUpload(); 
		smart.initialize(pageContext);
		smart.upload();
		String filename = smart.getFiles().getFile(0).getFileName();
		String pathtemp = getServletContext().getRealPath("/")+"UploadTemp";
		officePath=pathtemp+"\\"+filename;
		if(filename.equals("")||filename==null){
			    String res=""; 
			    res += "MessageBox.Show(null,'上传失败！',null,'LogOK','Error',1,'上传失败,没有选择上传的文件');";
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
				smart.save("UploadTemp");
				docno=smart.getRequest().getParameter("DocNo");
				docclass=smart.getRequest().getParameter("storetype");
				type=filename.substring(filename.length()-4,filename.length());
				if(type.equals(".doc")||type.equals(".dot")||type.equals(".ppt")||type.equals(".xls")){
			        pdfname=filename.substring(0,filename.length()-4)+".pdf";
			        swfname=filename.substring(0,filename.length()-4)+".swf";
			        pdfPath = pathtemp +"\\"+pdfname;
			        swfPath = pathtemp +"\\"+swfname;
			       	Office2Pdf office2Pdf=new Office2Pdf();
					office2Pdf.createPDF(officePath,pdfPath);
			       // createPDF(officePath,pdfPath);   
			       PdfToSwf pdftoswf= new PdfToSwf();
				   pdftoswf.PdfSwf(pdfPath);
		    	 }else if(type.equals("docx")||type.equals("xlsx")||type.equals("pptx")){
			        pdfname=filename.substring(0,filename.length()-4)+"pdf";
			        pdfPath = pathtemp +"\\"+pdfname;
			        swfname=filename.substring(0,filename.length()-4)+"swf";
			        swfPath = pathtemp +"\\"+swfname;
			        //createPDF(officePath,pdfPath);
			        Office2Pdf office2Pdf=new Office2Pdf();
					office2Pdf.createPDF(officePath,pdfPath);
					PdfToSwf pdftoswf= new PdfToSwf();
				    pdftoswf.PdfSwf(pdfPath);
		        }
		

		
		 FtpStoreFile file=new FtpStoreFile();//用于存储office文档
	     FtpStoreFile pdffile = new FtpStoreFile();//用于存储pdf文档
	     FtpStoreFile swffile = new FtpStoreFile();//用于存储flash文档
		
         Calendar c = Calendar.getInstance();
   		 String year = "" + c.get(c.YEAR);
		 String month = "" + (c.get(c.MONTH) + 1);
		 String day = "" + c.get(c.DATE);
		 String date=year+"-"+month+"-"+day;
		 if(type.equals(".pdf")){
		        file.setCreatedate(date);
				file.setLastupdatedate(date);
         		file.setDocno(docno);
         		//String docclass=smart.getRequest().getParameter("storetype");
	      		file.setDocclass(docclass);
	      		file.setFilecontenttpye("pdf");//3  代表pdf文档，后面还要改的更标准一点
	         	file.setFilename(filename);
	         	FileInputStream in=new FileInputStream(officePath);
	         	FtpStore ftp=new FtpStore();
	         	String storefileno=ftp.FtpUpload(file,in);
	         	
	         		swfname=filename.substring(0, filename.lastIndexOf("."))+".swf";
			        swfPath = pathtemp +"\\"+swfname;
	         		PdfToSwf pdftoswf= new PdfToSwf();
				    pdftoswf.PdfSwf(officePath);
				swffile.setCreatedate(date);
				swffile.setLastupdatedate(date);
         		swffile.setDocno(docno);
         		//String docclass=smart.getRequest().getParameter("storetype");
	      		swffile.setDocclass(docclass);
	      		swffile.setFilecontenttpye("swf");//3  代表pdf文档，后面还要改的更标准一点
	         	swffile.setFilename(swfname);
	         	FileInputStream in2=new FileInputStream(officePath);
	         	FtpStore ftp2=new FtpStore();
	         	String storefileno2=ftp2.FtpUpload(swffile,in2);
	         	
	         	 if(storefileno!=""&&storefileno!=null&&storefileno2!=""&&storefileno2!=null){
	     				String res=""; 
	     				res += "MessageBox.Show(null,'上传成功！',null,'LogOK',null,1,'上传成功！');";
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
	     				File f=new File(officePath);
		    			f.delete();	
		    			File f2=new File(swfPath);
		    			f2.delete();			
	     		}else{
	     				String res=""; 
	     				res += "MessageBox.Show(null,'上传失败！',null,'LogOK','Error',1,'上传失败');";
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
	     				File f=new File(officePath);
		    		    f.delete();	
		    		    File f2=new File(swfPath);
		    			f2.delete();	
	     		}
		 }else
		 {
		    if(type.equals(".doc")){
		        file.setFilecontenttpye("doc");
		        file.setFilename(filename.substring(0,filename.length()-4)+type);
	       
		     }else if(type.equals("docx")){
		        file.setFilecontenttpye("doc");
		        file.setFilename(filename.substring(0,filename.length()-4)+"doc");
		       
		     }else if(type.equals(".dot")){
		        file.setFilecontenttpye("doc");
		        file.setFilename(filename.substring(0,filename.length()-4)+".doc");
		      
		     }else if(type.equals(".ppt")){
		         file.setFilecontenttpye("ppt");
		         file.setFilename(filename.substring(0,filename.length()-4)+type);
		       
		     }else if(type.equals("pptx")){
		         file.setFilecontenttpye("ppt");
		         file.setFilename(filename.substring(0,filename.length()-4)+"ppt");
		       
		     }else if(type.equals("xlsx")){
		         file.setFilecontenttpye("xls");
		         file.setFilename(filename.substring(0,filename.length()-4)+"xls");
		       
		     }else if(type.equals(".xls")){
		         file.setFilecontenttpye("xls");
		         file.setFilename(filename.substring(0,filename.length()-4)+type);
		        
		     }//.VSD、.VSS、.VST、.VDX、.VSX
		     else if(type.equals(".vsd")||type.equals(".vss")||type.equals(".vst")||type.equals(".vdx")||type.equals(".vsx")){
		         file.setFilecontenttpye("vsd");
		         file.setFilename(filename.substring(0,filename.length()-4)+type);
		        
		     }
		    FileInputStream officeIn = new FileInputStream(officePath);
	        FtpStore ftp=new FtpStore();
	        file.setCreatedate(date);
	        file.setLastupdatedate(date);
	        file.setDocno(docno);
	        file.setDocclass(docclass);
	        String storefileno1="";
	        String storefileno2="";
	        if(!file.getFilecontenttype().equals("vsd")){
	        	    FileInputStream pdfIn = new FileInputStream(pdfPath);
	        		pdffile.setCreatedate(date);
	        		pdffile.setLastupdatedate(date);
	        		pdffile.setFilecontenttpye("pdf");
	        		pdffile.setFilename(pdfname);
	        		pdffile.setDocno(docno);
	        		pdffile.setDocclass(docclass);
	        		storefileno1=ftp.FtpUpload(pdffile,pdfIn);
	        			        	    FileInputStream swfIn = new FileInputStream(swfPath);
	        		swffile.setCreatedate(date);
	        		swffile.setLastupdatedate(date);
	        		swffile.setFilecontenttpye("swf");
	        		swffile.setFilename(swfname);
	        		swffile.setDocno(docno);
	        		swffile.setDocclass(docclass);
	        		storefileno2=ftp.FtpUpload(swffile,swfIn);
	        }else{
	        		storefileno1="novsd";
	        }
	        String storefileno=ftp.FtpUpload(file,officeIn);
	        
	  	    if(storefileno!=""&&storefileno!=null&&storefileno1!=""&&storefileno1!=null&&storefileno2!=""&&storefileno2!=null){
	     		String res=""; 
	     				res += "MessageBox.Show(null,'上传成功！',null,'LogOK',null,1,'上传成功！');";
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
	     				    File f=new File(officePath);
		    				f.delete();		
		    				 File f1=new File(pdfPath);
		    				f1.delete();
		    				 File f2=new File(swfPath);
		    				f2.delete();	
	     }else{
	     		String res=""; 
	     				res += "MessageBox.Show(null,'上传失败！',null,'LogOK','Error',1,'上传失败');";
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
	     				    File f=new File(officePath);
		    				f.delete();	
		    				File f1=new File(pdfPath);
		    				f1.delete();
		    				 File f2=new File(swfPath);
		    				f2.delete();
	     }
		 }
		    
	    		}
	      
		//	request.getSession().setAttribute("savepdfname",pdfname);//必须用session，用fs.getfilename()的话如果name有空格就会只获得到空格

			//fmCtrl.setTagId("FileMakerCtrl1"); //此行必须
	      
%>



</html>
