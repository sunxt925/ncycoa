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
<script language="javascript" src="../js/public/key.js"></script>
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

	request.setCharacterEncoding("gb2312");
	String bm=Format.NullToBlank(request.getParameter("bm"));
  	DocMetaVersionInfo mo=new DocMetaVersionInfo(bm);
%>
<BODY class="mainbody"  scroll="no">

<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="1" cellspacing="0" cellpadding="0" class="table_listchao">
      <tr>
        <td width="20%"><div align="right">������ţ�</div></td>
        <td width="80%"><input name="DocCode" type="text" class="input1" id="DocCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getDocCode()%>" size="30" maxlength="30"  readonly="readonly"></td>
      </tr>
      <tr>
        <td><div align="right">�������ƣ�</div></td>
        <td><input name="DocVersionName" type="text" class="input1" id="DocVersionName" onKeyDown="EnterKeyDo('')" value="<%=mo.getDocVersionName()%>" size="30" maxlength="50"></td>
      </tr>
      <tr>
      	  <td width="20%"><div align="right">�����汾��</div></td>
        <td width="80%">
        <select name="UpdateFlag"  id="UpdateFlag" style="width:192px">
             <option value="<%=mo.getUpdateFlag()%>" SELECTED><%=mo.getUpdateFlag()%></option>
             <option value="A/0">A/0</option>
             <option value="A/1">A/1</option>
          </select>
          </td>     
      </tr>
       <tr>
      	  <td width="20%"><div align="right">�����������</div></td>
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
	  <td width="20%"><div align="right">�������ڣ�</div></td>
        <td width="80%"><input name="DrawUpDate" type="Wdate" class="input1" id="DrawUpDate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=mo.getDrawUpDate()%>" size="30" maxlength="30"></td>     
       </tr>
      <tr>  
	   <td width="20%"><div align="right">��ע��</div></td>
        <td width="80%"><input name="Memo" type="text" class="input1" id="Memo" onKeyDown="EnterKeyDo('')" value="<%=mo.getMemo ()%>" size="30" maxlength="3000"></td>     
	     </tr>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="modify">
        <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input type="reset" name="reset" value="����" style="display:none">
        <input name="DocNo" type="hidden" id="DocNo" value="<%=mo.getDocNo()%>">
        <input name="DocClassCode" type="hidden" id="DocClassCode" value="<%=mo.getDocClassCode()%>">
        <input name="BelongMode" type="hidden" id="MemberCount" value="<%=mo.getBelongMode()%>">
        <input name="BelongDocNo" type="hidden" id="BelongDocNo" value="<%=mo.getBelongDocNo()%>"></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdManageAction"></td>
      </tr>
    </table></td>
  </tr>

  </table>
</form>

</BODY>
</HTML>
