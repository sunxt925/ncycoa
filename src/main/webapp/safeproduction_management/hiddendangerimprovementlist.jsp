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
	<h:datagrid actionUrl="hiddendangerimprovement_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="hiddendangerimprovementlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="number" title="序号" query="true"></h:dgColumn>
		<h:dgColumn field="place" title="地点" ></h:dgColumn>
		<h:dgColumn field="date" title="日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
     	<h:dgColumn field="dangerContent" title="隐患内容"></h:dgColumn>
		<h:dgColumn field="dangerType" title="隐患分类" query="true"></h:dgColumn>
		<h:dgColumn field="dangerLevel" title="隐患分级" replace="一般_0,重点 _1"></h:dgColumn>
		<h:dgColumn field="improveStatus" title="整改措施及完成情况" ></h:dgColumn>
		<h:dgColumn field="improveDepart" title="整改责任单位/部门"></h:dgColumn>
		<h:dgColumn field="improveChecker" title="整改验证人"></h:dgColumn>
		<h:dgColumn field="memo" title="备注" ></h:dgColumn>
		<h:dgToolBar url="hiddendangerimprovement_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="hiddendangerimprovement_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="hiddendangerimprovement_management.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='date_begin']").attr("class","easyui-datebox");
		$("input[name='date_end']").attr("class","easyui-datebox");
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