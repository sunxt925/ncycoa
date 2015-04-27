<%@page contentType="application/json;charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*,com.performance.service.*" errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.business.*"%>
<%@page import="com.entity.index.Indexitem"%>

<%
	response.setContentType("application/json;charset=gb2312");
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	if(request.getParameter("orgcode") == null)
	{
		response.getWriter().write("[]");
		response.getWriter().flush();
		response.getWriter().close();
		return;
	}
	
	String orgCode = request.getParameter("orgcode");    
	IndexRelService service = new IndexRelService();
	response.getWriter().write(service.getOrgsJson(orgCode));
	response.getWriter().flush();
	response.getWriter().close();
 %>
