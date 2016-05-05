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
<body style="overflow-x:hidden" onload="init()">
<form id="formobj" name="formobj" action="objindexitem.htm?save_item"  method="post" >
<input type="hidden" id="btn_sub" class="btn_sub"/> 
<input style="display:none" type="button" id="btn_ok" onclick="ret()"/> 
<input id="parentIndexCode" name="parentIndexCode" type="hidden" value="${pcode}">
<table style="width:720px;border-spacing:1px;" class="formtable">
<!--     <tr> -->
<!-- 		<td align="right" width="70px"><label class="Validform_label">编码名称</label></td> -->
<%-- 		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="indexCode" name="indexCode"  value="${item.indexCode}"> --%>
<!-- 		<span class="Validform_checktip"></span> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td align="right" width="70px"><label class="Validform_label">指标选择：</label></td>
		<td class="value" width="700px">
	   	<input id="archcode" name="archcode" type="hidden" value="${item.parentIndexCode}">
		<input id="indexname" name="indexName" type="text" style="width:150px;background-color:white;" value="${item.indexName}">
		<a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >选择</span></a>
<!-- 		 <input type="submit"  value="查询"/> -->
	</td>
	</tr>
	<tr>
		<td align="right" width="70px"><label class="Validform_label">指标描述</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="indexDesc" name="indexDesc" value="${item.indexDesc}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr >
		<td align="right"><label class="Validform_label">计分周期</label></td>
		<td class="value">
		<select class="inputxt" id="scorePeriod" name="scorePeriod" style="width:156px;">
			<option value="M00">月度</option>
			<option value="S00">季度</option>
			<option value="H00">半年</option>
			<option value="Y00">年度</option>
			<option value="D00">随机抽查</option>
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<c:choose> 
	<c:when test="${item.alarmFlag==0}">
	<tr>
		<td align="right"><label class="Validform_label">报警标志</label></td>
		<td class="value">
		<input type="radio" name="alarmFlag" value="1" />报警
        <input type="radio" name="alarmFlag" value="0" checked="checked"/>不报警
		</td>
	</tr>
	</c:when>
	<c:when test="${item.alarmFlag==1}">
	<tr>
		<td align="right"><label class="Validform_label">报警标志</label></td>
		<td class="value">
		<input type="radio" name="alarmFlag" value="1" checked="checked" />报警
        <input type="radio" name="alarmFlag" value="0"/>不报警
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
		<td align="right"><label class="Validform_label">报警标志</label></td>
		<td class="value">
		<input type="radio" name="alarmFlag" value="1" />报警
        <input type="radio" name="alarmFlag" value="0" />不报警
		</td>
	</tr>
	</c:otherwise>
	</c:choose>
	<tr id="vf">
		<td align="right" width="70px"><label class="Validform_label">报警公式</label></td>
		<td class="value" width="700px"><input class="inputxt" style="width:150px;" id="valueFunc" name="valueFunc" value="${item.valueFunc}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<c:choose>
	<c:when test="${item.examFlag==0}">
	<tr>
		<td align="right"><label class="Validform_label">考核标志</label></td>
		<td class="value">
		<input type="radio" name="examFlag" value="1" />考核
        <input type="radio" name="examFlag" value="0" checked="checked"/>不考核
		</td>
	</tr>
	</c:when>
	<c:when test="${item.examFlag==1}">
	<tr>
		<td align="right"><label class="Validform_label">考核标志</label></td>
		<td class="value">
		<input type="radio" name="examFlag" value="1"  checked="checked"/>考核
        <input type="radio" name="examFlag" value="0"/>不考核
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
		<td align="right"><label class="Validform_label">考核标志</label></td>
		<td class="value">
		<input type="radio" name="examFlag" value="1"/>考核
        <input type="radio" name="examFlag" value="0"/>不考核
		</td>
	</tr>
	</c:otherwise>
	</c:choose>
	<tr id="et">
		<td align="right" width="70px"><label class="Validform_label">考核时段</label></td>
		<td class="value" width="700px">
<%-- 		<input class="inputxt" style="width:150px;" id="examTime1" name="examTime1" value="${item.examTime}"> --%>
		<select class="inputxt" id="examTime" name="examTime" style="width:156px;">
			
			
		</select>
		<span class="Validform_checktip"></span>
		</td>
	</tr>

	
	
</table>
<div style="width: 690px; height: 1px;"></div>
<input type="submit" id="sub"  style="display:none">
</form>

<script type="text/javascript">
$('#scorePeriod').change(function(){ 
	var opt=$('#scorePeriod').val();
	var objSelectNow=document.getElementById("examTime");
	if(opt=="M00"){
		var inner="<option value='M01'>1月</option><option value='M02'>2月</option><option value='M03'>3月</option><option value='M04'>4月</option><option value='M05'>5月</option><option value='M06'>6月</option><option value='M07'>7月</option><option value='M08'>8月</option><option value='M09'>9月</option><option value='M10'>10月</option><option value='M11'>11月</option><option value='M12'>12月</option><option value='M00'>全年</option>"
	}else if(opt=="S00"){
		var inner="<option value='S01'>1季度</option><option value='S02'>2季度</option><option value='S03'>3季度</option><option value='S04'>4季度</option><option value='S00'>全年</option>";
	}else if(opt=="H00"){
		var inner="<option value='H01'>上半年</option><option value='H02'>下半年</option><option value='H00'>全年</option>";
	}else if(opt=="Y00"){
		var inner="<option value='Y00'>全年</option>";
	}
	
	objSelectNow.innerHTML=inner;
	}) 

function init(){
	//alert("${item.scorePeriod}");
	var p1=$("input[name='alarmFlag']:checked").val();
    if(p1=="0"){
   	 //$("tr[id='tr1']").attr("hidden","true");
   	 //$("tr[id='tr1']").attr("style","display:none");
   	 $("tr[id='vf']").attr("style","display:none");
    }else if(p1=="1"){
   	 
   	 $("tr[id='vf']").attr("style","");
    }
    
    var p2=$("input[name='examFlag']:checked").val();
    if(p2=="0"){
   	 $("tr[id='et']").attr("style","display:none");
    }else if(p1=="1"){
   	 
   	 $("tr[id='et']").attr("style","");
    }
    
/*     var sel = document.getElementById("scorePeriod");
    var sp="${item.scorePeriod}"; */
    
    var opt="${item.scorePeriod}";
	var objSelectNow=document.getElementById("examTime");
	if(opt=="M00"){
		var inner="<option value='M01'>1月</option><option value='M02'>2月</option><option value='M03'>3月</option><option value='M04'>4月</option><option value='M05'>5月</option><option value='M06'>6月</option><option value='M07'>7月</option><option value='M08'>8月</option><option value='M09'>9月</option><option value='M10'>10月</option><option value='M11'>11月</option><option value='M12'>12月</option><option value='M00'>全年</option>"
	}else if(opt=="S00"){
		var inner="<option value='S01'>1季度</option><option value='S02'>2季度</option><option value='S03'>3季度</option><option value='S04'>4季度</option><option value='S00'>全年</option>";
	}else if(opt=="H00"){
		var inner="<option value='H01'>上半年</option><option value='H02'>下半年</option><option value='H00'>全年</option>";
	}else if(opt=="Y00"){
		var inner="<option value='Y00'>全年</option>";
	}
	
	objSelectNow.innerHTML=inner;
    $("#scorePeriod option[value='${item.scorePeriod}']").attr("selected","true");
    $("#examTime option[value='${item.examTime}']").attr("selected","true");
 /*    if(sp=="M00"){
    	alert(sel.options[0].checked);
    	sel.options[0].value="123";
    }else if(sp=="S00"){
    	sel.options[1].selected="true";
    }else if(sp=="H00"){
    	sel.options[2].checked=checked;
    }else if(sp=="Y00"){
    	sel.options[3].checked=checked;
    }else{
    	sel.options[4].checked=checked;
    } */
    //scorePeriod
   // $("option[value='${item.scorePeriod}']").attr("checked","checked");
}
$("input[name='alarmFlag']").change(function(){
	   var p1=$("input[name='alarmFlag']:checked").val();
	   
	     if(p1=="0"){
	    	 $("tr[id='vf']").attr("style","display:none");
	     }else if(p1=="1"){
	    	 
	    	 $("tr[id='vf']").attr("style","");
	     }
});

$("input[name='examFlag']").change(function(){
	   var p1=$("input[name='examFlag']:checked").val();
	   
	     if(p1=="0"){
	    	 $("tr[id='et']").attr("style","display:none");
	     }else if(p1=="1"){
	    	 
	    	 $("tr[id='et']").attr("style","");
	     }
});

$("#indexsel").click(function(){
	createwindow('选择体系','objindexitem.htm?getIndexToArch&class=${classT}',500,500,returnorgValue );
    });
    
function returnorgValue(data){
	
	var org = data.code;
	//alert(org.archname);
	
	$('#archcode').val(org.archcode);
	$('#indexname').val(org.archname);
	
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