<%@page import="com.db.DBObject"%>
<%@page import="edu.cqu.ncycoa.domain.MeetingInfo"%>
<%@page import="com.dao.system.StaffDao"%>
<%@page import="com.entity.index.AllMeritCollection"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="edu.cqu.ncycoa.domain.MeetingRoom"%>
<%@page import="java.util.List"%>
<%@page import="edu.cqu.ncycoa.util.SystemUtils"%>
<%@page import="edu.cqu.ncycoa.common.service.CommonService"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
DBObject db = new DBObject();

String sql = "select rnum from (select count(*) as rnum from NCYCOA_MEETING where  MEETING_ROOM='"+request.getParameter("roomno")+"'  and meeting_begindate between to_date('"+request.getParameter("startdate")+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+request.getParameter("enddate")+"','yyyy-mm-dd hh24:mi:ss')"
           +  "union    select count(*) as rnum from NCYCOA_MEETING where  MEETING_ROOM='"+request.getParameter("roomno")+"'  and meeting_enddate between to_date('"+request.getParameter("startdate")+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+request.getParameter("enddate")+"','yyyy-mm-dd hh24:mi:ss')"
           +   "union select count(*) as rnum from NCYCOA_MEETING where  MEETING_ROOM='"+request.getParameter("roomno")+"'  and meeting_begindate <=to_date('"+request.getParameter("startdate")+"','yyyy-mm-dd hh24:mi:ss') and meeting_enddate >=to_date('"+request.getParameter("enddate")+"','yyyy-mm-dd hh24:mi:ss')"
           +")"
           +  "group by rnum";
String msg = "";
if(Integer.parseInt(db.runSelectQuery(sql).get(0).getString("rnum")) >= 1){
msg = "0";	
}else{
	msg = "1";
}

	response.getWriter().write(msg);
	response.getWriter().flush();
	response.getWriter().close();
 %>
