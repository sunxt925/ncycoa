<%@page import="com.common.ComponentUtil"%>
<%@page import="com.common.CodeDictionary"%>
<%@page import="com.common.IndexCode"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
String groupno=request.getParameter("groupno");
ComponentUtil cu=new ComponentUtil();
%>
<body>
	<form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
		<div id="p" style="padding: 10px">
			<a id="btn_save" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">����</a> <a id="btn_reset"
				href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true">����</a> <a
				id="btn_ref" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
		</div>
		<div >
		<table cellpadding="5"  width="100%" align="left" >
			<tr>
				<td><span>��ˮ�ţ�</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUP","GROUPNO")); 
					%>
				</td>
			</tr>
			<tr>
				<td><span>������</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUPMEMBER","GROUPNO",groupno,"readonly"));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>ʹ�������</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUPMEMBER","OBJECTTYPE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>�������룺</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUPMEMBER","ORGCODE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>��λ���룺</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUPMEMBER","POSITIONCODE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>Ա�����룺</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUPMEMBER","STAFFCODE"));
					%>
					<a id="btn_selectobject" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-add',plain:true">���</a>
				</td>
			</tr>
			<tr>
				<td><span>��ע��</span></td>
				<td>
					<%
					out.print(cu.print("TBM_ALLMERITGROUP","MEMO"));
					%>
				</td>
			</tr>
			
			
		</table>
		
		</div>
		<!-- �����ֶ� -->
		<input name="entity" id="entity" type="hidden" value="TBM_ALLMERITGROUPMEMBER"/>
        <input name="act" type="hidden" id="act" value="add">
           <input type="submit" name="Submit" value="�ύ" style="display:none">
            <input type="reset" name="reset" value="����" style="display:none">
          <input type="hidden" name="TBM_ALLMERITGROUPMEMBER.RECNO" id="TBM_ALLMERITGROUPMEMBER.RECNO" value="<%=IndexCode.getRecno("AM")%>" />
            <input name="action_class" type="hidden" id="action_class" value="com.action.index.AllMeritGroupMemberAction">
	</form>
	<script>
	    $("#btn_reset").click(function(){
	    	document.all("reset").click();
    	    });
	    $("#btn_save").click(function(){
	    	if(sumbit_check())
	   	        {
	   	            document.all("Submit").click();
	                window.returnValue="refresh";
	   	        }
    	    });
	    $("#btn_ref").click(function(){
	    	window.location.reload();
    	    });
	    
	    function returnValue(data){
	    	document.getElementById("TBM_ALLMERITGROUPMEMBER.ORGCODE").value=data.orgcode;
	    	document.getElementById("TBM_ALLMERITGROUPMEMBER.POSITIONCODE").value=data.positioncode;
	    	document.getElementById("TBM_ALLMERITGROUPMEMBER.STAFFCODE").value=data.staffcode;
		}
		function createwindow(title, url, width, height,func) {
			width = width ? width : 700;
			height = height ? height : 400;
			if (width == "100%" || height == "100%") {
				width = document.body.offsetWidth;
				height = document.body.offsetHeight - 100;
			}
			if (typeof (windowapi) == 'undefined') {
				$.dialog({
					data:func,
					id:'CLHG1976D',
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
					cancelVal : '�ر�',
					cancel : true/* Ϊtrue�ȼ���function(){} */
				});
			}
		}
	    
	    $("#btn_addlist").click(function(){
	    	
	 	   	createwindow('ѡ�����',url,800,600,returnValue);
    	    });

	</script>
</body>
</html>
<%
out.print(cu.getCheckstr());
%>