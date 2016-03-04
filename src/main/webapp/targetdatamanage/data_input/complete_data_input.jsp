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
<form id="forminput" name="forminput" action="datainput.htm?getcompleteTarget"  method="post">
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
	 <input type="submit"  class="pdi_query" value="²éÑ¯"/> 
	</fieldset>
	
	 </form>
	 <!--   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">²éÑ¯</a> -->
	 <!-- <input type="button" id="update"  value="Ìá½»"/> -->
</div>
<div class="pdi_content">
	<div class="pdi_view_target">
	 <table class="easyui-datagrid"  style="width:210px;height:641px">
        <thead>
            <tr>
                <th data-options="field:'itemid',width:200">Ö¸±êÃû³Æ</th>
               
            </tr>
        </thead>
        <tbody>
        	 <c:forEach begin="1" end="9" var="item" >
        	   <tr><td><a href="#" onclick="cid_M()">M0${item}</a> </td></tr>
		      </c:forEach>
	        <tr><td ><a href="#" onclick="cid_M()">M10</a> </td></tr>
	        <tr><td><a href="#"  onclick="cid_M()">M11</a> </td></tr>
       	    <tr><td><a href="#"  onclick="cid_M()">M12</a> </td></tr>
       	     <c:forEach begin="1" end="4" var="item" >
        	   <tr><td><a href="#"  onclick="cid_S()">S0${item}</a> </td></tr>
		      </c:forEach>
		       <c:forEach begin="1" end="2" var="item" >
        	   <tr><td><a href="#"  onclick="cid_H()">H0${item}</a> </td></tr>
		      </c:forEach>
		      <tr><td><a href="#"  onclick="cid_Y()">Y00</a> </td></tr>
		      <tr><td><a href="#"  onclick="cid_D()">D00</a> </td></tr>
        </tbody>
    </table>
		
	</div>
	<div class="pdi_input_target"></div>
</div>
</body>
<script type="text/javascript">
function cid_M(){
	var tagettable=$(".pdi_input_target");
	var indexname=this.innerHTML;
	var archcode = document.getElementById("archcode").value;
	var ajaxCallUrl="datainput.htm?getcompleteobjbytype";
	$.ajax({
		type:"post",
		traditional:true,
		url:ajaxCallUrl,  
        data:{archcode:archcode,type:"M"},
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
      	  $("<th align=center width=100px data-options=\"field:'code'\">Ê±¼ä¶Î</th>").appendTo(tr); 
  		
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
    });
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
				alert("dsakd");
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
</html>