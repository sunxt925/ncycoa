<%@page import="edu.cqu.ncycoa.target.service.TargetService"%>
<%@page import="com.common.Format"%>
<%@page import="com.dao.system.IndexTreeTe"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
	String ccm=Format.NullToBlank(request.getParameter("ccm"));
	response.getWriter().write(new TargetService().getTreeNew(ccm));
	response.getWriter().flush();
	response.getWriter().close();
 %>
