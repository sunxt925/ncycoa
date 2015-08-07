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
	<h:datagrid actionUrl="reform_management.htm?dgdataquery" fit="true" fitColumns="true" queryMode="group" name="reformlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="整改项目名称" ></h:dgColumn>
		<h:dgColumn field="xdzgOrgcode" title="整改下达单位"  dictionary="base_org,orgcode,orgname"  query="true"></h:dgColumn>
		
		<h:dgColumn field="handler" title="整改下达者"  dictionary="base_staff,staffcode,staffname"></h:dgColumn>
		<h:dgColumn field="clOrgcode" title="要求整改部门"  dictionary="base_org,orgcode,orgname" ></h:dgColumn>
		<h:dgColumn field="xdDate" title="下达日期" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="fileName" title="附件">
		<h:dgOpenOpt url="fileupload/download.jsp?filename={fileName}" title="{fileName}"></h:dgOpenOpt>
		</h:dgColumn>
		<h:dgColumn field="memo" title="整改说明"></h:dgColumn>
		<h:dgColumn field="flag" title="状态"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		  <h:dgFunOpt funname="reformback({id})" title="反馈结果"></h:dgFunOpt>
		  <h:dgFunOpt funname="fileload({fileName})" title="附件下载"></h:dgFunOpt>
		 </h:datagrid>
</body>
<script type="text/javascript">
function fileload(fileName){
	   window.open("fileupload/downweb.jsp?filename="+fileName);
}
  function reformback(id){
	  createwindow("反馈结果","reformback_management.htm?add&reformid="+id,600,400);
  }
  function returnValue(data){
	  alert(data.code);
  }
  function createwindow(title, url, width, height) {
		
		$.dialog({
				id:'CLHG1976D',
				data:returnValue,
				content : 'url:' + url,
				lock : true,
				width : width,
				height : height,
				title : title,
				zIndex :2000,
				opacity : 0.3,
				cache : false,
				ok : function() {
					$('#btn_ok', this.iframe.contentWindow.document).click();
					return true;
				},
				cancelVal : '关闭',
				cancel : true/* 为true等价于function(){} */
			});
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>