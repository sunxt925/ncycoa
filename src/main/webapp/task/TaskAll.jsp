<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.UserInfo,com.entity.task.*,com.dao.system.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";



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
	DataTable dt=Task.getAllTaskList(page_no,per_page);
	int dtcount=Task.getAllTaskListCount();
	int pagecount=0;
	if(dtcount%per_page==0)
	    pagecount=dtcount/per_page;
	else
		pagecount=(dtcount/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript">






function getmember(taskno)
{
    var rand=Math.floor(Math.random()*10000);
	var newmemberurl='ParticipantList.jsp?taskno='+taskno ;
	window.open(newmemberurl,"participantList");
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
<td class="table_td_jb_iframe"> 工作列表&nbsp;&nbsp; 




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
		tableutil.setHeadWidth("计划名称","15%");
		tableutil.setHeadWidth("计划内容","40%");
		tableutil.setHeadWidth("参与人数目","10%");
		tableutil.setHeadWidth("备注","25%");
		tableutil.setDisplayCol("rn,工作序号,审核结果,选择");
		
		tableutil.setLineAction("onclick=\"getmember('@工作序号@')\"");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        
          <td align="right">
          <%
         
      	out.print(PageUtil.DividePage(page_no,pagecount,"TaskAll.jsp",""));
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