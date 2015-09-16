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
	<h:datagrid actionUrl="reform_management.htm?dgdataquery" fit="true" fitColumns="true" queryMode="group" name="reformlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="name" title="������Ŀ����" ></h:dgColumn>
		<h:dgColumn field="xdzgOrgcode" title="�����´ﵥλ"  dictionary="base_org,orgcode,orgname"  query="true"></h:dgColumn>
		
		<h:dgColumn field="handler" title="�����´���"  dictionary="base_staff,staffcode,staffname"></h:dgColumn>
		<h:dgColumn field="clOrgcode" title="Ҫ�����Ĳ���"  dictionary="base_org,orgcode,orgname" ></h:dgColumn>
		<h:dgColumn field="xdDate" title="�´�����" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgColumn field="fileName" title="����">
		<h:dgOpenOpt url="fileupload/download.jsp?filename={fileName}" title="{fileName}"></h:dgOpenOpt>
		</h:dgColumn>
		<h:dgColumn field="memo" title="����˵��"></h:dgColumn>
		<h:dgColumn field="flag" title="״̬"></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		  <h:dgFunOpt funname="reformback({id})" title="�������"></h:dgFunOpt>
		  <h:dgFunOpt funname="fileload({fileName})" title="��������"></h:dgFunOpt>
		 </h:datagrid>
</body>
<script type="text/javascript">
function fileload(fileName){
	   window.open("fileupload/downweb.jsp?filename="+fileName);
}
  function reformback(id){
	  createwindow("�������","reformback_management.htm?add&reformid="+id,600,400);
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
				cancelVal : '�ر�',
				cancel : true/* Ϊtrue�ȼ���function(){} */
			});
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>