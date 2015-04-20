<%@page contentType="application/json;charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.performance.ParaDataHelper" %>

<%
	response.setContentType("application/json;charset=gb2312");
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	if(request.getParameter("indexcode") == null)
	{
		response.getWriter().write("[]");
		response.getWriter().flush();
		response.getWriter().close();
		return;
	}
	
	String indexCode = request.getParameter("indexcode");   
	response.getWriter().write(ParaDataHelper.getAllPeriodCode(indexCode));
	response.getWriter().flush();
	response.getWriter().close();
 %>
