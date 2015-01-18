<%@page import="com.entity.system.UserInfo"%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.OrgPosition" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orgcode=request.getParameter("unitccm");
String orgname = request.getParameter("orgname");
String orgclass = request.getParameter("orgclass");

%>
<HTML>
<HEAD>
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	
	//DBObject db = new DBObject();
	//String sql="select * from base_orgposition where orgcode='"+ orgcode+"' order by orgcode";
	//DataTable dt=db.runSelectQuery(sql);
    OrgPosition op = new OrgPosition();
     UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full()/2-2;
  
	DataTable dt=op.getPositionList(page_no,per_page,orgcode);
	DataTable dtcount=op.getAllOrgPositionList(orgcode);
	
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript">
function F3()
{

	var rand=Math.floor(Math.random()*10000);
	window.open("CopyOfunit_new.jsp?sid="+rand+"&unitccm="+"<%=orgcode%>","_self");
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
function getmember(positioncode,positionname)
{

    var rand=Math.floor(Math.random()*10000);
	var newmemberurl='member_list.jsp?positioncode='+positioncode + '&positionname='+positionname+'&orgcode='+'<%=orgcode%>'+'&orgname='+'<%=orgname%>'+'&orgclass='+'<%=orgclass%>';
	window.open(newmemberurl,"memberlist");
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<script language="javascript">
 window.open("empty.jsp","memberlist");
</script>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr><td colspan="3" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">　&gt;&gt;岗位信息</td>
      </tr>
  </table>
  </td></tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">

    </table>
      <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>=0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("岗位编码","20%");
		tableutil.setHeadWidth("岗位名称","20%");
		tableutil.setHeadWidth("配置人数","10%");
		tableutil.setHeadWidth("岗位说明","50%");
		tableutil.setLineAction("onclick=\"getmember('@岗位编码@','@岗位名称@')\"");
		out.print(tableutil.DrawTable());
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
         
          <td align="right">
          <%
         String position = "unitccm="+orgcode+"&orgname="+orgname+"&orgclass="+orgclass;
      	out.print(PageUtil.DividePage(page_no,pagecount,"postion_list.jsp",position));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
    
      <input name="act" type="hidden" id="act" value="del">
      <input name="orgclass" type="hidden" id="orgclass" value="<%=orgclass %>">
      <input name="orgname" type="hidden" id="act" value="<%=orgname %>">
      <input name="orgcode" type="hidden" id="act" value="<%=orgcode %>">
      <input name="dao_class" type="hidden" id="dao_class" value="com.dao.query.QueryUnitDao"></td>
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