<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bm=Format.NullToBlank(request.getParameter("unitccm"));
if (bm.equals("")) bm="NC";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<link rel="stylesheet" type="text/css" href="../../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="../../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../../jscomponent/easyui/jquery.easyui.min.js"></script>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	Org og=new Org();
	OrgPosition orgposition=new OrgPosition();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half()-3;
	DataTable dt=orgposition.getOrgPositionList(page_no,per_page,bm);
	DataTable dtcount=orgposition.getAllOrgPositionList(bm);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//String trackName=og.getTrack(bm,"");
	String trackName="";
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
	var url="xtwh/orgposition/unitposition_new.jsp?sid="+rand+"&bm="+"<%=bm%>";
	createwindow('增加本层机构岗位',url,'1000px','720px');
	//showModalDialogWin("unitposition_new.jsp?sid="+rand+"&bm="+"<%=bm%>",1000,720);
	//window.location.reload();
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
}
function F1(orgcode,positioncode)
{
	
	var rand=Math.floor(Math.random()*10000);
	var url="xtwh/orgposition/unitposition_mod.jsp?sid="+rand+"&bm="+orgcode+"&positioncode="+positioncode+"";
	createwindow('修改本层机构岗位',url,'600px','520px');
	//showModalDialogWin("unitposition_mod.jsp?sid="+rand+"&bm="+orgcode+"&positioncode="+positioncode+"",600,520);
	//window.location.reload();
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("父级菜单的删除将级联删除子菜单，是否继续？"))
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
function dele(orgposition)
{
//alert(orgcode);
document.getElementById("org").value=orgposition
			//document.getElementsByName(orgcode).checked="checked";
			//document.form1.docno.value=docno;
			if (confirm("确定要删除吗？"))
			{
				document.all("form1").submit();
			}
			
			//window.location.reload();
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="table_td_jb">当前单位：<%=trackName %>岗位信息</td>
      </tr>
      <tr>
      <td><a href="#" onClick="F3()" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">增加本层机构岗位</a></td>
      </tr>
    </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
	   out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <!--<td width="50%">【<a href="#" onClick="F4()">删除</a>】【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          --><td align="right">
          <%
         String unitccmtemp="&unitccm="+bm;
      	out.print(PageUtil.DividePage(page_no,pagecount,"unit_positionlist.jsp",unitccmtemp));
       %>
       </td>
          <input name="act" type="hidden" id="act" value="del">
          <input name="orgcode" type="hidden" id="orgcode" value="<%=bm%>">
          <input name="org" type="hidden" id="org" value="">
        <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitPositionAction">
        </tr>
        
      </table>
      <%}%>
    
        
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </td>
  </tr>
</form>
</table>
</BODY>
</HTML>