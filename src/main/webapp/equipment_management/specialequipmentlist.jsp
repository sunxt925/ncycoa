<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

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
</head>
<body>
	<h:datagrid actionUrl="checkplan_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="checkplanlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="设备名称" query="true"></h:dgColumn>
		<h:dgColumn field="model" title="设备型号" query="true"></h:dgColumn>
		<h:dgColumn field="manufacturer" title="生产厂家" query="true"></h:dgColumn>
		<h:dgColumn field="serialNum" title="出场编号" query="true"></h:dgColumn>
		<h:dgColumn field="weight" title="设备重量" query="true"></h:dgColumn>
		<h:dgColumn field="installPosition" title="安装位置" query="true"></h:dgColumn>
		<h:dgColumn field="fileNum" title="档案编号" query="true"></h:dgColumn>
		<h:dgColumn field="size" title="外形尺寸" query="true"></h:dgColumn>
		<h:dgColumn field="madeTime" title="制造日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
     	<h:dgColumn field="useTime" title="启用时间" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
  		<h:dgToolBar url="checkplan_management.htm?add" icon="icon-add" funname="add" title="新增特种设备"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?del" icon="icon-remove" funname="del" title="删除特种设备"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?update" icon="icon-reload" funname="myedit" title="更新特种设备"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?addsub" icon="icon-add" funname="add" title="添加附属设备"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?sub" icon="icon-search" funname="add" title="查看附属设备"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='madeTime_begin']").attr("class","easyui-datebox");
		$("input[name='madeTime_end']").attr("class","easyui-datebox");
	});
	
	
	function myedit(title, actionUrl, gname, width, height) {
		gridname=gname;
		var rows;
		try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
		try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
		
		if (!rows || rows.length==0) {
			tip('请先选择一条记录');
			return;
		}
		if (rows.length > 1) {
			tip('不能同时对多条记录编辑，请勾选一条记录');
			return;
		}
	
		if(actionUrl.indexOf("?") >= 0) {
			actionUrl += '&id='+ rows[0].id;
		} else {
			actionUrl += '?id='+ rows[0].id;
		}
		createwindow(title, actionUrl, width, height);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>