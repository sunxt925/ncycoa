<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*,com.entity.index.*,com.common.*" pageEncoding="gb2312"%>
<%
    String staffallmeritrecno=request.getParameter("recno");
    String flag = request.getParameter("flag");
    double changescore=Double.parseDouble(request.getParameter("changescore"));
    String changeobjectcode=request.getParameter("objectcode");
    
    if(new StaffAllMerit().changescore(changescore, staffallmeritrecno,flag))
       { UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
        new ChangeScoreLog().writeLog(IndexCode.getRecno("CS"), changeobjectcode, staffallmeritrecno, u.getStaffcode(),changescore);
    	response.getWriter().write("success");
       }
    else{
    	response.getWriter().write("fail");
        }
    response.getWriter().flush();
	response.getWriter().close();
 %>
