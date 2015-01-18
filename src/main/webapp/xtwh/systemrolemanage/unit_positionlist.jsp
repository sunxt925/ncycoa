<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bm=Format.NullToBlank(request.getParameter("unitccm"));
String rolecode=Format.NullToBlank(request.getParameter("rolecode"));
//System.out.println(rolecode+"dvxvxvxcvx");
if (bm.equals("")) bm="11511301";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	//System.out.println(bm);
	Org og=new Org();
	OrgPosition orgposition=new OrgPosition();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=20;
	DataTable dt=orgposition.getRoleOrgPositionList(page_no,per_page,bm);
	DataTable dtcount=orgposition.getAllOrgPositionList(bm);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	String trackName=og.getTrack(bm,"");
	//System.out.println(trackName);
	//System.out.println(page_no);
	/* DBObject db = new DBObject();
	
	String sql="select * from system_unit where unit_ccm like'"+ unitccm+"___' order by unit_ccm";
	DataTable dt=db.runSelectQuery(sql); */
%>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	if (CheckSelect("form1"))
	{
		if (confirm("确定要为该角色添加岗位,是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要添加的内容！");
	}
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
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
   <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">当前单位：<%=trackName %></td>
      </tr>
      <tr>
      <td><a href="#" onClick="F3()" >保存</a></td>
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
          <td width="50%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          <td align="right">
          <%
         String unitccmtemp="&unitccm="+bm;
      	out.print(PageUtil.DividePage(page_no,pagecount,"unit_positionlist.jsp",unitccmtemp));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
     <input name="act" type="hidden" id="act" value="add">
          <input name="orgcode" type="hidden" id="orgcode" value="<%=bm%>">
           <input name="rolecode" type="hidden" id="rolecode" value="<%=rolecode%>" >
        <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemRolePositionAction">
     
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>