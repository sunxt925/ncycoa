<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.business.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�ϳ��̲�ר����</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<base target="_self">
<%
 UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
   // String reportno=Format.NullToBlank(request.getParameter("reportno"));
   // String projectcode=Format.NullToBlank(request.getParameter("projectcode"));
   String eventno=Format.NullToBlank(request.getParameter("eventno"));
  // System.out.print(eventno);
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full()/2;
	BuyReportItem buyreItem=new BuyReportItem(); 
	BuyGoodsItem buyGoodsItem=new BuyGoodsItem();
	String auditflag="11";
	//DataTable dt=buyGoodsItem.getBuyReportitemlist(reportno,projectcode,page_no,per_page,auditflag);
	//DataTable dtcount=buyGoodsItem.getAllBuyReportitemlist(reportno,projectcode,auditflag);
	DataTable dt=buyreItem.getReportEventItemlist(page_no,per_page,eventno);
	DataTable dtcount=buyreItem.getAllReportEventItemlist(eventno);
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

</script>



<BODY class="mainbody" onLoad="this.focus()">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<form name="form2" id="form2" method="post"
				action="../servlet/PageHandler">
				
				<tr>
					<td colspan="3" valign="middle" class="table_td_jb">
						&nbsp;&nbsp;
						
						��<a href="#" onClick="F5()">ˢ��[F5]</a>
						
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
								tu.setCheckBoxName("ѡ��");
							//	tu.setDisplayCol("buyno,rn");
							//	tu.setCheckBoxValue("buyno");
								tu.setDisplayCol("reportno,rn");
								tu.setCheckBoxValue("reportno");
								tu.setRowValue("����","<a href=\"buyreportqueryitem_collist.jsp?reportno=@reportno@&projectcode=@��Ŀ����@\"  class=\"button4\">��ϸ</a>");
								//tu.setRowCode("Ʒ��","@Ʒ��@"+",com_goodsclass,goodscode,goodsname");
										out.print(tu.DrawTable());
						%>
						<table width="100%" border="0" cellpadding="3" cellspacing="0">
							<tr>
								<td width="50%">
									<!--��<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��-->
								</td>
								<td width="50%" align="right">
									<%
									out.print(PageUtil.DividePage(page_no, pagecount,
													"buyreportqueryitemlist.jsp", ""));
									%>
								</td>
						</table>
						<%
							}
						%>
						<input name="eventno" id="eventno" type="hidden" value="<%=eventno %>"/>
						<input name="entity" id="entity" type="hidden" value="COM_BUYGOODSITEM"/>
					 
						<input name="act" type="hidden" id="act" value="del">
						<input name="action_class" type="hidden" id="action_class"
							value="com.action.buygoods.BuyReportItemAction">
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
