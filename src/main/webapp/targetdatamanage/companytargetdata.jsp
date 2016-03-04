<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>�ϳ��̲�ר����</title>
</head>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<style type="text/css">
*{font-size:12px; font-family:΢���ź�,������}
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
		<label for="yearsel">���: </label>
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
		<label for="periodsel">����: </label>
		<input id="periodsel" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{value:'Q01',text:'һ����'},{value:'Q02',text:'������'},{value:'Q03',text:'������',selected:true},{value:'Q04',text:'�ļ���'}],valueField:'value',textField:'text',onSelect:onPeriodChanged">

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
		<label for="indexname">ָ��: </label>
	   	<input id="indexcode" name="indexcode" type="hidden">
		<input id="indexname" name="indexname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >ѡ��</span></a>
		 <input type="submit"  value="��ѯ"/>
	</div> 
	</td>
	</tr>
</tbody>
</table>
</form>
<div class="table_all">

	<table id="dg" class="easyui-datagrid" style="float:left;
	            url="get_users.php"
	            toolbar="#toolbar" pagination="false"
	            rownumbers="false" fitColumns="true" singleSelect="true">
	        <thead >
	            <tr >
	                <th rowspan="2" field="firstname" >ָ�����</th>
	                <th rowspan="2" field="lastname" >ָ������</th>
	                <c:forEach items="${objcodes}" var="item">         
		    <th colspan="3" >${item}</th>				
	    </c:forEach>   
	               
	                
	            </tr>
	           <tr>
	            <%
	                	for(int i=0;i<10;i++){
	                		%> 
	            <th>�ƻ�ֵ</th> <th >���ֵ</th>  <th >�÷�</th> 
	             <%
	                	}
	                %> </tr>
	           
	        </thead>
	    </table>
	   <c:forEach items="${items}" var="item">         
		    <th colspan="3" >${item.indexCode}</th>				
	    </c:forEach>    
	    
    </div>
 <script type="text/javascript">
$("#indexsel").click(function(){
	createwindow('ѡ����ϵ','objresult.htm?getArch&class=C',500,500,returnorgValue );
    });
    
function returnorgValue(data){
	
	var org = data.code;
	alert(org.archname);
	
	$('#indexcode').val(org.archcode);
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
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
}
 </script>
</body>

</html>
