<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.entity.system.StaffInfo" %>
<%@page import="com.performance.RightDataHelper" %>
<%@page import="java.util.List" %>

<%
	response.setContentType("application/json;charset=gb2312");
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	if(request.getParameter("taskType") == null){
		response.getWriter().write("[]");
		response.getWriter().flush();
		response.getWriter().close();
		return;
	}
	
	StringBuffer sb = new StringBuffer();
	
	List<StaffInfo> staffs = RightDataHelper.getStaffByTaskType(request.getParameter("taskType"));
	sb.append("[");
	for(StaffInfo staff : staffs){
		sb.append("{\"staffcode\":").append("\""+staff.getCode()+"\"").append(",");
		sb.append("\"staffname\":").append("\""+staff.getName()+"\"").append(",");
		sb.append("\"tasktype\":").append("\""+ request.getParameter("taskType") +"\"").append("},");
	}
	if(sb.length() > 1) sb.delete(sb.length() - 1, sb.length());
    sb.append("]");
		
	response.getWriter().write(sb.toString());
	response.getWriter().flush();
	response.getWriter().close();
%>
