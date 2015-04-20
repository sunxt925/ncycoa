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
		<h:dgColumn field="projectName" title="��Ŀ����"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgCode" title="���벿��"  query="true"></h:dgColumn>
		<h:dgColumn field="repairFree" title="ά����ĿԤ��"  query="true"></h:dgColumn>
		<h:dgColumn field="appDate" title="��������"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="repairContent" title="ά����Ҫ����"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgOpinion" title="���벿�����" ></h:dgColumn>
		<h:dgColumn field="guikouorgOpinion" title="��ڲ�������������" ></h:dgColumn>
		<h:dgColumn field="sanxiangOpinion" title="��������" ></h:dgColumn>
		<h:dgColumn field="auditFlag" title="���״̬" replace="��ڲ���ͨ��_11,δ���_0,��ڲ���δͨ��_10,�����ͨ��_21,�����δͨ��_20" style="color:red_10,color:red_20,color:blue_11,color:blue_21,color:green_0" query="true"></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgConfOpt title="ͨ��" url="repair_management.htm?audit&auditor=office&id={id}&res=yes" message="ȷ����ˣ�" />
		<h:dgConfOpt title="��ͨ��" url="repair_management.htm?audit&auditor=office&id={id}&res=no" message="ȷ����ˣ�" />
	
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