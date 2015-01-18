<%@ page contentType="application/json;charset=gb2312" language="java" errorPage=""%>
<%@ page import="com.performance.IndexDataHelper,org.apache.commons.fileupload.*,org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload,java.io.*,java.util.*"%>

<%
		response.setContentType("application/json;charset=gb2312");
		request.setCharacterEncoding("gbk");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(request);
			Iterator<FileItem> it = fileList.iterator();
			while (it.hasNext()) {
				FileItem item = it.next();
				if (!item.isFormField()) {
					IndexDataHelper.initImport(item.getInputStream(), request.getParameter("type"));
					item.getInputStream().close();
				}
			}
			
			response.getWriter().write("{\"success\":true,\"msg\":\"Excel数据导入成功\"}");
			response.getWriter().close();
		} catch (FileUploadException ex) {
			response.getWriter().write("{\"error\":true,\"msg\":\"文件上传失败\"}");
			response.getWriter().close();
		} catch (Exception e){
			response.getWriter().write("{\"error\":true,\"msg\":\""+ e.getMessage() +"\"}");
			response.getWriter().close();
		}
%>
