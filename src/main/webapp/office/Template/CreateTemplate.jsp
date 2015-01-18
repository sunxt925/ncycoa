<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*;"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
//******************************׿��PageOffice�����ʹ��*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
	//���ز˵���
	poCtrl1.setMenubar(false);
	//���ع�����
	poCtrl1.setCustomToolbar(false);
	//���ñ���ҳ��
	poCtrl1.setSaveFilePage("SaveNewTemplate.jsp");
	//String fnew_url=request.getParameter("f_url"); //�ļ���ftp�������µ�Ŀ¼��
	//request.getSession().setAttribute("new_url",fnew_url);
	//�½�Word�ļ���webCreateNew�����е����������ֱ�ָ���������ˡ��͡��½�Word�ĵ��İ汾�š�
	String tem=request.getParameter("mb");
	//System.out.println("kkkkkkkkkkkkkk     "+tem);
	if(tem!=null&&tem.length()>0){
		request.getSession().setAttribute("type",tem);
		if(tem.equals("0")){
			poCtrl1.webCreateNew("������",DocumentVersion.Word2003);
		}else if(tem.equals("1")){
			poCtrl1.webCreateNew("������",DocumentVersion.Excel2003);
		}else if(tem.equals("2")){
			poCtrl1.webCreateNew("������",DocumentVersion.PowerPoint2003);
		}
			
	}else{
		request.getSession().setAttribute("type","0");
	    poCtrl1.webCreateNew("������",DocumentVersion.Word2003);
	 }
	poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link href="../../images/csstg.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		        function Save() {
		            document.getElementById("PageOfficeCtrl1").WebSave();
		            if(document.getElementById("PageOfficeCtrl1").CustomSaveResult=="ok"){
			            alert('����ɹ���');
			            location.href = "TemplateManage.jsp";
		            }else{
		            	alert('����ʧ�ܣ�');
		            }
		        }
		
		        function Cancel() {
		            location.href = "TemplateManage.jsp";
		        }
		
		        function getFocus() {
		            var str = document.getElementById("FileSubject").value;
		            if (str == "�������ĵ�����") {
		                document.getElementById("FileSubject").value = "";
		            }
		        }
		        function lostFocus() {
		            var str = document.getElementById("FileSubject").value;
		            if (str.length <= 0) {
		                document.getElementById("FileSubject").value = "�������ĵ�����";
		            }
		        }
		        function show(val){
		                window.open("CreateTemplate.jsp?mb=" + val,"_self");
		        }
		    </script>
	</head>
	<body>
		<form id="form2" action="CreateWord.aspx">
			<div id="content">
				<div id="textcontent" style="width: auto; height: auto;">
					<div class="flow4">
						<span style="width: 100px;"> &nbsp; </span>
					</div>
					<div>
						�ĵ����⣺
						<input name="FileSubject" id="FileSubject" type="text"
							onfocus="getFocus()" onBlur="lostFocus()" class="boder"
							style="width: 180px;" value="�������ĵ�����" />
						<input type="button" onClick="Save()" value="����" />
						<input type="button" onClick="Cancel()" value="ȡ��" />
						ģ������:
						<select name="type" id="type" onChange="show(this.value)">
							<option value="0">wordģ��</option>
							<option value="1">excelģ��</option>
							<option value="2">pptģ��</option>
						</select>
					</div>
					<div>
						&nbsp;
					</div>
					<po:PageOfficeCtrl id="PageOfficeCtrl1" />
				</div>
			</div>
			<div id="footer">
				<hr width="1000px;" />
				
			</div>
		</form>
	</body>
</html>
