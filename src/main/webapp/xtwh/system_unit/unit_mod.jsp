<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<script language="javascript" type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>

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
	if (CkEmptyStr(document.all("OrgCode"),"层次码不能为空！"))
	{
		//alert (document.all("act"));
		document.all("Submit").click();
	}
}

function select()
{
	var newtreeurl='unitcheckbox_tree.jsp';
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //定义一数组 
	strs=str.split(";"); //字符分割 
	 
	document.getElementById("BASE_ORG.BLONGADMINORGNAME").value=strs[0];
	document.getElementById("BASE_ORG.BLONGADMINORGCODE").value=strs[1];
	//window.open (newtreeurl,"Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=yes, copyhistory=no,width=350,height=540,left=200,top=300");
}
</script>
<%

	request.setCharacterEncoding("gb2312");
	String bm=Format.NullToBlank(request.getParameter("bm"));
	
  	Org mo=new Org(bm);
  	ComponentUtil cu=new ComponentUtil();
  	
%>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">当前操作&gt;&gt; 单位信息 &gt;&gt;<%=mo.getName()%>&gt;&gt; 信息修改</td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
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
        <td width="20%"><div align="right">机构编码：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","ORGCODE",mo.getCode(),"readonly"));
        %>
        <!--<input name="OrgCode" type="text" class="input1" id="OrgCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getCode()%>" size="30" maxlength="30"  readonly="readonly">
        
        --></td>
      </tr>
      <tr>
        <td><div align="right">机构全称：</div></td>
        <td>
        <%
        	out.print(cu.print("BASE_ORG","ORGNAME",mo.getName()));
        %>
        <!--<input name="OrgName" type="text" class="input1" id="OrgName" onKeyDown="EnterKeyDo('')" value="<%=mo.getName()%>" size="30" maxlength="40">
        --></td>
      </tr>
      <tr>
        <td><div align="right">机构简称：</div></td>
        <td>
        <%
        	out.print(cu.print("BASE_ORG","ORGSIMPLENAME",mo.getOrgSimpleName ()));
        %>
        <!--<input name="OrgSimpleName" type="text" class="input1" id="OrgSimpleName" onKeyDown="EnterKeyDo('')" value="<%=mo.getOrgSimpleName ()%>" size="30" maxlength="200">
        --></td>
      </tr>
      <tr>
        <td><div align="right">机构职能：</div></td>
         <td width="80%">
         <%
         	out.print(cu.print("BASE_ORG","ORGDESC",mo.getOrgDesc ()));
         %>
         <!--<input name="OrgDesc" type="text" class="input1" id="OrgDesc" onKeyDown="EnterKeyDo('')" value="<%=mo.getOrgDesc ()%>" size="30" maxlength="30">
         --></td>
      </tr>
      <tr>
	  <td width="20%"><div align="right">成员数：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","MEMBERCOUNT",mo.getMemberCount ()));
        %>
        <!--<input name="MemberCount" type="text" class="input1" id="MemberCount" onKeyDown="EnterKeyDo('')" value="<%=mo.getMemberCount ()%>" size="30" maxlength="30">
        --></td>
       </tr>
      <tr> 
	  <td width="20%"><div align="right">岗位数：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","POSITIONCOUNT",mo.getPositionCount ()));
        %>
        
        <!--<input name="PositionCount" type="text" class="input1" id="PositionCount" onKeyDown="EnterKeyDo('')" value="<%=mo.getPositionCount ()%>" size="30" maxlength="30">
        --></td>     
      </tr>
      <tr> 
	  <td width="20%"><div align="right">办公地址：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","OFFCIEADDRESS",mo.getOffcieAddress ()));
        %>
        <!--<input name="OffcieAddress" type="text" class="input1" id="OffcieAddress" onKeyDown="EnterKeyDo('')" value="<%=mo.getOffcieAddress ()%>" size="30" maxlength="30">
        --></td>     
       </tr>
      <tr> 
	  <td width="20%"><div align="right">邮政编码：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","POSTCODE",mo.getPostCode ()));
        %>
        <!--<input name="PostCode" type="text" class="input1" id="PostCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getPostCode ()%>" size="30" maxlength="30">
        --></td>     
       </tr>
      <tr> 
	  <td width="20%"><div align="right">联系方式：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","CONTACTINFO",mo.getContactInfo ()));
        %>
        <!--<input name="ContactInfo" type="text" class="input1" id="ContactInfo" onKeyDown="EnterKeyDo('')" value="<%=mo.getContactInfo ()%>" size="30" maxlength="30">
        --></td>     
       </tr>
      <tr> 
	  <td width="20%"><div align="right">机构类别：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","ORGCLASS",mo.getOrgClass()));
        %>
        <!--<input name="OrgClass" type="text" class="input1" id="OrgClass" onKeyDown="EnterKeyDo('')" value="<%=mo.getOrgClass ()%>" size="30" maxlength="30">
        --></td>     
       </tr>
      <tr> 
	  <td width="20%"><div align="right">创建日期：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","CREATEDATE",mo.getCreatedate()));
        %>
        <!--<input name="Createdate" type="Wdate" class="input1" id="Createdate" onFocus="new WdatePicker(this,null,false,'whyGreen')" onKeyDown="EnterKeyDo('')" value="<%=mo.getCreatedate()%>" size="30" maxlength="30">
        --></td>     
       </tr>
      
      <tr> 
	  <td width="20%"><div align="right">上级部门代码：</div></td>
        <td width="80%">
         <%
         	out.print(cu.print("BASE_ORG","PARENTORGCODE",mo.getParentOrgCode(),"readonly"));
         %>
        <!--<input name="ParentOrgCode" type="text" class="input1" id="ParentOrgCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getParentOrgCode()%>" size="30" maxlength="30"  readonly="readonly">
        --></td>     
       </tr>
      <tr>
	  <td width="20%"><div align="right">上级部门名称：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","PARENTORGNAME",mo.getParentOrgName(),"readonly"));
        %>
        <!--<input name="ParentOrgName" type="text" class="input1" id="ParentOrgName" onKeyDown="EnterKeyDo('')" value="<%=mo.getParentOrgName()%>" size="30" maxlength="30"  readonly="readonly">
        --></td>      
      </tr>
      <tr> 
	  <td width="20%"><div align="right">挂靠行政机构：</div></td>
        <td width="80%">
        <input name="BASE_ORG.BLONGADMINORGCODE" type="text" class="input1" id="BASE_ORG.BLONGADMINORGCODE" onKeyDown="EnterKeyDo('')" value="<%=mo.getBlongAdminOrgCode ()%>" readonly size="48" maxlength="48"><a href="#" onClick="select()" class="button4">选择</a>
        <!--<input name="BlongAdminOrgCode" type="text" class="input1" id="BlongAdminOrgCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getBlongAdminOrgCode ()%>" readonly size="30" maxlength="30"><a href="#" onClick="select()" class="button4">选择</a>
        --></td>     
       </tr>
      <tr>
	  <td width="20%"><div align="right">挂靠行政机构名称：</div></td>
        <td width="80%">
        <input name="BASE_ORG.BLONGADMINORGNAME" type="text" class="input1" id="BASE_ORG.BLONGADMINORGNAME" onKeyDown="EnterKeyDo('')" value="<%=mo.getBlongAdminOrgName ()%>" readonly size="48" maxlength="200">
        <!--<input name="BlongAdminOrgName" type="text" class="input1" id="BlongAdminOrgName" onKeyDown="EnterKeyDo('')" value="<%=mo.getBlongAdminOrgName ()%>" readonly size="30" maxlength="30">--></td>      
      <tr>  
	   <td width="20%"><div align="right">备注：</div></td>
        <td width="80%">
        <%
        	out.print(cu.print("BASE_ORG","MEMO",mo.getMemo ()));
        %>
        <!--<input name="Memo" type="text" class="input1" id="Memo" onKeyDown="EnterKeyDo('')" value="<%=mo.getMemo ()%>" size="30" maxlength="30">
        
        --></td>     
	      <tr>
      <td>
         <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="modify"></div></td>
        <input name="OrgCode" type="hidden" id="OrgCode" value="<%=mo.getCode()%>">
        <input type="hidden" name="entity" id="entity" value="BASE_ORG"/>
        <input name="old_ORGCODE" type="hidden" id="old_ORGCODE" value="<%=mo.getCode()%>">
        
        <input name="BASE_ORG.NONLEAFFLAG" type="hidden" class="input1" id="BASE_ORG.NONLEAFFLAG" value="<%=mo.getNonLeafFlag ()%>" size="30" maxlength="30">
        <!--<input name="NonLeafFlag" type="hidden" class="input1" id="NonLeafFlag" onKeyDown="EnterKeyDo('')" value="<%=mo.getNonLeafFlag ()%>" size="30" maxlength="30">
        --><td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitAction"></td>
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