<%@page import="java.io.*,com.common.Util"%>
<%
String filename = request.getParameter("f_name");
File file = new File(Util.getfileCfg().get("uploadfilepath")+filename);
FileInputStream fin = new FileInputStream(file);
byte[] buffer = new byte[1024];
int len = -1;
while((len = fin.read(buffer,0,1024)) != -1){
	response.getOutputStream().write(buffer, 0, len);
	out.clear();
	out = pageContext.pushBody();
}
fin.close();
%>