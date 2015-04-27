<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>计划执行</title>

<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_Datatype.js"></script>

<style type="text/css">
*{font-size:12px;font-family:微软雅黑,新宋体}
</style>

</head>
<body style="overflow-x:hidden;text-align:center">

<form id="formobj" name="formobj" action="plan-management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${taskId}">
<table style="width:100%;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 审核</label></td>
		<td class="value">
		<input type="radio" name="step_type" checked value="0">通过
		<input type="radio" name="step_type" value="1">不通过
		</td>
	</tr>
</table>
</form>

</body>
</html>