package edu.cqu.ncycoa.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.CodeDictionary;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SupplierServiceImpl;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TQRestriction;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.dao.SupplierDao;
import edu.cqu.ncycoa.domain.Notice;
import edu.cqu.ncycoa.domain.RandomRecord;
import edu.cqu.ncycoa.safety.domain.CheckPlan;
import edu.cqu.ncycoa.safety.domain.SafetyEdu;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/random")
public class RandomSupplierController {
	@Resource(name="systemService")
	SystemService systemService;
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "supplier/addrandom";
	}
	
	@RequestMapping(params="del")
	@ResponseBody
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
			message = "数据不合法";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		systemService.removeEntities(ids, RandomRecord.class);
		message = "删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="seeContent")
    public ModelAndView seeContent(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		Notice notice = systemService.findEntityById(Long.parseLong(id), Notice.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/seenotice");
		mav.addObject("notice",notice);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(RandomRecord notice, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (notice.getID() != null) {
			message = "更新成功";
			RandomRecord t = systemService.findEntityById(notice.getID(), RandomRecord.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(notice, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "添加成功";
			
			//notice.setRandomTime(new Date());
			
			String[] departcodes = request.getParameter("departsList").replace("[","").replace("]", "").split(",");
			List<String> departslist= new ArrayList<String>();
			for(String s : departcodes){
				departslist.add(s.trim());
			}
			notice.setDepartsList(departslist);
			System.out.println(request.getParameter("departsList"));
			
			systemService.addEntity(notice);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview_random")
	public ModelAndView dgViewA(HttpServletRequest request) {	
		ModelAndView mav=new ModelAndView("supplier/random_add");
		//int year=Calendar.getInstance().get(Calendar.YEAR);	
		String content=SupplierDao.getRandomContent();
		mav.addObject("randomPanel", content);
		//mav.addObject("count", SupplierServiceImpl.getContentCount());
		//mav.addObject("supplierID", supplierID);
		return mav;
	}
	@RequestMapping(params="dgview_list")
	public String dgViewS(HttpServletRequest request) {	
		return "supplier/randomlist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgDataA(RandomRecord notice, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<RandomRecord> cq = new QueryDescriptor<RandomRecord>(RandomRecord.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<RandomRecord> tqBuilder = QueryUtils.getTQBuilder(notice, request.getParameterMap());
		//tqBuilder.addRestriction(new TQRestriction( "depart", "in", Arrays.asList(SupplierDao.getOneDepartCode())));

		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	
	@RequestMapping(params="queryAttender")
	public ModelAndView queryAttender(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mov =new ModelAndView();
		String id = request.getParameter("id");
		RandomRecord rr = systemService.findEntityById(Long.parseLong(id), RandomRecord.class);
		Map<String,String> map = new HashMap<String, String>();
		for(String s : rr.getDepartsList()){
			map.put(s, s);
		}
		System.out.println(map.size());
		mov.setViewName("supplier/memberlist");
		mov.addObject("maps", map);
		return mov;
	}
	
	@RequestMapping(params="getdeparts")
	@ResponseBody
	public void judge(HttpServletRequest request, HttpServletResponse response) {
		String code=request.getParameter("code");
		System.out.println("code:"+code);
		String message = "";
		AjaxResultJson j = new AjaxResultJson();
		//这里判断
		message=SupplierDao.getRandomContentByCode(code);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
}
