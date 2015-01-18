<%@ page contentType="text/html; charset=GB18030" language="java" import="java.text.SimpleDateFormat,java.util.Date,com.entity.system.*,net.sf.json.*,java.sql.*,com.db.*, java.io.PrintWriter,com.common.*,java.util.regex.Pattern,java.util.regex.Matcher,java.util.ArrayList"  errorPage="" %> 

<%
Date now = new Date(); 
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
String time = dateFormat.format( now ); 





String eventtype=request.getParameter("eventtype");
if(eventtype.equals("1"))
{
	eventtype="入库";
}
else
	eventtype="出库";
//System.out.println(eventtype);
	String eventID=request.getParameter("eventID");
	
	
	//System.out.println(time);
	String username=request.getParameter("username");
	String orgname=request.getParameter("orgname");
	response.setContentType("text/html;charset=GB18030"); 
	DBObject db=new DBObject();
	String sql = "insert into com_storeevent(storeeventno,eventtype,auditorcode,inputorgcode,inputdate) values (?,?,?,?,to_date(?,'yyyy-mm-dd'))";
	Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	{ new Parameter.String(eventID), new Parameter.String(eventtype),new Parameter.String(username), new Parameter.String(orgname),new Parameter.String(time)
	};
	PrintWriter write = response.getWriter();
	if (db.run(sql, pp))
	{
		  
       	  write.print("success");
          write.close();
    }
    else
    {
    	  write.print("faile");
          write.close();
    }
		  
%>