<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.index.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
%>
<% 
   Org og=new Org();
   DataTable dt=og.getTopList();
%>
<script language="javascript" src="<%=request.getContextPath() %>/js/public/select.js"></script>

<script language="javascript">
function changetopunit(unitccm)
{


	var newlisturl='std_orgpostlist.jsp?unitccm='+unitccm;
	var newtreeurl='../tree/unit_tree.jsp?pageurl=../std_search/std_orgposttab.jsp&pagetarget=postlist&unitccm='+unitccm;
	window.open(newlisturl,'postlist');
	window.open(newtreeurl,'unittree')
}
</script>
<BODY class="mainbody" onLoad="this.focus()">

<form name="form1" id="form1" method="post" action="<%=request.getContextPath() %>/servlet/PageHandler">
 <table width="95%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="250" valign="top">
		<table  width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">
                
          
            <div style="position: relative;overflow-y:auto; overflow-x:auto; border:1px solid gray;">
          
              <%
				if (dt!= null && dt.getRowsCount()>0)
				{
					for (int i=0;i<dt.getRowsCount();i++)
					{
						out.print("<tr><td class='table_td_jb_iframe'style='padding-left:10px; padding-top:5px;'><a href=\"#\" onclick=\"changetopunit('"+dt.get(i).getString("orgcode")+"')\">○ "+dt.get(i).getString("orgname")+"</a></td></tr>");	
					}
				}
			%>
          
     	 	</div>
            </td>
          </tr>
          
        </table>
		
		</td>
		 </tr>
          <tr>
            <td valign="top" height="100%"><iframe src="../tree/unit_tree.jsp?pageurl=../std_search/std_orgposttab.jsp&pagetarget=postlist" name="unittree" id="unittree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
		
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="<%=request.getContextPath() %>/images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="<%=request.getContextPath() %>/images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </table>
</form>

</BODY>
</HTML>