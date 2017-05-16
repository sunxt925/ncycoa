<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//if(unitccm==null||unitccm=="")
//	unitccm=(String)request.getSession().getAttribute("orgcode");
//request.getSession().setAttribute("orgcode",unitccm);
%>
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	UserInfo UserInfo=(UserInfo)request.getSession().getAttribute("UserInfo");
	String staffcode=UserInfo.getStaffcode();
	StaffInfo staff=new StaffInfo();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-4;
	DataTable dt=staff.getOrgPostList(page_no,per_page,staffcode); 
	DataTable dtcount=staff.getAllOrgPostList(staffcode);
	int pagecount=(dtcount.getRowsCount()/per_page)+1;
	String res=staff.getStaffname(staffcode).get(0).get(0).toString();
	//request.getSession().setAttribute("pageno",page_no);
	//var belongno= document.getElementById ('items').value;
%>
<script language="javascript" src="../js/public/select.js"></script>
<script type="text/javascript" src="../js/tab/jquery.js"></script>
<script type="text/javascript" src="../js/tab/tab/tab.js"></script>
<style type="text/css">
@IMPORT url("../js/tab/tab/tab.css");

h2 {
	background-color: #cccccc;
	padding: 4px;
	font-size: 10px;
	font-family: "黑体";
}
#tab_menu {
	padding: 0px;
	width: 100%;
	height: 30px;
	overflow: hidden;
}
	#page {
	width: 100%;
	height: 31%;
	border: solid 1px #cccccc;
	}
#tab_menu2 {
	padding: 0px;
	width: 100%;
	height: 30px;
	overflow: hidden;
}

		#page2 {
	width: 100%;
	height: 31%;
	border: solid 1px #cccccc;
	}

</style>
<script language="javascript">
var tab=null;
$( function() {
	  	tab = new TabView( {
		containerId :'tab_menu',
		pageid :'page',
		cid :'tab_po',
		position :"top"
	});
			  	tab2 = new TabView( {
		containerId :'tab_menu2',
		pageid :'page2',
		cid :'tab_po2',
		position :"top"
	});
});
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scrollbar=no;help: no;resizable:no;status:no;");
}


function F5()
{
	window.location.reload();
}
var index1=1;
var index2=300;
function F8(positioncode,positionname,orgcode)
{	
        var newurl='std_list.jsp?positioncode='+positioncode+'&orgcode='+orgcode;
         var name='标准：'+positionname+'的标准';

         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton").click();
}
function page()
{
var url=document.form1.url.value;
var name=document.form1.name.value;
var id=index1+5;
		tab.add( {
		id :id,
		title :name==""?("标签页面"+(index1+1)):name,
		url :url,
		isClosed :true
	});
		index1=index1+5;
}
function page2()
{
var url=document.form1.url.value;
var name=document.form1.name.value;
var flag=document.form1.flag.value;
if(flag=='std')
{
		while(index2>300)
		{
			tab2.close(index2);
			index2=index2-5;
		}
}
var id=index2+5;
		tab2.add( {
		id :id,
		title :name==""?("标签页面"+(index2+1)):name,
		url :url,
		isClosed :true
	});
		index2=index2+5;
}
</script>
<BODY class="" onLoad="this.focus()"  style="background-color:white" style="height:100%;">
<table width="100%" height="100%" class="maintable">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
<tr>
<td colspan="3" valign="top" class="main_table_centerbgtebie" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="30px">
    <tr>
		    <td width="3%" class="main_table_topbg" ><img src="../images/table_lt.jpg" ></td>
		    <td width="94%" valign="middle" class="main_table_topbg" >首页 &gt;&gt; 标准化管理 &gt;&gt; 个人标准查询&gt;&gt; 个人岗位列表 </td>
		    <td width="3%" align="right" class="main_table_topbg" ><img src="../images/table_rt.jpg" ></td>
		    </tr>
	</table>
	</td>
  
</tr>
   
    
  
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>职工姓名：<%=res %></td>
      </tr>
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
        <td width="50%"></td>
          <td align="right">
          <%
      	out.print(PageUtil.DividePage(page_no,pagecount,"person_postlistmanage.jsp",""));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
      </td>
  </tr>
  <tr>
  <td>
  	<div align="right">
          <input name="staffname" type="hidden" id="staffname" value="<%=res%>">
          <input name="url" type="hidden" id="url" value="">
          <input name="name" type="hidden" id="name" value="">
          <input name="flag" type="hidden" id="flag" value="">
    </div>
  </td>
  </tr>
  <!-- <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr> -->

<!--   <div id="tab_menu" style="text-align: center; border:1px "></div>-->
<!--<div id="page" style="text-align: center;position: relative;  height:31%; width:100%; border:1px "></div> -->
<!--	<div id="tab_menu2" style="text-align: center;position: relative; border:1px "></div>-->
<!--	<div id="page2" style="text-align: center;position: relative;  height:31%; width:100%; border:1px "></div> -->

</form>
  </table>
</BODY>
</HTML>