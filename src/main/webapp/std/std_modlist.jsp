<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.std.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<base target="_self">
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<script language=
                "javascript" type="text/javascript" src="../js/MyDatePicker/WdatePicker.js">  </script>

<script language="javascript" src="../js/public/check.js"></script>
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
	if (CkEmptyStr(document.all("DocNo"),"����벻��Ϊ�գ�"))
	{
		//alert (document.all("act"));
		document.all("Submit").click();
	}
}
function fun(DocClassName)
{
    if(DocClassName=="������׼.����˵����")
    {
    	document.form1.DocClassCode.value="001.001";
    }else if(DocClassName=="������׼.��λ˵����")
    {
    	document.form1.DocClassCode.value="001.002";
    }else if(DocClassName=="�����׼.����ҵ����")
    {
    	document.form1.DocClassCode.value="002.001";
    }else if(DocClassName=="������׼.����ҵ����")
    {
    	document.form1.DocClassCode.value="003.001";
    }
}
</script>
<%
	//String orgcode=request.getParameter("orgcode");
	request.setCharacterEncoding("gb2312");
	String bm=Format.NullToBlank(request.getParameter("bm"));/// readonly="readonly"
  	DocMetaVersionInfo mo=new DocMetaVersionInfo(bm);
  	String orgcode=mo.getDrawUpOrg();
  	Org org=new Org(orgcode);
  	String orgname=org.getName();
%>
<BODY class="mainbody" onLoad="document.all('newbm').focus();" scroll="no">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
    <tr>
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">����[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">��׼��ţ�</div></td>
        <td width="80%"><input name="DocCode" type="text" class="input1" id="DocCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getDocCode()%>" size="30" maxlength="30"></td>
      </tr>
      <tr>
        <td><div align="right">��׼���ƣ�</div></td>
        <td><input name="DocVersionName" type="text" class="input1" id="DocVersionName" onKeyDown="EnterKeyDo('')" value="<%=mo.getDocVersionName()%>" size="30" maxlength="50"></td>
      </tr>
      <tr>
      	  <td width="20%"><div align="right">��׼�汾��</div></td>
        <td width="80%">
        <select name="UpdateFlag"  id="UpdateFlag" style="width:192px">
             <option value="<%=mo.getUpdateFlag()%>" SELECTED><%=mo.getUpdateFlag()%></option>
             <option value="A/0">A/0</option>
             <option value="A/1">A/1</option>
          </select>
          </td>     
      </tr>
            <tr>
      	  <td width="20%"><div align="right">��׼�����ƣ�</div></td>
        <td width="80%">
        <select name="DocClassName" onChange="fun(this.value)" id="DocClassName" style="width:192px">
             <option value="<%=mo.getDocClassName()%>"SELECTED><%=mo.getDocClassName()%></option>
             <option value="������׼.����˵����">������׼.����˵����</option>
             <option value="������׼.��λ˵����">������׼.��λ˵����</option>
             <option value="�����׼.����ҵ����">�����׼.����ҵ����</option>
             <option value="������׼.����ҵ����">������׼.����ҵ����</option>
          </select>
          </td>     
      </tr>
       <tr>
      	  <td width="20%"><div align="right">��׼�������</div></td>
        <td width="80%">
        <select name="DocContentType"  id="DocContentType" style="width:192px">
             <option value="<%=mo.getDocContentType()%>"SELECTED><%=mo.getDocContentType()%></option>
             <option value="�����ĵ�">�����ĵ�</option>
             <option value="����ͼ">����ͼ</option>
             <option value="��¼���">��¼���</option>
          </select>
          </td>     
      </tr>
      <tr>
	  <td width="20%"><div align="right">����������</div></td>
         <td width="80%"><input name="AttachDocCount" type="text" class="input1" id="ParentOrgName" onKeyDown="EnterKeyDo('')" value="<%=mo.getAttachDocCount()%>" size="30" maxlength="30"  readonly="readonly"></td>      
      </tr>
      <tr>
      	<td width="20%"><div align="right">�޶�������</div></td>
        <td width="80%"><input name="PartDocCount" type="text" class="input1" id="ParentOrgCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getPartDocCount()%>" size="30" maxlength="30" ></td>
       </tr>
      <tr> 
	  <td width="20%"><div align="right">�����ˣ�</div></td>
        <td width="80%"><input name="DrawUpPerson" type="text" class="input1" id="DrawUpPerson" onKeyDown="EnterKeyDo('')" value="<%=mo.getDrawUpPerson()%>" size="30" maxlength="30"></td>     
       </tr>
      <tr>
	  <td width="20%"><div align="right">���Ʋ��ţ�</div></td>
        <td width="80%"><input name="DrawOrg" type="text" class="input1" id="DrawOrg" onKeyDown="EnterKeyDo('')" value="<%=orgname%>" size="30" maxlength="30" readonly="readonly"></td>      
      </tr>
            <tr> 
	  <td width="20%"><div align="right">�������ڣ�</div></td>
        <td width="80%"><input name="DrawUpDate" type="Wdate" class="input1" id="DrawUpDate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=mo.getDrawUpDate()%>" size="30" maxlength="30"></td>     
       </tr>
             <tr> 
	  <td width="20%"><div align="right">��׼���ڣ�</div></td>
        <td width="80%"><input name="ApproveDate" type="Wdate" class="input1" id="ApproveDate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=mo.getApproveDate()%>" size="30" maxlength="30"></td>     
       </tr>
             <tr> 
	  <td width="20%"><div align="right">��׼�ˣ�</div></td>
        <td width="80%"><input name="Approver" type="text" class="input1" id="Approver" onKeyDown="EnterKeyDo('')" value="<%=mo.getApprover()%>" size="30" maxlength="30"></td>     
       </tr>
            <tr> 
	  <td width="20%"><div align="right">��Ч��ʼ���ڣ�</div></td>
        <td width="80%"><input name="ValidBeginDate" type="Wdate" class="input1" id="ValidBeginDate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=mo.getValidBeginDate()%>" size="30" maxlength="30"></td>     
       </tr>
                   <tr> 
	  <td width="20%"><div align="right">��Ч�������ڣ�</div></td>
        <td width="80%"><input name="ValidEndDate" type="Wdate" class="input1" id="ValidEndDate" onfocus="new WdatePicker({lang:'zh-cn'})" value="<%=mo.getValidEndDate()%>" size="30" maxlength="30"></td>     
       </tr>
      <tr>  
	   <td width="20%"><div align="right">��ע��</div></td>
        <td width="80%"><input name="Memo" type="text" class="input1" id="Memo" onKeyDown="EnterKeyDo('')" value="<%=mo.getMemo ()%>" size="30" maxlength="3000"></td>     
	     </tr>
	      <tr>
      <td>
         <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input type="reset" name="reset" value="����" style="display:none">
          </td>
      </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="modify">
        <input name="DocNo" type="hidden" id="DocNo" value="<%=mo.getDocNo()%>">
        <input name="DocClassCode" type="hidden" id="DocClassCode" value="<%=mo.getDocClassCode()%>">
        <input name="BelongMode" type="hidden" id="MemberCount" value="<%=mo.getBelongMode()%>">
         <input name="flag" type="hidden" id="flag" value="<%=mo.getFlag() %>">
         <input name="DrawUpOrg" type="hidden" id="DrawUpOrg" value="<%=mo.getDrawUpOrg()%>" >
        <input name="BelongDocNo" type="hidden" id="BelongDocNo" value="<%=mo.getBelongDocNo()%>">
        <input name="DocVersion" type="hidden" id="DocVersion" value="<%=mo.getDocVersion()%>">
        <input name="DocVersionStatus" type="hidden" id="DocVersionStatus" value="<%=mo.getDocVersionStatus()%>">
        <input name="TempleteFlag" type="hidden" id="TempleteFlag" value="<%=mo.getTempleteFlag()%>">
        <input name="StoreFileFlag" type="hidden" id="StoreFileFlag" value="<%=mo.getStoreFileFlag()%>">
        <input name="PartDocCount" type="hidden" id="PartDocCount" value="<%=mo.getPartDocCount()%>">
        <input name="olddoccode" type="hidden" id="olddoccode" value="<%=mo.getDocCode()%>">
        <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>">
        </div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdManageAction"></td>
      </tr>
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