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
	GoodsStoreEvent gse=new GoodsStoreEvent();
	
	String name=request.getParameter("name");
    
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
    //int page_no=3;
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	
	DataTable dt=gse.getAllGoodsStore(page_no,per_page,"入库");
	//DataTable dt=og.getStdList(page_no,per_page,unitccm);
	DataTable dtcount=gse.getAllGoodsStore("入库");
	//DataTable dtname=og.getOrgName(unitccm);
	//String name=dtname.get(0).get(1).toString();
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	String newcode=AutoCoding.Codingnolevel("com_storeevent","StoreEventNo","IN",6);
	//System.out.println(newcode);
	String username=orgmember.getStaffname();
	Org og=new Org(orgmember.getOrgCode());
	String orgname=og.getName();
%>
<base target="_self">
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script type="text/javascript" src="../../js/public/searchvalue.js"></script>
<script language="javascript">
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
		
		var url = "goodsstoreeventIn.jsp?eventtype=1&username=<%=username%>&orgname=<%=orgname%>&eventID=<%=newcode%>";
		
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
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	validate();

	//showModalDialogWin("goodsinformINList.jsp?StoreEventNo=<%=newcode%>&detail=0",1000,800);
	//showModalDialogWin("goodsIn_new.jsp?StoreEventNo=<%=newcode%>&detail=0",1000,800);
	//window.location.reload();
	window.open("goodsinformINList.jsp?StoreEventNo=<%=newcode%>&detail=0&sid="+rand,"goodsinformINList");
	//window.location.reload();
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

function detail(eventid)
{
	showModalDialogWin("goodsinformINdetail.jsp?StoreEventNo="+eventid,1000,800);
	window.location.reload();
}
function dele(eventid)
{
//alert(orgcode);

var temp=eventid.split(",");
document.getElementById("eventid").value=temp[0];

if(!temp[1])
{
	if (confirm("确定要删除吗？"))
			{
				document.all("Submit").click();
			}
}
else
{
	alert("该事件不能删除");
}
		
			//document.getElementsByName(orgcode).checked="checked";
			//document.form1.docno.value=docno;
			
			
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
        <td class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 物资入库管理</td>
        <td class="main_table_topbg" height="31" align="right"></td>
      </tr>
      <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F3()">物资入库[F3]</a><a href="#" onClick="F5()">刷新[F5]</a></td>
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
          
          <td align="right">
          <%
          String unitccmtemp="&name="+name;
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsIn_manage.jsp",unitccmtemp));
       %>
       <input type="submit" name="Submit" value="提交" style="display:none">
          <input name="act" type="hidden" id="act" value="del">
          <input name="eventid" type="hidden" id="eventid" value="">
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemStoreeventAction">
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
