<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*"%>
<%@page import="org.activiti.engine.*"%>

<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
	// Get Activiti services
	RuntimeService runtimeService = processEngine
		.getRuntimeService();
	// Start a process instance
	Map params = new HashMap();
	params.put("owner", "user");
	params.put("public", "public1");
	params.put("towhere", "yingxiao");
	List<String> yingxiaogroup=new ArrayList<String>();
	List<String> zhuanmaigroup=new ArrayList<String>();
	List<String> anquangroup=new ArrayList<String>();
	List<String> wuliugroup=new ArrayList<String>();
	List<String> jichugroup=new ArrayList<String>();
	List<String> weiyuangroup=new ArrayList<String>();
	yingxiaogroup.add("yingxiao1");
	yingxiaogroup.add("yingxiao2");
	zhuanmaigroup.add("zhuanmai1");
	zhuanmaigroup.add("zhuanmai2");
	anquangroup.add("anquan1");
	anquangroup.add("anquan2");
	wuliugroup.add("wuliu1");
	wuliugroup.add("wuliu2");
	jichugroup.add("jichu1");
	jichugroup.add("jichu2");
	weiyuangroup.add("weiyuan1");
	weiyuangroup.add("weiyuan2");
	params.put("YingXiaoGroup",yingxiaogroup);
	params.put("ZhuanMaiGroup",zhuanmaigroup);
	params.put("AnQuanGroup",anquangroup);
	params.put("WuLiuGroup",wuliugroup);
	params.put("JiChuGroup",jichugroup);
	params.put("WeiYuanGroup",weiyuangroup);
	runtimeService.startProcessInstanceByKey("stdflow", params);
	response.sendRedirect("index.jsp");
%>