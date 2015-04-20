<%@page import="com.common.Format"%>
<%@page import="com.entity.index.IndexArchUser"%>
<%@page import="com.common.ComponentUtil"%>
<%@page import="com.common.CodeDictionary"%>
<%@page import="com.common.IndexCode"%>
<%@page import="java.text.DateFormat"%>
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
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=path%>/js/public/check.js"></script>
<script type="text/javascript" src="../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<style type="text/css">
    span{
        font-size: 12px;
    }
    table{
        border: 1px solid #f2f2f2;
    }
</style>
</head>
<%
String indexarchcode=request.getParameter("indexarchcode");
String indexclass=request.getParameter("indexclass");
String objectcode=request.getParameter("objectcode");
IndexArchUser indexArchUser=new IndexArchUser(indexarchcode,objectcode);
ComponentUtil cu=new ComponentUtil();
%>
<body>
	<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
		<div id="p" style="padding: 10px">
			<!-- <a id="btn_save" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">保存</a> -->
				 <a id="btn_reset"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">重置</a> <a
				id="btn_ref" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">刷新</a>
		</div>
		<div >
		<table cellpadding="5"  width="100%" align="left" >
			<tr>
				<td><span><% if(!indexclass.equals("s")){%>
                                                                                  机构编码：
                                    <%}else{ %>
                                                                                  员工编码：                     
                                    <%} %></span></td>
				<td>
					<%
					out.print(cu.print("TBM_INDEXARCHUSER","OBJECTCODE",indexArchUser.getObjectcode()));
					%>
					<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">选择</a>
				</td>
			</tr>
			<tr>
				<td><span>对象类型：</span></td>
				<td>
					<%
					String objecttype="";
			         if(indexclass.equals("s"))
			              objecttype="staff";
			         else if(indexclass.equals("d"))
			              objecttype="depart";
			         else if(indexclass.equals("c"))
			        	  objecttype="company";
			         out.print(cu.print("TBM_INDEXARCHUSER","OBJECTTYPE",indexArchUser.getObjecttype(),"readonly"));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>排序级别：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_INDEXARCHUSER","MULTIINDEXORDER",String.valueOf(indexArchUser.getMultiindexorder())));
					%>
				</td>
			</tr>
			<tr>
				<td><span>起始日期：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_INDEXARCHUSER","STARTDATE",Format.dateToStr(indexArchUser.getStartDate())));
					%>
				</td>
			</tr>
			<tr>
				<td><span>终止日期：</span></td>
				<td>
					<%
	            	out.print(cu.print("TBM_INDEXARCHUSER","ENDDATE",Format.dateToStr(indexArchUser.getEndDate())));
					%>
				</td>
			</tr>
			<tr>
				<td><span>备注：</span></td>
				<td>
					<%
					out.print(cu.print("TBM_INDEXARCHUSER","MEMO",indexArchUser.getMemo()));
					%>
				</td>
			</tr>
			
			
		</table>
		
		</div>
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<!-- 隐藏字段 -->
		<input name="entity" id="entity" type="hidden" value="TBM_INDEXARCHUSER"/>
		 <input type="hidden" name="TBM_INDEXARCHUSER.INDEXARCHCODE" id="TBM_INDEXARCHUSER.INDEXARCHCODE" value="<%=indexarchcode%>" />
		 <input type="hidden" name="old_INDEXARCHCODE" id="old_INDEXARCHCODE" value="<%=indexarchcode%>" />
		 <input type="hidden" name="old_OBJECTCODE" id="old_OBJECTCODE" value="<%=objectcode%>" />
        <input name="act" type="hidden" id="act" value="modify">
        <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
        <input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexArchUserAction">
	</form>
	<script>
	function ret(){
		   var api = frameElement.api;
	    
	    	if(sumbit_check())
	   	    {   if(document.getElementById("TBM_INDEXARCHUSER.STARTDATE").value<document.getElementById("TBM_INDEXARCHUSER.ENDDATE").value&&document.getElementById("TBM_INDEXARCHUSER.STARTDATE").value!="null"&&document.getElementById("TBM_INDEXARCHUSER.ENDDATE").value!="null"){
	    		   document.all("Submit").click();
	   	           (api.data)({code:"refresh"});
	   	           }else{
	   	        	   alert("输入日期有误");
	   	           }
	   	       
	   	    }
	   }
	    $("#btn_reset").click(function(){
	    	document.all("reset").click();
    	    });
	  
	    $("#btn_ref").click(function(){
	    	window.location.reload();
    	    });
	    
	    function returnobjValue(data){
	    	var objectarray=data.code;
	    	document.getElementById("TBM_INDEXARCHUSER.OBJECTCODE").value=objectarray[0].orgcode;
		}
		function createwindow(title, url, width, height,func) {
			var api = frameElement.api, W = api.opener;
			width = width ? width : 700;
			height = height ? height : 400;
			if (width == "100%" || height == "100%") {
				width = document.body.offsetWidth;
				height = document.body.offsetHeight - 100;
			}
			if (typeof (windowapi) == 'undefined') {
				W.$.dialog({
					id:'CLHG1976D',
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
						return true;
					},
					cancelVal : '关闭',
					cancel : true/* 为true等价于function(){} */
				});
			}
		}
	    
	    $("#btn_selectobject").click(function(){
	    	 var request_class="<%=indexclass%>";
	 	     var url="";
	 	     if(request_class=="s"){
	 	         url="indexmanage/selectstaff.jsp";
	 	     }else{
	 	         url="indexmanage/selectunit.jsp";
	 	     }
	 	   	createwindow('选择对象',url,800,600,returnobjValue);
    	    });

	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>