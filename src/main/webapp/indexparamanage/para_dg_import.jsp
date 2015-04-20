<%@ page contentType="application/json;charset=utf-8" language="java" errorPage=""%>
<%@ page import="com.performance.ParaDataHelper,org.apache.commons.fileupload.*,org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload,java.io.*,java.util.*"%>

<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(request);
			String periodcode = request.getParameter("periodcode");
			String indexcode = request.getParameter("indexcode");
			
			Iterator<FileItem> it = fileList.iterator();
			while (it.hasNext()) {
				FileItem item = it.next();
				if (!item.isFormField()) {
					request.getSession().setAttribute("paradata", ParaDataHelper.importXls(item.getInputStream()));
					item.getInputStream().close();
					//ParaDataHelper.insertData(ParaDataHelper.importXls(item.getInputStream()), request.getParameter("periodcode"), request.getParameter("indexcode"));
				}
			}
			
			response.getWriter().write("{\"success\":true,\"msg\":\"Excel数据导入成功\"}");
			response.getWriter().close();
		} catch (FileUploadException ex) {
			response.getWriter().write("{\"success\":true,\"msg\":\"文件上传失败\"}");
			response.getWriter().close();
		} catch (Exception e){
			response.getWriter().write("{\"success\":true,\"msg\":\"Excel文件解析失败,请确认输入的数据格式\"}");
			response.getWriter().close();
		}
%>
