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
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body>
	<h:datagrid actionUrl="contract-management.htm?dgdata&type=${type }" fit="true" fitColumns="true" queryMode="group" name="contractlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="status" title="status" hidden="true"/>
		<h:dgColumn field="code" title="合同编码" ></h:dgColumn>
		<h:dgColumn field="name" title="合同名称"></h:dgColumn>
		<h:dgColumn field="relevantDepartment" title="归口部门" ></h:dgColumn>
		<h:dgColumn field="type" title="合同类别" query="true"></h:dgColumn>
		<h:dgColumn field="partyA" title="甲方" ></h:dgColumn>
		<h:dgColumn field="partyB" title="乙方" ></h:dgColumn>
		<h:dgColumn field="contractValue" title="合同金额" query="true"></h:dgColumn>
		<h:dgColumn field="contractObject" title="合同标的" ></h:dgColumn>
		<h:dgColumn field="signingDate" title="签订日期" dateFormatter="yyyy-MM-dd" query="true"></h:dgColumn>
		<h:dgColumn field="implementationStage" title="执行情况" ></h:dgColumn>
		<h:dgColumn field="finishingDate" dateFormatter="yyyy-MM-dd" title="完成日期"></h:dgColumn>
		<h:dgColumn field="renewal" title="续签" ></h:dgColumn>
		<h:dgColumn field="contractFilePath" title="" style="display:none"></h:dgColumn>
		<h:dgColumn field="audittable" title="" style="display:none"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="commitcontract({id},{status})" title="提交合同"></h:dgFunOpt>
		<h:dgFunOpt funname="producecontract({id},{status})" title="审批表生成"></h:dgFunOpt>
		<h:dgFunOpt funname="downloadcontract({audittable})" title="审批表下载"></h:dgFunOpt>
		<h:dgToolBar url="contract-management.htm?add&type=${type }" icon="icon-add" funname="add" title="新增"></h:dgToolBar>
		<h:dgToolBar url="contract-management.htm?del" icon="icon-remove" funname="del" title="删除"></h:dgToolBar>
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
		              title:'提示',
		              msg:obj.msg,
		              showType:'show'
		          });
				setTimeout(function(){
		        	  window.location.reload();
		   	      },800);
			});
		}else{
			
			$.dialog.alert("合同已提交，不能重复提交!");
			
		}
	}
	function producecontract(id,flag){
		
			
			$.post("contract-management.htm?produceContract&id="+id,function(data,status){
				var obj = eval('(' + data + ')');
				$.messager.show({
		              title:'提示',
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
			$.dialog.alert("审批表不存在，请生成审批表!");
		}
    }
</script>
</html>