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
	<h:datagrid actionUrl="repair_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="repairlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="projectName" title="项目名称"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgCode" title="申请部门"  query="true"></h:dgColumn>
		<h:dgColumn field="repairFree" title="维修项目预算"  query="true"></h:dgColumn>
		<h:dgColumn field="appDate" title="申请日期"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="repairContent" title="维修主要内容"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgOpinion" title="申请部门意见" ></h:dgColumn>
		<h:dgColumn field="guikouorgOpinion" title="归口部门意见部门意见" ></h:dgColumn>
		<h:dgColumn field="sanxiangOpinion" title="三项办意见" ></h:dgColumn>
		<h:dgColumn field="auditFlag" title="审核状态" replace="归口部门通过_11,未审核_0,归口部门未通过_10,三项办通过_21,三项办未通过_20" style="color:red_10,color:red_20,color:blue_11,color:blue_21,color:green_0" query="true"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgConfOpt title="通过" url="repair_management.htm?audit&auditor=office&id={id}&res=yes" message="确认审核？" />
		<h:dgConfOpt title="不通过" url="repair_management.htm?audit&auditor=office&id={id}&res=no" message="确认审核？" />
	
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='meetingBeginDate']").attr("class","easyui-datebox");
		$("input[name='meetingEndDate']").attr("class","easyui-datebox");
	});
	function ss(){
		alert("dsa");
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>