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
*{font-size:12px; font-family:΢���ź�,������}
a.dgopt{
	color:#000;
	cursor:pointer;
	text-decoration:none;
	border-bottom:1px dotted #000;
}
</style>
</head>
<body>
	<h:datagrid actionUrl="plan-management.htm?dgdata_exec" fit="true" fitColumns="true" queryMode="group" name="plan_audit_list">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="�ƻ�����" query="true"></h:dgColumn>
		<h:dgColumn field="type" title="�ƻ�����" replace="��λ�ƻ�_0,���żƻ�_1" query="true"></h:dgColumn>
		<h:dgColumn field="status" title="�ƻ�״̬" 
			replace="�༭״̬_0,�ȴ����_1,���ͨ��_2,���δͨ��_3,����ִ��_4,ִ�н���_5,ִ���ж�_6" 
			style="color:blue_0,color:red_1,color:green_2,color:red_3" query="true">
		</h:dgColumn>
		<h:dgColumn field="inputUser" title="¼����Ա" dictionary="base_staff,staffcode,staffname" query="true"></h:dgColumn>
		<h:dgColumn field="inputDate" title="¼��ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgConfOpt title="ִ�мƻ�" url="plan-management.htm?exec&id={id}" exp="status#in#2" message="ȷ����ʼִ�иüƻ���" />
		<h:dgOpenOpt title="�������" url="plan-management.htm?exec_view&id={id}" exp="status#in#4"/>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='inputDate_begin']").click(function(){WdatePicker();});
		$("input[name='inputDate_end']").click(function(){WdatePicker();});
	});
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>