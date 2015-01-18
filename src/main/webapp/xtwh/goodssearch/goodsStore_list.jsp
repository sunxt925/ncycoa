<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//System.out.println("dsfdfd");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String goodscode=Format.NullToBlank(request.getParameter("goodscode"));
String styleIn=Format.NullToBlank(request.getParameter("styleIn"));
String styleOut=Format.NullToBlank(request.getParameter("styleOut"));
String department=Format.NullToBlank(request.getParameter("department"));
String startdate=Format.NullToBlank(request.getParameter("startdate"));
String enddate=Format.NullToBlank(request.getParameter("enddate"));

//System.out.println("styleIn:"+styleIn+"styleOut:"+styleOut+"goodscode:"+goodscode+"department:"+department+"startdate:"+startdate+"enddate:"+enddate);
//if (goodscode.equals("")) goodscode="WF";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	//String name=request.getParameter("name");
	GoodsStoreInfo goodsStore=new GoodsStoreInfo();
	
	//Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=goodsStore.getStoreInfoSearch(page_no,per_page,styleIn,styleOut,goodscode,startdate,enddate,department);
	//DataTable dt=goodsStore.getNextGoodsStoreInfo(page_no,per_page,goodscode);
	DataTable dtcount=goodsStore.getAllNextGoodsStoreInfo(styleIn,styleOut,goodscode,startdate,enddate,department);
	//System.out.println("styleIn:"+styleIn+";styleOut:"+styleOut);
	
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
	//System.out.println(pagecount);
	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	
	showModalDialogWin("goodsnew.jsp?sid="+rand+"&goodscode="+"<%=goodscode%>",490,500);
	
	
	parent.window.location.reload();
	//parent.unittree.location.reload();
	//window.open("unit_new.jsp?sid="+rand+"&unitccm="+"<%=goodscode%>","unittemp");
}
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
var xmlHttp;
	 
	function createXMLHttpRequest() {
		//表示当前浏览器不是ie,如ns,firefox
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function validate() {
		UnSelectAll('form1');
		//创建Ajax核心对象XMLHttpRequest
		createXMLHttpRequest();
		
		var url = "goodsstoreeventOut.jsp?eventtype=0&username=<%=username%>&orgname=<%=orgname%>&eventID=<%=newcode%>";
		
		//设置请求方式为GET，设置请求的URL，设置为异步提交
		xmlHttp.open("GET", url, true);
		
		//将方法地址复制给onreadystatechange属性
		//类似于电话号码
		xmlHttp.onreadystatechange=callback;
		
		//将设置信息发送到Ajax引擎
		xmlHttp.send(null);
	
		
	}
	
	function callback() {
		var str;
		var str1;
		var ch = new Array;
        var cks =document.getElementsByName("items");
		//Ajax引擎状态为成功
		if (xmlHttp.readyState == 4) {
			//HTTP协议状态为成功
			
			if (xmlHttp.status == 200) {
			str=xmlHttp.responseText;
			
			}else {
				alert("请求失败，错误码=" + xmlHttp.status);
			}
		}
	}
function F1(message)//物资出库
{	
//alert(goodscode);
var temp=message.split(",");
var goodscode=temp[0];
var goodsname=temp[1];
var goodsnumber=temp[2];
//alert(goodsnumber);
	if(goodsnumber=="0")
	{
		alert("该物品库存不足，不能出库");
		return;
	}
	validate();//该函数进行入库事件插入
	showModalDialogWin("goodsOut_new.jsp?goodsnumber="+goodsnumber+"&goodsname="+goodsname+"&goodscode="+goodscode+"&StoreEventNo=<%=newcode%>",1000,800);
	window.location.reload();
	//showModalDialogWin("unit_mod.jsp?bm="+orgcode,490,500);
	//parent.window.location.reload();
	//parent.unittree.location.reload();
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
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
 <tr>
 <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">当前各种物资库存情况：<%=res %></td>
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
          String unitccmtemp="&goodscode="+goodscode+"&styleIn="+styleIn+"&styleOut="+styleOut+"&startdate="+startdate+"&enddate="+enddate+"&department="+department;
         
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsStore_list.jsp",unitccmtemp));
       %>
       </td>
       <input type="submit" name="Submit" value="提交" style="display:none">
           <input name="act" type="hidden" id="act" value="del">
          <input type="hidden" id="entity" name="entity" value="COM_GOODSCLASS"/>
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsAction">
        </tr>
        
      </table>
      <%}%>
     
         
      
</td>
  </tr>
</form>
</table>
</BODY>
</HTML>