<%
String path = request.getContextPath();
response.sendRedirect(path+"/contract-management.htm?submitTask&taskId="+request.getParameter("id"));
%>