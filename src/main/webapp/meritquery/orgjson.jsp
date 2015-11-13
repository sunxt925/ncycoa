<%@page import="com.dao.system.UnitDao"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    String orgccm="";
    if(request.getParameter("orgccm").equals("NC.01")){
    	orgccm = "NC00";
    }else{
    	orgccm = request.getParameter("orgccm");
    }
	response.getWriter().write(new UnitDao().getorgJson(orgccm));
	response.getWriter().flush();
	response.getWriter().close();
 %>
