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

<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	Goods goods=new Goods();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=goods.getnextGoodsList(page_no,per_page,goodscode);
	DataTable dtcount=goods.getAllnextgoodsList(goodscode);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	String res="";
%>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
 <tr>
 <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">当前单位：<%=res %></td>
      </tr>
      <tr>
      <td><a href="#" onClick="F3()" >增加本层物资</a></td>
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
          String unitccmtemp="&goodscode="+goodscode;
      	out.print(PageUtil.DividePage(page_no,pagecount,"goods_list.jsp",unitccmtemp));
       %>
       </td>
       <input type="submit" name="Submit" value="提交" style="display:none">
           <input name="act" type="hidden" id="act" value="del">
          <input type="hidden" id="entity" name="entity" value="COM_GOODSCLASS"/>
          <input name="goodscode" type="hidden" id="goodscode" value="">
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsAction">
        </tr>
        
      </table>
      <%}%>
      
</td>
  </tr>
</form>
</table>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	var url="goodsmanage/goodsnew.jsp?sid="+rand+"&goodscode="+"<%=goodscode%>";
	createwindow('新增', url, 490, 500);
	
	
}
function F1(goodscode)
{	
	var url="goodsmanage/goodsclass_mod.jsp?goodscode="+goodscode;
	createwindow('修改', url, 490, 500);
}
function dele(orgcode)
{
         document.getElementById("goodscode").value=orgcode; 
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
    	
    }
}
</script>
</BODY>
</HTML>