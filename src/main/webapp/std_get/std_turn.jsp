<%@ page language="java" pageEncoding="UTF-8" import="edu.cqu.ncycoa.domain.GetStd"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<%
String path = request.getContextPath();
%>
<html>
<head>
<title>计划管理</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" href="<%=path%>/jscomponent/validform/css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=path%>/jscomponent/validform/css/tableform.css" type="text/css" />
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/datagrid.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/validform/js/Validform_v5.3.1_ncr_min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/validform/js/Validform_Datatype.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/ajaxfileupload.js"></script>
<script language=
                "javascript" type="text/javascript" src="<%=path%>/js/MyDatePicker/WdatePicker.js">  </script>
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
<form id="formobj" name="formobj" action="stdget.htm?turn" enctype="multipart/form-data" method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${getstd.id}">
<table style="width:100%;border-spacing:1px;"  class="formtable">
	<tr>
		<td align="right" width="30%"><label class="Validform_label"> 转化为标准名称 </label></td>
		<td class="value"  width="70%"><input class="inputxt" style="width:200px;" id="gstStdname" name="gstStdname" value="${getstd.gstStdname}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%
	GetStd stdget=((GetStd)(request.getAttribute("getstd")));
	String stdtype=stdget.getGstStdtype();
	if(stdtype!=null&&!(stdtype.equals(""))){
	String valuename="";if(stdtype.equals("0")){valuename="技术标准";}else if(stdtype.equals("2")){valuename="管理标准";}else if(stdtype.equals("2")){valuename="工作标准";}
	%>
	<tr>
		<td align="right"><label class="Validform_label"> 转化为标准类型 </label></td>
		<td class="value">
		<select class="inputxt" id="gstStdtype" name="gstStdtype" style="width:205px;">
			<option value="${getstd.gstStdtype}"><%=valuename %></option>
			<option value="0">技术标准</option>
			<option value="1">管理标准</option>
			<option value="2">工作标准</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%} else{%>
		<tr>
		<td align="right"><label class="Validform_label"> 转化为标准类型 </label></td>
		<td class="value">
		<select class="inputxt" id="gstStdtype" name="gstStdtype" style="width:205px;">
			<option value="0">技术标准</option>
			<option value="1">管理标准</option>
			<option value="2">工作标准</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<%} %>
		<tr>
		<td align="right"><label class="Validform_label"> 文件发布时间 </label></td>
		<td class="value"><input class="inputxt" style="width:200px;" class="Wdate" type="Wdate" id="gstTurntime" onfocus="new WdatePicker({lang:'zh-cn'})" name="gstTurntime" value="${getstd.gstTurntime}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>

</form>


</body>