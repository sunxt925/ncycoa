<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orgcode = request.getParameter("orgcode");
String orgname=request.getParameter("orgname");
String positioncode=request.getParameter("positioncode");
String positionname=request.getParameter("positionname");
session.setAttribute("positioncode",positioncode);
session.setAttribute("positionname",positionname);
session.setAttribute("orgcode",orgcode);
session.setAttribute("orgname",orgname);
%>
<HTML>
<HEAD>
<TITLE>�ϳ����̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	DBObject db = new DBObject();
	String sql="select * from base_org where orgcode='"+orgcode+"'";
	DataTable dt=db.runSelectQuery(sql);
	
	String blongadmin="";
	if (dt != null && dt.getRowsCount() == 1)
	{
	DataRow r = dt.get(0);
	blongadmin = r.getString("blongadminorgcode");
	}
		
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("unit_new.jsp?sid="+rand,"unitlist");
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

function changetopunit(orgcode)
{
	var newlisturl='user_choose.jsp?unitccm='+orgcode+'&orgname='+'<%=orgname%>'+'&positioncode='+'<%=positioncode%>'+'&positionname='+'<%=positionname%>';
	var newtreeurl='../../tree/unit_tree.jsp?unitccm='+orgcode+'&pageurl=../xtwh/user/user_choose.jsp&pagetarget=orgmember';
	window.open(newlisturl,'orgmember');
	window.open(newtreeurl,'unittree');
	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; ϵͳά�� &gt;&gt; ��Աѡ�� </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="250" valign="top"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">

            </td>
          </tr>
          <tr>
           <td valign="top" height="100%"><iframe src="../../tree/unit_tree.jsp?unitccm=<%=blongadmin %>&pageurl=../xtwh/user/user_choose.jsp&pagetarget=orgmember" name="unittree" id="unittree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
        </table></td>
       <td valign="top"><iframe src="user_choose.jsp" name="orgmember" id="orgmember" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
      </tr>
    </table></td>
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