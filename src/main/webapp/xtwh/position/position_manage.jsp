<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
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
	Position position=new Position();
	String name=request.getParameter("name");
    
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
    //int page_no=3;
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	
	DataTable dt=position.getAllPositionListDrawTable(page_no,per_page,name);
	//DataTable dt=og.getStdList(page_no,per_page,unitccm);
	DataTable dtcount=position.getAllPositionList(name);
	//DataTable dtname=og.getOrgName(unitccm);
	//String name=dtname.get(0).get(1).toString();
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
    
%>

<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script type="text/javascript" src="../../js/public/searchvalue.js"></script>



 <script type="text/javascript" src="../../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../../jscomponent/tools/outwindow.js"></script>
<script language="javascript">
function F3()
{
	var url="xtwh/position/position_new.jsp";
	createwindow('新增岗位',url,'500px','340px');
//	var rand=Math.floor(Math.random()*10000);
//	showModalDialogWin("position_new.jsp");
//	window.location.reload();
	//window.open("position_new.jsp?sid="+rand,"_self");
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("删除岗位，是否继续？"))
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
function searchName()
{
	var name=document.getElementById("search").value;
	var rand=Math.floor(Math.random()*10000);
	
	window.open("position_manage.jsp?sid="+rand+"&name="+name,"_self");
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 岗位管理</td>
        <td class="main_table_topbg" height="31" align="right"><input name="search" type="text" class="easyui-textbox" id="search"  onfocus="cls()" onblur="res()"  value="请输入关键字" size="30" maxlength="30"><a href="#" onClick="searchName()" class="easyui-linkbutton">查询</a></td>
      </tr>
      <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;
    <a href="#" onClick="F3()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">增加[F3]</a>
    <a href="#" onClick="F4()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除[F4]</a>
    <a href="#" onClick="F5()" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新[F5]</a>
    </td>
  </tr>
    </table>
    <!--<div style="overflow-x: auto; overflow-y: auto; height: 600px;">
    --><%
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
	   out.print(tableutil.DrawTable());
	%>
      <!--</div>
      --><table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          <td align="right">
          <%
          String unitccmtemp="&name="+name;
      	out.print(PageUtil.DividePage(page_no,pagecount,"position_manage.jsp",unitccmtemp));
       %>
          <input name="act" type="hidden" id="act" value="del"><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemPositionAction">
       </td>
          
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
