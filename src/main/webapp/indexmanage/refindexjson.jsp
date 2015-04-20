<%@page import="com.entity.index.Indexitem"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(new Indexitem().getrefindexjson(request.getParameter("objectcode")));
    response.getWriter().flush();
	response.getWriter().close();
 %>
