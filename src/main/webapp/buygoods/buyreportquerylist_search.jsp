<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*" errorPage="" %>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.business.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script></HEAD>
<base target="_self">
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">
function search()
{
var stime=document.all("apptime").value;
var etime=document.all("appendtime").value;
var buymode=document.all("COM_BUYREPORTEVENT.BUYMODE").value;

 window.parent.open("buyreportquerylistitem.jsp?appdate="+stime+"&appenddate="+etime+"&buymode="+buymode,"buyreportquerylistitem");

}
</script>

<% ComponentUtil cu=new ComponentUtil(); 
   cu.Width="150px";
%>
<BODY class="mainbody" onLoad="this.focus()">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<form name="form1" id="form1" method="post"
				action="../servlet/PageHandler">
				
				<tr>
   <td colspan="3" valign="middle" class="table_td_jb">  
      
 申请日期段：   <input type="text" onfocus="new WdatePicker(this,null,false,'whyGreen')" name="apptime" id="apptime" class="input1"/>
             
---      <input type="text" onfocus="new WdatePicker(this,null,false,'whyGreen')" name="appendtime" id="appendtime" class="input1"/>
 
       &nbsp;&nbsp;&nbsp;&nbsp;
    采购模式：  <!--   <input type="text" name="buymode" id="buymode"/> -->
    <%out.print(cu.print("COM_BUYREPORTEVENT","BUYMODE")); %>
      &nbsp;&nbsp;&nbsp;&nbsp;

<button onclick="search()">查询</button>
</td>
</tr>
				
			</form>
			
		</table>
	</BODY>

</HTML>