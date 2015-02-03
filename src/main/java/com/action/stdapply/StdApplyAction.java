package com.action.stdapply;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import com.action.ActionInterface;
import com.db.DBObject;
import com.db.DataTable;
import com.entity.stdapply.DocApplyPerson;
import com.entity.stdapply.DocApplySuggest;
import com.entity.stdapply.DocReviseInifo;
import com.workflow.std.jbpm.Sign;

public class StdApplyAction extends ActionInterface{
	private java.util.Properties m_ftpProperties;
	private static String shenhe;
	private static String orgweiyuan;
	@SuppressWarnings("unchecked")
	public String getResult(HttpServletRequest request)
	{
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
		String taskid=request.getParameter("taskid");
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
					ProcessEngine pe = Configuration.getProcessEngine();
					TaskService ts = pe.getTaskService();
					Map map = new HashMap();
					map.put("applyid",applyidString);
					ts.setVariables(taskid,map);
					ts.completeTask(taskid);

				res += "MessageBox.Show(null,'保存成功！',null,'LogOK',null,1,'保存成功');";
			} else {
				res += "MessageBox.Show(null,'保存失败！',null,'LogOK','Error',1,'保存失败，请检查！');";
			}
		} else if (action != null && action.equals("appro")) {
				String where=request.getParameter("towhere");
				String result=request.getParameter("result");
				String sugstaffcode=request.getParameter("sugstaffcode");
				String suggestion=request.getParameter("suggest");
				LoadConfig("/com/workflow/std/jbpm/position.properties");
				DataTable dt0=isWeiYuan(sugstaffcode);
				if(dt0.getRowsCount()>0){
					where="weiyuan";
				}
				Calendar c = Calendar.getInstance();
		   		 String year = "" + c.get(c.YEAR);
				 String month = "" + (c.get(c.MONTH) + 1);
				 String day = "" + c.get(c.DATE);
				 String date=year+"-"+month+"-"+day;
				DocApplySuggest applysuggest=new DocApplySuggest();
				applysuggest.setApplyid(applyidString);
				applysuggest.setSuggestion(suggestion);
				applysuggest.setSugstaffcode(sugstaffcode);
				applysuggest.setWheresug(where);
				applysuggest.setSugdate(date);
				applysuggest.Insert();
				ProcessEngine processEngine=Configuration.getProcessEngine();  
				TaskService taskService=processEngine.getTaskService(); 
				Task joinTask = taskService.getTask(taskid);
				String parentid=joinTask.getName(); 
				Map map = new HashMap();
				if(result != null && "审核通过".equals(result)){
					map.put("msg", taskService.getVariable(taskid, "msg")+"<hr>驳回");
					taskService.setVariables(taskid,map);
					

					Sign sign=new Sign(parentid,joinTask,"同意");  
					processEngine.execute(sign);  
					res += "MessageBox.Show(null,'审核通过！',null,'LogOK',null,1,'审核通过');";
				}else{
					map.put("msg", taskService.getVariable(taskid, "msg")+"<hr>审批通过");
					taskService.setVariables(taskid,map);
					

					Sign sign=new Sign(parentid,joinTask,"不同意");  
					processEngine.execute(sign); 
					res += "MessageBox.Show(null,'已驳回！',null,'LogOK','Error',1,'已驳回！');";
				}

		}else if(action!=null && action.equals("public")){
			
			DocReviseInifo docRevise=new DocReviseInifo();
			try {
				res=docRevise.StdPublic(applyidString, applystaffcode);
				DocApplyPerson applyPerson=new DocApplyPerson();
				applyPerson.UpdateFlag(applyid);
				ProcessEngine pe = Configuration.getProcessEngine();
				TaskService ts = pe.getTaskService();
				Map map = new HashMap();
				map.put("msg", ts.getVariable(taskid, "msg")+"<hr>发布");
				ts.setVariables(taskid,map);
				ts.completeTask(taskid);
				res += "MessageBox.Show(null,'发布成功！',null,'LogOK',null,1,'发布成功');";
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
