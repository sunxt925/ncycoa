<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.std.*" errorPage="" %>
<%@page import="com.entity.std.DocMetaVersionInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<HTML>
<HEAD>
<base target="_self">
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%

	String orgcode=request.getParameter("orgcode");
	String doccode=request.getParameter("doccode");
	OrgPosition orgpost = new OrgPosition();
	int page_no = Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=orgpost.stdGetOrgPositionList(page_no,per_page,orgcode);
	DataTable dtcount=orgpost.getAllOrgPositionList(orgcode);
			int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);

	
}
function F8()
{
	if (CheckSelect("form1"))
	{
			document.all("form1").submit();
	}
	else
	{
		alert ("你没有选中要添加的内容！");
	}
}
function F5()
{
	window.location.reload();
}

</script>
<BODY class="mainbody" onLoad="this.focus()" scroll="no">

<!--<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">-->
<!--<tr>-->
<!--<td class="table_td_jb_iframe">&nbsp;&nbsp; -->
<!--   <a href="#" onClick="F4()" >保存</a>-->
<!--</td>-->
    
<!--</tr>-->
<!--</table>-->


<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
				TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        <!--  
          <td width="50%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          -->
          <td align="left">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          <td align="right">
          <%String para="orgcode="+orgcode+"&doccode="+doccode;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_orgpost.jsp",para));
       %>
       </td>
          
        </tr>
               <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="add">
        <input name="orgcode" type="hidden" id="docno" value="<%=orgcode%>">
        <input name="doccode" type="hidden" id="docno" value="<%=doccode%>">
        <input name="all" type="hidden" id="all" value="no"></div></td>
       <td> <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdAboutPostAction"></td>
      </tr>
      </table>
      <%}%>
	</td>
  </tr>
  </table>
</form>

</BODY>
</HTML>
