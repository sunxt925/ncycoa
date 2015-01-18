<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
String StoreEventNo=request.getParameter("StoreEventNo");
Goodsoutstoreitem goutstoreitem=new Goodsoutstoreitem();
	String detail=request.getParameter("detail");
	DataTable dt;
    
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));

	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	if(detail.equals("1"))
		 dt=goutstoreitem.getGoodsStoreItemListdetail(page_no,per_page,StoreEventNo);
	else
		 dt=goutstoreitem.getGoodsStoreItemList(page_no,per_page,StoreEventNo);
	//DataTable dt=og.getStdList(page_no,per_page,unitccm);
	DataTable dtcount=goutstoreitem.getAllGoodsStoreItemList(StoreEventNo);
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
<base target="_self">
<a id="reload" href="goodsinformOUTList.jsp?StoreEventNo=<%=StoreEventNo%>" style="display:none"></a>
<script language="javascript">
 
function F3(message)//修改
{
	var temp=message.split(",");
	var outno=temp[0];
	
	var goodsnumber=temp[1];
	//alert(goodsnumber);
	if(goodsnumber=="0")
	{
		alert("该物品库存不足，不能出库");
		return;
	}
	var rand=Math.floor(Math.random()*10000);
	
	showModalDialogWin("goodsinformOUT_mod.jsp?outno="+outno,700,600);
	//reload.click();
	window.open("goodsinformOUTList.jsp?detail=0&StoreEventNo=<%=StoreEventNo%>&sid="+rand,"_self");
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
function dele(outno)
{
//alert(orgcode);
document.getElementById("outno").value=outno;

document.getElementById("COM_OUTSTOREITEM.STOREEVENTNO").value="<%=StoreEventNo%>";

			//document.getElementsByName(orgcode).checked="checked";
			//document.form1.docno.value=docno;
			if (confirm("确定要删除吗？"))
			{
				document.all("Submit").click();
			}
			
			//window.location.reload();
}
//function select()
//{
//	var rand=Math.floor(Math.random()*10000);
//	var newtreeurl='goodsclasscheckboxOut_tree.jsp?StoreEventNo=<%=StoreEventNo%>';
//	showModalDialogWin(newtreeurl,700,600);
	
//	window.location.reload();
//	window.open("goodsOut_manage.jsp?sid="+rand,"goodsOut_manage");
//}
function select()
{
	var rand=Math.floor(Math.random()*10000);
	var newtreeurl='goodsOutStore_list.jsp?StoreEventNo=<%=StoreEventNo%>';
	showModalDialogWin(newtreeurl,1200,600);
	
	window.location.reload();
	window.open("goodsOut_manage.jsp?sid="+rand,"goodsOut_manage");
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="main_table_topbg" height="31"> 物资出库清单列表，本次出库事件流水号：<%=StoreEventNo %></td>
       
        <td class="main_table_topbg" height="31" align="right"><a href="#" onClick="select()" class="button4">选择出库物资</a></td>
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
          
          <td align="right">
          <%
          String unitccmtemp="&name="+StoreEventNo;
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsinformOUTList.jsp",unitccmtemp));
       %>
       <input type="submit" name="Submit" value="提交" style="display:none">
          <input name="act" type="hidden" id="act" value="del">
          <input name="outno" type="hidden" id="outno" value="">
          <input name="COM_OUTSTOREITEM.STOREEVENTNO" type="hidden" id="COM_OUTSTOREITEM.STOREEVENTNO" value="">
          
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformOUTAction">
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
