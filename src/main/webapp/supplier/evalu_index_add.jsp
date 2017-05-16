<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>供应商管理</title>
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
					win.tip("使用记录添加成功");
					
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
<form id="formobj" name="formobj" action="supplier.htm?evalu_index_save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 

<table style="width:600px;border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 评价年份</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="evaluYear" name="evaluYear" value="${evaluDefine.evaluYear}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
	<td align="right"><label class="Validform_label">指标编码</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="indexCode" name="indexCode" value="${evaluDefine.indexCode}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">指标类别</label></td>
		<td class="value">
		<input type="radio" name="isparent" value="1" />一级
        <input type="radio" name="isparent" value="0" />二级
		</td>
	</tr>
	<tr>
	<td align="right"><label class="Validform_label">指标内容</label></td>
		<td class="value"><textarea class="inputxt" name="indexName" style="overflow-x:hidden;width:400px;height:100px">${evaluDefine.indexName}</textarea>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr id="tr1">
	<td align="right"><label class="Validform_label">选项定义</label></td>
		<td class="value"><textarea class="inputxt" name="indexOption" style="overflow-x:hidden;width:400px;height:100px">${evaluDefine.indexOption}</textarea>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr id="tr2">
		<td align="right"><label class="Validform_label">说明</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="instruction" name="instruction" value="${evaluDefine.instruction}" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	
</table>
<div style="width: 690px; height: 1px;"></div>

</form>

</body>
<script>
$(document).ready(function(){
	$("textarea[name='indexOption']").attr("value","()=()");
});

$("input[name='isparent']").change(function(){
	   var p1=$("input[name='isparent']:checked").val();
	   
	     if(p1=="1"){
	    	 //$("tr[id='tr1']").attr("hidden","true");
	    	 //$("tr[id='tr1']").attr("style","display:none");
	    	 $("tr[id='tr2']").attr("style","display:none");
	    	 $("tr[id='tr1']").hide();
	     }else if(p1=="0"){
	    	 $("tr[id='tr1']").show();
	    	 //$("tr[id='tr1']").attr("style","");
	    	 $("tr[id='tr2']").attr("style","");
	     }
});

</script> 