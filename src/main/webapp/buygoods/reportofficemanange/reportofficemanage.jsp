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

<script language="javascript" src="<%=path%>/js/public/select.js"></script>
<script language="javascript" src="<%=path%>/js/public/key.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
UserInfo user=(UserInfo)request.getSession().getAttribute("UserInfo");
String eventno=Format.NullToBlank(request.getParameter("eventno"));
String ul = path+"/buygoods/reportofficemanange/reportjson.jsp";
CodeDictionary cd = new CodeDictionary();
%>
<body  class="easyui-layout">
   <div data-options="region:'north'" style="height:30px">
		<a id="btn_ref" href="#" class="easyui-linkbutton" 
				data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
		</div>
		<div data-options="region:'center'">
			<table id="dg" class="easyui-datagrid"
				data-options="url:'<%=ul%>',fitColumns:true,singleSelect:true,collapsible:true">
				<thead>
					<tr>
				        <th data-options="field:'ck',checkbox:true,width:100"></th>
						<th data-options="field:'reportno',width:100">�ʱ����</th>
						<th data-options="field:'projectname',width:100">��Ŀ����</th>
						<th data-options="field:'relatedorgcode',width:100">ʵʩ����</th>
						<th data-options="field:'dealmode',width:100">ʵʩ��ʽ</th>
						<th data-options="field:'totalcost',width:100">�漰���</th>
						<th data-options="field:'checkcontents',width:100">�����ص�</th>
						<th data-options="field:'firstauditorg',width:100">������</th>
						<th data-options="field:'firstauditopinion',width:100">�������</th>
						<th data-options="field:'firstauditsummary',width:100">ժҪ</th>
						<th data-options="field:'op_office',width:100">����</th>
					</tr>
				</thead>
			</table>
		</div>
   <script type="text/javascript">
 
   $("#btn_ref").click(function(){
	   window.location.reload();
		});
   function audit(){
	   var row = $('#dg').datagrid('getSelected');
	   var path="<%=path%>";
	   var u=path+"/buygoods/reportofficemanange/reportitemaudit.jsp?reportno="+row.reportno;
	   createwindow('���',u,300,400);
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
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
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
   	    title :'��ʾ',
   	    ok: function(){
   	    	
   	        this.title('��ʾ').time(0.3);
   	        return false;
   	    },
   	    cancelVal: '�ر�',
   	    cancel: true /*Ϊtrue�ȼ���function(){}*/
   	});
   }
	</script>
</body>
</html>
