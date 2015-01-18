<%@page contentType="text/html;charset=gb2312" language="java" errorPage=""%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>

<body>
<select id="type" class="easyui-combobox" name="type" style="width:200px;">
    <option value="staff">个人</option>
    <option value="company">单位</option>
    <option value="depart">部门</option>
</select>
<a id="btnUpload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-putout'">导入</a>

<script type="text/javascript">
		function tip(msg) {
			$.dialog.setting.zIndex = 1980;
			$.messager.show({
				title : '提示信息',
				msg : msg,
				timeout : 1000 * 6
			});
		}
		
		function reloadTable(){
			//$('#dg').panel('refresh');
		}

		(function($){
			$("#btnUpload").click(function(){
				$.dialog({
				    content: 'url:xlsupload.jsp?uploader=' + encodeURIComponent('performance/init_import.jsp?type=' + $('#type').combobox('getValue')),
					cache : false,
					button : [{
						name : '开始上传',
						callback : function() {
							iframe = this.iframe.contentWindow;
							iframe.upload();
							return false;
						},
						focus : true
					}, {
						name : '取消上传',
						callback : function() {
							iframe = this.iframe.contentWindow;
							iframe.cancel();
						}
					}]
				});
			});
		})($);
</script>
</body>


