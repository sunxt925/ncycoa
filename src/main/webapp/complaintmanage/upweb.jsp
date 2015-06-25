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
String fileName =fu.getFileName(files.get("file"));  
File file=new File(this.getServletContext().getRealPath("upload\\"+fileName));  
try {  
      
    files.get("file").write(file);  
      
} catch (Exception e) {  
    e.printStackTrace();  
}  
FileUpload.copyFile(this.getServletContext().getRealPath("upload\\"+fileName), "e:\\ftproot\\temp\\"+fileName);
file.delete();
out.println("上传成功");

//out.print("<script>alert('上传成功');</script>"); 
 %>
