package edu.cqu.ncycoa.target.web.controller;

import java.util.Arrays;
import java.util.List;

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
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TQRestriction;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.dao.SupplierDao;
import edu.cqu.ncycoa.domain.Notice;
import edu.cqu.ncycoa.safety.domain.CheckPlan;
import edu.cqu.ncycoa.target.domain.ObjIndexArchUser;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.service.TargetService;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/objindexarchuser")
public class ObjIndexArchUserController {
	@Resource(name="systemService")
	SystemService systemService;
	
	@RequestMapping(params="indexarchuserlist_c")
	public ModelAndView indexarchuserlist_c(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexarchuserlist");		
		request.setAttribute("classT", "C");
		mav.addObject("classT","C");
		return mav;
	}
	@RequestMapping(params="indexarchuserlist_d")
	public ModelAndView indexarchuserlist_d(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexarchuserlist");		
		request.setAttribute("classT", "D");
		mav.addObject("classT","D");
		return mav;
	}
	@RequestMapping(params="indexarchuserlist_s")
	public ModelAndView indexarchuserlist_s(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexarchuserlist");		
		request.setAttribute("classT", "S");
		mav.addObject("classT","S");
		return mav;
	}
	@RequestMapping(params="itemlist")//关联对象表
	public ModelAndView itemlist_c(HttpServletRequest request, HttpServletResponse response){
		String classT = request.getParameter("indexclass"); 
		String archcode=request.getParameter("indexarchcode"); 
		List<ObjIndexArchUser> items = systemService.findEntitiesByProperty("IndexArchCode", archcode, ObjIndexArchUser.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexdefuserlist");
		mav.addObject("items",items);
		request.setAttribute("classT", classT);
		mav.addObject("classT",classT);
		mav.addObject("archcode",archcode);
		return mav;
	}
	
	@RequestMapping(params="add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		String classT = request.getParameter("class"); 
		String archcode=request.getParameter("archcode");
		String type="";
		if(classT.equals("C")){
			type="公司";
		}else if(classT.equals("D")){
			type="部门";
		}else {
			type="员工";
		}
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/addarchuser");
		//mav.addObject("items",items);
		request.setAttribute("indexclass", classT);
		mav.addObject("classT",classT);
		mav.addObject("objecttype",type);
		mav.addObject("archcode",archcode);
		return mav;
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
		
		systemService.removeEntities(ids, ObjIndexArchUser.class);
		message = "删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		ObjIndexArchUser notice = systemService.findEntityById(Long.parseLong(id), ObjIndexArchUser.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/addarchuser");
		mav.addObject("item",notice);
		mav.addObject("archcode",notice.getIndexArchCode());
		mav.addObject("objecttype",notice.getObjecttype());
		mav.addObject("objectname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", notice.getObjectcode()));
		return mav;
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
	public void save(ObjIndexArchUser notice, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("here");
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (notice.getId() != null) {
			message = "更新成功";
			ObjIndexArchUser t = systemService.findEntityById(notice.getId(), ObjIndexArchUser.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(notice, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "添加成功";
			systemService.addEntity(notice);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview_add")
	public String dgViewA(HttpServletRequest request) {	
		return "notice/mynoticelist";
	}
	@RequestMapping(params="dgview_see")
	public String dgViewS(HttpServletRequest request) {	
		return "notice/allnoticelist";
	}
	@RequestMapping(params="dgview_del")
	public String dgViewD(HttpServletRequest request) {	
		return "notice/adminnoticelist";
	}
	
	
	
	
	
}
