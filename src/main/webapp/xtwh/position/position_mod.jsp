<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.common.*,com.entity.system.Position" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
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
	if (CkEmptyStr(document.all("PositionCode"),"岗位ID不能为空！"))
	{
		//alert ("aaa");
		document.all("Submit").click();
	}
}
</script>
<%
	request.setCharacterEncoding("gb2312");
	String id=Format.NullToBlank(request.getParameter("bm"));
	Position position = new Position(id);
%>
<BODY class="mainbody" onLoad="document.all('PositionCode').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; <a href="position_manage.jsp">岗位管理</a>  &gt;&gt; 岗位修改</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">岗位代码：</div></td>
        <td width="80%"><input name="PositionCode" type="text" class="input1" id="PositionCode" onKeyDown="EnterKeyDo('F8()')" value="<%=position.getPositionCode() %>" size="30" maxlength="30"></td>
      </tr>
    	<tr>
        <td width="20%"><div align="right">岗位名称：</div></td>
        <td width="80%"><input name="PositionName" type="text" class="input1" id="PositionName" onKeyDown="EnterKeyDo('')" value="<%=position.getPositionName()%>" size="30" maxlength="30"></td>
      </tr>
      <tr>
        <td><div align="right">岗位说明：</div></td>
        <td width="80%"><input name="PositionDesc" type="text" class="input1" id="PositionDesc" size="30" maxlength="30" value="<%=position.getPositionDesc()%>" onKeyDown="EnterKeyDo('')">
        <input name="act" type="hidden" id="act" value="modify">
        <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemPositionAction"></td>
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
