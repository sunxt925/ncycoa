<%@page import="com.common.FileUpload"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Map"%>
<%@page import="com.common.Util"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page contentType="application/json;charset=utf-8" language="java"  errorPage="" %>
<%
response.setContentType("text/html;charset=utf-8");  
java.io.BufferedInputStream bis = null;     
java.io.BufferedOutputStream bos = null;     
String fileName = request.getParameter("filename");  
String filePath = Util.getfileCfg().get("uploadfilepath")+fileName; 
try {     
    long fileLength = new File(filePath).length();     

    response.setContentType("application/x-msdownload;");     
    response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"),"ISO8859-1"));     
    response.setHeader("Content-Length", String.valueOf(fileLength));     

    bis = new BufferedInputStream(new FileInputStream(filePath));     
    bos = new BufferedOutputStream(response.getOutputStream());
    out.clear();
    out = pageContext.pushBody();
    byte[] buff = new byte[2048];     
    int bytesRead;     
    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {     
        bos.write(buff, 0, bytesRead);     
    }     
} catch (Exception e) {     
    e.printStackTrace();    
   // out.print("<script>alert('下载失败');</script>"); 
} finally {     
    if (bis != null)     
        bis.close();     
    if (bos != null)     
        bos.close();     
}     
 %>
