<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<%@page import="com.business.BuyReportItem"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%
String reportno=Format.NullToBlank(request.getParameter("reportno"));
BuyReportItem buyReportItem=new BuyReportItem(reportno);
String reportflag=buyReportItem.getSummitflag();
   ComponentUtil cu=new ComponentUtil();
   CodeDictionary cd=new CodeDictionary();
 %>
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

	 if(sumbit_check())
	 {
	
	 document.all("Submit").click();
     
		window.returnValue="refresh";
	 }
	
	
}
</script>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">����[F8]</a>��<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <%if(reportflag.equals("0")){ %>
      <tr>
        <td width="20%"><div align="right">��Ŀ���룺</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","PROJECTCODE",buyReportItem.getProcjetCode())); 
        
        %>
      
        </td>
      </tr>
      <tr>
      <td><div align="right">��Ŀ���ƣ�</div></td>
        <td>
        <%out.print(cu.print("COM_BUYREPORTITEM","PROJECTNAME",buyReportItem.getProjectName())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">ʵʩ��ʽ��</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","DEALMODE",buyReportItem.getDealMode())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">�����ص㣺</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","CHECKCONTENTS",buyReportItem.getCheckContents())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">�����ţ�</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITORG",buyReportItem.getFirstAuditOrg())); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">���������</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITOPINION",buyReportItem.getFirstAuditOpinion()));
         %>
        </td>
      </tr>
       <tr>
        <td width="20%"><div align="right">��������</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITFLAG",buyReportItem.getFirstAuditFlag()));
         %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">ժҪ��</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITSUMMARY",buyReportItem.getFirstAuditSummary())); %>
        </td>
      </tr>
      <%}else{ %>
      ��Ŀ�Ѿ��ʱ����޷��޸�
      <%} %>
       <tr>
       <input name="entity" id="entity" type="hidden" value="COM_BUYREPORTITEM"/>
        <input name="act" type="hidden" id="act" value="modify">
        <input name="COM_BUYREPORTITEM.REPORTNO" id="COM_BUYREPORTITEM.REPORTNO" type="hidden" value="<%=reportno%>"/>
                 <input name="old_REPORTNO" id="old_REPORTNO" type="hidden" value="<%=reportno%>"/>
         
         <input type="submit" name="Submit" value="�ύ" style="display:none">
            <input type="reset" name="reset" value="����" style="display:none">
            <input name="action_class" type="hidden" id="action_class" value="com.action.buygoods.BuyReportItemAction"></td>
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
<%
out.print(cu.getCheckstr());
%>