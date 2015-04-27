<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitccm=Format.NullToBlank(request.getParameter("unitccm"));
//if(unitccm==null||unitccm=="")
//	unitccm=(String)request.getSession().getAttribute("orgcode");
//request.getSession().setAttribute("orgcode",unitccm);
if (unitccm.equals("")) unitccm="NC";
%>
<HTML>
<HEAD>
<TITLE>重庆市地方税务局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
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
if(docname==null) 
	docname="";
String getType=request.getParameter("gettype");
if(getType==null)
	getType="";
String drawupperson=request.getParameter("drawupperson");
if(drawupperson==null) 
	drawupperson="";
String doccode=request.getParameter("doccode");
if(doccode==null) doccode=""; 
	Org og=new Org();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=(((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-2)*4/5;
	DataTable dt=og.getStdList(page_no,per_page,unitccm,begin,end,docname,doccode,sortkind,drawupperson,getType);
	DataTable dtcount=og.getAllStdList(unitccm,begin,end,docname,doccode,drawupperson,getType);
	DataTable dtname=og.getOrgName(unitccm);
	String name=dtname.get(0).get(1).toString();
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
	String res=og.getTrack(unitccm,"");
	//request.getSession().setAttribute("pageno",page_no);
	//var belongno= document.getElementById ('items').value;
%>
<script language=
                "javascript" type="text/javascript" src="../js/MyDatePicker/WdatePicker.js">  </script>
                
 <script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="../jscomponent/tools/outwindow.js"></script>
                
<script language="javascript" src="../js/public/select.js"></script>

<script type="text/javascript" src="../js/tab/tab/tab.js"></script>


<script language="javascript">


function F2()
{	
	var check_array=document.getElementsByName("items");
	var select_array=new Array();
	var j=0;
	for(var i=0;i<check_array.length;i++)
    {
        if(check_array[i].checked==true)
        {   
        	select_array[j]=check_array[i].value;
        	j=j+1;
        }
    }
	if(select_array.length==0){
		alert('请在第二列<选择>中勾选一个标准！！！');
	}else if(select_array.length>1){
		alert('不能同时选多个标准！！！');
	}else{
		var docno=select_array[0];
	  var newurl='std/std_localup.jsp?docNo='+docno;
	  createwindowUpFile('为标准上传文件',newurl,'300px','200px');
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
	
function F6(orgcode)
{ 
    var stdupnewurl='std/std_upnew.jsp?orgcode='+orgcode;
    createwindow('上传新标准',stdupnewurl,'450px','380px');
}
	
//function F6(orgcode)
//{
//    var stdupnewurl='std_upnew.jsp?orgcode='+orgcode;
//    //window.open(stdupnewurl,"stdlist");
//    window.showModalDialog(stdupnewurl,window,"dialogWidth=500px;dialogHeight=400px");
//    window.location.reload();
//}
var index=1;
function F7()
{
	var check_array=document.getElementsByName("items");
	var select_array=new Array();
	var j=0;
	for(var i=0;i<check_array.length;i++)
    {
        if(check_array[i].checked==true)
        {   
        	select_array[j]=check_array[i].value;
        	j=j+1;
        }
    }
	if(select_array.length==0){
		alert('请在第二列<选择>中勾选一个标准！！！');
	}else if(select_array.length>1){
		alert('不能同时选多个标准！！！');
	}else{
 		 var newurl='std_attachedfile.jsp?docNo='+select_array[0];
  		var name='附件列表';
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton").click();
	}
}

function F8()
{ 
	
	
	var check_array=document.getElementsByName("items");
	var select_array=new Array();
	var j=0;
	for(var i=0;i<check_array.length;i++)
    {
        if(check_array[i].checked==true)
        {   
        	select_array[j]=check_array[i].value;
        	j=j+1;
        }
    }
	if(select_array.length==0){
		alert('请在第二列<选择>中勾选一个标准！！！');
	}else if(select_array.length>1){
		alert('不能同时选多个标准！！！');
	}else{
		var orgcode=document.getElementById("orgcode").value;
  		var newurl='std_aboutpost.jsp?docno='+select_array[0]+'&orgcode='+orgcode;
    	var name='关联岗位';
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton2").click();
	}
}

function F9()
{		
	var check_array=document.getElementsByName("items");
	var select_array=new Array();
	var j=0;
	for(var i=0;i<check_array.length;i++)
    {
        if(check_array[i].checked==true)
        {   
        	select_array[j]=check_array[i].value;
        	j=j+1;
        }
    }
	if(select_array.length==0){
		alert('请在第二列<选择>中勾选一个标准！！！');
	}else if(select_array.length>1){
		alert('不能同时选多个标准！！！');
	}else{
	  var newurl='std/std_filelist.jsp?docNo='+select_array[0];
	  createwindowNoButton('标准的文件列表',newurl,'800px','500px');
	}

//  window.showModalDialog(newurl,window,"dialogWidth=800px;dialogHeight=500px");
//  window.location.reload();
  	  //window.open(newurl,"attachposlist");
}


function F1()
{	
	
	var check_array=document.getElementsByName("items");
	var select_array=new Array();
	var j=0;
	for(var i=0;i<check_array.length;i++)
    {
        if(check_array[i].checked==true)
        {   
        	select_array[j]=check_array[i].value;
        	j=j+1;
        }
    }
	if(select_array.length==0){
		alert('请在第二列<选择>中勾选一个标准！！！');
	}else if(select_array.length>1){
		alert('不能同时选多个标准！！！');
	}else{
		var docno=select_array[0];
 		 var stdupnewurl='std/std_modlist.jsp?bm='+docno;
  		createwindow('修改标准信息',stdupnewurl,'450px','380px');
	}
}
function dele()
{
			//document.form1.docno.value=docno;
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
function search1(){
	var begin=document.form1.begin.value;
	var end=document.form1.end.value;
	var orgcode=document.form1.orgcode.value;
	var newurl='std_list.jsp?unitccm='+orgcode;
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
	var newurl='std_list.jsp?unitccm='+orgcode;
	window.parent.document.getElementById("url").value=newurl;
		window.parent.document.getElementById("begin").value="";
	window.parent.document.getElementById("end").value="";
	window.parent.document.getElementById("docname").value=docname;
	window.parent.document.getElementById("hidbutton4").click();
}
function search(){
var docname=document.form1.docname.value;
var drawupperson=document.form1.drawupperson.value;
	var begin=document.form1.begin.value;
	var end=document.form1.end.value;
	var orgcode=document.form1.orgcode.value;
	var doccode=document.form1.doccode.value;
	var newurl='std_list.jsp?unitccm='+orgcode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("begin").value=begin;
	window.parent.document.getElementById("end").value=end;
	window.parent.document.getElementById("docname").value=docname;
	window.parent.document.getElementById("doccode").value=doccode;
	window.parent.document.getElementById("drawupperson").value=drawupperson;
         window.parent.document.getElementById("flag").value="";
         window.parent.document.getElementById("hidbutton0").click();
}
function doccodesort(){
	window.parent.document.getElementById("sorttype").value="code";
	var orgcode=document.form1.orgcode.value;
	var newurl='std_list.jsp?unitccm='+orgcode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
function datesort(){
	window.parent.document.getElementById("sorttype").value="date";
	var orgcode=document.form1.orgcode.value;
	var newurl='std_list.jsp?unitccm='+orgcode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
function gettype(gettype){
	window.parent.document.getElementById("gettype").value=gettype;
	var orgcode=document.form1.orgcode.value;
	var newurl='std_list.jsp?unitccm='+orgcode;
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
</script>
<script type="text/javascript">

</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;">
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">

<table width="100%" height="100%" border="0" class="main_table_centerbg" cellpadding="0" cellspacing="0">

  <tr>
    <td colspan="3" valign="top" align="left">
        <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td class="table_td_jb">　&gt;&gt;当前单位：<%=res %></td>
      </tr>
  </table>
        <table width="100%" height="5%"  border="0" cellspacing="0" cellpadding="0"  class="table_td_jb">
      <tr valign="middle">
          <td  width="100%"  colspan="3" align="center" >
          时间段：<input name="begin" type="Wdate" class="input1" id="begin" onFocus="new WdatePicker({lang:'zh-cn'})"  value="" size="15" maxlength="30">
     -- <input name="end" type="Wdate" class="input1" id="end" onFocus="new WdatePicker({lang:'zh-cn'})"  value="" size="15" maxlength="30">
<!--     <button onClick="search1()">查询</button>-->
     
     关键字<input name="docname" type="text" class="input1" id="docname" onKeyDown="EnterKeyDo('')" value="" size="20" maxlength="30" >
   标准编号<input name="doccode" type="text" class="input1" id="doccode" onKeyDown="EnterKeyDo('')" value="" size="20" maxlength="30" >
   编制人<input name="drawupperson" type="text" class="input1" id="drawupperson" onKeyDown="EnterKeyDo('')" value="" size="20" maxlength="30" >
<!--     <button onClick="search2()">查询</button>-->
 <button onClick="search()">查询</button>
          </td>
      </tr>
     
  </table>

<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td class="table_td_jb_iframe">&nbsp;&nbsp; 
     <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-add',plain:true" onClick="F6('<%=unitccm%>')" >上传新标准</a>&nbsp;&nbsp;
	
<a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F1()" >修改</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="dele()" >删除</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F2()" >上传文件</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F7()" >附件查看</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F8()" >涉及岗位</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F9()" >文件查看</a>
    </td>
            <td class="table_td_jb_iframe" align="left">&nbsp;&nbsp; 
<!--       <a href="#" onClick="doccodesort()" >标准编号排序</a>&nbsp;&nbsp; <a href="#" onClick="datesort()" >日期排序</a> -->
<a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-search',plain:true" onClick="gettype('gl')" >管理</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-search',plain:true" onClick="gettype('gz')" >工作</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-search',plain:true" onClick="gettype('js')" >技术</a>
    </td>
  </tr>
</table>

    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
// 		tableutil.setHeadWidth("序号","5%");
// 		tableutil.setHeadWidth("标准编号","15%");
// 		tableutil.setHeadWidth("标准名称","20%");
// 		tableutil.setHeadWidth("编制日期","10%");
// 		tableutil.setHeadWidth("有/无附件","10%");
		/*
		tableutil.setRowCode("文档类名称","@文档类名称@"+",std_docclass");
		tableutil.setHeadWidth("文档版本状态","15%");
		tableutil.setRowCode("文档版本状态","@文档版本状态@"+",std_versionstatus");
		*/
// 		tableutil.setHeadWidth("操作","40%");
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
         
          <td align="right">
          <%// <td width="50%">【<a href="#" onClick="F4()">删除</a>】【<a href="#" onClick="SelectAll('form1')">全选</a>】【<a href="#" onClick="ChangeSelect('form1')">反选</a>】【<a href="#" onClick="UnSelectAll('form1')">清空</a>】</td>
          String ccm="unitccm="+unitccm+"&begin="+begin+"&end="+end+"&doccode="+doccode+"&docname="+docname+"&sortkind="+sortkind+"&gettype="+getType;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_list.jsp",ccm));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"><input name="act" type="hidden" id="act" value="del">
        <input name="orgcode" type="hidden" id="orgcode" value="<%=unitccm%>">
                    <input name="url" type="hidden" id="url" value="">
          <input name="name" type="hidden" id="name" value="">
          <input type="submit" name="Submit" value="提交" style="display:none">
          <input name="docno" type="hidden" id="docno" value=""></div></td>
        <td><input name="action_class" type="hidden" id="action_class" value="com.action.std.StdManageAction"></td>
      </tr>
      </td>
            </tr>
  <tr>
    <td width="3%" height="5" class="main_table_bottombg"><img src="../images/table_lb.jpg" width="10" height="5"></td>
    <td width="94%" height="5" class="main_table_bottombg"></td>
    <td width="3%" height="5" align="right" class="main_table_bottombg"><img src="../images/table_rb.jpg" width="10" height="5"></td>
  </tr>
  </table>
<!--        <div id="tab_menu" style="text-align: center;border:1px "></div>-->
<!--		<div id="page"  style="text-align: center;position: relative;  height:45%; width:100%; border:1px "></div>-->
<!--      <div id="tab_menu" style="text-align: center;position: absolute; top: 49.5%; height:28px; width:100%; border:1px "></div>-->
<!--<div id="page" style="text-align: center;position: absolute; top: 54%; height:45%; width:100%; border:1px "></div>-->
</form>
</BODY>
</HTML>