<%@page import="com.entity.index.AllMeritCollection"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(AllMeritCollection.getCollectionJson(request.getParameter("year")));
	response.getWriter().flush();
	response.getWriter().close();
 %>
