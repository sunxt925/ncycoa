<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<% 
   Org og=new Org();
  DataTable dt=og.getTopList(); 
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">

function F5()
{
	window.location.reload();
}

function changetopunit(unitccm)
{


	var newlisturl='std_orgpostlist.jsp?unitccm='+unitccm;
	var newtreeurl='../tree/unit_tree.jsp?pageurl=../std_search/std_orgposttab.jsp&pagetarget=postlist&unitccm='+unitccm;
	window.open(newlisturl,'postlist');
	window.open(newtreeurl,'unittree')
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 模块设置 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <!--  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr> -->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15%" valign="top">
          <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" valign="top" bgcolor="#DBEAFD">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <%
				if (dt!= null && dt.getRowsCount()>0)
				{
					for (int i=0;i<dt.getRowsCount();i++)
					{
						out.print("<tr><td class='table_td_jb_iframe'style='padding-left:50px; padding-top:5px;'><a href=\"#\" onclick=\"changetopunit('"+dt.get(i).getString("orgcode")+"')\">○ "+dt.get(i).getString("orgname")+"</a></td></tr>");	
					}
				}
			%>
            </table>
            </td>
          </tr>
          <tr>
            <td valign="top" height="100%"><iframe src="../tree/unit_tree.jsp?pageurl=../std_search/std_orgposttab.jsp&pagetarget=postlist" name="unittree" id="unittree" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
          </tr>
        </table></td>


            <td valign="top" height="100%"><iframe src="" name="postlist" id="postlist" width="100%" height="100%" scrolling="no" frameborder="2"></iframe></td>
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