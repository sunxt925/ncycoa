<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<%@page import="com.dao.system.LoadCode"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String bm=Format.NullToBlank(request.getParameter("bm"));
	SystemEntity se=new SystemEntity();
	DataTable dt=se.getEntityColumn(bm);
	//LoadCodeAction en=new LoadCodeAction();  
    CodeDictionary cd=new CodeDictionary();
%>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">
function F8()
{
	document.all("Submit").click();
}
function F5()
{
	window.location.reload();
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 系统配置 &gt;&gt; <a href="entitymanage.jsp">实体管理</a>：<%=bm%></td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
      <tr>
	    <td width="13%" align="center">字段名</td>
        <td width="13%" align="center">字段中文名</td>
		<td width="20%" align="center">字段说明</td>
        <td width="13%" align="center">引用编码表</td>
		<td width="13%" align="center">数据编辑方式</td>
        <td width="13%" align="center">数据校验方式</td>
        <td width="15%" align="center">数据校验参数</td>
        </tr>
	  <%
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
	  %>
      <tr onMouseOver="this.style.backgroundColor='#D0E9ED'" onMouseOut="this.style.backgroundColor=''">
	    <td align="left"><%=r.getString("column_code")%></td>
        <td align="left"><input name="column_name_<%=r.getString("column_code")%>" type="text" id="column_name_<%=r.getString("column_code")%>" value="<%=r.getString("column_name")%>" style="width:100%"></td>
		
		<td align="left"><input name="column_comment_<%=r.getString("column_code")%>" type="text" id="column_comment_<%=r.getString("column_code")%>" value="<%=r.getString("column_comment")%>" style="width:100%"></td>
       
		<td align="left">
		<%=cd.getselectItem("code_entity_"+r.getString("column_code"),r.getString("code_entity")) %>
	
		</td>
		
        <td align="left">
       
	    <%=cd.getselectItem("data_edittype","edit_code_"+r.getString("column_code"),r.getString("edit_code"))%>
        </td>
        <td align="left">
       
	    <%=cd.getselectItem("data_checktype","check_code_"+r.getString("column_code"),r.getString("check_code")) %>
        </td>
        
        <td align="left"><input name="check_parameter_<%=r.getString("column_code")%>" type="text" id="check_parameter_<%=r.getString("column_code")%>" value="<%=r.getString("check_parameter")%>" style="width:100%"></td>
        </tr>
	  <%
	  	}}
	  %>
    </table>
      <input name="act" type="hidden" id="act" value="modifycolumn"><input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
      <input name="entity_code" type="hidden" id="entity_code" value="<%=bm%>">
      <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemEntityAction"></td>
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
