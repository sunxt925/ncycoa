<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>

<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
    String  StoreEventNo=request.getParameter("StoreEventNo");
     SystemRole role=new SystemRole();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_half();
	//System.out.println(orgcode+"sdfsdfds");
	DataTable dt=role.getRoleList(page_no,per_page);
	DataTable dtcount=role.getAllRoleList();
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	
	//System.out.println(newcode);
%>
<base target="_self">
<a id="reload" href="goodsOUT_new.jsp?StoreEventNo=<%=StoreEventNo%>" style="display:none"></a>
<HEAD>
<script language="javascript" src="../js/public/select.js"></script>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    
<tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
		<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		  
		  <tr valign="top">
		    <td valign="top"><iframe src="goodsinformOUTListdetail.jsp?StoreEventNo=<%=StoreEventNo %>&detail=1" name="goodsinformOUTList" id="goodsinformOUTList" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		</table>
		</td>
      </tr>
    </table></td>
  </tr>
</table>

</BODY>
</HTML>
