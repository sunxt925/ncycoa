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
	GoodsStoreEvent gse=new GoodsStoreEvent();
	String name=request.getParameter("name");
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half()-4;
	DataTable dt=gse.getAllGoodsStore(page_no,per_page,"���");
	DataTable dtcount=gse.getAllGoodsStore("���");
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	String newcode=AutoCoding.Codingnolevel("com_storeevent","StoreEventNo","IN",6);
	String username=orgmember.getStaffname();
	Org og=new Org(orgmember.getOrgCode());
	String orgname=og.getName();
%>
<base target="_self">

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
     
      <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F3()">�������[F3]</a><a href="#" onClick="F5()">ˢ��[F5]</a></td>
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
       <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input name="act" type="hidden" id="act" value="del">
          <input name="eventid" type="hidden" id="eventid" value="">
          <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemStoreeventAction">
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
 var xmlHttp;
	 
	function createXMLHttpRequest() {
		//��ʾ��ǰ���������ie,��ns,firefox
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	var newcode="<%=AutoCoding.Codingnolevel("com_storeevent","StoreEventNo","IN",6)%>";
	function validate() {
		UnSelectAll('form1');
		//����Ajax���Ķ���XMLHttpRequest
		createXMLHttpRequest();
		
		var url = "goodsstoreeventIn.jsp?eventtype=1&username=<%=username%>&orgname=<%=orgname%>&eventID=<%=newcode%>";
		
		//��������ʽΪGET�����������URL������Ϊ�첽�ύ
		xmlHttp.open("GET", url, true);
		
		//��������ַ���Ƹ�onreadystatechange����
		//�����ڵ绰����
		xmlHttp.onreadystatechange=callback;
		
		//��������Ϣ���͵�Ajax����
		xmlHttp.send(null);
	
		
	}
	
	function callback() {
		var str;
		var str1;
		var ch = new Array;
        var cks =document.getElementsByName("items");
		//Ajax����״̬Ϊ�ɹ�
		if (xmlHttp.readyState == 4) {
			//HTTPЭ��״̬Ϊ�ɹ�
			
			if (xmlHttp.status == 200) {
			str=xmlHttp.responseText;
			
			}else {
				alert("����ʧ�ܣ�������=" + xmlHttp.status);
			}
		}
	}
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	validate();
	window.open("goodsinformINList.jsp?StoreEventNo=<%=newcode%>&detail=0&sid="+rand,"goodsinformINList");
	F5();
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

function detail(eventid)
{
	createwindow('��ϸ', "goodsmanage/goodsinformINdetail.jsp?StoreEventNo="+eventid,1000,550);
}
function dele(eventid)
{
var temp=eventid.split(",");
document.getElementById("eventid").value=temp[0];

if(!temp[1])
{
	if (confirm("ȷ��Ҫɾ����"))
			{
				document.all("Submit").click();
			}
}
else
{
	alert("���¼�����ɾ��");
}
}
function createwindow(title, url, width, height) {
	$.dialog({
		id:'LHG1976D',
		content : 'url:' + url,
		lock : true,
		width : width,
		height : height,
		title : title,
		opacity : 0.3,
		cache : false,
		cancelVal : '�ر�',
		cancel : true/* Ϊtrue�ȼ���function(){} */
	});

}
</script>
</BODY>
</HTML>
