<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ÄÏ³äÑÌ²Ý×¨Âô¾Ö</title>
</head>
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:Î¢ÈíÑÅºÚ,ÐÂËÎÌå}
</style>
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<body style="width:100%;height:100%;margin:0 3px;padding:0;overflow:hidden">
<table>
	<tbody><tr>
	<td>
	<div>
		<label for="yearsel">Äê¶È: </label>
		<input id="yearsel" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{text:'2016',value:2016,selected:true}],valueField:'value',textField:'text',onSelect:onYearChanged">
		<input type="hidden" class="combo-value" value="2016">
		<script type="text/javascript">
		function onYearChanged(record){
			var period = $('#periodsel').combobox('getValue');
			var indexcode = $("#indexcode").val();
			if(period && indexcode){
				$("#indexdg").attr('src','index_datagrid.jsp?objType=depart&periodcode=' + period + '&indexcode=' + indexcode + '&relateyear=' + record.value);
			}
		}
		</script>
	</div>
	</td>
	
	<td>
	<div>
		<label for="periodsel">¼¾¶È: </label>
		<input id="periodsel" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{value:'Q01',text:'Ò»¼¾¶È'},{value:'Q02',text:'¶þ¼¾¶È'},{value:'Q03',text:'Èý¼¾¶È',selected:true},{value:'Q04',text:'ËÄ¼¾¶È'}],valueField:'value',textField:'text',onSelect:onPeriodChanged">

		<input type="hidden" class="combo-value" value="M03">
		<script type="text/javascript">
		function onPeriodChanged(record){
			var year = $('#yearsel').combobox('getValue');
			var indexcode = $("#indexcode").val();
			if(year && indexcode){
				$("#indexdg").attr('src','index_datagrid.jsp?objType=depart&periodcode=' + record.value + '&indexcode=' + indexcode + '&relateyear=' + year);
			}
		}
		</script>
	</div>
	</td>
	
	<td>
	<div>
		<label for="indexname">Ö¸±ê: </label>
	   	<input id="indexcode" name="indexcode" type="hidden">
		<input id="indexname" name="indexcode" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		<span >Ñ¡Ôñ</span></a>
	</div> 
	</td>
	</tr>
</tbody>
</table>
<div class="table_all">
	<table id="dg" class="easyui-datagrid" style="width:700px;float:left;
	            url="get_users.php"
	            toolbar="#toolbar" pagination="false"
	            rownumbers="false" fitColumns="true" singleSelect="true">
	        <thead>
	            <tr>
	                <th rowspan="2" field="firstname" >Ö¸±ê±àÂë</th>
	                <th rowspan="2" field="lastname" >Ö¸±êÃû³Æ</th>
	            </tr>
	        </thead>
	    </table>
	<table id="target" class="easyui-datagrid" style="width:700px;
	            url="get_users.php"
	            toolbar="#toolbar" pagination="false"
	            rownumbers="false" fitColumns="true" singleSelect="true" >
	        <thead>
	            <tr>
	              <th colspan="3" >¶ÔÏóÒ»</th> 
		<c:forEach items="${items}" var="item">         
		    <th colspan="3" >${item.indexCode}</th>				
	    </c:forEach>    
	            </tr>
	            <tr>
	            <th>¼Æ»®Öµ</th> <th >Íê³ÉÖµ</th>  <th >µÃ·Ö</th> 
	            </tr>
	            
	        </thead>
	  </table>
    </div>
</body>
</html>