<%@ page contentType="text/html; charset=gb2312" language="java" import="java.util.*,java.io.*,jp.ne.so_net.ga2.no_ji.jcom.*,com.entity.ftp.*,com.entity.stdapply.*,com.ftp.*,com.zhuozhengsoft.pageoffice.FileMakerCtrl,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*" pageEncoding="gb2312"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="javax.swing.JPanel"%>
<%@page import="org.apache.commons.net.ftp.FTP"%>

<% 
request.setCharacterEncoding("UTF-8");
String path0 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path0+"/";
%>



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
			    res += "alert('�ϴ�ʧ��,û��ѡ���ϴ����ļ�');";
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
				if(docclass.equals("1")){
					docclass="��׼��";
				}else if(docclass.equals("2")){
					docclass="������";
				}else{docclass="������";}
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
		

		
		 FtpStoreFile file=new FtpStoreFile();//���ڴ洢office�ĵ�
	     FtpStoreFile pdffile = new FtpStoreFile();//���ڴ洢pdf�ĵ�
	     FtpStoreFile swffile = new FtpStoreFile();//���ڴ洢flash�ĵ�
		
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
	      		file.setFilecontenttpye("pdf");//3  ����pdf�ĵ������滹Ҫ�ĵĸ���׼һ��
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
	      		swffile.setFilecontenttpye("swf");//3  ����pdf�ĵ������滹Ҫ�ĵĸ���׼һ��
	         	swffile.setFilename(swfname);
	         	FileInputStream in2=new FileInputStream(officePath);
	         	FtpStore ftp2=new FtpStore();
	         	String storefileno2=ftp2.FtpUpload(swffile,in2);
	         	
	         	 if(storefileno!=""&&storefileno!=null&&storefileno2!=""&&storefileno2!=null){
	     				String res=""; 
	     				res += "alert('�ϴ��ɹ���');";
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
	     				res += "alert('�ϴ�ʧ��');";
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
		        
		     }//.VSD��.VSS��.VST��.VDX��.VSX
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
	     				res += "alert('�ϴ��ɹ���');";
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
	     				res += "alert('�ϴ�ʧ��');";
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
	      
		//	request.getSession().setAttribute("savepdfname",pdfname);//������session����fs.getfilename()�Ļ����name�пո�ͻ�ֻ��õ��ո�

			//fmCtrl.setTagId("FileMakerCtrl1"); //���б���
	      
%>

