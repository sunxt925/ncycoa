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
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
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
String doccode=request.getParameter("doccode");
if(doccode==null) doccode=""; 
if(docname==null) 
	docname="";
String getType=request.getParameter("gettype");
if(getType==null)
	getType="";
String drawupperson=request.getParameter("drawupperson");
if(drawupperson==null) 
	drawupperson="";
	DocOrgPost docorgpost=new DocOrgPost();
	int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page=((UserInfo)request.getSession().getAttribute("UserInfo")).getPerpage_full()-4;
	DataTable dt=docorgpost.getStdByClass(page_no,per_page,begin,end,docname,doccode,sortkind,drawupperson,getType);
	DataTable dtcount=docorgpost.getAllStdByClass(begin,end,docname,doccode,drawupperson,getType);
	int pagecount=0;
	if(dtcount.getRowsCount()%per_page==0)
	    pagecount=dtcount.getRowsCount()/per_page;
	else
		pagecount=(dtcount.getRowsCount()/per_page)+1;
%>
<script language=
                "javascript" type="text/javascript" src="<%=path%>/js/MyDatePicker/WdatePicker.js">  </script>
<script language="javascript" src="<%=path%>/js/public/select.js"></script>

 <script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript" src="<%=path%>/jscomponent/tools/outwindow.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/tabletoexcel.js"></script>


<script language="javascript">




function F5()
{
	window.location.reload();
}

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
        // var newurl='std_attachedfile.jsp?docNo='+docno+'&docversionname='+docversionname;
        var newurl='/ncycoa/std_search/std_attachedfile.jsp?docNo='+select_array[0];
         var name='附件列表';
         window.parent.document.getElementById("url").value=newurl;
         window.parent.document.getElementById("name").value=name;
         window.parent.document.getElementById("flag").value="std";
         window.parent.document.getElementById("hidbutton2").click();
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
  		var newurl='/ncycoa/std_search/std_aboutpost.jsp?docno='+select_array[0]+'&orgcode='+'';
		  createwindowNoButton('关联岗位',newurl,'450px','400px');
	}
}

function OpenFile(storefileno,filecontenttype)
{
    if(filecontenttype=="pdf"){
       var stdupnewurl='/ncycoa/std_search/ftpopenpdf.jsp?storefileno='+storefileno;
       window.open(stdupnewurl);
      // window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
       //window.location.reload();
   }else if(filecontenttype=="swf"){
          var stdupnewurl='/ncycoa/std_search/ftpopenswf.jsp?storefileno='+storefileno;
       window.open(stdupnewurl);
   }else{
            var stdupnewurl='/ncycoa/std_search/std_officeopen.jsp?storefileno='+storefileno;
     window.open(stdupnewurl);
     //window.showModalDialog(stdupnewurl,window,"dialogWidth=1500px;dialogHeight=800px");
     //window.location.reload();
    }
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
  		var stdupnewurl='/ncycoa/std_search/std_detail.jsp?bm='+docno;
  		createwindowNoButton('标准的信息',stdupnewurl,'450px','400px');
	}
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
	var newurl='std_list.jsp?orgcode='+''+'&positioncode='+'';
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
	var doccode=document.form1.doccode.value;
	var drawupperson=document.form1.drawupperson.value;
	var newurl='std_list.jsp?orgcode='+''+'&positioncode='+'';
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
	var newurl='std_list.jsp?orgcode='+''+'&positioncode='+'';
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
function datesort(){
	window.parent.document.getElementById("sorttype").value="date";
	var newurl='std_list.jsp?orgcode='+''+'&positioncode='+'';
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
function gettype(gettype){
	window.parent.document.getElementById("gettype").value=gettype;
	var newurl='std_list.jsp?orgcode='+''+'&positioncode='+'';
	window.parent.document.getElementById("url").value=newurl;
	window.parent.document.getElementById("sortbutton").click();
}
function toexcel(){
	saveAsExcel('tableout');
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white">
<form name="form1" id="form1" method="post">
    <table width="100%" height="5%" border="0" cellspacing="0" cellpadding="0"  class="table_td_jb">
      <tr>
          <td width="100%" colspan="3" align="center" >
          时间段：<input name="begin" type="Wdate" class="input1" id="begin" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="20" maxlength="30">
     --- <input name="end" type="Wdate" class="input1" id="end" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="20" maxlength="30">
<!--     <button onclick="search1()">查询</button>-->
     
     关键字 <input name="docname" type="text" class="input1" id="docname" onKeyDown="EnterKeyDo('')" value="" size="20" maxlength="30" >
      标准编号<input name="doccode" type="text" class="input1" id="doccode" onKeyDown="EnterKeyDo('')" value="" size="20" maxlength="30" >
  编制人<input name="drawupperson" type="text" class="input1" id="drawupperson" onKeyDown="EnterKeyDo('')" value="" size="20" maxlength="30" >
<!--     <button onclick="search2()">查询</button>-->
<button onclick="search()">查询</button>
          </td>
      </tr>
  </table>
  <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td class="table_td_jb_iframe" align="left"> 
    <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F1()" >标准信息</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F7()" >附件查看</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" onClick="F8()" >涉及岗位</a>
    </td>
        <td class="table_td_jb_iframe" align="left">&nbsp;&nbsp; 
<a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-search',plain:true" onClick="gettype('gl')" >管理标准</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-search',plain:true" onClick="gettype('gz')" >工作标准</a>
				        <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-search',plain:true" onClick="gettype('js')" >技术标准</a>
    </td>
  </tr>
</table>
 <table id="table1" width="100%" height="85%" border="0"  class="main_table_centerbg" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" valign="top" align="left">
    
    <%
		//out.print(dt.getRowsCount());
		if (dt!=null && dt.getRowsCount()>0) {
				TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
// 		tableutil.setHeadWidth("序号","5%");
// 		tableutil.setHeadWidth("标准编号","10%");
// 		tableutil.setHeadWidth("标准名称","25%");
// 		tableutil.setHeadWidth("编制日期","10%");
// 		tableutil.setHeadWidth("有/无附件","12%");
// 		tableutil.setHeadWidth("标准正文","15%");
// 		tableutil.setHeadWidth("操作","25%");
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
          <%String para="begin="+begin+"&end="+end+"&doccode="+doccode+"&docname="+docname+"&sortkind="+sortkind+"&gettype="+getType;
      	out.print(PageUtil.DividePage(page_no,pagecount,"std_list.jsp",para));
       %>
       </td>
          
        </tr>
        
      </table>
      <%}%>
       <tr>
        <td><div align="right"></div></td>
          
        <td></td>
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