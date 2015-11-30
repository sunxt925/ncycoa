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
	<h:datagrid actionUrl="plan-management.htm?dgdata_user_review&y=${param.year}&m=${param.month}" checkbox="false" fit="true" fitColumns="true" queryMode="group" name="plan_review_list">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="participantCode" title="��Ա����"></h:dgColumn>
		<h:dgColumn field="participantName" title="��Ա����"></h:dgColumn>
		<h:dgColumn field="statistics" title="ͳ��"></h:dgColumn>
		<h:dgColumn field="overDeadTimeCounts" title="��ʱ"></h:dgColumn>
		<h:dgColumn field="noOverDeadTimeCounts" title="����"></h:dgColumn>
		<h:dgColumn field="result" title="����"></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgConfOpt url="plan-management.htm?review_post&id={id}&result=10" title="��" message="ȷ���Ըüƻ��������ۣ����ۺ����������޸�" exp="result#empty#true"></h:dgConfOpt>
		<h:dgConfOpt url="plan-management.htm?review_post&id={id}&result=0" title="��" message="ȷ���Ըüƻ��������ۣ����ۺ����������޸�" exp="result#empty#true"></h:dgConfOpt>
		<h:dgConfOpt url="plan-management.htm?review_post&id={id}&result=-10" title="��" message="ȷ���Ըüƻ��������ۣ����ۺ����������޸�" exp="result#empty#true"></h:dgConfOpt>
	</h:datagrid>
</body>

<script type="text/javascript">
function myedit_1(){
	var rows;
	try{rows=$('#plan_review_list').datagrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('��ѡ��һ����¼');
		return;
	}
	if (rows.length > 1) {
		tip('��ѡ��һ����¼');
		return;
	}
	
	var url = "plan-management.htm?update&id=" + rows[0].planId;
	openwindow("�鿴�ƻ�", url,"plan_review_list", 800, 600);
}

function myreview_1() {
	var rows;
	try{rows=$('#plan_review_list').datagrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('��ѡ��һ����¼');
		return;
	}
	if (rows.length > 1) {
		tip('��ѡ��һ����¼');
		return;
	}

	var url = "plan-management.htm?review&id=" + rows[0].planId;
	openwindow("����", url,"plan_review_list", 800, 600);
}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>