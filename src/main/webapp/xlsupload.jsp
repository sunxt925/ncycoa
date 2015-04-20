<%@ page contentType="text/html;charset=utf-8" language="java" import="java.util.*"%>
<%@ taglib uri="/gem-tags" prefix="t"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>Excel导入</title>
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
</head>

<body style="overflow-y: hidden" scroll="no">
	<div class="form">
		<t:upload name="files" buttonText="选择要导入的文件" uploader="${param.uploader}" extend="*.xls;" id="file_upload"></t:upload>
	</div>
	<div class="form" id="filediv" style="height:50px"></div>
</body>
</html>
