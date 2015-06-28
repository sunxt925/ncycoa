<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<TITLE>四川省南充烟草公司</TITLE>
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script type="text/javascript" src="<%=path%>/js/public/searchvalue.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
String StoreEventNo=request.getParameter("StoreEventNo");
String detail=request.getParameter("detail");
Goodsinstoreitem ginstoreitem=new Goodsinstoreitem();
	DataTable dt;
    
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));

	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half()-4;
	if(detail.equals("1"))
		 dt=ginstoreitem.getGoodsStoreItemListdetail(page_no,per_page,StoreEventNo);
	else
		 dt=ginstoreitem.getGoodsStoreItemList(page_no,per_page,StoreEventNo);
	DataTable dtcount=ginstoreitem.getAllGoodsStoreItemList(StoreEventNo);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
    
%>
<base target="_self">
<a id="reload" href="" style="display:none"></a>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
      <tr>
        <td class="main_table_topbg" height="31"> 物资入库清单列表，本次入库事件流水号为：<%=StoreEventNo %></td>
        <td align="right" class="main_table_topbg" height="31"> <a href="#" onClick="select()" class="button4">选择录入物资</a></td>
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
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsinformINList.jspdetail=0&StoreEventNo="+StoreEventNo+"",unitccmtemp));
       %>
       <input type="submit" name="Submit" value="提交" style="display:none">
          <input name="act" type="hidden" id="act" value="del">
          <input name="inno" type="hidden" id="inno" value="">
          <input name="COM_INSTOREITEM.STOREEVENTNO" type="hidden" id="COM_INSTOREITEM.STOREEVENTNO" value="">
          
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformINAction">
       </td>
          
        </tr>
        
      </table>
      <%}%>
     
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </td>
  </tr>
</form>
</table>
<script language="javascript">
 
function F3(inno)//修改
{
	var rand=Math.floor(Math.random()*10000);
	 createwindow('修改', "goodsmanage/goodsinformIN_mod.jsp?inno="+inno,700,550);
	//window.open("goodsinformINList.jsp?detail=0&StoreEventNo=<%=StoreEventNo%>&sid="+rand,"_self");
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
function select()
{
	var rand=Math.floor(Math.random()*10000);
	var newtreeurl='goodsmanage/goodsclasscheckbox_tree.jsp?StoreEventNo=<%=StoreEventNo%>';
	createwindow('', newtreeurl, 700,550);
	}

function dele(inno)
{
document.getElementById("inno").value=inno;

document.getElementById("COM_INSTOREITEM.STOREEVENTNO").value="<%=StoreEventNo%>";

			if (confirm("确定要删除吗？"))
			{
				document.all("Submit").click();
			}
}
function createwindow(title, url, width, height) {
	$.dialog({
		id:'LHG1976D',
		data:returnValue,
		content : 'url:' + url,
		lock : true,
		width : width,
		height : height,
		title : title,
		opacity : 0.3,
		cache : false,
		ok : function() {
			$('#btn_ok', this.iframe.contentWindow.document).click();
			return false;
		},
		cancelVal : '关闭',
		cancel : true/* 为true等价于function(){} */
	});

}
function returnValue(data){
var f=data.code;
if(f=="refresh"){
	window.setTimeout(function(){
		window.location.reload();
	},1000);
	window.open("goodsIn_manage.jsp?sid="+rand,"goodsIn_manage");
}else{
	window.setTimeout(function(){
		window.location.reload();
	},1000);
}
}
</script>
</BODY>
</HTML>
