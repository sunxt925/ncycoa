<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<%@page import="com.business.BuyReportItem"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<base target="_self">
<%

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
function select()
{
	var newtreeurl='goodsclasscheckbox_tree.jsp';
	var str=showModalDialog(newtreeurl);
	if(str==null||str=="")
		return;
	var strs= new Array(); //定义一数组 
	
	strs=str.split(";"); //字符分割 
	/*if(strs==null||strs=="")
		return;*/
	//document.getElementById("COM_BUYGOODSITEM.GOODSNAME").value=strs[0];
	//document.getElementById("COM_BUYGOODSITEM.GOODSCODE").value=strs[1];
	document.getElementById("COM_BUYREPORTITEM.PROJECTCODE").value=strs[1];
	document.getElementById("COM_BUYREPORTITEM.PROJECTNAME").value=strs[0];
	
	
	//document.getElementById("COM_INSTOREITEM.GOODSCODE").value=strs[1];
	//window.open (newtreeurl,"Sample","toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=yes, copyhistory=no,width=350,height=540,left=200,top=300");
}
</script>
<BODY class="mainbody" onLoad="this.focus();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a>　<a href="#" onClick="F3()">重填[F3]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table_list1">
      <tr>
        <td width="20%"><div align="right">项目代码：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","PROJECTCODE")); 
        
        %>
      
        </td>
      </tr>
      <tr>
      <td><div align="right">项目名称：</div></td>
        <td>
        <%out.print(cu.print("COM_BUYREPORTITEM","PROJECTNAME")); %>
        <a href="#" onClick="select()" class="button4">选择</a>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">实施方式：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","DEALMODE")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">审议重点：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","CHECKCONTENTS")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">初审部门：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITORG")); %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">初审意见：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITOPINION"));
         %>
        </td>
      </tr>
      <tr>
        <td width="20%"><div align="right">摘要：</div></td>
        <td width="80%">
        <%out.print(cu.print("COM_BUYREPORTITEM","FIRSTAUDITSUMMARY")); %>
        </td>
      </tr>
      
       <tr>
       <input name="entity" id="entity" type="hidden" value="COM_BUYREPORTITEM"/>
        <input name="act" type="hidden" id="act" value="add">
             <input type="submit" name="Submit" value="提交" style="display:none">
            <input type="reset" name="reset" value="重置" style="display:none">
            <input type="hidden" name="COM_BUYREPORTITEM.SUMMITFLAG" id="COM_BUYREPORTITEM.SUMMITFLAG" value="0"/>
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