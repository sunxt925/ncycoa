<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String goodscode=Format.NullToBlank(request.getParameter("goodscode"));

if (goodscode.equals("")) goodscode="WF";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String name=request.getParameter("name");
	GoodsStoreInfo goodsStore=new GoodsStoreInfo();
	//Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	per_page=5;
	DataTable dt=goodsStore.getNextGoodsStoreInfo(page_no,per_page,goodscode);
	DataTable dtcount=goodsStore.getAllNextGoodsStoreInfo(goodscode);
	//System.out.println(dtcount.getRowsCount()+"nihaoasdjfhkjasdhfjkh");
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//String res=og.getTrack(goodscode,"");
	String res="";
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	String newcode=AutoCoding.Codingnolevel("com_storeevent","StoreEventNo","OUT",6);
	//System.out.println(newcode);
	String username=orgmember.getStaffname();
	Org og=new Org(orgmember.getOrgCode());
	String orgname=og.getName();
%>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
 <tr>
 <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">当前各种物资库存情况：<%=res %></td>
      </tr>
      
    </table>
    <%
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
          String unitccmtemp="&goodscode="+goodscode;
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsStore_list.jsp",unitccmtemp));
       %>
       </td>
        </tr>
        
      </table>
      <%}%>
</td>
  </tr>
</table>
</BODY>
</HTML>