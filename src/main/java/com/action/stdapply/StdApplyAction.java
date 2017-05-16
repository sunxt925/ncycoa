package com.action.stdapply;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.action.ActionInterface;
import com.db.DBObject;
import com.db.DataTable;
import com.entity.stdapply.DocApplyPerson;
import com.entity.stdapply.DocApplySuggest;
import com.entity.stdapply.DocReviseInifo;

public class StdApplyAction extends ActionInterface{
	private java.util.Properties m_ftpProperties;
	private static String shenhe;
	private static String orgweiyuan;
	@SuppressWarnings("unchecked")
	public String getResult(HttpServletRequest request)
	{
		ServletContext servletContext = request.getSession().getServletContext();  
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        ProcessEngine processEngine = (ProcessEngine) ctx.getBean("processEngine");
		TaskService taskService=processEngine.getTaskService(); 
		String res="";
		String action=request.getParameter("act");
		int applyid=Integer.parseInt(request.getParameter("applyid"));
		String applyidString=String.valueOf(applyid);
		String applyperson=request.getParameter("applyperson");
		String applystaffcode=request.getParameter("applystaffcode");
		String applyapart=request.getParameter("applyapart");
		String applydate=request.getParameter("applydate");
		String applyradio=request.getParameter("radio");
		String applyreason=request.getParameter("applyreason");
		String applyorgcodeString=request.getParameter("orgcode");
		String taskid=request.getParameter("taskId");
		DocApplyPerson docapplyperson=null;
		if (action != null && action.equals("add")) {
			boolean flag = true;

			if (flag) {


					docapplyperson = new DocApplyPerson();
					DataTable dTable = docapplyperson.check(applyid);
					if (dTable.getRowsCount() == 0) {
						docapplyperson.setApplyid(applyid);
						docapplyperson.setApplyapart(applyapart);
						docapplyperson.setApplydate(applydate);
						docapplyperson.setApplyperson(applyperson);
						docapplyperson.setApplyreason(applyreason);
						docapplyperson.setApplystaffcode(applystaffcode);
						docapplyperson.setApplyorgcode(applyorgcodeString);
						docapplyperson.setProcessInstanceId(taskid);
						docapplyperson.setFlag("0");

						if (!docapplyperson.Insert()) {
							flag = false;
						}
					} else {
						int appid = 0;
						try {
							appid = Integer.parseInt(dTable.get(0).get(0)
									.toString());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						docapplyperson.setApplyid(appid);
						docapplyperson.setApplyapart(applyapart);
						docapplyperson.setApplydate(applydate);
						docapplyperson.setApplyperson(applyperson);
						docapplyperson.setApplyreason(applyreason);
						// System.out.println("applyreason::::::"+applyreason);
						docapplyperson.setApplystaffcode(applystaffcode);
						docapplyperson.setApplyorgcode(applyorgcodeString);
						docapplyperson.setFlag("0");
						try {
							docapplyperson.setProcessInstanceId(dTable.get(0)
									.get(1).toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						docapplyperson.Update();
					}
					Map map = new HashMap();
					String newstd=request.getParameter("newstd");
					String modstd=request.getParameter("modstd");
					String delstd=request.getParameter("delstd");
					map.put("newstd",newstd);
					map.put("modstd",modstd);
					map.put("delstd",delstd);
					map.put("applyid",applyidString);
					taskService.complete(taskid,map);

				res += "alert('保存成功');  var api = frameElement.api; W = api.opener; W.reloadTable();api.close();";
			} else {
				res += "alert('保存失败，请检查！'); var api = frameElement.api; W = api.opener; W.reloadTable();api.close();";
			}
		} else if (action != null && action.equals("appro1")) {
			
			String where=(String) taskService.getVariable(taskid, "towhere");
			String result=request.getParameter("result");
			String sugstaffcode=request.getParameter("sugstaffcode");
			String suggestion=request.getParameter("suggest");
			Calendar c = Calendar.getInstance();
	   		 String year = "" + c.get(c.YEAR);
			 String month = "" + (c.get(c.MONTH) + 1);
			 String day = "" + c.get(c.DATE);
			 String date=year+"-"+month+"-"+day;
			 if(suggestion==null&&(sugstaffcode.equals(applystaffcode))){
			 }else{
					DocApplySuggest applysuggest=new DocApplySuggest();
					applysuggest.setApplyid(applyidString);
					applysuggest.setSuggestion(suggestion);
					applysuggest.setSugstaffcode(sugstaffcode);
					applysuggest.setWheresug(where);
					applysuggest.setSugdate(date);
					applysuggest.Insert();  
			 }
			Map map = new HashMap();
			if(result != null && "1".equals(result)){
				map.put("passtomember", false);//批准
				map.put("pass", true);//批准
				map.put("back", false);//驳回
				taskService.setVariables(taskid,map); 
				res += "alert('审核通过并跳过委员会成员'); var api = frameElement.api; W = api.opener; W.reloadTable();api.close();";
			}else if(result != null && "3".equals(result)){
				map.put("passtomember", true);//批准
				map.put("pass", false);//批准
				map.put("back", false);//驳回
				taskService.setVariables(taskid,map); 
				res += "alert('审核通过并转到委员会成员'); var api = frameElement.api;W = api.opener; W.reloadTable();api.close();";
			}else if(result != null && "2".equals(result)){
				map.put("passtomember", false);//批准
				map.put("pass", false);//批准
				map.put("back", true);//驳回
				res += "alert('已驳回！'); var api = frameElement.api;W = api.opener; W.reloadTable();api.close();";
			}
			taskService.complete(taskid,map);//执行  有参

	}else if (action != null && action.equals("appro")) {
				
				String where=(String) taskService.getVariable(taskid, "towhere");
				String result=request.getParameter("result");
				String sugstaffcode=request.getParameter("sugstaffcode");
				String suggestion=request.getParameter("suggest");
				LoadConfig("/com/workflow/serviceimpl/position.properties");
				DataTable dt0=isWeiYuan(sugstaffcode);
				if(dt0.getRowsCount()>0){
					where="weiyuan";
				}
				Calendar c = Calendar.getInstance();
		   		 String year = "" + c.get(c.YEAR);
				 String month = "" + (c.get(c.MONTH) + 1);
				 String day = "" + c.get(c.DATE);
				 String date=year+"-"+month+"-"+day;
				 if(suggestion==null&&(sugstaffcode.equals(applystaffcode))){
				 }else{
						DocApplySuggest applysuggest=new DocApplySuggest();
						applysuggest.setApplyid(applyidString);
						applysuggest.setSuggestion(suggestion);
						applysuggest.setSugstaffcode(sugstaffcode);
						applysuggest.setWheresug(where);
						applysuggest.setSugdate(date);
						applysuggest.Insert();  
				 }
				Map map = new HashMap();
				if(result != null && "1".equals(result)){
					map.put("go", true);//批准
					map.put("back", false);//驳回
					taskService.setVariables(taskid,map); 
					res += "alert('审核通过'); var api = frameElement.api;W = api.opener; W.reloadTable();api.close();";
				}else if(result != null && "2".equals(result)){
					map.put("go", false);//批准
					map.put("back", true);//驳回
					res += "alert('已驳回！'); var api = frameElement.api; W = api.opener; W.reloadTable();api.close();";
				}
				taskService.complete(taskid,map);//执行  有参

		}else if(action!=null && action.equals("public")){
			
			DocReviseInifo docRevise=new DocReviseInifo();
			try {
				res=docRevise.StdPublic(applyidString, applystaffcode);
				DocApplyPerson applyPerson=new DocApplyPerson();
				applyPerson.UpdateFlag(applyid);
				Map map = new HashMap();
				map.put("public", true);
				taskService.complete(taskid,map);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	

		}
		return res;
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
				shenhe = consume(m_ftpProperties, "shenhe");
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
	public DataTable isWeiYuan(String staffcode) {
		try {
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_orgmember where  positioncode='" + shenhe + "' and staffcode='"+staffcode+"' and orgcode='"+orgweiyuan+"'");
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
