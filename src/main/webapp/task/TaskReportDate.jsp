<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.UserInfo,com.entity.task.*,com.dao.system.*" errorPage="" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";



UserInfo uinfo=(UserInfo)request.getSession().getAttribute("UserInfo");
Role.sysmng.login.User u=(Role.sysmng.login.User)request.getSession().getAttribute("USER");

String date = request.getParameter("date");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<base target="_self">
</HEAD>
<%
	
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=uinfo.getPerpage_third();
	//Log.Debug(per_page);
	DataTable dt=Participant.getMyTaskList(page_no,per_page,u.getZgdm());
	int dtcount=Task.getTaskListCount(u.getZgdm());
	int pagecount=0;
	if(dtcount%per_page==0)
	    pagecount=dtcount/per_page;
	else
		pagecount=(dtcount/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript">









function taskreport(taskno)
{
  window.showModalDialog("task_report.jsp?taskno="+taskno,window,"scroll=no;status=no;dialogWidth=700px;dialogHeight=400px;center=yes;help=no;");
}

function postreport(taskno,participantcode)
{
   if(confirm("是否确认提交任务号为："+taskno+"的完成情况？"))
    {
    var newmemberurl='../servlet/PageHandler?act=report&entity=MONTHTASKPARTICIPANT&action_class=com.action.task.ParticipantAction&old_TASKNO='+taskno+'&old_PARTICIPANTCODE='+participantcode+"&MONTHTASKPARTICIPANT.FINISHEDFLAG=11"+"&MONTHTASKPARTICIPANT.TASKNO="+taskno+"&MONTHTASKPARTICIPANT.PARTICIPANTCODE="+participantcode;
    window.open(newmemberurl,"_self");
    }
}



function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("父级菜单的删除将级联删除子菜单，是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function F5()
{
	window.location.reload();
}
function F3()
{
	window.showModalDialog("task_new.jsp",window,"scroll=no;status=no;dialogWidth=700px;dialogHeight=400px;center=yes;help=no;");	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe">&nbsp;&nbsp; 





</td>
</tr>
</table>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">

    </table>
   <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>=0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("任务名称","10%");
		tableutil.setHeadWidth("完成任务","20%");
		tableutil.setHeadWidth("完成时间","10%");
		tableutil.setHeadWidth("完成情况","30%");
		tableutil.setHeadWidth("备注","20%");
		tableutil.setHeadWidth("操作","10%");
		tableutil.setRowCode("任务名称","@任务名称@"+",MONTHTASK,TASKNO,TASKNAME");
		
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        <td width="50%" align="left">
        工作列表
        </td>
          <td align="right">
          <%
         
      	out.print(PageUtil.DividePage(page_no,pagecount,"TaskReport.jsp",""));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
 
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>