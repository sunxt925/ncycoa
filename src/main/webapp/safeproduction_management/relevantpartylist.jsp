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
	<h:datagrid actionUrl="relevantparty_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="relevantpartylist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="relevantPartyName" title="��ط�����λ/���ˣ�" query="true"></h:dgColumn>
		<h:dgColumn field="responsiblePerson" title="��ط�������"></h:dgColumn>
     	<h:dgColumn field="content" title="��ط���ҵ��������"></h:dgColumn>
		<h:dgColumn field="telephone" title="��ط���ϵ�绰"></h:dgColumn>
		<h:dgColumn field="address" title="��ط���ַ" query="true" ></h:dgColumn>
		<h:dgColumn field="aptitude" title="�߱�������"></h:dgColumn>
		<h:dgColumn field="projectname" title="�ڱ���λ��ҵ��Ŀ"></h:dgColumn>
		<h:dgColumn field="jobplace" title="�ڱ���λ��ҵ����"></h:dgColumn>
		<h:dgColumn field="jobpersoncount" title="�ڱ���λ��ҵ��Ա��"></h:dgColumn>
		<h:dgColumn field="gkorgcode" title="�ڱ���λ��ڹ�����"  dictionary="base_org,orgcode,orgname"></h:dgColumn>
		<h:dgToolBar url="relevantparty_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="relevantparty_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="relevantparty_management.htm?update" icon="icon-reload" funname="myedit" title="����"></h:dgToolBar>
	     <h:dgColumn title="����" field="opt"></h:dgColumn>
		  <h:dgFunOpt funname="fileload({fileName})" title="��������"></h:dgFunOpt>
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
			tip('����ѡ��һ����¼');
			return;
		}
		if (rows.length > 1) {
			tip('����ͬʱ�Զ�����¼�༭���빴ѡһ����¼');
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