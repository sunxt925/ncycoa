<%@page import="edu.cqu.ncycoa.target.service.TargetService"%>
<%@page import="com.entity.index.Indexitem"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(new TargetService().getindexjsonBytype(request.getParameter("indexclass").toUpperCase()));
	response.getWriter().flush();
	response.getWriter().close();
 %>
