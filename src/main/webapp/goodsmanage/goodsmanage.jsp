<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
if (unitccm.equals("")) unitccm="NC";
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>

<% 
   Org og=new Org();
   DataTable dt=og.getTopList();
%>

<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="3%" class="main_table_topbg" height="0"><img src="../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="0"> </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../images/table_rt.jpg" width="22" height="31"></td>
  </tr>

  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="250" valign="top">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          
          <tr>
            <td valign="top" height="100%"><iframe src="../tree/goods_tree.jsp?pageurl=../goodsmanage/goods_list.jsp&pagetarget=goodslist" name="goodstree" id="goodstree" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe></td>
          </tr>
        </table>
		
		</td>
		<td valign="top" height="100%">
			<iframe src="goods_list.jsp" name="goodslist" id="goodslist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
			
        </td>
		
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td height="5" class="main_table_bottombg"></td>
    <td height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</table>
</BODY>
</HTML>