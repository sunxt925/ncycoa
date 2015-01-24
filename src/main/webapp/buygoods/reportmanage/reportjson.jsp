<%@page import="com.business.BuyReportItem"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    if(request.getParameter("state").equals("true")){
    	response.getWriter().write(new BuyReportItem().getBuyreportJson(true));
    }
     else{
    	response.getWriter().write(new BuyReportItem().getBuyReportitemJson(
    			 request.getParameter("reportno"), request.getParameter("projectcode"), request.getParameter("state")));
    } 
    response.getWriter().flush();
	response.getWriter().close();
 %>
