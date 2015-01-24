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

<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
UserInfo user=(UserInfo)request.getSession().getAttribute("UserInfo");
String ul = path+"/buygoods/reportmanage/reportjson.jsp?state=true";
CodeDictionary cd = new CodeDictionary();
%>
<body  class="easyui-layout">

   <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
   <div data-options="region:'north'" style="height:400px">
			<div data-options="region:'north'" style="height:20px">
				<a id="btn_save" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true">新增</a> <a id="btn_del"
					href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:true">删除</a> <a
					id="btn_ref" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload',plain:true">刷新</a> <label>呈报模式</label>
				<select id="cc" name="dept" style="width:200px; ">
					<%=cd.getselectItem("BUYMODE")%>
				</select>
			</div>
			<div data-options="region:'center'" style="height:350px">
				<a id="btn_auto" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true">自动产生</a> <a
					id="btn_commit" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload',plain:true">呈报</a>

				<table id="dg" class="easyui-datagrid"
					style="width:900px;height:320px"
					data-options="url:'<%=ul%>',fitColumns:true,singleSelect:false,collapsible:true">
					<thead>
						<tr>
							<th data-options="field:'ck',checkbox:true,width:100"></th>
							<th data-options="field:'reportno',width:100">呈报编号</th>
							<th data-options="field:'projectname',width:100">项目名称</th>
							<th data-options="field:'relatedorgcode',width:100">实施部门</th>
							<th data-options="field:'dealmode',width:100">实施方式</th>
							<th data-options="field:'totalcost',width:100">涉及金额</th>
							<th data-options="field:'checkcontents',width:100">审议重点</th>
							<th data-options="field:'firstauditorg',width:100">初审部门</th>
							<th data-options="field:'firstauditsummary',width:100">摘要</th>
							<th data-options="field:'summitflag',width:100">呈报标志</th>
							<th data-options="field:'op',width:100">操作</th>

						</tr>
					</thead>
				</table>

			</div>
		</div>
       <div data-options="region:'center'" style="height:350px">
           <iframe src=""  name="reportitem" width="98%" height="98%">
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
   $("#btn_save").click(function(){
	            var path="<%=path%>";
				var u = path+"/buygoods/reportmanage/buyreportadd.jsp";
				createwindow("添加", u, 400, 500);
				});
   $("#btn_del").click(function(){
	   document.all("act").value="del";
	   var row = $('#dg').datagrid('getSelections');
	   if(row.length==0){
		   createalert("未选中要删除项目");
	   }else{
		   if (confirm("确定要删除选中的记录？"))
			{
			   
			   var items="";
			   for(var i=0;i<row.length;i++){
				   items = items+row[i].reportno+",";
			   }
			   $("#items").val(items);
			   document.all("form1").submit();
			}
	   }
	   
		});
   $("#btn_auto").click(function(){
	   document.all("act").value="autoget";
	   document.all("form1").submit();
		});
   $("#btn_ref").click(function(){
	   window.location.reload();
		});
   $("#btn_commit").click(function(){
	   document.all("act").value="report";
	   var buymode=$("#cc").val();
	   $("#buymode").val(buymode);
	   var row = $('#dg').datagrid('getSelections');
	   if(row.length==0){
		   createalert("未选中要呈报项目");
	   }else{
		   if(buymode!=""){
			   if (confirm("确定要申报选中的记录？"))
				{
				   
				   var items="";
				   for(var i=0;i<row.length;i++){
					   items = items+row[i].reportno+",";
				   }
				   $("#items").val(items);
				   document.all("form1").submit();
				}
		   }else{
			   createalert("请选择呈报模式");
		   }
		   
	   }
		});
   function modify(){
	   var row = $('#dg').datagrid('getSelected');
	   var path="<%=path%>";
	   var u = path+"/buygoods/reportmanage/buyreportmod.jsp?reportno="+row.reportno;
	   createwindow("修改", u, 400, 500);
   }
   function detail(){
	   var row = $('#dg').datagrid('getSelected');
	   var u="reportitemlist.jsp?reportno="+row.reportno+"&projectcode="+row.projectcode;
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
