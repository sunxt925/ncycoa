<%@page import="com.common.CodeDictionary"%>
<%@page import="com.entity.index.AllMeritGroupMember"%>
<%@page import="com.entity.index.AllMeritGroup"%>
<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
	<div data-options="region:'west',split:false,title:'指标体系树'" style="width:400px;padding:10px;">
	   <a id="btn_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
	   <span id="staff"></span>
	  <table id="dg_tree" class="easyui-treegrid"    data-options="method: 'get',rownumbers: true,idField: 'indexcode',treeField: 'indexname',fitColumns:true" >
	     <thead>
<tr>
<th data-options="field:'indexname',width:180">指标体系列表</th>
</tr>
</thead>
	</table> 
	</div>
	<div data-options="region:'center'">

		<div style="width: 90%;padding: 10px">
			<div style="width: 90%;padding: 20px">
				<div id="p" class="easyui-panel" title="所属综合绩效模板"
					style="width:800px;height:300px;padding:10px;">
				
					  </div>
			</div>
			<div style="width: 100%;padding: 20px;display: none" >
				<div id="p" class="easyui-panel" title="C"
					style="width:800px;height:300px;padding:10px;"></div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
	$("#btn_search").click(function(){
		var u="indexmanage/selectstaff.jsp";
    	createwindow('选择对象',u,800,600,returnobjValue);
    	
    	    });
	function returnobjValue(data){
    	var objectarray=data.code;
    	var refobjectcode=objectarray[0].staffcode;
    	var path="<%=path%>";
        
        $("#staff").load(path+"/indexmanage/getstaffname.jsp?staffcode="+refobjectcode,function(responseTxt,statusTxt,xhr){
		      ;
		    });
    	var url1="refindexjson.jsp?objectcode="+refobjectcode;
    	$('#dg_tree').treegrid({
		      url:url1
		      });
    	
 		var u=path+"/indexmanage/indexmeritinfo.jsp?objectcode="+refobjectcode;
 			    $("#p").load(u,function(responseTxt,statusTxt,xhr){
 			      ;
 			    });
	}
	
	function createwindow(title, url, width, height,func) {
	
		width = width ? width : 700;
		height = height ? height : 400;
		if (width == "100%" || height == "100%") {
			width = document.body.offsetWidth;
			height = document.body.offsetHeight - 100;
		}
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				data:func,
				content : 'url:' + url,
				lock : true,
				width : width,
				height : height,
				title : title,
				opacity : 0.3,
				cache : false,
				ok : function() {
					$('#btn_ok', this.iframe.contentWindow.document).click();
					this.title(title).time(2);
					return true;
				},
				cancelVal : '关闭',
				cancel : true/* 为true等价于function(){} */
			});
		}
	}
	</script>
</body>
</html>
