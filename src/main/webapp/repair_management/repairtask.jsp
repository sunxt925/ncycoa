<%
String path = request.getContextPath();
response.sendRedirect(path+"/repair_management.htm?submitTask&taskId="+request.getParameter("id"));
%>