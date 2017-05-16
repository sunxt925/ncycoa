<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.std.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<base target="_self">
<TITLE>南充烟草专卖局</TITLE>
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
	if (CkEmptyStr(document.all("DocNo"),"层次码不能为空！"))
	{
		//alert (document.all("act"));
		document.all("Submit").click();
	}
}
function fun(DocClassName)
{
    if(DocClassName=="工作标准.部门说明书")
    {
    	document.form1.DocClassCode.value="001.001";
    }else if(DocClassName=="工作标准.岗位说明书")
    {
    	document.form1.DocClassCode.value="001.002";
    }else if(DocClassName=="管理标准.管理业务类")
    {
    	document.form1.DocClassCode.value="002.001";
    }else if(DocClassName=="技术标准.技术业务类")
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
    <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
  </tr>
<!--  <tr>-->
<!--    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>-->
<!--  </tr>-->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">标准编号：</div></td>
        <td width="80%"><input name="DocCode" type="text" class="input1" id="DocCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getDocCode()%>" size="30" maxlength="30"></td>
      </tr>
      <tr>
        <td><div align="right">标准名称：</div></td>
        <td><input name="DocVersionName" type="text" class="input1" id="DocVersionName" onKeyDown="EnterKeyDo('')" value="<%=mo.getDocVersionName()%>" size="30" maxlength="50"></td>
      </tr>
      <tr>
      	  <td width="20%"><div align="right">标准版本：</div></td>
        <td width="80%">
        <select name="UpdateFlag"  id="UpdateFlag" style="width:192px">
             <option value="<%=mo.getUpdateFlag()%>" SELECTED><%=mo.getUpdateFlag()%></option>
             <option value="A/0">A/0</option>
             <option value="A/1">A/1</option>
          </select>
          </td>     
      </tr>
            <tr>
      	  <td width="20%"><div align="right">标准类名称：</div></td>
        <td width="80%">
        <select name="DocClassName" onChange="fun(this.value)" id="DocClassName" style="width:192px">
             <option value="<%=mo.getDocClassName()%>"SELECTED><%=mo.getDocClassName()%></option>
             <option value="工作标准.部门说明书">工作标准.部门说明书</option>
             <option value="工作标准.岗位说明书">工作标准.岗位说明书</option>
             <option value="管理标准.管理业务类">管理标准.管理业务类</option>
             <option value="技术标准.技术业务类">技术标准.技术业务类</option>
          </select>
          </td>     
      </tr>
       <tr>
      	  <td width="20%"><div align="right">标准内容类别：</div></td>
        <td width="80%">
        <select name="DocContentType"  id="DocContentType" style="width:192px">
             <option value="<%=mo.getDocContentType()%>"SELECTED><%=mo.getDocContentType()%></option>
             <option value="资料文档">资料文档</option>
             <option value="流程图">流程图</option>
             <option value="记录表格">记录表格</option>
          </select>
          </td>     
      </tr>
      <tr>
	  <td width="20%"><div align="right">附件数量：</div></td>
         <td width="80%"><input name="AttachDocCount" type="text" class="input1" id="ParentOrgName" onKeyDown="EnterKeyDo('')" value="<%=mo.getAttachDocCount()%>" size="30" maxlength="30"  readonly="readonly"></td>      
      </tr>
      <tr>
      	<td width="20%"><div align="right">修订次数：</div></td>
        <td width="80%"><input name="PartDocCount" type="text" class="input1" id="ParentOrgCode" onKeyDown="EnterKeyDo('')" value="<%=mo.getPartDocCount()%>" size="30" maxlength="30" ></td>
       </tr>
      <tr> 
	  <td width="20%"><div align="right">编制人：</div></td>
        <td width="80%"><input name="DrawUpPerson" type="text" class="input1" id="DrawUpPerson" onKeyDown="EnterKeyDo('')" value="<%=mo.getDrawUpPerson()%>" size="30" maxlength="30"></td>     
       </tr>
      <tr>
	  <td width="20%"><div align="right">编制部门：</div></td>
        <td width="80%"><input name="DrawOrg" type="text" class="input1" id="DrawOrg" onKeyDown="EnterKeyDo('')" value="<%=orgname%>" size="30" maxlength="30" readonly="readonly"></td>      
      </tr>
            <tr> 
	  <td width="20%"><div align="right">编制日期：</div></td>
        <td width="80%"><input name="DrawUpDate" type="Wdate" class="input1" id="DrawUpDate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=mo.getDrawUpDate()%>" size="30" maxlength="30"></td>     
       </tr>
             <tr> 
	  <td width="20%"><div align="right">批准日期：</div></td>
        <td width="80%"><input name="ApproveDate" type="Wdate" class="input1" id="ApproveDate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=mo.getApproveDate()%>" size="30" maxlength="30"></td>     
       </tr>
             <tr> 
	  <td width="20%"><div align="right">批准人：</div></td>
        <td width="80%"><input name="Approver" type="text" class="input1" id="Approver" onKeyDown="EnterKeyDo('')" value="<%=mo.getApprover()%>" size="30" maxlength="30"></td>     
       </tr>
            <tr> 
	  <td width="20%"><div align="right">生效起始日期：</div></td>
        <td width="80%"><input name="ValidBeginDate" type="Wdate" class="input1" id="ValidBeginDate" onfocus="new WdatePicker({lang:'zh-cn'})"  value="<%=mo.getValidBeginDate()%>" size="30" maxlength="30"></td>     
       </tr>
                   <tr> 
	  <td width="20%"><div align="right">生效结束日期：</div></td>
        <td width="80%"><input name="ValidEndDate" type="Wdate" class="input1" id="ValidEndDate" onfocus="new WdatePicker({lang:'zh-cn'})" value="<%=mo.getValidEndDate()%>" size="30" maxlength="30"></td>     
       </tr>
      <tr>  
	   <td width="20%"><div align="right">备注：</div></td>
        <td width="80%"><input name="Memo" type="text" class="input1" id="Memo" onKeyDown="EnterKeyDo('')" value="<%=mo.getMemo ()%>" size="30" maxlength="3000"></td>     
	     </tr>
	      <tr>
      <td>
         <input type="submit" name="Submit" value="提交" style="display:none">
          <input type="reset" name="reset" value="重置" style="display:none">
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