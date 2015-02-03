<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String StoreEventNo=Format.NullToBlank(request.getParameter("StoreEventNo"));
%>
<HTML>

<a id="reload" href="#" style="display:none"></a>
<base target="_self">
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script type="text/javascript" src="<%=path%>/js/public/searchvalue.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String name=request.getParameter("name");
	GoodsStoreInfo goodsStore=new GoodsStoreInfo();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=goodsStore.getGoodsStoreInfoNumberAndName(page_no,per_page,name);
	DataTable dtcount=goodsStore.getAllGoodsStoreInfo();
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	String res="";
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	
	String username=orgmember.getStaffname();
	Org og=new Org(orgmember.getOrgCode());
	String orgname=og.getName();
%>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
 <tr>
 <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">当前各种物资库存情况：<%=res %></td>
          <td class="table_td_jb" height="31" align="right"><input name="search" type="text" class="input1" id="search"  onfocus="cls()" onblur="res()"  value="请输入关键字" size="30" maxlength="30"><a href="#" onClick="searchName()" class="button4">查询</a></td>
       </td>
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
          -->
          <td align="right">
          <%
          String unitccmtemp="&StoreEventNo="+StoreEventNo;
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsOutStore_list.jsp",unitccmtemp));
       %>
       </td>
       <input id="btn_ok" type="hidden" onclick="ret()">
       <input type="submit" name="Submit" value="提交" style="display:none">
       <input type="hidden" id="entity" name="entity" value="COM_OUTSTOREITEM"/>
           <input type="txt" name="StoreEventNo" id="StoreEventNo" value="<%=StoreEventNo %>" style="display:none">
         
         <input name="act" type="hidden" id="act" value="addDul">
         <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformOUTAction">
        </tr>
       
      </table>
      <%}%>
      
</td>
  </tr>
</form>
</table>
<script language="javascript">
function ret(){
	var api = frameElement.api;
		document.all("Submit").click();
		(api.data)({code:"refresh"});
}
function searchName()
{
	var name=document.getElementById("search").value;
	var rand=Math.floor(Math.random()*10000);
	var storeeventno="<%=StoreEventNo%>";
	var u="goodsOutStore_list.jsp?sid="+rand+"&StoreEventNo="+storeeventno+"&name="+name;
	window.open(u,'_self');
	
	
}
</script>
</BODY>
</HTML>