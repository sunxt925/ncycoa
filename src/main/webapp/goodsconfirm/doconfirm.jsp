<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.db.*"%>
<%@page import="java.util.Date"%>
<%@page contentType="application/html;charset=gb2312" language="java"  errorPage="" %>
<%
DBObject db = new DBObject();
UserInfo u = (UserInfo)request.getSession().getAttribute("UserInfo");
Date confirmdate = new Date();
String sql = "update com_outstoreitem set isconfirm='1',confirmdate=to_date('"+Format.dateToStr(confirmdate)+"','yyyy-mm-dd') where handler='"+u.getStaffcode()+"' and  isconfirm is null or isconfirm='0' ";
if(db.run(sql)){
	response.getWriter().write("ȷ�ϳɹ�");
}else{
	response.getWriter().write("ȷ��ʧ��");
}
response.getWriter().flush();
response.getWriter().close();
%>