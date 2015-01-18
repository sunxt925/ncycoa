<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.Staff" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String positioncode=request.getParameter("positioncode");
String positionname=request.getParameter("positionname");

String rolecode=Format.NullToBlank(request.getParameter("rolecode"));
String orgcode=(String)session.getAttribute("orgcode");
String orgname=(String)session.getAttribute("orgname");
session.setAttribute("positioncode",positioncode);
session.setAttribute("positionname",positionname);
String org="";
String membid="";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>南充烟草局</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
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
	int per_page=6;
	DataTable dt=staff.getRoleMemberList(page_no,per_page,positioncode,orgcode);
	DataTable dtcount=staff.getAllMemberList(positioncode,orgcode);
	
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="../../js/public/select.js"></script>
<script language="javascript" src="../../js/public/key.js"></script>
<script language="javascript">
function adduser()
{
   window.showModalDialog("user_new.jsp",window,"status=no;dialogWidth=600px;dialogHeight=600px;center=yes;help=no;");
 	//var rand=Math.floor(Math.random()*10000);
	//window.open("user_new.jsp?sid="+rand , "_self" );
}

function adduserlogin(staffcode)
{
    
   window.showModalDialog("login_new.jsp?staffcode="+staffcode,window,"status=no;dialogWidth=350px;dialogHeight=300px;center=yes;help=no;"); 
 //var rand=Math.floor(Math.random()*10000);
 
	//window.open("login_new.jsp?sid="+rand+"&staffcode="+staffcode , "_self" );
}
function moduser(staffcode)
{

    var rand=Math.floor(Math.random()*10000);
    window.showModalDialog("user_mod.jsp?sid="+rand+"&staffcode="+staffcode,window,"status=no;dialogWidth=400px;dialogHeight=400px;center=yes;help=no;");
 	
	//window.open("user_mod.jsp?sid="+rand+"&staffcode="+staffcode , "_self" );
}

function modstaff()
{
    var staffcode="";
    var  staffcodes= document.getElementsByName("staffcode");
    for(i=0;i<staffcodes.length;i++){
    	if(staffcodes[i].checked)
    		staffcode=staffcodes[i].value;
    }
   if(staffcode!="null")
   {

    //idcard = document.getElementById("idcard"+staffcode).innerHTML;
 	var rand=Math.floor(Math.random()*10000);
 	window.showModalDialog("staff_mod.jsp?sid="+rand+"&staffcode="+staffcode,window,"status=no;dialogWidth=700px;dialogHeight=400px;center=yes;help=no;");
	//window.open("staff_mod.jsp?sid="+rand+"&idcard="+idcard , "_self" );
	//showModalDialog("staff_mod.jsp?sid="+rand+"&idcard="+idcard);
	}
	else
	  alert("请先选择一个员工");
}

function deleteuser(staffcode)
{
    var newmemberurl='../../servlet/PageHandler?act=del&action_class=com.action.system.StaffAction&staffcode='+staffcode;
    window.open(newmemberurl,"_self");
}

function positioninfo()
{
    
    var staffcode="null";
    var  staffcodes= document.getElementsByName("staffcode");
    for(i=0;i<staffcodes.length;i++){
    	if(staffcodes[i].checked)
    		staffcode=staffcodes[i].value;
    }
   if(staffcode!="null")
    {
    var rand=Math.floor(Math.random()*10000);
    window.showModalDialog("user_mod.jsp?sid="+rand+"&staffcode="+staffcode,window,"status=no;dialogWidth=400px;dialogHeight=400px;center=yes;help=no;");
	//window.open("user_mod.jsp?sid="+rand+"&staffcode="+staffcode , "_self");
	}
	else
	alert("请先选择一个员工"); 
}

function logininfo()
{
    var staffcode="null";
    var  staffcodes= document.getElementsByName("staffcode");
    for(i=0;i<staffcodes.length;i++){
    	if(staffcodes[i].checked)
    		staffcode=staffcodes[i].value;
    }
    if(staffcode!="null")
    {
    var rand=Math.floor(Math.random()*10000);
    window.showModalDialog("login_mod.jsp?sid="+rand+"&staffcode="+staffcode,window,"status=no;dialogWidth=350px;dialogHeight=300px;center=yes;help=no;");
	//window.open("login_mod.jsp?sid="+rand+"&staffcode="+staffcode , "_self");
	}
	else
	 alert("请先选择一个员工"); 
}

function chooseuser()
{
  if (CheckSelect("form1"))
	{
		if (confirm("确定要为该角色添加岗位,是否继续？"))
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
<a href="#" onClick="chooseuser()" >保存</a>
</table>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
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
        <tr>
          <td width="50%" align="left">
        岗位职工信息---<%=(String)session.getAttribute("positionname") %>
        </td>
          <td align="right">
          <%
         String position = "positioncode="+positioncode+"&positionname="+positionname;
      	out.print(PageUtil.DividePage(page_no,pagecount,"member_list.jsp",position));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
    
        <input name="act" type="hidden" id="act" value="add"></div>
           <input name="rolecode" type="hidden" id="rolecode" value="<%=rolecode%>">
        <input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemRoleMemberAction">
      
  </tr>
  <tr>        
    <td width="3%" height="5" class="main_table_bottombg"><img src="../../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
</form>
</table>
</BODY>
</HTML>