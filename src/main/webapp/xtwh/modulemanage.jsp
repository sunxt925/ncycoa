<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�����еط�˰���</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	DBObject db=new DBObject();
	String sql="select * from xtwh_zcd order by cdccm";
	DataTable dt=db.runSelectQuery(sql);
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("module_new.jsp?sid="+rand,"_self");
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
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="module_do.jsp">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; ϵͳά�� &gt;&gt; ģ������ </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F3()">����[F3]</a>��<a href="#" onClick="F4()">ɾ��[F4]</a>��<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
      <tr>
	    <td width="5%" align="center">&nbsp;</td>
        <td width="10%" align="center">�����</td>
        <td width="20%" align="center">ģ������</td>
		<td width="20%" align="center">·��</td>
        <td width="10%" align="center">����</td>
		<td width="25%" align="center">��ע</td>
        <td width="10%" align="center">����</td>
      </tr>
	  <%
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
	  %>
      <tr onMouseOver="this.style.backgroundColor='#D0E9ED'" onMouseOut="this.style.backgroundColor=''">
	    <td align="center"><input type="checkbox" id="items" name="items" value="<%=r.getString("cdccm")%>" class="checkbox1"></td>
        <td align="left"><%=r.getString("cdccm")%></td>
        <td align="left"><%=Coder.getCdfromccm(r.getString("cdccm"))%><%=r.getString("cdmc")%></td>
		<td align="left"><%=r.getString("url")%></td>
        <td align="center"><%=Coder.getEnumValue(r.getString("mklx"),"1,�о�ר��;2,���ؾ�ר��;3,����")%></td>
		<td align="left"><%=r.getString("bz")%></td>
        <td align="center"><a href="module_mod.jsp?bm=<%=r.getString("cdccm")%>" class="button4">�� ��</a></td>
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
          <td width="50%">��<a href="#" onClick="SelectAll('form1')">ȫѡ</a>����<a href="#" onClick="ChangeSelect('form1')">��ѡ</a>����<a href="#" onClick="UnSelectAll('form1')">���</a>��</td>
          <td width="50%" align="right">&nbsp;</td>
        </tr>
      </table><%}%>
      <input name="act" type="hidden" id="act" value="del"></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
