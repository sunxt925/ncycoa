<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
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
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
int per_page = u.getPerpage_full();

//Indexitem indexitem=new Indexitem();
//String index_class=request.getParameter("class").toUpperCase();
//DataTable dt=indexitem.getIndexList(index_class,"-1",page_no,per_page);//-1��ʾ��ȡ���ڵ�
//DataTable dtcount=indexitem.getIndexAllList(index_class,"-1");

//int pagecount=dtcount.getRowsCount()/per_page;
//int ppp=dtcount.getRowsCount()%per_page;
//if(pagecount!=0&&ppp!=0)
//		pagecount++;
//if(pagecount==0)
//	pagecount=1;
 %> 
<body>
<form name="form1" id="form1" method="post"action="../servlet/PageHandler">
    <div id="p" style="width: 95%;padding: 10px">
    <a id="btn_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">����</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    </div>
	<table width="100%" style="border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;" border="1" cellpadding="5" cellspacing="0" class="table_list">
	<tr height='22' bgcolor='D0E9ED' style="border-color: #ece9d8">
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;"><input type='checkbox' name='allitems' id='allitems' onclick='allitems_click()'></td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">ָ�����</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">ָ������</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">ָ������</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">���㹫ʽ</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">��׼��ֵ</td>
	<td nowrap  align='center' style="border-color: #ece9d8;font-size:12px;">����</td>
	</tr>
	
	<c:forEach items="${items}" var="item">
            <tr>
            <td><input type="checkbox" id="items" name="items" value="${item.indexCode}"></td>
					<td>${item.indexCode}</td>
					<td>${item.indexName}</td>
					<td>${item.indexDesc}</td>
					<td>${item.valueFunc}</td>
					<td>${item.standardscore}</td>
					<td><a  href="#"  onclick="modify('${item.indexCode}')" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">�޸�</a>
	<a  href="#"  onclick="del('${item.indexCode}')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a></td>
				</tr>
    </c:forEach>
	</table>
	
	<div align="right">
	${items.size()}
		<%
		//	out.print(PageUtil.DividePage(page_no, pagecount, "indexrootlist.jsp",
		//			"class="+index_class));
		   
		%>
	</div>
	
	<input name="entity" id="entity" type="hidden" value="TBM_INDEXITEM" />
<%-- 	<input name="index_class" id="index_class" type="hidden" value="<%=index_class%>" /> --%>
	<input name="indexcode" id="indexcode" type="hidden" value="" />
	<input name="act" type="hidden" id="act" value="del">
	<input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexItemAction">
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
				cancelVal : '�ر�',
				cancel : true/* Ϊtrue�ȼ���function(){} */
			});
		
	}
    $("#btn_ref").click(function(){
    	window.location.reload();
    	    });
    $("#btn_save").click(function(){
    	var index_class="c";
    	var u="./objindexitem.htm?add_item&ccm=${pcode}";
    	//var u="targetmanage/indexarchadd.jsp?index_class="+index_class+"&pIndexcode=-1";
    	createwindow("����",u,800,650);
    	    });
    $("#btn_del").click(function(){
    	//����ɾ��
    	    });
    function ret(){
    	var api = frameElement.api;
    	
    	 (api.data)({code:"refresh"});
    	
    }
    function del(para)
    {
    	var actionUrl="./objindexitem.htm?del_arch&id="+para;
    	var rows = null;
    	alert("dsdsd");
    	createdialog('ɾ��ȷ�� ', 'ȷ��ɾ���ü�¼�� ?', actionUrl);
    	
    }
    function createdialog(title, content, url) {
    	alert("dsdsd");
    	$.dialog.confirm(
    		content, 
    		function(){
    			doSubmit(url, "ɾ��");
    			rowid = '';
    			
    		}, 
    		function(){
    		
    		}
    	).zindex();	
    }
    function doSubmit(url, gname) {
    	gridname=gname;
    	$.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		url : url,// �����action·��
    		error : function() {// ����ʧ�ܴ�����
    			 $(this).dialog('close');  
    		},
    		success : function(data) {
    			var d = $.parseJSON(data);
    			if (d.success) {
    				var msg = d.msg;
    				tip(msg);
    			
    				reloadTable(gname);
    				
    			}
    		}
    	});
    }
    function modify(para){
    	//alert("11");
    	var u="./objindexitem.htm?update_arch&id="+para;
    	createwindow("�޸�",u,800,650);
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
</script>
</body>
</html>
