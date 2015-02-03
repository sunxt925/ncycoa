<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,java.util.*,com.db.*,com.common.*,com.entity.system.*,com.entity.stdapply.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE>南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<% 
	String staffcode=request.getParameter("applystaffcode");
	String applyid=request.getParameter("applyid");
	String type=request.getParameter("type");
	ApplyModOrgList applymodOrgList = new ApplyModOrgList();
	applymodOrgList.getListByStaffcode(staffcode); 
	List<String> orgcodelist=applymodOrgList.getOrgcodelist();
	List<String> orgnamelist=applymodOrgList.getOrgnamelist();
	request.getSession().setAttribute("applyerid",applyid);
	
	
%>
<script language="javascript" src="<%=request.getContextPath()%>/js/public/select.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/public/key.js"></script>
<script language="javascript">

function F5()
{
	window.location.reload();
}

function changetopunit(unitccm)
{
alert(unitccm);
var type=document.form1.type.value;
alert(type);
	//var newlisturl='/ncycoa/stdapply/std_orgpostlist.jsp?unitccm='+unitccm+'&type='+type;
	                        //var newtreeurl='../tree/unit_tree.jsp?pageurl=../std_allunitsearch/std_orgpostlist.jsp&pagetarget=stdlist&unitccm='+unitccm;
	//window.open(newlisturl,'postlist');
							//window.open(newtreeurl,'unittree')
	var newlisturl='/ncycoa/stdapply/std_modlist.jsp?orgcode='+unitccm+'&type='+type;
	window.open(newlisturl,'stdlist');
}

</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/servlet/PageHandler">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="<%=request.getContextPath()%>/images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 标准化管理 &gt;&gt; 单位标准查询 </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="<%=request.getContextPath()%>/images/table_rt.jpg" width="22" height="31"></td>
  </tr>
  <!-- 
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
   -->
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
         <td width="200" valign="top"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <%
				if (orgcodelist!= null && orgcodelist.size()>0)
				{
					for (int i=0;i<orgcodelist.size();i++)
					{
						out.print("<tr><td class='table_td_jb_iframe' align='center'><a href=\"#\" onclick=\"changetopunit('"+orgcodelist.get(i)+"')\">【"+orgnamelist.get(i)+"】</a></td></tr>");	
					}
				}
			%>
            </table>
            </td>
          </tr>
         </table>
         </td>
            <td valign="top" height="100%">
<!--            <iframe src="" name="postlist" id="postlist" width="100%" height="51%" scrolling="no" frameborder="2" ></iframe><br>-->
           <iframe src="" name="stdlist" id="stdlist" width="100%" height="100%" scrolling="no" frameborder="2" ></iframe></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="<%=request.getContextPath()%>/images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="<%=request.getContextPath()%>../images/table_rb.jpg" width="10" height="5"></td>
  <td><input name="type" type="hidden" id="type" value="<%=type %>"> </td>
  </tr>
</form>
</table>
</BODY>
</HTML>