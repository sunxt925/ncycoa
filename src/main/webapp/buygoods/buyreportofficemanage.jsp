<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.business.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<base target="_self">
<%
 UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full();
	BuyReportItem buyreItem=new BuyReportItem(); 
	String flag="0";
	DataTable dt=buyreItem.getBuyreportList(page_no,per_page,0);
	DataTable dtcount=buyreItem.getAllBuyreportList();
	int pagecount=dtcount.getRowsCount()/per_page;
	int ppp=dtcount.getRowsCount()%per_page;
	if(pagecount!=0&&ppp!=0)
			pagecount++;
	if(pagecount==0)
		pagecount=1;
		
		
 %>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">

function F5()
{
	window.location.reload();
}
function audit(para,para2)
{
var rand=Math.floor(Math.random()*10000);
	var re=window.showModalDialog("buyreportitemexe.jsp?reportno="+para+"&sid="+rand,window,"scroll=no;status=no;dialogWidth=400px;dialogHeight=500px;center=yes;help=no;");
	if(re=="refresh")
	{window.location.reload();
	}
}
</script>



<BODY class="mainbody" onLoad="this.focus()">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<form name="form1" id="form1" method="post"
				action="../servlet/PageHandler">
				
				<tr>
					<td colspan="3" valign="middle" class="table_td_jb">
						&nbsp;&nbsp;
						<!--  
						<a href="#" onClick="audit()">审核</a>-->
						　<a href="#" onClick="F5()">刷新[F5]</a>
						 
					</td>
				</tr>
				
				<tr>
					<td colspan="3" valign="top" class="main_table_centerbg"
						align="center">
						
						<%
							if (dt != null && dt.getRowsCount() >= 0)
							{
								TableUtil tu = new TableUtil();
								//DBObject db2=new DBObject();
								tu.setDt(dt);
								tu.setCheckBoxName("选择");
								tu.setDisplayCol("reportno,项目代码,呈报标志,rn");
								tu.setCheckBoxValue("reportno");
								
								//tu.setRowCode("品名","@品名@"+",com_goodsclass,goodscode,goodsname");
								//tu.setHeadWidth("选择", "10");
								tu.setRowValue("操作","<a href=\"#\" onclick=\"audit('@reportno@','@项目代码@')\" class=\"button4\">审核</a>");
								//tu.setRowValue("查询语句","<%=db2.getClob(\"query_baseinfo\", \"querysql\" ,\"queryid=\'@queryid@'\")"+"%"+">");
								out.print(tu.DrawTable());
						%>
						<table width="100%" border="0" cellpadding="3" cellspacing="0">
							<tr>
								<td width="50%">
									<!--【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】-->
								</td>
								<td width="50%" align="right">
									<%
									out.print(PageUtil.DividePage(page_no, pagecount,
													"buyreportofficemanage.jsp", ""));
									%>
								</td>
						</table>
						<%
							}
						%>
						<input name="entity" id="entity" type="hidden" value="COM_BUYREPORTITEM"/>
						<input name="act" type="hidden" id="act" value="del">
						<input name="action_class" type="hidden" id="action_class"
							value="com.action.buygoods.BuyReportOfficeAction">
					</td>
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
