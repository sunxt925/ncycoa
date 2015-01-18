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
<%
String url="unitjson.jsp?class=s";
%>
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
          <span>员工姓名：</span>
          <input  id="staffname" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:100px">
           <a id="btn_search" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
     <div data-options="region:'west',split:true" style="width:200px;">
         <ul id="tt" class="easyui-tree" data-options="url:'<%=url %>',onClick:selectorg">
        </ul>
    </div>
    <div data-options="region:'center',split:true" style="padding:5px;background:#fff;">
		<iframe src="staffallmerityearlist.jsp?year=<%=year %>&staffname=&orgcode=" name="allmerityearlist" style="border: 0px;width: 100%;height: 95%">
	    </iframe>
	</div>
	<input type="hidden" id="year" value="<%=year%>">
    </body>
    <script type="text/javascript">
        
        function change(record){
        	 var year=record.value;
        	 $("#year").val(year);
             var url="staffallmerityearlist.jsp?year="+year+"&staffname="+"&orgcode=";
             window.open(url,"allmerityearlist");
        }
        $("#btn_search").click(function(){
	    	var name=$("#staffname").val();
	    	var year=$("#year").val();
	    	if(year==""){
	    		year="<%=year%>";
	    	}
	    	var url="staffallmerityearlist.jsp?year="+year+"&staffname="+name+"&orgcode=";
	            window.open(url,"allmerityearlist");
	    	
	    	
    	    });
        function selectorg(){
        	var url="";
            $('#tt').tree({
     			onClick: function(node){
     				var year=$("#year").val();
     				var orgcode=node.id;
     			    url="staffallmerityearlist.jsp?year="+year+"&orgcode="+orgcode+"&staffname=";
     				window.open(url,"allmerityearlist");
     			}
     		});
        }
    </script>
</html>
