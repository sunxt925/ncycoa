<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
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
<title>�Ĵ��ϳ��̲�ר��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
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
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
Indexitem indexitem=new Indexitem();
String indexclass=request.getParameter("class");
DataTable dt=indexitem.getReferIndexList(indexclass.toUpperCase());
%>
<body class="easyui-layout">
	<div id="top" data-options="region:'north',split:true"
		style="height:0px;"></div>
	<div id="left" data-options="region:'west',split:true"
		style="width:250px;">
		<div id="p1" class="easyui-panel" 
			style="height:200px;  padding:10px;background:#fafafa;overflow: auto;"
			data-options="iconCls:'icon-save',closable:true,
            collapsible:true,minimizable:true,maximizable:true'">
			
			 <%
				if (dt!= null && dt.getRowsCount()>0)
				{
					for (int i=0;i<dt.getRowsCount();i++)
					{
						out.print("<li><a  href=\"#\" onclick=\"select('"+dt.get(i).getString("indexcode")+"')\">"+dt.get(i).getString("indexname")+"</a></li>");
			
					}
				}
			%>
			
		</div>
		

	</div>
	<div id="center" data-options="region:'center'"
		style="padding:5px;">
		    <iframe src="" name="referindexlist" style="border: 0px;height: 95%;width: 100%;">
		    </iframe>
		</div>
<script type="text/javascript">
     function select(para){
    	 var url1='referindexlist.jsp?indexccm='+para+"&indexclass="+"<%=indexclass%>";
    	 window.open(url1,"referindexlist");
 		 }
</script>
</body>
</html>
