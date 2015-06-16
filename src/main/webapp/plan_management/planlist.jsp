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
*{font-size:12px; font-family:΢���ź�,������}
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
		<h:dgColumn field="name" title="�ƻ�����" query="true"></h:dgColumn>
		<c:if test="${isAdmin}"><!-- �Ƿ��ǿ����ۺϹ���Ա -->
		<h:dgColumn field="type" title="�ƻ�����" replace="��λ�ƻ�_0,���żƻ�_1" query="true"></h:dgColumn>
		</c:if>
		<h:dgColumn field="status" title="�ƻ�״̬" 
			replace="�༭״̬_0,�ȴ����_1,���ͨ��_2,���δͨ��_3,����ִ��_4,ִ�н���_5,ִ���ж�_6" 
			style="color:#4d77cc_0,color:#ce3f38_1,color:#5eaf5e_2,color:#ce3f38_3,color:#fa9f1e_4" query="true">
		</h:dgColumn>
		<h:dgColumn field="description" title="����"></h:dgColumn>
		<h:dgColumn field="inputUser" title="¼��Ա" dictionary="base_staff,staffcode,staffname" query="false"></h:dgColumn>
		<h:dgColumn field="inputDate" title="¼��ʱ��" dateFormatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="scope"></h:dgColumn>
		<h:dgToolBar url="plan-management.htm?add" icon="icon-add" funname="add" title="�����¼ƻ�"></h:dgToolBar>
		<h:dgToolBar url="plan-management.htm?del" icon="icon-remove" funname="mydel" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="plan-management.htm?update" icon="icon-edit" funname="myedit" title="�༭�ƻ�"></h:dgToolBar>
		<h:dgColumn field="opt" title="����"></h:dgColumn>
		<h:dgConfOpt url="plan-management.htm?foraudit&id={id}" title="�ύ���" message="ȷ���ύ�üƻ����ƻ��ύ���ȴ���ˣ��ڼ佫���ܱ༭�޸�" exp="status#in#0"></h:dgConfOpt>
		<h:dgOpenOpt url="dgdata.htm?open" title="�鿴ԭ��" exp="status#in#3"></h:dgOpenOpt>
		<h:dgFunOpt funname="myedit_1({id})" title="�༭�ƻ�" exp="status#in#0,3"></h:dgFunOpt>
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
	createwindow("�༭�ƻ�", url, 800, 600);
}


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
	
	if(rows[0].status != 0 && rows[0].status != 3){
		$.dialog.alert("ֻ�ܶԴ��� ���༭״̬�� �� �����δͨ���� ״̬�µļƻ����б༭");
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
		tip('��ѡ��');
		return;
	}
	if (rows.length > 1) {
		tip('����ͬʱɾ��������¼���빴ѡһ����¼');
		return;
	}
	
	if(rows[0].status != 0 && rows[0].status != 3){
		$.dialog.alert("ֻ��ɾ������ ���༭״̬�� �� �����δͨ���� ״̬�µļƻ�");
		return;
	}
	
	if(actionUrl.indexOf("?") == -1) {
		actionUrl += '?id='+ rows[0].id;
	} else {
		actionUrl += '&id='+ rows[0].id;
	}
	
	createdialog('ɾ��ȷ�� ', 'ȷ��ɾ���ü�¼�� ?', actionUrl, gname);
}



$(document).ready(function(){
	$("input[name='inputDate_begin']").click(function(){WdatePicker();});
	$("input[name='inputDate_end']").click(function(){WdatePicker();});
});
</script>
</html>