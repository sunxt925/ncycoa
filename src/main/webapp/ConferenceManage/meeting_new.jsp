<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
</HEAD>
<base target="_self">
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires",0);
SystemModule og=new SystemModule();
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
 DataTable dt=og.getMenuList(unitccm);
 String newcoding="";
 if(dt!=null && dt.getRowsCount()>0){
 String code =dt.get(dt.getRowsCount()-1).getString("level_code");
 
 String temp=code.substring(code.length()-3,code.length());
 
 int newcode=Integer.parseInt(temp)+1; 
  String s = String.valueOf(newcode);
  String res="";
  for(int i=s.length();i<3;i++)
  {
  	res+="0";
  }
 res+=s;
 newcoding=code.substring(0,code.length()-3)+res;

 }
 else{
 newcoding=unitccm+"001";
 }
 %>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript" src="../js/public/check.js"></script>
<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>

<style type="text/css">
	body{ font: 80% "Trebuchet MS", sans-serif; margin: 50px;}
</style>


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
		//alert ("aaa");
		document.all("Submit").click();
		//window.close();
	}
	
}
</script>

<% ComponentUtil cu=new ComponentUtil(); %>


<BODY class="mainbody" >

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">会议录入</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
      <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">会议名称：</div></td>
        <td width="80%">
         <%out.print(cu.print("COM_MEETINGINFO","MEETINGNAME")); %><!--
        <input name="level_code" type="text" class="input1" id="level_code" onKeyDown="EnterKeyDo('')" value="<%=newcoding %>" size="60" maxlength="60" readonly="readonly"></td>
      --></tr>
      <tr>
        <td><div align="right">开始时段：</div></td>
        <td>
        <input type="text" onfocus="new WdatePicker(this,null,false,'whyGreen')" name="COM_MEETINGINFO.MEETINGBEGINDATE" class="input1" id="begintime"  >
        <!--
        <input name="menu_name" type="text" class="input1" id="menu_name" onKeyDown="EnterKeyDo('')" size="60" maxlength="60"></td>
      --></tr>
      <tr>
        <td><div align="right">结束时段：</div></td>
        <td>
        <input type="text" onfocus="new WdatePicker(this,null,false,'whyGreen')" name="COM_MEETINGINFO.MEETINGENDDATE" class="input1" id="endtime"  >
        <%//out.print(cu.print("COM_MEETINGINFO","MEETINGENDDATE")); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
       <tr>
        <td><div align="right">开会地点：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGINFO","MEETINGROOM")); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
       <tr>
        <td><div align="right">会议人数：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGINFO","NUMATTENDEE")); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr><!--
       <tr>
        <td><div align="right">需求部门：</div></td>
        <td>
        <%//out.print(cu.print("COM_MEETINGINFO","APPLYORGCODE",orgcode)); %></tr>
      <tr>
       --><tr>
        <td><div align="right">其他要求：</div></td>
        <td>
        <%out.print(cu.print("COM_MEETINGINFO","REQUESTDESC")); %><!--
        <input name="menu_url" type="text" class="input1" id="menu_url" onKeyDown="EnterKeyDo('')"  size="60" maxlength="60"></td>
      --></tr>
      <tr>
      <td>
      <input name="act" type="hidden" id="act" value="add">
        <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
           <input type="hidden" id="entity" name="entity" value="COM_MEETINGINFO">
           
           <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingAction"></td>
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
<% out.print(cu.getCheckstr());%>