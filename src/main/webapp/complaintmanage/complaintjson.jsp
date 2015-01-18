<%@page import="com.entity.index.Test"%>
<%@page import="com.entity.index.Complaint"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
   // Test.mm();
    response.getWriter().write(new Complaint().getComplaintJson());
	response.getWriter().flush();
	response.getWriter().close();
 %>
