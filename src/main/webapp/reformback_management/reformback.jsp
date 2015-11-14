<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>整改反馈管理</title>
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
<form id="formobj" name="formobj" action="reformback_management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<table style="border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 反馈名称 </label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="backname" name="backname"  datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<input type="hidden" id="reformId" name="reformId" value="${reformid}">
		
	<tr>
		<td align="right"><label class="Validform_label"> 原因分析 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="reasonAnalyer" name="reasonAnalyer" datatype="s2-100">
		<span class="Validform_checktip"></span>
		</td>
	</tr>

    <tr>
		<td align="right"><label class="Validform_label"> 纠正/预防措施 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="preMeasure" name="preMeasure" datatype="s2-100">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	 <tr>
		<td align="right"><label class="Validform_label"> 制定人 </label></td>
		<td class="value">
		<input type="hidden" id="zdStaff" name="zdStaff">
		<input class="inputxt" style="width:150px;" id="zdStaffdip" name="zdStaffdip" datatype="s2-100">
		<a id="btn_selectzd" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 制定日期</label></td>
		<td class="value">
		<input class="easyui-datebox" style="width:150px;" id="zdDate" name="zdDate" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	 <tr>
		<td align="right"><label class="Validform_label"> 批准人 </label></td>
		<td class="value">
		<input type="hidden" id="pzStaff" name="pzStaff">
		<input class="inputxt" style="width:150px;" id="pzStaffdip" name="pzStaffdip" datatype="s2-100">
		<a id="btn_selectpz" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 批准日期</label></td>
		<td class="value">
		<input class="easyui-datebox" style="width:150px;" id="pzDateDate" name="pzDateDate">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
	
	<tr>
		<td align="right"><label class="Validform_label"> 完成情况 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="memo" name="memo" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 有效性验证情况</label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="yanzheng" name="yanzheng" >
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 验证人 </label></td>
		<td class="value">
		<input type="hidden" id="yzStaff" name="yzStaff">
		<input class="inputxt" style="width:150px;" id="yzStaffdip" name="yzStaffdip" datatype="s2-100">
		<a id="btn_selectyz" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 验证日期</label></td>
		<td class="value">
		<input class="easyui-datebox" style="width:150px;" id="zrDateDate" name="zrDateDate">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
	<td align="right"><label class="Validform_label"> 附件 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="fileName" name="fileName" >
		 <a id="btn_uploadfile" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">上传文件</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
</table>
<input type="button" id="btn_ok" style="display: none" onclick="ret()">
</form>
<script type="text/javascript">
function ret(){
	 var api = frameElement.api;
	$('#formobj').submit();
	(api.data)({code:"refresh"});
}

$("#btn_uploadfile").click(function(){
	createwindow('文件上传','fileupload/fileupload.jsp',350,130,returnFile);
	    });

function returnFile(data){
	$('#fileName').val(data.code);
   
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
			zIndex :3000,
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
function returnobjValue1(data){

	var array = data.code;
	var staffcodes=array[0].staffcode;
	var staffnames=array[0].staffname;
	
	$('#zdStaff').val(staffcodes);
	$('#zdStaffdip').val(staffnames);
	
}
function returnobjValue2(data){

	var array = data.code;
	var staffcodes=array[0].staffcode;
	var staffnames=array[0].staffname;
	
	$('#pzStaff').val(staffcodes);
	$('#pzStaffdip').val(staffnames);
	
}
function returnobjValue3(data){

	var array = data.code;
	var staffcodes=array[0].staffcode;
	var staffnames=array[0].staffname;
	
	$('#yzStaff').val(staffcodes);
	$('#yzStaffdip').val(staffnames);
	
}
$("#btn_selectzd").click(function(){
	
	createwindow('选择人员','indexmanage/selectstaff.jsp',500,500,returnobjValue1 );
    });
$("#btn_selectpz").click(function(){
	
	createwindow('选择人员','indexmanage/selectstaff.jsp',500,500,returnobjValue2 );
    });
$("#btn_selectyz").click(function(){
	
	createwindow('选择人员','indexmanage/selectstaff.jsp',500,500,returnobjValue3 );
    });   
</script>
</body>