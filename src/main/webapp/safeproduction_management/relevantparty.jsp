<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html>
<html>
<head>
<title>��ط�����</title>
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
*{font-size:12px; font-family:΢���ź�,������}
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
<form id="formobj" name="formobj" action="relevantparty_management.htm?save"  method="post">
<input type="hidden" id="btn_sub" class="btn_sub" /> 
<input id="id" name="id" type="hidden" value="${relevantParty.id}">
<table style="width:600px;border-spacing:1px;" class="formtable">
     <tr>
		<td align="right"><label class="Validform_label">��ط�(��λ/����)</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="relevantPartyName" name="relevantPartyName"  value="${relevantParty.relevantPartyName}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">��ط�������</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="responsiblePerson" name="responsiblePerson" value="${relevantParty.responsiblePerson}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">��ط���ҵ��������</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="content" name="content" value="${relevantParty.content}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>

	<tr>
		<td align="right"><label class="Validform_label">��ط���ϵ�绰</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="telephone" name="telephone" value="${relevantParty.telephone}" datatype="m">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">��ط���ַ</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="address" name="address" value="${relevantParty.address}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">�߱�������</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="aptitude" name="aptitude" value="${relevantParty.aptitude}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">�ڱ���λ��ҵ��Ŀ</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="projectname" name="projectname" value="${relevantParty.projectname}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">�ڱ���λ��ҵ����</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="jobplace" name="jobplace" value="${relevantParty.jobplace}">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label">�ڱ���λ��ҵ����</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="jobpersoncount" name="jobpersoncount" value="${relevantParty.jobpersoncount}" datatype="n">
		<span class="Validform_checktip"></span>
		</td>
	</tr>
		<tr>
		<td align="right"><label class="Validform_label">�ڱ���λ��ڹ�����</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id="gkorgcode" name="gkorgcode" value="${relevantParty.gkorgcode}">
		<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">ѡ��</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
	<tr>
		<td align="right"><label class="Validform_label">���׼�¼</label></td>
		<td class="value"><input class="inputxt" style="width:150px;" id=fileName name="fileName" value="${relevantParty.fileName}">
		 <a id="btn_uploadfile" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">�ϴ��ļ�</a>
		<span class="Validform_checktip"></span>
		</td>
	</tr>
</table>
<div style="width: 690px; height: 1px;"></div>

</form>

<script type="text/javascript">
$("#btn_selectobject").click(function(){
	
	createwindow('ѡ����','indexmanage/selectunit.jsp',500,500,returnobjValue );
    });
function returnobjValue(data){
	var org = data.code;
	if(org.length>1){
		alert("���ֻ��ѡ��һ������");
	}else{
		$('#gkorgcode').val(org[0].orgcode);
	}
	
	
}
$("#btn_uploadfile").click(function(){
	createwindow('�ļ��ϴ�','fileupload/fileupload.jsp',350,130,returnFile);
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
			zIndex :2000,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return false;
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
}
</script>
</body>