<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ page import="com.entity.system.UserInfo"%>
<%@ page import="com.entity.index.Indexitem"%>
<%@ page import="com.performance.*"%>
<%@ page language="java" import="java.util.*"%>

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
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<body style="width:100%;height:100%;margin:0 3px;padding:0;overflow:hidden">
<%
	try{
		String type = request.getParameter("objType");
		String year = request.getParameter("relateyear");
		String period = request.getParameter("periodcode");
		String indexcode = request.getParameter("indexcode");
		
		ReviewDate date = new ReviewDate(year, period);
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		Map<String, HashMap<String, String>> data = (Map<String, HashMap<String, String>>)request.getSession().getAttribute("data");
		Review review = new Review(user);
		ReviewTask task = review.getReviewTask(date, indexcode, type);
		String content = IndexDataHelper.getDataGrid(task, data);
		
		out.write(content);
		if(data != null){
			data.clear();
			request.getSession().removeAttribute("data");
		}
%>
<form id="forDownloading" action="index_dg_export.jsp" method="post">
<input id="d" name="d" type="hidden"></input>
</form>

<div id="tb" style="padding:3px;height:auto">
	<a id="btnDownload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-put'">����</a> 
	<a id="btnUpload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-putout'">����</a> 
	<a id="btnSubmit" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">����</a>
	<a id="btnGetResult" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-sum'">�÷�</a>
	<a id="btnReload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">����</a>
</div>
<script type="text/javascript">
		function tip(msg) {
			parent.$.dialog.setting.zIndex = 1980;
			parent.$.messager.show({
				title : '��ʾ��Ϣ',
				msg : msg,
				timeout : 1000 * 6
			});
		}
		
		function reloadTable(){
			window.location.reload();
		}

		(function($){
			$("#btnDownload").click(function(){
				var data = getIndexData();
				$('#d').val(JSON.stringify(data));
				var formObj = document.getElementById("forDownloading");
			    formObj.submit();
			});
			$("#btnUpload").click(function(){
				$.dialog({
				    content: 'url:xlsupload.jsp?uploader=' + encodeURIComponent('performance/index_dg_import.jsp?indexcode=${param.indexcode}&periodcode=${param.periodcode}&objType=${param.objType}&relateyear=${param.relateyear}'),
					cache : false,
					button : [{
						name : '��ʼ�ϴ�',
						callback : function() {
							iframe = this.iframe.contentWindow;
							iframe.upload();
							return false;
						},
						focus : true
					}, {
						name : 'ȡ���ϴ�',
						callback : function() {
							iframe = this.iframe.contentWindow;
							iframe.cancel();
						}
					}]
				});
			});

			$("#btnSubmit").click(function() {
				$.dialog.confirm("ȷ������?", function() {
					onSubmit();
				}, function() {
				}, window);
			});
	
			$("#btnReload").click(function() {
				refresh();
			});
			
			$("#btnGetResult").click(function(){
				$.dialog({
				    content: 'url:performance/results.jsp?indexcode=${param.indexcode}&periodcode=${param.periodcode}&objType=${param.objType}&relateyear=${param.relateyear}',
					cache : false,
					lock : true,
					width : 700,
					height : 500,
					title : "�÷ֽ��",
					opacity : 0.3,
					cancelVal : 'ȷ��',
					cancel : true/* Ϊtrue�ȼ���function(){} */
				});
			});
			
			function getIndexData(){
				endEditing();
				
				var data = $('#index_tb').datagrid('getData');
				var index = data.rows[0];
				var results = {};
				for(var attr in index){
					if(attr.indexOf("obj_") === 0){
						results[attr] = {};
					}
				}
				
				var curIndexCode = null;
				for(var i = 0; i<data.rows.length; i++) {
					var row = data.rows[i];
					if(row.indexcode){
						curIndexCode = row.indexcode;
						for(var attr in results){
							if(row[attr]){
								results[attr][curIndexCode] = row[attr];	
							}else{
								results[attr][curIndexCode] = " ";
							}
						}
					}else{
						for(var attr in results){
							if(row[attr]){
								results[attr][curIndexCode] += "," + row[attr];	
							}else{
								results[attr][curIndexCode] += ", ";
							}
						}
					}
				}
				
				results.objtype='${param.objType}';
				results.indexcode="<%= request.getParameter("indexcode")%>";
				results.relateyear="<%= request.getParameter("relateyear")%>";
				results.periodcode="<%= request.getParameter("periodcode")%>";
				
				return results;
			}
			
			function onSubmit(){
				var results = getIndexData();
				$.ajax({
					url: "handler",
					type: "POST",
					data: {d:JSON.stringify(results)},
					success: function(data, status){
						if(data.status == 0){
							tip("����ɹ�");
						}
						else if(data.status == 1){
							tip("��ȡ���ݿ�����ʧ��");
						}
						else if(data.status == 3){
							tip(data.msg);
						}
					},
					error: function(jqXHR, status, errThrown){
						alert("status:" + status);
						alert("errThrown:" + errThrown);
					}
				});
			};	
	
			function refresh() {
				window.location.reload();
			}
		})($);
		
	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true;
		}
		if ($('#index_tb').datagrid('validateRow', editIndex)) {
			$('#index_tb').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index) {
		if (editIndex != index) {
			if (endEditing()) {
				$('#index_tb').datagrid('selectRow', index).datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#para_tb').datagrid('selectRow', editIndex);
			}
		}
	}

	function reject() {
		$('#index_tb').datagrid('rejectChanges');
		editIndex = undefined;
	}
</script>
<%
	} catch (Exception e){
		if(out != null){
			out.write(e.getMessage());
		}
		e.printStackTrace();
	}
%>
</body>
</html>
