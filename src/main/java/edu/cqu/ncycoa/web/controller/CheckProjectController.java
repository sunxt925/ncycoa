package edu.cqu.ncycoa.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.db.DBObject;
import com.db.DataTable;
import com.entity.system.StaffInfo;
import com.entity.system.UserInfo;
import com.workflow.orm.InstanceInfo;
import com.workflow.service.InstanceService;
import com.workflow.serviceimpl.InstanceServiceImpl;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.CheckProject;
import edu.cqu.ncycoa.domain.CheckReport;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/checkproject")
public class CheckProjectController{
	
	@Resource(name="systemService")
	SystemService systemService;
	@Resource(name="processEngine")
	ProcessEngine processEngine;
	
	private int approvenum=0;//管委会审核的人数
	
	private java.util.Properties m_ftpProperties;
	private static String startposition;
	private static String checkposition;
	private static String startorg;
	private static String checkorg;
	private static String banzhurenpos;
	private static String banorg;
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		return "std_check/checkproject/projectlist";
	}
	
	@RequestMapping(params="dgreportview")
	public String dgReportView(HttpServletRequest request) {
		return "std_check/checkproject/reportlist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	public void dgData(CheckProject checkproject, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<CheckProject> cq = new QueryDescriptor<CheckProject>(CheckProject.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<CheckProject> tqBuilder = QueryUtils.getTQBuilder(checkproject, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgreportdata")
	public void dgReportData(CheckReport checkreport, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<CheckReport> cq = new QueryDescriptor<CheckReport>(CheckReport.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<CheckReport> tqBuilder = QueryUtils.getTQBuilder(checkreport, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="del")
	public void del(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "data unlawful!";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		for(int i=0;i<ids.length;i++){
			CheckProject checkproject=systemService.findEntityById(ids[i], CheckProject.class);
			String filepath=checkproject.getFilePath();
			if (filepath != null && !(filepath.equals(""))) {
				String[] filepaths = filepath.split(";");
				for (int m = 0; m < filepaths.length; m++) {
					File deletefile = new File((request.getSession()
							.getServletContext().getRealPath("doc")
							+ "/checkproject/" + filepaths[m]));
					deletefile.delete();
				}
			}
		}
		systemService.removeEntities(ids, CheckProject.class);
		message = "delete success!";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="delreport")
	public void delreport(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "data unlawful!";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		for(int i=0;i<ids.length;i++){
			CheckReport checkreport=systemService.findEntityById(ids[i], CheckReport.class);
			String reportpath=checkreport.getReportPath();
			if (reportpath != null && !(reportpath.equals(""))) {
				String[] reportpaths = reportpath.split(";");
				for (int m = 0; m < reportpaths.length; m++) {
					File deletefile = new File((request.getSession()
							.getServletContext().getRealPath("doc")
							+ "/checkproject/" + reportpaths[m]));
					deletefile.delete();
				}
			}
		}
		systemService.removeEntities(ids, CheckReport.class);
		message = "delete success!";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="openoffice")
	public String openoffice(HttpServletRequest request)  {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return null;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			return null;
		}
		CheckProject checkproject=systemService.findEntityById(ids[0], CheckProject.class);
		String filename=checkproject.getFilePath();
		String[] filenames=filename.split(";");
		String filepath="\\checkproject\\"+filenames[0];
		request.setAttribute("filepath", filepath);
		return "std_check/std_officeopen";
	}
	
	@RequestMapping(params="openoffice2")
	public String openoffice2(HttpServletRequest request)  {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return null;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			return null;
		}
		CheckReport checkreport=systemService.findEntityById(ids[0], CheckReport.class);
		String filename=checkreport.getReportPath();
		String[] filenames=filename.split(";");
		String filepath="\\checkproject\\"+filenames[0];
		request.setAttribute("filepath", filepath);
		return "std_check/std_officeopen";
	}
	
	@RequestMapping(params="upfile")
	public void upfile(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		String staffCode=multipartRequest.getParameter("staffCode");
		
		String taskid =multipartRequest.getParameter("taskid");
		String checkCode =new String(multipartRequest.getParameter("checkCode").getBytes("ISO-8859-1"),"UTF-8");
		String staffName=new String(multipartRequest.getParameter("staffName").getBytes("ISO-8859-1"),"UTF-8");
		String checkName=(new String(multipartRequest.getParameter("checkName").getBytes("ISO-8859-1"),"UTF-8"));
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		String startTime=multipartRequest.getParameter("startTime");
		Date date = fmt.parse(startTime);
		String status="0";
		AjaxResultJson j = new AjaxResultJson();

		//
		//filename
		Date date1 = new Date();     
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");    
		 String str1 = sdf1.format(date1);  
		//
		
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取多个file
		int i=100;
		String filepath="";
		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = (String) it.next();
			MultipartFile imgFile = multipartRequest.getFile(key);
			if (imgFile.getOriginalFilename().length() > 0) {
				String originalName = imgFile.getOriginalFilename();
				String fileName = str1+i+originalName.substring(originalName.lastIndexOf("."),originalName.length());
				filepath=filepath+fileName+";";
				i++;
				// 改成自己的对象哦！
				// Constant.UPLOAD_GOODIMG_URL 是一个配置文件路径
				try {
					String uploadFileUrl = multipartRequest.getSession()
							.getServletContext()
							.getRealPath("doc/checkproject");
					File _apkFile = saveFileFromInputStream(
							imgFile.getInputStream(), uploadFileUrl, fileName);
					if (_apkFile.exists()) {
						FileInputStream fis = new FileInputStream(_apkFile);
					} else
						throw new FileNotFoundException("apk write failed:"
								+ fileName);
					result.put("success", true);
				} catch (Exception e) {
					result.put("success", false);
					e.printStackTrace();
				}
			}
		}
		TaskService taskService = processEngine
				.getTaskService();
		Map map = new HashMap();
		map.put("checkCode", checkCode);
		map.put("checkName", checkName);
		map.put("filepath", filepath);
		map.put("staffCode", staffCode);
		map.put("staffName", staffName);
		map.put("startdate", startTime);
		map.put("status", status);
		taskService.complete(taskid,map);//执行  有参
		String message;
		message = "提交成功";
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}

	
	@RequestMapping(params="upreport")
	public void upreport(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		TaskService taskService = processEngine
				.getTaskService();
		String taskid =multipartRequest.getParameter("taskid");
		AjaxResultJson j = new AjaxResultJson();

		//
		//filename
		Date date1 = new Date();     
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");    
		 String str1 = sdf1.format(date1);  
		//
		
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取多个file
		int i=100;
		String reportpath="";
		Object reportpathO=taskService.getVariable(taskid, "reportpath");
		if(reportpathO!=null)reportpath=reportpathO.toString();
		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = (String) it.next();
			MultipartFile imgFile = multipartRequest.getFile(key);
			if (imgFile.getOriginalFilename().length() > 0) {
				String originalName = imgFile.getOriginalFilename();
				String fileName = str1+i+originalName.substring(originalName.lastIndexOf("."),originalName.length());
				reportpath=reportpath+fileName+";";
				i++;
				// 改成自己的对象哦！
				// Constant.UPLOAD_GOODIMG_URL 是一个配置文件路径
				try {
					String uploadFileUrl = multipartRequest.getSession()
							.getServletContext()
							.getRealPath("doc/checkproject");
					File _apkFile = saveFileFromInputStream(
							imgFile.getInputStream(), uploadFileUrl, fileName);
					if (_apkFile.exists()) {
						FileInputStream fis = new FileInputStream(_apkFile);
					} else
						throw new FileNotFoundException("apk write failed:"
								+ fileName);
					result.put("success", true);
				} catch (Exception e) {
					result.put("success", false);
					e.printStackTrace();
				}
			}
		}
		Map map = new HashMap();
		map.put("reportpath", reportpath);
		taskService.complete(taskid,map);//执行  有参
		String message;
		message = "提交成功";
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="upallreport")
	public void upallreport(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		TaskService taskService = processEngine
				.getTaskService();
		String taskid =multipartRequest.getParameter("taskid");
		AjaxResultJson j = new AjaxResultJson();

		//
		//filename
		Date date1 = new Date();     
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");    
		 String str1 = sdf1.format(date1);  
		//
		
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取多个file
		int i=100;
		String reportpath="";
		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = (String) it.next();
			MultipartFile imgFile = multipartRequest.getFile(key);
			if (imgFile.getOriginalFilename().length() > 0) {
				String originalName = imgFile.getOriginalFilename();
				String fileName = str1+i+originalName.substring(originalName.lastIndexOf("."),originalName.length());
				reportpath=reportpath+fileName+";";
				i++;
				// 改成自己的对象哦！
				// Constant.UPLOAD_GOODIMG_URL 是一个配置文件路径
				try {
					String uploadFileUrl = multipartRequest.getSession()
							.getServletContext()
							.getRealPath("doc/checkproject");
					File _apkFile = saveFileFromInputStream(
							imgFile.getInputStream(), uploadFileUrl, fileName);
					if (_apkFile.exists()) {
						FileInputStream fis = new FileInputStream(_apkFile);
					} else
						throw new FileNotFoundException("apk write failed:"
								+ fileName);
					result.put("success", true);
				} catch (Exception e) {
					result.put("success", false);
					e.printStackTrace();
				}
			}
		}
		Map map = new HashMap();
		map.put("allreportpath", reportpath);
		taskService.complete(taskid,map);//执行  有参
		String message;
		message = "提交成功";
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	// 保存文件
	private File saveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		File file = new File(path + "/" + filename);
		FileOutputStream fs = new FileOutputStream(file);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
		return file;
	}

	
	@RequestMapping(params="approve")
	public String approve(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
		String result=request.getParameter("result");
		String taskid=request.getParameter("taskid");
		String suggest=new String(request.getParameter("suggest").getBytes("ISO-8859-1"),"UTF-8");
		Map map = new HashMap();
		TaskService taskService = processEngine
				.getTaskService();
		if(result!=null&&result.equals("2")){
			
			map.put("towhere", "back");//批准
			map.put("suggest",suggest);
			Object filepath = taskService.getVariable(taskid, "filepath");
			if (filepath != null){
				String[] filenames=filepath.toString().split(";");
				for (int m = 0; m < filenames.length; m++) {
				File deletefile = new File((request.getSession()
						.getServletContext().getRealPath("doc")
						+ "/checkproject/" + filenames[m]));
				deletefile.delete();
				}
				
			}
		}
		if(result != null && "1".equals(result)){
			map.put("suggest",suggest);
			if(approvenum==1){
			CheckProject checkproject = new CheckProject();
			Object checkCode = taskService.getVariable(taskid, "checkCode");
			if (checkCode != null)
				checkproject.setCheckCode(checkCode.toString());
			Object checkName = taskService.getVariable(taskid, "checkName");
			if (checkName != null)
				checkproject.setCheckName(checkName.toString());
			Object filepath = taskService.getVariable(taskid, "filepath");
			if (filepath != null)
				checkproject.setFilePath(filepath.toString());
			Object staffCode = taskService.getVariable(taskid, "staffCode");
			if (staffCode != null)
				checkproject.setStaffCode(staffCode.toString());
			Object staffName = taskService.getVariable(taskid, "staffName");
			if (staffName != null)
				checkproject.setStaffName(staffName.toString());
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Object startdate = taskService.getVariable(taskid, "startdate");
			if (startdate != null) {
				Date date = fmt.parse(startdate.toString());
				checkproject.setStartTime(date);
			}
			checkproject.setStatus("1");
			systemService.addEntity(checkproject);
			}
			approvenum=approvenum-1;
		}
		taskService.complete(taskid,map);//执行  有参
		return "std_check/havedo";
	}
	
	@RequestMapping(params="publicreport")
	public String publicreport(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
		String result=request.getParameter("result");
		String taskid=request.getParameter("taskid");
		String aboutcommit=new String(request.getParameter("CommitteeName").getBytes("ISO-8859-1"),"UTF-8");
		Map map = new HashMap();
		TaskService taskService = processEngine
				.getTaskService();

		if(result != null && "1".equals(result)){
			CheckReport checkreport = new CheckReport();
			Object allreportpath = taskService.getVariable(taskid, "allreportpath");
			if (allreportpath != null)
				checkreport.setReportPath(allreportpath.toString());
			Object checkName = taskService.getVariable(taskid, "checkName");
			if (aboutcommit != null&&!(aboutcommit.equals("")))
				checkreport.setAboutCommit(aboutcommit);
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String endtime = request.getParameter("endTime");
			if (endtime != null) {
				Date date = fmt.parse(endtime);
				checkreport.setEndTime(date);
			}
			systemService.addEntity(checkreport);
			}
		Object reportpathobject = taskService.getVariable(taskid, "reportpath");
		if(reportpathobject!=null){
			String report=reportpathobject.toString();
			if(!(report.equals(""))){
				String[] reports=report.split(";");
				for(int m=0;m<reports.length;m++){
					File deleteroport=new File((request.getSession()
							.getServletContext().getRealPath("doc")
							+ "/checkproject/" + reports[m]));
					deleteroport.delete();
				}
			}
		}
		taskService.complete(taskid,map);//执行  有参
		return "std_check/havedo";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="start")
	public String start(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoadConfig("startcheckposition.properties");
		UserInfo user=SystemUtils.getSessionUser();
		boolean flag=false;
		String[] startorgs=startorg.split(",");
		String[] startpositions=startposition.split(",");
		for (int i = 0; i < startorgs.length; i++) {
			for (int m = 0; m < startpositions.length; m++) {
				DataTable ApproveOrg = isApplyMan(startorgs[i],
						startpositions[m], user.getStaffcode());
				if (ApproveOrg.getRowsCount() != 0) {
					flag = true;
					break;
				}
			}
			if(flag==true)
				break;
		}
		if(!flag)
			return "std_check/sorry";
		RuntimeService runtimeService = processEngine
				.getRuntimeService();
			Map<String,Object> map = new HashMap<String,Object>();
		map.put("owner",user.getStaffcode());
		List<String> checkprojectgroup=new ArrayList<String>();
		String[] checkpos=checkposition.split(",");
		String[] checkorgs=checkorg.split(",");
		for(int j=0;j<checkpos.length;j++){
			for(int n=0;n<checkorgs.length;n++){
				DataTable checkpersonDT=getMemberList(checkpos[j],checkorgs[n]);
				for(int i=0;i<checkpersonDT.getRowsCount();i++){
					checkprojectgroup.add(checkpersonDT.get(i).getString("staffcode"));
				}
			}
		}
		approvenum=checkprojectgroup.size();
		map.put("checkprojectgroup",checkprojectgroup);//管委会主任，其实是一个人，这里是按多个人写的，兼容一个人。同时为评审发起和评审过程中的管委会那两步分配人员
		DataTable banzhuren=getMemberList(banzhurenpos,banorg);//查到办公室主任
		map.put("banleader",banzhuren.get(0).getString("staffcode"));//为办公室主任上传全市报告结点分人员
		if((user.getStaffcode()).equals(banzhuren.get(0).getString("staffcode"))){
			map.put("towhere","pass");//是办公室主任发起的，就不需要相应分技术委员会初评
		}else{
			map.put("towhere","jishu");//技术委员会主任发起的，需要初评
			map.put("jishuleader",user.getStaffcode());//技术委员会主任发起的评审，评审过程还由他上传初评报告
		}
		ProcessInstance pi=runtimeService.startProcessInstanceByKey("checkproject",map);
		StaffInfo staffinfo=new StaffInfo(user.getStaffcode());
		String staffname=staffinfo.getName();
		Calendar c = Calendar.getInstance();
		String year = "" + c.get(c.YEAR);
		 String month = "" + (c.get(c.MONTH) + 1);
		 String day = "" + c.get(c.DATE);
		 String date=year+"-"+month+"-"+day;
		 String INSTANCEID=pi.getId();
		 InstanceInfo instanceInfo=new InstanceInfo();
		 instanceInfo.setInitdate(date);
		 instanceInfo.setInitstaffcode(user.getStaffcode());
		 instanceInfo.setInitstaffname(staffname);
		 instanceInfo.setInstanceid(INSTANCEID);
		 instanceInfo.setPng("stdflow.png");
		 instanceInfo.setInstancename("制修订标准流程");
		 InstanceService instanceService=new InstanceServiceImpl();
		 instanceService.deleteById(INSTANCEID);
		 boolean f=instanceService.saveInstance(instanceInfo);
		 if(f){
			 TaskService taskService = processEngine
						.getTaskService();
			 List<Task> groupTaskList=taskService.createTaskQuery().taskAssignee(user.getStaffcode()).orderByTaskCreateTime().desc().list();
				Task task=groupTaskList.get(0);
			 request.setAttribute("id", task.getId());
			 return "std_check/checkproject/startcheck";
		 }
		return null;
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
				startposition = consume(m_ftpProperties, "startposition");
				checkposition = consume(m_ftpProperties, "checkposition");
				startorg = consume(m_ftpProperties, "startorg");
				checkorg = consume(m_ftpProperties, "checkorg");
				banzhurenpos = consume(m_ftpProperties, "banzhurenpos");
				banorg = consume(m_ftpProperties, "banorg");
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
	public DataTable isApplyMan(String startorgcode,String startpositoncode,String staffcode) {
		try {
			DBObject db = new DBObject();
			String urlString="select orgcode from base_orgmember where  positioncode='" + startposition + "' and staffcode='"+staffcode+"'";
			DataTable dt = db.runSelectQuery("select orgcode from base_orgmember where orgcode='"+startorgcode+"' and  positioncode='" + startpositoncode + "' and staffcode='"+staffcode+"'");
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
