<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%
    String table_name=Format.NullToBlank(request.getParameter("table_name"));
    //String level_id=Format.NullToBlank(request.getParameter("level_id"));
    String code_id=Format.NullToBlank(request.getParameter("code_id"));
    String pcode_id=Format.NullToBlank(request.getParameter("pcode_id"));
   if(code_id.equals("")){
     code_id=pcode_id;
   }
    String pcode_name=Format.NullToBlank(request.getParameter("pcode_name"));
    ComponentUtil cu=new ComponentUtil();
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
	//CkEmptyStr(document.all('bm'),"编码不能为空！");
	 if(sumbit_check())
	 {
	 document.all("Submit").click();
		window.returnValue="refresh";
	 }
	 // if (CkEmptyStr(document.all("code_id"),"编码不能为空！")&&CkEmptyStr(document.all("code_name"),"编码说明不能为空！"))
	 // {
	//	document.all("Submit").click();
	//	window.returnValue="refresh";
	 // }
	
}
</script>
<BODY class="mainbody" onLoad="document.all('table_name').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <!--  
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 编码管理 &gt;&gt; <a href="codemanage.jsp">编码表管理</a> &gt;&gt; 添加编码 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>-->
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">编码：</div></td>
        <td width="80%">
        <%out.print(cu.print("SYSTEM_TABLECODEMETA_COL","CODE_ID")); %>
      
        </td>
      </tr>
      <tr>
      <td><div align="right">编码说明：</div></td>
        <td>
        <%out.print(cu.print("SYSTEM_TABLECODEMETA_COL","CODE_NAME")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">编码层次：</div></td>
        <td width="80%">
        <%out.print(cu.print("SYSTEM_TABLECODEMETA_COL","LEVEL_ID",String.valueOf((code_id.length()/4+1)),"readonly")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">父级编码：</div></td>
        <td width="80%">
        <%out.print(cu.print("SYSTEM_TABLECODEMETA_COL","PCODE_ID",code_id,"readonly"));
         %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">父级编码描述：</div></td>
        <td width="80%">
        <%out.print(cu.print("SYSTEM_TABLECODEMETA_COL","PCODE_NAME",pcode_name,"readonly")); %>
        </td>
      </tr>
       <tr>
       <input name="entity" id="entity" type="hidden" value="SYSTEM_TABLECODEMETA_COL"/>
       <input name="SYSTEM_TABLECODEMETA_COL.TABLE_NAME" id="SYSTEM_TABLECODEMETA_COL.TABLE_NAME" type="hidden" value="<%=table_name %>"/>
       <input name="table_name" type="hidden" id="act" value="<%=table_name %>">
        <input name="act" type="hidden" id="act" value="addcolumn">
         <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
            <input name="action_class" type="hidden" id="action_class" value="com.action.system.CodeAction"></td>
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
<%
out.print(cu.getCheckstr());
%>