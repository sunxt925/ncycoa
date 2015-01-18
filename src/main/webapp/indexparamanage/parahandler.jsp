<%@ page contentType="application/json;charset=gb2312" language="java" errorPage=""%>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="com.performance.ParaDataHelper" %>
<%
response.setContentType("application/json;charset=gb2312");
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

JSONObject obj = JSONObject.fromObject(request.getParameter("d"));

response.getWriter().write("{\"status\":"+ParaDataHelper.insertData(obj)+"}");
response.getWriter().flush();
response.getWriter().close();
 %>
