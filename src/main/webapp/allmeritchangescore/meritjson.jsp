<%@page import="com.entity.index.StaffAllMerit"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    String str=new StaffAllMerit().getMeritChangeJson(request.getParameter("year"),request.getParameter("month"),request.getParameter("company"),request.getParameter("depart"));
    response.getWriter().write(str);
	response.getWriter().flush();
	response.getWriter().close();
 %>
