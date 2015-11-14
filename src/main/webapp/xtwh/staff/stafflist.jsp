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
	<h:datagrid actionUrl="staff.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="stafflist">
		<h:dgColumn field="idcard" title="���֤��" query="true" ></h:dgColumn>
		<h:dgColumn field="staffcode" title="Ա������" query="true" ></h:dgColumn>
		<h:dgColumn field="staffname" title="Ա������" query="true" ></h:dgColumn>
     	<h:dgColumn field="gender" title="�Ա�" ></h:dgColumn>
		<h:dgColumn field="salarylevel" title="н�ʵȼ�" ></h:dgColumn>
		<h:dgColumn field="nationality" title="����" ></h:dgColumn>
		<h:dgColumn field="email" title="email"></h:dgColumn>
		<h:dgColumn field="qq" title="QQ" ></h:dgColumn>
		<h:dgColumn field="mobilephone" title="�绰" ></h:dgColumn>
		<h:dgColumn field="birthday" title="����" dateFormatter="yyyy-MM-dd"></h:dgColumn>
		<h:dgColumn field="begincareerdate" title="��ְʱ��" dateFormatter="yyyy-MM-dd"></h:dgColumn>
		<h:dgToolBar url="staff.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="staff.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgToolBar url="staff.htm?update" icon="icon-reload" funname="myedit" title="����"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='birthday_begin']").attr("class","easyui-datebox");
		$("input[name='birthday_end']").attr("class","easyui-datebox");
		$("input[name='begincareerdate_begin']").attr("class","easyui-datebox");
		$("input[name='begincareerdate_end']").attr("class","easyui-datebox");
	});
	
	function add(title, actionUrl, gname, width, height) {
		gridname=gname;
		createwindow(title, actionUrl, 600, 500);
	}
	
	function del(title, actionUrl, gname) {
		var rows = null;
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
			actionUrl += '&id='+ rows[0].idcard;
		} else {
			actionUrl += '?id='+ rows[0].idcard;
		}
		
		createdialog('ɾ��ȷ�� ', 'ȷ��ɾ���ü�¼�� ?', actionUrl, gname);
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
	
		if(actionUrl.indexOf("?") >= 0) {
			actionUrl += '&id='+ rows[0].idcard;
		} else {
			actionUrl += '?id='+ rows[0].idcard;
		}
		createwindow(title, actionUrl, 600, 500);
	}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>