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
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
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
		if (confirm("�����˵���ɾ��������ɾ���Ӳ˵����Ƿ������"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("��û��ѡ��Ҫɾ�������ݣ�");
	}
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
//alert(goodscode);
var temp=message.split(",");
var goodscode=temp[0];
var goodsname=temp[1];
var goodsnumber=temp[2];
//alert(goodsnumber);
	if(goodsnumber=="0")
	{
		alert("����Ʒ��治�㣬���ܳ���");
		return;
	}
	validate();//�ú�����������¼�����
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
			if (confirm("ȷ��Ҫɾ����"))
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
          <td class="table_td_jb">��ǰ�������ʿ�������<%=res %></td>
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
          <!--<td width="50%">��<a href="#" onClick="F4()">ɾ��</a>����<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          -->
          <td align="right">
          <%
          String unitccmtemp="&goodscode="+goodscode+"&styleIn="+styleIn+"&styleOut="+styleOut+"&startdate="+startdate+"&enddate="+enddate+"&department="+department;
         
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsStore_list.jsp",unitccmtemp));
       %>
       </td>
       <input type="submit" name="Submit" value="�ύ" style="display:none">
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