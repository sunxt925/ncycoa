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
<script language="javascript" src="<%=path%>/js/public/check.js"></script>
</head>
<%
    UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
	int per_page = u.getPerpage_full();
	Indexitem indexitem=new Indexitem();
	String request_class=request.getParameter("indexccm");
	//String indexname=request.getParameter("indexname");
	String para1=request_class;
	String para2=request_class;
	DataTable dt=indexitem.getIndexList(para1,para2,page_no,per_page);
	DataTable dtcount=indexitem.getIndexAllList(para1,para2);
	int pagecount=dtcount.getRowsCount()/per_page;
	int ppp=dtcount.getRowsCount()%per_page;
	if(pagecount!=0&&ppp!=0)
			pagecount++;
	if(pagecount==0)
		pagecount=1;
 %> 
<body>
<form name="form1" id="form1" method="post" action="../servlet/PageHandler">
    <div id="p" style="width: 95%;padding: 10px">
    <a id="btn_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">����</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    </div>
	
		<%
			if (dt != null && dt.getRowsCount() >= 0) {
				TableUtil tu = new TableUtil();
				tu.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
				tu.setDt(dt);
				tu.setCheckBoxName("ѡ��");
				tu.setRowWidth("ָ��ֵ���㹫ʽ", "100px");
				tu.setRowWidth("��������", "80px");
				tu.setRowWidth("�÷ֺ���", "80px");
				tu.setDisplayCol("���,indexcode,uniindexcode,ָ��ʹ������, ָ����, �Ƿ�ָ��, ��ָ�����, ����ֵ��������,��������,ָ��ֵ������λ, scorefunctype,isrequired, scoreperiod, scoredefault, scoresumlow, scoresummax, uppersumweight,ָ�꿪ʼʹ������,ָ�����ʹ������,enabled,memo, rn");
				tu.setCheckBoxValue("indexcode");
				tu.setRowValue(
						"����",
						"<a  href=\"#\"  onclick=\"modify('@indexcode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\">�޸�</a><a  href=\"#\"  onclick=\"del('@indexcode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove',plain:true\">ɾ��</a>");
				out.print(tu.DrawTable());
			}
		%>
	
	<div align="right">
		<%
		String para="indexccm="+request_class;
			out.print(PageUtil.DividePage(page_no, pagecount, "indexlist.jsp",
					para));
		%>
	</div>
	    <input name="entity" id="entity" type="hidden" value="TBM_INDEXITEM"/>
	    <input name="index_class" id="index_class" type="hidden" value="<%=request_class %>"/>
	    <input name="indexcode" id="indexcode" type="hidden" value=""/>
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
    function modify(para){
    	var u="indexmanage/indexmod.jsp?indexcode="+para;
    	createwindow("�޸�",u,800,500);
    }
    function del(para){
    	document.all("act").value="del";
    	document.getElementById("indexcode").value=para;
    	$.dialog.confirm('ȷ��ɾ��������¼��',function(){
    		
	           document.all("form1").submit();
        });	
    	
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
					return false;
					// this.title(title).time(0.5);
					// $('#center').panel('open').panel('refresh');
				},
				cancelVal : '�ر�',
				cancel : true/* Ϊtrue�ȼ���function(){} */
			});
		
	}
    $("#btn_ref").click(function(){
    	//$('#center').panel('open').panel('refresh');
    	window.location.reload();
    	    });
    $("#btn_save").click(function(){
    	var pp="<%=request_class %>";
    	var u="indexmanage/indexadd.jsp?pIndexcode="+pp;
    	createwindow("����",u,800,500);
    	    });
    $("#btn_del").click(function(){
    	document.all("act").value="del";
    	
    	if (CheckSelect("form1"))
    	{
    		$.dialog.confirm('ȷ��ɾ����',function(){
    			           document.all("form1").submit();
    	               });	
    	}
    	else
    	{
    		createalert ("��û��ѡ��Ҫɾ�������ݣ�");
    	}
    })
    
    
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
