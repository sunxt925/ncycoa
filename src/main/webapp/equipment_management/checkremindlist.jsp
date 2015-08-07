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
	<h:datagrid actionUrl="specialequipment_management.htm?dgdata_remind" fit="true" fitColumns="true" queryMode="group" name="checkplanlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="设备名称" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="设备类型" replace="电梯 _0,压力容器 _1,场内机动车 _2,机动车_3,消防设备_4,报警设备_5,巡逻设备_6,其他设备_7" query="true"></h:dgColumn>
		<h:dgColumn field="maintenDepart" title="维保单位"></h:dgColumn>
		<h:dgColumn field="checkCycle" title="检验周期（月）"></h:dgColumn>
		<h:dgColumn field="checkDate" title="当前检测日期" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="checkStatus" title="当前检测情况"></h:dgColumn>
     	<h:dgColumn field="nextCheckDate" title="下次检测日期" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="specialequipment_management.htm?update_check" icon="icon-add" funname="myedit" title="添加检查日志"></h:dgToolBar>
		<h:dgToolBar url="specialequipment_management.htm?sub" icon="icon-search" funname="myedit" title="查看检查记录"></h:dgToolBar>
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