<%@ page language="java"
	import="java.util.*,com.db.*,com.common.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*;"
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
	poCtrl1.setSaveFilePage("SaveNewFile.jsp");
	//�½�Word�ļ���webCreateNew�����е����������ֱ�ָ���������ˡ��͡��½�Word�ĵ��İ汾�š�
	String tem=request.getParameter("mb");
	System.out.println("createword:    "+tem);
	if(tem!=null&&tem.length()>0){
		request.getSession().setAttribute("templateName",tem);
		poCtrl1.webOpen("Templatestream.jsp", OpenModeType.docNormalEdit, "����");	
	}else{
	     poCtrl1.webCreateNew("������",DocumentVersion.Word2003);
	 }
	poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link href="../images/csstg.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		        function Save() {
		            document.getElementById("PageOfficeCtrl1").WebSave();
		            if(document.getElementById("PageOfficeCtrl1").CustomSaveResult=="ok"){
			            alert('����ɹ���');
			            location.href = "WordManage.jsp";
		            }else{
		            	alert('����ʧ�ܣ�');
		            }
		        }
		
		        function Cancel() {
		            location.href = "WordManage.jsp";
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
		        function OpenTemplate(){
		        	var mb = document.getElementById("template").value;
		        	window.open("CreateWord.jsp?mb=" + mb,"_self");
		        	//document.getElementById("form1").action = "CreatePPT.jsp?mb=" + mb;
		        }
		    </script>
	</head>
	<body>
		<form id="form1">
			<div id="content">
				<div id="textcontent" style="width: auto; height: auto;">
					<div class="flow4">
						<span style="width: 100px;"> &nbsp; </span>
					</div>
					<div>
						<div align="center">
						  <table width="1646" height="25" border="1">
                            <tr>
                              <td width="500" height="21">�ĵ����⣺
						 			 <input name="FileSubject" id="FileSubject" type="text"
											onfocus="getFocus()" onBlur="lostFocus()" class="boder"
											style="width: 180px;" value="�������ĵ�����" />
						  					<input type="button" onClick="Save()" value="����" />
						 					<input type="button" onClick="Cancel()" value="ȡ��" /></td>
                              <td width="576">
						  <%
								DBObject db = new DBObject();
								String sql="select template_name from office_template where template_type=0 order by template_id";
								DataTable dt=db.runSelectQuery(sql);
						  %>
                             <select name="template"
								id="template" style="width: 240px;">
									  <%
		 						if (dt!=null && dt.getRowsCount()>0)
		 						{
		 							for (int i=0;i<dt.getRowsCount();i++)
									{
										DataRow r=dt.get(i);
	  								  %>
	  								  <% if(i==0){
	  								  %>
											<option value='<%=r.getString("template_name") %>' >
												<%=r.getString("template_name") %>
											</option>
										 <% }else{%>
										    <option value='<%=r.getString("template_name") %>'>
												<%=r.getString("template_name") %>
											</option>
										 <%} %>
								<%
								}}
								%>
							</select>
							<input type="button" onClick="OpenTemplate()" value="ʹ��" />
							</td>
                              <td width="548">&nbsp;</td>
                            </tr>
                          </table>
						
						</div>
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
