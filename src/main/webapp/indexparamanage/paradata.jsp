<%@ page contentType="text/html;charset=gb2312" language="java" errorPage=""%>
<%@ page import="java.sql.*,com.db.*,com.common.*,com.entity.*,com.entity.system.*"%>
<%@ page import="com.performance.ParaDataHelper" %>
<%@ page import="com.performance.IndexDataHelper" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>南充烟草专卖局</title>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
</head>
<body class="easyui-layout">
<!-- 左 -->
<div data-options="region:'west',border:false" style="width:230px;padding:0 3px">
	<div style="margin:5px 0px;">
		<label for="yearsel">年度: </label>
		<input id='yearsel' class="easyui-combobox" style="width:185px;" data-options="data:<%=IndexDataHelper.getYearJson() %>,valueField:'value',textField:'text',onSelect:onYearChanged" />
		<script type="text/javascript">
			function onYearChanged(record){
				var indexcode = $('#indexcode').val();
				var periodcode = $('#periodsel').combobox('getValue');
				if(indexcode && periodcode){
					$('#datalog').datagrid('load',{
						indexcode: indexcode,
						periodcode: periodcode,
						year: record.value
					});
				}
			}
		</script>
	</div>
	<div style="margin-bottom:5px;">
		<label for="indexname">体系: </label>
	   	<input id="indexcode" name="indexcode" type="hidden"></input>
		<input id="indexname" name="indexcode" type="text" style="width:120px;background-color:white;" readonly="readonly"></input>
		<a id="indexsel" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">选择</a>
	</div>
	<div style="margin-bottom:8px;">
		<label for="periodsel">周期: </label>
		<input id="periodsel" class="easyui-combobox" style="width:185px;height:25px;" data-options="valueField:'periodcode',textField:'periodname',data:[],onSelect:onPeriodChanged"></input>
		<script type="text/javascript">
			function onPeriodChanged(record){
				$('#datalog').datagrid('load',{
					indexcode: $('#indexcode').val(),
					periodcode: record.periodcode,
					year: $('#yearsel').combobox('getValue')
				});
			}
		</script>
	</div> 
	
   <table id="datalog" class="easyui-datagrid" style="width:220px;height:350px" data-options="url:'periodjson2.jsp',singleSelect:true,onClickRow:onClickRow">
        <thead>
           <tr>
               <th data-options="field:'code',hidden:true"></th>
               <th data-options="field:'name',width:100"></th>
               <th data-options="field:'flag',width:100"></th>
           </tr>
       </thead>
   </table>
   <script type="text/javascript">
      function onClickRow(rowIndex, rowData){
    	  $("#dg").attr('src','para_datagrid.jsp?year=' + $('#yearsel').combobox('getValue') + '&periodcode=' + rowData['code'] + '&indexcode=' + $('#indexcode').val());
      }
	</script>
</div>
<!-- 中 -->
<div data-options="region:'center',border:false">
<iframe id="dg" frameborder="0" scrolling="no" style="width:100%;height:99.5%;border:0px none;"></iframe>
</div>
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript">
(function($){
	function returnValue(result){
		if(result.code != $('#indexcode').val()){
			$("#indexcode").val(result.code);
			$("#indexname").val(result.name);
			
			$.ajax({
				async: false,
				url : 'periodjson.jsp',
				type : 'post',
				data : {
					indexcode : result.code
				},
				cache : false,
				success : function(value) {
					$('#periodsel').combobox('loadData', value);
					var periodcode = null;
					for(var i=0; i<value.length; i++){
						if(value[i].selected){
							periodcode = value[i].periodcode;
							break;
						}
					}
					$('#datalog').datagrid('load',{
						indexcode: result.code,
						periodcode: periodcode,
						year: $('#yearsel').combobox('getValue')
					});
				}
			});
		}
	}
	
	function createwindow(title, url, width, height) {
		width = width ? width : 700;
		height = height ? height : 400;
		if (width == "100%" || height == "100%") {
			width = document.body.offsetWidth;
			height = document.body.offsetHeight - 100;
		}
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				data:returnValue,
				content : 'url:' + url,
				lock : true,
				width : width,
				height : height,
				title : title,
				opacity : 0.3,
				cache : false,
				ok : function() {
					$('#btn_ok', this.iframe.contentWindow.document).click();
				},
				cancelVal : '关闭',
				cancel : true/* 为true等价于function(){} */
			});
		}
	}
	
	$('#indexsel').click(function(){
		createwindow('指标体系', 'indexparamanage/selectindex.jsp', 600);
	});
})($);
</script>
</body>
</html>
