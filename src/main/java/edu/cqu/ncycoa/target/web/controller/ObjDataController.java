package edu.cqu.ncycoa.target.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.domain.TargetResult;
import edu.cqu.ncycoa.target.service.ResultService;
import edu.cqu.ncycoa.target.service.TargetService;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/objresult")
public class ObjDataController {
	@Resource(name="systemService")
	SystemService systemService;
	
	@RequestMapping(params="view_c") //公司页面
	public ModelAndView indexrootList_c(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
//		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetdatamanage/companytargetdata");
		mav.addObject("class","C");
		return mav;
	}
	
	@RequestMapping(params="view_d") //部门页面
	public ModelAndView indexrootList_d(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
//		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetdatamanage/departmenttargetdata");
		mav.addObject("class","D");
		return mav;
	}
	
	@RequestMapping(params="view_s") //公司页面
	public ModelAndView indexrootList_s(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
//		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.addObject("class","S");
		mav.setViewName("targetdatamanage/personaltargetdata");
		return mav;
	}
	
	//点击选择指标体系后
	@RequestMapping(params="getArch")
	public ModelAndView getArchList(HttpServletRequest request, HttpServletResponse response){
		String classT=request.getParameter("class");
		
		String jpql="FROM ObjIndexItem as o where o.IsParent='1' and o.IndexCode LIKE '"+classT+"%' and o.IsLast='1'";
		List<ObjIndexItem> items=systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("archList", items);
		mav.setViewName("targetdatamanage/selectarch");
		return mav;
	}
	//选择年份，季度，指标体系之后：
	@RequestMapping(params="getTable")
	public ModelAndView getTableList(HttpServletRequest request, HttpServletResponse response){
		String year=request.getParameter("year");
		String season=request.getParameter("season");
		String archcode=request.getParameter("archcode");
		
		String jpql="FROM ObjIndexItem as o where o.IsParent='0' and o.IndexCode LIKE '"+archcode+"%' and o.IsLast='1'";
		List<ObjIndexItem> items=systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		
		//String jpql2="FROM ObjIndexItem as o where o.IsParent='0' and o.IndexCode LIKE '"+archcode+"%' and o.IsLast='1'";
		List<String> objcodes=ResultService.getObjCodesByArch(archcode);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("indexList", items);
		mav.addObject("objcodes",objcodes);
		mav.setViewName("targetdatamanage/companytargetdata");
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save_item(TargetResult item, HttpServletRequest request, HttpServletResponse response) {
	//	String classT=request.getParameter("class");
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (item.getID() != null) {
			message = "更新成功";
			TargetResult t = systemService.findEntityById(item.getID(), TargetResult.class);
			if(t==null){
				message = "添加成功";
				systemService.addEntity(item);
			}else{
				try {
					MyBeanUtils.copyBeanNotNull2Bean(item, t);
					systemService.saveEntity(t);
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

				} catch (Exception e) {
					message = "更新失败";
				}
			}
		} else {
			message = "添加成功";
			systemService.addEntity(item);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
}
