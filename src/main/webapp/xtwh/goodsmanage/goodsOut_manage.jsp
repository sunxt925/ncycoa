<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	GoodsStoreEvent gse=new GoodsStoreEvent();
	
	String name=request.getParameter("name");
    
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
    //int page_no=3;
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half()-1;
	
	DataTable dt=gse.getAllGoodsStore(page_no,per_page,"����");
	//DataTable dt=og.getStdList(page_no,per_page,unitccm);
	DataTable dtcount=gse.getAllGoodsStore("����");
	//DataTable dtname=og.getOrgName(unitccm);
	//String name=dtname.get(0).get(1).toString();
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	String newcode=AutoCoding.Codingnolevel("com_storeevent","StoreEventNo","OUT",6);
	
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
	showModalDialogWin("goodsinformOUTdetail.jsp?StoreEventNo="+eventid,1000,800);
	window.location.reload();
}
function dele(eventid)
{
//alert(orgcode);
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
			//document.getElementsByName(orgcode).checked="checked";
			//document.form1.docno.value=docno;
			
			
			//window.location.reload();
}

var xmlHttp;
	 
	function createXMLHttpRequest() {
		//��ʾ��ǰ���������ie,��ns,firefox
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function validate() {
		UnSelectAll('form1');
		//����Ajax���Ķ���XMLHttpRequest
		createXMLHttpRequest();
		
		var url = "goodsstoreeventOut.jsp?eventtype=0&username=<%=username%>&orgname=<%=orgname%>&eventID=<%=newcode%>";
		
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
function F1(message)//���ʳ���
{	

	var rand=Math.floor(Math.random()*10000);
	validate();//�ú�������chu���¼�����
	window.open("goodsinformOUTList.jsp?StoreEventNo=<%=newcode%>&detail=0&sid="+rand,"goodsinformOUTList");
	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="main_table_topbg" height="31">��ҳ &gt;&gt; ϵͳά�� &gt;&gt; ���ʳ������</td>
        <td class="main_table_topbg" height="31" align="right"></td>
      </tr>
      <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F1()">���ʳ���[F3]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>
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
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsOut_manage.jsp",unitccmtemp));
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
