<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*,com.db.*,com.common.*,com.entity.system.*"
	errorPage=""%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	//String path = request.getContextPath();
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String orgcode = Format.NullToBlank(request.getParameter("bm"));
	String positioncode = Format.NullToBlank(request
			.getParameter("positioncode"));
	String positionname = Format.NullToBlank(request
			.getParameter("positionname"));
	if (orgcode.equals(""))
		orgcode = "NC";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	OrgPositionRelation opr = new OrgPositionRelation();
	DataTable dt = opr
			.getOrgPositionRelationList(orgcode, positioncode);
	OrgPosition orgposition = new OrgPosition();
	Org og = new Org();

	//String trackName=og.getTrack(orgcode,"");
	String trackName = "";

	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="../../js/public/select.js"></script>


 <script type="text/javascript" src="../../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="../../jscomponent/tools/outwindow.js"></script>


<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("relationposition_new.jsp?level=heigher&positioncode=<%=positioncode%>&sid="+rand+"&orgcode=<%=orgcode%>","_self");
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
}
function Higher()
{
	
	var rand=Math.floor(Math.random()*10000);
	var url="xtwh/orgposition/RelationUnit_positionmanage.jsp?level=higher&positioncode=<%=positioncode%>&sid="+rand+"&orgcode=<%=orgcode%>";
	createwindowIframe('新增当前岗位的下级岗位',url,'1200px','700px',"Relationunitpositionlist");
	//alert("RelationUnit_positionmanage.jsp?level=higher&positioncode=<%=positioncode%>&orgcode=<%=orgcode%>&sid="+rand+"");
//	showModalDialogWin("RelationUnit_positionmanage.jsp?level=higher&positioncode=<%=positioncode%>&sid="+rand+"&orgcode=<%=orgcode%>",1200,700);
	//showModalDialogWin("RelationUnit_positionmanage.jsp?level=higher&positioncode="+<%=positioncode%>+"&sid="+rand+"&orgcode="+<%=orgcode%>+"",1200,700);
//	window.location.reload();
}
function Lower()
{
	var rand=Math.floor(Math.random()*10000);
	var url = "xtwh/orgposition/RelationUnit_positionmanage.jsp?level=lower" + 
			"&positioncode=<%=positioncode%>&sid=" + rand +
			"&orgcode=<%=orgcode%>";
	createwindowIframe('新增当前岗位的下级岗位',url,'1200px','700px',"Relationunitpositionlist");
//	showModalDialogWin(url, 1200, 700);
//		window.location.reload();
	}
	function F4() {
		if (CheckSelect("form1")) {
			if (confirm("父级菜单的删除将级联删除子菜单，是否继续？")) {
				document.all("form1").submit();
			}
		} else {
			alert("你没有选中要删除的内容！");
		}
	}
	function F5() {
		window.location.reload();
	}
	function dele(orgcode) {
		//alert(orgcode);
		document.getElementById("org").value = orgcode
		//document.getElementsByName(orgcode).checked="checked";
		//document.form1.docno.value=docno;
		if (confirm("确定要删除吗？")) {
			document.all("form1").submit();
		}

		//window.location.reload();
	}
</script>
<BODY class="mainbody" onLoad="this.focus()">
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<form name="form1" id="form1" method="post"
			action="../../servlet/PageHandler">
			<tr>
				<td colspan="3" valign="top" class="main_table_centerbg"
					align="left"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td class="table_td_jb">当前操作：<%=trackName%><%=positionname%>&gt;&gt;关联岗位信息
							</td>
						</tr>
						<tr>
							<td><a href="#" onclick="return Higher();" class="">增加当前岗位的上级岗位</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
								href="#" onClick="Lower()" class="">增加当前岗位的下级岗位</a></td>
						</tr>
					</table>
					<div style="overflow-x: auto; overflow-y: auto; height: 320px;">
						<%
							//out.print(dt.getRowsCount());
							if (dt != null && dt.getRowsCount() > 0) {

								TableUtil tableutil = new TableUtil();
								tableutil.setDt(dt);
								out.print(tableutil.DrawTable());
						%>
					</div>
					<table width="100%" border="0" cellpadding="3" cellspacing="0">
						<tr>
							<!--<td width="50%">【<a href="#" onClick="F4()">删除</a>】【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          -->
							<td align="right"></td>
							<input name="act" type="hidden" id="act" value="del">

							<input type="txt" name="positioncode" id="positioncode"
								value="<%=positioncode%>" style="display:none">
							<input name="orgcode" type="hidden" id="orgcode"
								value="<%=orgcode%>">
							<input type="txt" name="positionname" id="positionname"
								value="<%=positionname%>" style="display:none">
							<input name="org" type="hidden" id="org" value="">

							<input name="action_class" type="hidden" id="action_class"
								value="com.action.system.SystemUnitPositionRelationAction" />

						</tr>

					</table> <%
 	}
 %>
			<tr>
				<td width="3%" height="5" class="main_table_bottombg"><img
					src="../../images/table_lb.jpg" width="10" height="5"></td>
				<td width="94%" height="5" class="main_table_bottombg"></td>
				<td width="3%" height="5" align="right" class="main_table_bottombg"><img
					src="../../images/table_rb.jpg" width="10" height="5"></td>
			</tr>
			</td>
			</tr>
		</form>
	</table>
</BODY>
</HTML>