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
	<h:datagrid actionUrl="supplier.htm?exit_dgdata" fit="true" fitColumns="true" queryMode="group" name="supplierlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="����������" query="true"></h:dgColumn>	
     	<h:dgColumn field="reason" title="�˳�ԭ��" replace="�����˳�_0,Υ���˳�_1,�����˳�_2" query="true"></h:dgColumn>
		<h:dgColumn field="exitTime" title="�˳�ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="fobbidenTime" title="��������"></h:dgColumn>
		<h:dgToolBar url="supplier.htm?mainten_exit" icon="icon-add" funname="add" title="�����˳�"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='exitTime_begin']").attr("class","easyui-datebox");
		$("input[name='exitTime_end']").attr("class","easyui-datebox");
	});
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 600, 200);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>