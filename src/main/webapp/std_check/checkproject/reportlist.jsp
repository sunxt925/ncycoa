<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<%
String path = request.getContextPath();
%>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>�ϳ��̲�ר����</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>

<script type="text/javascript">
function detailcanoperate(title,url, id,width,height) {
	var rowsData = $('#'+id).datagrid('getSelections');
	
	if (!rowsData || rowsData.length == 0) {
		tip('��ѡ��鿴��Ŀ');
		return;
	}
	if (rowsData.length > 1) {
		tip('��ѡ��һ����¼�ٲ鿴');
		return;
	}
    url += '&id='+rowsData[0].id;
	window.open(url,"���󷽰�����");
}
</script>
<body>
	<h:datagrid actionUrl="checkproject.htm?dgreportdata" fit="true" fitColumns="true" queryMode="group" name="stdgetlist">
		<h:dgColumn field="id" title="id" hidden="true" align="center"  width="50"></h:dgColumn>
		<h:dgColumn field="aboutCommit" title="��ؼ���ίԱ��" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="endTime" title="���ʱ��" query="true" align="center" dateFormatter="yyyy-MM-dd HH:mm:ss" width="80" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="checkproject.htm?openoffice2" icon="icon-reload" funname="detailcanoperate" title="�鿴���󱨸�"></h:dgToolBar>
		<h:dgToolBar url="checkproject.htm?delreport" icon="icon-remove" funname="del" title="ɾ�����󱨸�"></h:dgToolBar>
	</h:datagrid>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='endTime_begin']").attr("class","easyui-datebox");
		$("input[name='endTime_end']").attr("class","easyui-datebox");
		
	});
</script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
</html>
