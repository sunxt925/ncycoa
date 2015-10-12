<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>

<%
	Position position=new Position();
	String name=request.getParameter("name");
	String doccode=request.getParameter("doccode");
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
    //int page_no=3;
	int per_page=250;
	
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

<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script type="text/javascript" src="<%=path%>/js/public/searchvalue.js"></script>



 <script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="<%=path%>/jscomponent/tools/outwindow.js"></script>
<script language="javascript">
function F8()
{
	if (CheckSelect("form1"))
	{

			document.all("form1").submit();
	}
	else
	{
		alert ("你没有选中要添加的岗位！");
	}
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
	
	window.open("allposition.jsp?name="+name+"&doccode="+'<%=doccode%>',"_self");
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="<%=path%>/servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="main_table_topbg" height="31">首页 &gt;&gt; 标准初始化 &gt;&gt; 添加关联岗位</td>
        <td class="main_table_topbg" height="31" align="right"><input name="search" type="text" class="easyui-textbox" id="search"  onfocus="cls()" onblur="res()"  value="请输入关键字" size="30" maxlength="30"><a href="#" onClick="searchName()" class="easyui-linkbutton">查询</a></td>
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
<input name="all" type="hidden" id="all" value="allpos">
          <input name="act" type="hidden" id="act" value="add"><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdAboutPostAction">
       </td>
          
        </tr>
        
      </table>
      <%}%>
     
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="<%=path%>/images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="<%=path%>/images/table_rb.jpg" width="10" height="5"></td>
  </tr>
        <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;
     <a id="F8" style="display:none" href="#" onClick="F8()">保存[F8]</a><input name="doccode" type="hidden" id="doccode" value="<%=doccode%>">
    </td>
  </tr>
  </td>
  </tr>
</form>
</table>

</BODY>
</HTML>
