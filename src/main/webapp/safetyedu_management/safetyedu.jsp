<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>安全教育培训</title>
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
<form id="formobj" name="formobj" action="safetyedu_management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${safetyEdu.id}">
<table style="width:600px;border-spacing:1px;" class="formtable">
     <tr>
		<td align="right"><label class="Validform_label">培训时间</label></td>
		<td class="value"><input class="easyui-datebox" style="width:150px;" id="trainTime" name="trainTime"  value="${safetyEdu.trainTime}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">地点</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="place" name="place" value="${safetyEdu.place}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">主题</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="theme" name="theme" value="${safetyEdu.theme}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">讲师情况</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="teacher" name="teacher" value="${safetyEdu.teacher}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">参加人员</label></td>
		<td class="value"><input class="inputxt" style="width:150px;display:none" id=participants name="participants" value="${safetyEdu.participants}">
			<input class="inputxt" style="width:150px;" id="managerName" name="managerName" value="${safetyEdu.participants}">
			<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
    <tr>
		<td align="right"><label class="Validform_label"> 附件</label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="filePath" name="filePath" value="${safetyEdu.filePath}" >
		 <a id="btn_uploadfile" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">上传文件</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">考核结果</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="trainRes" name="trainRes" value="${safetyEdu.trainRes}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
</table>
<div style="width: 690px; height: 1px;"></div>

</form>
<script type="text/javascript">
$("#btn_uploadfile").click(function(){
	createwindow('文件上传','fileupload/fileupload.jsp',350,130,returnFile);
	    });

function returnFile(data){
	$('#filePath').val(data.code);
   
}
$("#btn_selectobject").click(function(){
	
	createwindow('选择人员','indexmanage/selectstaff.jsp',500,500,returnobjValue );
    });
    
 
function returnobjValue(data){

	var array = data.code;
	var staffs="";
	var names="";
	for(var i=0;i<array.length;i++){
		staffs += array[i].staffcode+",";
		names += array[i].staffname+",";
	}
	
	$('#participants').val(staffs);
	$('#managerName').val(names);
	
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