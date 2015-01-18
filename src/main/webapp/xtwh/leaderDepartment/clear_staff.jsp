<%@ page contentType="text/html; charset=GB18030" language="java" import="net.sf.json.*,java.sql.*,com.db.*, java.io.PrintWriter,com.common.*,java.util.regex.Pattern,java.util.regex.Matcher,java.util.ArrayList"  errorPage="" %> 

<%
	response.setContentType("text/html;charset=GB18030"); 
	 DBObject db=new DBObject();
	 String staffcode = request.getParameter("staffcode");
	 //System.out.println(staffcode);
     String list ="false"; 
    String sql="delete  from tbm_admindpt where staffcode ='"+staffcode+"' ";
	//DataTable dt = db.runSelectQuery(sql);
	if(db.run(sql)){
		list="true";
        PrintWriter write = response.getWriter();
        write.print(list);
        write.close();
		}
	else{
		list="false";
        PrintWriter write = response.getWriter();
        write.print(list);
        write.close();
	} 
%>