<%@page import="com.entity.index.ReferObjectIndex"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
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
int per_page = u.getPerpage_full()/2-2;
ReferObjectIndex ref=new ReferObjectIndex();
String indexcode=request.getParameter("indexcode");
String paracode=request.getParameter("paracode");
String refmode=request.getParameter("refmode");
String indexclass=request.getParameter("indexclass");
DataTable dt=ref.getReferobjectdeflist(indexcode,paracode,refmode,page_no, per_page);
DataTable dtcount=ref.getAllReferobjectdeflist(indexcode,paracode);
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
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
    </div>
	
		<%
			if (dt != null && dt.getRowsCount() >= 0) {
				TableUtil tu = new TableUtil();
				tu.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
				tu.setDt(dt);
				tu.setCheckBoxName("选择");
				tu.setDisplayCol("序号,objectcode,paracode,indexcode,被引用者,被引用者类别,被引用指标编码,memo,rn");
				if(indexclass.equals("S")){
					tu.setRowCode("使用者", "@使用者@"+",base_staff,staffcode,staffname");
				}else{
					tu.setRowCode("使用者", "@使用者@"+",base_org,orgcode,orgname");
				}
				
				tu.setRowCode("指标编码", "@指标编码@"+",tbm_indexitem,indexcode,indexname");
				tu.setRowCode("关联状态", "@关联状态@"+",linkflag");
				//tu.setCheckBoxValue("indexcode");
				tu.setRowValue(
						"操作",
						"<a  href=\"#\"  onclick=\"link('@objectcode@','@对象类型@','@指标编码@','@paracode@','@引用模式@','@关联状态@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\">关联</a>");
				out.print(tu.DrawTable());
			}
		%>
	<div align="right">
		<%
		String para="indexcode="+indexcode+"&paracode="+paracode+"&refmode="+refmode+"&indexclass="+indexclass;
		out.print(PageUtil.DividePage(page_no, pagecount,
						"referobjectlist.jsp", para));
		   
		%>
	</div>
	<input name="act" type="hidden" id="act" value="del">
	<input name="entity" id="entity" type="hidden" value="TBM_REFEROBJECTINDEX"/>
    <input type="submit" name="Submit" value="提交" style="display:none" />
    <input type="reset" name="reset" value="重置" style="display:none" />
    <input name="action_class" type="hidden" id="action_class" value="com.action.index.ReferObjectAction" />
	</form>
	<script type="text/javascript">

	function returnValue(data){
        var f=data.code;
        if(f=="refresh"){
        	window.setTimeout(function(){
        	 window.location.reload();
        	},1000);
        	
        }
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
					this.title(title).time(0.5);
					return false;
				},
				cancelVal : '关闭',
				cancel : true/* 为true等价于function(){} */
			});
		
	}
    $("#btn_ref").click(function(){
    	window.location.reload();
    	    });
  
    function link(objectcode,objecttype,indexcode,paracode,refmode,refstate){
    	if(refstate=="1"){
    		alert("已经关联,不能重复关联");
    	}else{
    		var u="indexmanage/indexreferadd.jsp?"+"objectcode="+objectcode+"&objecttype="+objecttype+"&paracode="+paracode+"&indexcode="+indexcode+"&refmode="+refmode+"&indexclass="+"<%=indexclass%>";
        	createwindow("关联",u,600,400);
    	}
    	
    }
   
</script>
</body>
</html>
