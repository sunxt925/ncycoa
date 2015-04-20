<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
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

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/tools/curdtools.js"></script>
</head>
<%

    UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full()/2-2;
	Indexitem indexitem=new Indexitem();
	String indexclass=request.getParameter("indexclass").toUpperCase();
	String request_class=request.getParameter("indexccm");
	DataTable dt=indexitem.getIndexitemList(request_class,page_no,per_page);
	DataTable dtcount=indexitem.getIndexAllitemList(request_class);
	int pagecount=dtcount.getRowsCount()/per_page;
	int ppp=dtcount.getRowsCount()%per_page;
	if(pagecount!=0&&ppp!=0)
			pagecount++;
	if(pagecount==0)
		pagecount=1;
 %>
<body class="easyui-layout">
<div  id="top" data-options="region:'north',split:true" style="height:350px; border: 0px;">
 <div id="p" style="width: 95%;padding: 10px">
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    </div>
<%
							if (dt != null && dt.getRowsCount() >= 0)
							{
								
								TableUtil tu = new TableUtil();
								tu.setDt(dt);
								tu.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
								
								tu.setCheckBoxName("ѡ��");
								tu.setDisplayCol("���,ѡ��,indexcode,memo,paravaluemode, rn");
								tu.setCheckBoxValue("indexcode");
								tu.setRowValue(
										"����",
										"<a  href=\"#\"  onclick=\"linkobj('@indexcode@','@��������@','@paravaluemode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\">��������</a><a  href=\"#\"  onclick=\"query('@indexcode@','@��������@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove',plain:true\">��ѯ</a>");
								out.print(tu.DrawTable());
							}
						%>
			<div align="right">
				<%
				    String para="indexccm="+request_class+"&indexclass="+indexclass;
					out.print(PageUtil.DividePage(page_no, pagecount,
							"referindexlist.jsp", para));
				%>
			</div>
		</div>

<div id="bottom" data-options="region:'center'" style="padding:5px;border: 0px;width: 90%;">
    <iframe src="" name="referobjectlist" style="border: 0px;width: 100%;height: 90%;">
     </iframe>
 </div>

   
  <script type="text/javascript">

    function linkobj(para1,para2,para3){
    	var u="referobjectlist.jsp?indexcode="+para1+"&paracode="+para2+"&refmode="+para3+"&indexclass="+"<%=indexclass%>";
    	window.open(u,"referobjectlist");
    	/*  $('#bottom').panel({
	    		 href:u
	    	 }); */
    }
    function query(para1,para2){
    	var u="referobjectquerylist.jsp?indexcode="+para1+"&paracode="+para2+"&indexclass="+"<%=indexclass%>";
    	window.open(u,"referobjectlist");
    	/*  $('#bottom').panel({
    		 href:u
    	 }); */
    }
    
    $("#btn_ref").click(function(){
    	window.location.reload();
    	    });
   
</script>
</body>
</html>
