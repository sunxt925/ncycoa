<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充市烟草局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<style type="text/css">
<!--
.STYLE1 {color: #ff0000}
-->
</style>
</HEAD>
<base target="_self">
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript" src="../js/public/check.js"></script>
<script language="javascript" src="../js/DatePicker/WdatePicker.js"></script>
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
//alert(sumbit_check());
	if(sumbit_check()){
	
	document.all("Submit").click();
	window.returnValue="refresh";
	
	}
	else
	{
	alert(false);
	}
	
	
}
</script>
<%
	request.setCharacterEncoding("gb2312");
	ComponentUtil cu=new ComponentUtil();
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
 
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">工作计划名称：</div></td>
        <td width="80%">
        
        <%out.print(cu.print("MONTHTASK","TASKNAME")); %>
        <!-- 
        <input name="cxmc" type="text" class="input1" id="cxmc" onKeyDown="EnterKeyDo('')" size="50" maxlength="100">
         -->
        </td>
      </tr>
      <tr>
        <td align="right">工作计划说明：</td>
        <td>
         <%out.print(cu.print("MONTHTASK","TASKDESC")); 
         
         %>
         <!-- 
        <textarea name="ms" rows="5" class="text" id="ms" style="width:100%"></textarea>
         -->
        
        </td>
      </tr>
	  <tr>
        <td align="right">执行月份：</td>
        <td>
        <input type="text" id="MONTHTASK.MONTH" name="MONTHTASK.MONTH" onFocus="new WdatePicker(this,'%Y-%M-01',false,'whyGreen')"/>
        <!--
        <textarea name="sql" rows="10" class="text" id="sql" style="width:100%"></textarea>
          -->
        </td>
      </tr>
     


      
	  <tr>
        <td><div align="right">备注：</div></td>
        <td>
        
        <!-- 
        <input name="gdls" type="text" class="input1" id="gdls" onKeyDown="EnterKeyDo('F8()')" onKeyUp="this.value=this.value.replace(/\D/g,'')" value="0" size="3" maxlength="3"  onafterpaste="this.value=this.value.replace(/\D/g,'')" style="text-align:right">
        
         -->
         <%out.print(cu.print("MONTHTASK","MEMO")); %>
        </td>
      </tr>
         <input type="hidden" name="entity" id="entity" value="MONTHTASK"/>
          <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
            <input name="act" type="hidden" id="act" value="add">
          
            
            
            <input name="action_class" id="action_class" type="hidden" value="com.action.task.TaskAction"/>
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

 <%out.print(cu.getCheckstr());%>
