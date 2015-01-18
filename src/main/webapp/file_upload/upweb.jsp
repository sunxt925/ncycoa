<%@ page language="java" import="com.db.DBObject,com.db.Parameter,java.util.*,java.io.*,jp.ne.so_net.ga2.no_ji.jcom.*,com.entity.ftp.*,com.entity.stdapply.*,com.ftp.*,com.zhuozhengsoft.pageoffice.FileMakerCtrl,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*" pageEncoding="utf-8"%>
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
    <body onLoad="location.href = url;" style='overflow:hidden;overflow-y:hidden'>

		
  </body>


<%
		String docno="";
		String docclass="";
		String pdfname="";
		String pdfPath="";
		String officePath="";
		String type="";
		String taskno="";
		
		com.cms.model.sysmng.login.User u=(com.cms.model.sysmng.login.User)request.getSession().getAttribute("USER");
		String participant = u.getZgdm();
		
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
		}else{
				smart.save("UploadTemp");
				docno=smart.getRequest().getParameter("DocNo");
				docclass=smart.getRequest().getParameter("storetype");
				type=filename.substring(filename.length()-4,filename.length());
			    taskno = smart.getRequest().getParameter("taskno");
	
		

		
		 FtpStoreFile file=new FtpStoreFile();//用于存储office文档
	     FtpStoreFile pdffile = new FtpStoreFile();//用于存储pdf文档
		
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
	         	 if(storefileno!=""&&storefileno!=null){
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
	     				File f=new File(officePath);
		    			f.delete();			
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
	     				File f=new File(officePath);
		    		    f.delete();	
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
	        String storefileno1="test";

	        String storefileno=ftp.FtpUpload(file,officeIn);
	        
	  	    if(storefileno!=""&&storefileno!=null&storefileno1!=""&&storefileno1!=null){
	  	        	try{		
	  	        	DBObject db = new DBObject();
	  	        	String sql = "update MONTHTASKPARTICIPANT set FINISHEDREPORT='"+storefileno+"' where TASKNO='"+taskno+"' and PARTICIPANTCODE='"+participant+"'";
	  	        	System.out.print(sql);
	  	        	Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	  	        	{ 
	  	        	new Parameter.String(storefileno),
	  	        	new Parameter.String(taskno),
	  	        	new Parameter.String(participant)
	  	        	
	  	        	};
	  	        	
	  	        	if(db.run(sql))
	  	        	{
	  	        	  System.out.print(true);
	  	        	}
	  	        	else
	  	        	{
	  	        	  System.out.print(false);
	  	        	}
	  	        	}
	  	        	catch(Exception e)
	  	        	{
	  	        	
	  	        	}
	  	    
	     		String res=""; 
	     				res += "MessageBox.Show(null,'上传成功！',null,'LogOK',null,1,'上传成功！');";
				res +="window.close();";
			//	res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
			//	  res += "parent.unittree.location.reload();";
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
	     				    File f=new File(officePath);
		    				f.delete();	
		    				File f1=new File(pdfPath);
		    				f1.delete();
	     }
		 }
		    
	    		}
	      
		//	request.getSession().setAttribute("savepdfname",pdfname);//必须用session，用fs.getfilename()的话如果name有空格就会只获得到空格

			//fmCtrl.setTagId("FileMakerCtrl1"); //此行必须
	      
%>



</html>
