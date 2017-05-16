<%@ page language="java" import="com.db.DBObject,com.db.Parameter,java.util.*,java.io.*,com.entity.std.DocPolicy" pageEncoding="GBK"%>
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
		String type=smart.getRequest().getParameter("type");
		File ff=new File(Util.getfileCfg().get("uploadfilepath")+"policy");
		if (!ff.exists())
			ff.mkdirs();
		String pathtemp = Util.getfileCfg().get("uploadfilepath")+"policy";
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
			 String hour=""+c.get(c.HOUR);
			 String minute=""+c.get(c.MINUTE);
			 String date=year+"-"+month+"-"+day+" "+hour+":"+minute;
				String policyname="",classs="";
				if(type.equals("3")){
					policyname="标准化方针、目标";
					classs="stdgoal";
					}
			 String savename=classs+year+month+day+hour+minute+"."+ext;
			smart.getFiles().getFile(0).saveAs(pathtemp+java.io.File.separator+savename);
			System.out.println(pathtemp+java.io.File.separator+savename);
			String policypath="policy"+"/"+savename;
			DocPolicy policy=new DocPolicy(date,policyname,policypath,type);
			boolean flag=policy.insert();
		
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
	     				res += "alert('上传失败');";
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
