<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
                  request.getServerName()+":"+
		          request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>四川南充烟草专卖</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>

    <body class="easyui-layout">
    <div data-options="region:'north'" style="height:30px;">
    
   <label for="indexname">目标体系: </label>
	   	<input id="archcode" name="archcode" type="hidden" value="${archcode}">
		<input id="indexname" name="indexname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >选择</span></a>
		 
	 <label for="objname">对象选择: </label>
	   	<input id="objectcode" name="objectcode" type="hidden" value="${objectcode}">
		<input id="objname" name="objname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="objsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >选择</span></a>
		 
   <label for="objname">指标选择: </label>
	   	<input id="indexitemcode" name="indexitemcode" type="hidden" value="${indexcode}">
		<input id="indexitemname" name="indexitemname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="indexitemsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >选择</span></a>
	<label >时间选择: </label>
		<input id="examTime" name="examTime" type="hidden" value="${examTime}">
	 <select class="inputxt" id="season" name="season" style="width:156px;">
<!-- 		<option value="S01">一季度</option> -->
<!-- 		<option value="S02">二季度</option> -->
<!-- 		<option value="S03">三季度</option> -->
<!-- 		<option value="S04">四季度</option> -->
		</select>
<!--        <span> 年度选择：</span><input id="startyear" name="startyear" class="easyui-textbox"> -->
<!--        <input id="endyear" name="endyear" class="easyui-textbox"> -->
       <input type="button" onclick="getShow()" value="查看"> 
    </div>
    
    <div data-options="region:'center',split:true" style="padding:5px;background:#fff;">
		<iframe src="" name="bar_gragh" style="border: 0px;width: 100%;height: 95%">
	    </iframe>
	</div>
	<input type="hidden" id="year" value="">
	<input type="hidden" id="month" value="">
	<input type="hidden" id="indexcode" value="">
	<input type="hidden" id="standardscore" value="">
	
    </body>
    <script type="text/javascript">

    function changeIndex(){
    	var opt=$('#examTime').val().substring(0,3);
    	var objSelectNow=document.getElementById("season");
    	if(opt=="M00"){
    		var inner="<option value='M01'>1月</option><option value='M02'>2月</option><option value='M03'>3月</option><option value='M04'>4月</option><option value='M05'>5月</option><option value='M06'>6月</option><option value='M07'>7月</option><option value='M08'>8月</option><option value='M09'>9月</option><option value='M10'>10月</option><option value='M11'>11月</option><option value='M12'>12月</option>"
    	}else if(opt=="S00"){
    		var inner="<option value='S01'>1季度</option><option value='S02'>2季度</option><option value='S03'>3季度</option><option value='S04'>4季度</option>";
    	}else if(opt=="H00"){
    		var inner="<option value='H01'>上半年</option><option value='H02'>下半年</option>";
    	}else if(opt=="Y00"){
    		var inner="<option value='Y00'>全年</option>";
    	}
    	
    	objSelectNow.innerHTML=inner;
    } 
    function getShow(){
        var startyear=$("#startyear").val();
        var endyear=$("#endyear").val();
       
        if($("#archcode").val()==""){
        	alert("请选择体系");
        }else if($("#objectcode").val()==""){
        	alert("请选择对象");
        }else if($("#indexitemcode").val()==""){
        	alert("请选择指标");
        } 
        if($("#archcode").val()!=""&&$("#startyear").val()!=""&&$("#endyear").val()!=""){
        	var archcode=$("#archcode").val();
        	var indexcode=$("#indexitemcode").val();
        	var start=$("#startyear").val();
        	var end=$("#endyear").val();
        	var objcode=$("#objectcode").val();
        	var season=$("#season").val();
        	window.open("targetdatamanage/bar_gragh.jsp?archcode="+archcode+"&objcode="+objcode+"&indexcode="+indexcode+"&type=t&season="+season,"bar_gragh");
        	//window.open("targetdatamanage/bar_gragh.jsp?archcode="+archcode+"&objcode="+objcode+"&startyear="+start+"&endyear="+end,"bar_gragh");
        }
   }
        
        function selectyear(record){
        	 var year=record.value;
        	 $("#year").val(year);
        }
        $("#indexsel").click(function(){
        	createwindow('选择体系','objresult.htm?getArch&class=C',500,500,returnorgValue );
    	    });
        
        $("#objsel").click(function(){
        	if($("#archcode").val()==""){
            	alert("请选择体系");
            }else{        	
            	var code=$("#archcode").val();
            	createwindow('选择对象','objindexarchuser.htm?getObjByArch&arch='+code,500,500,returnobjValue );
            }
    	    });
        
        $("#indexitemsel").click(function(){
        	if($("#objectcode").val()==""){
            	alert("请选择对象");
            }else{
            	var code=$("#archcode").val();
            	createwindow('选择对象','objindexarchuser.htm?getIndexByArch&arch='+code,500,500,returnindexValue );
            }
    	    });
        
        function returnorgValue(data){
        	
        	var org = data.code;
        	//alert(org.archname);
        	
        	$('#archcode').val(org.archcode);
        	$('#indexname').val(org.archname);
        	
        }    
        function returnobjValue(data){
        	
        	var org = data.code;
        	
        	$('#objectcode').val(org.archcode);
        	$('#objname').val(org.archname);
        	
        }   
        function returnindexValue(data){
        	
        	var org = data.code;
        	
        	$('#indexitemcode').val(org.archcode);
        	$('#indexitemname').val(org.archname);
        	$('#examTime').val(org.time);
        	changeIndex();
        	
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
        			cancelVal : '关闭',
        			cancel : true/* 为true等价于function(){} */
        		});
        }
    </script>
</html>
