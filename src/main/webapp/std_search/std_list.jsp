<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,com.entity.std.*" errorPage="" %>
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
<TITLE>南充市烟草局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
String sortkind=request.getParameter("sortkind");
if(sortkind==null) sortkind="";
String begin=request.getParameter("begin");
if(begin==null) 
	begin="";
String end=request.getParameter("end");
if(end==null) 
	end="";
String docname=request.getParameter("docname");
String doccode=request.getParameter("doccode");
if(doccode==null) doccode=""; 
if(docname==null) 
	docname="";
	String orgcode=request.getParameter("orgcode");
	String positioncode=request.getParameter("positioncode");
	Position position=new Position(positioncode);
	String positionname=position.getPositionName();
	DocOrgPost docorgpost=new DocOrgPost();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-4;
	DataTable dt=docorgpost.getStdList(page_no,per_page,orgcode,positioncode,begin,end,docname,doccode,sortkind);
	DataTable dtcount=docorgpost.getAllStdList(orgcode,positioncode,begin,end,docname,doccode);
	int pagecount=(dtcount.getRowsCount()/per_page)+1;
	//request.getSession().setAttribute("pageno",page_no);
	//var belongno= document.getElementById ('items').value;
%>
<script language=
                "javascript" type="text/javascript" src="../js/MyDatePicker/WdatePicker.js">  </script>
<script language="javascript" src="../js/public/select.js"></script>

 <script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>


<script language="javascript">

function F2(docno)
{
	  var newurl='std_localup.jsp?docNo='+docno;
  	  //window.open(newurl,"stdlist");
  	  window.showModalDialog(newurl,"dialogWidth=300px;dialogHeight=300px");
      window.location.reload();
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

function F6(orgcode)
{
  var stdupnewurl='std_upnew.jsp?orgcode='+orgcode;
  //window.open(stdupnewurl,"stdlist");
  window.showModalDialog(stdupnewurl,"dialogWidth=500px;dialogHeight=1000px");
  window.location.reload();
}
function F7(docno,docversionname)
{ 
         var newurl='std_attachedfile.jsp?docNo='+docno+'&docversionname='+docversionname;
         var name='文档'+docversionname+'的附件:';
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="std";
         window.parent.document.getElementById("hidbutton2").click();

}


function F9(docno,docversionname)
{
	  var newurl='std_filelist.jsp?docNo='+docno;
	  var name='文档'+docversionname+'的文件:';
	  window.parent.document.getElementById("url").value=newurl;
      window.parent.document.getElementById("name").value=name;
      window.parent.document.getElementById("hidbutton").click();
  	  //window.open(newurl,"attachposlist");
}
function OpenFile(storefileno,filecontenttype)
{
    if(filecontenttype=="pdf"){
       var stdupnewurl='ftpopenpdf.jsp?storefileno='+storefileno;
       window.open(stdupnewurl);
      // window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
       //window.location.reload();
   }else if(filecontenttype=="swf"){
          var stdupnewurl='ftpopenswf.jsp?storefileno='+storefileno;
       window.open(stdupnewurl);
   }else{
            var stdupnewurl='std_officeopen.jsp?storefileno='+storefileno;
     window.open(stdupnewurl);
     //window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
     //window.location.reload();
    }
}
function F1(docno)
{
  var stdupnewurl='std_search/std_detail.jsp?bm='+docno;
  createwindowNoButton('标准的信息',stdupnewurl,'450px','400px');
  //window.open(stdupnewurl,"stdlist");
//  window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=600px");
//  window.location.reload();

}
function search1(){
	var begin=document.form1.begin.value;
	var end=document.form1.end.value;
	var orgcode=document.form1.orgcode.value;
	var positioncode=document.form1.positioncode.value;
	var newurl='std_list.jsp?orgcode='+orgcode+'&positioncode='+positioncode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("begin").value=begin;
	window.parent.document.getElementById("end").value=end;
	window.parent.document.getElementById("docname").value="";
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton3").click();
}
function search2(){
	var docname=document.form1.docname.value;
		var orgcode=document.form1.orgcode.value;
		var positioncode=document.form1.positioncode.value;
	var newurl='std_list.jsp?orgcode='+orgcode+'&positioncode='+positioncode;
	window.parent.document.getElementById("url").value=newurl;
		window.parent.document.getElementById("begin").value="";
	window.parent.document.getElementById("end").value="";
	window.parent.document.getElementById("docname").value=docname;
	window.parent.document.getElementById("hidbutton4").click();
}
function search(){
var docname=document.form1.docname.value;
	var begin=document.form1.begin.value;
	var end=document.form1.end.value;
	var orgcode=document.form1.orgcode.value;
	var positioncode=document.form1.positioncode.value;
	var doccode=document.form1.doccode.value;
	var newurl='std_list.jsp?orgcode='+orgcode+'&positioncode='+positioncode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("begin").value=begin;
	window.parent.document.getElementById("end").value=end;
	window.parent.document.getElementById("docname").value=docname;
	window.parent.document.getElementById("doccode").value=doccode;
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton0").click();
}
function doccodesort(){
	window.parent.document.getElementById("sorttype").value="code";
	var orgcode=document.form1.orgcode.value;
	var positioncode=document.form1.positioncode.value;
	var newurl='std_list.jsp?orgcode='+orgcode+'&positioncode='+positioncode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
function datesort(){
	window.parent.document.getElementById("sorttype").value="date";
	var orgcode=document.form1.orgcode.value;
	var positioncode=document.form1.positioncode.value;
	var newurl='std_list.jsp?orgcode='+orgcode+'&positioncode='+positioncode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
    <table width="100%" height="5%" border="0" cellspacing="0" cellpadding="0"  class="table_td_jb">
    <tr><td colspan="3" width="100%" height=30>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">　&gt;&gt;岗位：<%=positionname %>的标准列表</td>
      </tr>
  </table>
  </td></tr>
      <tr>
      <td width="5%"></td>
          <td width="60%" colspan="3" valign="middle" >
          时间段：<input name="begin" type="Wdate" class="input1" id="begin" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30" maxlength="30">
     --- <input name="end" type="Wdate" class="input1" id="end" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30" maxlength="30">
<!--     <button onclick="search1()">查询</button>-->
     
     关键字 <input name="docname" type="text" class="input1" id="docname" onKeyDown="EnterKeyDo('')" value="" size="30" maxlength="30" >
      标准编号<input name="doccode" type="text" class="input1" id="doccode" onKeyDown="EnterKeyDo('')" value="" size="30" maxlength="30" >
<!--     <button onclick="search2()">查询</button>-->
<button onclick="search()">查询</button>
          </td>
          <td width="5%"></td>
      </tr>
  </table>
  <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td class="table_td_jb_iframe">&nbsp;&nbsp; 
      <a href="#" onClick="doccodesort()" >标准编号排序</a>&nbsp;&nbsp; <a href="#" onClick="datesort()" >日期排序</a>
    </td>
  </tr>
</table>
 <table width="100%" height="85%" border="0"  class="main_table_centerbg" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" align="left">
    
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
				TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
		tableutil.setHeadWidth("序号","5%");
		tableutil.setHeadWidth("标准编号","10%");
		tableutil.setHeadWidth("标准名称","25%");
		tableutil.setHeadWidth("编制日期","10%");
		tableutil.setHeadWidth("有/无附件","12%");
		tableutil.setHeadWidth("标准正文","15%");
		tableutil.setHeadWidth("操作","25%");
		tableutil.setRowreadLink("标准正文","@标准正文@");
		tableutil.setHaveAttach("有无附件","@有无附件@");
		tableutil.setDisplayCol("no");
				tableutil.setSort1("标准编号");
		tableutil.setSortlink1("<a href='#' onClick='doccodesort()' >标准编号</a>");
		tableutil.setSort2("编制日期");
		tableutil.setSortlink2("<a href='#' onClick='datesort()' >编制日期</a>");
		out.print(tableutil.DrawTable());
	%>
      
      <table width="100%" border="0" cellpadding="3" cellspacing="0">
        <tr>
          <td width="50%"></td>
          <td align="right">
          <%String para="orgcode="+orgcode+"&positioncode="+positioncode+"&begin="+begin+"&end="+end+"&doccode="+doccode+"&docname="+docname+"&sortkind="+sortkind;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_list.jsp",para));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
        <input name="orgcode" type="hidden" id="orgcode" value="<%=orgcode%>">
        <input name="positioncode" type="hidden" id="positioncode" value="<%=positioncode%>"></div></td>
          
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdManageAction"></td>
      </tr>
        <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>

  </table>
</form>
</BODY>
</HTML>