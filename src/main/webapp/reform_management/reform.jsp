<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>整改管理</title>
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
<form id="formobj" name="formobj" action="reform_management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${reform.id}">
<table style="border-spacing:1px;" class="formtable">
	<tr>
		<td align="right"><label class="Validform_label"> 整改项目名称 </label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="name" name="name" value="${reform.name}" datatype="s2-10">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 下达整改部门 </label></td>
		<td class="value">
		
		<input class="inputxt" style="width:150px;" id="xdzgOrgname" name="xdzgOrgname" value="${XdzgOrgname}">
		<input type="hidden" id="xdzgOrgcode" name="xdzgOrgcode" value="${reform.clOrgcode}">
		<a id="btn_selectorg" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>

	
	<tr>
	<td align="right"><label class="Validform_label"> 要求整改部门 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="clOrgname" name="clOrgname" value="${ClOrgname}" >
		<input type="hidden" id="clOrgcode" name="clOrgcode" value="${reform.clOrgcode}">
		<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 附件</label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="fileName" name="fileName" value="${reform.fileName}" >
		 <a id="btn_uploadfile" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">上传文件</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label"> 整改说明 </label></td>
		<td class="value">
		<input class="inputxt" style="width:150px;" id="memo" name="memo" value="${reform.memo}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>
<input type="hidden" id="flag" name="flag" value="${reform.flag}">
</form>
<script type="text/javascript">
$("#btn_selectobject").click(function(){
	
	createwindow('选择单位','reform_management/selectunit.jsp',600,500,returnobjValue );
    });
    
$("#btn_selectorg").click(function(){
	
	createwindow('选择单位','reform_management/selectunit.jsp',600,500,returnorgValue );
    });
       
    
$("#btn_uploadfile").click(function(){
	createwindow("文件上传","fileupload/fileupload.jsp",350,130,returnFile);
	    });

function returnFile(data){
	$('#fileName').val(data.code);
   
}
function returnorgValue(data){
	$('#xdzgOrgcode').val(data.code[0].orgcode);
	$('#xdzgOrgname').val(data.code[0].orgname);
	
}    
    
function returnobjValue(data){
	var orgcodes="";
	var orgnames="";
	for(var i=0;i<data.code.length;i++){
		orgcodes+=data.code[i].orgcode+",";
		orgnames+=data.code[i].orgname+",";
	}
	$('#clOrgcode').val(orgcodes);
	$('#clOrgname').val(orgnames);
	
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