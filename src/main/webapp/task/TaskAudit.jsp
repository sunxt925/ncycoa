<%@ page language="java" import="java.util.*,com.entity.system.UserInfo,com.common.*,com.db.*,com.entity.task.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
com.cms.model.sysmng.login.User u=(com.cms.model.sysmng.login.User)request.getSession().getAttribute("USER");
UserInfo uinfo=(UserInfo)request.getSession().getAttribute("UserInfo");

String taskno = request.getParameter("taskno");
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
	DataTable dt=Participant.getAuditTaskList(page_no,per_page,u.getYybdm(),taskno);
	int dtcount=Participant.getAllAuditList(u.getYybdm(),taskno);
	int pagecount=0;
	if(dtcount%per_page==0)
	    pagecount=dtcount/per_page;
	else
		pagecount=(dtcount/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript">




function audittask(taskno,participantcode)
{
   window.showModalDialog("participant_audit.jsp?taskno="+taskno+"&participantcode="+participantcode,window,"scroll=no;status=no;dialogWidth=500px;dialogHeight=400px;center=yes;help=no;");	
   
}

function uploadfile(storefileno)
{
   window.open("../file_upload/officeopen.jsp?storefileno="+storefileno);
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
	//window.showModalDialog("ParticipantChooseManage.jsp?taskno=",window,"scroll=no;status=no;dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;");	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe">审核列表&nbsp;&nbsp; 






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
		tableutil.setHeadWidth("员工名称","10%");
		tableutil.setHeadWidth("完成任务","25%");
		tableutil.setHeadWidth("完成时间","10%");
		tableutil.setHeadWidth("完成情况","40%");
		
		tableutil.setHeadWidth("审核情况","5%");	
		tableutil.setHeadWidth("操作","10%");
		tableutil.setRowCode("任务名称","@任务名称@"+",MONTHTASK,TASKNO,TASKNAME");
		tableutil.setRowCode("员工名称","@员工名称@"+",BASE_STAFF,STAFFCODE,STAFFNAME");
		tableutil.setDisplayCol("rn,任务名称,备注");
		
		
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        
          <td align="right">
          <%
         
      	out.print(PageUtil.DividePage(page_no,pagecount,"TaskAudit.jsp",""));
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