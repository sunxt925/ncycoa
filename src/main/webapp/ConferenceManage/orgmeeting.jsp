<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
	<link type="text/css" href="timepicker-v0.2/css/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="timepicker-v0.2/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="timepicker-v0.2/js/jquery-ui-1.7.2.custom.min.js"></script>
	<script type="text/javascript" src="timepicker-v0.2/js/timepicker.js"></script>
</HEAD>
<base target="_self">
<%
    com.cms.model.sysmng.login.User user=(com.cms.model.sysmng.login.User)request.getSession().getAttribute("USER");
    String orgcode= user.getYybdm();
     MeetingInfo role=new MeetingInfo();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=1;
	DataTable dt;
	DataTable dtcount;
	String date=Format.NullToBlank(request.getParameter("date"));
	System.out.println(date);
	if(date.equals("")){
	 dt=role.getOrgMeetingList(page_no,per_page,orgcode);
	dtcount=role.getAllOrgMeetingList(orgcode);
	}
	else{
	 dt=role.getOrgMeetingList(page_no,per_page,orgcode,date);
	dtcount=role.getAllOrgMeetingList(orgcode,date);
	}
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">
$(function() {
    $('.datetime').datepicker({
        duration: '',
        showTime: false,           //日期控件是否显示时间
        constrainInput: false,
        stepMinutes: 1,
        stepHours: 1,
        altTimeField: '',
        time24h: true  //是否是24h制
     });
});
function F3()
{
	showModalDialogWin("meeting_new.jsp",490,500);
	window.location.reload();
}
function F4()
{
	window.location.href="orgmeeting.jsp?date="+document.getElementById("begintime").value;
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
          
}
function F5()
{
	window.location.reload();
}
function F1(rolecode){
var meetingflag=rolecode.split(",")[0];
var meetingno=rolecode.split(",")[1];
if(meetingflag==10)
{
confirm("会议已取消，无法进行操作!")
}
else if(meetingflag==0){
showModalDialogWin("meeting_handle1.jsp?bm="+meetingno,490,500);
	window.location.reload();
}
else if(meetingflag==11){
showModalDialogWin("meeting_handle2.jsp?bm="+meetingno,490,500);
	window.location.reload();
}


}
function F6(aa){

if (confirm("删除会议，是否继续？"))
		{
			document.getElementById("item").value=aa;
document.all("form1").submit();
		}
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <form name="form1" id="form1" method="post" action="../servlet/PageHandler">
   <tr>
      <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
      <td width="94%" valign="middle" class="main_table_topbg" height="31">首页&gt;&gt;会议管理  &gt;&gt;部门会议列表  </td>
      <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
    </tr>
    <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb"><a href="#" onClick=F4() class="button4">按日期查询：</a>
         <input type="text" name="COM_MEETINGINFO.MEETINGBEGINDATE" class="datetime" id="begintime"  >
          
      </tr>
  </table>
  </td></tr>
	<tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <tr>
          </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>=0) {
		TableUtil tu=new TableUtil();
		tu.setDt(dt);
		tu.setRowCode("会议开会标志","@会议开会标志@"+",Finishflag");
		tu.setRowCode("审核情况","@审核情况@"+",mflag");
		tu.setRowCode("开会地点","@开会地点@"+",mroom");
		tu.setRowCode("需求部门","@需求部门@"+",BASE_ORG,ORGCODE,ORGNAME");
	   out.print(tu.DrawTable());
	%>
    <%}%>
      <input name="act" type="hidden" id="act" value="del1">
       <input name="item" type="hidden" id="item" value="">
       <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingAction">
        <input type="hidden" id="entity" name="entity" value="COM_MEETINGINFO"/>
       <table width="100%" border="0" cellpadding="3" cellspacing="0" >
         <tr>
           <td width="51%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
           <td width="49%" align="right"><%
          String unitccmtemp="date="+date;
      	out.print(PageUtil.DividePage(page_no,pagecount,"orgmeeting.jsp",unitccmtemp));
       %>           </td>
         </tr>
         <tr>
           <td valign="top" height="100%">&nbsp;</td>
         </tr>
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
