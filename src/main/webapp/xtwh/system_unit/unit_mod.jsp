<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<script language="javascript" type="text/javascript" src="../../js/MyDatePicker/WdatePicker.js"></script>

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
	if (CkEmptyStr(document.all("OrgCode"),"����벻��Ϊ�գ�"))
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
	var strs= new Array(); //����һ���� 
	strs=str.split(";"); //�ַ��ָ� 
	 
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
  <table cellpadding="5"  width="100%" align="left" >
				<tr>
					<td><a id="F8" style="display: none" href="#" onClick="F8()">����[F8]</a></td>
				</tr>
			
			<tr>
				<td><span>�������룺</span></td>
				<td>
				<%
        	out.print(cu.print("BASE_ORG","ORGCODE",mo.getCode(),"readonly"));
        %>			
        </td>
			</tr>
			<tr>
				<td><span>����ȫ�ƣ�</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","ORGNAME",mo.getName()));
        %>
				</td>
			</tr>
			 
			<tr>
				<td><span>������ƣ�</span></td>
				<td>
					 <%
        	out.print(cu.print("BASE_ORG","ORGSIMPLENAME",mo.getOrgSimpleName ()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>����ְ�ܣ�</span></td>
				<td>
				 <%
         	out.print(cu.print("BASE_ORG","ORGDESC",mo.getOrgDesc ()));
         %>
				</td>
			</tr>
			
			<tr>
				<td><span>��Ա����</span></td>
				<td>
				<%
        	out.print(cu.print("BASE_ORG","MEMBERCOUNT",mo.getMemberCount ()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>��λ����</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","POSITIONCOUNT",mo.getPositionCount ()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>�칫�ҵ�ַ��</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","OFFCIEADDRESS",mo.getOffcieAddress ()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>�������룺</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","POSTCODE",mo.getPostCode ()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>��ϵ��ʽ��</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","CONTACTINFO",mo.getContactInfo ()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>�������</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","ORGCLASS",mo.getOrgClass()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>�������ڣ�</span></td>
				<td>
					<%
        	out.print(cu.print("BASE_ORG","CREATEDATE",mo.getCreatedate()));
        %>
				</td>
			</tr>
			<tr>
				<td><span>�ϼ����ű��룺</span></td>
				<td>
					<%
         	out.print(cu.print("BASE_ORG","PARENTORGCODE",mo.getParentOrgCode(),"readonly"));
         %>
				</td>
			</tr>
			<tr>
				<td><span>�ϼ��������ƣ�</span></td>
				<td>
                   <%
        	out.print(cu.print("BASE_ORG","PARENTORGNAME",mo.getParentOrgName(),"readonly"));
        %>
                    </td>		
			</tr>
			<tr>
				<td><span>�ҿ�����������</span></td>
				<td>
					<input name="BASE_ORG.BLONGADMINORGCODE" type="text" class="input1" id="BASE_ORG.BLONGADMINORGCODE" onKeyDown="EnterKeyDo('')" value="<%=mo.getBlongAdminOrgCode ()%>" readonly size="48" maxlength="48"><a href="#" onClick="select()" class="button4">ѡ��</a>
				</td>
			</tr>
			<tr>
				<td><span>�ҿ�������������</span></td>
				<td>
					 <input name="BASE_ORG.BLONGADMINORGNAME" type="text" class="input1" id="BASE_ORG.BLONGADMINORGNAME" onKeyDown="EnterKeyDo('')" value="<%=mo.getBlongAdminOrgName ()%>" readonly size="48" maxlength="200">
				</td>
			</tr>
			<tr>
				<td><span>��ע��</span></td>
				<td>
					<%out.print(cu.print("BASE_ORG","MEMO",mo.getMemo())); %>
				</td>
			</tr>
			
		</table>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
     
	      <tr>
      <td>
         <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input type="reset" name="reset" value="����" style="display:none">
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