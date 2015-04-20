<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.db.*,com.common.*,com.entity.system.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Position position=new Position();
	String name=request.getParameter("PositionCode");
	//System.out.println("hello"+name);
    DataTable dt=position.getAllPositionListDrawTable(name);
    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

    
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
  <!--<table style="height: 300px">
  <tr>
  <td>Èö´ó½ã·ò¹þ¾Æ</td>
  </tr>
  
  </table>
       --><!--<div style="overflow-x: auto; overflow-y: auto; height: 600px;">
    --><%
    //for(int i=1;i<5;i++){
    
		if (dt!=null && dt.getRowsCount()>0) {
		TableUtil tableutil=new TableUtil();
		tableutil.setDt(dt);
	   out.print(tableutil.DrawTable());
	   }
	  /* if (dt!=null && dt.getRowsCount()>0)
				{
					for (int j=0;j<dt.getRowsCount();j++)
					{
						DataRow r=dt.get(j);
						
						//out.print("<option id='"+r.getString("role_id")+"' value='privillege.jsp?role_id="+r.getString("role_id")+"'>"+r.getString("role_name")+"</option>");
						//out.print("<option  value='"+r.getString("rolecode")+"' "+selected+">"+r.getString("rolename")+"</option>");
					}
				}
	   */
	   
	  // }
	%>
	<!--
      </div> --><br>
  </body>
</html>
