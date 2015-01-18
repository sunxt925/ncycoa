<%@page import="com.dao.system.StaffDao"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	//response.getWriter().write(StaffDao.getMemberJson(request.getParameter("orgcode"),request.getParameter("positioncode")));
	response.getWriter().write(StaffDao.getMemberJson(request.getParameter("unitccm")));
	response.getWriter().flush();
	response.getWriter().close();
 %>
