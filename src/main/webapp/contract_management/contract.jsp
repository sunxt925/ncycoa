<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>合同管理</title>
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
<form id="formobj" name="formobj" action="contract-management.htm?save" method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${contract.id}">

<table style="width:100%;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 合同编码 </label></td>
		<td class="value"><input class="inputxt" id="code" name="code" value="${contract.code}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>

	<tr>
		<td align="right"><label class="Validform_label"> 合同名称 </label></td>
		<td class="value"><input class="inputxt" id="name" name="name" value="${contract.name}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 归口部门 </label></td>
		<td class="value">
		
		<input class="inputxt" disabled id="relevantDepartment_disp" name="relevantDepartment_disp" value="${relevantDepartment_disp}"></input>
		<input type="hidden" id="relevantDepartment" name="relevantDepartment" value="${relevantDepartment}"></input>
		<h:choose textname="text" hiddenid="id" inputTextname="relevantDepartment_disp" hiddenName="relevantDepartment" url="performance/departselection.jsp" icon="icon-search" title="部门列表" isclear="true"></h:choose>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 合同类别 </label></td>
		<td class="value">
		<select class="inputxt" id="type" name="type">
		<c:if test="${contract.type == 0 || contract.type == null}">
			<option value="0" selected="selected">工程合同</option>
			<option value="1">采购合同</option>
			<option value="2">维修合同</option>
		</c:if>
		<c:if test="${contract.type == 1}">
			<option value="0">工程合同</option>
			<option value="1" selected="selected">采购合同</option>
			<option value="2">维修合同</option>
		</c:if>
		<c:if test="${contract.type == 2}">
			<option value="0">工程合同</option>
			<option value="1">采购合同</option>
			<option value="2" selected="selected">维修合同</option>
		</c:if>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 甲方 </label></td>
		<td class="value"><input class="inputxt" id="partyA" name="partyA" value="${contract.partyA}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 乙方 </label></td>
		<td class="value"><input class="inputxt" id="partyB" name="partyB" value="${contract.partyB}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 合同金额 </label></td>
		<td class="value"><input class="inputxt" id="contractValue" name="contractValue" value="${contract.contractValue}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 合同标的 </label></td>
		<td class="value"><input class="inputxt" id="contractObject" name="contractObject" value="${contract.contractObject}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 签订日期 </label></td>
		<td class="value"><input class="inputxt" id="signingDate" name="signingDate" value="${contract.signingDate}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 执行情况 </label></td>
		<td class="value"><input class="inputxt" id="implementationStage" name="implementationStage" value="${contract.implementationStage}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 完成日期 </label></td>
		<td class="value"><input class="inputxt" id="finishingDate" name="finishingDate" value="${contract.finishingDate}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 续签情况 </label></td>
		<td class="value"><input class="inputxt" id="renewal" name="renewal" value="${contract.renewal}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	<tr>
		<td align="right"><label class="Validform_label"> 存储路径 </label></td>
		<td class="value"><input class="inputxt" id="contractFilePath" name="contractFilePath" value="${contract.contractFilePath}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
</table>

</form>
</body>