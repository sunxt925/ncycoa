<%@page contentType="application/json;charset=gb2312" language="java" errorPage="" %>
<%@page import="com.performance.RightDataHelper" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	if(request.getParameter("taskType") == null || request.getParameter("staffcode") == null || request.getParameter("orgcode") == null){
		return;
	}
	
	RightDataHelper.delTask(request.getParameter("staffcode"), request.getParameter("taskType"), request.getParameter("orgcode"));
	
	response.getWriter().flush();
	response.getWriter().close();
 %>
