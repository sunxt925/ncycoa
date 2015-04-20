<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
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
 
		document.all("Submit").click();
		window.returnValue="refresh";
		
}
</script>
<%
	request.setCharacterEncoding("gb2312");
	String bm=Format.NullToBlank(request.getParameter("bm"));
	SystemEntity e=new SystemEntity(bm);
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; <a href="entitymanage.jsp">实体维护</a> &gt;&gt; 查询结点修改</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">实体编码：</div></td>
        <td width="80%"><input name="entity_code" type="text" class="input1" id="entity_code" onKeyDown="EnterKeyDo('')" value="<%=bm%>" size="30" maxlength="30" readonly></td>
      </tr>
      <tr>
        <td><div align="right">实体名称：</div></td>
        <td><input name="entity_name" type="text" class="input1" id="entity_name" onKeyDown="EnterKeyDo('')" value="<%=e.getEntity_name()%>" size="40" maxlength="40"></td>
      </tr>
      <tr>
        <td><div align="right">实体描述：</div></td>
        <td><input name="entity_comment" type="text" class="input1" id="entity_comment" onKeyDown="EnterKeyDo('')" value="<%=e.getEntity_comment()%>" size="40" maxlength="40">
          <input name="act" type="hidden" id="act" value="modifyentity">
          <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
            <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemEntityAction"></td>
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
