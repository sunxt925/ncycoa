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
	<h:datagrid actionUrl="repair_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="repairlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="projectName" title="项目名称"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgCode" title="申请部门" dictionary="base_org,orgcode,orgname"  query="true"></h:dgColumn>
		<h:dgColumn field="repairFree" title="维修项目预算"  ></h:dgColumn>
		<h:dgColumn field="appDate" title="申请日期"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="repairContent" title="维修主要内容" replace="无_null" ></h:dgColumn>
		<h:dgColumn field="apporgOpinion" title="申请部门意见" replace="无_null" ></h:dgColumn>
		<h:dgColumn field="auditFlag" title="审核状态" replace="未提交_0,已提交_1" style="color:red_1,color:green_0" ></h:dgColumn>
		<h:dgToolBar url="repair_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="repair_management.htm?update" icon="icon-add" funname="edit" title="编辑"></h:dgToolBar>
		<h:dgToolBar url="repair_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="apprepair({id},{auditFlag})" title="申请维修"></h:dgFunOpt>
		  
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='meetingBeginDate']").attr("class","easyui-datebox");
		$("input[name='meetingEndDate']").attr("class","easyui-datebox");
	});
	function apprepair(id,flag){
		if(flag == "0"){
			$.post("repair_management.htm?repairAudit&id="+id,function(data,status){
				var obj = eval('(' + data + ')');
				$.messager.show({
		              title:'提示',
		              msg:obj.msg,
		              showType:'show'
		          });
				setTimeout(function(){
		        	  window.location.reload();
		   	      },800);
			});
		}else{
			
			$.dialog.alert("维修申请已提交!");
			
		}
	}
	function edit(title, actionUrl, gname, width, height){
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
		
		if(rows[0].auditFlag != 0){
			$.dialog.alert("维修申请已经提交,不能修改!");
			return;
		}
		if(actionUrl.indexOf("?") == -1) {
			actionUrl += '?id='+ rows[0].id;
		} else {
			actionUrl += '&id='+ rows[0].id;
		}
		createwindow(title, actionUrl, width, height);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>