<%@page import="com.entity.index.MeritHelper"%>
<%@page import="edu.cqu.ncycoa.plan.PlanHelper"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.entity.system.StaffInfo" %>
<%@page import="java.util.List" %>

<%
	response.setContentType("application/json;charset=gb2312");
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	StringBuffer sb = new StringBuffer();
	
	List<StaffInfo> staffs = MeritHelper.getStaff();
	sb.append("[");
	for(StaffInfo staff : staffs){
		sb.append("{\"staffcode\":").append("\""+staff.getCode()+"\"").append(",");
		sb.append("\"staffname\":").append("\""+staff.getName()+"\"").append("},");
	}
	if(sb.length() > 1) sb.delete(sb.length() - 1, sb.length());
    sb.append("]");
		
	response.getWriter().write(sb.toString());
	response.getWriter().flush();
	response.getWriter().close();
%>
