<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
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
<%
 
 UserInfo user = (UserInfo)request.getSession().getAttribute("UserInfo");
 Staff staff = new Staff(user.getStaffcode());
 String orgcode = staff.getOrgcode();

 String taskno = request.getParameter("taskno");
 String date = Format.NullToBlank(request.getParameter("date"));
 session.setAttribute("taskno",taskno);
 session.setAttribute("date",date);
		
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("unit_new.jsp?sid="+rand,"unitlist");
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

function changetopunit(orgcode)
{
	var newlisturl='ParticipantChoose.jsp?unitccm=<%=orgcode%>';
	var newtreeurl='../tree/unit_tree.jsp?unitccm='+orgcode+'&pageurl=../task/ParticipantChoose.jsp&pagetarget=orgmember';
	window.open(newlisturl,'orgmember');
	window.open(newtreeurl,'unittree');
	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 人员选择 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="250" valign="top"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">

            </td>
          </tr>
          <tr>
           <td valign="top" height="100%"><iframe src="../tree/unit_tree.jsp?unitccm=<%=orgcode %>&pageurl=../task/ParticipantChoose.jsp&pagetarget=orgmember" name="unittree" id="unittree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
        </table></td>
       <td valign="top"><iframe src="ParticipantChoose.jsp" name="orgmember" id="orgmember" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src=".../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>