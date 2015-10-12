<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<%
  //String count=request.getAttribute("count").toString();
  //String sid=request.getAttribute("supplierID").toString();
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
		obj = document.getElementsByName("supplier");
		check_val = [];
		var l=0;
		for(k in obj){
			if(obj[k].checked){
				check_val.push(obj[k].value);
				l++;
			}
		}
		//alert(l);
		var randomNum=Math.round(Math.random()*l);
		if(randomNum==l){
			randomNum=0;
		}
		
		//alert("随机选择第"+(randomNum+1)+"个供应商："+check_val[randomNum]);
		
        //alert(str);
        //str.split(",");
		
        
		document.getElementById('departs').value=check_val;
		document.getElementById('result').value=check_val[randomNum];
	}
	
	function getdeparts(){
		$.ajax({
	        url: "random.htm?getdeparts",
	        type: "post",
	        dataType: "json",
	        data:{"code":$("#depart").val()},
	        success: function(result) {
	        	$('#dlists').html(result.msg);
	        },
	        error: function(result) {}
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
	});
	
</script>
</head>
<body style="overflow-x:hidden">

<form id="formobj" name="formobj" action="random.htm?save"  method="post">

<%
    //String id=request.getAttribute("sid").toString();
   // String html="<form id='formobj' name='formobj' action='supplier.htm?evalu_save&id="+sid+"' method='post'>";
//	out.write(html);
%>

				

<input id="id" name="id" type="hidden" value="${randomRecord.id}">

<br>

<!--
<span>&nbsp;&nbsp;评价年度:</span>
<input id="evaluYear" name="evaluYear" style="width: 50px" disabled="disabled" value="${evaluResult.evaluYear}">
 -->
<table style="width:600px;border-spacing:1px;" class="formtable">
<tr>
		<td align="right"><label class="Validform_label"> 抽取部门</label></td>
		<td class="value"><input class="inputxt" onchange="getdeparts()" style="width:150px;" id="depart" name="depart" value="${randomRecord.depart}">
		<a id="btn_selectorg" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>

<tr>
		<td align="right"><label class="Validform_label">抽取时间</label></td>
		<td class="value"><input class="easyui-datebox" style="width:250px;" id="randomTime" name="randomTime" value="${randomRecord.randomTime}">
		<span class="Validform_checktip"></span>
		</td>
</tr>

<tr >
	<td align="right"><label class="Validform_label">抽取目的</label></td>
		<td class="value"><textarea class="inputxt" name="purpose" style="overflow-x:hidden;width:400px;height:100px">${randomRecord.purpose}</textarea>
		<span class="Validform_checktip"></span>
		</td>
</tr>
<tr >
<td align="right"><label class="Validform_label">可选供应商</label></td>
<td class="value" id="dlists">
	<%
	//out.write(request.getAttribute("randomPanel").toString());
	%>
</td>
</tr>

<tr >
	<td align="right"><label class="Validform_label">抽取结果</label></td>
		<td class="value"><input id="result" name="result">
		<span class="Validform_checktip"></span>
		</td>
</tr>
</table>
<input id="departs" name="departsList" type="hidden">
<!-- <input id="result" name="result" type="hidden"> -->
<button onclick="makeScore()" type="button">抽取</button>
<input type="submit" id="btn_sub" class="btn_sub"/> 
<div style="width: 690px; height: 1px;"></div>

</form>
<script type="text/javascript">
$("#btn_selectorg").click(function(){
	
	createwindow('选择部门','indexmanage/selectunit.jsp',500,500,returnorgValue );
    });
function returnorgValue(data){
	var org = data.code;
	var codes="";
	if(org.length>1){
		alert("请选择一个部门!");
	}else{
		$('#depart').val(org[0].orgcode);
		$('#depart').change();
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