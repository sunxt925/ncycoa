<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
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
	<!-- <p>请选择指标体系和年份:</p> -->
	<fieldset class="pdi_choosefield">
			<legend>选择指标体系和年份</legend>
			<div class="pdi_choose_year">
		<label for="yearsel">年度: </label>
		<input id="yearsel" type="number" name="year"   class="easyui-textbox pdi_input_year"  value="2016">
			<!-- <input id="yearsel" name="year" class="easyui-combobox combobox-f combo-f" style="width: 80px; display: none;" data-options="data:[{text:'2016',value:2016,selected:true}],valueField:'value',textField:'text',onSelect:onYearChanged">
			<input type="hidden"  class="combo-value" value="2016">
			 -->
			
	</div>
		<div class="pdi_choose_target">
			<label for="indexname">指标体系: </label>
		   	<input id="archcode" name="archcode" type="hidden" value="${archcode}">
			<input id="indexname" name="indexname" class="easyui-textbox" type="text" style="width:150px;background-color:white;" readonly="readonly">
			 <a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
			 <span >选择</span></a> 	  
		</div>
		
	<!--  <input type="submit"  class="pdi_query" value="查询"/>  -->
	</fieldset>
	
	 </form>
	 <!--   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">查询</a> -->
	 <!-- <input type="button" id="update"  value="提交"/> -->
</div>
<div class="pdi_input_target" id="content" style="float:right;margin-top: 6px;margin-right:424px;"></div>
<div class="pdi_content">
<form id="formsave" action="datainput.htm?savescoreobj" method="post">  
<div id="score" >
</div>
 </form>
 <!-- <div>
 <table id="score_tb" class="easyui-datagrid" style="width:700px;height:250px;" data-options="singleSelect:true,onClickRow:onClickRow">
 <thead data-options="frozen:true">
 <tr>
 <th data-options="field:'indexname'">指标</th>
 <th data-options="field:'time'">时间段</th>
 </tr>
 </thead>
 <thead>
 <tr>
 <th data-options="field:'obj1'">对象一</th>
 <th data-options="field:'obj2'">对象二</th>
 </tr>
 </thead>
 <tbody>
 <tr><td>指标一</td>
 <td>M01</td>
 <td>
 	<div align="right"><span>计划值</span><input type="text" style="width:100px;"></div><br/>
 	<div align="right"><span>完成值</span><input type="text" style="width:100px;"></div><br/>
 	<div align="right"><span>得分</span><input type="text" style="width:100px;"></div>
 </td>
 <td>
 	<div align="right"><span>计划值</span><input type="text" style="width:100px;"></div><br/>
 	<div align="right"><span>完成值</span><input type="text" style="width:100px;"></div><br/>
 	<div align="right"><span>得分</span><input type="text" style="width:100px;"></div>
 </td></tr>
 </tbody>
 </table>
 </div> -->
</div>

<input type="hidden" value="${message}" id="message"> 
</body>
<script type="text/javascript">
function onClickRow() {
	
}
$(document).ready(function(){ 
	var message=document.getElementById("message");
	if(message.value!=""){
		alert(message.value);
	}
});

$("#indexsel").click(function(){
	//	alert("dsa");
		/*  createwindow('选择体系','objresult.htm?getArch&class=C',500,500,returnorgvalue );  */
		 createwindow('选择体系','datainput.htm?getArch&class=C',500,500,returnorgValue );
	    });
function returnorgValue(data){
	  
		var org = data.code;
		
		
		$('#archcode').val(org.archcode);
		$('#indexname').val(org.archname);
		
		var tagettable=$(".pdi_content");
		
		var archcode = document.getElementById("archcode").value.trim();
			//$("#comple_tab").attr('src','complete_datagrid.jsp?season=' + season + '&archcode=' + archcode );
			 var ajaxCallUrl="datainput.htm?getscoreobjbytype2";
			  $.ajax({
			    type:"post",
			    traditional:true,
			    url:ajaxCallUrl,  
			        data:{archcode:archcode},
			        success:function(data){ 
			         
			          var p=eval(data);
			         // alert(p[0].table);
			          var ddd=document.getElementById("score");
			          ddd.innerHTML="";
			          $('#score').append(p[0].table);
			          $('#score_tb').datagrid({ url:null});
			        
			              
			        },
			        error: function(request) {
			            alert("Connection error");
			        }
			    });
		
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
				cancelVal : '关闭',
				cancel : true/* 为true等价于function(){} */
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