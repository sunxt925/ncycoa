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
	<h:datagrid actionUrl="hiddendangerimprovement_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="hiddendangerimprovementlist">
		<h:dgColumn field="id" title="流水号"></h:dgColumn>
		<h:dgColumn field="place" title="隐患部位" ></h:dgColumn>
		<h:dgColumn field="improveMethod" title="整改措施"></h:dgColumn>
		<h:dgColumn field="improveStatus" title="完成情况" ></h:dgColumn>
		<h:dgColumn field="improveDepart" title="整改责任单位/部门"></h:dgColumn>
		<h:dgColumn field="improveChecker" title="整改验证人"></h:dgColumn>
		<h:dgColumn field="memo" title="备注" ></h:dgColumn>
		<h:dgColumn field="filePath" title="整改文件" query="true"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="fileload({filePath},{id})" title="文件下载"></h:dgFunOpt>
		<h:dgToolBar url="hiddendangerimprovement_management.htm?update" icon="icon-reload" funname="myedit" title="添加整改"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
function fileload(fileName,id){
	   window.open("fileupload/downwebpoi.jsp?type=dangerimprove&filename="+fileName+"&id="+id);
}
	$(document).ready(function(){
		$("input[name='date_begin']").attr("class","easyui-datebox");
		$("input[name='date_end']").attr("class","easyui-datebox");
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