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
	<h:datagrid actionUrl="pressureequipment_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="pressureequipmentlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="designPressure" title="���ѹ��һ" query="true"></h:dgColumn>
     	<h:dgColumn field="designPressure2" title="���ѹ����" query="true"></h:dgColumn>
		<h:dgColumn field="maxPressure" title="��߹���ѹ��һ" ></h:dgColumn>
		<h:dgColumn field="maxPressure2" title="��߹���ѹ����" ></h:dgColumn>
		<h:dgColumn field="designTemperature" title="����¶�һ"></h:dgColumn>
		<h:dgColumn field="designTemperature2" title="����¶ȶ�" ></h:dgColumn>
		<h:dgColumn field="maxTemperature" title="��߹����¶�һ" ></h:dgColumn>
		<h:dgColumn field="maxTemperature2" title="��߹����¶ȶ�" ></h:dgColumn>
		<h:dgColumn field="manageDepart" title="��ز���" ></h:dgColumn>
		<h:dgColumn field="manager" title="��ظ�����" ></h:dgColumn>
		<h:dgToolBar url="pressureequipment_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="pressureequipment_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="pressureequipment_management.htm?update" icon="icon-reload" funname="myedit" title="����"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
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