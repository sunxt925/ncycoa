<%@ page contentType="text/html; charset=GB18030" language="java" import="java.sql.*,com.db.*, java.io.PrintWriter,com.common.*,java.util.regex.Pattern,java.util.regex.Matcher,java.util.ArrayList"  errorPage="" %> 

<%
	response.setContentType("text/html;charset=GB18030"); 
	 DBObject db=new DBObject();
	 String role_id = request.getParameter("role_id");
     String list =""; 
    String sql="select * from SYSTEM_MENU_PRIVILLEGE where role_id=? ";
    Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]{ new Parameter.String(role_id) };
	DataTable dt = db.runSelectQuery(sql, pp);
	String sql1="select * from system_menu order by level_code ";
	DataTable dt1=db.runSelectQuery(sql1);
	
	
	      if (dt!=null && dt.getRowsCount()>0)
		 {
		 //System.out.println("执行到这里 ");		
		 	for (int i=0;i<dt.getRowsCount();i++)
			{
				DataRow r=dt.get(i);
				//list+=r.getString("level_code");
				if (i == dt.getRowsCount() - 1)
				{
					list+=r.getString("level_code");
					
				}
				else
				{
					list+=r.getString("level_code") + ",";
					//System.out.println(list);
				}
				
				
		    }
		    
			/*Pattern p = Pattern.compile("\\s*|\t|\r|\n"); 
            Matcher m = p.matcher(list); 
            list = m.replaceAll(""); */
        PrintWriter write = response.getWriter();
        write.print(list);
        write.close();

		  // out.println(list);
		    
			
		}
%>