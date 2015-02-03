<%@ page contentType="text/html; charset=GB18030" language="java" import="java.sql.*,com.db.*, java.io.PrintWriter,com.common.*,java.util.regex.Pattern,java.util.regex.Matcher,java.util.ArrayList"  errorPage="" %> 

<%
	response.setContentType("text/html;charset=GB18030"); 
	 DBObject db=new DBObject();
	 String staffcode = request.getParameter("staffcode");
     String list =""; 
    String sql="select orgcode,orgname from base_org where orgcode in(select orgcode from tbm_admindpt where staffcode ='"+staffcode+"') order by orgcode ";
    //Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]{ new Parameter.String(role_id) };
	DataTable dt = db.runSelectQuery(sql);
	
	
	      if (dt!=null && dt.getRowsCount()>0)
		 {
		 //System.out.println("执行到这里 ");		
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
				//list+=r.getString("level_code");
				
					list+=r.getString("orgcode")+","+r.getString("orgname") + ";";
					//	
		    }
		    
		
        PrintWriter write = response.getWriter();
        write.print(list);
        write.close();

		  // out.println(list);
		    
			
		}
%>