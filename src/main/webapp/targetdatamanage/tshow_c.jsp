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
<title>�Ĵ��ϳ��̲�ר��</title>
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
    
   <label for="indexname">Ŀ����ϵ: </label>
	   	<input id="archcode" name="archcode" type="hidden" value="${archcode}">
		<input id="indexname" name="indexname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="indexsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >ѡ��</span></a>
		 
	 <label for="objname">����ѡ��: </label>
	   	<input id="objectcode" name="objectcode" type="hidden" value="${objectcode}">
		<input id="objname" name="objname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="objsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >ѡ��</span></a>
		 
   <label for="objname">ָ��ѡ��: </label>
	   	<input id="indexitemcode" name="indexitemcode" type="hidden" value="${indexcode}">
		<input id="indexitemname" name="indexitemname" type="text" style="width:150px;background-color:white;" readonly="readonly">
		<a id="indexitemsel" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:'icon-search'" >
		 <span >ѡ��</span></a>
	<label >����ѡ��: </label>
	 <select class="inputxt" id="season" name="season" style="width:156px;">
		<option value="S01">һ����</option>
		<option value="S02">������</option>
		<option value="S03">������</option>
		<option value="S04">�ļ���</option>
		</select>
<!--        <span> ���ѡ��</span><input id="startyear" name="startyear" class="easyui-textbox"> -->
<!--        <input id="endyear" name="endyear" class="easyui-textbox"> -->
       <input type="button" onclick="getShow()" value="�鿴"> 
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
    function getShow(){
        var startyear=$("#startyear").val();
        var endyear=$("#endyear").val();
       
        if($("#archcode").val()==""){
        	alert("��ѡ����ϵ");
        }else if($("#objectcode").val()==""){
        	alert("��ѡ�����");
        }else if($("#indexitemcode").val()==""){
        	alert("��ѡ��ָ��");
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
        	createwindow('ѡ����ϵ','objresult.htm?getArch&class=C',500,500,returnorgValue );
    	    });
        
        $("#objsel").click(function(){
        	var code=$("#archcode").val();
        	createwindow('ѡ�����','objindexarchuser.htm?getObjByArch&arch='+code,500,500,returnobjValue );
    	    });
        
        $("#indexitemsel").click(function(){
        
        	var code=$("#archcode").val();
        	createwindow('ѡ�����','objindexarchuser.htm?getIndexByArch&arch='+code,500,500,returnindexValue );
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
</html>
