<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.action.system.*" errorPage="" %>
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
SystemEntity se=new SystemEntity(); 
 DataTable dt=se.getAllEntityList();
 
 CodeDictionary cd=new CodeDictionary();
 //LoadCodeAction lc=new LoadCodeAction();
 ComponentUtil cu=new ComponentUtil();
  %>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript" src="../../js/public/check.js"></script>

<script language="javascript" src="../../js/public/Calendar1.js"></script>

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
	   if(document.all("table_name1").value=="")
	   {
	    document.all("SYSTEM_TABLECODEMETA.TABLE_NAME").value=document.all("table_name2").value;
	   }
	   else
	   {
	     document.all("SYSTEM_TABLECODEMETA.TABLE_NAME").value=document.all("table_name1").value;
	   }
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
<script>
    
    function select(value)
    {
    if(value=="00020002")
    {
      document.getElementById("table_name1").style.display="none";
       document.getElementById("table_name2").style.display="";
      
    }
    else
    {
   
     document.getElementById("table_name2").style.display="none";
     document.getElementById("table_name1").style.display="";
     
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
        <%
        out.print(cd.getselectItem("code_class"));
          %>
        
        </select>
        
        </td>
      </tr>
     <tr>
        <td width="20%"><div id="bm_id" align="right">编码表名：</div></td>
        <td width="80%">
        
       <input type="text" name="table_name1" id="table_name1" style="width:300;display:''" class="input1"/>
       <select name="table_name2" id="table_name2" style="width:300;display:none">
       <%out.print(cd.getselectItem(dt,"entity_name")); %>
       
       </select>
       <input type="hidden" name="SYSTEM_TABLECODEMETA.TABLE_NAME" id="SYSTEM_TABLECODEMETA.TABLE_NAME" style="display:none"/>
       <%//out.print(ComponentUtil.Print("system_tablecodemeta","table_name")); %>
 
    
        </td>
       
      </tr>
      
      <tr>
        <td><div align="right">编码表描述：</div></td>
        <td>
       
            
        <% out.print(cu.print("SYSTEM_TABLECODEMETA","TABLE_TITLE")); %>
        </td>
      </tr>
      <tr>
        <td><div align="right">编码字段名：</div></td>
        <td>
        
          <% 
          out.print(cu.print("SYSTEM_TABLECODEMETA","CODE_COL")); %>
        </td>
      </tr>
      <tr>
        <td><div align="right">编码值字段名：</div></td>
        <td>
      
              <% out.print(cu.print("SYSTEM_TABLECODEMETA","VALUE_COL")); %>
            
        </td>
      </tr>
      
      
     <tr>
        <td><div align="right">父级编码值字段名：</div></td>
          <td>  <%
         out.print(cu.print("SYSTEM_TABLECODEMETA","PCODE_COL"));
        
         
          %>
    
        </td>
      </tr>
      <tr>
        <td><div align="right">是自动否加载内存：</div></td>
       
        <td width="80%"><%=Coder.getBoolealRadioItem("SYSTEM_TABLECODEMETA.ISLOAD","1")%></td>
      
      </tr>
      
    </table></td>
  </tr>
  <tr>
    <input type="hidden" id="entity" name="entity" value="SYSTEM_TABLECODEMETA"/>
    
    <input name="action_class" type="hidden" id="action_class" value="com.action.system.CodeAction"/>
    <input name="act" id="act" type="hidden" value="add"/>
      <input type="submit" name="Submit" value="提交" style="display:none">
      <input type="reset" name="reset" id="rest" value="重置" style="display:none">
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