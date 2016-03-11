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
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:Î¢ÈíÑÅºÚ,ÐÂËÎÌå}
</style>
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<body style="width:100%;height:100%;margin:0 3px;padding:0;overflow:hidden">
<form id="formarch" name="formarch" action="objresult.htm?getTable"  method="post">
<table>
	<tbody><tr>
	<td>
	<div>
		<label for="yearsel">Äê¶È: </label>
		<input id="yearsel" name="year" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{text:'2016',value:2016,selected:true}],valueField:'value',textField:'text',onSelect:onYearChanged">
		<input type="hidden"  class="combo-value" value="2016">
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
		<input id="periodsel" name="season" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{value:'Q01',text:'Ò»¼¾¶È'},{value:'Q02',text:'¶þ¼¾¶È'},{value:'Q03',text:'Èý¼¾¶È',selected:true},{value:'Q04',text:'ËÄ¼¾¶È'}],valueField:'value',textField:'text',onSelect:onPeriodChanged">

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
		<label for="indexname">Ö¸±êÌåÏµ: </label>
	   	<input id="archcode" name="archcode" type="hidden" value="${archcode}">
		<input id="indexname" name="indexname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >Ñ¡Ôñ</span></a>
		 <input type="submit"  value="²éÑ¯"/>
		 <input type="button" id="update"  value="Ìá½»"/>
	</div> 
	</td>
	</tr>
</tbody>
</table>
<!-- </form> -->
<div class="table_all">
<!-- <form id="datatable" name="datatable" action="objresult.htm?save"  method="post"> -->
	<table id="dg" class="easyui-datagrid" style="float:left;
	            url="get_users.php"
	            toolbar="#toolbar" pagination="false"
	            rownumbers="false" fitColumns="true" singleSelect="true">
	        <thead >
				<tr>
					<th rowspan="2" field="firstname">Ö¸±ê±àÂë</th>
					<th rowspan="2" field="lastname">Ö¸±êÃû³Æ</th>
					<c:forEach items="${objList}" var="item">
						<th  colspan="3">${item.uniIndexCode}</th>
						
					</c:forEach>
				</tr>
				
				<tr>
				<c:forEach items="${objList}" var="item">
						<th field="pvalue">¼Æ»®Öµ</th> <th field="rvalue">Íê³ÉÖµ</th>  <th field="score">µÃ·Ö</th>
					</c:forEach>
	            </tr>
	           
	        </thead>
	        <tbody>
	        <c:forEach items="${indexList}" var="item" varStatus="status">         
		    <tr>
		    <td><input style="width: 50px" id="result[${ status.index}].indexCode" name="result[${ status.index}].indexCode" value="${item.indexCode}"></td>
		    <td>${item.indexName}</td>
<%-- 		    <td>${objlist.size()}</td> --%>
		    <c:forEach items="${objList}" var="item">
		    <input style="display:none; width: 50px" id="result[${ status.index}].objectCode" name="result[${ status.index}].objectCode" value="${item.objectcode}">
						<td><input style="width: 50px" id="result[${ status.index}].planValue" name="result[${ status.index}].planValue" value="" ></td> 
						<td><input style="width: 50px" id="result[${ status.index}].realValue" name="result[${ status.index}].realValue" value="" ></td>  
						<td><input style="width: 50px" id="result[${ status.index}].score" name="result[${ status.index}].score" value="" ></td>
					</c:forEach>
		    </tr>				
	    </c:forEach>    
	    
	        </tbody>
	    </table>
	        </div>
	</form>

 <script type="text/javascript">
$("#update").click(function(){
	var form=$("#formarch");
	form.attr("action","objresult.htm?save");
	form.submit();
    });
    
$("#indexsel").click(function(){
	createwindow('Ñ¡ÔñÌåÏµ','objresult.htm?getArch&class=S',500,500,returnorgValue );
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
			cancelVal : '¹Ø±Õ',
			cancel : true/* ÎªtrueµÈ¼ÛÓÚfunction(){} */
		});
}
 </script>
</body>

</html>
