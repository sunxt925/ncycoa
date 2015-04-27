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
	<h:datagrid actionUrl="repair_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="repairlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="projectName" title="项目名称"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgCode" title="申请部门"  query="true"></h:dgColumn>
		<h:dgColumn field="repairFree" title="维修项目预算"  query="true"></h:dgColumn>
		<h:dgColumn field="appDate" title="申请日期"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="repairContent" title="维修主要内容"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgOpinion" title="申请部门意见"  query="true"></h:dgColumn>
		<h:dgColumn field="auditFlag" title="审核状态" replace="通过_11,未审核_0,未通过_10" style="color:red_10,color:blue_11,color:green_0" query="true"></h:dgColumn>
		<h:dgToolBar url="repair_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="repair_management.htm?update" icon="icon-add" funname="update" title="编辑"></h:dgToolBar>
		<h:dgToolBar url="repair_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgConfOpt title="申请维修" url="repair_management.htm?repairAudit&id={id}" message="确认提交申请？" />
		
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='meetingBeginDate']").attr("class","easyui-datebox");
		$("input[name='meetingEndDate']").attr("class","easyui-datebox");
	});
	function ss(){
		alert("dsa");
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>