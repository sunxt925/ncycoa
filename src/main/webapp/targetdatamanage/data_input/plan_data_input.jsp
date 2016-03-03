<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/pdi_style.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

</head>

<body>
<div class="pdi_choose">
<form id="forminput" name="forminput" action="datainput.htm?getplanTarget"  method="post">
	<!-- <p>请选择指标体系和年份:</p> -->
	<fieldset class="pdi_choosefield">
			<legend>选择指标体系和年份</legend>
		<div class="pdi_choose_target">
			<label for="indexname">指标体系: </label>
		   	<input id="archcode" name="archcode" type="hidden" value="${archcode}">
			<input id="indexname" name="indexname" class="easyui-textbox" type="text" style="width:150px;background-color:white;" readonly="readonly">
			 <a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
			 <span >选择</span></a> 	  
		</div>
		<div class="pdi_choose_year">
		<label for="yearsel">年度: </label>
		<input id="yearsel" type="number" name="year"   class="easyui-textbox pdi_input_year"  value="2016">
			<!-- <input id="yearsel" name="year" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{text:'2016',value:2016,selected:true}],valueField:'value',textField:'text',onSelect:onYearChanged">
			<input type="hidden"  class="combo-value" value="2016">
			 -->
			
	</div>
	 <input type="submit"  class="pdi_query" value="查询"/> 
	</fieldset>
	
	 </form>
	 <!--   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">查询</a> -->
	 <!-- <input type="button" id="update"  value="提交"/> -->
</div>
<div class="pdi_content" style="width:900px;">
	<div class="pdi_view_target" style="width:233px;float:left;"> 
	
	<table class="easyui-datagrid" >    
    <thead>    
        <tr>    
            <th data-options="field:'name'" style="width:100px;">指标名称</th>  
        </tr>
    </thead>
    <tbody> 
	    <c:forEach items="${indexList}" var="item" varStatus="status">
			<%-- <li>${item.indexCode}</li>  --%>
			<tr>    
	            <td>
	           <%--  <input type="button" id="selectedobj" value="${item.indexName}(${item.memo})" onclick="select()">
	            --%> 
	            <a id="selectedobj" href="#" onclick="select('${item.indexName}${item.memo}','${item.indexCode}');return false;" >${item.indexName}(${item.memo})</a>
			  </td>  
	        </tr> 
	   </c:forEach>     
    </tbody>
    </table>
	</div>
	<div class="pdi_input_target" style="float:left;margin-top: 6px;">
	
	</div>
</div>
<!-- <table class="easyui-datagrid">    
    <thead>    
        <tr>    
            <th data-options="field:'code'">Code</th>    
            <th data-options="field:'name'">Name</th>    
            <th data-options="field:'price'">Price</th>    
        </tr>    
    </thead>    
    <tbody>    
        <tr>    
            <td>001</td><td>name1</td><td>2323</td>    
        </tr>    
        <tr>    
            <td>002</td><td>name2</td><td>4612</td>    
        </tr>    
    </tbody>    
</table>    --> 
<input type="hidden" value="${message}" id="message"> 
</body>
<script type="text/javascript">
$(document).ready(function(){ 
	var message=document.getElementById("message");
	if(message.value!=""){
		alert(message.value);
	}
});
function select(indexname,indexCode){
	/* alert(indexname);
	alert(indexCode); */
	var tagettable=$(".pdi_input_target");
	var archcode = document.getElementById("archcode").value;
	var ajaxCallUrl="datainput.htm?getplanobj";
	$.ajax({
		type:"post",
		traditional:true,
		url:ajaxCallUrl,  
        data:{indexname:indexname,archcode:archcode},
        success:function(data){ 
          var p=eval(data);
          tagettable.html("");
          var form=$("<form id=\"formsave\" name=\"formsave\" action=\"datainput.htm?saveplanobj\"  method=\"post\"></form>");
          form.appendTo(tagettable);
          var table=$("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" class=\"easyui-datagrid\">");
          table.appendTo(form);
          var thead = $("<thead></thead>");
          var tr=$("<tr></tr>");
          thead.appendTo(table);
          tr.appendTo(thead);
          var count = 0;
          var type ;
          var obj_result=0;
          var plannums=new Array();
          var i=0;
      	  $("<th align=center width=100px data-options=\"field:'code'\">时间段</th>").appendTo(tr); 
  		
  		 var num = 0;
  		 type = p[0].objtype;
 	  	  obj_result=p[1].obj_count;
 	  	  for(var i=0;i<obj_result;i++)
 	  		{
 	  		  plannums[i]=p[2].obj_res[i].planValue;
 	  		//  alert(plannums[i]);
 	  		}
 	  	 count = p[p.length-1].count;
 	  //	 alert("coubnt"+count);
 	  	 for(var i=0;i<count;i++){
 	  		// alert(p[3].obj[i].objectcode);
 	  		$("<td align=center width=100px  data-options=\"field:'"+ p[3].obj[i].uniIndexCode+"'\">"+p[3].obj[i].uniIndexCode+"<input type=\"hidden\" name=\"objcode\" value=\""+p[3].obj[i].objectcode+"\"></td>").appendTo(tr);
 		      
 	  	 }
        
        
          tr.appendTo(thead);
          thead.appendTo(table);
          tr=null;
         var tbody = $("<tbody></tbody>");
         tbody.appendTo(table);
      	// alert(type);
      	 //alert(count);
      	  if(type=="M"){
      		var com_count=0;
        	  for(var i=1;i<=12;i++){
        		  tr=$("<tr></tr>");
        		  if(i<10){
        			  $("<td align=center width=30px >"+"M0"+i+"<input type=\"hidden\" name=\"time\" value=\""+"M0"+i+"\"></td>").appendTo(tr);
             		 
        		  }else{
        			  $("<td align=center width=30px >"+"M"+i+"<input type=\"hidden\" name=\"time\" value=\""+"M"+i+"\"></td>").appendTo(tr);
             		  
        		  }
        		 for(var j=0;j<count;j++)
        			 {
        			 if(plannums.length>1){
        				  $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value='"+plannums[com_count++] +"'></td>").appendTo(tr);
        		        	
        			 }else{
        				 $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value=''></td>").appendTo(tr);
     		        	
        			 }
        			 
        			 }
        				  tr.appendTo(tbody);
        		  
        	  }
        	
          } else if(type=="S"){
        	  var com_count=0;
        	// alert(plannums.length);
        	  for(var i=1;i<=4;i++){
        		  tr=$("<tr></tr>");
        		  $("<td align=center width=30px >"+"S0"+i+"<input type=\"hidden\" name=\"time\" value=\""+"S0"+i+"\"></td>").appendTo(tr);
        		  for(var j=0;j<count;j++)
        		  {
         			 if(plannums.length>1){
        			  $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value='"+plannums[com_count++] +"' ></td>").appendTo(tr);
         			 }
         			else{
       				 $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value=''></td>").appendTo(tr);
    		        	
       			 }
        		  }
        			  tr.appendTo(tbody);
        	  }
          }else if(type=="H"){
        	  var com_count=0;
        	  for(var i=1;i<=2;i++){
        		  tr=$("<tr></tr>");
        		  $("<td align=center width=30px >"+"H0"+i+"<input type=\"hidden\" name=\"time\" value=\""+"H0"+i+"\"></td>").appendTo(tr);
        		  for(var j=0;j<count;j++)
        		  {
         			 if(plannums.length>1){
        			  $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value='"+plannums[com_count++] +"' ></td>").appendTo(tr);
         			 }
         			else{
       				 $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value=''></td>").appendTo(tr);
    		        	
       			 }
        		  }
        		  tr.appendTo(tbody);
        	  }
          }else if(type=="Y"){
        	  var com_count=0;
        	  tr=$("<tr></tr>");
    		  $("<td align=center width=30px >Y00<input type=\"hidden\" name=\"time\" value=\"Y00\"></td>").appendTo(tr);
    		  for(var j=0;j<count;j++)
    		  {
     			 if(plannums.length>1){
    			  $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value='"+plannums[com_count++] +"' ></td>").appendTo(tr);
     			 }
     			else{
   				 $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value=''></td>").appendTo(tr);
		        	
   			 }
    		  }
    		  tr.appendTo(tbody);
          }else if(type=="D"){
        	  var com_count=0;
        	  tr=$("<tr></tr>");
    		  $("<td align=center width=30px >D00<input type=\"hidden\" name=\"time\" value=\"D00\"></td>").appendTo(tr);
    		  for(var j=0;j<count;j++)
    		  {
     			 if(plannums.length>1){
    			  $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" value='"+plannums[com_count++] +"' ></td>").appendTo(tr);
     			 }
     			else{
   				 $("<td align=center width=50px><input name=\"plannumber\" align=center type=\"text\" ></td>").appendTo(tr);
		        	
   			 }
    		  }
    		  tr.appendTo(tbody);
          }
      	  tbody.appendTo(table);
      	  
      	/*  trend.appendTo(table); */
          $("</table>").appendTo(form);
          $("<input type=\"submit\" value=\"提交\">").appendTo(form);
          form.appendTo(tagettable);
          $("<input type=\"hidden\" name=\"indexCode\" value=\""+indexCode+"\">").appendTo(form);
          form.appendTo(tagettable);
          $("<input type=\"hidden\" name=\"archCode\" value=\""+archcode+"\">").appendTo(form);
          form.appendTo(tagettable);
         /*  $("<input type=\"submit\" value=\"提交\">").appendTo(tagettable); */
        },
        error: function(request) {
            alert("Connection error");
        }
    });
}
			$("#inputsubmit").click(function(){
				 alert("success");
				/* var tagettable=$(".pdi_input_target");
				var indexname=this.innerHTML;
				var archcode = document.getElementById("archcode").value;
				var ajaxCallUrl="datainput.htm?getplanobj";
				$.ajax({
					type:"post",
					traditional:true,
					url:ajaxCallUrl,  
	                data:{indexname:indexname,archcode:archcode},
	                success:function(data){ 
	                  var p=eval(data);
	                  tagettable.html("");
	                  var table=$("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" class=\"easyui-datagrid\">");
	                  table.appendTo(tagettable);
	                  var thead = $("<thead></thead>");
	                  var tr=$("<tr></tr>");
	                  tr.appendTo(table);
	                  var count = 0;
	                  var type ;
	              	  $("<th align=center width=100px data-options=\"field:'code'\">时间段</th>").appendTo(tr); 
	          		
	          		 var num = 0;
	                  $(p).each(function(key,value){
	                	  num++;
	                	  if(num>=2 && p.length-num>0){
	                			//alert(num);
	                	  	$("<td align=center width=100px data-options=\"field:'"+value['obj']+"'\">"+value['obj']+"</td>").appendTo(tr);
	                	  }
	                	  if(num==1){
	                		   type = value['objtype'];
	                		 // type=new String(type.getBytes("iso-8859-1"),"GB2312");
	                		   //type =encodeURIComponent(type);
	                	  	  //alert(type);
	                	  }
	                	  if(num==p.length){count = value['count'];}
	                  });
	                  tr.appendTo(table);
	                  tr=null;
	                 
	              	// alert(type);
	              	 //alert(count);
	              	  if(type=="M"){
	              		
		            	  for(var i=1;i<=4;i++){
		            		  tr=$("<tr></tr>");
		            		  $("<td align=center width=30px>M0"+i+"</td>").appendTo(tr);
		            		  for(var j=0;j<count;j++)
		            			  $("<td align=center width=50px><input align=center type=\"number\" ></td>").appendTo(tr);
		            		  tr.appendTo(table);
		            	  }
		            	
		              } 
	              	 trend.appendTo(table);
		              $("</table>").appendTo(tagettable);
	                  
	                },
	                error: function(request) {
	                    alert("Connection error");
	                }
	            }); */
			});
		
		  $("#indexsel").click(function(){
			//	
				/*  createwindow('选择体系','objresult.htm?getArch&class=C',500,500,returnorgvalue );  */
				 createwindow('选择体系','datainput.htm?getArch&class=C',500,500,returnorgValue );
			    });
		  function returnorgValue(data){
			  
				var org = data.code;
				
				
				$('#archcode').val(org.archcode);
				$('#indexname').val(org.archname);
				
			}    
			    
			function createwindow(title, url, width, height,func) {
			//	alert("dsakd");
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
</html>