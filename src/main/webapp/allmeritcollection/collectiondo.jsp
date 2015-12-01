<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.entity.index.AllMeritCollection"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
    String year=request.getParameter("year");
    String period=request.getParameter("period");
    String flag=request.getParameter("flag");
    UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    boolean res=true;
    if(flag.equals("staff")){
    	res = AllMeritCollection.meritCollect(year, period,u.getStaffcode(),request.getParameter("op"));
    }else{
    	res= AllMeritCollection.companyMeritCollect(year, period,u.getStaffcode(),request.getParameter("op"));
    }
    	
    if(res){
    	response.getWriter().write("汇总成功");
    }else{
    	
    	response.getWriter().write("汇总失败");
    }
	response.getWriter().flush();
	response.getWriter().close();
	
 %>
