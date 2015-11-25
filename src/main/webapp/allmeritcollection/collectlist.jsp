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
    <th data-options="field:'year',width:25">���</th>
    <th data-options="field:'period',width:25">�·�</th>
    <th data-options="field:'objectnum',width:60">�ѻ�������/Ա������</th>
    <th data-options="field:'staffnum',width:90">�Ѵ��Ա����/����ָ��Ա����</th>
    <th data-options="field:'departnum',width:90">�Ѵ�ֲ�����/����ָ�겿����</th>
    <th data-options="field:'companynum',width:90">�Ѵ�ֹ�˾��/����ָ�깫˾��</th>
     <th data-options="field:'state',width:15">״̬</th>
     <th data-options="field:'op',width:120">���ܲ���</th>
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
				                         '<%=collectentity.getNeedcollectobjectnum()%>','staff')">����</a>
				     <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="collect('<%=collectentity.getPeriod()%>',
				                         '<%=collectentity.getCollectedobjectnum()%>',
				                         '<%=collectentity.getNeedcollectobjectnum()%>','company')">��˾</a>
				    <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="recollect('<%=collectentity.getPeriod()%>',
				                         '<%=collectentity.getCollectedobjectnum()%>',
				                         '<%=collectentity.getNeedcollectobjectnum()%>','staff')">��(��)/</a>
				    <a href="#" class="easyui-linkbutton"
				        data-options="iconCls:'icon-reload',plain:true" 
				        onclick="recollect('<%=collectentity.getPeriod()%>',
				                         '<%=collectentity.getCollectedobjectnum()%>',
				                         '<%=collectentity.getNeedcollectobjectnum()%>','company')">��(��)</a>
				                         </td>
			</tr>
			<%} %>
    </tbody>
    </table>
    
    <input type="button" id="kkk" onClick="sAlert('���Ե����㲢����Ч��');" style="display:none"/>
    </div>
 <script type="text/javascript">
      function collect(period,collectnum,neednum,flag){
    	  collectnum=collectnum/1;
    	  neednum=neednum/1;
    	  var path="<%=path%>";
    	  var year="<%=year%>";
    	  var myDate = new Date();
    	  var yy=myDate.getFullYear();        //��ȡ��ǰ���(2λ)
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
        		  createalert("�����Ѿ����ܹ�һ�Σ���Ҫ�����������»���");
        	  }else{
        		  createalert("�����Ѿ����ܣ������ظ�����");
        	  }
    	  }else{
    		  createalert("δ������ʱ�䣬���ܻ���");
    	  }
    	  
    	  
      }
      function recollect(period,collectnum,neednum,flag){
    	  var path="<%=path%>";
    	  var year="<%=year%>";
    	  var myDate = new Date();
    	  var yy=myDate.getFullYear();        //��ȡ��ǰ���(2λ)
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
    		  createalert("δ������ʱ�䣬���ܻ���");
    	  }
    	  
    	  
      }
      function createalert(content){
      	$.dialog({
      	    content: content,
      	    title:'��ʾ',
      	    ok: function(){
      	        this.title('��ʾ').time(0.1);
      	        return false;
      	    },
      	    cancelVal: '�ر�',
      	    cancel: true /*Ϊtrue�ȼ���function(){}*/
      	});
      }
      function progress(){
         var win = $.messager.progress({
              title:'��ȴ�',
              text:'��Ч������...'
              
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
              title:'��ʾ',
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
