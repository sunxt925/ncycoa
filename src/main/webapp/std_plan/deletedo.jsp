<%@page contentType="text/html;charset=UTF-8"%>

<%@page import="java.util.*,java.io.*,com.entity.std.DocPlan"%>


<%
	String id=request.getParameter("id");
	String planpath=request.getParameter("planpath");
	DocPlan plan=new DocPlan();
	boolean flag=plan.deletebyID(Integer.parseInt(id));
	String path = getServletContext().getRealPath("/");
	File f=new File(planpath);
	f.delete();
	if(flag){
    	response.getWriter().write("删除成功");
    }else{
    	
    	response.getWriter().write("删除失败");
    }
	response.getWriter().flush();
	response.getWriter().close();
	//runtimeService.suspendProcessInstanceById(instanceid);//挂起
	
%>
