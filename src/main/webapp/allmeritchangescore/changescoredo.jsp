<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*,com.entity.index.*,com.common.*" pageEncoding="gb2312"%>
<%
    String staffallmeritrecno=request.getParameter("recno");
    double changescore=Double.parseDouble(request.getParameter("changescore"));
    String changeobjectcode=request.getParameter("staffcode");
    if(new StaffAllMerit().changescore(changescore, staffallmeritrecno))
       { UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
        new ChangeScoreLog().writeLog(IndexCode.getRecno("CS"), changeobjectcode, staffallmeritrecno, u.getStaffcode(),changescore);
    	response.getWriter().write("success");
       }
    else{
    	response.getWriter().write("failure");
        }
    response.getWriter().flush();
	response.getWriter().close();
 %>
