<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.entity.index.AllMeritCollection"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
    String year=request.getParameter("year");
    String period=request.getParameter("period");
    UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    boolean res=AllMeritCollection.meritCollect(year, period,u.getStaffcode(),request.getParameter("op"));
    if(res){
    	response.getWriter().write("���ܳɹ�");
    }else{
    	
    	response.getWriter().write("����ʧ��");
    }
	response.getWriter().flush();
	response.getWriter().close();
	
 %>
