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
<script language="javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
String eventno=request.getParameter("eventno");
String ul = path+"/buygoods/applicationquery/eventitemjson.jsp?eventno="+eventno;
CodeDictionary cd = new CodeDictionary();
%>
<body  class="easyui-layout">
    <div data-options="region:'north'" style="height:30px"></div>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    
    <div data-options="region:'center'">
   <table id="dg" class="easyui-datagrid" style="width:800px;height:600px"
    data-options="url:'<%=ul %>',fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'buyno',width:100">�¼����</th>
	    <th data-options="field:'projectname',width:100">��Ŀ����</th>
	    <th data-options="field:'goodsname',width:100">Ʒ��</th>
	    <th data-options="field:'goodsstyle',width:100">����ͺ�</th>
	    <th data-options="field:'goodsunit',width:100">������λ</th>
	    <th data-options="field:'goodsnumber',width:100">�ɹ�����</th>
	    <th data-options="field:'goodsprice',width:100">����</th>
	    <th data-options="field:'totalcost',width:100">Ԥ����</th>
	    <th data-options="field:'buygoodsdesc',width:100">�ɹ�˵��</th>
	    <th data-options="field:'needmonth',width:100">����ʱ��</th>
	    <th data-options="field:'auditflag',width:100">�ύ״̬</th>
	    
    </tr>
    </thead>
     </table>
     
    </div>
   <script type="text/javascript">
   
   $("#btn_ref").click(function(){
	   window.location.reload();
		});
	</script>
</body>
</html>
