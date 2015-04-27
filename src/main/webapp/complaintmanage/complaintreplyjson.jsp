<%@page import="com.entity.index.ComplaintReply"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(new ComplaintReply().getComplaintreplyJson(request.getParameter("staffcode")));
	response.getWriter().flush();
	response.getWriter().close();
 %>
