<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</head>

<body>
	<h:datagrid actionUrl="dgdata.htm?fake" queryMode="group" name="lstest">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="code" title="编码" query="true"></h:dgColumn>
		<h:dgColumn field="name" title="字典名" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="字典类别"></h:dgColumn>
		<h:dgColumn field="opt" title="操作"></h:dgColumn>
		<h:dgDelOpt url="dgdata.htm?del" title="删除"></h:dgDelOpt>
		<h:dgOpenOpt url="dgdata.htm?open" title="打开"></h:dgOpenOpt>
		<h:dgToolBar url="dgdata.htm?add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="dgdata.htm?del" funname="del" title="删除"></h:dgToolBar>
	</h:datagrid>
</body>
</html>
