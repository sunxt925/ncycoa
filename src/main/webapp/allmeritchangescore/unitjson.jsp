<%@page import="com.dao.system.UnitDao"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(new UnitDao().getCompanytreeJson("s"));
	response.getWriter().flush();
	response.getWriter().close();
 %>
