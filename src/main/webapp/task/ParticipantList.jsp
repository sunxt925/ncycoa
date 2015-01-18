<%@ page language="java" import="java.util.*,com.entity.system.UserInfo,com.common.*,com.db.*,com.entity.task.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String taskno = request.getParameter("taskno");
String date = Format.NullToBlank(request.getParameter("date"));
String auditflag = request.getParameter("auditflag");

UserInfo uinfo=(UserInfo)request.getSession().getAttribute("UserInfo");
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
	Task task=new Task(taskno);
	DataTable dt=Participant.getParticipantList(page_no,per_page,taskno);
	int dtcount=Participant.getAllParticipantList(taskno);
	int pagecount=0;
	if(dtcount%per_page==0)
	    pagecount=dtcount/per_page;
	else
		pagecount=(dtcount/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript">







function deleteparticipant(taskno,participantcode)
{
      if(confirm("是否确定删除任务号为："+taskno+"员工号为"+participantcode+"的任务？"))
    {
    var newmemberurl='../servlet/PageHandler?act=del&entity=MONTHTASKPARTICIPANT&action_class=com.action.task.ParticipantAction&date=<%=date%>&TASKNO='+taskno+'&participantcode='+participantcode;
    window.open(newmemberurl,"_self");
    }
  
}

function gettask(taskno,participantcode)
{
  window.showModalDialog("task_get.jsp?taskno="+taskno+"&participantcode="+participantcode,window,"scroll=no;status=no;dialogWidth=700px;dialogHeight=400px;center=yes;help=no;");
  
}

function reloadPage()
{
  window.open('../task/TaskList.jsp','tasklist');
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
	window.showModalDialog("ParticipantChooseManage.jsp?date=<%=date%>&taskno=<%=taskno%>",window,"scroll=no;status=no;dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;");	
    window.open('../task/TaskListDate.jsp?date=<%=date%>','tasklist');
}
</script>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe"><%=task.getTaskname() %>>>人员分配&nbsp;&nbsp; 

<a href="#" onClick="F3()">增加[F3]</a>





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
		tableutil.setHeadWidth("选择","10%");
		tableutil.setHeadWidth("参与人","15%");
		tableutil.setHeadWidth("完成任务","30%");
		tableutil.setHeadWidth("完成时间","10%");
		tableutil.setHeadWidth("备注","20%");
		tableutil.setHeadWidth("操作","15%");
		tableutil.setRowCode("参与人","@参与人@"+",BASE_STAFF,STAFFCODE,STAFFNAME");
		
		
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        
          <td align="right">
          <%
         
      	out.print(PageUtil.DividePage(page_no,pagecount,"TaskList.jsp",""));
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