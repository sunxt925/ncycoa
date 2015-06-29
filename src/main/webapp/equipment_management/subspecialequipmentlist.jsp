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
<input id="id" name="id"  value="${id}">
<body>
	<h:datagrid actionUrl="specialequipment_management.htm?dgdatasub&id=${id}" fit="true" fitColumns="true" queryMode="group" idField="${id}" name="checkplanlist">
		<h:dgColumn field="serialNum" title="序号" query="true" hidden="true"></h:dgColumn>
		<h:dgColumn field="equipmentNum" title="设备位号" query="true" ></h:dgColumn>
     	<h:dgColumn field="equipmentName" title="设备名称"></h:dgColumn>
		<h:dgColumn field="model" title="规格型号" query="true"></h:dgColumn>
		<h:dgColumn field="manufacturer" title="生产厂家" ></h:dgColumn>
		<h:dgColumn field="maintenCycle" title="维护周期 "></h:dgColumn>
		<h:dgColumn field="memo" title="备注" ></h:dgColumn>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>