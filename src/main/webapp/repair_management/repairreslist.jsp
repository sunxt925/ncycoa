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
	<h:datagrid actionUrl="repair_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="repairlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		
		<h:dgColumn field="apporgCode" title="ά�����뵥λ(����)"  dictionary="base_org,orgcode,orgname"  query="true"></h:dgColumn>
		<h:dgColumn field="projectName" title="ά����Ŀ����"  query="true"></h:dgColumn>
		<h:dgColumn field="repairContent" title="ά������"  query="true"></h:dgColumn>
		<h:dgColumn field="serviceProvider" title="ά����"  query="true"></h:dgColumn>
		<h:dgColumn field="handlePerson" title="������" dictionary="base_staff,staffcode,staffname"></h:dgColumn>
		<h:dgColumn field="repairFree" title="Ԥ����" ></h:dgColumn>
		<h:dgColumn field="trueFree" title="ʵ�ʽ��" ></h:dgColumn>
		<h:dgColumn field="handleDate" title="����ʱ��"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="endTime" title="���ʱ��"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
	    <h:dgColumn field="acceptor" title="������" dictionary="base_staff,staffcode,staffname"></h:dgColumn>
	    <h:dgToolBar url="repair_management.htm?updaterec" icon="icon-add" funname="update" title="���¼�¼"></h:dgToolBar>
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