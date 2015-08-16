<%@page import="com.common.FileUpload"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page contentType="application/json;charset=utf-8" language="java"  errorPage="" %>
<%
response.setContentType("text/html;charset=utf-8");  
FileUpload fu=new FileUpload(); 
fu.setMap(request);//解析request  

Map<String,FileItem> files=fu.getFiles();  
Map<String,String> params = fu.getParams();
System.out.println(params.get("filename"));
String fileName =fu.getFileName(files.get("file")); 

File file=new File(this.getServletContext().getRealPath("upload\\"+fileName));  
try {  
      
    files.get("file").write(file);  
      
} catch (Exception e) {  
    e.printStackTrace();  
}  
File f = new File("d:\\ftproot\\temp\\"+params.get("filename"));
if (!f.getParentFile().exists())
	f.getParentFile().mkdirs();
FileUpload.copyFile(this.getServletContext().getRealPath("upload\\"+fileName), "d:\\ftproot\\temp\\"+params.get("filename"));
file.delete();
out.println("上传成功");

//out.print("<script>alert('上传成功');</script>"); 
 %>
