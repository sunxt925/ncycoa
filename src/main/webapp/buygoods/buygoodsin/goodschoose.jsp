<%@ page contentType="text/html; charset=gb2312" %>
<%@page import="com.business.GoodsChoose"%>
<%
response.setContentType("text/html;charset=gb2312");
String s=request.getParameter("q");
out.print(GoodsChoose.getGoodsinfo(s));
 %>

