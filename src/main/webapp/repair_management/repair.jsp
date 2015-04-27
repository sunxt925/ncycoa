<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>计划管理</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="jscomponent/validform/js/Validform_Datatype.js"></script>

<script type="text/javascript" src="js/MyDatePicker/WdatePicker.js"></script></HEAD>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
<script type="text/javascript">
	function resetTrNum(tableId) {
		$tbody = $("#" + tableId + "");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function() {
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if (name != null) {
					if (name.indexOf("#index#") >= 0) {
						$this.attr("name",name.replace('#index#',i));
					} else {
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s + 1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}
	
	$(function() {
		$("#formobj").Validform({
			tiptype : 1,
			btnSubmit : "#btn_sub",
			btnReset : "#btn_reset",
			ajaxPost : true,
			callback : function(data) {
				var win = frameElement.api.opener;
				if (data.success == true) {
					frameElement.api.close();
					win.tip(data.msg);
					
				} else {
					if (data.responseText == ''|| data.responseText == undefined){
						$("#formobj").html(data.msg);
					}else{
						$("#formobj").html(data.responseText);
					}
					return false;
				}
				win.reloadTable();
			}
		});
		
		$('#addBtn').bind('click', function(){   
	 		 var tr =  $("#add_participant_table_template tr").clone();
		 	 $("#add_participant_table").append(tr);
		 	 resetTrNum('add_participant_table');
	    });  
		$('#delBtn').bind('click', function(){   
	       $("#add_participant_table").find("input:checked").parent().parent().remove();   
	        resetTrNum('add_participant_table');
	    });
	});
</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="repair_management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${repairAudit.id}">
<table style="width:600px;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 项目名称 </label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="projectName" name="projectName" value="${repairAudit.projectName}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 申请单位 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="apporgCode" name="apporgCode" value="${repairAudit.apporgCode}" datatype="s2-10">
		<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 维修项目预算 </label></td>
		<td class="value">
		 <input  class="inputxt" style="width:150px;" id="repairFree" name="repairFree"   value="${repairAudit.repairFree}" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 维修主要内容 </label></td>
		<td class="value">
		<input  class="inputxt" style="width:150px;" id="repairContent" name="repairContent"   value="${repairAudit.repairContent}" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 申请部门意见 </label></td>
		<td class="value">
		<input  class="inputxt" style="width:150px;" id="apporgOpinion" name="apporgOpinion"   value="${repairAudit.apporgOpinion}" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>

	<input  type="hidden" id="projectType" name="projectType" value="${repairAudit.projectType}">
	<input  type="hidden" id="auditFlag" name="auditFlag" value="${repairAudit.auditFlag}">
</table>
</form>
<script type="text/javascript">
$("#btn_selectobject").click(function(){
	
	createwindow('选择部门','indexmanage/selectunit.jsp',500,500,returnobjValue );
    });
function returnobjValue(data){
	var org = data.code;
	if(org.length>1){
		alert("最多只能选择一个部门");
	}else{
		$('#apporgCode').val(org[0].orgcode);
	}
	
	
}
function createwindow(title, url, width, height,func) {
	
	$.dialog({
			id:'CLHG1976D',
			data:func,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			zIndex :2000,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return true;
			},
			cancelVal : '关闭',
			cancel : true/* 为true等价于function(){} */
		});
}
</script>
</body>