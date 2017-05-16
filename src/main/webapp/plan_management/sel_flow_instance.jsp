<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<input id="planid" name="planid" type="hidden" value="${planid}">
	<table class="easyui-datagrid" style="width:700px" data-options="singleSelect:true,collapsible:true,fitColumns:true" >
    <thead>
		<tr>
			<th data-options="field:'name'">流程实例名称</th>
			<th data-options="field:'processDefinitionId'">流程Id</th>
			<th data-options="field:'processDefinitionName'">流程名称</th>
		</tr>
    </thead>
    <tbody>
    <c:forEach items="${instances}" var="aInstance" varStatus="instance_status">
		<tr>
			<td>${aInstance.name}</td><td>${aInstance.processDefinitionId}</td><td>${aInstance.processDefinitionName}</td>
		</tr>
	</c:forEach>
    </tbody>
</table>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
</script>
</html>