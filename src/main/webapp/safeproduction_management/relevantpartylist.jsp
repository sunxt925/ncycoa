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
	<h:datagrid actionUrl="relevantparty_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="relevantpartylist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="relevantPartyName" title="相关方（单位/个人）" query="true"></h:dgColumn>
		<h:dgColumn field="responsiblePerson" title="相关方负责人"></h:dgColumn>
     	<h:dgColumn field="content" title="相关方作业从事内容"></h:dgColumn>
		<h:dgColumn field="telephone" title="相关方联系电话"></h:dgColumn>
		<h:dgColumn field="address" title="相关方地址" query="true" ></h:dgColumn>
		<h:dgColumn field="aptitude" title="具备的资质"></h:dgColumn>
		<h:dgColumn field="projectname" title="在本单位作业项目"></h:dgColumn>
		<h:dgColumn field="jobplace" title="在本单位作业区域"></h:dgColumn>
		<h:dgColumn field="jobpersoncount" title="在本单位作业人员数"></h:dgColumn>
		<h:dgColumn field="gkorgcode" title="在本单位归口管理部门"  dictionary="base_org,orgcode,orgname"></h:dgColumn>
		<h:dgToolBar url="relevantparty_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="relevantparty_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		<h:dgToolBar url="relevantparty_management.htm?update" icon="icon-reload" funname="myedit" title="更新"></h:dgToolBar>
	     <h:dgColumn title="操作" field="opt"></h:dgColumn>
		  <h:dgFunOpt funname="fileload({fileName})" title="附件下载"></h:dgFunOpt>
	</h:datagrid>
</body>

<script type="text/javascript">
function fileload(fileName){
	   window.open("fileupload/downweb.jsp?filename="+fileName);
}
	$(document).ready(function(){
		$("input[name='checkTime_begin']").attr("class","easyui-datebox");
		$("input[name='checkTime_end']").attr("class","easyui-datebox");
	});
	
	
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
		createwindow(title, actionUrl, width, height);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>