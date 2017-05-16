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
int per_page = u.getPerpage_full();

Indexitem indexitem=new Indexitem();
String index_class=request.getParameter("class").toUpperCase();
DataTable dt=indexitem.getIndexList(index_class,"-1",page_no,per_page);//-1表示获取根节点
DataTable dtcount=indexitem.getIndexAllList(index_class,"-1");
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
    <a id="btn_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
    </div>
	
		<%
			if (dt != null && dt.getRowsCount() >= 0) {
				TableUtil tu = new TableUtil();
				tu.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
				tu.setDt(dt);
				tu.setCheckBoxName("选择");
				tu.setRowWidth("编码描述", "80px");
				tu.setRowWidth("得分函数", "80px");
				tu.setDisplayCol("序号,indexcode,uniindexcode,指标使用限制, 指标类, 是否父指标, 父指标编码, 编码值计算类型,参数个数,指标值度量单位, scorefunctype,isrequired, scoreperiod, scoredefault, scoresumlow, scoresummax, uppersumweight,指标开始使用日期,指标结束使用日期,enabled,memo, rn");
				tu.setCheckBoxValue("indexcode");
				tu.setRowCode("计分周期", "@计分周期@"+",ScorePeriod");
				tu.setRowValue(
						"操作",
						"<a  href=\"#\"  onclick=\"modify('@indexcode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a><a  href=\"#\"  onclick=\"del('@indexcode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>");
				out.print(tu.DrawTable());
			}
		%>
	<div align="right">
		<%
			out.print(PageUtil.DividePage(page_no, pagecount, "indexrootlist.jsp",
					"class="+index_class));
		   
		%>
	</div>
	
	<input name="entity" id="entity" type="hidden" value="TBM_INDEXITEM" />
	<input name="index_class" id="index_class" type="hidden" value="<%=index_class%>" />
	<input name="indexcode" id="indexcode" type="hidden" value="" />
	<input name="act" type="hidden" id="act" value="del">
	<input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexItemAction">
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
    	var index_class="<%=index_class%>";
    	var u="indexmanage/indexadd.jsp?index_class="+index_class+"&pIndexcode=-1";
    	createwindow("新增",u,800,650);
    	    });
    $("#btn_del").click(function(){
    	document.all("act").value="del";
    	if (CheckSelect("form1"))
    	{
    		$.dialog.confirm('确认删除吗',function(){
        		
 	           document.all("form1").submit();
           });	
    	}
    	else
    	{
    		createalert("你没有选中要删除的内容！");
    		
    	}
    	    });
    function del(para)
    {
       document.all("act").value="del";
       document.getElementById("indexcode").value=para;
       $.dialog.confirm('确认删除该条记录吗',function(){
   		
           document.all("form1").submit();
       });	
    }
    function modify(para){
    	
    	var u="indexmanage/indexmod.jsp?indexcode="+para;
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
</script>
</body>
</html>
