<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<%@page import="com.business.BuyReportItem"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%
String prmcod=request.getParameter("prmcod");
 ComponentUtil cu=new ComponentUtil();
  
 %>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript" src="../../js/public/check.js"></script>
<script language="javascript" src="../../js/DatePicker/WdatePicker.js"></script>

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
	
	 document.all("Submit").click();
     
		window.returnValue="refresh";
	 }
	
	
}

</script>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      
      <tr>

        <td width="30%"><div align="right"> 表单字典编码：</div></td>
        <td width="70%">
        <%out.print(cu.print("CODCTDEP","PRMCOD",prmcod,"readonly")); 
        
        %>
        </td>
           </tr>
      
      <tr>

        <td width="30%"><div align="right"> 编码：</div></td>
        <td width="70%">
        <%out.print(cu.print("CODCTDEP","PRMCON")); 
        
        %>
        </td>
           </tr>
           <tr>

        <td width="30%"><div align="right"> 编码名称：</div></td>
        <td width="70%">
        <%out.print(cu.print("CODCTDEP","PRMCND")); 
        
        %>
        </td>
           </tr>
        <input type="hidden" name="CODCTDEP.DEPART" id="CODCTDEP.DEPART" value="000"/>
<input type="hidden" name="CODCTDEP.CNDPM4" id="CODCTDEP.CNDPM4" value="0"/>
<input type="hidden" name="CODCTDEP.CNDPM5" id="CODCTDEP.CNDPM5" value="0"/>
<input type="hidden" name="CODCTDEP.CNDPM6" id="CODCTDEP.CNDPM6" value="0"/>

       <tr>
       <input name="entity" id="entity" type="hidden" value="CODCTDEP"/>
        <input name="act" type="hidden" id="act" value="add">
           <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
          
            <input name="action_class" type="hidden" id="action_class" value="com.action.system.FormCodeItemAction"></td>
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