<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
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
<BODY class="mainbody" onLoad="this.focus();">

<BODY class="mainbody" >

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; <a href="systemrole_manage.jsp">角色管理</a>  &gt;&gt; 添加角色</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
      <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">角色代码：</div></td>
        <td width="80%">
      <%out.print(cu.print("SYSTEM_ROLE","ROLECODE")); %>
        <!--<input name="Rolecode" type="text" class="input1" id="Rolecode" onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
      --></td>
    <tr>
        <td width="20%"><div align="right">角色名称：</div></td>
        <td width="80%">
        <%out.print(cu.print("SYSTEM_ROLE","ROLENAME")); %><!--<input name="Rolename" type="text" class="input1" id="Rolename" onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
      --></td>
      <tr>
        <td width="20%"><div align="right">角色模式：</div></td>
        <td width="80%">
         <%out.print(cu.print("SYSTEM_ROLE","ROLEMODE"));%><!--<input name="Rolemode" type="text" class="input1" id="Rolemode" onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
      --></td>
      <tr>
        <td><div align="right">有效标志：</div></td>
        <td width="80%">
         <%out.print(cu.print("SYSTEM_ROLE","VALIDFLAG"));%><!--
        <input name="ValidFlag" type="text" class="input1" id="ValidFlag" size="30" maxlength="30" onKeyDown="EnterKeyDo('F8()')">
        --><input name="act" type="hidden" id="act" value="add">
        <input type="hidden" id="entity" name="entity" value="SYSTEM_ROLE"/>
        <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
         <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemRoleAction"></td>
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
<% out.print(cu.getCheckstr());%>