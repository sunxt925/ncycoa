<%@page import="com.business.BuyReportItem"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    response.getWriter().write(new BuyReportItem().getBuyreportJson());
    response.getWriter().flush();
	response.getWriter().close();
 %>
