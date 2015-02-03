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
String ul = path+"/buygoods/goodsjson.jsp?flag=0&eventno=null";
CodeDictionary cd = new CodeDictionary();
%>
<body  class="easyui-layout">
   <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
    <div data-options="region:'north'" style="height:30px">
    <a id="btn_mod" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">修改</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
    <a id="btn_commit" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">申报</a>
    
    <select id="cc" name="dept" style="width:200px; ">
    <%=cd.getselectItem("BUYMODE") %>
    </select>
    </div>
    <div data-options="region:'center'">
       <table id="dg" class="easyui-datagrid"
    data-options="url:'<%=ul %>',fitColumns:true,singleSelect:false,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true,width:100"></th>
	    <th data-options="field:'buyno',width:100">序号</th>
	    <th data-options="field:'projectname',width:100">项目名称</th>
	    <th data-options="field:'goodsname',width:100">品名</th>
	    <th data-options="field:'goodsstyle',width:100">规格型号</th>
	    <th data-options="field:'goodsunit',width:100">计量单位</th>
	    <th data-options="field:'goodsnumber',width:100">采购数量</th>
	    <th data-options="field:'goodsprice',width:100">单价</th>
	    <th data-options="field:'totalcost',width:100">预算金额</th>
	    <th data-options="field:'buygoodsdesc',width:100">采购说明</th>
	    <th data-options="field:'needmonth',width:100">需求时间</th>
	    <th data-options="field:'inputmonth',width:100">录入时间</th>
	    
    </tr>
    </thead>
     </table>
     
    </div>
    <input name="entity" id="entity" type="hidden" value="COM_BUYGOODSITEM"/>
    <input name="buymode" id="buymode" type="hidden" value=""/>
	<input name="act" type="hidden" id="act" value="del">
	<input name="goods" type="hidden" id="goods" value="">
	<input name="action_class" type="hidden" id="action_class" value="com.action.buygoods.BuyGoodsAppAction">
   </form>
   <script type="text/javascript">
   $("#btn_del").click(function(){
	   document.all("act").value="del";
	   var row = $('#dg').datagrid('getSelections');
	   if(row.length==0){
		   createalert("未选中要删除项目");
	   }else{
		   if (confirm("确定要删除选中的记录？"))
			{
			   
			   var goods="";
			   for(var i=0;i<row.length;i++){
				   goods = goods+row[i].buyno+",";
			   }
			   $("#goods").val(goods);
			   document.all("form1").submit();
			}
	   }
	   
		});
   $("#btn_mod").click(function(){
	    var row = $('#dg').datagrid('getSelected');
	    var path="<%=path%>";
		var u = path+"/buygoods/buygoodsin/buygoodsitemmod.jsp?buyno="+row.buyno;
		createwindow("修改物品", u, 400, 500);
		});
   $("#btn_ref").click(function(){
	   window.location.reload();
		});
   $("#btn_commit").click(function(){
	   document.all("act").value="application";
	   var buymode=$("#cc").val();
	   $("#buymode").val(buymode);
	   var row = $('#dg').datagrid('getSelections');
	   if(row.length==0){
		   createalert("未选中要申报项目");
	   }else{
		   if(buymode!=""){
			   if (confirm("确定要申报选中的记录？"))
				{
				   
				   var goods="";
				   for(var i=0;i<row.length;i++){
					   goods = goods+row[i].buyno+",";
				   }
				   $("#goods").val(goods);
				   document.all("form1").submit();
				}
		   }else{
			   createalert("请选择申报模式");
		   }
		   
	   }
		});
   
   
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
