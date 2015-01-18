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

</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
int per_page = u.getPerpage_full()/2-2;
ReferObjectIndex ref=new ReferObjectIndex();
String indexcode=request.getParameter("indexcode");
String paracode=request.getParameter("paracode");
String indexclass=request.getParameter("indexclass");
DataTable dt=ref.getReferobjectlist(indexcode,paracode,page_no, per_page);
DataTable dtcount=ref.getAllReferobjectlist(indexcode,paracode);
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
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    </div>
	
		<%
			if (dt != null && dt.getRowsCount() >= 0) {
				TableUtil tu = new TableUtil();
				tu.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
				tu.setDt(dt);
				tu.setCheckBoxName("ѡ��");
				tu.setDisplayCol("���,recno,objectcode,paracode,memo,rn");
				if(indexclass.equals("S")){
					tu.setRowCode("ʹ����", "@ʹ����@"+",base_staff,staffcode,staffname");
					tu.setRowCode("��������", "@��������@"+",base_staff,staffcode,staffname");
				}else{
					tu.setRowCode("ʹ����", "@ʹ����@"+",base_org,orgcode,orgname");
					tu.setRowCode("��������", "@��������@"+",base_org,orgcode,orgname");
				}
				tu.setRowCode("ָ�����", "@ָ�����@"+",tbm_indexitem,indexcode,indexname");
				tu.setRowCode("������ָ�����", "@������ָ�����@"+",tbm_indexitem,indexcode,indexname");
				tu.setRowCode("��������", "@��������@"+",UseMode");
				tu.setRowCode("�����������", "@�����������@"+",UseMode");
				tu.setCheckBoxValue("recno");
				tu.setRowValue(
						"����",
						"<a  href=\"#\"  onclick=\"modify('@recno@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\">�޸�</a><a  href=\"#\"  onclick=\"del('@recno@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove',plain:true\">ɾ��</a>");
				out.print(tu.DrawTable());
			}
		%>
	<div align="right">
		<%
		String para="indexcode="+indexcode+"&paracode="+paracode+"&indexclass="+indexclass;
		out.print(PageUtil.DividePage(page_no, pagecount,
						"referobjectquerylist.jsp", para));
		   
		%>
	</div>
	<input name="act" type="hidden" id="act" value="del">
	<input name="entity" id="entity" type="hidden" value="TBM_REFEROBJECTINDEX"/>
    <input name="recno" id="recno" type="hidden" >
    <input name="indexcode" id="indexcode" type="hidden" value="<%=indexcode%>">
    <input name="paracode" id="paracode" type="hidden" value="<%=paracode%>">
    <input name="indexclass" id="indexclass" type="hidden" value="<%=indexclass%>">
    <input type="submit" name="Submit" value="�ύ" style="display:none" />
    <input type="reset" name="reset" value="����" style="display:none" />
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
				},
				cancelVal : '�ر�',
				cancel : true/* Ϊtrue�ȼ���function(){} */
			});
		
	}
    $("#btn_ref").click(function(){
    	window.location.reload();
    	    });
    $("#btn_del").click(function(){
    	document.all("act").value="del";
    	if (CheckSelect("form1"))
    	{
    		$.dialog.confirm('ȷ��ɾ��ѡ�м�¼��',function(){
        		
 	           document.all("form1").submit();
           });	
    	}
    	else
    	{
    		createalert("��û��ѡ��Ҫɾ�������ݣ�");
    	}
    	    });
    function modify(para){
    	var u="indexmanage/indexrefermod.jsp?recno="+para+"&indexclass="+"<%=indexclass%>";
    	createwindow("�޸�",u,600,400);
    }
    function del(para){
    	document.all("act").value="del";
    	document.getElementById("recno").value=para;
    	$.dialog.confirm('ȷ��ɾ��������¼��',function(){
    		
	           document.all("form1").submit();
        });	
    }
    function createalert(content){
    	$.dialog({
    	    content: content,
    	    title:'��ʾ',
    	    ok: function(){
    	        this.title('��ʾ').time(0.1);
    	        return false;
    	    },
    	    cancelVal: '�ر�',
    	    cancel: true /*Ϊtrue�ȼ���function(){}*/
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
</script>
</body>
</html>
