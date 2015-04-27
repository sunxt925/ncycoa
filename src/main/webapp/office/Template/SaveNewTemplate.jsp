<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,java.sql.*,com.ftp.*,com.entity.ftp.*,java.io.*,com.db.*,com.common.*"
	pageEncoding="gb2312"%>
<%@page import="javax.swing.JOptionPane"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
    
		//此处最好不用此方法pstmt.setBytes(1,fs.getFileBytes());获取Word文档中的内容数据，因为在Java中创建的二进制数组的长度是有限制的
	//String name=(String)request.getSession().getAttribute("name");
    FileSaver fs = new FileSaver(request, response);
	String templatePath = getServletContext().getRealPath("/")+"template";
	String FileSubject = fs.getFormField("FileSubject");
	String type=(String)request.getSession().getAttribute("type");
	String filename = "";
	if(type.equals("0")){
		filename = FileSubject+".doc";
	}else if(type.equals("1")){
		filename = FileSubject+".xls";
	}else if(type.equals("2")){
		filename = FileSubject+".ppt";
	}
    File file = new File(templatePath);
    File[] files = file.listFiles();  
    boolean flag= false;
    
     for(int i= 0;i<files.length;i++){
        if(files[i].getName().equals(filename)){
          flag = true;
        }
     }
      DBObject db = new DBObject();     
    String insertsql = "insert into OFFICE_TEMPLATE(template_id,template_name,template_type,template_path) values (template_id.nextval,?,?,?)";
     if(!flag){
     		fs.saveToFile(templatePath+ "/" + filename);
     			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(filename),
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
     } 
     while(flag){
          String inputString = JOptionPane.showInputDialog(null,"该模板名已存在,请重新输入。");
          System.out.println(inputString);
          String newname = "";
          if(type.equals("0")){
				newname = inputString+".doc";
		  }else if(type.equals("1")){
			 	newname = inputString+".xls";
		  }else if(type.equals("2")){
		        newname = inputString+".ppt";
		  }
          flag=false;
          for(int i= 0;i<files.length;i++){
         	 if(files[i].getName().equals(newname)){
          		 flag = true;
             }
          }
          if(!flag){
          		fs.saveToFile(templatePath+ "/" + newname);
          		     			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(newname),
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
          }
     }
   
	//设置保存结果 
	fs.setCustomSaveResult("ok");
    fs.close();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'SaveFile.jsp' starting page</title>

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
	</body>
</html>