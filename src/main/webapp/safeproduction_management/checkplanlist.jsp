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
		<h:dgColumn field="type" title="检查类别" query="true"></h:dgColumn>
		<h:dgColumn field="checkTime" title="检查时间" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
     	<h:dgColumn field="checker" title="检查人"></h:dgColumn>
		<h:dgColumn field="checkedDepart" title="受检单位/部门" query="true"></h:dgColumn>
		<h:dgColumn field="keyContent" title="检查重点内容" ></h:dgColumn>
		<h:dgColumn field="checkMethod" title="检查方法"></h:dgColumn>
		<h:dgColumn field="memo" title="备注" ></h:dgColumn>
		<h:dgToolBar url="checkplan_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="checkplan_management.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='checkTime_begin']").attr("class","easyui-datebox");
		$("input[name='checkTime_end']").attr("class","easyui-datebox");
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