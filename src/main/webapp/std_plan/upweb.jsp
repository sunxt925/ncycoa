<%@ page language="java" import="com.db.DBObject,com.db.Parameter,java.util.*,java.io.*,com.entity.std.DocPlan" pageEncoding="GBK"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="javax.swing.JPanel"%>
<%@page import="com.common.Util"%>
<%@page import="org.apache.commons.net.ftp.FTP"%>
<%
request.setCharacterEncoding("GBK");
String path0 = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path0+"/";

		
		
		
		SmartUpload smart=new SmartUpload(); 
		smart.initialize(pageContext);
		smart.setAllowedFilesList("doc,docx");
		smart.upload();
		String filename = smart.getFiles().getFile(0).getFileName();
		String plandate=smart.getRequest().getParameter("plandate");
		String plantype=smart.getRequest().getParameter("plantype");

		String pathtemp = Util.getfileCfg().get("uploadfilepath")+"plan";
		 File ff=new File(Util.getfileCfg().get("uploadfilepath")+"plan");
			if (!ff.exists())
				ff.mkdirs();
		if(filename.equals("")||filename==null){
			    String res=""; 
			    res += "alert('上传失败,没有选择上传的文件');";
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
			String ext=smart.getFiles().getFile(0).getFileExt();
			
			Calendar c = Calendar.getInstance();
	   		 String year = "" + c.get(c.YEAR);
			 String month = "" + (c.get(c.MONTH) + 1);
			 String day = "" + c.get(c.DATE);
			 String date=year+"-"+month+"-"+day;
				String policyname="";
				if(plantype.equals("1"))
				{
					policyname="年度计划";
				}else if(plantype.equals("2")){
					policyname="规划";
				}
			 String savename=plandate+"."+ext;
			
			smart.getFiles().getFile(0).saveAs(pathtemp+java.io.File.separator+savename);
			String policypath=Util.getfileCfg().get("uploadfilepath")+"plan"+"/"+savename;
			DocPlan plan=new DocPlan(plandate,policyname,policypath,date);
			boolean flag=false;
			flag=plan.insert();
		
	         	 if(flag){
	     				String res=""; 
	     				res += "alert('上传成功！');";
	     				res +="var api = frameElement.api;api.close();";
	     				//res += "window.location.close();";
//  	     				res += "parent.policylist.location.reload();";
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
	     				res += "alert('Error',1,'上传失败');";
	     				res +="var api = frameElement.api;api.close();";
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
