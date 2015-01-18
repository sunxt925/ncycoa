<%@page contentType="text/html;charset=gb2312" language="java" errorPage=""%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>�ϳ��̲�ר����</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
</style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false">
<div style="padding:5px;">
	<label for="taskType">�������:&nbsp;&nbsp;</label>
	<select class="easyui-combobox" id="taskType" name="taskType" data-options="onSelect:onTaskTypeChanged" style="width:200px;">
	<option value="company">��λ��Ч</option>
    <option value="depart">���ż�Ч</option>
    <option value="staff">���˼�Ч</option>
	</select>
	<script type="text/javascript">
		function onTaskTypeChanged(record){
				$('#personTable').datagrid('load',{
					taskType: record.value
				});
		}
	</script>
</div>
</div>
<!-- �� -->
<div data-options="region:'center',border:false">
	<table id="personTable" class="easyui-datagrid" title="��Աѡ��"
	 data-options="fit:true,fitColumns:true,rownumbers:true,singleSelect:true,url:'right_staff_json.jsp',toolbar:'#tbForPersonTable',onClickRow:onClickRow">
	<thead>
       <tr>
       	   <th data-options="field:'ck',checkbox:true"></th>
           <th data-options="field:'staffcode'">Ա������</th>
           <th data-options="field:'staffname'">����</th>
           <th data-options="field:'tasktype'">�������</th>
       </tr>
    </thead>
	</table>
		<script type="text/javascript">
		function onClickRow(rowIndex, rowData){
			$('#orgTable').datagrid('load',{
				staffcode: rowData.staffcode,
				taskType: $('#taskType').combobox('getValue')
			});
		}
	</script>

	<div id="tbForPersonTable" style="padding:5px;height:auto">
    <a id="staffAdd" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">����</a>
    <a id="staffDel" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">ɾ��</a>
	</div>
</div>
<!-- �� -->
<div data-options="region:'east',border:false,split:true" style="width:500px;overflow:hidden;">
	<table id="orgTable" class="easyui-datagrid" title="Ȩ��ѡ��" data-options="fit:true,rownumbers:true,singleSelect:true,url:'right_org_json.jsp',toolbar:'#tbForOrgTable'">
	<thead>
       <tr>
           <th data-options="field:'ck',checkbox:true"></th>
           <th data-options="field:'orgcode'">��������</th>
           <th data-options="field:'orgname'">��������</th>
       </tr>
    </thead>
	</table>

	<div id="tbForOrgTable" style="padding:5px;height:auto">
	<a id="permAdd" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">����</a>
    <a id="permDel" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">ɾ��</a>
	</div>
</div>
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript">
$(function(){
	$('#staffAdd').click(function(){
		$.dialog({
			content : 'url:performance/staffselection.jsp',
			lock : true,
			width : 800,
			height : 480,
			title : '�����Ա',
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
		    	var selected = iframe.getSelectRows();
		    	if(selected == '' || selected == null){
					alert("��ѡ��һ����Ŀ");
					return false;
				}
		    	
		    	$.ajax({
					url: "staffadd.jsp?staffcode=" + selected.staffcode + "&taskType=" + $('#taskType').combobox('getValue'),
					type: "get",
					success: function(data, status){
						$('#personTable').datagrid('load',{
							taskType: $('#taskType').combobox('getValue')
						});
					},
					error: function(jqXHR, status, errThrown){
						alert("status:" + status);
						alert("errThrown:" + errThrown);
					}
				});
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
	});
	
	$('#staffDel').click(function(){
		var selected = $('#personTable').datagrid('getSelected');
		if(selected == null){
			alert("��ѡ��һ����Ŀ");
		}
		$.ajax({
			url: "staffdel.jsp?staffcode=" + selected.staffcode + "&taskType=" + $('#taskType').combobox('getValue'),
			type: "get",
			success: function(data, status){
				$('#personTable').datagrid('load',{
					taskType: $('#taskType').combobox('getValue')
				});
			},
			error: function(jqXHR, status, errThrown){
				alert("status:" + status);
				alert("errThrown:" + errThrown);
			}
		});
	});
	
	$('#permAdd').click(function(){
		$.dialog({
			content : 'url:performance/departselection.jsp',
			lock : true,
			width : 800,
			height : 480,
			title : '��ӻ���',
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
		    	var selected = iframe.getSelectRows();
		    	if(selected == null || selected.length == 0){
					alert("��ѡ��һ����Ŀ");
					return false;
				}
		    	
		    	var staffcode = $('#personTable').datagrid('getSelected').staffcode;
		    	
		    	$.ajax({
					url: "orgadd.jsp?staffcode=" + staffcode + "&taskType=" + $('#taskType').combobox('getValue') + '&orgcode=' + selected[0].id,
					type: "get",
					success: function(data, status){
						$('#orgTable').datagrid('load',{
							staffcode: staffcode,
							taskType: $('#taskType').combobox('getValue')
						});
					},
					error: function(jqXHR, status, errThrown){
						alert("status:" + status);
						alert("errThrown:" + errThrown);
					}
				});
			},
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
	});
	
	$('#permDel').click(function(){
		var selected = $('#orgTable').datagrid('getSelected');
		if(selected == null){
			alert("��ѡ��һ����Ŀ");
		}
		var staffcode = $('#personTable').datagrid('getSelected').staffcode;
		$.ajax({
			url: "orgdel.jsp?staffcode=" + staffcode + "&taskType=" + $('#taskType').combobox('getValue') + '&orgcode=' + selected.orgcode,
			type: "get",
			success: function(data, status){
				$('#orgTable').datagrid('load',{
					staffcode: staffcode,
					taskType: $('#taskType').combobox('getValue')
				});
			},
			error: function(jqXHR, status, errThrown){
				alert("status:" + status);
				alert("errThrown:" + errThrown);
			}
		});
	});
});
</script>
</body>
</html>
