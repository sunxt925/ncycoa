<%@page import="com.entity.index.Indexitem"%>
<%@page import="com.db.DataTable"%>
<%@page import="edu.cqu.ncycoa.domain.EvaluDefine"%>
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
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="../jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="../jscomponent/tools/datagrid.js"></script>
<style type="text/css">
  
    #p1 li{
        
        list-style:none;
        
        background: url("t_bg.jpg");
        height: 30px;
        line-height:30px;
        padding: 0px;
    }
    #p1 li a{
        
        color:#000000;
        text-decoration: none;
        padding: 5px;
        
    }
    #p1 li a:HOVER {
         color: #f00;	
    }
</style>
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/curdtools.js"></script>
</head>
<%
	Indexitem indexitem=new Indexitem();
	String index_class = request.getParameter("class").toUpperCase();
	DataTable dt = indexitem.getrootList(index_class);
%>
<body class="easyui-layout" onload="changepoint('E')">
	<div id="top" data-options="region:'north',split:true"
		style="height:0px;"></div>
	<div id="left" data-options="region:'west',split:true"
		style="width:250px;">
<!-- 		<div id="p1" class="easyui-panel"  -->
<!-- 			style="height:200px;  padding:10px;background:#fafafa;overflow: auto;" -->
<!-- 			data-options="iconCls:'icon-save',closable:true, -->
<!--             collapsible:true,minimizable:true,maximizable:true'"> -->
			
			 <%
				//if (dt!= null && dt.getRowsCount()>0)
				//{
				//	for (int i=0;i<dt.getRowsCount();i++)
					//{
				//		out.print("<li><a  href=\"#\" onclick=\"changepoint('"+dt.get(i).getString("indexcode")+"')\">"+dt.get(i).getString("indexname")+"</a></li>");
			
				//	}
			//	}
			%>
			
<!-- 		</div> -->
		<div id="p2" class="easyui-panel" 
			style="padding:10px;background:#fafafa;"
			data-options="iconCls:'icon-save',closable:true,
            collapsible:true,minimizable:true,maximizable:true'">
            
			<ul id="tt" class="easyui-tree"   data-options="onClick:change">
			</ul>
		</div>

	</div>
	<div id="center" data-options="region:'center'"
		style="padding:5px;">
		 <iframe  style="border: 0px; width: 100%;height: 100%" src="" name="indexlist">
         </iframe>
		</div>
<script type="text/javascript">
     function change(){
    	 $('#tt').tree({
 			onClick: function(node){
 				//var url1='indexlist.jsp?indexccm='+node.id+'&indexname='+node.text;
 				var url1='evalu_indexlist.jsp?indexccm='+node.id;
 				 window.open(url1,"indexlist");
 				//$('#center').panel({
 		    	//	 href:url1
 		    	// });
 			}
 		});
    	 
     }
     function ss(){
    	 alert("das");
     }
     function changepoint(ccm){
    	 
    	 var url1="indextreejson.jsp?ccm="+ccm;
    	   $('#tt').tree({
    	       url:url1
    	       });
     }
</script>
</body>
</html>
