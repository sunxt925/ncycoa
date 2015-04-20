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
	<h:datagrid actionUrl="contract-management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="contractlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="code" title="合同编码" query="true"></h:dgColumn>
		<h:dgColumn field="name" title="合同名称" query="true"></h:dgColumn>
		<h:dgColumn field="relevantDepartment" title="归口部门" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="合同类别" query="true"></h:dgColumn>
		<h:dgColumn field="partyA" title="甲方" query="true"></h:dgColumn>
		<h:dgColumn field="partyB" title="乙方" query="true"></h:dgColumn>
		<h:dgColumn field="contractValue" title="合同金额" query="true"></h:dgColumn>
		<h:dgColumn field="contractObject" title="合同标的" query="true"></h:dgColumn>
		<h:dgColumn field="signingDate" title="签订日期" query="true"></h:dgColumn>
		<h:dgColumn field="implementationStage" title="执行情况" query="true"></h:dgColumn>
		<h:dgColumn field="finishingDate" title="完成日期"></h:dgColumn>
		<h:dgColumn field="renewal" title="续签" query="true"></h:dgColumn>
		<h:dgColumn field="contractFilePath" title="存储路径"></h:dgColumn>
		
		<h:dgToolBar url="contract-management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="contract-management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='inputDate_begin']").attr("class","easyui-datebox");
		$("input[name='inputDate_end']").attr("class","easyui-datebox");
	});
</script>
</html>