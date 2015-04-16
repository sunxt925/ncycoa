<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.entity.index.Complaint"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    if(request.getParameter("staffcode")!=null&&!"".equals(request.getParameter("staffcode"))){
	   response.getWriter().write(new Complaint().getComplaintJson(request.getParameter("staffcode"),"",""));
    }else{
    	UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
	   response.getWriter().write(new Complaint().getComplaintJson(request.getParameter("class"),u.getStaffcode()));
    }
    response.getWriter().flush();
	response.getWriter().close();
 %>
