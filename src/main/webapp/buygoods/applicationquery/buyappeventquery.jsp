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
<script language="javascript" src="<%=path%>/js/MyDatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
UserInfo user=(UserInfo)request.getSession().getAttribute("UserInfo");
String ul = path+"/buygoods/applicationquery/eventjson.jsp";
CodeDictionary cd = new CodeDictionary();
%>
<body  class="easyui-layout">
   <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
    <div data-options="region:'north'" style="height:30px">
    <label>��ѯʱ���</label>
    <input name="starttime" type="Wdate" class="input1" id="starttime" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="20"  maxlength="30">
	-- <input name="endtime" type="Wdate" class="input1" id="endtime" onfocus="new WdatePicker({lang:'zh-cn'})"  value="" size="20"  maxlength="30">
	<label>�걨ģʽ</label>
    <select id="cc" name="dept" style="width:200px; ">
    <%=cd.getselectItem("BUYMODE") %>
    </select>
    <a id="btn_search" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">��ѯ</a>
    
    </div>
     
    <div data-options="region:'center'">
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    
    <a id="btn_detial" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">��ϸ</a>
    
       <table id="dg" class="easyui-datagrid"
    data-options="url:'<%=ul %>',fitColumns:true,singleSelect:true,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true,width:100"></th>
	    <th data-options="field:'eventno',width:100">�¼����</th>
	    <th data-options="field:'eventdesc',width:100">�¼�˵��</th>
	    <th data-options="field:'numgoodsitem',width:100">��������</th>
	    <th data-options="field:'summitdate',width:100">��������</th>
	    <th data-options="field:'handler',width:100">¼����</th>
	    <th data-options="field:'buymode',width:100">�ɹ�ģʽ</th>
	    <th data-options="field:'summitflag',width:100">������</th>
	    
    </tr>
    </thead>
     </table>
     
    </div>
    <input name="buymode" id="buymode" type="hidden" value=""/>
	<input name="act" type="hidden" id="act" value="del">
	<input name="goods" type="hidden" id="goods" value="">
   </form>
   <script type="text/javascript">
   
   $("#btn_ref").click(function(){
	   window.location.reload();
		});
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
			   createalert("�����ѯ���ڶ���Ч������������");
		   }else{
			   $('#dg').datagrid({
					  url:u+"?starttime="+starttime+"&endtime="+endtime+"&buymode="+buymode 
				   });
		   }
	   }
	   
	  
		});
   $("#btn_detial").click(function(){
	   var row = $('#dg').datagrid('getSelected');
	   var path="<%=path%>";
	   var u=path+"/buygoods/applicationquery/buyappeventdetial.jsp?eventno="+row.eventno;
	   createwindow("��ϸ", u, 850, 650);
		});
   function createwindow(title, url, width, height) {
		$.dialog({
			id:'LHG1976D',
			content : 'url:' + url,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			cancelVal : '�ر�',
			cancel : true/* Ϊtrue�ȼ���function(){} */
		});
	
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
