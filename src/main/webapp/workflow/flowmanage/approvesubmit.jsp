<%@page contentType="text/html;charset=gb2312"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.activiti.engine.*"%>
<%@page import="org.activiti.engine.repository.ProcessDefinition"%>

<%@page import="org.activiti.engine.task.Task"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.*"%>


<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	TaskService taskService = processEngine
			.getTaskService();
	String taskId = request.getParameter("taskId");
	//request.setCharacterEncoding("gb2312");
	String result = request.getParameter("result");
	System.out.println("result::::::::::::::::::"+result);
	Map map = new HashMap();
	if(result.equals("������׼")){
		System.out.println("resulttongyi::::::::::::::::::"+result);
		map.put("go", true);//��׼
		map.put("back", false);//����
		map.put("over", false);
	}else if(result.equals("������")){
		System.out.println("resultbohui::::::::::::::::::"+result);
		map.put("go", false);//��׼
		map.put("back", true);//����
		map.put("over", false);
	}
	else if(result.equals("��������")){
		System.out.println("resultbohui::::::::::::::::::"+result);
		map.put("go", false);//��׼
		map.put("back", false);//����
		map.put("over", true);
	}
	taskService.complete(taskId,map);//ִ��  �в�
	
	
	
// 	ts.setVariables(taskId,map);
// 	ts.completeTask(taskId,result);
	
	response.sendRedirect("index.jsp");
%>