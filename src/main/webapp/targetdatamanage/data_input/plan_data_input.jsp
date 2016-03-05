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
<script type="text/javascript">     
       /*  $(function () {      
            $('#plandata').datagrid({      
                title: 'My Title',      
                width: 600,      
                height: 350,      
                dataType: 'json',      
                url: 'GetAjaxData.ashx?action=GetUserList2',      
                columns: [[]],      
                pagination: true,      
                pageSize: 5,                //每页记录数      
                pageList: [5, 10, 15, 20, 30, 50], //分页记录数数组      
                onLoadSuccess: function (data, param) {      
                          
                }      
            });      
        });   */    
    </script>  
<style>
    .btn1 {
    font-size: 9pt;
    color: #003399;
    border: 1px #003399 solid;
    color: #006699;
    border-bottom: #93bee2 1px solid;
    border-left: #93bee2 1px solid;
    border-right: #93bee2 1px solid;
    border-top: #93bee2 1px solid;
    background-image: url(../images/bluebuttonbg.gif);
    background-color: #e8f4ff;
    cursor: hand;
    font-style: normal;
    width: 60px;
    height: 22px;
}
</style> 
</head>

<body >
<div class="pdi_choose">
<form id="forminput" name="forminput" action="datainput.htm?getplanTarget"  method="post" onsubmit="return check()">
	<!-- <p>请选择指标体系和年份:</p> -->
	<fieldset class="pdi_choosefield">
			<legend>选择指标体系和年份</legend>
		<div class="pdi_choose_target">
			<label for="indexname">指标体系: </label>
		   	<input id="archcode" name="archcode" type="hidden" value="${archcode}">
		   	
			<input id="indexname" name="indexname" class="easyui-textbox" type="text" style="width:150px;background-color:white;" readonly="readonly" value="${indexname}">
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
	<c:if test="${flag == 1}">
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
    </c:if>
	</div>
	<div class="pdi_input_target" style="float:left;margin-top: 6px;">
	<form id="formsave" action="datainput.htm?saveplanobj" method="post" >     
    <div id="plandata">
    
  
    </div> 
    </form>  
	</div>
</div>
<input id="type" value="${type}" type="hidden">
</body>
<script type="text/javascript">
function onClickRow() {
	
}

function check(){
	var archcode = document.getElementById("archcode").value.trim();
	if(archcode==""){
		alert("请选择体系");
		return false;
	}else{
		return true;
	}
}

function saveplan() { 
	var formid="formsave";
	var ajaxCallUrl="datainput.htm?saveplanobj";
	var str_data = $("#" + formid + " input").map(function() {
		return ($(this).attr("name") + '=' + $(this).val());
	}).get().join("&");
	$.ajax({
        cache: true,
        type: "POST",
        url:ajaxCallUrl,
        data:str_data,// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
           alert("更新成功");
           
        }
    });

	} 
function select(indexname,indexCode){
	/* alert(indexname);
	alert(indexCode); */
	//var tagettable=$("#plandata");
	var archcode = document.getElementById("archcode").value;
	var ajaxCallUrl="datainput.htm?getplanobj2";
//	alert(indexCode);
	$.ajax({
		type:"post",
		traditional:true,
		url:ajaxCallUrl,  
        data:{indexname:indexname,archcode:archcode,indexCode:indexCode},
        success:function(data){ 
        	 var p=eval(data);
        	// var p=eval(data);
	        //  alert(p[0].table);
	          var ddd=document.getElementById("plandata");
	          ddd.innerHTML="";
	          $('#plandata').append(p[0].table);
	          $('#plan_tb').datagrid();
        	 
        	 
        	 
        	 
            /*  var columns=eval(p[0].colum);
           //  alert(columns);
             var url=eval(p[1].url); */
             
            // alert(url);
            /*   $('#plandata').datagrid({ 
                width:600,
                  height:350,
                  singleSelect:true,
               	 iconCls:'icon-edit',
                  columns:columns,   
              }).datagrid("loadData", url);  */
              
             // document.getElementById("submit").style.display='block';
              
        },
        error: function(request) {
            alert("Connection error");
        }
    });
}
			$("#inputsubmit").click(function(){
				 alert("success");
				
			});
		
		  $("#indexsel").click(function(){
			//	
				/*  createwindow('选择体系','objresult.htm?getArch&class=C',500,500,returnorgvalue );  */
			
				 var type=$("#type").val();
				//alert(type);
				
				 createwindow('选择体系','datainput.htm?getArch&class='+type,500,500,returnorgValue );
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