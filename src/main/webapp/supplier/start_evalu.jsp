<%@page import="edu.cqu.ncycoa.dao.SupplierDao"%>
<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<%
  String flag=request.getAttribute("isFlag").toString();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
<style type="text/css"> 
table.table1{
    font-family: "Trebuchet MS", sans-serif;
    font-size: 16px;
    font-weight: bold;
    line-height: 0.6em;
    font-style: normal;
    border-collapse:separate;
}
.table1 thead th{
    padding:8px;
    text-align:center;
    color:#fff;
    text-shadow:1px 1px 1px #568F23;
    border:1px solid #93CE37;
    border-bottom:3px solid #9ED929;
    background-color:#9DD929;
    background:-webkit-gradient(
        linear,
        left bottom,
        left top,
        color-stop(0.02, rgb(123,192,67)),
        color-stop(0.51, rgb(139,198,66)),
        color-stop(0.87, rgb(158,217,41))
        );
    background: -moz-linear-gradient(
        center bottom,
        rgb(123,192,67) 2%,
        rgb(139,198,66) 51%,
        rgb(158,217,41) 87%
        );
    -webkit-border-top-left-radius:5px;
    -webkit-border-top-right-radius:5px;
    -moz-border-radius:5px 5px 0px 0px;
    border-top-left-radius:5px;
    border-top-right-radius:5px;
}
.table1 thead th:empty{
    background:transparent;
    border:none;
}
.table1 tbody th{
    color:#fff;
    text-shadow:1px 1px 1px #568F23;
    background-color:#9DD929;
    border:1px solid #93CE37;
    border-right:3px solid #9ED929;
    padding:0px 7px;
    background:-webkit-gradient(
        linear,
        left bottom,
        right top,
        color-stop(0.02, rgb(158,217,41)),
        color-stop(0.51, rgb(139,198,66)),
        color-stop(0.87, rgb(123,192,67))
        );
    background: -moz-linear-gradient(
        left bottom,
        rgb(158,217,41) 2%,
        rgb(139,198,66) 51%,
        rgb(123,192,67) 87%
        );
    -moz-border-radius:5px 0px 0px 5px;
    -webkit-border-top-left-radius:5px;
    -webkit-border-bottom-left-radius:5px;
    border-top-left-radius:5px;
    border-bottom-left-radius:5px;
}
.table1 tbody td{
    padding:6px;
    text-align:center;
    background-color:#DEF3CA;
    border: 2px solid #E7EFE0;
    -moz-border-radius:2px;
    -webkit-border-radius:2px;
    border-radius:2px;
    color:#666;
    text-shadow:1px 1px 1px #fff;
}
</style>
</head>
<body>
	<h:datagrid actionUrl="supplier.htm?" fit="true" fitColumns="true" queryMode="group" name="evalulist">
		<!-- url="supplier.htm?evalu_addi" icon="icon-add" funname="add" title="添加评价"-->
		<%if(flag=="true") {%>
		<h:dgToolBar url="supplier.htm?closeevalu" icon="icon-no" onclick="success()" title="关闭评价工作" ></h:dgToolBar>
		<% }%>
	</h:datagrid>
	<span>选择部门:</span>
		<!-- 
<select class="easyui-combobox" id="evaluDepart" name="evaluDepart" style="width:156px;">
		<%
		//String getDepart=SupplierDao.getDepart();
		//out.write(getDepart);
		%>
	
</select>
 -->
<%
	String depart=SupplierDao.getOneDepart();
    String html="<input id='evaluDepart' name='evaluDepart' style='width: 250px' disabled='disabled' value='"+depart+"'>";
	out.write(html);
%>
<span>&nbsp;&nbsp;评价年度:</span>
<input id="evaluYear" name="evaluYear" style="width: 50px" disabled="disabled">
	<div>
	 <table style="width:600px;border-spacing:1px;" class="table1" id="status">
	  <thead>
        <tr>
            <th field="name1" width="50">供应商名称</th>
            <th field="name2" width="50">是否评价</th>
            <th field="name3" width="50">评价得分</th>
            <th field="name4" width="50">操作</th>
        </tr>                          
    </thead>                           
    <tbody>
	<%
		String status=SupplierDao.getSuppliersEvaluStatus();
		out.write(status);
	%>
	</tbody>
	 </table>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		 var d = new Date();
	     var nowYear = d.getFullYear();
	     $("#evaluYear").attr("value",nowYear);
		//DataTable dt=(DataTable)datagrid-view.DataSource;
		//datagrid-view.Clear();
	});
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 620, height);
	}
	function makeTable(id){
		var url="supplier.htm?evalu_addi";
		if(url.indexOf("?") >= 0) {
			url += '&id='+id;
		} else {
			url += '?id='+id;
		}
		createwindow("添加评价",url, 620, 500);
	}
	function makeSeeTable(id){
		var url="supplier.htm?evalu_see";
		if(url.indexOf("?") >= 0) {
			url += '&id='+id;
		} else {
			url += '?id='+id;
		}
		createwindow("添加评价",url, 620, 500);
	}
	
	function success(){
		alert("已关闭评价工作!");
		self.location.href="supplier.htm?closeevalu";
	}
	

</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>