<%@page import="com.entity.index.AllMeritGroupMember"%>
<%@page import="com.entity.index.AllMeritGroup"%>
<%@page import="com.entity.index.ReferPara"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    AllMeritGroup allMeritGroup=AllMeritGroupMember.getAllmeritMode(request.getParameter("objectcode"));
    StringBuilder sb=new StringBuilder();
    sb.append("");
    if(allMeritGroup==null){
    	sb.append("<label><span>����δ�����ۺϼ�Ч���飬��ѯ����������Ϣ������</span></label><br><br><br><br>");
    }else{
    	sb.append("<label><span>ģ���ţ�&nbsp;&nbsp;&nbsp;&nbsp;"+allMeritGroup.getGroupNo()+"</span></label><br><br><br><br>");
    	sb.append("<label><span>ģ�����ƣ�&nbsp;&nbsp;&nbsp;&nbsp;"+allMeritGroup.getGroupName() +"</span></label><br><br><br><br>");
    	sb.append("<label><span>ģ�幹�ɣ�&nbsp;&nbsp;&nbsp;&nbsp;"+allMeritGroup.getAllmeritfunc()+"</span></label><br><br><br><br>");
		sb.append("<label><span>ģ��˵����&nbsp;&nbsp;&nbsp;&nbsp;"+allMeritGroup.getMemo() +"</span></label><br><br><br><br>");
    }
	response.getWriter().write(sb.toString());
	response.getWriter().flush();
	response.getWriter().close();
 %>
