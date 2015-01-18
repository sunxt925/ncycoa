<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.sql.*,java.io.*,com.db.*,com.common.*,javax.servlet.*,javax.servlet.http.*,com.ftp.*,com.entity.ftp.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.DataRegion"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.WordDocument"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>

<%
	DBObject db = new DBObject();
	String sql="select * from office_template order by template_id";
	DataTable dt=db.runSelectQuery(sql);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<head>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">

  //保存 
     function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();
        }
    function F3(){    
			window.open("CreateTemplate.jsp","_self");
    }
    function F4(){
           window.open("UpLoadTemplate.jsp","_self");
    }
    function F5()
{
	window.location.reload();
}
</script>
</head>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 文档管理 &gt;&gt; 文档模板管理 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F3()">新建模板[F3]</a>　<a href="#" onClick="F4()">上传模板[F4]</a>　<a href="#" onClick="F5()">刷新[F5]</a> </td>
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
	    <td align="center"><input type="checkbox" id="items" name="items" value="" class="checkbox1"></td>
	    <td align="center"><%=r.getString("template_name")%></td>
        <td style="text-align:center;">
			<a href="TemplateOpen.jsp?filename=<%=r.getString("template_name")%>&file_path=<%=r.getString("template_path")%>&file_type=<%=r.getString("template_type")%>"><span style=" color:Green;">编辑</span></a>&nbsp;&nbsp;&nbsp;
			<a href="TempDelete.jsp?filename=<%=r.getString("template_name")%>&file_path=<%=r.getString("template_path")%>"><span style=" color:Green;">删除</span></a>&nbsp;&nbsp;&nbsp;				
		</td> 
      </tr>
	  <%
	  	}}
	  %>
    </table>
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%">【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          <td width="50%" align="right">&nbsp;</td>
        </tr>
      </table>
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
</html>
