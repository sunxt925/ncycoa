<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bm=Format.NullToBlank(request.getParameter("unitccm"));
String level=request.getParameter("level");
	String positioncode=request.getParameter("positioncode");
	String orgcode=request.getParameter("orgcode");
if (bm.equals("")) bm="NC";
%>
<HTML>
<HEAD>
<TITLE>�Ĵ�ʡ�ϳ��̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	
	Org og=new Org();
	OrgPosition orgposition=new OrgPosition();
	 //OrgPosition orgposition=new OrgPosition(positioncode,bm);
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	DataTable dt=orgposition.getAllRelationOrgPositionList(bm);
	DataTable dtcount=orgposition.getAllOrgPositionList(bm);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//String trackName=og.getTrack(bm,"");

	String trackName="";
	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	showModalDialogWin("unitposition_new.jsp?sid="+rand+"&bm="+"<%=bm%>",1000,720);
	window.location.reload();
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
function F5()
{
	window.location.reload();
}
function F8()
{
      
		document.all("Submit").click();
	//	window.location.reload();
		//this.window.close();
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>��ǰ��λ��<%=trackName %>��λ��Ϣ</td>
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
          <td width="50%">��<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          <td align="right">
          <a href="#" id="F8" style="display:none" onClick="F8()" class="button4">ȷ��</a>
       </td>
           <input name="act" type="hidden" id="act" value="add">
          <input type="submit" name="Submit" value="�ύ" style="display:none">
          <input type="txt" name="positioncode" id="positioncode" value="<%=positioncode %>" style="display:none">
         <input type="txt" name="orgcode" id="orgcode" value="<%=orgcode %>" style="display:none">
         <input type="txt" name="level" id="level" value="<%=level %>" style="display:none">
          
      <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitPositionRelationAction">
        </tr>
        
      </table>
      <%}%>
  
  </td>
  </tr>
</form>
</table>
</BODY>
</HTML>