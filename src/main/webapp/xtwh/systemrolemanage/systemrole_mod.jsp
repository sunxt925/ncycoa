<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript" src="../../js/public/check.js"></script>
<script language="javascript">
function F5()
{
	window.location.reload();
}
function F3()
{
	document.all("reset").click();
}
function F8()
{
	if (sumbit_check())
	{
		//alert ("aaa");
		document.all("Submit").click();
	}
}
</script>
<% ComponentUtil cu=new ComponentUtil(); %>
<%
	request.setCharacterEncoding("gb2312");
	String id=Format.NullToBlank(request.getParameter("bm"));
	SystemRole systemRole = new SystemRole(id);
%>
<BODY class="mainbody" >
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">

<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
<table cellpadding="5"  width="100%" align="left" >		
 <tr>
          <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
  </tr>
			<tr>
				<td><span>角色代码：</span></td>
				<td>
					<%out.print(cu.print("SYSTEM_ROLE","ROLECODE",systemRole.getRolecode())); %>
				</td>
			</tr>
			<tr>
				<td><span>角色名称：</span></td>
				<td>
					<%out.print(cu.print("SYSTEM_ROLE","ROLENAME",systemRole.getRolename())); %>
				</td>
			</tr>
			 
			<tr>
				<td><span>角色模式：</span></td>
				<td>
					<%out.print(cu.print("SYSTEM_ROLE","ROLEMODE",systemRole.getRolemode()));%>
				</td>
			</tr>	
			<tr>
				<td><span>有效标志：</span></td>
				<td>
					<%out.print(cu.print("SYSTEM_ROLE","VALIDFLAG",systemRole.getValidFlag()));%>
				</td>
			</tr>			
</table>
  <tr>
<table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
    </table></td>
	<input name="act" type="hidden" id="act" value="modify">
          <input name="old_ROLECODE" type="hidden" id="old_ROLECODE" value="<%=systemRole.getRolecode()%>">
		  <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
             <input type="hidden" id="entity" name="entity" value="SYSTEM_ROLE"/>
          
        
            <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemRoleAction">
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
<% out.print(cu.getCheckstr());%>