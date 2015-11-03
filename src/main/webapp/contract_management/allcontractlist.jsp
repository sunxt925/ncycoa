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
<script type="text/javascript" src="js/MyDatePicker/WdatePicker.js"></script>
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>
<body>
	<h:datagrid actionUrl="contract-management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="contractlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="status" title="status" hidden="true"/>
		<h:dgColumn field="code" title="��ͬ����" ></h:dgColumn>
		<h:dgColumn field="name" title="��ͬ����"></h:dgColumn>
		<h:dgColumn field="relevantDepartment" title="ʵʩ����"  dictionary="base_org,orgcode,orgname"></h:dgColumn>
		<h:dgColumn field="type" title="��ͬ���" query="true" replace="���̺�ͬ_0,�ɹ���ͬ_1,ά�޺�ͬ_2,������ͬ_3"></h:dgColumn>
		<h:dgColumn field="contactMethod" title="ʵʩ��ʽ" query="true" replace="�����б�_0,�����б�_1,������̸��_2,ѯ��_3,��һ��Դ_4"></h:dgColumn>
		<h:dgColumn field="partyA" title="�׷�" ></h:dgColumn>
		<h:dgColumn field="partyB" title="�ҷ�" ></h:dgColumn>
		<h:dgColumn field="contractValue" title="��ͬ���" query="true"></h:dgColumn>
		<h:dgColumn field="contractObject" title="��ͬ���" ></h:dgColumn>
		<h:dgColumn field="signingDate" title="ǩ������" dateFormatter="yyyy-MM-dd" query="true"></h:dgColumn>
		<h:dgColumn field="implementationStage" title="ִ�����" ></h:dgColumn>
		<h:dgColumn field="finishingDate" dateFormatter="yyyy-MM-dd" title="�������"></h:dgColumn>
		<h:dgColumn field="renewal" title="��ǩ" ></h:dgColumn>
		<h:dgColumn field="contractFilePath" title="" style="display:none"></h:dgColumn>
		<h:dgColumn field="audittable" title="" style="display:none"></h:dgColumn>
		<h:dgColumn field="status" title="" style="display:none"></h:dgColumn>
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="downloadcontract({audittable})" title="����������"></h:dgFunOpt>
		<h:dgFunOpt funname="downloadfile({contractFilePath})" title="��ͬ����"></h:dgFunOpt>
		<h:dgToolBar url="contract-management.htm?update" icon="icon-add" funname="update" title="����̨��"></h:dgToolBar>
		<h:dgToolBar url="contract-management.htm?exportExcel" icon="icon-print" funname="exportExcel" title="����̨��"></h:dgToolBar>
	</h:datagrid>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='inputDate_begin']").attr("class","easyui-datebox");
		$("input[name='inputDate_end']").attr("class","easyui-datebox");
	});
	function commitcontract(id,flag){
		if(flag == "0"){
			createwindow2('�ύ����',"contract_management/chooseaudit.jsp?id="+id,300,400,returnValue);
			
		}else{
			
			$.dialog.alert("��ͬ���ύ�������ظ��ύ!");
			
		}
	}
	function producecontract(id,flag){
		
			
			$.post("contract-management.htm?produceContract&id="+id,function(data,status){
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
	function downloadfile(filename){
		if(filename != "null" && filename !=""){
			window.location.href="fileupload/downweb.jsp?filename="+filename;
		}else{
			$.dialog.alert("��ͬ�ļ�������!");
		}
    }
	function update(title, actionUrl, gname, width, height){
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
		if(rows[0].status == 1){
			$.dialog.alert("��ͬ�������,�����޸�!");
			return;
		}
		if(actionUrl.indexOf("?") == -1) {
			actionUrl += '?id='+ rows[0].id;
		} else {
			actionUrl += '&id='+ rows[0].id;
		}
		createwindow(title, actionUrl, width, height);
	}
	function returnValue2(data){
		window.location.href="contract-management.htm?exportExcel&sDate="+data.sDate+"&eDate="+data.eDate;
		$.dialog({id:'choose01'}).close();
	}
	function exportExcel(title, actionUrl, gname, width, height){
		createwindow2('����Excel',"contract_management/contract_dg_export.jsp",200,200,returnValue2);
	}
	function returnValue(data){
		$.dialog({id:'choose01'}).close();
		 $.dialog.confirm('��˳ɹ�',function(){
			   window.location.reload();
           });
	}
    function createwindow2(title, url, width, height,func) {
		
		$.dialog({
			id:'choose01',
			data:func,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return false;
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
	
}
</script>
</html>