<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.dao.system.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String positioncode=request.getParameter("positioncode");
String positionname=request.getParameter("positionname");
String orgcode=request.getParameter("orgcode");
String orgname=request.getParameter("orgname");
String  orgclass= request.getParameter("orgclass");
//System.out.println(positionname);
//System.out.println(positioncode);
//System.out.println(orgclass);
//session.setAttribute("positioncode",positioncode);
//session.setAttribute("positionname",positionname);
String org="";
String membid="";
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");

if(orgclass.equals("00080001"))
{
  org = "��λ��Ϣ";
  membid = "����֤��";
}
else if(orgclass.equals("00080002"))
{
  org = "������Ϣ";
  membid = "��֤����";
}
//System.out.println(orgclass);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>�ϳ����̲ݹ�˾</TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<base target="_self">
</HEAD>
<%
	Staff staff = new Staff();
  int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full()/2-2;
	DataTable dt=staff.getMemberList(page_no,per_page,positioncode,orgcode);
	DataTable dtcount=staff.getAllMemberList(positioncode,orgcode);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
		
%>
<script language="javascript" src="../../js/public/select.js"></script>

 <script type="text/javascript" src="../../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../../jscomponent/tools/outwindow.js"></script>


<script language="javascript">
function adduser()
{ var url="xtwh/user/user_new.jsp?orgcode="+"<%=orgcode%>"+"&orgname="+"<%=orgname%>"+"&positioncode="+"<%=positioncode%>"+"&positionname="+"<%=positionname%>";
			createwindow('�����û���Ա����',url,'700px','300px');
 //  window.showModalDialog("user_new.jsp?orgcode="+"<%=orgcode%>"+"&orgname="+"<%=orgname%>"+"&positioncode="+"<%=positioncode%>"+"&positionname="+"<%=positionname%>",window,"scroll=no;status=no;dialogWidth=700px;dialogHeight=400px;center=yes;help=no;");
 	//var rand=Math.floor(Math.random()*10000);
	//window.open("user_new.jsp?sid="+rand , "_self" );
}

function adduserlogin(staffcode)
{
    
   window.showModalDialog("login_new.jsp?staffcode="+staffcode,window,"scroll=no;status=no;dialogWidth=350px;dialogHeight=300px;center=yes;help=no;"); 
 //var rand=Math.floor(Math.random()*10000);
 
	//window.open("login_new.jsp?sid="+rand+"&staffcode="+staffcode , "_self" );
}
function moduser(staffcode)
{
    if(staffcode!="null")
   {
    var rand=Math.floor(Math.random()*10000);
    window.showModalDialog("user_mod.jsp?sid="+rand+"&staffcode="+staffcode+"&positioncode="+"<%=positioncode%>"+"&positionname="+"<%=positionname%>"+"&orgcode="+"<%=orgcode%>" +"&orgname="+"<%=orgname%>"+ "&orgclass="+"<%=orgclass%>",window,"scroll=no;status=no;dialogWidth=400px;dialogHeight=400px;center=yes;help=no;");
    }
 	else
	  alert("����ѡ��һ��Ա��");

}

function modstaff()
{
    var staffcode="null";
����var  staffcodes= document.getElementsByName("items");
����for(i=0;i<staffcodes.length;i++)
����{
    ���� if(staffcodes[i].checked)
       ����staffcode=staffcodes[i].value;
����}

   if(staffcode!="null")
   {

    //idcard = document.getElementById("idcard"+staffcode).innerHTML;
 	var rand=Math.floor(Math.random()*10000);
 	var url="xtwh/user/staff_mod.jsp?sid="+rand+"&staffcode="+staffcode;
 	createwindow('������Ϣ',url,'700px','400px');
 	//window.showModalDialog("staff_mod.jsp?sid="+rand+"&staffcode="+staffcode,window,"scroll=no;status=no;dialogWidth=700px;dialogHeight=400px;center=yes;help=no;");
	//window.open("staff_mod.jsp?sid="+rand+"&idcard="+idcard , "_self" );
	//showModalDialog("staff_mod.jsp?sid="+rand+"&idcard="+idcard);
	}
	else
	  alert("����ѡ��һ��Ա��");
}

function deleteuser(staffcode)
{
    if(confirm("�Ƿ�ȷ��ɾ��Ա����Ϊ��"+staffcode+"��Ա����"))
    {
    var newmemberurl='../../servlet/PageHandler?act=del&action_class=com.action.system.StaffAction&staffcode='+staffcode+'&positioncode='+'<%=positioncode%>'+'&positionname='+'<%=positionname%>'+'&orgcode='+'<%=orgcode%>'+'&orgname='+'<%=orgname%>'+'&orgclass='+'<%=orgclass%>';
    window.open(newmemberurl,"_self");
    }
}

function positioninfo()
{
    
    var staffcode="null";
����var  staffcodes= document.getElementsByName("items");
����for(i=0;i<staffcodes.length;i++)
����{
    ���� if(staffcodes[i].checked)
       ����staffcode=staffcodes[i].value;
����}
   if(staffcode!="null")
    {
    var rand=Math.floor(Math.random()*10000);
    var url="xtwh/user/user_mod.jsp?sid="+rand+"&staffcode="+staffcode+"&orgcode="+"<%=orgcode%>"+"&orgclass="+"<%=orgclass%>"+"&orgname="+"<%=orgname%>"+"&positioncode="+"<%=positioncode%>"+"&positionname="+"<%=positionname%>";
    createwindow('��λ��Ϣ',url,'400px','400px');
    //window.showModalDialog("user_mod.jsp?sid="+rand+"&staffcode="+staffcode+"&orgcode="+"<%=orgcode%>"+"&orgclass="+"<%=orgclass%>"+"&orgname="+"<%=orgname%>"+"&positioncode="+"<%=positioncode%>"+"&positionname="+"<%=positionname%>",window,"scroll=no;status=no;dialogWidth=400px;dialogHeight=400px;center=yes;help=no;");
	//window.open("user_mod.jsp?sid="+rand+"&staffcode="+staffcode , "_self");
	}
	else
	alert("����ѡ��һ��Ա��"); 
}

function logininfo()
{
    
    var staffcode="null";
����var  staffcodes= document.getElementsByName("items");
����for(i=0;i<staffcodes.length;i++)
����{
    ���� if(staffcodes[i].checked)
       ����staffcode=staffcodes[i].value;
����}
    if(staffcode!="null")
    {
    var rand=Math.floor(Math.random()*10000);
    var url="xtwh/user/login_mod.jsp?sid="+rand+"&staffcode="+staffcode;
    createwindowNoRefresh('��¼��Ϣ',url,'350px','300px');
 //   window.showModalDialog("login_mod.jsp?sid="+rand+"&staffcode="+staffcode,window,"scroll=no;status=no;dialogWidth=350px;dialogHeight=300px;center=yes;help=no;");
	//window.open("login_mod.jsp?sid="+rand+"&staffcode="+staffcode , "_self");
	}
	else
	 alert("����ѡ��һ��Ա��"); 
}

function chooseuser(orgcode)
{
  window.showModalDialog("choosemanage.jsp?orgcode="+orgcode+"&orgname="+"<%=orgname%>"+"&positioncode="+"<%=positioncode%>"+"&positionname="+"<%=positionname%>",window,"scroll=no;status=no;dialogWidth=1000px;dialogHeight=600px;center=yes;help=no;");
}


function F4()
{
	if (CheckSelect("form1"))
	{
		if (confirm("�����˵���ɾ��������ɾ���Ӳ˵����Ƿ������"))
		{
			document.all("form1").submit();
		}
	}
	else
	{
		alert ("��û��ѡ��Ҫɾ�������ݣ�");
	}
}
function F5()
{
	window.location.reload();
}
function F3()
{
	adduser();	
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="table_td_jb_iframe">&nbsp;&nbsp; 
<%if(orgclass.equals("00080001")){ %>
<a href="#" onClick="F3()">����[F3]</a>
<a href='#' onClick="logininfo()" >��¼��Ϣ</a>
<a href='#' onClick="positioninfo()" ><%=org %></a>
<a href='#' onClick="modstaff()" >������Ϣ</a>
<%}else{ %>
<a href='#' onClick="chooseuser('<%=orgcode %>')" >ѡ��Ա��</a>
<a href='#' onClick="positioninfo()" ><%=org %></a>

<%} %>
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
		tableutil.setRowValue("����","<a href=\"#\" onClick=deleteuser('@Ա������@') class=\"button4\">ɾ��</a>");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
        <td width="50%" align="left">
        ��λְ����Ϣ---<%=positionname %>
        </td>
          <td align="right">
          <%
        /// String position = "positioncode="+positioncode+"&positionname="+positionname;
      
   String para="positioncode="+positioncode+"&positionname="+positionname+"&orgcode="+orgcode+"&orgname="+orgname+"&orgclass="+orgclass;
      	out.print(PageUtil.DividePage(page_no,pagecount,"member_list.jsp",para));
       %>
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
</table>
</BODY>
</HTML>