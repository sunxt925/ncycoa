<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
String rolecode=Format.NullToBlank(request.getParameter("bm"));
//System.out.println(rolecode+"dvxvxvxcvx");
if (unitccm.equals("")) unitccm="NC";
%>
<HTML>
<HEAD>
<TITLE>�����еط�˰���</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	   Org og=new Org();
   DataTable dt=og.getTopList();
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
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
	var newlisturl="member_list.jsp?unitccm="+orgcode+"&rolecode="+"<%=rolecode%>";
	var newtreeurl="../tree/RolePosition_tree.jsp?pageurl=../ConferenceManage/member_list.jsp?rolecode="+"<%=rolecode%>"+"&pagetarget=memberlist&unitccm="+orgcode;
	window.open(newlisturl,'memberlist');
	window.open(newtreeurl,'RoleMembertree');
	//var newlisturl='postion_list.jsp?orgcode='+orgcode;
	//var newtreeurl='../../tree/unit_tree.jsp?orgcode='+orgcode;
	//window.open(newlisturl,'postionlist');
	//window.open(newtreeurl,'unittree');
	//window.open("empty.jsp","memberlist");
}

function getmember(positioncode)
{
  var newmemberurl='member_list.jsp?positioncode='+positioncode;
  window.open(newmemberurl,"memberlist");
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; ϵͳά�� &gt;&gt; �û����� </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="200" valign="top"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">
            <%
				if (dt!= null && dt.getRowsCount()>0)
				{
					for (int i=0;i<dt.getRowsCount();i++)
					{
						out.print("<tr><td class='table_td_jb_iframe' align='center'><a href=\"#\" onclick=\"changetopunit('"+dt.get(i).getString("orgcode")+"')\">��"+dt.get(i).getString("orgname")+"��</a></td></tr>");	
					}
				}
			%>
			</fieldset>
            </td>
          </tr>
          <tr>
            <td valign="top" height="100%"><iframe src="" name="RoleMembertree" id="RoleMembertree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
        </table></td>
		<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr >
		    <td valign="bottom"><iframe src="" name="memberlist" id="memberlist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		</table>
		</td>
      </tr>
    </table></td>
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