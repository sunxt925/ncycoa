<%@ page language="java" import="java.util.*,com.common.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.UserInfo"%>
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

</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
ComponentUtil cu=new ComponentUtil();
%>
<body>
<center>
    <div style="margin:0px 0;"></div>
    <div class="easyui-panel"  style="width:500px;padding:10px 0;margin:5px 0;">
        <div style="padding:10px 60px 20px 60px">
         <form name="form1" id="form1"  method="post" action="../servlet/PageHandler">
            <table cellpadding="20">
                <tr>
                    <td>申诉标题:</td>
                    <td><%
                       out.print(cu.print("TBM_COMPLAINT", "COMPLAINTTITLE"));
                    %></td>
                </tr>
               <tr>
                    <td>申诉类别:</td>
                    <td>
                        <%
                       out.print(cu.print("TBM_COMPLAINT", "COMPLAINTCATEGORY"));
                    %></td>
                </tr>
                <tr>
                    <td>申诉理由:</td>
                    <td><%
                       out.print(cu.print("TBM_COMPLAINT", "COMPLAINTREASON"));
                    %></td>
                </tr>
                
                <tr>
                    <td>申诉材料附件:</td>
                   <td>
                     <input type="text" id="TBM_COMPLAINT.ATTACHEDFILE" name="TBM_COMPLAINT.ATTACHEDFILE">
                     <a id="btn_uploadfile" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">上传文件</a>
                  </td>
                </tr>
                <tr>
                    <td>备注:</td>
                   <td><%
                       out.print(cu.print("TBM_COMPLAINT", "MEMO"));
                    %></td>
                </tr>
            </table>
            <input type="button" id="btn_ok" style="display: " onclick="ret()" value="提交">
		   <input type="hidden" id="TBM_COMPLAINT.COMPLAINTNO" name="TBM_COMPLAINT.COMPLAINTNO" value="<%=IndexCode.getRecno("CM")%>">
            <input type="hidden" id="TBM_COMPLAINT.COMPLAINTERCODE" name="TBM_COMPLAINT.COMPLAINTERCODE" value="<%=u.getStaffcode()%>">
            <input type="hidden" id="TBM_COMPLAINT.ENABLEDFLAG" name="TBM_COMPLAINT.ENABLEDFLAG" value="0">
            <input type="hidden" id="TBM_COMPLAINT.COMPLAINTDATE" name="TBM_COMPLAINT.COMPLAINTDATE" value="<%=Format.getNowtime2()%>">
            
            <input name="entity" id="entity" type="hidden" value="TBM_COMPLAINT"/>
             <input name="act" type="hidden" id="act" value="add">
             <input name="action_class" type="hidden" id="action_class" value="com.action.index.ComplaintAction">
             <input id="sss" type="submit" name="Submit" value="提交" style="display:none">
             <input type="reset" name="reset" value="重置" style="display:none">
             
       </form>
        
        </div>
    </div>
    <script>
    
      function ret(){
    	  var api = frameElement.api;
 		   if(sumbit_check())
	   	     { 
 			 if(document.all("TBM_COMPLAINT.COMPLAINTCATEGORY").value==""){
 				 alert("请选择申诉类别");
 			 }else{
 				 document.all("Submit").click();
	 			   (api.data)({code:"refresh"});
	 			   window.close();
 			 }
	   	     }
 		  
        }
        
        $("#btn_uploadfile").click(function(){
        	createwindow("文件上传","fileupload/fileupload.jsp",350,130);
        	    });
        function createwindow(title, url, width, height) {
        	var api = frameElement.api, W = api.opener;
    		 W.$.dialog({
    			data:returnValue,
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
    				return false;
    			},
    			cancelVal : '关闭',
    			cancel : true/* 为true等价于function(){} */
    		}); 
    		}
        function returnValue(data){
           document.getElementById("TBM_COMPLAINT.ATTACHEDFILE").value=data.code;
           
       }
    </script>
</body>
</center>
</html>
<%
out.print(cu.getCheckstr());
%>