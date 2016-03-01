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
<link rel="stylesheet" type="text/css" href="<%=path%>/css/target.css">
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
 <script type="text/javascript">
            var pageSize = 15;    //ÿҳ��ʾ�ļ�¼����
             var curPage=0;        //��ǰҳ
             var lastPage;        //���ҳ
             var direct=0;        //����
            var len;            //������
            var page;            //��ҳ��
            var begin;
            var end;

                
            $(document).ready(function display(){   
                len =$("#mytable tr").length - 1;    // �����������������޳���һ�н���
                page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//���ݼ�¼����������ҳ��
                // alert("page==="+page);
                curPage=1;    // ���õ�ǰΪ��һҳ
                displayPage(1);//��ʾ��һҳ

                document.getElementById("btn0").innerHTML="��ǰ " + curPage + "/" + page + " ҳ    ÿҳ ";    // ��ʾ��ǰ����ҳ
                document.getElementById("sjzl").innerHTML="��������: " + len + "";        // ��ʾ������
                document.getElementById("pageSize").value = pageSize;

                

                $("#btn1").click(function firstPage(){    // ��ҳ
                    curPage=1;
                    direct = 0;
                    displayPage();
                });
                $("#btn2").click(function frontPage(){    // ��һҳ
                    direct=-1;
                    displayPage();
                });
                $("#btn3").click(function nextPage(){    // ��һҳ
                    direct=1;
                    displayPage();
                });
                $("#btn4").click(function lastPage(){    // βҳ
                    curPage=page;
                    direct = 0;
                    displayPage();
                });
                $("#btn5").click(function changePage(){    // תҳ
                    curPage=document.getElementById("changePage").value * 1;
                    if (!/^[1-9]\d*$/.test(curPage)) {
                        alert("������������");
                        return ;
                    }
                    if (curPage > page) {
                        alert("��������ҳ��");
                        return ;
                    }
                    direct = 0;
                    displayPage();
                });

                
                $("#pageSizeSet").click(function setPageSize(){    // ����ÿҳ��ʾ��������¼
                    pageSize = document.getElementById("pageSize").value;    //ÿҳ��ʾ�ļ�¼����
                    if (!/^[1-9]\d*$/.test(pageSize)) {
                        alert("������������");
                        return ;
                    }
                    len =$("#mytable tr").length - 1;
                    page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//���ݼ�¼����������ҳ��
                    curPage=1;        //��ǰҳ
                     direct=0;        //����
                     firstPage();
                });
            });

            function displayPage(){
                if(curPage <=1 && direct==-1){
                    direct=0;
                    alert("�Ѿ��ǵ�һҳ��");
                    return;
                } else if (curPage >= page && direct==1) {
                    direct=0;
                    alert("�Ѿ������һҳ��");
                    return ;
                }

                lastPage = curPage;

                // �޸���len=1ʱ��curPage�����0��bug
                if (len > pageSize) {
                    curPage = ((curPage + direct + len) % len);
                } else {
                    curPage = 1;
                }

                
                document.getElementById("btn0").innerHTML="��ǰ " + curPage + "/" + page + " ҳ    ÿҳ ";        // ��ʾ��ǰ����ҳ

                begin=(curPage-1)*pageSize + 1;// ��ʼ��¼��
                end = begin + 1*pageSize - 1;    // ĩβ��¼��

                
                if(end > len ) end=len;
                $("#mytable tr").hide();    // ���ȣ���������Ϊ����
                $("#mytable tr").each(function(i){    // Ȼ��ͨ�������жϾ��������Ƿ�ָ���ʾ
                    if((i>=begin && i<=end) || i==0 )//��ʾbegin<=x<=end�ļ�¼
                        $(this).show();
                });
             }
    </script>
  
<body>
<form name="form1" id="form1" method="post"action="../servlet/PageHandler">
    <div id="p" style="width: 95%;padding: 10px">
    <a id="btn_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">����</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a>
    <a id="btn_ref" href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    </div>
	<table id="mytable"  class="rootlisttab"   width="100%" border="1" cellpadding="5" cellspacing="0" class="table_list">
	<tr class="title_table" height='22' bgcolor='D0E9ED' >
	<td nowrap >
	<input type='checkbox' name='allitems' id='allitems' onclick='allitems_click()'></td>
	<td nowrap >ָ�����</td>
	<td nowrap >ָ������</td>
	<td nowrap >ָ������</td>
	<td nowrap >���㹫ʽ</td>
	<td nowrap >�Ʒ�����</td>
	<td nowrap >����</td>
	</tr>
	
	<c:forEach items="${items}" var="item">
            <tr>
            <td><input type="checkbox" id="items" name="items" value="${item.indexCode}"></td>
					<td>${item.indexCode}</td>
					<td>${item.indexName}</td>
					<td>${item.indexDesc}</td>
					<td>${item.valueFunc}</td>
					<td>${item.scorePeriod}</td>
					<td><a  href="#"  onclick="modify('${item.indexCode}')" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">�޸�</a>
	<a  href="#"  onclick="del('${item.indexCode}')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a></td>
				</tr>
    </c:forEach>
	</table>
	<div class="pagecontent">
	<a id="btn0"></a>	<input id="pageSize" type="text" size="1" maxlength="2" value="getDefaultValue()"/><a> �� </a> 
		<a id="sjzl"></a> </div>
	
<div class="mypagination">
		
		
		<a  href="#" id="btn1">��ҳ</a>
		<a  href="#" id="btn2">��һҳ</a>
		<a  href="#" id="btn3">��һҳ</a>
		<a  href="#" id="btn4">βҳ</a> 
		<a>ת�� </a>
		<input id="changePage" type="text" size="1" maxlength="4"/>
		<a>ҳ </a>
		<a  href="#" id="btn5">��ת</a>
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
    	var u="./objindexitem.htm?add_item&ccm=${pcode}&classT=${classT}";
    	//var u="targetmanage/indexarchadd.jsp?index_class="+index_class+"&pIndexcode=-1";
    	createwindow("����",u,800,650);
    	    });
    $("#btn_del").click(function(){
    	var spCodesTemp = "";
        $("input:checkbox[name='items']:checked").each(function(i){
         if(0==i){
          spCodesTemp = $(this).val();
         }else{
          spCodesTemp += (","+$(this).val());
         }
        });
        
    	var actionUrl="./objindexitem.htm?del&id="+spCodesTemp;
    	$.dialog.confirm('ɾ��',function(){
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
    	 $.dialog.confirm('ɾ��',function(){
    		 
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
    	var u="./objindexitem.htm?update_item&id="+para;
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
