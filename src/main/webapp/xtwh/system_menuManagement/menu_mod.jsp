<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.common.*,com.entity.system.*" errorPage="" %>
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
	if(sumbit_check())
	{
		//alert ("aaa");
		document.all("Submit").click();
	}
}
</script>
<% ComponentUtil cu=new ComponentUtil(); %>
<%
	request.setCharacterEncoding("gb2312");
	String level_code=Format.NullToBlank(request.getParameter("bm"));
	String s=level_code.substring(0,level_code.length()-3);
	//System.out.println(level_code);
	SystemModule mo=new SystemModule(level_code);
	
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler"><!--
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; <a href="modulemanage.jsp">模块管理</a> &gt;&gt; 模块信息修改</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  --><tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">模块层次码：</div></td>
        <td width="80%">
        <%out.print(cu.print("SYSTEM_MENU","LEVEL_CODE",mo.getLevel_code())); %>
        <!--<input name="newlevel_code" type="text" class="input1" id="newlevel_code" onKeyDown="EnterKeyDo('')" value="<%=mo.getLevel_code()%>" size="40" maxlength="40" readonly="readonly"></td>-->
      </tr>
      <tr>
        <td><div align="right">模块名称：</div></td>
        <td>
        <%out.print(cu.print("SYSTEM_MENU","MENU_NAME",mo.getMenu_name())); %>
        <!--<input name="menu_name" type="text" class="input1" id="menu_name" onKeyDown="EnterKeyDo('')" value="<%=mo.getMenu_name()%>" size="40" maxlength="40"></td>-->
      </tr>
      <tr>
        <td><div align="right">路径：</div></td>
        <td>
        <%out.print(cu.print("SYSTEM_MENU","MENU_URL",mo.getMenu_url())); %>
        <!--<input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')" value="<%=mo.getMenu_url()%>" size="40" maxlength="40"></td>-->
      </tr>
      <tr>
        <td><div align="right">模块类型：</div></td>
        <td>
        <%out.print(cu.print("SYSTEM_MENU","MENU_TYPE",mo.getMenu_type())); %>
        </td>
		
		</table></td>
	<input name="act" type="hidden" id="act" value="modify">
         <input name="old_LEVEL_CODE" type="hidden" id="old_LEVEL_CODE" value="<%=mo.getLevel_code()%>">
		  <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
             <input type="hidden" id="entity" name="entity" value="SYSTEM_MENU">
           
           <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemModuleAction">
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