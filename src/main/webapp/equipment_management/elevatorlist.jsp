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
	<h:datagrid actionUrl="elevator_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="elevatorlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="model" title="型号" query="true"></h:dgColumn>
     	<h:dgColumn field="equipmentNum" title="企业设备编号" query="true"></h:dgColumn>
		<h:dgColumn field="ratedLoad" title="额定载重" ></h:dgColumn>
		<h:dgColumn field="ratedPassenger" title="额定载客" ></h:dgColumn>
		<h:dgColumn field="ratedSpeed" title="额定速度"></h:dgColumn>
		<h:dgColumn field="frequency" title="使用频率" ></h:dgColumn>
		<h:dgColumn field="piles" title="层数" ></h:dgColumn>
		<h:dgColumn field="stations" title="站数门数" ></h:dgColumn>
		<h:dgColumn field="factoryDate" title="出厂日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="checkDATE" title="检验日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope" ></h:dgColumn>
		<h:dgColumn field="status" title="使用状况" ></h:dgColumn>
		<h:dgToolBar url="elevator_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="elevator_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="elevator_management.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='factoryDate_begin']").attr("class","easyui-datebox");
		$("input[name='factoryDate_end']").attr("class","easyui-datebox");
		$("input[name='checkDate_begin']").attr("class","easyui-datebox");
		$("input[name='checkDate_end']").attr("class","easyui-datebox");
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