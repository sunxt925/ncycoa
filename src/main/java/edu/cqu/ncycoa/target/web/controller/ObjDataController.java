package edu.cqu.ncycoa.target.web.controller;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONArray;

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
import edu.cqu.ncycoa.domain.Supplier;
import edu.cqu.ncycoa.target.domain.ObjIndexArchUser;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.domain.ResultModel;
import edu.cqu.ncycoa.target.domain.TargetResult;
import edu.cqu.ncycoa.target.domain.TotalScore;
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
	
	@RequestMapping(params="view_s") //公司页面 result_viewc
	public ModelAndView indexrootList_s(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
//		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.addObject("class","S");
		mav.setViewName("targetdatamanage/personaltargetdata");
		return mav;
	}
	
	@RequestMapping(params="result_viewc") //公司结果
	public ModelAndView result_viewc(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM TargetResult as o where o.IndexArchCode like '"+archcode.substring(0, 7)+"'";
//		List<ObjIndexArchUser> results=systemService.readEntitiesByJPQL(jpql, TargetResult.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		//mav.addObject("class","S");
		mav.setViewName("targetdatamanage/dataview_c");
		return mav;
	}
	
	@RequestMapping(params="total_viewc") //公司总分
	public ModelAndView total_viewc(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM TargetResult as o where o.IndexArchCode like '"+archcode.substring(0, 7)+"'";
//		List<ObjIndexArchUser> results=systemService.readEntitiesByJPQL(jpql, TargetResult.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		//mav.addObject("class","S");
		mav.setViewName("targetdatamanage/totalview_c");
		return mav;
	}
	
	@RequestMapping(params="tshow_c") //公司同比展示
	public ModelAndView tshow_c(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		//mav.addObject("class","S");
		mav.setViewName("targetdatamanage/tshow_c");
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
	public ModelAndView getTableList(String archcode,HttpServletRequest request, HttpServletResponse response){
//		String year=request.getParameter("year");
//		String season=request.getParameter("season");
//		String archcode=request.getParameter("archcode");
		System.out.println(archcode);
		String jpql="FROM ObjIndexItem as o where o.IsParent='0' and o.IndexCode LIKE '"+archcode.substring(0, 7)+"%' and o.IsLast='1'";
		List<ObjIndexItem> items=systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		
		String jpql2="FROM ObjIndexArchUser as o where o.IndexArchCode='"+archcode.substring(0, 7)+"'";
		List<ObjIndexArchUser> objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
		
		
		//List<String> objcodes=ResultService.getObjCodesByArch(archcode);
		//System.out.println(objcodes);
		ModelAndView mav = new ModelAndView();
		mav.addObject("indexList", items);
		mav.addObject("objList",objs);
		mav.addObject("archcode",archcode);
		//mav.addObject("resultList",results);
		System.out.println(items.size());
		mav.setViewName("targetdatamanage/companytargetdata");
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save_item(ResultModel model,String archcode,String year,String season, HttpServletRequest request, HttpServletResponse response) {
	//	String classT=request.getParameter("class");
		//System.out.println(JSONArray.toJSON(model));
		System.out.println(archcode);
		AjaxResultJson j = new AjaxResultJson();
		String message;
		List<Long> totalScores=new ArrayList<Long>();
		for(TargetResult item:model.getResult()){
			
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
				String[] objcodes=item.getObjectCode().split(",");
				String[] planvs=item.getPlanValue().split(",");
				String[] realvs=item.getRealValue().split(",");
				String[] scores=item.getScore().split(",");
				for(int i=0;i<objcodes.length;i++){
					TargetResult tr=new TargetResult();
					tr.setYear(year);
					tr.setSeason(season);
					tr.setArchCode(archcode);
					tr.setIndexCode(item.getIndexCode().split(",")[0]);
					tr.setObjectCode(objcodes[i]);
					tr.setPlanValue(planvs[i]);
					tr.setRealValue(realvs[i]);
					tr.setScore(scores[i]);
					if(totalScores.size()<=i){
						totalScores.add(Long.parseLong(scores[i]));
					}else {
						totalScores.set(i, totalScores.get(i)+Long.parseLong(scores[i]));
					}
					systemService.addEntity(tr);
				}
			}
		}
		String[] objcodes=model.getResult().get(0).getObjectCode().split(",");
		for(int i=0;i<objcodes.length;i++){
			TotalScore ts=new TotalScore();
			ts.setYear(year);
			ts.setSeason(season);
			ts.setArchCode(archcode);
			ts.setObjectCode(objcodes[i]);
			ts.setTotalScore(totalScores.get(i));
			systemService.addEntity(ts);
		}
		/*if (item.getID() != null) {
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
		}*/
		//j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(TargetResult supplier, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<TargetResult> cq = new QueryDescriptor<TargetResult>(TargetResult.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<TargetResult> tqBuilder = QueryUtils.getTQBuilder(supplier, request.getParameterMap());
		//只能看自己部门的供应商
		//tqBuilder.addRestriction(new TQRestriction( "manage_depart", "like", "%"+SupplierDao.getOneDepart()+"%"));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="totaldgdata")
	@ResponseBody
	public void dgDataTotal(TotalScore supplier, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<TotalScore> cq = new QueryDescriptor<TotalScore>(TotalScore.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<TotalScore> tqBuilder = QueryUtils.getTQBuilder(supplier, request.getParameterMap());
		//只能看自己部门的供应商
		//tqBuilder.addRestriction(new TQRestriction( "manage_depart", "like", "%"+SupplierDao.getOneDepart()+"%"));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
}
