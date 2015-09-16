package edu.cqu.ncycoa.safety.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import edu.cqu.ncycoa.safety.domain.DangerSource;
import edu.cqu.ncycoa.safety.poi.ExcelReader;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;
@Controller
@RequestMapping("/dangersource_management")
public class DangerSourceController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add_main")
	public String addMain(HttpServletRequest request) {
		return "safeproduction_management/maindangersource";
	}
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "safeproduction_management/dangersource";
	}
	
	@RequestMapping(params="import")
	public String importXLS(HttpServletRequest request) {
		String path=request.getParameter("path");
		System.out.println(path);
		ExcelReader excelReader = new ExcelReader();
		InputStream is2 = null;
		try {
			is2 = new FileInputStream(path.replace("\\", "/"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, String> map=excelReader.readExcelContent(is2);
		for (int i = 1; i <= map.size(); i++) {
			String[] values=map.get(i).split(",");
			DangerSource ds=new DangerSource();
			//ds.setId(Long.valueOf(values[0]));
			ds.setActivityType(values[1]);
			ds.setJobActivity(values[2]);
			ds.setMainDangerSource(values[3]);
			ds.setDanger(values[4]);
			if(values[5].equals("重大"))
				ds.setDangerLevel(new Short((short)0));
			else{
				ds.setDangerLevel(new Short((short)1));
			}
			ds.setMeasureA(values[6]);
			ds.setMeasureB(values[7]);
			ds.setMeasureC(values[8]);
			ds.setMemo(values[9]);
			systemService.addEntity(ds);
        }
		return "safeproduction_management/dangersourcelist";
	}
	
	@RequestMapping(params="importm")
	public String importXLSm(HttpServletRequest request) {
		String path=request.getParameter("path");
		System.out.println(path);
		ExcelReader excelReader = new ExcelReader();
		InputStream is2 = null;
		try {
			is2 = new FileInputStream(path.replace("\\", "/"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, String> map=excelReader.readExcelContent(is2);
		for (int i = 1; i <= map.size(); i++) {
			String[] values=map.get(i).split(",");
			DangerSource ds=new DangerSource();
			//ds.setId(Long.valueOf(values[0]));
			ds.setActivityType(values[1]);
			ds.setJobActivity(values[2]);
			ds.setMainDangerSource(values[3]);
			ds.setDanger(values[4]);
			if(values[5].equals("重大"))
				ds.setDangerLevel(new Short((short)0));
			else{
				ds.setDangerLevel(new Short((short)1));
			}
			ds.setMeasureA(values[6]);
			ds.setMeasureB(values[7]);
			ds.setMeasureC(values[8]);
			ds.setMemo(values[9]);
			systemService.addEntity(ds);
        }
		return "safeproduction_management/maindangersourcelist";
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
		
		systemService.removeEntities(ids, DangerSource.class);
		message = "记录删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update_main")
    public ModelAndView updateMain(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		DangerSource dangerSource = systemService.findEntityById(Long.parseLong(id), DangerSource.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safeproduction_management/maindangersource");
		mav.addObject("dangerSource",dangerSource);
		return mav;
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		DangerSource dangerSource = systemService.findEntityById(Long.parseLong(id), DangerSource.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("safeproduction_management/dangersource");
		mav.addObject("dangerSource",dangerSource);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(DangerSource dangerSource, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (dangerSource.getId() != null) {
			message = "更新成功";
			DangerSource t = systemService.findEntityById(dangerSource.getId(), DangerSource.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(dangerSource, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "添加成功";
			
			systemService.addEntity(dangerSource);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="dgview_main")
	public String dgViewMain(HttpServletRequest request) {
		
		return "safeproduction_management/maindangersourcelist";
		
	}
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "safeproduction_management/dangersourcelist";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_main")
	@ResponseBody
	public void dgDataMain(DangerSource dangerSource, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<DangerSource> cq = new QueryDescriptor<DangerSource>(DangerSource.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<DangerSource> tqBuilder = QueryUtils.getTQBuilder(dangerSource, request.getParameterMap());
		
		tqBuilder.addRestriction(new TQRestriction( "dangerLevel", "in", Arrays.asList(new Short((short)0))));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(DangerSource dangerSource, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<DangerSource> cq = new QueryDescriptor<DangerSource>(DangerSource.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<DangerSource> tqBuilder = QueryUtils.getTQBuilder(dangerSource, request.getParameterMap());
		
		tqBuilder.addRestriction(new TQRestriction( "dangerLevel", "in", Arrays.asList(new Short((short)1))));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
