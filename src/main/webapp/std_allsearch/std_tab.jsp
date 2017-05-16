<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String begin="";
String end="";
String docname="";
%>
<HTML>
<HEAD>
<TITLE>南充烟草专卖局</TITLE>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>

</HEAD>
<%

%>
<script language=
                "javascript" type="text/javascript" src="../js/MyDatePicker/WdatePicker.js">  </script>
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

function search0()
{
	var begin=document.getElementById("begin").value;
	var end=document.getElementById("end").value;
	var type=document.getElementById("type").value;
	var url=null;
	if(begin==''&&end==''){
		 window.open('std_list.jsp?type='+type,"stdlist");
	}else if(begin==''){
		 url='std_list.jsp'+'?end='+end+'&type='+type;
		 window.open(url,"stdlist");
	}else if(end==''){
		 url='std_list.jsp'+'?begin='+begin+'&type='+type;
		 window.open(url,"stdlist");
	}else{
		 url="std_list.jsp?begin="+begin+"&end="+end+"&type="+type+"";
		 window.open(url,"stdlist");
		
	}
	

	
}
function sort()
{
		var begin=document.getElementById("begin").value;
	var end=document.getElementById("end").value;
	var type=document.getElementById("type").value;
	var sorttype=document.getElementById("sorttype").value;
	var url=null;
	if(begin==''&&end==''){
		 window.open('std_list.jsp?type='+type+'&sortkind='+sorttype,"stdlist");
	}else if(begin==''){
		 url='std_list.jsp'+'?end='+end+'&type='+type+'&sortkind='+sorttype;
		 window.open(url,"stdlist");
	}else if(end==''){
		 url='std_list.jsp'+'?begin='+begin+'&type='+type+'&sortkind='+sorttype;
		 window.open(url,"stdlist");
	}else{
		 url='std_list.jsp?beign='+begin+'&end='+end+'&type='+type+'&sortkind='+sorttype;
	
		 window.open(url,"stdlist");
		
	}
}
</script>
<BODY class="mainbody" onLoad="this.focus()" style="background-color:white" style="height:100%;" >

<div id="tab_menu" style="text-align: center;position: absolute; top: 0%; height:5%; width:100%; border:1px ">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="table_td_jb">
      <tr>
          <td width="100%" align="center" >
          时间段：<input name="begin" type="Wdate" class="input1" id="begin" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30" maxlength="30">
     ----- <input name="end" type="Wdate" class="input1" id="end" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="30" maxlength="30">
        <select name="type"  id="type" style="width:192px">
             <option value="new">新建标准</option>
             <option value="mod">修订标准</option>
             <option value="del">废除标准</option>
             <option value="his">历史版本</option>
          </select>
             <button  onclick="search0()">查询</button>
          </td>
      </tr>
  </table>
  </div>
  <div id="tab_menu" style="text-align: center;position: relative; top: 5%; height:100%; width:100%; border:3px ">
<iframe src="" name="stdlist" id="stdlist" width="100%" height="100%" scrolling="no" frameborder="0"></iframe><
</div>
<div><input name="sortbutton" type="button" id="sortbutton" value="" onclick="sort()" style="display:none">
<input name="sorttype" type="hidden" id="sorttype" value="">
</div>
<!--	<div id="tab_menu2" style="text-align: center;position: relative; border:1px "></div>-->
<!--	<div id="page2" style="text-align: center;position: relative;  height:31%; width:100%; border:1px "></div> -->
<!--    <div id="tab_menu" style="text-align: center;position: absolute; top: 30%; height:3%; width:100%; border:1px "></div>-->
<!--<div id="page" style="text-align: center;position: absolute; top: 34%; height:31%; width:100%; border:1px "></div>-->
<!--    <div id="tab_menu2" style="text-align: center;position: absolute; top: 65%; height:3%; width:100%; border:1px "></div>-->
<!--<div id="page2" style="text-align: center;position: absolute; top: 68%; height:31%; width:100%; border:1px "></div>-->
</BODY>
</HTML>
