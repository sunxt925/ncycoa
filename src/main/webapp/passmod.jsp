<%@page import="com.entity.system.UserInfo"%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.UserLogin" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">



<HTML>
<HEAD>
<base target="_self">
<TITLE>�ϳ����̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<script language="javascript" src="js/public/key.js"></script>
<script language="javascript" src="js/public/check.js"></script>
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

	if (CkEmptyStr(document.all("password"),"���벻��Ϊ�գ�"))
	{
		
		
		if((document.getElementById("password").value)!=(document.getElementById("password0").value))
		  alert("�����������벻һ�£����������롣");
		else
		  document.all("Submit").click();
	}
}
</script>

<%
	request.setCharacterEncoding("gb2312");
	UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
	UserLogin staff=new UserLogin(u.getStaffcode());

	
%>
<BODY class="mainbody" onLoad="document.all('staffcode').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="servlet/PageHandler">
 
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      
     
      <tr>
        <td><div align="right">�����룺</div></td>
        <td><input name="password" type="text" class="input1" id="password" onKeyDown="EnterKeyDo('')" size="40" maxlength="40"></td>
      </tr>
      <tr>
        <td><div align="right">�ظ����룺</div></td>
        <td><input name="password0" type="text" class="input1" id="password0"  onKeyDown="EnterKeyDo('')" size="40" maxlength="40"></td>
      </tr>
       <input type="hidden" name="usercode" id="usercode" value="<%=staff.getUsercode() %>" >
      <tr>
       
          <input name="act" type="hidden" id="act" value="modify">
          <input name="staffcode" type="hidden" id="staffcode" value="<%=u.getStaffcode() %>">
          
          <input type="reset" name="reset" value="����" style="display:none">
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.UserLoginAction"></td>
      </tr>
      
      <tr>
      <input type="submit" name="Submit" value="�ύ" style="display:none" >
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
