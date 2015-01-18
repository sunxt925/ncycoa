<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String table_name=Format.NullToBlank(request.getParameter("table_name"));
	
	Code code=new Code();
	//DataTable dt=.getEntityColumn(table_name);
	UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
	
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=u.getPerpage_full();
	DataTable dt=code.getEntityCodeList(table_name,page_no,per_page);
	DataTable dtcount=code.getAllEntityCodeList(table_name);
	
	int pagecount=dtcount.getRowsCount()/per_page;
	int ppp=dtcount.getRowsCount()%per_page;
	if(pagecount!=0&&ppp!=0)
			pagecount++;
	if(pagecount==0)
		pagecount=1;
	
	
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">
function F5()
{
	window.location.reload();
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
  <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
   <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableUtil=new TableUtil();
		tableUtil.setDt(dt);
		out.print(tableUtil.DrawTable());
		//out.print(PageUtil.DrawTable(dt,false));
		
	%>
	
	  <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>

         <td width="50%" align="center"> 
      <%
      	out.print(PageUtil.DividePage(page_no,pagecount,"code_list2.jsp","table_name="+table_name));
       %></td>
      </table><%}else{%>
       <td width="50%" align="center">实体表未初始化或不存在</td>
      <%} %>
   
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
