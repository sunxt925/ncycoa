<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bm=Format.NullToBlank(request.getParameter("unitccm"));
//System.out.println(bm+"dvxvxvxcvx");
if (bm.equals("")) bm="11511301";
String url="std_orgpostlist.jsp?unitccm="+bm;
request.setAttribute("url",url);
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<%

%>
<script language="javascript" src="../js/public/select.js"></script> 
<script type="text/javascript" src="../js/tab/jquery.js"></script>
<script type="text/javascript" src="../js/tab/tab/tab.js"></script>
<style type="text/css">
@IMPORT url("../js/tab/tab/tab.css");

h2 {
	background-color: #cccccc;
	padding: 4px;
	font-size: 18px;
	font-family: "黑体";
}
#tab_menu {
	padding: 0px;
	width: 100%;
	height: 30px ;
	overflow: hidden;
}

	#page {
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
tab.close('tab1_id_index1');
tab.close('tab1_id_index2');
tab.close('tab1_id_index3');
	tab.add( {
		id :'tab1_id_index1',
		title :"岗位列表",
		url :'<%=request.getAttribute("url")%>',
		isClosed :true
	});
});
function showModalDialogWin(url,wh,hg) {
        var obj = window.showModalDialog(url, window,"status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scrollbar=no;help: no;resizable:no;status:no;");
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
function F7(docno,docversionname)
{alert(docversionname);
}
var index1=1;
function page()
{
var url=document.form1.url.value;
var name=document.form1.name.value;
var id=index1+5;
tab.close('tab1_id_index2');
tab.close('tab1_id_index3');
		tab.add( {
		id :'tab1_id_index2',
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
			tab.close('tab1_id_index3');

		tab.add( {
		id :'tab1_id_index3',
		title :name==""?("标签页面"+(index1+1)):name,
		url :url,
		isClosed :true
	});
}
function search1()
{
	var begin=document.form1.begin.value;
	var end=document.form1.end.value;
	var urlsearch=document.form1.url.value;
	var url=null;
	if(begin==''&&end==''){
		 url=urlsearch;
	}else if(begin==''){
		 url=urlsearch+'&end='+end;
	}else if(end==''){
		 url=urlsearch+'&begin='+begin;
	}else{
		 url=urlsearch+'&begin='+begin+'&end='+end;
	}
tab.close('tab1_id_index2');
tab.close('tab1_id_index3');
		tab.add( {
		id :'tab1_id_index2',
		title :"标准列表",
		url :url,
		isClosed :true
	});
	
}
function search2()
{
	var url=null;
	var docname=document.form1.docname.value;
	var urlsearch=document.form1.url.value;
	if(docname==''){
		url=urlsearch;
	}else{
		url=urlsearch+'&docname='+docname;
	}
tab.close('tab1_id_index2');
tab.close('tab1_id_index3');
		tab.add( {
		id :'tab1_id_index2',
		title :"标准列表",
		url :url,
		isClosed :true
	});
}
function search()
{
	var begin=document.form1.begin.value;
	var end=document.form1.end.value;
	var urlsearch=document.form1.url.value;
	var url=urlsearch;
	var docname=document.form1.docname.value;
	var doccode=document.form1.doccode.value;
	if(docname==''){
		url=url;
	}else{
		url=url+'&docname='+docname;
	}
	if(begin==''){
		url=url;
	}else{
		url=url+'&begin='+begin;
	}
	 if(end==''){
		 url=url;
	}else{
		 url=url+'&end='+end;
	}
		if(doccode==''){
		url=url;
	}else{
		url=url+'&doccode='+doccode;
	}
tab.close('tab1_id_index2');
tab.close('tab1_id_index3');
		tab.add( {
		id :'tab1_id_index2',
		title :"标准列表",
		url :url,
		isClosed :true
	});
	
}
function sort()
{
	var begin=document.form1.begin.value;
	var end=document.form1.end.value;
	var urlsearch=document.form1.url.value;
	var docname=document.form1.docname.value;
	var doccode=document.form1.doccode.value;
	var sorttype=document.form1.sorttype.value;
	var url=urlsearch;
	if(docname==''){
		 url=url;
	}else {
		url=url+'&docname='+docname;
	}
	if(begin==''){
		url=url;
	}else {
		url=url+'&begin='+begin;
	}
	if(end==''){
		 url=url;
	}else{
		 url=url+'&end='+end;
	}
	if(doccode==''){
		url=url;
	}else{
		url=url+'&doccode='+doccode;
	}
	if(sorttype==''){
		url=url;
	}else{
		url=url+'&sortkind='+sorttype;
	}
	tab.close('tab1_id_index2');
	tab.close('tab1_id_index3');
		tab.add( {
		id :'tab1_id_index2',
		title :"标准列表",
		url :url,
		isClosed :true
	});
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;" >

<form name="form1" id="form1" method="post" action="../servlet/PageHandler">

     <div id="tab_menu" style="text-align: center; border:1px "></div>
<div id="page" style="text-align: center;position: relative;  height:95%; width:100%; border:1px "></div> 
<table width="100%" height="0%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="left">
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
          <input name="orgcode" type="hidden" id="orgcode" value="<%=bm%>">
          <input name="url" type="hidden" id="url" value="">
          <input name="name" type="hidden" id="name" value="">
          <input name="flag" type="hidden" id="flag" value="">
           <input name="docname" type="hidden" id="docname" value="">
           <input name="doccode" type="hidden" id="doccode" value="">
          <input name="begin" type="hidden" id="begin" value="">
          <input name="end" type="hidden" id="end" value="">
          <input name="sorttype" type="hidden" id="sorttype" value="">
          <input name="hidbutton" type="button" id="hidbutton" value="" onclick="page();" style="display:none">
          <input name="hidbutton2" type="button" id="hidbutton2" value="" onclick="page2();" style="display:none">
          <input name="hidbutton3" type="button" id="hidbutton3" value="" onclick="search1();" style="display:none">
          <input name="hidbutton4" type="button" id="hidbutton4" value="" onclick="search2();" style="display:none">
          <input name="hidbutton4" type="button" id="hidbutton0" value="" onclick="search();" style="display:none">
          <input name="sortbutton" type="button" id="sortbutton" value="" onclick="sort()" style="display:none"></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.system.SystemUnitPositionAction"></td>
      </tr>
       </td>
  </tr>
 
  </table>
<!--	<div id="tab_menu2" style="text-align: center;position: relative; border:1px "></div>-->
<!--	<div id="page2" style="text-align: center;position: relative;  height:31%; width:100%; border:1px "></div> -->
<!--    <div id="tab_menu" style="text-align: center;position: absolute; top: 30%; height:3%; width:100%; border:1px "></div>-->
<!--<div id="page" style="text-align: center;position: absolute; top: 34%; height:31%; width:100%; border:1px "></div>-->
<!--    <div id="tab_menu2" style="text-align: center;position: absolute; top: 65%; height:3%; width:100%; border:1px "></div>-->
<!--<div id="page2" style="text-align: center;position: absolute; top: 68%; height:31%; width:100%; border:1px "></div>-->
</form>
</BODY>
</HTML>
