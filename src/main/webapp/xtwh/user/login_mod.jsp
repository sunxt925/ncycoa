<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.UserLogin" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">



<HTML>
<HEAD>
<base target="_self">
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>

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
	if (CkEmptyStr(document.all("usercode"),"用户名不能为空！") && CkEmptyStr(document.all("password"),"密码不能为空！"))
	{
		if((document.getElementById("password").value)!=(document.getElementById("password0").value))
		  alert("两次输入密码不一致，请重新输入。");
		else
		  document.all("Submit").click();
	}
}
</script>

<%
	request.setCharacterEncoding("gb2312");
	String staffcode=Format.NullToBlank(request.getParameter("staffcode"));
	UserLogin staff=new UserLogin(staffcode);

	
%>
<BODY class="mainbody" onLoad="document.all('staffcode').focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  
      <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top">
    <table cellpadding="5"  width="100%" align="left" >
						<tr>
							<td><span>登录名：</span></td>

							<%
								if (staff.getUsercode().equals("")) {
							%>
							<td><input name="usercode" type="text" class="easyui-textbox"
								id="usercode" onKeyDown="EnterKeyDo('')" size="30"
								maxlength="30"></td>
							<%
								} else {
							%>

							<td><input name="usercode" type="text" class="easyui-textbox"
								id="usercode" value="<%=staff.getUsercode()%>"
								onKeyDown="EnterKeyDo('')" size="30" maxlength="30"></td>
							<%
								}
							%>
						</tr>
			<tr>
				<td><span>新密码：</span></td>
				<td>
					<input name="password" type="password" class="easyui-textbox" id="password" onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
			 
			<tr>
				<td><span>重复密码：</span></td>
				<td>
					<input name="password0" type="password" class="easyui-textbox" id="password0"  onKeyDown="EnterKeyDo('')" size="40" maxlength="40">
				</td>
			</tr>
      
    
       
          <input name="act" type="hidden" id="act" value="modify">
          <input name="staffcode" type="hidden" id="staffcode" value="<%=staffcode %>">
          
          <input type="reset" name="reset" value="重置" style="display:none">
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.UserLoginAction"></td>
      
       <input type="button" id="btn_ok" style="display: none" onclick="ret()">
      <input type="submit" name="Submit" value="提交" style="display:none" >
    </table></td>
  </tr>
 <!--  <tr>
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr> -->
</form>
</table>
<script type="text/javascript">
function ret(){
	var api = frameElement.api;
 	document.all("Submit").click();
 	(api.data)({code:"refresh"});
}
</script>
</BODY>
</HTML>
