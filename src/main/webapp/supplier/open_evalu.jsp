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
	<h:datagrid actionUrl="supplier.htm?evaludgdata" fit="true" fitColumns="true" queryMode="group" name="evalulist">
		<h:dgToolBar onclick="success()" url="supplier.htm?openevalu" icon="icon-add" title="发起评价工作"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?add_index" icon="icon-add" funname="add" title="添加指标定义"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='evaluTime_begin']").attr("class","easyui-datebox");
		$("input[name='evaluTime_end']").attr("class","easyui-datebox");
		//DataTable dt=(DataTable)datagrid-view.DataSource;
		//datagrid-view.Clear();
	});

	function success(){
		alert("评价发起成功");
		self.location.href="supplier.htm?openevalu" ;
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>