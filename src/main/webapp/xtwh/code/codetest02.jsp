<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% String userid=request.getParameter("userid");
   String username=request.getParameter("username");

   %>
<body>

userid:<input type="text" name="userid" value="<%=userid %>"/>

username:<input type="text" name="username" value="<%=username %>"/>

</body>
</html>