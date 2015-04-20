<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,java.util.Date.*,java.text.SimpleDateFormat.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<link type="text/css" href="timepicker-v0.2.1/css/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="timepicker-v0.2.1/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="timepicker-v0.2.1/js/jquery-ui-1.7.2.custom.min.js"></script>
	<script type="text/javascript" src="timepicker-v0.2.1/js/timepicker.js"></script>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
</HEAD>
<base target="_self">
<%
	request.setCharacterEncoding("gb2312");
	String no=Format.NullToBlank(request.getParameter("bm"));
	MeetingInfo mo=new MeetingInfo(no);
	
 %>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript" src="../js/public/check.js"></script>
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
<BODY class="mainbody" onLoad="document.all('bm').focus();">

<BODY class="mainbody" >

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31"></td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">当前会议状态：</div></td>
        <td width="80%">
         未开会
    <tr>
        <td width="20%"><div align="right">请选择会议状态：</div></td>
        <td width="80%">
        
         取消会议<input type="radio"  name="COM_MEETINGINFO.MEETINGFLAG" id="COM_MEETINGINFO.AUDITFLAG" value="10" />
          已完成<input type="radio"  name="COM_MEETINGINFO.MEETINGFLAG" id="COM_MEETINGINFO.AUDITFLAG" value="11" />

        </td>
      </tr>
      <tr>
      <td>
      
      <input name="act" type="hidden" id="act" value="handle">
        <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
           <input type="hidden" id="entity" name="entity" value="COM_MEETINGINFO">
            <input name="COM_MEETINGINFO.MEETINGNO" id="COM_MEETINGINFO.MEETINGNO" type="hidden" value="<%=no%>"/>
            <input name="old_MEETINGNO" id="old_MEETINGNO" type="hidden" value="<%=no%>"/>
           <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingAction"></td>
      </tr>
    </table></td>
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