<%@page import="com.entity.index.Indexitem"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(new Indexitem().getindexjsonBytype(request.getParameter("indexclass").toUpperCase()));
	response.getWriter().flush();
	response.getWriter().close();
 %>
