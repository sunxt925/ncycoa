<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.entity.system.TbmSumlog"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
                  request.getServerName()+":"+
		          request.getServerPort()+path+"/";
%>
<%
String selectedyear=request.getParameter("Year");

int selectYear=0;
if(selectedyear!=null&&!selectedyear.equals("selection")){
	
	selectYear= Integer.parseInt(selectedyear);
}

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
<script type="text/javascript">
function change()
	{
		//alert(document.all("menuYear").value);
		window.location.href="index.jsp?Year="+document.all("menuYear").value;
	}
</script>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
int per_page = u.getPerpage_full();

TbmSumlog tbmsumlog=new TbmSumlog();
String parameteryear=String.valueOf(selectYear);

DataTable dt=tbmsumlog.getIndexList(parameteryear,page_no,per_page);//-1��ʾ��ȡ���ڵ�
DataTable dtcount=tbmsumlog.getIndexAllList(parameteryear);
int pagecount=dtcount.getRowsCount()/per_page;
int ppp=dtcount.getRowsCount()%per_page;
if(pagecount!=0&&ppp!=0)
		pagecount++;
if(pagecount==0)
	pagecount=1;
 %> 
<body>
<form name="form1" id="form1" method="post"action="../servlet/PageHandler">
    <div id="p" style="width: 100%;padding: 10px">
    ���ѡ��<select  name="menuYear" id="menuYear"  onChange="change()" >
          <option value="selection">==��ѡ��==</option>
            <%
            	Calendar c=Calendar.getInstance();//���ϵͳ��ǰ����    
            	int year=c.get(Calendar.YEAR);
            	String selected="";
				
            	for(int i=year;i>=year-1;i--){
            		if(i==selectYear)
    					selected="selected";
            		out.print("<option  value='"+i+"' "+selected+">"+i+"</option>");
            		if(i==2014)
            			break;
            	}
				
				
			%>
          </select>
    <a id="btn_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">����</a>
    <a id="btn_del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a>
    <a id="btn_ref" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    </div>
	
		<%
			if (dt != null && dt.getRowsCount() >= 0) {
				TableUtil tableutil=new TableUtil();
				tableutil.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
				tableutil.setDt(dt);
				tableutil.setRowStateOp("����","2,δ����,��ʱ����,������,<a onclick='sss('@iii@')'></a>,<a></a>");
			    out.print(tableutil.DrawTable());
			}
		%>
	<div align="right">
		<%
			out.print(PageUtil.DividePage(page_no, pagecount, "index.jsp",
					"Year="+selectedyear));
		   
		%>
	</div>
	<input name="entity" id="entity" type="hidden" value="TBM_INDEXITEM" />

	<input name="indexcode" id="indexcode" type="hidden" value="" />
	<input name="act" type="hidden" id="act" value="del">
	<input name="action_class" type="hidden" id="action_class" value="com.action.index.IndexItemAction">
	</form>
	<script type="text/javascript">

    
   
    function createwindow(title, url, width, height) {
		
			$.dialog({
				content : 'url:' + url,
				lock : true,
				width : width,
				height : height,
				title : title,
				opacity : 0.3,
				cache : false,
				ok : function() {
					//$('#btn_ok', this.iframe.contentWindow.document).click();
				},
				cancelVal : '�ر�',
				cancel : true/* Ϊtrue�ȼ���function(){} */
			});
		
	}
    $("#btn_ref").click(function(){
    	window.location.reload();
    	    });
    $("#btn_save").click(function(){
    	var index_class="";
    	var u="indexmanage/add.jsp?index_class="+index_class+"&pIndexcode=-1";
    	createwindow("����",u,800,650);
    	    });
    $("#btn_del").click(function(){
    	document.all("act").value="del";
    	if (CheckSelect("form1"))
    	{
    		if (confirm("ȷ��Ҫɾ��ѡ�еļ�¼��"))
    		{
    			document.all("form1").submit();
    			
    		}
    	}
    	else
    	{
    		alert ("��û��ѡ��Ҫɾ�������ݣ�");
    	}
    	    });
    function del(para)
    {
       document.all("act").value="del";
       document.getElementById("indexcode").value=para;
       if (confirm("ȷ��Ҫɾ��ѡ�еļ�¼��"))
         {
    	   document.all("form1").submit();
         }
    }
    function modify(para){
    	
    	var u="indexmanage/mod.jsp?indexcode="+para;
    	createwindow("�޸�",u,800,650);
    }
</script>
</body>
</html>
