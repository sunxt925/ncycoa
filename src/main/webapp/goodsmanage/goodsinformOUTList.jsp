<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>

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
Goodsoutstoreitem goutstoreitem=new Goodsoutstoreitem();
	String detail=request.getParameter("detail");
	DataTable dt;
    
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));

	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half()-4;
	if(detail.equals("1"))
		 dt=goutstoreitem.getGoodsStoreItemListdetail(page_no,per_page,StoreEventNo);
	else
		 dt=goutstoreitem.getGoodsStoreItemList(page_no,per_page,StoreEventNo);
	DataTable dtcount=goutstoreitem.getAllGoodsStoreItemList(StoreEventNo);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	
    
%>
<base target="_self">
<a id="reload" href="goodsinformOUTList.jsp?StoreEventNo=<%=StoreEventNo%>" style="display:none"></a>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="main_table_topbg" height="31"> ���ʳ����嵥�б����γ����¼���ˮ�ţ�<%=StoreEventNo %></td>
       
        <td class="main_table_topbg" height="31" align="right"><a href="#" onClick="select()" class="button4">ѡ���������</a></td>
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
       <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input name="act" type="hidden" id="act" value="del">
          <input name="outno" type="hidden" id="outno" value="">
          <input name="COM_OUTSTOREITEM.STOREEVENTNO" type="hidden" id="COM_OUTSTOREITEM.STOREEVENTNO" value="">
          
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformOUTAction">
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
 
function F3(message)//�޸�
{
	var temp=message.split(",");
	var outno=temp[0];
	
	var goodsnumber=temp[1];
	if(goodsnumber=="0")
	{
		alert("����Ʒ��治�㣬���ܳ���");
		return;
	}
	var rand=Math.floor(Math.random()*10000);
	createwindow('�޸�', "goodsmanage/goodsinformOUT_mod.jsp?outno="+outno,700,550);
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("ɾ����λ���Ƿ������"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("��û��ѡ��Ҫɾ�������ݣ�");
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
document.getElementById("outno").value=outno;

document.getElementById("COM_OUTSTOREITEM.STOREEVENTNO").value="<%=StoreEventNo%>";

			if (confirm("ȷ��Ҫɾ����"))
			{
				document.all("Submit").click();
			}
}
function select()
{
	var rand=Math.floor(Math.random()*10000);
	var newtreeurl='goodsmanage/goodsOutStore_list.jsp?StoreEventNo=<%=StoreEventNo%>';
	createwindow('ѡ��', newtreeurl,1000,550);
	
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
		cancelVal : '�ر�',
		cancel : true/* Ϊtrue�ȼ���function(){} */
	});

}
function returnValue(data){
var f=data.code;
if(f=="refresh"){
	window.setTimeout(function(){
		window.location.reload();
	},1000);
	window.open("goodsOut_manage.jsp?sid="+rand,"goodsOut_manage");
}else{
	window.setTimeout(function(){
		window.location.reload();
	},1000);
}
}
</script>
</BODY>
</HTML>
