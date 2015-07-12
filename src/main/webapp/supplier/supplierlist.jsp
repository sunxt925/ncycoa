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
	<h:datagrid actionUrl="supplier.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="supplierlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="供货商名称" query="true" ></h:dgColumn>
     	<h:dgColumn field="goodsType" title="物资类别" replace="办公物资 _0,信息物资 _1,物流物资 _2,安防物资 _3,烘烤物资_4,烟叶物资_5" hidden="true"></h:dgColumn>
		<h:dgColumn field="manageDepart" title="归口管理部门"  query="true"></h:dgColumn>
		<h:dgColumn field="range" title="经营范围" ></h:dgColumn>
		<h:dgColumn field="address" title="地址"></h:dgColumn>
		<h:dgColumn field="tel" title="电话" ></h:dgColumn>
		<h:dgColumn field="registMoney" title="注册资金" ></h:dgColumn>
		<h:dgColumn field="agent" title="代理人" ></h:dgColumn>
		<h:dgColumn field="inputTime" title="入库时间" dateFormatter="yyyy-MM-dd" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="outputTime" title="出库时间" dateFormatter="yyyy-MM-dd" hidden="true"></h:dgColumn>
		<h:dgToolBar url="supplier.htm?addi" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="supplier.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
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
			tip('请先选择一条记录');
			return;
		}
		if (rows.length > 1) {
			tip('不能同时对多条记录编辑，请勾选一条记录');
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