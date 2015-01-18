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
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String name=request.getParameter("name");
	GoodsStoreInfo goodsStore=new GoodsStoreInfo();
	//Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=goodsStore.getGoodsStoreInfoNumberAndName(page_no,per_page,name);
	DataTable dtcount=goodsStore.getAllGoodsStoreInfo();
	//System.out.println(dtcount.getRowsCount()+"nihaoasdjfhkjasdhfjkh");
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//String res=og.getTrack(goodscode,"");
	String res="";
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	
	//System.out.println(newcode);
	String username=orgmember.getStaffname();
	Org og=new Org(orgmember.getOrgCode());
	String orgname=og.getName();
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script type="text/javascript" src="../../js/public/searchvalue.js"></script>
<script language="javascript">

function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
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
function dele(orgcode)
{
//alert(orgcode);

			//document.getElementsByName(orgcode).checked="checked";
			//document.form1.docno.value=docno;
			if (confirm("确定要删除吗？"))
			{
				document.all("Submit").click();
			}
			
			//window.location.reload();
}
function F8()
{
      
		document.all("Submit").click();
		//this.window.close();
}
function searchName()
{
	var name=document.getElementById("search").value;
	var rand=Math.floor(Math.random()*10000);
	var reload=document.getElementById("reload");
	reload.href="goodsOutStore_list.jsp?sid="+rand+"&StoreEventNo="+"<%=StoreEventNo%>"+"&name="+name;
	reload.click();
	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
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
       
       <input type="submit" name="Submit" value="提交" style="display:none">
       <input type="hidden" id="entity" name="entity" value="COM_OUTSTOREITEM"/>
           <input type="txt" name="StoreEventNo" id="StoreEventNo" value="<%=StoreEventNo %>" style="display:none">
         
         <input name="act" type="hidden" id="act" value="addDul">
         <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformOUTAction">
        </tr>
        <tr><td align="right"><a href="#" onClick="F8()" class="button4">确定出库物资</a></tr>
      </table>
      <%}%>
     
         
      
</td>
  </tr>
</form>
</table>
</BODY>
</HTML>