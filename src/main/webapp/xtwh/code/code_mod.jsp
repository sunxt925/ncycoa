<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.common.*,com.entity.*" errorPage="" %>
<%@page import="com.entity.system.Code"%>
<%@page import="com.entity.system.SystemEntity"%>
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
request.setCharacterEncoding("gb2312");
	String table_name=Format.NullToBlank(request.getParameter("table_name"));
	Code co=new Code(table_name);

ComponentUtil cu=new ComponentUtil(); 
   CodeDictionary cd=new CodeDictionary();
   SystemEntity se=new SystemEntity(); 
   
 DataTable dt=se.getAllEntityList();
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
	if (sumbit_check())
	{
	   
		//alert ("true");	
		document.all("Submit").click();
		window.returnValue="refresh";
	}
	else
	{
		alert ("false");
	}
	
}
</script>

<BODY class="mainbody">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      
         <tr>
      
      
       <td><div align="right">编码表类别：</div></td>
        <td>
         
        <select onchange="select(this.value)" name="SYSTEM_TABLECODEMETA.CODE_CLASS" id="SYSTEM_TABLECODEMETA.CODE_CLASS" style="width: 300">
        <option value="<%=co.getCode_class() %>"><%=CodeDictionary.code_traslate("code_class",co.getCode_class()) %></option>
        <%
        out.print(cd.getselectItem("code_class"));
          %>
        
        </select>
        
        </td>
       </tr>
      
           <!-- 
       <input type="text" name="table_name1" id="table_name1" style="width:300;display:''" class="input1" value=""/>
       <select name="table_name2" id="table_name2" style="width:300;display:none">
       <option value="></option>
      
       
       </select> -->
      
       <tr>
        <td width="20%"><div id="bm_id" align="right">编码表名：</div></td>
        <td width="80%">
       <input type="text" name="SYSTEM_TABLECODEMETA.TABLE_NAME" id="SYSTEM_TABLECODEMETA.TABLE_NAME" class="input1" style="width:300" value="<%=co.getTable_name()%>"/>
   
        </td>
   
      </tr>
      
      
   
      <tr>
        <td><div align="right">编码表描述：</div></td>
        <td>
        
        <%out.print(cu.print("SYSTEM_TABLECODEMETA","TABLE_TITLE",co.getTable_title())); %>
        
        </td>
      </tr>
      <tr>
        <td><div align="right">编码字段名：</div></td>
        <td>
        <%out.print(cu.print("SYSTEM_TABLECODEMETA","CODE_COL",co.getCode_col())); %>
       </td>
      </tr>
      <tr>
        <td><div align="right">编码值字段名：</div></td>
        <td>
         <%out.print(cu.print("SYSTEM_TABLECODEMETA","VALUE_COL",co.getValue_col())); %>
      </td>
      </tr>
      <tr>
        <td><div align="right">父级编码值字段名：</div></td>
        <td>
         <%out.print(cu.print("SYSTEM_TABLECODEMETA","PCODE_COL",co.getPcode_col())); %>
       </td>
      </tr>
      <tr>
        <td><div align="right">是否自动加载：</div></td>
        <td width="80%"><%=Coder.getBoolealRadioItem("SYSTEM_TABLECODEMETA.ISLOAD",co.getIsload())%></td>
       
      </tr>
      
    </table></td>
  </tr>
  <tr>
  
    <input name="old_TABLE_NAME" id="old_TABLE_NAME" type="hidden" value="<%=co.getTable_name()%>"/>
        <input name="act" type="hidden" id="act" value="modify">
       <input name="entity" type="hidden" id="entity" value="SYSTEM_TABLECODEMETA"/>
          <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
            <input name="action_class" type="hidden" id="action_class" value="com.action.system.CodeAction"/>
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
<% out.print(cu.getCheckstr());%>