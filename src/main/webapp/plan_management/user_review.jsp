<%@page contentType="text/html;charset=gb2312" language="java" errorPage=""%>
<%@ page import="edu.cqu.ncycoa.plan.PlanHelper"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body class="easyui-layout">
<!-- 上 width:223px;padding:0 3px-->
<div data-options="region:'north',border:false">
<table>
	<tr>
	<td>
	<div>
		<label for="yearsel">年度: </label>
		<input id='yearsel' class="easyui-combobox" style="width:80px;" data-options="data:<%=PlanHelper.getYearJson() %>,valueField:'value',textField:'text',onSelect:onYearChanged" />
		<script type="text/javascript">
		function onYearChanged(record){
			var period = $('#periodsel').combobox('getValue');
			if(period){
				$("#userdg").attr('src','plan-management.htm?users&month=' + period +'&year=' + record.value);
			}
		}
		</script>
	</div>
	</td>
	
	<td>
	<div>
		<label for="periodsel">周期: </label>
		<input id='periodsel' class="easyui-combobox" style="width:80px;" data-options="data:<%=PlanHelper.getPeriodJson() %>,valueField:'value',textField:'text',onSelect:onPeriodChanged" />
		<script type="text/javascript">
		function onPeriodChanged(record){
			var year = $('#yearsel').combobox('getValue');
			if(year){
				$("#userdg").attr('src','plan-management.htm?users&month=' + record.value + '&year=' + year);
			}
		}
		</script>
	</div>
	</td>
	</tr>
</table>
</div>
<!-- 中 -->
<div data-options="region:'center',border:false">
<iframe id="userdg" frameborder="0" scrolling="no" style="width:100%;height:99.5%;border:0px none;"></iframe>
</div>
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript">
$(function(){
	var period = $('#periodsel').combobox('getValue');
	var year = $('#yearsel').combobox('getValue');
	$("#userdg").attr('src','plan-management.htm?users&month=' + period +'&year=' + year);
});
</script>
</body>
</html>
