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
	<h:datagrid actionUrl="pressurepipe_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="pressurepipe">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="diameter" title="公称直径" query="true"></h:dgColumn>
     	<h:dgColumn field="pipeMaterial" title="管道材质" query="true"></h:dgColumn>
		<h:dgColumn field="thickness" title="管道壁厚" ></h:dgColumn>
		<h:dgColumn field="length" title="管道长度" ></h:dgColumn>
		<h:dgColumn field="workPressure" title="管道工作压力"></h:dgColumn>
		<h:dgColumn field="strengthPressure" title="管道强度试验压力" ></h:dgColumn>
		<h:dgColumn field="rigorousPressure" title="管道严密性试验压力" ></h:dgColumn>
		<h:dgColumn field="workTemperature" title="管道工作温度" ></h:dgColumn>
		<h:dgColumn field="useDate" title="管道投用日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="layingMethod" title="管道敷设方式" ></h:dgColumn>
		<h:dgColumn field="anticorrosionMethod" title="管道防腐方式" ></h:dgColumn>
		<h:dgColumn field="adiabaticMethod" title="管道绝热方式 " ></h:dgColumn>
		<h:dgColumn field="designCode" title="管道设计规范 " ></h:dgColumn>
		<h:dgColumn field="manageDepart" title="监控部门" ></h:dgColumn>
		<h:dgColumn field="manager" title="监控负责人" ></h:dgColumn>
		<h:dgToolBar url="pressurepipe_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="pressurepipe_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="pressurepipe_management.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='useDate_begin']").attr("class","easyui-datebox");
		$("input[name='useDate_end']").attr("class","easyui-datebox");
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