<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�����еط�˰���</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript" src="../js/public/check.js"></script>
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
	if (CkEmptyStr(document.all("bm"),"����벻��Ϊ�գ�") && CkEmptyStr(document.all("bmnr"),"ģ�����Ʋ���Ϊ�գ�"))
	{
		//alert ("aaa");
		document.all("Submit").click();
	}
}
</script>
<BODY class="mainbody" onLoad="document.all('bm').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="module_do.jsp">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; ϵͳά�� &gt;&gt; <a href="modulemanage.jsp">ģ�����</a>  &gt;&gt; ���ģ��</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">ģ�����룺</div></td>
        <td width="80%"><input name="bm" type="text" class="input1" id="bm" onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
      </tr>
      <tr>
        <td><div align="right">ģ�����ƣ�</div></td>
        <td><input name="bmnr" type="text" class="input1" id="bmnr" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"></td>
      </tr>
      <tr>
        <td><div align="right">·����</div></td>
        <td><input name="url" type="text" class="input1" id="url" onKeyDown="EnterKeyDo('')" size="30" maxlength="200"></td>
      </tr>
      <tr>
        <td><div align="right">ģ�����ͣ�</div></td>
        <td><%=Coder.getSelectFromString("1,�о�ר��;2,���ؾ�ר��;3,����","mklx","")%></td>
      </tr>
      <tr>
        <td><div align="right">��ע��</div></td>
        <td width="80%"><input name="bz" type="text" class="input1" id="bz" size="30" maxlength="30" onKeyDown="EnterKeyDo('F8()')">
        <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input type="reset" name="reset" value="����" style="display:none">
          <input name="act" type="hidden" id="act" value="add"></td>
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
