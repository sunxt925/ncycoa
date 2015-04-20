<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<style type="text/css">
   table tr td{
      height: 50px;
   }
</style>
</head>
  
  <body>

	<table id="paratable">
		<tr>
			<td>
			<div>
					<label for="name" >公司:&nbsp;&nbsp;&nbsp;</label> 
					<input id="C00" class="easyui-numberbox" type="text" name="C00"  data-options="min:0,max:100" />
			</div>
			</td>
		</tr>
		<tr>
			<td>
			<div>
					<label for="name">
					<select id="dchange" style="width: 65px">
					  <option value="1">部门</option>
					  <option value="2">部门AVG</option>
					</select></label> 
					<input id="D00" class="easyui-numberbox" type="text" name="D00"  data-options="min:0,max:100" style="display:"/>
					<input id="Davg" class="easyui-numberbox" type="text" name="Davg"  data-options="min:0,max:100" style="display:none"/>
			</div>
			</td>
			
		</tr>
		<tr>
			<td>
			<div>
					<label for="name">主岗:&nbsp;&nbsp;&nbsp;</label> 
					<input id="SX1" class="easyui-numberbox" type="text" name="SX1" data-options="min:0,max:100" />
					<a id="btn_add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
			</div>
			</td>
		</tr>

	</table>
	<input type="button" id="btn_ok" style="display: none" onclick="ret()">
    <script>
    var x=1;
    $("#btn_add").click(function(){
	     x=x+1;
	    var tr="<tr>"+
			   "<td>"+
		       "<div>"+
			   "<label for=\"name\">兼岗"+(x-1)+":&nbsp;&nbsp;&nbsp;</label>"+ 
			   "<input id=\"SX"+x+"\" class=\"easyui-numberbox\" type=\"text\" name=\"SX"+x+"\" data-options=\"min:0,max:100\" />"+
		       "</div>"+
		       "</td>"+
	           "</tr>"; 
		$("#paratable").append(tr);
	    });
    $("#dchange").change(function(){
      var p1=$(this).children('option:selected').val();
 	     if(p1=="1"){
 	        document.getElementById("Davg").style.display="none";
 	        document.getElementById("D00").style.display="";
 	     }
 	     if(p1=="2"){
 	    	document.getElementById("Davg").style.display="";
 	        document.getElementById("D00").style.display="none";
 	     }
     });
    function ret(){
    	var api = frameElement.api;
    	var C00 = document.getElementById("C00").value;
    	var D00="";
    	var D="";
    	var p=$("#dchange").children('option:selected').val();
    	if(p==1){
    		D00=document.getElementById("D00").value;
    		D="D00";
    	}else{
    		D00=document.getElementById("Davg").value;
    		D="Davg";
    	}
    	var row=serialize(C00,D,D00);
    	
    	if(row!=""){
    		(api.data)({code:row});
    	}
	   
    }
    function serialize(Cvalue,Dcode,Dvalue){
    	 var table=document.getElementById("paratable");
	     var rownum=table.rows.length;
	     var v="";
	     var S="";
	      for(var i=2;i<rownum;i++){
	          var vv=document.getElementById("SX"+(i-1)).value;
	          v=v+","+vv;
	          S=S+",SX"+(i-1);
	         
	       }
	     
	      var n=new Array();
	      var num=Cvalue+","+Dvalue+v;
	      n=num.split(",");
	      var sss=0;
	      for(var j=0;j<n.length;j++){
	    	 sss=sss+n[j]/1;
	    	 
	      }
	      if(sss==100){
	    	   var fvalue="";
	    	  for(var i=0;i<n.length-1;i++){
	    		  fvalue=fvalue+n[i]/100+",";
	    	  }
	    	  fvalue=fvalue+n[n.length-1]/100; 
	    	  return "(C00,"+Dcode+S+")=("+fvalue+")";
	      }else{
	    	  alert("构造数据比例不正确，请重新输入");
	    	  return "";
	      }
    }
    
 
    </script>
</body>
</html>
