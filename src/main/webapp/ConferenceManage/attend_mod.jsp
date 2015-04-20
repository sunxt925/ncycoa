<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">

</HEAD>
<base target="_self">
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript" src="../js/public/check.js"></script>
<script language="javascript">
function F5()
{
	window.location.reload();
}
function F3()
{
	document.all("reset").click();
}
function F8()
{
	if(sumbit_check())
	{
		
		document.all("Submit").click();
	}
}
</script>
<% ComponentUtil cu=new ComponentUtil(); %>
<%
	request.setCharacterEncoding("gb2312");
	String no=Format.NullToBlank(request.getParameter("bm"));
	//System.out.println(no);
	meetingattendee mo=new meetingattendee(no);
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31"> &gt;&gt;  &gt;&gt; </a> &gt;&gt;</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
         <tr>
        <td width="20%"><div align="right">参会人员：</div></td>
        <td width="80%">
         <%out.print(cu.print("COM_MEETINGATTENDEE","ATTENDEECODE",mo.getAttendeeCode())); %><!--
        
      --></tr>
      <tr>
        <td><div align="right">所在部门：</div></td>
        <td>
         <%//out.print(cu.print("COM_MEETINGINFO","MEETINGBEGINDATE",mo.getMeetingBeginDate())); 
         out.print(cu.print("COM_MEETINGATTENDEE","ATTENDEEORGCODE",mo.getAttendeeOrgcode()));
         
         %>         
         <!--
        <input name="menu_name" type="text" class="input1" id="menu_name" onKeyDown="EnterKeyDo('')" size="60" maxlength="60"></td>
      --></tr>
      <tr>
        <td><div align="right">邮件：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGATTENDEE","EMAIL",mo.getEmail())); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
       <tr>
        <td><div align="right">手机：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGATTENDEE","MOBILEPHONE",mo.getMobilePhone())); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
       <tr>
        <td><div align="right">备注：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGATTENDEE","MEMO",mo.getMemo())); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
     
     
      <tr>
		<input name="act" type="hidden" id="act" value="modify">
		  <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
             <input type="hidden" id="entity" name="entity" value="COM_MEETINGATTENDEE">
              <input name="COM_MEETINGATTENDEE.MEETINGNO" id="COM_MEETINGATTENDEE.MEETINGNO" type="hidden" value="<%=mo.getMeetingNo()%>"/>
            <input name="old_MEETINGNO" id="old_MEETINGNO" type="hidden" value="<%=mo.getMeetingNo()%>"/>
          <input name="COM_MEETINGATTENDEE.ATTENDEECODE" id="COM_MEETINGATTENDEE.ATTENDEECODE" type="hidden" value="<%=mo.getAttendeeCode()%>"/>
           <input name="old_ATTENDEECODE" type="hidden" id="old_ATTENDEECODE" value="<%=mo.getAttendeeCode()%>">
           <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingattendAction">
		</table></td>
	
 </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
<% out.print(cu.getCheckstr());%>