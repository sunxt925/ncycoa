<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>安全生产管理</title>
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
	
	/* $(function() {
		$("#formobj").Validform({
			tiptype : 1,
			btnSubmit : "#btn_sub",
			btnReset : "#btn_reset",
			ajaxPost : true,
			callback : function(data) {
				var win = frameElement.api.opener;
				if (data.success == true) {
					frameElement.api.close();
					win.tip("hello");
					
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
		});	 */
		/*  $('#addBtn').bind('click', function(){ 
			
	 	 	 var tr =  $("#add_participant_table_template tr").clone();
		 	 $("#add_participant_table").append(tr);
		 	 resetTrNum('add_participant_table');
		 	
	    });   
		$('#delBtn').bind('click', function(){   
	       $("#add_participant_table").find("input:checked").parent().parent().remove();   
	        resetTrNum('add_participant_table');
	    }); */
	/* });
	 */
</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="objindexitem.htm?save_item"  method="post" >
<input type="hidden" id="btn_sub" class="btn_sub"/> 
 <input style="display:none" type="button" id="btn_ok" class="btn_sub" onclick="ret()"/>
<input id="parentIndexCode" name="parentIndexCode" type="hidden" value="${pcode}">
<table style="width:720px;border-spacing:1px;" class="formtable">
<!--     <tr> -->
<!-- 		<td align="right" width="70px"><label class="Validform_label">编码名称</label></td> -->
<%-- 		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="indexCode" name="indexCode"  value="${item.indexCode}"> --%>
<!-- 		<span class="Validform_checktip"></span> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td align="right" width="70px"><label class="Validform_label">指标名称</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="indexName" name="indexName" value="${item.indexName}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right" width="70px"><label class="Validform_label">指标描述</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="indexDesc" name="indexDesc" value="${item.indexDesc}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right" width="70px"><label class="Validform_label">计算公式</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="valueFunc" name="valueFunc" value="${item.valueFunc}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right" width="70px"><label class="Validform_label">标准分值</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="standardscore" name="standardscore" value="${item.standardscore}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
</table>
<div style="width: 690px; height: 1px;"></div>

</form>

<script type="text/javascript">
function ret(){
	alert("das");
	var api = frameElement.api;
	
	 (api.data)({code:"refresh"});
}
</script>
<table style="display: none">
	<tbody id="add_participant_table_template">
		<tr>
			<td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
			<td align="left"><input name="name" type="text" value=""></td>
			<td align="left"><input name="depart" type="text" value=""></td>
			<td align="left"><input name="task" type="text" value=""></td>
			<td align="left"><input name="memo" maxlength="200" type="text" value=""></td>
		</tr>
	</tbody>
</table>
</body>