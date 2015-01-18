<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.business.*"%>
<%@page import="com.dao.system.User"%>
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
    /*  String staffcode=u.getStaffcode();
	String orgcode=User.getOrgCode(staffcode);//登陆者岗位编码
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full();
	BuyReportItem buyreItem=new BuyReportItem(); */
	
//	DataTable dt=buyreItem.getBuyreportList(page_no,per_page,1/*,orgcode*/);
//	DataTable dtcount=buyreItem.getAllBuyreportList(/*,orgcode*/);
	/*int pagecount=dtcount.getRowsCount()/per_page;
	int ppp=dtcount.getRowsCount()%per_page;
	if(pagecount!=0&&ppp!=0)
			pagecount++;
		
	if(pagecount==0)
		pagecount=1;*/
		
 %>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">

function F4()
{
	document.all("act").value="del";
	if (CheckSelect("form1"))
	{
		if (confirm("确定要删除选中的记录？"))
		{
			document.all("form1").submit();
			
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function F5()
{
	window.location.reload();
}

</script>



<BODY class="mainbody" onLoad="this.focus()">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<form name="form1" id="form1" method="post"
				action="../servlet/PageHandler">
				<tr>
					<td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
					<td width="94%" valign="middle" class="main_table_topbg"
						height="31">
						首页 &gt;&gt; 物资采购&gt;&gt; 采购呈报查询
					</td>
					<td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
				</tr>
				 <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>

		<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr valign="top">
            <td valign="top"><iframe src="buyreportquerylist.jsp" name="buyreportquerylist" id="buyreportquerylist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		  <tr valign="bottom">
		    <td valign="bottom"><iframe src="buyreportqueryitemlist.jsp" name="buyreportqueryitemlist" id="buyreportqueryitemlist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		</table>
		</td>
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
