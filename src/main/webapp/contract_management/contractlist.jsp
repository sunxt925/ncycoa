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
<body>
	<h:datagrid actionUrl="contract-management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="contractlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="code" title="��ͬ����" query="true"></h:dgColumn>
		<h:dgColumn field="name" title="��ͬ����" query="true"></h:dgColumn>
		<h:dgColumn field="relevantDepartment" title="��ڲ���" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="��ͬ���" query="true"></h:dgColumn>
		<h:dgColumn field="partyA" title="�׷�" query="true"></h:dgColumn>
		<h:dgColumn field="partyB" title="�ҷ�" query="true"></h:dgColumn>
		<h:dgColumn field="contractValue" title="��ͬ���" query="true"></h:dgColumn>
		<h:dgColumn field="contractObject" title="��ͬ���" query="true"></h:dgColumn>
		<h:dgColumn field="signingDate" title="ǩ������" query="true"></h:dgColumn>
		<h:dgColumn field="implementationStage" title="ִ�����" query="true"></h:dgColumn>
		<h:dgColumn field="finishingDate" title="�������"></h:dgColumn>
		<h:dgColumn field="renewal" title="��ǩ" query="true"></h:dgColumn>
		<h:dgColumn field="contractFilePath" title="�洢·��"></h:dgColumn>
		
		<h:dgToolBar url="contract-management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="contract-management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
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