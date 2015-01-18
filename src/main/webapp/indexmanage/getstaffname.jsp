<%@page import="com.entity.system.StaffInfo"%>
<%@page import="com.entity.index.AllMeritGroupMember"%>
<%@page import="com.entity.index.AllMeritGroup"%>
<%@page import="com.entity.index.ReferPara"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    StaffInfo staffInfo=new StaffInfo(request.getParameter("staffcode"));
    response.getWriter().write(staffInfo.getName());
	response.getWriter().flush();
	response.getWriter().close();
%>
