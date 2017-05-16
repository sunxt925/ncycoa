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
<script type="text/javascript" src="js/MyDatePicker/WdatePicker.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
a.dgopt{
	color:#000;
	cursor:pointer;
	text-decoration:none;
	border-bottom:1px dotted #000;
}
</style>
</head>
<body>
	<h:datagrid actionUrl="plan-management.htm?dgdata_review" checkbox="false" fit="true" fitColumns="true" queryMode="group" name="plan_review_list">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="planId" title="planId" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="计划名称" query="true"></h:dgColumn>
		<h:dgColumn field="summary" title="概述"></h:dgColumn>
		<h:dgColumn field="participants" title="参与人"></h:dgColumn>
		<h:dgColumn field="planBeginDate" title="开始时间" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="planEndDate" title="结束时间" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgToolBar icon="icon-search" onclick="myedit_1();" title="详细信息"></h:dgToolBar>
		<h:dgToolBar icon="icon-search" onclick="myreview_1();" title="执行情况"></h:dgToolBar>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgConfOpt url="plan-management.htm?review_post&id={id}&result=10" title="好" message="确定对该计划进行评价？评价后结果将不能修改"></h:dgConfOpt>
		<h:dgConfOpt url="plan-management.htm?review_post&id={id}&result=0" title="中" message="确定对该计划进行评价？评价后结果将不能修改"></h:dgConfOpt>
		<h:dgConfOpt url="plan-management.htm?review_post&id={id}&result=-10" title="差" message="确定对该计划进行评价？评价后结果将不能修改"></h:dgConfOpt>
	</h:datagrid>
</body>

<script type="text/javascript">
function myedit_1(){
	var rows;
	try{rows=$('#plan_review_list').datagrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('请选择一条记录');
		return;
	}
	if (rows.length > 1) {
		tip('请选择一条记录');
		return;
	}
	
	var url = "plan-management.htm?update&id=" + rows[0].planId;
	openwindow("查看计划", url,"plan_review_list", 800, 600);
}

function myreview_1() {
	var rows;
	try{rows=$('#plan_review_list').datagrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('请选择一条记录');
		return;
	}
	if (rows.length > 1) {
		tip('请选择一条记录');
		return;
	}

	var url = "plan-management.htm?review&id=" + rows[0].planId;
	openwindow("评审", url,"plan_review_list", 800, 600);
}

$(document).ready(function(){
	$("input[name='planBeginDate_begin']").click(function(){WdatePicker();});
	$("input[name='planBeginDate_end']").click(function(){WdatePicker();});
	$("input[name='planEndDate_begin']").click(function(){WdatePicker();});
	$("input[name='planEndDate_end']").click(function(){WdatePicker();});
});
	

</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>