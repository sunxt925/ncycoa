<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.common.Format"%>
<%@page import="com.common.ComponentUtil"%>
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

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>

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

UserInfo user =(UserInfo)request.getSession().getAttribute("UserInfo");
ComponentUtil cu = new ComponentUtil();
%>
<body>
 <form name="form1" id="form1" method="post" action="../../servlet/PageHandler">
		
		<table cellpadding="5"  width="100%" align="left" >
			
			<tr>
				<td><span>��Ŀ���ƣ�</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","PROJECTNAME"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>Ʒ����</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSNAME"));
					%>
					<a id="btn_selectgoods" href="#" class="easyui-linkbutton"
				       data-options="iconCls:'icon-search',plain:true">ѡ��</a>
				</td>
			</tr>
			<tr>
				<td><span>����ͺţ�</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSSTYLE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>������λ��</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSUNIT"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>�ɹ�˵����</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","BUYGOODSDESC"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>���ۣ�</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSPRICE"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>�ɹ�������</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","GOODSNUMBER"));
					%>
				</td>
			</tr>
			
			<tr>
				<td><span>����ʱ�䣺</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","NEEDMONTH"));
					%>
				</td>
			</tr>
			<tr>
				<td><span>�ɹ�ģʽ��</span></td>
				<td>
					<%
					out.print(cu.print("COM_BUYGOODSITEM","BUYMODE"));
					%>
				</td>
			</tr>
		</table>
			
		
		<input type="button" id="btn_ok" style="display: none" onclick="ret()">
		<input name="entity" id="entity" type="hidden" value="COM_BUYGOODSITEM"/>
        <input name="act" type="hidden" id="act" value="add">
        <input name="COM_BUYGOODSITEM.SUMMITFLAG" id="COM_BUYGOODSITEM.SUMMITFLAG" type="hidden" value="0"/>
        <input name="COM_BUYGOODSITEM.INPUTDATE" id="COM_BUYGOODSITEM.INPUTDATE" type="hidden" value="<%=Format.getNowtime2()%>"/>
        <input name="COM_BUYGOODSITEM.HANDLER" id="COM_BUYGOODSITEM.HANDLER" type="hidden" value="<%=user.getStaffcode()%>"/>
        <input name="COM_BUYGOODSITEM.TOTALCOST" id="COM_BUYGOODSITEM.TOTALCOST" type="hidden" value=""/>
        <input type="hidden" name="COM_BUYGOODSITEM.GOODSCODE" id="COM_BUYGOODSITEM.GOODSCODE"/>
        <input type="hidden" name="COM_BUYGOODSITEM.PROJECTCODE" id="COM_BUYGOODSITEM.PROJECTCODE"/>
        <input type="submit" name="Submit" value="�ύ" style="display:none">
        <input type="reset" name="reset" value="����" style="display:none">
        <input name="action_class" type="hidden" id="action_class" value="com.action.buygoods.BuyGoodsInAction">
	</form>
		
	<script>
	  
	function ret(){
		var api = frameElement.api;
		
				  
		if(sumbit_check())
   	    {
			var goodnum=document.getElementById("COM_BUYGOODSITEM.GOODSNUMBER").value;
			var goodprice=document.getElementById("COM_BUYGOODSITEM.GOODSPRICE").value;
			if(goodnum>0&&goodprice>0){
				document.getElementById("COM_BUYGOODSITEM.TOTALCOST").value=goodnum*goodprice;
				if(document.getElementById("COM_BUYGOODSITEM.NEEDMONTH").value!=""){
					if(document.getElementById("COM_BUYGOODSITEM.BUYMODE").value!=""){
						 document.all("Submit").click();
	 	                 (api.data)({code:"refresh"});
					}else{
						createalert("��ѡ��ɹ�ģʽ��");
					}
		            
				}else{
				     createalert("���ڲ���Ϊ�գ�");
			    }
   	        }else{
   	    	     createalert("���ۺͲɹ���������Ϊ�գ�");
   	        }
   	    }
	}
	$("#btn_selectgoods").click(function(){
		var path="<%=path%>";
    	var u=path+"/buygoods/buygoodsin/goodsclasscheckbox_tree.jsp";
    	createwindow('ѡ�����',u,500,600,returnValue);
	    });
	function createwindow(title, url, width, height) {
		var api = frameElement.api, W = api.opener;
		W.$.dialog({
			id:'CLHG1976D',
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
				return true;
			}
		});
	
   }
   function returnValue(data){
       var str=data.code;
       var strs= new Array(); //����һ���� 
   	   strs=str.split(";"); //�ַ��ָ� 
   	   document.getElementById("COM_BUYGOODSITEM.GOODSNAME").value=strs[0];
   	   document.getElementById("COM_BUYGOODSITEM.GOODSCODE").value=strs[1];
   	   document.getElementById("COM_BUYGOODSITEM.PROJECTCODE").value=strs[2];
   	   document.getElementById("COM_BUYGOODSITEM.PROJECTNAME").value=strs[3];
   }
   function createalert(title){
	var api = frameElement.api, W = api.opener;
   	W.$.dialog({
   		id:'BLHG1976D',
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
<%
out.print(cu.getCheckstr());
%>