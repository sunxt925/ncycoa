<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.Staff" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String positioncode=request.getParameter("positioncode");
String positionname=request.getParameter("positionname");
String orgcode1=request.getParameter("unitccm");
String meetingno=Format.NullToBlank(request.getParameter("rolecode"));

String orgcode=(String)session.getAttribute("orgcode");
String orgname=(String)session.getAttribute("orgname");
session.setAttribute("positioncode",positioncode);
session.setAttribute("positionname",positionname);
//System.out.println(orgname);


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草局</TITLE>
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
	int per_page=15;
	
	DataTable dt=staff.getMemberListByorg(page_no,per_page,orgcode1);
	DataTable dtcount=staff.getAllMemberListByorg(orgcode);
	
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="../js/public/select.js"></script>
<script language="javascript" src="../js/public/key.js"></script>
<script language="javascript">

function F8()
{
  if (CheckSelect("form1"))
	{
		if (confirm("确定要为该会议添加人员,是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要添加的内容！");
	}
}


function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("父级菜单的删除将级联删除子菜单，是否继续？"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("你没有选中要删除的内容！");
	}
}
function F5()
{
	window.location.reload();
}

</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe">&nbsp;&nbsp; 
<a href="#" id="F8" style="display:none" onClick="F8()" >保存</a>
</table>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">

    </table>
   <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
       
          <td align="right">
          <%
         String position = "unitccm="+orgcode1+"&rolecode="+meetingno;
        	out.print(PageUtil.DividePage(page_no,pagecount,"member_list.jsp",position));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
    
        <input name="act" type="hidden" id="act" value="add"></div>
           <input name="meetingno" type="hidden" id="meetingno" value="<%=meetingno%>">
              <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode1%>">
        <input name="action_class" type="hidden" id="action_class" value="com.action.system.meetingattendAction">
      
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