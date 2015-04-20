<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.dao.system.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";




String orgcode=request.getParameter("orgcode");
String orgname=request.getParameter("orgname");
String positioncode=request.getParameter("positioncode");
String positionname=request.getParameter("positionname");
String staffname = request.getParameter("staffname");
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<base target="_self">
</HEAD>

<%
	StaffInfo staff = new StaffInfo();
  int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full()/2-2;
	DataTable dt=staff.getMemberList(page_no,per_page,staffname);
	DataTable dtcount=staff.getAllMemberList(staffname);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>

<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">

function F5()
{
	window.location.reload();
}


function F8()
{

		//alert ("aaa");
		document.all("Submit").click();
	
}

function commituser(idcard){

     document.getElementById("idcard0").value=idcard;
     document.all("Submit0").click();
}

</script>
<body>
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe">&nbsp;&nbsp; 



</td>
</tr>
</table>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">

    </table>
   <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>=0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setRowValue("操作","<a href=\"#\" onClick=commituser('@身份证号@') class=\"button4\">确定</a>");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        <td width="50%" align="left">
        员工选择
        </td>
          <td align="right">

       </td>
          
        </tr>
        
      </table>
      <%}%>
 
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>

<form name="form2" id="form2" method="post" action="../../servlet/PageHandler">
 <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode %>">
 <input name="orgname" type="hidden" id="orgname" value="<%=orgname %>">
 <input name="positioncode" type="hidden" id="positioncode" value="<%=positioncode %>">
 <input name="positionname" type="hidden" id="positionname" value="<%=positionname %>">
 <input name="idcard0" type="hidden" id="idcard0" >
 <input name="act" type="hidden" id="act" value="get"/>
 <input name="action_class" type="hidden" id="dao_class" value="com.action.system.StaffAction">
 <input type="submit" name="Submit0" value="提交" style="display:none">

</form>

</table>
</body>
</html>