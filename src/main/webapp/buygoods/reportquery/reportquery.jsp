<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.common.CodeDictionary"%>
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
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>

<script language="javascript" src="<%=path%>/js/MyDatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
UserInfo user=(UserInfo)request.getSession().getAttribute("UserInfo");
String appdate=Format.NullToBlank(request.getParameter("appdate"));
String appenddate=Format.NullToBlank(request.getParameter("appenddate"));
String buymode=Format.NullToBlank(request.getParameter("buymode"));
String ul = path+"/buygoods/reportquery/eventjson.jsp";
CodeDictionary cd = new CodeDictionary();
%>
<body  class="easyui-layout">

   <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
   <div data-options="region:'north'" style="height:400px">
			<div data-options="region:'north'" style="height:30px">
				 <label>查询时间段</label> 
				<input name="starttime" type="Wdate" class="input1" id="starttime" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="20"  maxlength="30">
				--
				 <input name="endtime" type="Wdate" class="input1" id="endtime" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="20"  maxlength="30">
				   <label>申报模式</label>
				<select id="cc" name="dept" style="width:200px; ">
					<%=cd.getselectItem("BUYMODE")%>
				</select> <a id="btn_search" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a>

			</div>
			<div data-options="region:'center'" style="height:350px">
				<table id="dg" class="easyui-datagrid"
					data-options="url:'<%=ul%>',fitColumns:true,singleSelect:false,collapsible:true">
					<thead>
						<tr>
							<th data-options="field:'ck',checkbox:true,width:100"></th>
							<th data-options="field:'eventno',width:100">事件编号</th>
							<th data-options="field:'eventdesc',width:100">事件说明</th>
							<th data-options="field:'numgoodsitem',width:100">物资采购申请条数</th>
							<th data-options="field:'summitdate',width:100">申请日期</th>
							<th data-options="field:'handler',width:100">录入人</th>
							<th data-options="field:'memo',width:100">说明</th>
							<th data-options="field:'buymode',width:100">采购模式</th>
							<th data-options="field:'summitflag',width:100">处理结果</th>
							<th data-options="field:'op',width:100">操作</th>

						</tr>
					</thead>
				</table>

			</div>
		</div>
       <div data-options="region:'center'" style="height:350px">
           <iframe src=""  name="reportitem" width="99%" height="98%">
           </iframe>
       </div>
    
    <input name="entity" id="entity" type="hidden" value="COM_BUYREPORTITEM"/>
	<input name="act" type="hidden" id="act" value="del">
	<input name="buymode" id="buymode" type="hidden" value=""/>
	<input name="items" type="hidden" id="items" value="">
	<input name="action_class" type="hidden" id="action_class"
		  value="com.action.buygoods.BuyReportAction">
   </form>
   
   
   <script type="text/javascript">
   $("#btn_search").click(function(){
	   var u="<%=ul%>";
	   var starttime=$("#starttime").val();
	   var endtime=$("#endtime").val();
	   var buymode=$("#cc").val();
	   if(starttime==""||endtime==""){
		   $('#dg').datagrid({
				  url:u+"?starttime="+starttime+"&endtime="+endtime+"&buymode="+buymode 
			   });
	   }else{
		   if(endtime<starttime){
			   createalert("输入查询日期段无效，请重新输入");
		   }else{
			   $('#dg').datagrid({
					  url:u+"?starttime="+starttime+"&endtime="+endtime+"&buymode="+buymode 
				   });
		   }
	   }
	   
	  
		});
   $("#btn_ref").click(function(){
	   window.location.reload();
		});
   function detail(){
	   var row = $('#dg').datagrid('getSelected');
	   var u="reportitemlist.jsp?eventno="+row.eventno;
	   window.open(u,"reportitem");
   }
   
   function createwindow(title, url, width, height) {
		$.dialog({
			id:'LHG1976D',
			data:returnValue,
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				$('#btn_ok', this.iframe.contentWindow.document).click();
				return false;
			},
			cancelVal : '关闭',
			cancel : true/* 为true等价于function(){} */
		});
	
   }
   function returnValue(data){
       var f=data.code;
       if(f=="refresh"){
       	window.setTimeout(function(){
       		window.location.reload();
       	},1000);
       	
       }
   }
   function createalert(title){
   	$.dialog({
   	    content: title,
   	    title :'提示',
   	    ok: function(){
   	    	
   	        this.title('提示').time(0.3);
   	        return false;
   	    },
   	    cancelVal: '关闭',
   	    cancel: true /*为true等价于function(){}*/
   	});
   }
	</script>
</body>
</html>
