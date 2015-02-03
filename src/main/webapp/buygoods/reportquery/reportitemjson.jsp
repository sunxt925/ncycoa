<%@page import="com.business.BuyReportItem"%>
<%@page import="com.common.Format"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    
    response.getWriter().write(new BuyReportItem().getReportEventitemJson(request.getParameter("eventno")));
    
	response.getWriter().flush();
	response.getWriter().close();
 %>
