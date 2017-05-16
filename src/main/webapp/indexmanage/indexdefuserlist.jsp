<%@page import="com.entity.index.IndexArchUser"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
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
<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
int per_page = u.getPerpage_full()/2;
String  indexarchcode=request.getParameter("indexarchcode");
String indexclass=request.getParameter("indexclass");
IndexArchUser indexArchUser=new IndexArchUser();
DataTable dt=indexArchUser.getIndexarchuserlist(indexarchcode, page_no, per_page);
DataTable dtcount=indexArchUser.getAllIndexarchuserlist(indexarchcode);
int pagecount=dtcount.getRowsCount()/per_page;
int ppp=dtcount.getRowsCount()%per_page;
if(pagecount!=0&&ppp!=0)
		pagecount++;
if(pagecount==0)
	pagecount=1;
 %> 
<body>
<form name="form1" id="form1" method="post"action="../servlet/PageHandler">
    <div id="p" style="width: 95%;padding: 10px">
    <a id="btn_selectobject" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
    </div>
	
		<%
			if (dt != null && dt.getRowsCount() >= 0) {
				TableUtil tu = new TableUtil();
				tu.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
				tu.setDt(dt);
				tu.setCheckBoxName("选择");
			    if(!indexclass.equals("s")){
			    	tu.setDisplayCol("序号,indexarchcode,objectcode,memo,所属部门, rn");
			    }
			    else{
			    	tu.setDisplayCol("序号,indexarchcode,objectcode,memo, rn");
			    	
			    
			    }
			    	
				tu.setCheckBoxValue("indexarchcode,objectcode");
				if(indexclass.equals("s")){
					tu.setRowCode("对象编码", "@对象编码@"+",base_staff,staffcode,staffname");
					tu.setRowCode("所属部门", "@所属部门@"+",base_org,orgcode,orgname");
				}else{
					tu.setRowCode("对象编码", "@对象编码@"+",base_org,orgcode,orgname");
				}
				tu.setRowCode("对象类型", "@对象类型@"+",UseMode");
				tu.setRowValue(
						"操作",
						"<a  href=\"#\"  onclick=\"modify('@indexarchcode@','@objectcode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a><a  href=\"#\"  onclick=\"del('@indexarchcode@','@objectcode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>");
				out.print(tu.DrawTable());
			}
		%>
	<div align="right">
		<%
		String para="indexarchcode="+indexarchcode+"&indexclass="+indexclass;
			out.print(PageUtil.DividePage(page_no, pagecount, "indexdefuserlist.jsp",
					para));
		%>
	</div>
	<input name="entity" id="entity" type="hidden" value="TBM_INDEXARCHUSER" />
	<input name="indexarchcode" id="indexarchcode" type="hidden" >
	<input name="objectcode" id="objectcode" type="hidden" >
		<input name="act" type="hidden" id="act" value="del">
		<input name="TBM_INDEXARCHUSER.OBJECTCODE" id="TBM_INDEXARCHUSER.OBJECTCODE" type="hidden" >
		<input name="TBM_INDEXARCHUSER.OBJECTTYPE" id="TBM_INDEXARCHUSER.OBJECTTYPE" type="hidden" >
		<input name="TBM_INDEXARCHUSER.STARTDATE" id="TBM_INDEXARCHUSER.STARTDATE" type="hidden" >
		<input name="TBM_INDEXARCHUSER.ENDDATE" id="TBM_INDEXARCHUSER.ENDDATE" type="hidden" >
		<input name="TBM_INDEXARCHUSER.STAFFORG" id="TBM_INDEXARCHUSER.STAFFORG" type="hidden" >
		<input name="TBM_INDEXARCHUSER.MULTIINDEXORDER" id="TBM_INDEXARCHUSER.MULTIINDEXORDER" type="hidden" value="1" >
		<input name="indexclass" id="indexclass" type="hidden" value="<%=indexclass%>">
		<input type="hidden" name="TBM_INDEXARCHUSER.INDEXARCHCODE" id="TBM_INDEXARCHUSER.INDEXARCHCODE" value="<%=indexarchcode%>" />
		 <input type="submit" name="Submit" value="提交" style="display:none">
        <input type="reset" name="reset" value="重置" style="display:none">
		<input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexArchUserAction">
	</form>
	<script type="text/javascript">

function createwindow(title, url, width, height,func) {
	  $.dialog({
		  data:func,
		  id:'LHG1976D',
		  content : 'url:' + url,
		  lock : true,
		  width : width,
		  height : height,
		  title : title,
		  opacity : 0.3,
		  cache : false,
		  ok : function() {
			  $('#btn_ok', this.iframe.contentWindow.document).click();
			  this.title(title).time(2);
			  return false;
		  },
		  cancelVal : '关闭',
		  cancel : true/* 为true等价于function(){} */
	  });

  }
 $("#btn_ref").click(function(){
	 window.location.reload();
 }) ;  
 $("#btn_del").click(function(){
	 document.all("act").value="del";
	 document.getElementById("indexarchcode").value="<%=indexarchcode%>";
 	if (CheckSelect("form1"))
 	{
 		 $.dialog.confirm('确认删除该条记录吗',function(){
 		   		
 	           document.all("form1").submit();
 	       });	
 	}
 	else
 	{
 		createalert("你没有选中要删除的内容！");
 	}
 }) ;
 function del(para1,para2)
 {
    document.all("act").value="del";
    document.getElementById("indexarchcode").value=para1;
    document.getElementById("objectcode").value=para2;
    $.dialog.confirm('确认删除该条记录吗',function(){
   		
        document.all("form1").submit();
    });	
 }
 function returnvalue(data){
	 var d=data.code;
	 if(d=="refresh"){
		 window.setTimeout(function(){
			 window.location.reload();
		 }, 1000);
		
	 }
 }
 function modify(para1,para2){
 	var indexarchcode=para1;
 	var objectcode=para2;
 	var indexclass="<%=indexclass%>";
 	var u="indexmanage/indexarchusermod.jsp?indexclass="+indexclass+"&indexarchcode="+indexarchcode+"&objectcode="+objectcode;
 	createwindow("修改",u,500,400,returnvalue);
 }
 function CheckSelect(form_name)
 {
 	var res=0;
 	for (var i=0;i<document.all(form_name).elements.length;i++) 
 	{ 
 		var e=document.all(form_name).elements[i]; 
 		if (e.type=='checkbox' && e.id=='items') 
 		{ 
 			if (e.checked==true)
 			{
 				res++;
 			}
 		} 
 	} 
 	if (res==0)
 	{
 		return false;	
 	}
 	else
 	{
 		return true;	
 	}
 }
 
 
 
 function returnobjValue(data){
 	
 	var objectarray=data.code;
     var objectcode="";
     var stafforg="";
     var indexclass="<%=indexclass%>";
     if(indexclass!="s"){
     	 for(var i=0;i<objectarray.length;i++){
	        	objectcode=objectcode+objectarray[i].orgcode+",";
	        }
     }else{
     	 for(var i=0;i<objectarray.length;i++){
	 	        	objectcode=objectcode+objectarray[i].staffcode+",";
	 	        	stafforg=stafforg+objectarray[i].orgcode+",";
	 	        }
     }
     document.getElementById("TBM_INDEXARCHUSER.OBJECTCODE").value=objectcode;
    
     document.getElementById("act").value="add";
     if(objectcode!=""){
    		if(indexclass=="s"){
    	   		document.getElementById("TBM_INDEXARCHUSER.OBJECTTYPE").value="staff";
    	   	    document.getElementById("TBM_INDEXARCHUSER.STAFFORG").value=stafforg;
    	   	}else if(indexclass=="d"){
    	   		document.getElementById("TBM_INDEXARCHUSER.OBJECTTYPE").value="depart";
    	   	}else if(indexclass=="c"){
    	   		document.getElementById("TBM_INDEXARCHUSER.OBJECTTYPE").value="company";
    	   	}
    		document.getElementById("TBM_INDEXARCHUSER.STARTDATE").value="<%=Format.getNowtime2()%>";
	   	    document.getElementById("TBM_INDEXARCHUSER.ENDDATE").value="<%=Format.getFuturetime(10)%>";
	   	   document.all("Submit").click();
     } 
  
	}
 $("#btn_selectobject").click(function(){
	    var indexclass="<%=indexclass%>";
	      var url="";
	     if(indexclass=="s"){
	         url="indexmanage/selectstaff.jsp";
	     }else{
	         url="indexmanage/selectunit.jsp";
	     }
	   	createwindow('选择对象',url,800,600,returnobjValue);
	 
    });
 
</script>
</body>
</html>
