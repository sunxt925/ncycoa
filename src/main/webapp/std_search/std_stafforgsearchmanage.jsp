<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE>�����еط�˰���</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<% 
    UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
	Staff staff = new Staff();
	DataTable dt = staff.getAllOrgByStaffCode(staffcode); 
	String pageheight=UserInfo.getAvalilheight();
	  int pageHeight=(int)(Integer.parseInt(pageheight)*0.8);
	
%>
<script language="javascript" src="../js/public/select.js"></script>

<script language="javascript">

function F5()
{
	window.location.reload();
}

function changetopunit(unitccm)
{


	var newlisturl='std_orgposttab.jsp?unitccm='+unitccm;
	//var newtreeurl='../tree/unit_tree.jsp?pageurl=../std_allunitsearch/std_orgpostlist.jsp&pagetarget=postlist&unitccm='+unitccm;
	window.open(newlisturl,'postlist');
	//window.open(newtreeurl,'unittree')
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg"><img src="../images/table_lt.jpg" width="22" ></td>
    <td width="94%" valign="middle" class="main_table_topbg" >��ҳ &gt;&gt; ��׼������ &gt;&gt; ��λ��׼��ѯ </td>
    <td width="3%" align="right" class="main_table_topbg" ><img src="../images/table_rt.jpg" width="22" ></td>
  </tr>
  <!-- 
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F5()">ˢ��[F5]</a></td>
  </tr>
   -->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15%"  height="100%" class="main_table_centerbg" colspan="3" valign="top">
          <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" valign="top" bgcolor="#DBEAFD">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <%
				if (dt!= null && dt.getRowsCount()>0)
				{
					for (int i=0;i<dt.getRowsCount();i++)
					{
						out.print("<tr><td class='table_td_jb_iframe'style='padding-left:50px; padding-top:5px;'><a href=\"#\" onclick=\"changetopunit('"+dt.get(i).getString("orgcode")+"')\">��"+dt.get(i).getString("orgname")+"</a></td></tr>");	
					}
				}
			%>
            </table>
            </td>
          </tr>
         </table>
         </td>
<td width="1%">
</td>
            <td valign="top" height="<%=pageHeight%>px"><iframe src="" name="postlist" id="postlist" width="98%" height="98%" scrolling="no" frameborder="0"></iframe></td>
      </tr>
    </table></td>
  </tr>

</form>
</table>
</BODY>
</HTML>