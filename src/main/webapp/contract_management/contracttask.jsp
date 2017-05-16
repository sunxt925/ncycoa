<%
String path = request.getContextPath();
response.sendRedirect(path+"/contract-management.htm?submitTask&flag=0&taskId="+request.getParameter("id"));
%>