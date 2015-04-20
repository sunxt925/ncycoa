<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<base target="_self">
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    Code code=new Code();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full();
	
	
	DataTable dt=code.getCodeList(page_no,per_page);
	DataTable dtcount=code.getAllCodeList();
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
function F2()
{
	document.all("act").value="load";
	if (CheckSelect("form1"))
	{
		if (confirm("确定要重新加载选中的表？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要加载的表！");
	}
}




function F3()
{
	var rand=Math.floor(Math.random()*10000);
	var re=window.showModalDialog("code_new.jsp?sid="+rand,window,"scroll=no;status=no;dialogWidth=400px;dialogHeight=500px;center=yes;help=no;");
	if(re=="refresh")
	{window.location.reload();
	}
	//window.open("code_new.jsp?sid="+rand,"_self");
}
//同步流程引擎数据字典
function Synchronous()
{
document.all("act").value="synchronous";
document.all("form1").submit();

}
function  show1(url,url2)
{
    var rand=Math.floor(Math.random()*10000);
    
      window.showModalDialog("code_column.jsp?table_name="+url+"&code_class="+url2+"&sid="+rand,window,"scroll=no;status=no;dialogWidth=1024px;dialogHeight=600px;center=yes;help=no;");
    if(re=="refresh")
	{window.location.reload();
	}
}

function  show2(url)
{
//alert(url);
 var rand=Math.floor(Math.random()*10000);
    var re=window.showModalDialog("code_mod.jsp?table_name="+url+"&sid="+rand,window,"scroll=no;status=no;dialogWidth=400px;dialogHeight=500px;center=yes;help=no;");
  if(re=="refresh")
	{window.location.reload();
	}
	//window.open("code_mod.jsp?table_name="+url);
}

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
				action="../../servlet/PageHandler">
				<tr>
					<td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
					<td width="94%" valign="middle" class="main_table_topbg"
						height="31">
						首页 &gt;&gt; 系统维护 &gt;&gt; 编码管理 &gt;&gt; 编码表管理
					</td>
					<td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
				</tr>
				<tr>
					<td colspan="3" valign="middle" class="table_td_jb">
						&nbsp;&nbsp;
						
						<a href="#" onClick="F2()">加载[F2]</a>
						　<a href="#" onClick="F3()">增加[F3]</a>
						　<a href="#" onClick="F4()">删除[F4]</a>
						　<a href="#" onClick="F5()">刷新[F5]</a>
						
						 <a href="#" onClick="Synchronous()">同步</a>
					</td>
				</tr>
				<tr>
					<td colspan="3" valign="top" class="main_table_centerbg"
						align="center">
						
						<%
							//out.print(dt.getRowsCount());
							if (dt != null && dt.getRowsCount() >= 0)
							{
								TableUtil tu = new TableUtil();
								
								
								tu.setDt(dt);
								tu.setCheckBoxName("选择");
								tu.setDisplayCol("table_name,rn");
								tu.setCheckBoxValue("编码表名");
								tu.setHeadWidth("选择", "10");
								tu.setColumnAlign("编码表名", "left");
								tu.setRowCode("编码类别","@编码类别@"+",code_class");
								//tu.setRowCode("是否加载内存","@是否加载内存@"+",YESNO,");
								tu
										.setRowValue(
												"操作",
												
												"<a href=\"#\" onclick=\"show2('@table_name@')\"  class=\"button4\">修 改</a> <a href=\"#\" onclick=\"show1('@table_name@','@编码类别@')\"  class=\"button4\">明细</a>");
												
									//System.out.println(tu.DrawTable());			
					
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
													"codemanage.jsp", ""));
									%>
								</td>
						</table>
						<%
							}
						%>
						<input name="act" type="hidden" id="act" value="del">
						<input name="action_class" type="hidden" id="action_class"
							value="com.action.system.CodeAction">
					</td>
				</tr>
				<tr>
					<td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
					<td height="5" class="main_table_bottombg"></td>
					<td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
				</tr>
				
			</form>
			
		</table>
	</BODY>

</HTML>
