<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.common.Util"%>
<%@page import="java.util.*,java.io.*,com.entity.std.DocPolicy"%>


<%
	String id=request.getParameter("id");
	String policypath=request.getParameter("policypath");
	DocPolicy policy=new DocPolicy();
	boolean flag=policy.deletebyID(Integer.parseInt(id));
	String path = getServletContext().getRealPath("/");
	File f=new File(Util.getfileCfg().get("uploadfilepath")+policypath);
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
