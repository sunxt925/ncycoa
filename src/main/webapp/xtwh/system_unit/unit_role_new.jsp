<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
 String  orgcode=request.getParameter("bm");
 //String  opp=request.getParameter("opp");

 Org org=new Org(orgcode);

 SystemRole role=new SystemRole();
  DataTable dt= role.getAllRoleList();
 %>

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
	
		document.all("Submit").click();
}

</script>
<BODY class="mainbody" onLoad="document.all('role_id').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
    <tr>
      <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
      <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; <a href="systemrole_manage.jsp">角色管理</a> &gt;&gt; 添加角色</td>
      <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
    </tr>
    <tr>
      <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
    </tr>
    <tr>
      <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
        <tr>
          <td><div align="right">机构名称：</div></td>
          <td><input name="unitname" type="text" class="input1" id="unitname" onKeyDown="EnterKeyDo('')" value="<%=org.getName()%>" size="40" maxlength="40" readonly="readonly"></td>
        </tr>
        <tr>
          <td><div align="right">机构编码：</div></td>
          <td><input name="orgcode" type="text" class="input1" id="orgcode" onKeyDown="EnterKeyDo('')" value="<%=org.getCode()%>" size="40" maxlength="40"  readonly="readonly">
          </td>
        </tr>
        <tr>
          <td><div align="right">角色：</div></td>
        <td>
         <select id="pageselect" name="pageselect"  >
                    <%
       if (dt!= null && dt.getRowsCount()>0)
	   {
	     for (int i=0;i<dt.getRowsCount();i++){
          out.println("<option value='"+dt.get(i).getString("rolecode")+"'>"+dt.get(i).getString("ROLENAME")+"</option>");
       }
     }
    %>
         </select>
            </tr>
            <tr>
            </table>
          <input name="act" type="hidden" id="act" value="add">
          <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitRoleAction">
        </table></td>
    </tr>
  </form>
</table>
</BODY>
</HTML>
