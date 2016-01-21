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
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/objindexitem")
public class ObjIndexItemController {
	@Resource(name="systemService")
	SystemService systemService;
	
	//indexrootlist_c
	@RequestMapping(params="indexrootlist_c")
	public ModelAndView indexrootList_c(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		return mav;
	}
	
	@RequestMapping(params="indexrootlist_d")
	public ModelAndView indexrootList_d(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		return mav;
	}
	
	@RequestMapping(params="indexrootlist_s")
	public ModelAndView indexrootList_s(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		return mav;
	}
	@RequestMapping(params="add_arch")
	public String add(HttpServletRequest request) {
		return "targetmanage/indexarchadd";
	}
	//删除单个体系
	@RequestMapping(params="del_arch")
	@ResponseBody
	public void del_arch(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return;
		}
		systemService.removeEntityById(id, ObjIndexItem.class);
		message = "删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
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
		
		systemService.removeEntities(ids, Notice.class);
		message = "删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update_arch")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		ObjIndexItem item = systemService.findEntityById(id, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexarchadd");
		mav.addObject("item",item);
		//mav.addObject("orgname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", notice.getDepart()));
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
	
	@RequestMapping(params = "save_arch")
	@ResponseBody
	public void save(ObjIndexItem item, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (item.getIndexCode() != null) {
			message = "更新成功";
			ObjIndexItem t = systemService.findEntityById(item.getIndexCode(), ObjIndexItem.class);
			if(t==null){
				message = "添加成功";
				item.setEnabled(1);
				item.setIsParent("1");
				item.setParentIndexCode("-1");
				item.setVersion("V01");
				item.setScoreDefault(0);
				item.setStandardscore(0);
				systemService.addEntity(item);
			}else{
			try {
				item.setEnabled(1);
				item.setIsParent("1");
				item.setParentIndexCode("-1");
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			}
			} else {
			message = "添加成功";
			item.setEnabled(1);
			item.setIsParent("1");
			item.setParentIndexCode("-1");
			item.setScoreDefault(0);
			item.setStandardscore(0);
			systemService.addEntity(item);
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
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_add")
	@ResponseBody
	public void dgDataA(Notice notice, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Notice> cq = new QueryDescriptor<Notice>(Notice.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<Notice> tqBuilder = QueryUtils.getTQBuilder(notice, request.getParameterMap());
		tqBuilder.addRestriction(new TQRestriction( "depart", "in", Arrays.asList(SupplierDao.getOneDepartCode())));

		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_all")
	@ResponseBody
	public void dgDataS(Notice notice, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Notice> cq = new QueryDescriptor<Notice>(Notice.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<Notice> tqBuilder = QueryUtils.getTQBuilder(notice, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
