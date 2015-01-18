<%@page contentType="application/json;charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.performance.ParaDataHelper" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>

<%
	response.setContentType("application/json;charset=gb2312");
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	if(StringUtils.isEmpty(request.getParameter("indexcode")) || StringUtils.isEmpty(request.getParameter("periodcode"))){
		response.getWriter().write("[]");
		response.getWriter().flush();
		response.getWriter().close();
		return;
	}
	
	response.getWriter().write(ParaDataHelper.getPeriodDetail(request.getParameter("indexcode"), request.getParameter("periodcode"), request.getParameter("year"))); 
	response.getWriter().flush();
	response.getWriter().close();
 %>
