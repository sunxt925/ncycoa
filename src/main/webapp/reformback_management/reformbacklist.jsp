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
	<h:datagrid actionUrl="reformback_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="reformbacklist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="backname" title="反馈名称"  query="true"></h:dgColumn>
		<h:dgColumn field="reformId" title="整改项目ID"  query="true"></h:dgColumn>
		
		<h:dgColumn field="fileName" title="附件" ></h:dgColumn>
		<h:dgColumn field="reformFile" title="附件1"  style="display:none"></h:dgColumn>
		<h:dgColumn field="memo" title="说明"  query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="subDate" title="提交日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="subUser" title="提交者"   dictionary="base_staff,staffcode,staffname"></h:dgColumn>
		<h:dgToolBar url="reformback_management.htm?add" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		
		<h:dgToolBar url="reformback_management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
		
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		  <h:dgFunOpt funname="fileload({fileName})" title="附件下载"></h:dgFunOpt>
		  <h:dgFunOpt funname="fileload({reformFile})" title="反馈文档下载"></h:dgFunOpt>
	</h:datagrid>
</body>
<script type="text/javascript">
function fileload(fileName){
	   window.open("fileupload/downweb.jsp?filename="+fileName);
}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>