<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<%
  String count=request.getAttribute("count").toString();
%>

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
	
	$(document).ready(function(){
		var myDate = new Date();		
		$("input[name='evaluYear']").attr("value",myDate.getFullYear());
	});
	//计算分数
	function makeScore(){
		var detail="";
		var num=parseInt(<%=count%>)+1;
		var i=1;
		var sum=0;
		for(i=1;i<num;i++){
			var p=$("input[name='option"+i+"']:checked").val();
			if(p==null){
				alert("请填写完整");
				break;
				}
			detail=detail+p+",";
			sum+=parseInt(p);
		}
		//$("input[name='score']").attr("value","sum");
		//alert(sum);
		document.getElementById('score').value=sum;
		document.getElementById('detail').value=detail;
		alert($("input[name='detail']").val());
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
	});
	
</script>
</head>
<body style="overflow-x:hidden">
<form id="formobj" name="formobj" action="supplier.htm?evalu_save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" onclick="makeScore()"/> 
<input id="evaluID" name="evaluID" type="hidden" value="${evaluResult.evaluID}">
<br>
<span>选择部门:</span>
<select class="easyui-combobox" id="evaluDepart" name="evaluDepart" style="width:156px;">
		<%
		out.write(request.getAttribute("evaluDepart").toString());
		%>
</select>
<span>&nbsp;&nbsp;选择供应商:</span>
<select class="easyui-combobox" id="evaluSupplier" name="evaluSupplier" style="width:156px;">
		<%
		out.write(request.getAttribute("evaluSupplier").toString());
		%>
</select>
<span>&nbsp;&nbsp;评价年度:</span>
<input id="evaluYear" name="evaluYear" style="width: 50px" disabled="disabled" value="${evaluResult.evaluYear}">
<br><br>
<table style="width:600px;border-spacing:1px;" class="formtable">
	<%
	out.write(request.getAttribute("evaluPanel").toString());
	%>
</table>
<input id="score" name="score" type="hidden">
<input id="detail" name="detail" type="hidden">
<button  onclick="makeScore()" type="button" hidden="true">计算分数</button>
<div style="width: 690px; height: 1px;"></div>

</form>

</body>