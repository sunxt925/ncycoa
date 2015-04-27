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
	<h:datagrid actionUrl="pending-task.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="task_list">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="formKey" title="formKey" hidden="true"></h:dgColumn>
		<h:dgColumn field="content" width="300" sortable="false" title="任务描述"></h:dgColumn>
		<h:dgColumn field="genDate" title="发布时间" dateFormatter="yyyy-MM-dd hh:mm:ss" sortable="false" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgOpenOpt title="明细" url="pending-task.htm?view&id={id}" />
		<h:dgFunOpt title="处理" funname="handleTask({id},{formKey})" /> 
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='genDate_begin']").click(function(){WdatePicker();});
		$("input[name='genDate_end']").click(function(){WdatePicker();});
	});
	
	function handleTask(id, formkey){
		if(id == null || id == "" || id == "null"){
			createwindow("hello", formkey+"&id="+id);
		} else {
			createwindow("hello", formkey);
		}	
	}
</script>
</html>