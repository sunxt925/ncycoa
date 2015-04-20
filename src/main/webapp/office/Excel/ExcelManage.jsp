<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	DBObject db = new DBObject();
	String sql="select * from system_ftp where file_type=1 order by file_id";
	DataTable dt=db.runSelectQuery(sql);
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("CreateExcel.jsp?sid="+rand,"_self");
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("删除文件，是否继续？"))
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
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 模块设置 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F3()">新建文件[F3]</a>　<a href="#" onClick="F4()">删除[F4]</a>　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
      <tr>
	    <td width="5%" align="center">&nbsp;</td>
        <td width="45%" align="center">文件名</td>
		<td width="45%" align="center">操作</td>
      </tr>
	  <%
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
	  %>
      <tr onMouseOver="this.style.backgroundColor='#D0E9ED'" onMouseOut="this.style.backgroundColor=''">
	    <td align="center"><input type="checkbox" id="items" name="items" value="<%=r.getString("file_id")%>" class="checkbox1"></td>
        <td align="center"><%=r.getString("file_name")%></td>
         <td style="text-align:center;">
			<a href="ExcelOpen.jsp?f_url=<%=r.getString("file_url")%>&f_name=<%=r.getString("file_name")%>&f_id=<%=r.getString("file_id")%>"><span style=" color:Green;">编辑</span></a>&nbsp;&nbsp;&nbsp;
			<a href="ExcelDelete.jsp?f_url=<%=r.getString("file_url")%>&f_name=<%=r.getString("file_name")%>&f_id=<%=r.getString("file_id")%>" ><span style=" color:Green;">删除</span></a>&nbsp;&nbsp;&nbsp;
					
		</td> 
      </tr>
	  <%
	  	}}
	  %>
    </table>
	<%
		if (dt!=null && dt.getRowsCount()>0) {
	%>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          <td width="50%" align="right">&nbsp;</td>
        </tr>
      </table><%}%>
      <input name="act" type="hidden" id="act" value="del"><input name="dao_class" type="hidden" id="dao_class" value="com.dao.query.QueryUnitDao"></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
