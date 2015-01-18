<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
String rolecode=Format.NullToBlank(request.getParameter("bm"));
//System.out.println(rolecode+"dvxvxvxcvx");
if (unitccm.equals("")) unitccm="1151";
%>
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>

<% 
   Org og=new Org();
   DataTable dt=og.getTopList();
%>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("unit_new.jsp?sid="+rand,"_self");
	//window.open ("unit_new.jsp","Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no, copyhistory=no,width=350,height=140,left=200,top=300");        
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

function changetopunit(unitccm)
{

	var newlisturl="unit_positionlist.jsp?unitccm="+unitccm+"&rolecode="+"<%=rolecode%>";
	var newtreeurl="../../tree/RolePosition_tree.jsp?pageurl=../xtwh/systemrolemanage/unit_positionlist.jsp?rolecode="+"<%=rolecode%>"+"&pagetarget=unitpositionlist1&unitccm="+unitccm;
	window.open(newlisturl,'unitpositionlist1');
	window.open(newtreeurl,'RolePosition_tree');
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 模块设置 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="250" valign="top">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">        
            <%
				if (dt!= null && dt.getRowsCount()>0)
				{
					for (int i=0;i<dt.getRowsCount();i++)
					{
						out.print("<tr><td class='table_td_jb_iframe' align='center'><a href=\"#\" onclick=\"changetopunit('"+dt.get(i).getString("orgcode")+"')\">【"+dt.get(i).getString("orgname")+"】</a></td></tr>");	
					}
				}
			%>
             </fieldset>
            </td>
          </tr>
          <tr>
            <td valign="top" height="100%"><iframe src="../../tree/RolePosition_tree.jsp?pageurl=../../xtwh/systemrolemanage/unit_positionlist.jsp?rolecode=<%=rolecode %>&pagetarget=unitpositionlist1" name="RolePosition_tree" id="RolePosition_tree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
        </table>
		
		</td>
		<td  valign="top">
			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" height="50%">
            <iframe src="" name="unitpositionlist1" id="unitpositionlist1" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
            </td>
          </tr>

        </table>
        </td>
		
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