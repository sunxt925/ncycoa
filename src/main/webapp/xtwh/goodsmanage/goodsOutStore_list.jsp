<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String StoreEventNo=Format.NullToBlank(request.getParameter("StoreEventNo"));
%>
<HTML>

<a id="reload" href="#" style="display:none"></a>
<base target="_self">
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	String name=request.getParameter("name");
	GoodsStoreInfo goodsStore=new GoodsStoreInfo();
	//Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=goodsStore.getGoodsStoreInfoNumberAndName(page_no,per_page,name);
	DataTable dtcount=goodsStore.getAllGoodsStoreInfo();
	//System.out.println(dtcount.getRowsCount()+"nihaoasdjfhkjasdhfjkh");
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//String res=og.getTrack(goodscode,"");
	String res="";
	Orgmember orgmember=new Orgmember(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
	
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
function F8()
{
      
		document.all("Submit").click();
		//this.window.close();
}
function searchName()
{
	var name=document.getElementById("search").value;
	var rand=Math.floor(Math.random()*10000);
	var reload=document.getElementById("reload");
	reload.href="goodsOutStore_list.jsp?sid="+rand+"&StoreEventNo="+"<%=StoreEventNo%>"+"&name="+name;
	reload.click();
	
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
          <td class="table_td_jb" height="31" align="right"><input name="search" type="text" class="input1" id="search"  onfocus="cls()" onblur="res()"  value="������ؼ���" size="30" maxlength="30"><a href="#" onClick="searchName()" class="button4">��ѯ</a></td>
       </td>
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
          String unitccmtemp="&StoreEventNo="+StoreEventNo;
      	out.print(PageUtil.DividePage(page_no,pagecount,"goodsOutStore_list.jsp",unitccmtemp));
       %>
       </td>
       
       <input type="submit" name="Submit" value="�ύ" style="display:none">
       <input type="hidden" id="entity" name="entity" value="COM_OUTSTOREITEM"/>
           <input type="txt" name="StoreEventNo" id="StoreEventNo" value="<%=StoreEventNo %>" style="display:none">
         
         <input name="act" type="hidden" id="act" value="addDul">
         <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemGoodsinformOUTAction">
        </tr>
        <tr><td align="right"><a href="#" onClick="F8()" class="button4">ȷ����������</a></tr>
      </table>
      <%}%>
     
         
      
</td>
  </tr>
</form>
</table>
</BODY>
</HTML>