<%
String path = request.getContextPath();
response.sendRedirect(path+"/contract-management.htm?submitTask&flag=1&taskId="+request.getParameter("id"));
%>