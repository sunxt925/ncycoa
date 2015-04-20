<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orgcode=request.getParameter("unitccm");
String orgname = request.getParameter("orgname");
String orgclass = request.getParameter("orgclass");
session.setAttribute("orgclass",orgclass);
session.setAttribute("orgcode",orgcode);
session.setAttribute("orgname",orgname);
String rolecode=Format.NullToBlank(request.getParameter("rolecode"));
%>
<HTML>
<HEAD>
<TITLE>四川省南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	//System.out.println(request.getParameter("unitccm"));
	DBObject db = new DBObject();
	
	String sql="select * from base_orgposition where orgcode='"+ orgcode+"' order by orgcode";
	DataTable dt=db.runSelectQuery(sql);
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
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
function getmember(positioncode)
{

    positionname = document.getElementById("positionname"+positioncode).innerHTML;
    var rand=Math.floor(Math.random()*10000);
	var newmemberurl="member_list.jsp?positioncode="+positioncode + "&positionname="+positionname+"&rolecode="+"<%=rolecode%>";
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
      <tr>
        <td width="10%" align="center">岗位编码</td>
        <td width="15%" align="center">岗位名称</td>
        <td width="50%" align="center">岗位说明</td>
        <td width="10%" align="center">配置人数</td>
        
      </tr>
      <%
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
	  %>
      <tr onMouseOver="this.style.backgroundColor='#D0E9ED'" onMouseOut="this.style.backgroundColor=''" onClick="getmember('<%=r.getString("positioncode")%>')">
        <td align="center"><%=r.getString("positioncode")%></td>
        <td align="center" id="<%="positionname"+r.getString("positioncode")%>"><%=r.getString("positionname")%></td>
        <td align="left"><%=r.getString("memo")%></td>
        <td align="center"><%=r.getString("positionconfigcount")%></td>
        
      </tr>
      <%
	  	}}
	  %>
    </table>
      <%
		if (dt!=null && dt.getRowsCount()>0) {
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">

      </table>
      <%}%>
      <input name="act" type="hidden" id="act" value="del">
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