package com.workflow.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.db.DBObject;
import com.db.DataTable;
import com.entity.stdapply.DocApplyWhere;
import com.entity.system.StaffInfo;
import com.workflow.orm.InstanceInfo;
import com.workflow.service.InstanceService; 
public class ProcessHandler {
	private java.util.Properties m_ftpProperties;
	private static String qicao;
	private static String shenhe;
	private static String jishushenhe;
	private static String fabu;
	private static String orgfabu;
	private static String orgyingxiao;
	private static String orgzhuanmai;
	private static String organquan;
	private static String orgwuliu;
	private static String orgjichu;
	private static String orgweiyuan;
	public ProcessHandler(){}
//	public void deployStdFlow(HttpServletRequest request){
//		ServletContext servletContext = request.getSession().getServletContext();  
//        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
//        RepositoryService repositoryService = processEngine
//    			.getRepositoryService();
//        repositoryService.createDeployment()
//        .name("stdflow.bar")
//        .addClasspathResource("resource/stdflow.bpmn")
//        .addClasspathResource("resource/stdflow.png")
//        .deploy();
//	}
public boolean startProcessinstance(String staffcode,ProcessEngine processEngine) throws Exception{
	LoadConfig("/com/workflow/serviceimpl/position.properties");
	DataTable ApproveOrg =isApplyMan(staffcode);
	if(ApproveOrg.getRowsCount()==0)
		return false;
	// Get Activiti services
	RuntimeService runtimeService = processEngine
		.getRuntimeService();
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("owner",staffcode);
	List<String> yingxiaogroup=new ArrayList<String>();
	List<String> zhuanmaigroup=new ArrayList<String>();
	List<String> anquangroup=new ArrayList<String>();
	List<String> wuliugroup=new ArrayList<String>();
	List<String> jichugroup=new ArrayList<String>();
	List<String> weiyuangroup=new ArrayList<String>();
	String[] jishu=jishushenhe.split(",");
	for(int j=0;j<jishu.length;j++){
	DataTable yingxiaoDT=getMemberList(jishu[j],orgyingxiao);
	for(int i=0;i<yingxiaoDT.getRowsCount();i++){
		yingxiaogroup.add(yingxiaoDT.get(i).getString("staffcode"));
	}
	
	DataTable zhuanmaiDT=getMemberList(jishu[j],orgzhuanmai);
	for(int i=0;i<zhuanmaiDT.getRowsCount();i++){
		zhuanmaigroup.add(zhuanmaiDT.get(i).getString("staffcode"));
	}
	
	DataTable anquanDT=getMemberList(jishu[j],organquan);
	for(int i=0;i<anquanDT.getRowsCount();i++){
		anquangroup.add(anquanDT.get(i).getString("staffcode"));
	}
	
	DataTable wuliuDT=getMemberList(jishu[j],orgwuliu);
	for(int i=0;i<wuliuDT.getRowsCount();i++){
		wuliugroup.add(wuliuDT.get(i).getString("staffcode"));
	}
	
	DataTable jichuDT=getMemberList(jishu[j],orgjichu);
	for(int i=0;i<jichuDT.getRowsCount();i++){
		jichugroup.add(jichuDT.get(i).getString("staffcode"));
	}
	}
	DataTable weiyuanDT=getMemberList(shenhe,orgweiyuan);
	for(int i=0;i<weiyuanDT.getRowsCount();i++){
		weiyuangroup.add(weiyuanDT.get(i).getString("staffcode"));
	}
	DataTable fabuDT=getMemberList(fabu,orgfabu);
	String fabustaffcode=fabuDT.get(0).getString("staffcode");
	map.put("public", fabustaffcode);
	map.put("YingXiaoGroup",yingxiaogroup);
	map.put("ZhuanMaiGroup",zhuanmaigroup);
	map.put("AnQuanGroup",anquangroup);
	map.put("WuLiuGroup",wuliugroup);
	map.put("JiChuGroup",jichugroup);
	map.put("WeiYuanGroup",weiyuangroup);
	//////////////////////////////////////////////////////////////////////////////
		String towhere="";
		String orgcode=ApproveOrg.get(0).getString("orgcode");
		if(orgcode.equals(orgyingxiao)){
			towhere="yingxiao";
		}else if(orgcode.equals(orgzhuanmai)){
			towhere="zhuanmai";
		}else if(orgcode.equals(organquan)){
			towhere="anquan";
		}else if(orgcode.equals(orgwuliu)){
			towhere="wuliu";
		}else if(orgcode.equals(orgjichu)){
			towhere="jichu";
		}
		map.put("towhere",towhere);
		DocApplyWhere staffwhere=new DocApplyWhere();
		staffwhere.DeleteByStaffcode(staffcode);
		staffwhere.setStaffcode(staffcode);
		staffwhere.setApplywhere(towhere);
		staffwhere.insert();
	//////////////////////////////////////////////////////////////////////////////////
	ProcessInstance pi=runtimeService.startProcessInstanceByKey("stdflow",map);
	StaffInfo staffinfo=new StaffInfo(staffcode);
	String staffname=staffinfo.getName();
	Calendar c = Calendar.getInstance();
	String year = "" + c.get(c.YEAR);
	 String month = "" + (c.get(c.MONTH) + 1);
	 String day = "" + c.get(c.DATE);
	 String date=year+"-"+month+"-"+day;
	 String INSTANCEID=pi.getId();
	 InstanceInfo instanceInfo=new InstanceInfo();
	 instanceInfo.setInitdate(date);
	 instanceInfo.setInitstaffcode(staffcode);
	 instanceInfo.setInitstaffname(staffname);
	 instanceInfo.setInstanceid(INSTANCEID);
	 instanceInfo.setPng("stdflow.png");
	 instanceInfo.setInstancename("制修订标准流程");
	 InstanceService instanceService=new InstanceServiceImpl();
	 instanceService.deleteById(INSTANCEID);
	 boolean flag=instanceService.saveInstance(instanceInfo);
	 if(flag){
		 return true;
	 }else{
		 return false;
	 }
}
public DataTable isApplyMan(String staffcode) {
	try {
		DBObject db = new DBObject();
		String urlString="select orgcode from base_orgmember where  positioncode='" + qicao + "' and staffcode='"+staffcode+"'";
		DataTable dt = db.runSelectQuery("select orgcode from base_orgmember where  positioncode='" + qicao + "' and staffcode='"+staffcode+"'");
		return dt;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
public void LoadConfig(String name) 
{
	try
	{
		ClassLoader cl = getClass().getClassLoader();
		java.io.InputStream in;
		if (cl != null)
		{
			in = cl.getResourceAsStream(name);
		}
		else
		{
			in = ClassLoader.getSystemResourceAsStream(name);
		}
		if (in == null)
		{
			// 用文件读写
			in = new java.io.BufferedInputStream(
					new java.io.FileInputStream(name));
		}
			
		try
		{
			m_ftpProperties = new java.util.Properties();
			// 装载配置文件
			m_ftpProperties.load(in);
			// 得到配置内容
			qicao = consume(m_ftpProperties, "qicao");
			shenhe = consume(m_ftpProperties, "shenhe");
			jishushenhe = consume(m_ftpProperties, "jishushenhe");
			fabu = consume(m_ftpProperties, "fabu");
			orgfabu = consume(m_ftpProperties, "orgfabu");
			orgyingxiao = consume(m_ftpProperties, "orgyingxiao");
			orgzhuanmai = consume(m_ftpProperties, "orgzhuanmai");
			organquan = consume(m_ftpProperties, "organquan");
			orgwuliu = consume(m_ftpProperties, "orgwuliu");
			orgjichu = consume(m_ftpProperties, "orgjichu");
			orgweiyuan = consume(m_ftpProperties, "orgweiyuan");
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}
private String consume(java.util.Properties p, String key)
{
	String s = null;
	if ((p != null) && (key != null))
	{
		s = p.getProperty(key);
		// 找到，则从属性表中移去
		if (s != null)
		{
			p.remove(key);
		}
	}
	return s;
}
public DataTable getMemberList(String positioncode,String orgcode) {
	try {
		DBObject db = new DBObject();
		System.out.println("select staffcode from base_orgmember where  positioncode='" + positioncode + "' and orgcode='"+orgcode+"'");
		DataTable dt = db.runSelectQuery("select staffcode from base_orgmember where  positioncode='" + positioncode + "' and orgcode='"+orgcode+"'");
		return dt;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
}
