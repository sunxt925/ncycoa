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
	<h:datagrid actionUrl="plan-management.htm?dgdata_dpt_review" checkbox="false" fit="true" fitColumns="true" queryMode="group" name="plan_reviewdpt_list">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="orgCode" title="编码"></h:dgColumn>
		<h:dgColumn field="orgName" title="部门名称"></h:dgColumn>
		<h:dgColumn field="statistics" title="统计"></h:dgColumn>
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
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>