<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/datalist.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/pdi_style.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jscomponent/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<style>
.cid_a{position: relative; text-decoration: none; border-bottom: 1px dotted #ccc; padding-bottom: 5px; text-align:center}
.cid_a:hover{
	border-bottom: 1px solid #ccc;
	
}
</style>

</head>
<body id="div_shade">
 
<div class="pdi_choose">
<form id="forminput" name="forminput" action="datainput.htm?getscoreTarget"  method="post">
	<!-- <p>ÇëÑ¡ÔñÖ¸±êÌåÏµºÍÄê·Ý:</p> -->
	<fieldset class="pdi_choosefield">
			<legend>Ñ¡ÔñÖ¸±êÌåÏµºÍÄê·Ý</legend>
		<div class="pdi_choose_target">
			<label for="indexname">Ö¸±êÌåÏµ: </label>
		   	<input id="archcode" name="archcode" type="hidden" value="${archcode}">
			<input id="indexname" name="indexname" class="easyui-textbox" type="text" style="width:150px;background-color:white;" readonly="readonly">
			 <a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
			 <span >Ñ¡Ôñ</span></a> 	  
		</div>
		<div class="pdi_choose_year">
		<label for="yearsel">Äê¶È: </label>
		<input id="yearsel" type="number" name="year"   class="easyui-textbox pdi_input_year"  value="2016">
			<!-- <input id="yearsel" name="year" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{text:'2016',value:2016,selected:true}],valueField:'value',textField:'text',onSelect:onYearChanged">
			<input type="hidden"  class="combo-value" value="2016">
			 -->
			
	</div>
	<!--  <input type="submit"  class="pdi_query" value="²éÑ¯"/>  -->
	</fieldset>
	
	 </form>
	 <!--   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">²éÑ¯</a> -->
	 <!-- <input type="button" id="update"  value="Ìá½»"/> -->
</div>
<div class="pdi_input_target" id="content" style="float:right;margin-top: 6px;margin-right:424px;"></div>
<div class="pdi_content">
	<div class="pdi_view_target" style="width:210px;height:641px">
	 <table class="easyui-datagrid"  style="width:210px;height:641px">
        <thead>
            <tr>
                <th data-options="field:'itemid',width:200">Ê±¼ä</th>
               
            </tr>
        </thead>
        <tbody>
        	 <c:forEach begin="1" end="9" var="item" >
        	   <tr><td><a href="#" onclick="cid_M(this.innerHTML)">M0${item}</a> </td></tr>
		      </c:forEach>
	        <tr><td ><a href="#" onclick="cid_M(this.innerHTML)">M10</a> </td></tr>
	        <tr><td><a href="#"  onclick="cid_M(this.innerHTML)">M11</a> </td></tr>
       	    <tr><td><a href="#"  onclick="cid_M(this.innerHTML)">M12</a> </td></tr>
       	     <c:forEach begin="1" end="4" var="item" >
        	   <tr><td><a href="#"  onclick="cid_M(this.innerHTML)">S0${item}</a> </td></tr>
		      </c:forEach>
		       <c:forEach begin="1" end="2" var="item" >
        	   <tr><td><a href="#"  onclick="cid_M(this.innerHTML)">H0${item}</a> </td></tr>
		      </c:forEach>
		      <tr><td><a href="#"  onclick="cid_M(this.innerHTML)">Y00</a> </td></tr>
		      <tr><td><a href="#"  onclick="cid_M(this.innerHTML)">D00</a> </td></tr>
        </tbody>
    </table>
		
	</div>
	
</div>
<input type="hidden" value="${message}" id="message"> 
</body>
<script type="text/javascript">
$(document).ready(function(){ 
	var message=document.getElementById("message");
	if(message.value!=""){
		alert(message.value);
	}
});
function cid_M(season){

	var tagettable=$(".pdi_input_target");
	//var season=this.innerHTML;
	//alert(season);
	var type;
	/* if(season.indexOf("M")>-1){
		type="M";
	}else if(season.indexOf("S")>-1){
		type="S";
	}else if(season.indexOf("H")>-1){
		type="H";
	}else if(season.indexOf("Y")>-1){
		type="Y";
	}else if(season.indexOf("D")>-1){
		type="D";
	} */
	document.getElementById('content').innerHTML = "";
	var archcode = document.getElementById("archcode").value.trim();
	if(archcode==""){
		alert("ÇëÑ¡ÔñÌåÏµ");
	}else{
	var ajaxCallUrl="datainput.htm?getscoreobjbytype";
	$.ajax({
		type:"post",
		traditional:true,
		url:ajaxCallUrl,  
        data:{archcode:archcode,type:season},
        success:function(data){ 
         
        	var p=eval(data);
        	 var form=$("<form id=\"formsave\" name=\"formsave\" action=\"datainput.htm?savescoreobj\"  method=\"post\"></form>");
             form.appendTo(tagettable);
        	  var table=$("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" class=\"easyui-datagrid\">");
              table.appendTo(form);
              var thead = $("<thead></thead>");
              var tr=$("<tr></tr>");
              thead.appendTo(table);
              tr.appendTo(thead);
             // var count = 0;
              var type ;
              $("<th align=center width=100px data-options=\"field:'code'\">Ê±¼ä¶Î</th>").appendTo(tr); 
              var obj_count = p[0].obj_count;
             
              for(var i=0;i<obj_count;i++){
         
               $("<td align=center width=100px data-options=\"field:'"+p[1].objs[i].uniIndexCode+"'\">"+p[1].objs[i].uniIndexCode+"<input type='hidden' name='objectcode' value='"+p[1].objs[i].objectcode+"'></td>").appendTo(tr);
                 
              }
              tr.appendTo(thead);
              thead.appendTo(table);
              tr=null;
             var tbody = $("<tbody></tbody>");
             tbody.appendTo(table);
              var index_count = p[2].index_count;
           	
              var completenums=new Array();
              var res_count=p[4].res_count;
              var com_count=0;
              if(res_count!=0){
            	  for(var i=0;i<res_count;i++)
            		  {
            		 // alert(p[5].obj_res[i].realValue);
            		  completenums[i]=p[5].obj_res[i].score;
            		  }
            		 
              }
              for(var i=0;i<index_count;i++){
            	  tr=$("<tr></tr>");
            	//  alert(p[3].indexs[i].indexName);
                  $("<td align=center width=100px data-options=\"field:'"+p[3].indexs[i].indexName+"'\">"+p[3].indexs[i].indexName+"<input type=\"hidden\" name=\"indexcode\" value=\""+p[3].indexs[i].indexCode+"\"></td>").appendTo(tr);
                 
                  for(var j=0;j<obj_count;j++)
     			 	{
                	
     			 	if(completenums.length>1){
     			 		
     				  $("<td align=center width=50px><input name=\"scorenumber\" align=center type=\"text\" value='"+completenums[com_count++] +"'></td>").appendTo(tr);
     				
     				 }else{
     					 $("<td align=center width=50px><input name=\"scorenumber\" align=center type=\"text\" value=''></td>").appendTo(tr);	
     			 	}
     			 
     			 }
     				  tr.appendTo(tbody);
                 }

              tbody.appendTo(table);
            
            	/*  trend.appendTo(table); */
                $("</table>").appendTo(form);
                 $("<input class='com_submit' type=\"submit\" value=\"Ìá½»\">").appendTo(form);
                form.appendTo(tagettable);
                $("<input type=\"hidden\" name=\"season\" value=\""+season+"\">").appendTo(form);
                form.appendTo(tagettable);
                $("<input type=\"hidden\" name=\"archCode\" value=\""+archcode+"\">").appendTo(form); 
                form.appendTo(tagettable);
               // alert(obj_count); 
              
        },
        error: function(request) {
            alert("Connection error");
        }
    });
	}
}
$("#indexsel").click(function(){
	//	alert("dsa");
		/*  createwindow('Ñ¡ÔñÌåÏµ','objresult.htm?getArch&class=C',500,500,returnorgvalue );  */
		 createwindow('Ñ¡ÔñÌåÏµ','datainput.htm?getArch&class=C',500,500,returnorgValue );
	    });
function returnorgValue(data){
	  
		var org = data.code;
		
		
		$('#archcode').val(org.archcode);
		$('#indexname').val(org.archname);
		
	}    
	    
	function createwindow(title, url, width, height,func) {
		//alert("dsakd");
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
	$.parser.parse();

		  </script>
		  <style>
		  .com_submit{
		 display: block;
    float: left;
    position: relative;
    height: 25px;
    width: 80px;
    margin: 0 10px 18px 0;
     
    text-decoration: none;
    font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-weight: bold;
    line-height: 25px;
    text-align: center;
		  }
		  .com_submit:hover {
    color: #555;
   border-bottom: 4px solid #b2b1b1; 
    background: #eee;
}
 
.com_submit:hover { background: #e2e2e2; }</style>
</html>