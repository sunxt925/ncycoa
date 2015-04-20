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
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
</head>
    <body class="easyui-layout">
    <div data-options="region:'north'" style="height:30px;">
       <span> 年度选择：</span><select id="cc" class="easyui-combobox" name="menuyear" data-options="onSelect:change">
           <%
            	Calendar c=Calendar.getInstance();//获得系统当前日期    
            	int year=c.get(Calendar.YEAR);
            	String selected="selected";
            	for(int i=year;i>=year-1;i--){
            		if(i==year){
            			 out.print("<option  value='"+i+"' selected='"+selected+"'>"+i+"</option>");
            		}else{
            			 out.print("<option  value='"+i+"'>"+i+"</option>");
            		}
            		
                }
			%>
          </select>
    </div>
    <div data-options="region:'center',split:true" style="padding:5px;background:#fff;">
		<iframe src="collectlist.jsp?year=<%=year %>" name="collectlist" style="border: 0px;width: 100%;height: 100%">
	    </iframe>
	</div>
    </body>
    <script type="text/javascript">
        function change(record){
           window.open("collectlist.jsp?year="+record.value+"","collectlist");
        }
       
    </script>
</html>
