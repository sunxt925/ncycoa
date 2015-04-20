<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%
SystemModule og=new SystemModule();
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
 DataTable dt=og.getMenuList(unitccm);
 String newcoding="";
 if(dt!=null && dt.getRowsCount()>0){
 String code =dt.get(dt.getRowsCount()-1).getString("level_code");
 
 String temp=code.substring(code.length()-3,code.length());
 
 int newcode=Integer.parseInt(temp)+1; 
  String s = String.valueOf(newcode);
  String res="";
  for(int i=s.length();i<3;i++)
  {
  	res+="0";
  }
 res+=s;
 newcoding=code.substring(0,code.length()-3)+res;

 }
 else{
 newcoding=unitccm+"001";
 }
 %>
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
	/*if (CkEmptyStr(document.all("level_code"),"层次码不能为空！") && CkEmptyStr(document.all("menu_name"),"模块名称不能为空！"))*/
	if(sumbit_check())
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
  <!--<tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; <a href="modulemanage.jsp">模块管理</a>  &gt;&gt; 添加模块</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>-->
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">模块层次码：</div></td>
        <td width="80%">
         <%out.print(cu.print("SYSTEM_MENU","LEVEL_CODE",newcoding)); %><!--
        <input name="level_code" type="text" class="input1" id="level_code" onKeyDown="EnterKeyDo('')" value="<%=newcoding %>" size="60" maxlength="60" readonly="readonly"></td>
      --></tr>
      <tr>
        <td><div align="right">模块名称：</div></td>
        <td>
         <%out.print(cu.print("SYSTEM_MENU","MENU_NAME")); %><!--
        <input name="menu_name" type="text" class="input1" id="menu_name" onKeyDown="EnterKeyDo('')" size="60" maxlength="60"></td>
      --></tr>
      <tr>
        <td><div align="right">路径：</div></td>
        <td>
        <%out.print(cu.print("SYSTEM_MENU","MENU_URL")); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
        <td><div align="right">模块类型：</div></td>
        <td>
        
          <%out.print(cu.print("SYSTEM_MENU","MENU_TYPE")); %>
          </td>
          <!--
        
       </td>-->
      </tr>
      <tr>
      <td>
      <input name="act" type="hidden" id="act" value="add">
        <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
           <input type="hidden" id="entity" name="entity" value="SYSTEM_MENU">
           
           <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemModuleAction"></td>
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