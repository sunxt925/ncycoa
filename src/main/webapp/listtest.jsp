<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>�Ĵ��ϳ��̲�ר��</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</head>

<body>
	<h:datagrid actionUrl="dgdata.htm?fake" queryMode="group" name="lstest">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="code" title="����" query="true"></h:dgColumn>
		<h:dgColumn field="name" title="�ֵ���" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="�ֵ����"></h:dgColumn>
		<h:dgColumn field="opt" title="����"></h:dgColumn>
		<h:dgDelOpt url="dgdata.htm?del" title="ɾ��"></h:dgDelOpt>
		<h:dgOpenOpt url="dgdata.htm?open" title="��"></h:dgOpenOpt>
		<h:dgToolBar url="dgdata.htm?add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="dgdata.htm?del" funname="del" title="ɾ��"></h:dgToolBar>
	</h:datagrid>
</body>
</html>
