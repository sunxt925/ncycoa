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
	<h:datagrid actionUrl="staff.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="stafflist">
		<h:dgColumn field="idcard" title="身份证号" query="true" ></h:dgColumn>
		<h:dgColumn field="staffcode" title="员工编码" query="true" ></h:dgColumn>
		<h:dgColumn field="staffname" title="员工姓名" query="true" ></h:dgColumn>
     	<h:dgColumn field="gender" title="性别" ></h:dgColumn>
		<h:dgColumn field="salarylevel" title="薪资等级" ></h:dgColumn>
		<h:dgColumn field="nationality" title="民族" ></h:dgColumn>
		<h:dgColumn field="email" title="email"></h:dgColumn>
		<h:dgColumn field="qq" title="QQ" ></h:dgColumn>
		<h:dgColumn field="mobilephone" title="电话" ></h:dgColumn>
		<h:dgColumn field="birthday" title="生日" dateFormatter="yyyy-MM-dd"></h:dgColumn>
		<h:dgColumn field="begincareerdate" title="入职时间" dateFormatter="yyyy-MM-dd"></h:dgColumn>
		<h:dgToolBar url="staff.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="staff.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="staff.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='birthday_begin']").attr("class","easyui-datebox");
		$("input[name='birthday_end']").attr("class","easyui-datebox");
		$("input[name='begincareerdate_begin']").attr("class","easyui-datebox");
		$("input[name='begincareerdate_end']").attr("class","easyui-datebox");
	});
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 600, 500);
	}
	
	function del(title, actionUrl, gname) {
		var rows = null;
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
			actionUrl += '&id='+ rows[0].idcard;
		} else {
			actionUrl += '?id='+ rows[0].idcard;
		}
		
		createdialog('删除确认 ', '确定删除该记录吗 ?', actionUrl, gname);
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
			actionUrl += '&id='+ rows[0].idcard;
		} else {
			actionUrl += '?id='+ rows[0].idcard;
		}
		createwindow(title, actionUrl, 600, 500);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>