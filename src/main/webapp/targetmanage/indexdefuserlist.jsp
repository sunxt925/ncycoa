<%@page import="com.entity.index.IndexArchUser"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/gem-tags"%>
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
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
int per_page = u.getPerpage_full()/2;
// String  indexarchcode=request.getParameter("indexarchcode");
 String indexclass=(String)request.getAttribute("indexclass");
// IndexArchUser indexArchUser=new IndexArchUser();
// DataTable dt=indexArchUser.getIndexarchuserlist(indexarchcode, page_no, per_page);
// DataTable dtcount=indexArchUser.getAllIndexarchuserlist(indexarchcode);
// int pagecount=dtcount.getRowsCount()/per_page;
// int ppp=dtcount.getRowsCount()%per_page;
// if(pagecount!=0&&ppp!=0)
// 		pagecount++;
// if(pagecount==0)
// 	pagecount=1;
 %> 
<body>
<form name="form1" id="form1" method="post"action="../servlet/PageHandler">
    <div id="p" style="width: 95%;padding: 10px">
<!--     <a id="btn_selectobject" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> -->
     <a id="btn_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
    </div>
    
	<table width="100%" style="border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;" border="1" cellpadding="5" cellspacing="0" class="table_list">
	<tr height='22' bgcolor='D0E9ED' style="border-color: #ece9d8">
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;"><input type='checkbox' name='allitems' id='allitems' onclick='allitems_click()'></td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">体系编码</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">对象名称</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">对象类型</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">备注</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">操作</td>
	</tr>
	
	<c:forEach items="${items}" var="item">
            <tr>
            <td><input type="checkbox" id="items" name="items" value="${item.id}"></td>
					<td>${item.indexArchCode}</td>
					<td>${item.uniIndexCode}</td>
					<td>${item.objecttype}</td>
					<td>${item.memo}</td>
					<td><a  href="#"  onclick="modify('${item.id}')" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
	<a  href="#"  onclick="del('${item.id}')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a></td>
				</tr>
    </c:forEach>
	</table>
	<div align="right">
		<%
// 		String para="indexarchcode="+indexarchcode+"&indexclass="+indexclass;
// 			out.print(PageUtil.DividePage(page_no, pagecount, "indexdefuserlist.jsp",
// 					para));
		%>
	</div>
<!-- 	<input name="entity" id="entity" type="hidden" value="TBM_INDEXARCHUSER" /> -->
<!-- 	<input name="indexarchcode" id="indexarchcode" type="hidden" > -->
<!-- 	<input name="objectcode" id="objectcode" type="hidden" > -->
<!-- 		<input name="act" type="hidden" id="act" value="del"> -->
<!-- 		<input name="TBM_INDEXARCHUSER.OBJECTCODE" id="TBM_INDEXARCHUSER.OBJECTCODE" type="hidden" > -->
<!-- 		<input name="TBM_INDEXARCHUSER.OBJECTTYPE" id="TBM_INDEXARCHUSER.OBJECTTYPE" type="hidden" > -->
<!-- 		<input name="TBM_INDEXARCHUSER.STARTDATE" id="TBM_INDEXARCHUSER.STARTDATE" type="hidden" > -->
<!-- 		<input name="TBM_INDEXARCHUSER.ENDDATE" id="TBM_INDEXARCHUSER.ENDDATE" type="hidden" > -->
<!-- 		<input name="TBM_INDEXARCHUSER.STAFFORG" id="TBM_INDEXARCHUSER.STAFFORG" type="hidden" > -->
<!-- 		<input name="TBM_INDEXARCHUSER.MULTIINDEXORDER" id="TBM_INDEXARCHUSER.MULTIINDEXORDER" type="hidden" value="1" > -->
<%-- 		<input name="indexclass" id="indexclass" type="hidden" value="<%=indexclass%>"> --%>
<%-- 		<input type="hidden" name="TBM_INDEXARCHUSER.INDEXARCHCODE" id="TBM_INDEXARCHUSER.INDEXARCHCODE" value="<%=indexarchcode%>" /> --%>
		 <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
		<input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexArchUserAction">
	</form>
	<script type="text/javascript">
	function returnValue(data){
    	if(data.code=='refresh'){
    		window.setTimeout(function(){
        		window.location.reload();
        	},200);
    	}
    }
   
    function createwindow(title, url, width, height) {
		
			$.dialog({
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
					// this.title(title).time(2);
					 
					 return false;
				},
				cancelVal : '关闭',
				cancel : true/* 为true等价于function(){} */
			});
		
	}
    $("#btn_ref").click(function(){
    	window.location.reload();
    	    });
    $("#btn_save").click(function(){
    	var u="./objindexarchuser.htm?add&class=${classT}&archcode=${archcode}";
    	//var u="targetmanage/indexarchadd.jsp?index_class="+index_class+"&pIndexcode=-1";
    	createwindow("新增",u,800,650);
    	    });
    $("#btn_del").click(function(){
    	//批量删除
    	    });
    function ret(){
    	var api = frameElement.api;
    	
    	 (api.data)({code:"refresh"});
    	
    }
    function del(para)
    {
    	var actionUrl="./objindexarchuser.htm?del&id="+para;
    	var rows = null;
    	 $.dialog.confirm('删除',function(){
    		 
    		 $.get(actionUrl,function(data){
    			 var d = $.parseJSON(data);
     			if (d.success) {
     				var msg = d.msg;
     				
     			}
     			window.setTimeout(function(){
            		window.location.reload();
            	},100);
    		 });
			   
     });
    }
   
    function modify(para){
    	//alert("11");
    	var u="./objindexarchuser.htm?update&id="+para;
    	createwindow("修改",u,800,650);
    }
    function createalert(content){
    	$.dialog({
    	    content: content,
    	    title:'提示',
    	    ok: function(){
    	        this.title('提示').time(0.1);
    	        return false;
    	    },
    	    cancelVal: '关闭',
    	    cancel: true /*为true等价于function(){}*/
    	});
    }
 $("#btn_selectobject").click(function(){
	    var indexclass="<%=indexclass%>";
	      var url="";
	     if(indexclass=="S"){
	         url="indexmanage/selectstaff.jsp";
	         createwindow('选择对象',url,800,600,returnobjValues);
	     }else{
	         url="indexmanage/selectunit.jsp";
	         createwindow('选择对象',url,800,600,returnobjValue);
	     }
    });
 function returnorgValue(data){
		var org = data.code;
		var codes="";
		var names=""
		if(org.length>1){
			for(var i=0;i<org.length;i++){
				codes+=org[i].orgcode;
				codes+=",";
				names+=org[i].orgname;
				names+=",";
			}
			$('#manageDepart').val(codes);
			$('#apporgName').val(names);
		}else{
			$('#manageDepart').val(org[0].orgcode);
			$('#apporgName').val(org[0].orgname);
		}
	}
 
 function returnobjValues(data){

		var array = data.code;
		var staffs="";
		var names="";
		for(var i=0;i<array.length;i++){
			staffs += array[i].staffcode+",";
			names += array[i].staffname+",";
		}
		
		$('#participants').val(staffs);
		$('#managerName').val(names);
		//$('#numAttendee').val(array.length);	
	}
 
</script>
</body>
</html>
