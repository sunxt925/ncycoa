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
	<h:datagrid actionUrl="contract-management.htm?dgdata&type=${type }" fit="true" fitColumns="true" queryMode="group" name="contractlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="status" title="status" hidden="true"/>
		<h:dgColumn field="code" title="��ͬ����" ></h:dgColumn>
		<h:dgColumn field="name" title="��ͬ����"></h:dgColumn>
		<h:dgColumn field="relevantDepartment" title="��ڲ���" ></h:dgColumn>
		<h:dgColumn field="type" title="��ͬ���" query="true"></h:dgColumn>
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
		<h:dgColumn title="����" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="commitcontract({id},{status})" title="�ύ��ͬ"></h:dgFunOpt>
		<h:dgFunOpt funname="producecontract({id},{status})" title="����������"></h:dgFunOpt>
		<h:dgFunOpt funname="downloadcontract({audittable})" title="����������"></h:dgFunOpt>
		<h:dgToolBar url="contract-management.htm?add&type=${type }" icon="icon-add" funname="add" title="����"></h:dgToolBar>
		<h:dgToolBar url="contract-management.htm?del" icon="icon-remove" funname="del" title="ɾ��"></h:dgToolBar>
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
			$.post("contract-management.htm?commit&id="+id,function(data,status){
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
</script>
</html>