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
	<h:datagrid actionUrl="supplier.htm?evaludgdata" fit="true" queryMode="group" fitColumns="true" name="evalulist">
	    <h:dgColumn field="evaluYear" title="���" query="true" align="center" width="1"></h:dgColumn>
		<h:dgColumn field="evaluDepart" title="���۲���" query="true" align="center" width="3"></h:dgColumn>
     	<h:dgColumn field="evaluSupplier" title="���۹�Ӧ��" query="true" align="center" width="2"></h:dgColumn>
		<h:dgColumn field="score" title="�÷�" align="center" width="2"></h:dgColumn>
		<h:dgColumn field="level" title="���۵ȼ�" replace="��_0,��_1,һ��_2,������_3" query="true" align="center" width="2"></h:dgColumn>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='evaluTime_begin']").attr("class","easyui-datebox");
		$("input[name='evaluTime_end']").attr("class","easyui-datebox");
		//DataTable dt=(DataTable)datagrid-view.DataSource;
		//datagrid-view.Clear();
	});

</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>