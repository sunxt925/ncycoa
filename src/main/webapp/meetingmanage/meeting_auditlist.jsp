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
	<h:datagrid actionUrl="meeting_management.htm?dgdata&flag=uaudited" fit="true" fitColumns="true" queryMode="group" name="meetinglist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="meetingName" title="��������"  ></h:dgColumn>
		<h:dgColumn field="meetingTopics" title="��������" ></h:dgColumn>
		<h:dgColumn field="meetingBeginDate" title="��ʼʱ��"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingEndDate" title="����ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="meetingRoom" title="����ص�"   dictionary="ncycoa_meetingroom,room_no,room_name" ></h:dgColumn>
		<h:dgColumn field="numAttendee" title="��������" ></h:dgColumn>
		<h:dgColumn field="auditFlag" title="���״̬"  replace="ͨ��_11,δ���_0" style="color:red_0,color:blue_11" ></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgConfOpt title="ͨ��" url="meeting_management.htm?audit&id={id}&res=yes" message="ȷ����ˣ�" />
		<h:dgConfOpt title="��ͨ��" url="meeting_management.htm?audit&id={id}&res=no" message="ȷ����ˣ�" />
		<h:dgToolBar url="meeting_management.htm?audit" icon="icon-search" funname="update" title="���"></h:dgToolBar>
	
		</h:datagrid>
</body>

<script type="text/javascript">

	$(document).ready(function(){
		$("input[name='meetingBeginDate']").attr("class","easyui-datebox");
		$("input[name='meetingEndDate']").attr("class","easyui-datebox");
	});
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>