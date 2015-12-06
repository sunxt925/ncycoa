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
    <input id="index" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:100px">
     <a id="btn_search" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">选择指标</a>
       <span> 年度选择：</span><select id="cc" class="easyui-combobox" name="menuyear" data-options="onSelect:selectyear">
           <%
            	Calendar c=Calendar.getInstance();//获得系统当前日期  
            	int year=c.get(Calendar.YEAR);
            	int month=c.get(Calendar.MONTH);
            	String selected="selected";
            	for(int i=year;i>=year-1;i--){
            		if(i==year){
            			 out.print("<option  value='"+i+"' selected='"+selected+"'>"+i+"</option>");
            		}else{
            			 out.print("<option  value='"+i+"'>"+i+"</option>");
            		}
            		
                }
            	String defalutyear=""+year;
            	String defalutmonth="";
            	if(month<10){
            		defalutmonth="M0"+month;
            	}else{
            		defalutmonth="M"+month;
            	}
			%>
          </select>
           
          <span> 月份选择：</span><select id="cc2" class="easyui-combobox" name="menumonth" data-options="onSelect:selectmonth">
              <%for(int i=1;i<13;i++){
            	  String select="";
            		if(i==month){
            			select="selected="+"'selected'";
            		}
            	  if(i<10){
            		  out.print("<option  value='M0"+i+"' "+select+">0"+i+"</option>");
            	  }else{
            		  out.print("<option  value='M"+i+"' "+select+">"+i+"</option>");
            	  }
            	  
            	  }%>
         
          </select>
          <a id="btn_searchobj" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">对比分析</a>
    </div>
    
    <div data-options="region:'center',split:true" style="padding:5px;background:#fff;">
		<iframe src="" name="analysisres" style="border: 0px;width: 100%;height: 95%">
	    </iframe>
	</div>
	<input type="hidden" id="year" value="<%=year%>">
	<input type="hidden" id="month" value="">
	<input type="hidden" id="indexcode" value="">
	<input type="hidden" id="standardscore" value="">
	
    </body>
    <script type="text/javascript">
        
        function selectyear(record){
        	 var year=record.value;
        	 $("#year").val(year);
        }
        $("#btn_search").click(function(){
        	createwindow("选择指标","meritanalysis/selectindex.jsp",500,400,returnvalue);
    	    });
        $("#btn_searchobj").click(function(){
        	createwindow("对比分析","meritanalysis/contrastanalysis.jsp",500,400,returnobjvalue);
    	    });
        function selectmonth(record){
            var year=$("#year").val();
            if(year==null||year==""){
           	 year="<%=year%>";
            }
            var mon=record.value;
            $("#month").val(mon);
            
            if($("#indexcode").val()==""){
            	alert("请选择指标项");
            }
            if($("#year").val()==""){
            	alert("请选择年度");
            }
            if($("#month").val()==""){
            	alert("请选择月度");
            }
            if($("#index").val()!=""&&$("#year").val()!=""&&$("#month").val()!=""){
            	var indexcode=$("#indexcode").val();
            	var year=$("#year").val();
            	var month=$("#month").val();
            	window.open("analysisres.jsp?class=compare&indexcode="+indexcode+"&year="+year+"&month="+month,"analysisres");
            }
       }
        function createwindow(title, url, width, height,fun) {
      	  $.dialog({
      		  data:fun,
      		  id:'LHG1976D',
      		  content : 'url:' + url,
      		  lock : true,
      		  width : width,
      		  height : height,
      		  title : title,
      		  opacity : 0.3,
      		  cache : false,
      		  ok : function() {
      			  $('#btn_ok', this.iframe.contentWindow.document).click();
      			  this.title(title).time(0.01);
      			  return false;
      		  },
      		  cancelVal : '关闭',
      		  cancel : true/* 为true等价于function(){} */
      	  });

        }
        function returnvalue(data){
        	$('#index').val(data.indexname);
        	$('#standardscore').val(data.standardscore);
        	$('#indexcode').val(data.indexcode);
        }
        function returnobjvalue(data){
        	var v = data.code;
        	var objs = "";
        	
        	if(v.length!=0){
        		for(var i=0;i<v.length;i++){
        			objs+=v[i]+",";
        		}
        		if($("#indexcode").val()==""){
                	alert("请选择指标项");
                }
                if($("#year").val()==""){
                	alert("请选择年度");
                }
                if($("#month").val()==""){
                	alert("请选择月度");
                }
                if($("#index").val()!=""&&$("#year").val()!=""&&$("#month").val()!=""){
                	var indexcode=$("#indexcode").val();
                	var year=$("#year").val();
                	var month=$("#month").val();
                	window.open("meritanalysis/analysisres.jsp?class=compare&indexcode="+indexcode+"&year="+year+"&month="+month+"&objs="+objs,"analysisres");
                }
        	}else{
        		alert('没有选择对比对象');
        	}
            
        }
    </script>
</html>
