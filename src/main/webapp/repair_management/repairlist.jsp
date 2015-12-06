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
	<h:datagrid actionUrl="repair_management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="repairlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="projectName" title="��Ŀ����"  query="true"></h:dgColumn>
		<h:dgColumn field="apporgCode" title="���벿��" dictionary="base_org,orgcode,orgname"  query="true"></h:dgColumn>
		<h:dgColumn field="repairFree" title="ά����ĿԤ��"  ></h:dgColumn>
		<h:dgColumn field="appDate" title="��������"  dateFormatter="yyyy-MM-dd hh:mm:ss" query="true"></h:dgColumn>
		<h:dgColumn field="repairContent" title="ά����Ҫ����" replace="��_null" ></h:dgColumn>
		<h:dgColumn field="apporgOpinion" title="���벿�����" replace="��_null" ></h:dgColumn>
		<h:dgColumn field="audittable" title="" style="display:none" ></h:dgColumn>
		<h:dgColumn field="auditFlag" title="���״̬" replace="δ�ύ_0,���ύ_1,������_2" style="color:red_1,color:green_0,color:blue_2" ></h:dgColumn>
		<h:dgToolBar url="repair_management.htm?add" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="repair_management.htm?update" icon="icon-add" funname="edit" title="�༭"></h:dgToolBar>
		<h:dgToolBar url="repair_management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="apprepair({id},{auditFlag})" title="����ά��"></h:dgFunOpt>
		<h:dgFunOpt funname="producecontract({id},{auditFlag})" title="����������"></h:dgFunOpt>
		<h:dgFunOpt funname="downloadcontract({audittable})" title="����������"></h:dgFunOpt>  
	</h:datagrid>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='meetingBeginDate']").attr("class","easyui-datebox");
		$("input[name='meetingEndDate']").attr("class","easyui-datebox");
	});
	function apprepair(id,flag){
		if(flag == "0"){
			$.post("repair_management.htm?repairAudit&id="+id,function(data,status){
				var obj = eval('(' + data + ')');
				$.messager.show({
		              title:'��ʾ',
		              msg:obj.msg,
		              showType:'show'
		          });
				setTimeout(function(){
		        	  window.location.reload();
		   	      },800);
			});
		}else{
			
			$.dialog.alert("ά���������ύ!");
			
		}
	}
	function edit(title, actionUrl, gname, width, height){
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
		
		if(rows[0].auditFlag != 0){
			$.dialog.alert("ά�������Ѿ��ύ,�����޸�!");
			return;
		}
		if(actionUrl.indexOf("?") == -1) {
			actionUrl += '?id='+ rows[0].id;
		} else {
			actionUrl += '&id='+ rows[0].id;
		}
		createwindow(title, actionUrl, width, height);
	}
	
	function producecontract(id,flag){
		
		
		$.post("repair_management.htm?produceContract&id="+id,function(data,status){
			var obj = eval('(' + data + ')');
			$.messager.show({
	              title:'��ʾ',
	              msg:obj.msg,
	              showType:'show'
	          });
			setTimeout(function(){
	        	  window.location.reload();
	   	      },800);
		});
}
function downloadcontract(audittable){
	if(audittable != "null" && audittable !=""){
		window.location.href="fileupload/downweb.jsp?filename="+audittable;
	}else{
		$.dialog.alert("���������ڣ�������������!");
	}
}
</script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>