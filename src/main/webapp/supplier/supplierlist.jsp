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
	<h:datagrid actionUrl="supplier.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="supplierlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="����������" query="true" ></h:dgColumn>
     	<h:dgColumn field="goodsType" title="�������" replace="�칫���� _0,��Ϣ���� _1,�������� _2,�������� _3,�濾����_4,��Ҷ����_5" hidden="true"></h:dgColumn>
		<h:dgColumn field="manageDepart" title="��ڹ�����"  query="true"></h:dgColumn>
		<h:dgColumn field="range" title="��Ӫ��Χ" ></h:dgColumn>
		<h:dgColumn field="address" title="��ַ"></h:dgColumn>
		<h:dgColumn field="tel" title="�绰" ></h:dgColumn>
		<h:dgColumn field="registMoney" title="ע���ʽ�" ></h:dgColumn>
		<h:dgColumn field="agent" title="������" ></h:dgColumn>
		<h:dgColumn field="inputTime" title="���ʱ��" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="outputTime" title="����ʱ��" dateFormatter="yyyy-MM-dd" hidden="true"></h:dgColumn>
		<h:dgToolBar url="supplier.htm?addi" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?update" icon="icon-reload" funname="myedit" title="����"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='inputTime_begin']").attr("class","easyui-datebox");
		$("input[name='inputTime_end']").attr("class","easyui-datebox");
		$("input[name='outputTime_begin']").attr("class","easyui-datebox");
		$("input[name='outputTime_end']").attr("class","easyui-datebox");
	});
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 600, 500);
	}
	
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
		createwindow(title, actionUrl, 600, 500);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>