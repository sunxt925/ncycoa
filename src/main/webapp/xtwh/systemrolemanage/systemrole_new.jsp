<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�����еط�˰���</TITLE>
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
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; ϵͳά�� &gt;&gt; <a href="systemrole_manage.jsp">��ɫ����</a>  &gt;&gt; ��ӽ�ɫ</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
      <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">����[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">��ɫ���룺</div></td>
        <td width="80%">
      <%out.print(cu.print("SYSTEM_ROLE","ROLECODE")); %>
        <!--<input name="Rolecode" type="text" class="input1" id="Rolecode" onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
      --></td>
    <tr>
        <td width="20%"><div align="right">��ɫ���ƣ�</div></td>
        <td width="80%">
        <%out.print(cu.print("SYSTEM_ROLE","ROLENAME")); %><!--<input name="Rolename" type="text" class="input1" id="Rolename" onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
      --></td>
      <tr>
        <td width="20%"><div align="right">��ɫģʽ��</div></td>
        <td width="80%">
         <%out.print(cu.print("SYSTEM_ROLE","ROLEMODE"));%><!--<input name="Rolemode" type="text" class="input1" id="Rolemode" onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
      --></td>
      <tr>
        <td><div align="right">��Ч��־��</div></td>
        <td width="80%">
         <%out.print(cu.print("SYSTEM_ROLE","VALIDFLAG"));%><!--
        <input name="ValidFlag" type="text" class="input1" id="ValidFlag" size="30" maxlength="30" onKeyDown="EnterKeyDo('F8()')">
        --><input name="act" type="hidden" id="act" value="add">
        <input type="hidden" id="entity" name="entity" value="SYSTEM_ROLE"/>
        <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input type="reset" name="reset" value="����" style="display:none">
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