<%@page import="com.entity.system.StaffInfo"%>
<%@page import="com.entity.system.UserInfo"%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.common.*,com.entity.system.Staff" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<base target="_self">
<TITLE>南充市烟草公司</TITLE>
<style type="text/css">
   #main_div{
   
   }
   #main_table{
       padding-left: 200px;
       padding-top: 50px
   }
   #main_table span{
       font-size: 14px;
       font-family: "黑体";
       size: 50px;
       padding-right: 0px;
       margin: 10px;
   }
   #span1{
      float: right;
   }
</style>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<script language="javascript" src="js/public/key.js"></script>
<script language="javascript" src="js/public/check.js"></script>
<%
	request.setCharacterEncoding("gb2312");
	UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
	StaffInfo staff=new StaffInfo(u.getStaffcode());
%>
<BODY>
<div id="main_div">
		<table id="main_table">
			<tr>
				<td><span id="span1">员工编码:</span></td>
				<td><span><%=staff.getCode()%></span></td>
			</tr>
			<tr>
				<td><span id="span1">员工姓名:</td>
				<td><span><%=staff.getName()%></span></td>
			</tr>
			<tr>
				<td><span  id="span1">性别:</td>
				<td><span><%=staff.getGender()%></span></td>
			</tr>
			<tr>
				<td><span id="span1">身份证号:</td>
				<td><span><%=staff.getIdcard()%></span></td>
			</tr>
			<tr>
				<td><span id="span1">生日:</td>
				<td><span><%=staff.getBirthday()%></span></td>
			</tr>
			<tr>
				<td><span id="span1">手机:</span></td>
				<td><span><%=staff.getMobilephone() %></span></td>
			</tr>
		</table>
	</div>

</BODY>
</HTML>
