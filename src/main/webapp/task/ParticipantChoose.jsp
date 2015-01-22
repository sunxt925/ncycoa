<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.Staff" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String orgcode=request.getParameter("unitccm");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充市烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<base target="_self">
</HEAD>
<%

	//DBObject db = new DBObject();

	//String sql="select * from base_orgmember where positioncode='"+ positioncode+"' order by staffcode ";
	//DataTable dt=db.runSelectQuery(sql);
	Staff staff = new Staff();
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=10;
	DataTable dt=staff.getBlongMemberList(page_no,per_page,orgcode);
	DataTable dtcount=staff.getAllMemberList(orgcode);
	
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
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

</script>
<BODY class="mainbody" onLoad="this.focus()">
 
 
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">提交[F8]</a>　　<a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">

    <table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">

    </table>
   <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();

		tableutil.setDt(dt);
		tableutil.setCheckBoxName("选择");
		tableutil.setCheckBoxValue("员工编码");
		
		
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
         
          <td align="right">
          <%
         String org = "orgcode="+orgcode;
      	out.print(PageUtil.DividePage(page_no,pagecount,"ParicipantChoose.jsp",org));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
      
         <input name="act" type="hidden" id="act" value="choose"/>
         <input name="entity" type="hidden" id="entity" value="MONTHTASKPARTICIPANT"/>
                  <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode %>">
                  

         <input type="reset" name="reset" value="重置" style="display:none">
         <input name="action_class" type="hidden" id="dao_class" value="com.action.task.ParticipantAction"></td>
         <input type="submit" name="Submit" value="提交" style="display:none">
   
 
  </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>