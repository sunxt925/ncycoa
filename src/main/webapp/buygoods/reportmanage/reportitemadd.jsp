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

<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
UserInfo user=(UserInfo)request.getSession().getAttribute("UserInfo");
String reportno=Format.NullToBlank(request.getParameter("reportno"));
String projectcode=Format.NullToBlank(request.getParameter("projectcode"));
String ul = path+"/buygoods/reportmanage/reportitemjson?projectcode="+projectcode;
CodeDictionary cd = new CodeDictionary();
%>
<body  class="easyui-layout">
   <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
    <div data-options="region:'north'" style="height:30px">
    <a id="btn_commit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">提交</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
   
    </div>
    <div data-options="region:'center'">
     <table id="dg" class="easyui-datagrid" style="width:1000px;height:700px"
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
	    <th data-options="field:'summitflag',width:100">提交状态</th>
	    
    </tr>
    </thead>
     </table>
     
    </div>
    <input name="entity" id="entity" type="hidden" value="COM_BUYGOODSITEM"/>
	<input name="act" type="hidden" id="act" value="add_goods">
	<input name="items" type="hidden" id="items" value="">
	<input name="reportno" type="hidden" id="reportno" value="<%=reportno %>">
	<input name="action_class" type="hidden" id="action_class"
		   value="com.action.buygoods.BuyReportItemAction">
	</form>
   <script type="text/javascript">
   $("#btn_ref").click(function(){
	   window.location.reload();
		});
   $("#btn_commit").click(function(){
	   document.all("act").value="application";
	   var buymode=$("#cc").val();
	   $("#buymode").val(buymode);
	   var row = $('#dg').datagrid('getSelections');
	   if(row.length==0){
		   createalert("未选中要提交项目");
	   }else{
		   if (confirm("确定要提交选中的记录？"))
				{
				   
				   var goods="";
				   for(var i=0;i<row.length;i++){
					   goods = goods+row[i].buyno+",";
				   }
				   $("#items").val(goods);
				   document.all("form1").submit();
				}
		   
	   }
		});
   function createalert(title){
	var api = frameElement.api, W = api.opener;
   	W.$.dialog({
   	    content: title,
   	    id:'CLHG1976D',
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
