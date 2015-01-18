<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<link type="text/css" href="timepicker-v0.2/css/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="timepicker-v0.2/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="timepicker-v0.2/js/jquery-ui-1.7.2.custom.min.js"></script>
	<script type="text/javascript" src="timepicker-v0.2/js/timepicker.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
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
	
   /* var begintime = document.getElementById("begintime").value;
	 
	 var data1=begintime.substring(0,9);
	var time1=begintime.substring(11.15);
	var hour1=time1.split(":")[0];
	var min1=time1.split(":")[1];
	 var endtime =document.getElementById("endtime").value; 
	 var data2=endtime.substring(0,9);
	 var time2=endtime.substring(11.15);
	 var hour2=time2.split(":")[0];
	 var min2=time2.split(":")[1];
	 if(parseInt(hour2,10)-parseInt(hour1,10)<=0){
	       alert("时间格式错误！");}
	else*/ if(sumbit_check())
	{
	
		document.all("Submit").click();
	}
}
</script>
<script type="text/javascript">
$(function() {
    $('.datetime').datepicker({
        duration: '',
        showTime: true,           //日期控件是否显示时间
        constrainInput: false,
        stepMinutes: 1,
        stepHours: 1,
        altTimeField: '',
        time24h: true  //是否是24h制
     });
});
</script>

<style type="text/css">
	body{ font: 80% "Trebuchet MS", sans-serif; margin: 50px;}
</style>


<% ComponentUtil cu=new ComponentUtil(); %>
<%
	request.setCharacterEncoding("gb2312");
	String no=Format.NullToBlank(request.getParameter("bm"));
	MeetingInfo mo=new MeetingInfo(no);
	
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">  </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
      <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
         <tr>
        <td width="20%"><div align="right">会议名称：</div></td>
        <td width="80%">
         <%out.print(cu.print("COM_MEETINGINFO","MEETINGNAME",mo.getMeetingName())); %><!--
        
      --></tr>
      <tr>
        <td><div align="right">开始时段：</div></td>
        <td>
        <input type="text" name="COM_MEETINGINFO.MEETINGBEGINDATE" class="datetime" value="<%=mo.getMeetingBeginDate() %>"  id="begintime"  >
         <%//out.print(cu.print("COM_MEETINGINFO","MEETINGBEGINDATE",mo.getMeetingBeginDate())); 
        // System.out.println(cu.print("COM_MEETINGINFO","MEETINGBEGINDATE",mo.getMeetingBeginDate()));
         
         %>         
         <!--
        <input name="menu_name" type="text" class="input1" id="menu_name" onKeyDown="EnterKeyDo('')" size="60" maxlength="60"></td>
      --></tr>
      <tr>
        <td><div align="right">结束时段：</div></td>
        <td>
         <input type="text" name="COM_MEETINGINFO.MEETINGENDDATE" class="datetime" value="<%=mo.getMeetingEndDate() %> "   id="endtime"  >
        <%//out.print(cu.print("COM_MEETINGINFO","MEETINGENDDATE",mo.getMeetingEndDate())); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
       <tr>
        <td><div align="right">开会地点：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGINFO","MEETINGROOM",mo.getMeetingRoom())); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
       <tr>
        <td><div align="right">会议人数：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGINFO","NUMATTENDEE",mo.getNumAttendee())); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
       <!--<tr>
        <td><div align="right">需求部门：</div></td>
        <td>
        <%//out.print(cu.print("COM_MEETINGINFO","APPLYORGCODE",mo.getApplyOrgCode())); %></tr>
      <tr>
       --><tr>
        <td><div align="right">其他要求：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGINFO","REQUESTDESC",mo.getRequestDesc())); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
     
      <tr>
		
		</table></td>
	<input name="act" type="hidden" id="act" value="modify">
		  <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
             <input type="hidden" id="entity" name="entity" value="COM_MEETINGINFO">
           <input name="COM_MEETINGINFO.MEETINGNO" id="COM_MEETINGINFO.MEETINGNO" type="hidden" value="<%=no%>"/>
            <input name="old_MEETINGNO" id="old_MEETINGNO" type="hidden" value="<%=no%>"/>
           <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingAction">
 </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
<% out.print(cu.getCheckstr());%>