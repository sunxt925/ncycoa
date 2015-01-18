<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String  bm=request.getParameter("bm");
//System.out.println(bm+"dvxvxvxcvx");
%>
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	//System.out.println(bm);
	 meetingattendee og=new meetingattendee();
	
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=20;
     DataTable dt=og.getMemberList(page_no,per_page,bm);
	DataTable dtcount=og.getAllmemberList(bm);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	//System.out.println(trackName);
	//System.out.println(page_no);
	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	//var rand=Math.floor(Math.random()*10000);
	showModalDialogWin("attend_new.jsp?bm="+"<%=bm%>",1000,600);
	//document.all("form1").submit();
	window.location.reload();
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
function F5()
{
	window.location.reload();
}
function F1(code){
showModalDialogWin("attend_mod.jsp?bm="+code,490,500);
	window.location.reload();

}
function F6(aa){
if (confirm("删除人员，是否继续?"))
		{
			document.getElementById("item").value=aa;
document.all("form1").submit();
		}
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe">&nbsp;&nbsp;
<a href="#" onClick="F3()" >新增</a></td>
</table>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">

  
    </table>
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setRowCode("参会人员","@参会人员@"+",BASE_STAFF,STAFFCODE,STAFFNAME");
		tableutil.setRowCode("所在部门","@所在部门@"+",BASE_ORG,ORGCODE,ORGNAME");
		//tableutil.setRowCode("手机号","@参会人员@"+",BASE_STAFF,STAFFCODE,MOBILEPHONE");
	   out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr><!--
          <td width="50%">【<a href="#" onClick="F4()">删除</a>】【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          --><td align="right">
          <%
         String unitccmtemp="&bm="+bm;
      	out.print(PageUtil.DividePage(page_no,pagecount,"attend.jsp",unitccmtemp));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
      
        <input name="act" type="hidden" id="act" value="del"></div></td>
            <input type="hidden" id="entity" name="entity" value="COM_MEETINGATTENDEE">
            <input name="item" type="hidden" id="item" value="">
             <input name="meetingno" type="hidden" id=""meetingno"" value="<%=bm %>">
        <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingattendAction">
      
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>