<%@page import="com.entity.index.AllMeritGroupMember"%>
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
<script type="text/javascript" src="<%=path%>/js/public/select.js"></script>
</head>
<%
UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
int page_no=Integer.parseInt(Format.NullToZero(request.getParameter("page_no")));
int per_page = u.getPerpage_full()/3;
String groupno=request.getParameter("groupno");
AllMeritGroupMember allMeritGroupMember=new AllMeritGroupMember();
DataTable dt=allMeritGroupMember.getMeritgroupMemberlist(groupno,page_no,per_page);
DataTable dtcount=allMeritGroupMember.getAllMeritgroupMemberlist(groupno);
int pagecount=dtcount.getRowsCount()/per_page;
int ppp=dtcount.getRowsCount()%per_page;
if(pagecount!=0&&ppp!=0)
		pagecount++;
if(pagecount==0)
	pagecount=1;
 %> 
<body>
<form name="form2" id="form2" method="post"action="../../servlet/PageHandler">
    <div id="p" style="width: 95%;padding: 10px">
    <a id="btn_save_2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">����</a>
    <a id="btn_del_2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">ɾ��</a>
    <a id="btn_ref_2" href="#"    class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">ˢ��</a>
    </div>
	
		<%
			if (dt != null && dt.getRowsCount() >= 0) {
				TableUtil tu = new TableUtil();
				tu.setTablestyle("border-collapse:collapse;border:1px solid #464242;border-top:1px solid #ECE9D8;border-left:1px solid #ECE9D8;border-right:1px solid #ECE9D8;border-bottom:1px solid #ECE9D8;");
				tu.setDt(dt);
				tu.setCheckBoxName("ѡ��");
				tu.setHeadWidth("ѡ��", "3%");
				tu.setHeadWidth("ʹ�������", "10%");
				tu.setHeadWidth("��������", "10%");
				tu.setHeadWidth("��λ����", "10%");
				tu.setHeadWidth("Ա������", "10%");
				tu.setHeadWidth("����", "5%");
				tu.setDisplayCol("���,groupno,staffcode,ʹ�������,��λ����,��ע, rn");
				tu.setCheckBoxValue("groupno,staffcode");
				tu.setRowCode("��������", "@��������@"+",base_org,orgcode,orgname");
				tu.setRowCode("Ա������", "@��λ����@"+",base_staff,staffcode,staffname");
	            tu.setRowCode("ʹ�������","@ʹ�������@"+",objectType");
				tu.setRowValue(
						"����",
						"<a  href=\"#\"  onclick=\"del('@groupno@','@staffcode@')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove',plain:true\">ɾ��</a>");
				out.print(tu.DrawTable());
			}
		%>
	<div align="left">
		<%
		//	out.print("��������¼����"+dtcount.getRowsCount());
		   
		%>
		<span style="font-size: 12px">��������¼����<%=dtcount.getRowsCount() %>��</span>
	</div>	
	<div align="right">
		<%
			out.print(PageUtil.DividePage(page_no, pagecount, "allmeritgroupmember.jsp",
					"groupno="+groupno));
		   
		%>
	</div>
		<input name="entity" id="entity" type="hidden"
			value="TBM_ALLMERITGROUPMEMBER" /> <input type="hidden"
			name="groupno" id="groupno" />
			<input name="staffcode"  id="staffcode" type="hidden">
			 <input
			name="act" type="hidden" id="act" value="del"> <input name="recno"
			id="recno" type="hidden" value="" /> <input
			name="TBM_ALLMERITGROUPMEMBER.GROUPNO"
			id="TBM_ALLMERITGROUPMEMBER.GROUPNO" type="hidden"
			value="<%=groupno%>"> <input
			name="TBM_ALLMERITGROUPMEMBER.OBJECTTYPE"
			id="TBM_ALLMERITGROUPMEMBER.OBJECTTYPE" type="hidden"> <input
			name="TBM_ALLMERITGROUPMEMBER.ORGCODE"
			id="TBM_ALLMERITGROUPMEMBER.ORGCODE" type="hidden"> <input
			name="TBM_ALLMERITGROUPMEMBER.POSITIONCODE"
			id="TBM_ALLMERITGROUPMEMBER.POSITIONCODE" type="hidden"> <input
			name="TBM_ALLMERITGROUPMEMBER.STAFFCODE"
			id="TBM_ALLMERITGROUPMEMBER.STAFFCODE" type="hidden"> <input
			type="submit" name="Submit" value="�ύ" style="display:none"> <input
			type="reset" name="reset" value="����" style="display:none"> <input
			name="action_class" type="hidden" id="action_class"
			value="com.action.index.AllMeritGroupMemberAction">
	</form>
	<script type="text/javascript">
	 function returnValue(data){
 }

function createwindow_2(title, url, width, height,func) {
		
		$.dialog({
			data:func,
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
		});}
	 $("#btn_save_2").click(function(){
	 
	    	 var    url="indexmanage/selectstaff.jsp";
		     createwindow_2('ѡ�����',url,800,600,returnobjValue);
	    	    });
	 $("#btn_ref_2").click(function(){
		window.location.reload();
	 })
	 $("#btn_del_2").click(function(){
		  document.all("act").value="del";
			if (CheckSelect("form2"))
			{
				$.dialog.confirm('ȷ��ɾ��������¼��',function(){
			   		
			           document.all("form2").submit();
			       });	
			}
			else
			{
				createalert ("��û��ѡ��Ҫɾ�������ݣ�");
			} 
		
	 })
	 function returnobjValue(data){
 	    var objectarray=data.code;
        var objectcode="";
        var orgcodes="";
        var positioncodes="";
              for(var i=0;i<objectarray.length;i++){
	 	        	objectcode=objectcode+objectarray[i].staffcode+",";
	 	        	orgcodes=orgcodes+objectarray[i].orgcode+",";
	 	        	positioncodes=positioncodes+objectarray[i].positioncode+",";
	 	        }
              if(objectcode!=""){
          	   		document.getElementById("TBM_ALLMERITGROUPMEMBER.OBJECTTYPE").value="1";
          	   	    document.getElementById("TBM_ALLMERITGROUPMEMBER.STAFFCODE").value=objectcode;
          	   	 document.getElementById("TBM_ALLMERITGROUPMEMBER.ORGCODE").value=orgcodes;
          	   document.getElementById("TBM_ALLMERITGROUPMEMBER.POSITIONCODE").value=positioncodes;
          	   	    document.getElementById("act").value="add";
          	     	document.all("Submit").click();
               } 
	}
    function modify(para1,para2){
    	var groupno=para1;
    	var staffcode=para2;
    	var u="indexmanage/allmeritmanage/groupmembermod.jsp?groupno="+groupno+"&staffcode="+staffcode;
    	createwindow_2("�޸�",u,500,400,returnValue);
    }
	 function del(para1,para2){
		 document.all("act").value="del";
	       document.getElementById("groupno").value=para1;
	       document.getElementById("staffcode").value=para2;
	       $.dialog.confirm('ȷ��ɾ��������¼��',function(){
	   		
	           document.all("form2").submit();
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
   </script>
</body>
</html>
