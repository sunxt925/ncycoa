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
	<h:datagrid actionUrl="contract-management.htm?dgdata" fit="true" fitColumns="true" queryMode="group" name="contractlist">
		<h:dgColumn field="id" title="id" hidden="true"></h:dgColumn>
		<h:dgColumn field="status" title="status" hidden="true"/>
		<h:dgColumn field="code" title="合同编码" ></h:dgColumn>
		<h:dgColumn field="name" title="合同名称"></h:dgColumn>
		<h:dgColumn field="relevantDepartment" title="实施部门"  dictionary="base_org,orgcode,orgname"></h:dgColumn>
		<h:dgColumn field="type" title="合同类别" query="true" replace="工程合同_0,采购合同_1,维修合同_2,其他合同_3"></h:dgColumn>
		<h:dgColumn field="contactMethod" title="实施方式" query="true" replace="公开招标_0,邀请招标_1,竞争性谈判_2,询价_3,单一来源_4"></h:dgColumn>
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
		<h:dgColumn field="status" title="" style="display:none"></h:dgColumn>
		<h:dgColumn title="操作" field="opt"></h:dgColumn>
		<h:dgFunOpt funname="downloadcontract({audittable})" title="审批表下载"></h:dgFunOpt>
		<h:dgFunOpt funname="downloadfile({contractFilePath})" title="合同下载"></h:dgFunOpt>
		<h:dgToolBar url="contract-management.htm?update" icon="icon-add" funname="update" title="更新台账"></h:dgToolBar>
		<h:dgToolBar url="contract-management.htm?exportExcel" icon="icon-print" funname="exportExcel" title="导出台账"></h:dgToolBar>
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
			createwindow2('提交审批',"contract_management/chooseaudit.jsp?id="+id,300,400,returnValue);
			
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
	function downloadfile(filename){
		if(filename != "null" && filename !=""){
			window.location.href="fileupload/downweb.jsp?filename="+filename;
		}else{
			$.dialog.alert("合同文件不存在!");
		}
    }
	function update(title, actionUrl, gname, width, height){
		var rows = null;
		try{rows=$('#'+gname).datagrid('getSelections');}catch(ex){}
		try{rows=$('#'+gname).treegrid('getSelections');}catch(ex){}
		
		if (!rows || rows.length==0) {
			tip('请选择');
			return;
		}
		if (rows.length > 1) {
			tip('不能同时删除多条记录，请勾选一条记录');
			return;
		}
		if(rows[0].status == 1){
			$.dialog.alert("合同正在审核,不能修改!");
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
		createwindow2('导出Excel',"contract_management/contract_dg_export.jsp",200,200,returnValue2);
	}
	function returnValue(data){
		$.dialog({id:'choose01'}).close();
		 $.dialog.confirm('审核成功',function(){
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
			cancelVal : '关闭',
			cancel : true/* 为true等价于function(){} */
		});
	
}
</script>
</html>