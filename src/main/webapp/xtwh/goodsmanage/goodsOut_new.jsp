<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>

<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
    String  StoreEventNo=request.getParameter("StoreEventNo");
    //System.out.println(StoreEventNo);
    String  goodsnumber=request.getParameter("goodsnumber");
    String  goodsname=request.getParameter("goodsname");
    String  goodscode=request.getParameter("goodscode");
    
%>
<base target="_self">
<a id="reload" href="goodsOUT_new.jsp?StoreEventNo=<%=StoreEventNo%>" style="display:none"></a>
<HEAD>
<script language="javascript" src="../../js/public/select.js"></script>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
    <tr>
      <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
      <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 物资出库详细 </td>
      <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
   
    </tr>
<tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
		<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr valign="top">
            <td valign="top"><iframe src="goodsOut_manage.jsp?goodsname=<%=goodsname %>&goodscode=<%=goodscode %>&goodsnumber=<%=goodsnumber %>&StoreEventNo=<%=StoreEventNo %>" name="goodsOut_manage" id="goodsOut_manage" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		  <tr valign="bottom">
		    <td valign="bottom"><iframe src="" name="goodsinformOUTList" id="goodsinformOUTList" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		</table>
		</td>
      </tr>
    </table></td>
  </tr>
  
</form>
</table>

</BODY>
</HTML>
