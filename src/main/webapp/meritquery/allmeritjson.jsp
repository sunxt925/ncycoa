<%@page import="com.entity.index.StaffAllMerit"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	response.getWriter().write(new StaffAllMerit().getAllmeritJson(request.getParameter("year"),request.getParameter("staffname"),request.getParameter("orgcode")));
	response.getWriter().flush();
	response.getWriter().close();
 %>
