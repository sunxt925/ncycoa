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
String userclass=request.getParameter("class");
String url="";
if(!userclass.equals("c"))
url="unitjson.jsp?class="+userclass;
%>
    <body class="easyui-layout">
    <div data-options="region:'north'" style="height:30px;">
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
          <span> 月份选择：</span><select id="cc2" class="easyui-combobox" name="menuyear" data-options="onSelect:selectmonth">
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
    </div>
    <%if(!userclass.equals("c")) {%>
    <div data-options="region:'west',split:true" style="width:200px;">
        <ul id="tt" class="easyui-tree" data-options="url:'<%=url %>',onClick:change">
        </ul>
       
    </div>
    <%} %>
    <div data-options="region:'center',split:true" style="padding:5px;background:#fff;">
    <%if(userclass.equals("c")){ %>
		<iframe src="companymeritlist.jsp?year=<%=defalutyear %>&month=<%=defalutmonth %>" name="meritlist" style="border: 0px;width: 100%;height: 95%">
	    </iframe>
	    <%} %>
	 <%if(userclass.equals("d")){ %>
		<iframe src="departmeritlist.jsp?year=<%=defalutyear %>&month=<%=defalutmonth %>&company=" name="meritlist" style="border: 0px;width: 100%;height: 95%">
	    </iframe>
	    <%} %>
	    <%if(userclass.equals("s")){ %>
		<iframe src="staffmeritlist.jsp?year=<%=defalutyear %>&month=<%=defalutmonth %>&company=&depart=" name="meritlist" style="border: 0px;width: 100%;height: 95%">
	    </iframe>
	    <%} %>
	</div>
	<input type="hidden" name="year" id="year" value="<%=defalutyear%>">
	<input type="hidden" name="month" id="month" value="<%=defalutmonth %>">
    </body>
    <script type="text/javascript">
        
        function selectyear(record){
        	$("#year").val(record.value);
        }
        function selectmonth(record){
        	 var userclass="<%=userclass%>";
             var year=$("#year").val();
             if(year==null||year==""){
            	 year="<%=year%>";
             }
             var mon=record.value;
             $("#month").val(mon);
        	 var url="";
             if(userclass=="c"){
          	   url="companymeritlist.jsp?year="+year+"&month="+mon;
             }else if(userclass=="d"){
          	   url="departmeritlist.jsp?year="+year+"&month="+mon+"&company=";
             }else if(userclass=="s"){
          	   url="staffmeritlist.jsp?year="+year+"&month="+mon+"&company="+"&depart=";
             }
             window.open(url,"meritlist");
        }
        function change(){
        	var userclass="<%=userclass%>";
        	var url="";
            $('#tt').tree({
     			onClick: function(node){
     				var year=$("#year").val();
     				var month=$("#month").val();
     				var company=$('#tt').tree('getParent', node.target);
     				var depart=node.id;
     				if(year==""||month==""){
     					alert("请指定查询年份和月份");
     				}else{
     					if(userclass=="d"){
          				   if(depart=="NC.00")
          					   depart="NC.01.00";
          	         	   url="departmeritlist.jsp?year="+year+"&month="+month+"&company="+depart+"&depart=";
          	            }else if(userclass=="s"){
          	               if(company==null)
          	            		company="";
          	               else
          	            	   company=company.id;
          	         	   url="staffmeritlist.jsp?year="+year+"&month="+month+"&company="+company+"&depart="+depart;
          	         	
          	            }
          				window.open(url,"meritlist");
     					
     				}
     				
     				
     			}
     		});
        }
    </script>
</html>
