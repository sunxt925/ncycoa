<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.dao.system.*"
	errorPage=""%>
	<%
		UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
		String cdccm=Format.NullToBlank(request.getParameter("cdccm"));
		SystemEntity se = new SystemEntity();
		int page_no = Integer.parseInt(Format.NullToZero(request
				.getParameter("page_no")));
		int per_page = u.getPerpage_full();
		
		DataTable dt = se.getEntityList(page_no, per_page);

		DataTable dtcount = se.getAllEntityList();
		
		int pagecount = dtcount.getRowsCount() / per_page;
		int ppp=dtcount.getRowsCount() % per_page;
		if(pagecount!=0&&ppp!=0)
			pagecount++;
		if (pagecount == 0)
			pagecount = 1;
		
			
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
	</HEAD>
	<script language="javascript" src="../js/public/select.js">
</script>
	<script language="javascript" src="../js/public/check.js">
</script>
	<script language="javascript">
function F4() {
	document.all("act").value = "del";
	if (CheckSelect("form1")) {
		if (confirm("ȷ��Ҫɾ��ѡ�еļ�¼��")) {
			document.all("form1").submit();
		}
	} else {
		alert("��û��ѡ��Ҫɾ�������ݣ�");
	}
}

function F5() {
	window.location.reload();
}
function InitEntity() {
	document.all("act").value = "init";
	if (CkEmptyStr(document.all("init_entity_name"), "ʵ����� ����Ϊ�գ�")) {
		if (confirm("ȷ��Ҫ��ʼ�����ű�")) {
			document.all("form1").submit();
		}
	}
}
function modify(para){

   var rand=Math.floor(Math.random()*10000);
	var re=window.showModalDialog("entity_mod.jsp?bm="+para+"&sid="+rand,window,"scroll=no;status=no;dialogWidth=400px;dialogHeight=500px;center=yes;help=no;");
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
					<td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
					<td width="94%" valign="middle" class="main_table_topbg"
						height="31">
						��ҳ &gt;&gt; ϵͳά�� &gt;&gt; ϵͳ���� &gt;&gt; ʵ�����
					</td>
					<td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
				</tr>
				<tr>
					<td colspan="3" valign="middle" class="table_td_jb">&nbsp;<%=ButtonUtil.AddButton(u,cdccm,"delete","F4")%>&nbsp;&nbsp;<%=ButtonUtil.AddButton(u,cdccm,"refresh","F5")%></td>
				</tr>
				<tr>
					<td colspan="3" valign="top" class="main_table_centerbg"
						align="center">
						ʵ�������
						<input name="init_entity_name" type="text" id="init_entity_name"
							size="50" maxlength="200" class="input1">
						<input type="button" name="button" id="button" value="��ʼ��"
							class="button1" onClick="InitEntity()">
						<%
							//out.print(dt.getRowsCount());
							if (dt != null && dt.getRowsCount() >= 0)
							{
								TableUtil tu = new TableUtil();
								tu.setDt(dt);
								tu.setCheckBoxName("ѡ��");
								tu.setCheckBoxValue("ʵ�����");
								tu.setHeadWidth("ѡ��", "10");
								//tu.setHeadAlign("ʵ�����","left");
								//tu.setLineAction("onclick=\"alert ('@ʵ�����@====@ʵ������@')\"");
								tu.setRowColor("ʵ�����", "FF0000");
								tu.setColumnAlign("ʵ�����", "left");
								tu.setRowLink("ʵ�����", "aa.jsp?a=@ʵ�����@====@ʵ������@");
								tu
										.setRowValue(
												"����",
												"<a href=\"#\" onclick=\"modify('@entity_code@')\" class=\"button4\">�� ��</a> <a href=\"entity_column.jsp?bm=@entity_code@\" class=\"button4\">�� ϸ</a>");
								tu.setDisplayCol("rn");
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
													"entitymanage.jsp", ""));
									%>
								</td>
						</table>
						<%
							}
						%>
						<input name="act" type="hidden" id="act" value="del">
						<input name="action_class" type="hidden" id="action_class"
							value="com.action.system.SystemEntityAction">
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