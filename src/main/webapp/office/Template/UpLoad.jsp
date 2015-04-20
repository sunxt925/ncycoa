<%@ page language="java" import="java.util.*,java.io.*,com.entity.ftp.*,com.ftp.*,com.db.*,com.common.*" pageEncoding="utf-8"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="javax.swing.JPanel"%>
<% 
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript">
        var xmlHttp;
		function CallBack(){
			if(xmlHttp.status==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
		function AfterDocumentUpload() {
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UpFtp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
                       正在上传，请稍后。。
                       <%
String type="";
byte[] imageBytes = new byte[1024];
byte[] dest = new byte[0];
SmartUpload smart=new SmartUpload(); 
smart.initialize(pageContext);
smart.upload();
String SaveName=smart.getRequest().getParameter("LocalFile");
if ( SaveName== "请输入文档名称"){		
%>
<script type="text/javascript">
		alert("请输入文档名称");
		window.open("Localup.jsp","_self");
		</script>
<% 
	}
String filename = smart.getFiles().getFile(0).getFileName();
String typeExt=filename.substring(filename.length()-4,filename.length());
if(typeExt.equals(".doc")||typeExt.equals("docx")||typeExt.equals(".dot")){
	 type="0";
	 SaveName=SaveName+".doc";
}else if(typeExt.equals(".pdf")){
	 type="3";
	 SaveName=SaveName+".pdf";
}else if(typeExt.equals(".ppt")){
	 type="2";
	 SaveName=SaveName+".ppt";
}else if(typeExt.equals("xlsx")||typeExt.equals(".xls")){
	 type="1";
	 SaveName=SaveName+".xls";
}
DBObject db = new DBObject();
String sql="select template_name from office_template order by template_id";
DataTable dt=db.runSelectQuery(sql);
		 if (dt!=null && dt.getRowsCount()>0)
		 {
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
				String s=r.getString("template_name");
				if(s.equals(SaveName)){
				%>
						<script type="text/javascript">
								alert("命名冲突，请重新输入文档名称");
								window.open("UpLoadTemplate.jsp","_self");
						</script>
				<% 
				}
		    }
		 }
smart.getFiles().getFile(0).saveAs(getServletContext().getRealPath("/")+"template"+java.io.File.separator+SaveName);

	String insertsql = "insert into OFFICE_TEMPLATE(template_id,template_name,template_type,template_path) values (template_id.nextval,?,?,?)";
	Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	{ new Parameter.String(SaveName),
			new Parameter.String(type),new Parameter.String("template")
	};
	if(db.run(insertsql, pp)){
					%>
						<script type="text/javascript">
								alert("上传成功");
								window.open("TemplateManage.jsp","_self");
						</script>
					<% 
	}

%>
  </body>
</html>
