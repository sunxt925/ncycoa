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
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>
<input id="id" name="id"  value="${id}">
<body>
	<h:datagrid actionUrl="specialequipment_management.htm?dgdatasub&id=${id}" fit="true" fitColumns="true" queryMode="group" idField="${id}" name="checkplanlist">
		<h:dgColumn field="serialNum" title="���" query="true" hidden="true"></h:dgColumn>
		<h:dgColumn field="equipmentNum" title="�豸λ��" query="true" ></h:dgColumn>
     	<h:dgColumn field="equipmentName" title="�豸����"></h:dgColumn>
		<h:dgColumn field="model" title="����ͺ�" query="true"></h:dgColumn>
		<h:dgColumn field="manufacturer" title="��������" ></h:dgColumn>
		<h:dgColumn field="maintenCycle" title="ά������ "></h:dgColumn>
		<h:dgColumn field="memo" title="��ע" ></h:dgColumn>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>