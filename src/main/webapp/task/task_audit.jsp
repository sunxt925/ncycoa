<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.task.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ����̲ݾ�</TITLE>
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
	
	
	document.all("Submit").click();
	window.returnValue="refresh";
	


	
	
}
</script>
<%
	request.setCharacterEncoding("gb2312");
	String taskno = request.getParameter("taskno");

%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
 
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">��˽����</div></td>
        <td width="80%">
        
                  δͨ��<input type="radio"  name="auditflag" id="auditflag" value="10" />
                  ͨ��<input type="radio"  name="auditflag" id="auditflag" value="11" />

        </td>
      </tr>
     
          <input type="submit" name="Submit" value="�ύ" style="display:none">
            <input type="reset" name="reset" value="����" style="display:none">

            <input name="act" type="hidden" id="act" value="audit">
            <input name="taskno" type="hidden" id="taskno" value="<%=taskno %>" />
          
            
            
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

 
