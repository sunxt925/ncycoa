<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>

<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">

function F5()
{
	window.location.reload();
}



</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 情况审核 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>

		<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		 <tr valign="top"  height="10%">
            <td valign="top"><iframe src="DateQuery_Audit.jsp" name="datequery" id="datequery" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		  <tr valign="datequery" height="50%">
            <td valign="datequery"><iframe src="TaskOrgAudit.jsp" name="tasklist" id="tasklist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		 <tr valign="bottom" height="40%">
		    <td valign="bottom"><iframe src="empty.jsp" name="participantList" id="participantList" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
	
		</table>
		</td>
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