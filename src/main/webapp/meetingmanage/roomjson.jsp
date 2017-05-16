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
    UserInfo userInfo = (UserInfo)request.getSession().getAttribute("UserInfo");
    String company =  AllMeritCollection.getcompanyByobject(StaffDao.getOrgcode(userInfo.getStaffcode()));
    if(company.equals("NC.01.00"))
    	company="NC.00";
    CommonService commonService = SystemUtils.getCommonService();
    List<MeetingRoom> rooms = commonService.findAllEntities(MeetingRoom.class);
    StringBuilder sbBuilder = new StringBuilder();
    sbBuilder.append("");
    sbBuilder.append("[");
    for(MeetingRoom room : rooms){
    	if(room.getBelongOrg().equals(company)){
    	sbBuilder.append("{");
    	sbBuilder.append("\"roomid\":").append("\""+room.getRoomNo()+"\"").append(",");
    	sbBuilder.append("\"roomname\":").append("\""+room.getRoomName()+"\"").append(",");
    	sbBuilder.append("\"galleryful\":").append("\""+room.getGalleryful()+"\"").append(",");
    	sbBuilder.append("\"roommemo\":").append("\""+room.getRoomMemo()+"\"");
    	sbBuilder.append("}").append(",");}
    }
    if(rooms.size()>0)
    sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
    sbBuilder.append("]");
	response.getWriter().write(sbBuilder.toString());
	response.getWriter().flush();
	response.getWriter().close();
 %>
