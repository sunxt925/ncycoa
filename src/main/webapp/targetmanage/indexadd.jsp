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
	function ret(){
		
		var api = frameElement.api;
		
		 (api.data)({code:"refresh"});
		 $('#sub').click();
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
					return false;
				},
				cancelVal : '关闭',
				cancel : true/* 为true等价于function(){} */
			});
	}
	 
</script>
</head>
<body style="overflow-x:hidden" >
<form id="formobj" name="formobj" action="objindexitem.htm?save_itemdef"  method="post" >
<input type="hidden" id="btn_sub" class="btn_sub"/> 
<input style="display:none" type="button" id="btn_ok" onclick="ret()"/> 
<table style="width:720px;border-spacing:1px;" class="formtable">
     <tr> 
 		<td align="right" width="70px"><label class="validform_label">编码名称</label></td> 
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="stdIndexCode" name="stdIndexCode"  value="${item.stdIndexCode}">
 		<span class="validform_checktip"></span> 
 		</td> 
	</tr> 
	<tr>
		<td align="right" width="70px"><label class="Validform_label">指标名称</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="stdItemName" name="stdItemName" value="${item.stdItemName}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right" width="70px"><label class="Validform_label">所属系统</label></td>
		<td class="value" width="700px">
		<select id="appSystem" name="appSystem">
		<option value="目标系统" selected="selected">目标系统</option>
<!-- 		<option value="绩效系统">绩效系统</option> -->
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">私有标志</label></td>
		<td class="value">
		<input type="radio" name="publicFlag" value="1" />公开
        <input type="radio" name="publicFlag" value="0" checked="checked"/>私有
		</td>
	</tr>
	<tr>
		<td align="right" width="70px"><label class="Validform_label">所属机构</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="belongOrgcode" name="belongOrgcode" value="${item.belongOrgcode}">
		<input class="inputxt" style="width:150px;display:none" id="belongOrgname" name="belongOrgname" value="${item.belongOrgcode}">
		<a id="btn_selectorg" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right" width="70px"><label class="Validform_label">类别</label></td>
		<td class="value" width="700px">
		<select id="memo" name="memo">
		<option value="C" selected="selected">公司</option>
		<option value="D">部门</option>
		<option value="S">个人</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	
</table>
<div style="width: 690px; height: 1px;"></div>
<input type="submit" id="sub"  style="display:none">
</form>
<script type="text/javascript">
function createwindow(title, url, width, height,func) {
	//alert("creat");
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
$("#btn_selectorg").click(function(){
      var url="";
         url="indexmanage/selectunit.jsp";
         createwindow('选择对象',url,800,600,returnobjValue);
   
});

function returnobjValue(data){
	var org = data.code;
	var codes="";
	var names=""
	if(org.length>1){
		alert("请选择单一部门！");
	}else{
		$('#belongOrgcode').val(org[0].orgcode);
		$('#belongOrgname').val(org[0].orgname);
	}
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