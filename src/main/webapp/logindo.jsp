<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.dao.system.*"%>
<%@ page import="javax.servlet.http.Cookie"%>
<html>
<head>
<title></title>
</head>
<body>
	<%
		request.setCharacterEncoding("gb2312");
		javax.servlet.RequestDispatcher dis = null;
		//out.print(request.getParameter("availwidth"));
		String action = request.getParameter("act");
		if (action != null && action.equals("doLogin"))
		{
			String user_name = request.getParameter("user_name");
			String user_pwd = request.getParameter("passwd");
			try
			{
				User.login(user_name, user_pwd, request);
				response.sendRedirect("main.jsp");
			}
			catch (Exception e)
			{
				//得到登陆出错信息
				out.println("<script language=javascript>alert ('" + e.getMessage() + "');window.open('login.htm','_self');</script>");
			}
		}
		if (action != null && action.equals("logout"))
		{
			User.loginOut(request);
			response.sendRedirect("login.htm");
		}
		if (action == null)
		{
			out.println("请不要非法登录!!!!");
			//sout.print(Format.getMD5("123"));
		}
	%>
</body>
</html>
