<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*,java.io.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
</HEAD>
<%
	DBObject db = new DBObject();
	String sql="select * from system_ftp where file_type=3 order by file_id";
	DataTable dt=db.runSelectQuery(sql);
%>
<script language="javascript">
				//function Cancel() {
		            //window.open("PDFManage.jsp","_self");
		       // }
		
		        function getFocus() {
		            var str = document.getElementById("LocalFile").value;
		            if (str == "�������ĵ�����") {
		                document.getElementById("LocalFile").value = "";
		            }
		        }
		        function lostFocus() {
		            var str = document.getElementById("LocalFile").value;
		            if (str.length <= 0) {
		                document.getElementById("LocalFile").value = "�������ĵ�����";
		            }
		        }
</script>
<body >
<form name="form1" id="form1" method="post" action="UpLoad.jsp" enctype="multipart/form-data">
<table width="100%" height="4%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="3%" class="main_table_topbg" height="31"><img src="../../images/table_lt.jpg" width="22" height="31"></td>
    <td width="94%" valign="middle" class="main_table_topbg" height="31">��ҳ &gt;&gt; �ĵ����� &gt;&gt; �ĵ�ģ�����&gt;&gt; �����ϴ�ģ��  </td>
    <td width="3%" align="right" class="main_table_topbg" height="31"><img src="../../images/table_rt.jpg" width="22" height="31"></td>
  </tr>
</table>
  			<div id="content">
				<div id="textcontent" style="width: auto; height: auto;">
					<div class="flow4">
						<span style="width: 100px;"> &nbsp; </span>
					</div>
						<div>
						�ļ�����<input name="LocalFile" id="LocalFile" type="text"
							onfocus="getFocus()" onBlur="lostFocus()" class="boder"
							style="width: 180px;" value="�������ĵ�����" /><br>
							·  &nbsp;����<input type="file" name="pic" /><br>
							<input type="submit" value="�ϴ�" ><input type="reset" value="����">
					</div>
					<div>
						&nbsp;
					</div>
					<po:PageOfficeCtrl id="PageOfficeCtrl1" />
				</div>
			</div>	
</form>
</body>
</HTML>