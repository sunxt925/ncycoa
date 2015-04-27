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
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>
<body>
	<h:datagrid actionUrl="supplier.htm?" fit="true" fitColumns="true" queryMode="group" name="evalulist">
		<h:dgToolBar url="supplier.htm?evalu_addi" icon="icon-add" funname="add" title="�������"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?show" icon="icon-reload" funname="add" title="��ʾ���ۻ���"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?add_index" icon="icon-add" funname="add" title="���ָ�궨��"></h:dgToolBar>
		<%if(flag=="true") {%>
		<h:dgToolBar url="supplier.htm?closeevalu" icon="icon-no" onclick="success()" title="�ر����۹���" ></h:dgToolBar>
		<% }%>
	</h:datagrid>
	
</body>

<script type="text/javascript">
	$(document).ready(function(){
		//DataTable dt=(DataTable)datagrid-view.DataSource;
		//datagrid-view.Clear();
	});
	function success(){
		alert("�ѹر����۹���!");
		self.location.href="supplier.htm?closeevalu";
	}

</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>