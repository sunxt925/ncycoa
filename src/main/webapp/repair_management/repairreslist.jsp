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
		
		<h:dgColumn field="apporgCode" title="维修申请单位(部门)"  dictionary="base_org,orgcode,orgname"  query="true"></h:dgColumn>
		<h:dgColumn field="projectName" title="维修项目名称"  query="true"></h:dgColumn>
		<h:dgColumn field="repairContent" title="维修内容"  query="true"></h:dgColumn>
		<h:dgColumn field="serviceProvider" title="维修商"  query="true"></h:dgColumn>
		<h:dgColumn field="handlePerson" title="经办人" dictionary="base_staff,staffcode,staffname"></h:dgColumn>
		<h:dgColumn field="repairFree" title="预算金额" ></h:dgColumn>
		<h:dgColumn field="trueFree" title="实际金额" ></h:dgColumn>
		<h:dgColumn field="handleDate" title="审批时间"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="endTime" title="完成时间"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
	    <h:dgColumn field="acceptor" title="验收人" dictionary="base_staff,staffcode,staffname"></h:dgColumn>
	    <h:dgToolBar url="repair_management.htm?updaterec" icon="icon-add" funname="update" title="更新记录"></h:dgToolBar>
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