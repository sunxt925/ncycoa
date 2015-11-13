<%@page import="com.entity.index.Collectentity"%>
<%@page import="com.entity.index.AllMeritCollection"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  // String url="collectjson.jsp?year="+request.getParameter("year");
String year=request.getParameter("year");
   List<Collectentity> collectentities=AllMeritCollection.getCollectionList(year);
%>
<body>
   <div style="width: 100%">
    <table id="dg" class="easyui-datagrid" style=""
    data-options="fitColumns:true,singleSelect:true">
    <thead>
    <tr>
    <th data-options="field:'year',width:25">年份</th>
    <th data-options="field:'period',width:25">月份</th>
    <th data-options="field:'objectnum',width:60">已汇总人数/员工总数</th>
    <th data-options="field:'staffnum',width:90">已打分员工数/关联指标员工数</th>
    <th data-options="field:'departnum',width:90">已打分部门数/关联指标部门数</th>
    <th data-options="field:'companynum',width:90">已打分公司数/关联指标公司数</th>
     <th data-options="field:'state',width:15">状态</th>
     <th data-options="field:'op',width:120">汇总操作</th>
    </tr>
    </thead>
    <tbody>
	<%for(Collectentity collectentity:collectentities){ %>										        
			<tr>
			    <td><%=collectentity.getYear() %></td>
			    <td><%=collectentity.getPeriod() %></td>
			    <td><%=collectentity.getCollectedobjectnum() %>/<%=collectentity.getNeedcollectobjectnum() %></td>
			    <td><%=collectentity.getStaffneedcount() %>/<%=collectentity.getStaffsumcount() %></td>
			    <td><%=collectentity.getDeparneedcount() %>/<%=collectentity.getDepartsumcount() %></td>
			    <td><%=collectentity.getCompanyneedcount() %>/<%=collectentity.getCompanysumcount() %></td>
			    <% if(collectentity.getCollectedobjectnum()==collectentity.getNeedcollectobjectnum()){ %>
			    <td>0</td>
			    <%} else{%>
			    <td>1</td>
			    <%} %>
			    <td><a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="collect('<%=collectentity.getPeriod()%>',
				                         '<%=collectentity.getCollectedobjectnum()%>',
				                         '<%=collectentity.getNeedcollectobjectnum()%>','staff')">个人</a>
				     <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="collect('<%=collectentity.getPeriod()%>',
				                         '<%=collectentity.getCollectedobjectnum()%>',
				                         '<%=collectentity.getNeedcollectobjectnum()%>','company')">公司</a>
				    <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="recollect('<%=collectentity.getPeriod()%>',
				                         '<%=collectentity.getCollectedobjectnum()%>',
				                         '<%=collectentity.getNeedcollectobjectnum()%>','staff')">个(重)/</a>
				    <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="recollect('<%=collectentity.getPeriod()%>',
				                         '<%=collectentity.getCollectedobjectnum()%>',
				                         '<%=collectentity.getNeedcollectobjectnum()%>','company')">公(重)</a>
				                         </td>
			</tr>
			<%} %>
    </tbody>
    </table>
    
    <input type="button" id="kkk" onClick="sAlert('测试弹出层并锁屏效果');" style="display:none"/>
    </div>
 <script type="text/javascript">
      function collect(period,collectnum,neednum,flag){
    	  collectnum=collectnum/1;
    	  neednum=neednum/1;
    	  var path="<%=path%>";
    	  var year="<%=year%>";
    	  var myDate = new Date();
    	  var yy=myDate.getFullYear();        //获取当前年份(2位)
    	  var mon=myDate.getMonth();
    	  var mm="M";
    	  if(mon<10){
    		  mm=mm+"0"+mon;
    	  }else{
    		  mm=mm+mon;
    	  }
    	  
    	  if(period<=mm){
    		  if(collectnum<neednum&&collectnum==0)
        	  {
    			 progress();
    			 $.post(path+"/allmeritcollection/collectiondo.jsp?op=collect&flag="+flag,
        			    {
        			      year:year,
        			      period:period
        			    },
        				 function(data,status){
        			    	result(data);
        			    	//window.location.reload();
        			    }); 
        	  }else if(collectnum<neednum&&collectnum!=0){
        		  createalert("本月已经汇总过一次，若要继续请点击重新汇总");
        	  }else{
        		  createalert("本月已经汇总，不能重复汇总");
        	  }
    	  }else{
    		  createalert("未到汇总时间，不能汇总");
    	  }
    	  
    	  
      }
      function recollect(period,collectnum,neednum,flag){
    	  var path="<%=path%>";
    	  var year="<%=year%>";
    	  var myDate = new Date();
    	  var yy=myDate.getFullYear();        //获取当前年份(2位)
    	  var mon=myDate.getMonth();
    	  var mm="M";
    	  if(mon<10){
    		  mm=mm+"0"+mon;
    	  }else{
    		  mm=mm+mon;
    	  }
    	  if(period<=mm){
    		     progress();
    			 $.post(path+"/allmeritcollection/collectiondo.jsp?op=recollect&flag="+flag,
        			    {
        			      year:year,
        			      period:period
        			    },
        				 function(data,status){
        			    	result(data);
        			    	//window.location.reload();
        			    }); 
        	 
    	  }else{
    		  createalert("未到汇总时间，不能汇总");
    	  }
    	  
    	  
      }
      function createalert(content){
      	$.dialog({
      	    content: content,
      	    title:'提示',
      	    ok: function(){
      	        this.title('提示').time(0.1);
      	        return false;
      	    },
      	    cancelVal: '关闭',
      	    cancel: true /*为true等价于function(){}*/
      	});
      }
      function progress(){
         var win = $.messager.progress({
              title:'请等待',
              text:'绩效汇总中...'
              
          });
    	  
      }
      function result(data){
    	  show(data);
    	  closemsgshow();
    	 // window.location.reload();
      }
      function closemsgshow(){
    	  setTimeout(function(){
    	       $.messager.progress('close');
    	       
    	     },100); 
    	  //   window.location.reload();
      }
      function show(msg){
          $.messager.show({
              title:'提示',
              msg:msg,
              showType:'show'
          });
          setTimeout(function(){
        	  window.location.reload();
   	      },800);
      }
 </script>
</body>
</html>
