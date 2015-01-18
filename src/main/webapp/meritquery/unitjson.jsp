<%@page import="com.entity.index.AllMeritCollection"%>
<%@page import="com.entity.system.Staff"%>
<%@page import="com.dao.system.UnitDao"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    if(request.getParameter("class").equals("adminstaff")){
    	UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    	String orgcode=new Staff(u.getStaffcode()).getOrgcode();
    	response.getWriter().write(new UnitDao().getCompanytreeJson("s",AllMeritCollection.getcompanyByobject(orgcode)));
    }else{
    	response.getWriter().write(new UnitDao().getCompanytreeJson(request.getParameter("class")));
    }
	response.getWriter().flush();
	response.getWriter().close();
 %>
