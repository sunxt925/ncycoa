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
	
	//System.out.println(dtcount.getRowsCount());
%>
<base target="_self">

<HEAD>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">
function F3()
{
	var rand=Math.floor(Math.random()*10000);
	window.open("systemrole_new.jsp?sid="+rand+"","temp");
}
function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("删除角色，是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scroll=no;help: no;resizable:no;status:no;");
          
}
function F5()
{
	reload.click();
}
function F1(rolecode){
showModalDialogWin("systemrole_mod.jsp?bm="+rolecode,490,500);
	window.location.reload();

}
function F6(aa){
if (confirm("删除角色，是否继续？"))
		{
			document.getElementById("item").value=aa;
document.all("form1").submit();
		}

}
</script>

<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
    <!--<tr>
      <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
      <td width="94%" valign="middle" class="main_table_topbg" height="31">首页 &gt;&gt; 系统维护 &gt;&gt; 物资入库详细 </td>
      <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
   
    </tr>
--><tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
		<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		 <tr valign="top">
            <td valign="top"><iframe src="goodsIn_manage.jsp" name="goodsIn_manage" id="goodsIn_manage" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		  </tr>
		  
		<tr valign="top">
		    <td valign="top"><iframe  name="goodsinformINList" id="goodsinformINList" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
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
