<%@page import="com.business.BuyGoodsItem"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    
    response.getWriter().write(new BuyGoodsItem().getBuyAppeventitemJson(request.getParameter("eventno"))); 
	response.getWriter().flush();
	response.getWriter().close();
 %>
