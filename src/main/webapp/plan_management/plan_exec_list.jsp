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
	<h:datagrid actionUrl="plan-management.htm?dgdata_exec" fit="true" fitColumns="true" queryMode="group" name="plan_audit_list">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="计划名称" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="计划类型" replace="岗位计划_0,部门计划_1" query="true"></h:dgColumn>
		<h:dgColumn field="status" title="计划状态" 
			replace="编辑状态_0,等待审核_1,审核通过_2,审核未通过_3,正在执行_4,执行结束_5,执行中断_6" 
			style="color:blue_0,color:red_1,color:green_2,color:red_3" query="true">
		</h:dgColumn>
		<h:dgColumn field="inputUser" title="录入人员" dictionary="base_staff,staffcode,staffname" query="true"></h:dgColumn>
		<h:dgColumn field="inputDate" title="录入时间" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgConfOpt title="执行计划" url="plan-management.htm?exec&id={id}" exp="status#in#2" message="确定开始执行该计划？" />
		<h:dgOpenOpt title="运行情况" url="plan-management.htm?exec_view&id={id}" exp="status#in#4"/>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='inputDate_begin']").click(function(){WdatePicker();});
		$("input[name='inputDate_end']").click(function(){WdatePicker();});
	});
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>