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
	createdetailwindow(title,url,width,height);
}
function operate(title, actionUrl, gname) {
	var rows = null;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('��ѡ��');
		return;
	}
	
	var ids = '';
	for(var i=0; i<rows.length; i++) {
		ids += rows[i].id + ',';
	}
	if(actionUrl.indexOf("?") == -1) {
		actionUrl += '?id='+ ids;
	} else {
		actionUrl += '&id='+ ids;
	}
	createdialog('����ȷ��', 'ȷ����˲����� ?', actionUrl, gname);
}
function download(title, actionUrl, gname){
	var rows = null;
	try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
	try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
	
	if (!rows || rows.length==0) {
		tip('��ѡ��');
		return;
	}
	
	var ids = '';
	for(var i=0; i<rows.length; i++) {
		ids += rows[i].id + ',';
	}
	actionUrl += '&id='+ ids;
	window.open(actionUrl);
}
</script>
<body>
	<h:datagrid actionUrl="stdget.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="stdgetlist">
		<h:dgColumn field="id" title="id" hidden="true" align="center"  width="50"></h:dgColumn>
		<h:dgColumn field="gstFilecode" title="�ļ����" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstFilename" title="�ļ�����" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstFiletype" title="�ļ�����" replace="���ɷ���_0,�淶���ļ�_1,������׼_2" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstFilelevel" title="�ļ��㼶" replace="����_0,�ط�_1,��ҵ_2" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstPublictime" title="����ʱ��" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstYear" title="���" query="true" align="center"  width="40"></h:dgColumn>
		<h:dgColumn field="gstStaffname" title="¼����Ա" query="true" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstStafforg" title="������" align="center"  width="80"></h:dgColumn>
		<h:dgColumn field="gstOperate" title="����״̬" replace="δ����_0,����_1,ȫ������_2,��������_3,ת��Ϊ��׼_4" style="color:red_0,color:blue_1,color:green_2,color:red_3,color:blue_4" query="true"  align="center"  width="80"></h:dgColumn>
		<h:dgToolBar url="stdget.htm?detail" icon="icon-reload" funname="detailcanoperate" title="�ɱ����ݲ鿴"></h:dgToolBar>
		<h:dgToolBar url="stdget.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?quote0" icon="icon-remove" funname="operate" title="����"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?quote1" icon="icon-reload" funname="operate" title="ȫ������"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?quote2" icon="icon-reload" funname="operate" title="��������"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?turntostd" icon="icon-reload" funname="update" title="ת��Ϊ��׼"></h:dgToolBar>
 		<h:dgToolBar url="stdget.htm?download" icon="icon-remove" funname="download" title="���زɱ��ļ�"></h:dgToolBar>
 		
	</h:datagrid>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='inputDate_begin']").attr("class","easyui-datebox");
		$("input[name='inputDate_end']").attr("class","easyui-datebox");
	});
</script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
</html>
