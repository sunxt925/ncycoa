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
	<h:datagrid actionUrl="liftingequipment_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="liftingequipmentlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="mainWeight" title="����������" query="true"></h:dgColumn>
     	<h:dgColumn field="mainSpan" title="�������" query="true"></h:dgColumn>
		<h:dgColumn field="mainHeight" title="���������߶�" ></h:dgColumn>
		<h:dgColumn field="viceWeight" title="����������" ></h:dgColumn>
		<h:dgColumn field="viceSpan" title="�������"></h:dgColumn>
		<h:dgColumn field="viceHeight" title="���������߶�" ></h:dgColumn>
		<h:dgColumn field="mainPurpose" title="��Ҫ��;" ></h:dgColumn>
		<h:dgColumn field="useLocation" title="ʹ�õص�" ></h:dgColumn>
		<h:dgColumn field="factoryDate" title="��������" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="installDate" title="��װ�깤����" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope" ></h:dgColumn>
		<h:dgColumn field="useDATE" title="�״�Ͷ������" dateFormatter="yyyy-MM-dd hh:mm:ss"></h:dgColumn>
		<h:dgColumn field="certDate" title="��֤����" dateFormatter="yyyy-MM-dd hh:mm:ss" ></h:dgColumn>
		<h:dgColumn field="manageDepart" title="�豸������" ></h:dgColumn>
		<h:dgColumn field="manager" title="�豸������" ></h:dgColumn>
		<h:dgToolBar url="liftingequipment_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="liftingequipment_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="liftingequipment_management.htm?update" icon="icon-reload" funname="myedit" title="����"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='factoryDate_begin']").attr("class","easyui-datebox");
		$("input[name='factoryDate_end']").attr("class","easyui-datebox");
		$("input[name='installDate_begin']").attr("class","easyui-datebox");
		$("input[name='installDate_end']").attr("class","easyui-datebox");
		$("input[name='useDATE_begin']").attr("class","easyui-datebox");
		$("input[name='useDATE_end']").attr("class","easyui-datebox");
		$("input[name='certDate_begin']").attr("class","easyui-datebox");
		$("input[name='certDate_end']").attr("class","easyui-datebox");
	});
	
	
	function myedit(title, actionUrl, gname, width, height) {
		gridname=gname;
		var rows;
		try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
		try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
		
		if (!rows || rows.length==0) {
			tip('����ѡ��һ����¼');
			return;
		}
		if (rows.length > 1) {
			tip('����ͬʱ�Զ�����¼�༭���빴ѡһ����¼');
			return;
		}
	
		if(actionUrl.indexOf("?") >= 0) {
			actionUrl += '&id='+ rows[0].id;
		} else {
			actionUrl += '?id='+ rows[0].id;
		}
		createwindow(title, actionUrl, width, height);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>