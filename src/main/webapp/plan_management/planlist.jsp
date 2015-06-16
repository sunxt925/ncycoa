<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

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
*{font-size:12px; font-family:微软雅黑,新宋体}
a.dgopt{
	color:#000;
	cursor:pointer;
	text-decoration:none;
	border-bottom:1px dotted #000;
}
</style>
</head>
<body>
	<h:datagrid actionUrl="plan-management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="planlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="计划名称" query="true"></h:dgColumn>
		<c:if test="${isAdmin}"><!-- 是否是科室综合管理员 -->
		<h:dgColumn field="type" title="计划类型" replace="岗位计划_0,部门计划_1" query="true"></h:dgColumn>
		</c:if>
		<h:dgColumn field="status" title="计划状态" 
			replace="编辑状态_0,等待审核_1,审核通过_2,审核未通过_3,正在执行_4,执行结束_5,执行中断_6" 
			style="color:#4d77cc_0,color:#ce3f38_1,color:#5eaf5e_2,color:#ce3f38_3,color:#fa9f1e_4" query="true">
		</h:dgColumn>
		<h:dgColumn field="description" title="描述"></h:dgColumn>
		<h:dgColumn field="inputUser" title="录入员" dictionary="base_staff,staffcode,staffname" query="false"></h:dgColumn>
		<h:dgColumn field="inputDate" title="录入时间" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="plan-management.htm?add" icon="icon-add" funname="add" title="建立新计划"></h:dgToolBar>
		<h:dgToolBar url="plan-management.htm?del" icon="icon-remove" funname="mydel" title="删除"></h:dgToolBar>
		<h:dgToolBar url="plan-management.htm?update" icon="icon-edit" funname="myedit" title="编辑计划"></h:dgToolBar>
		<h:dgColumn field="opt" title="操作"></h:dgColumn>
		<h:dgConfOpt url="plan-management.htm?foraudit&id={id}" title="提交审核" message="确定提交该计划？计划提交后会等待审核，期间将不能编辑修改" exp="status#in#0"></h:dgConfOpt>
		<h:dgOpenOpt url="dgdata.htm?open" title="查看原因" exp="status#in#3"></h:dgOpenOpt>
		<h:dgFunOpt funname="myedit_1({id})" title="编辑计划" exp="status#in#0,3"></h:dgFunOpt>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
function myedit_1(id){
	var url = "plan-management.htm?update";
	if(url.indexOf("?") >= 0) {
		url += '&id='+ id;
	} else {
		url += '?id='+ id;
	}
	createwindow("编辑计划", url, 800, 600);
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
	
	if(rows[0].status != 0 && rows[0].status != 3){
		$.dialog.alert("只能对处于 ‘编辑状态’ 或 ‘审核未通过’ 状态下的计划进行编辑");
		return;
	}
	
	if(actionUrl.indexOf("?") >= 0) {
		actionUrl += '&id='+ rows[0].id;
	} else {
		actionUrl += '?id='+ rows[0].id;
	}
	createwindow(title, actionUrl, width, height);
}

function mydel(title, actionUrl, gname) {
	var rows = null;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('请选择');
		return;
	}
	if (rows.length > 1) {
		tip('不能同时删除多条记录，请勾选一条记录');
		return;
	}
	
	if(rows[0].status != 0 && rows[0].status != 3){
		$.dialog.alert("只能删除处于 ‘编辑状态’ 或 ‘审核未通过’ 状态下的计划");
		return;
	}
	
	if(actionUrl.indexOf("?") == -1) {
		actionUrl += '?id='+ rows[0].id;
	} else {
		actionUrl += '&id='+ rows[0].id;
	}
	
	createdialog('删除确认 ', '确定删除该记录吗 ?', actionUrl, gname);
}



$(document).ready(function(){
	$("input[name='inputDate_begin']").click(function(){WdatePicker();});
	$("input[name='inputDate_end']").click(function(){WdatePicker();});
});
</script>
</html>