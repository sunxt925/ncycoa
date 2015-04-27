<%@page import="com.dao.system.IndexDao"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(new IndexDao().getTreeNew(request.getParameter("indexcode")));
	response.getWriter().flush();
	response.getWriter().close();
 %>
