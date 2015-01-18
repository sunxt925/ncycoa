<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
    //String  orgcode=request.getParameter("bm");
     MeetingInfo role=new MeetingInfo();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=20;
	//System.out.println(orgcode+"sdfsdfds");
	DataTable dt=role.getAuditingList(page_no,per_page);
	DataTable dtcount=role.getAllMeetingList();
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	showModalDialogWin("meeting_new.jsp",490,500);
	window.location.reload();
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("删除角色，是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
          
}
function F5()
{
	window.location.reload();
}
function F1(rolecode){
showModalDialogWin("meeting_audit.jsp?bm="+rolecode,490,500);
	window.location.reload();

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
      <td width="94%" valign="middle" class="main_table_topbg" height="31">首页&gt;&gt;会议管理  &gt;&gt;审查会议申请  </td>
      <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
    </tr>
    <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">
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
		tu.setRowCode("审核情况","@审核情况@"+",mflag");
	   tu.setRowCode("需求部门","@需求部门@"+",BASE_ORG,ORGCODE,ORGNAME");
	   tu.setRowCode("开会地点","@开会地点@"+",mroom");
		
	   out.print(tu.DrawTable());
	%>
    <%}%>
      <input name="act" type="hidden" id="act" value="del">
       <input name="item" type="hidden" id="item" value="">
       <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingAction">
        <input type="hidden" id="entity" name="entity" value="COM_MEETINGINFO"/>
       <table width="100%" border="0" cellpadding="3" cellspacing="0" >
         <tr>
           <td width="51%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
           <td width="49%" align="right"><%
          String unitccmtemp="";
      	out.print(PageUtil.DividePage(page_no,pagecount,"Auditing.jsp",unitccmtemp));
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
