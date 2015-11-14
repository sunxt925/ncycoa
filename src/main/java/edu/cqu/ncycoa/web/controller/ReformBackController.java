package edu.cqu.ncycoa.web.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.common.Format;
import com.common.Util;
import com.common.WordUtils;
import com.dao.system.UnitDao;
import com.entity.index.AllMeritCollection;
import com.entity.system.UserInfo;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.Reform;
import edu.cqu.ncycoa.domain.ReformBack;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;


@Controller
@RequestMapping("/reformback_management")
public class ReformBackController {
	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("reformid"); 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reformback_management/reformback");
		mav.addObject("reformid",id);
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
		
		systemService.removeEntities(ids, ReformBack.class);
		message = "整改反馈删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(ReformBack reformBack, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (reformBack.getId() != null) {
			message = "整改反馈更新成功";
			ReformBack t = systemService.findEntityById(reformBack.getId(), ReformBack.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(reformBack, t);
				
				
				systemService.saveEntity(t);
				
				
			} catch (Exception e) {
				message = "整改反馈更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "整改反馈上传成功";
			reformBack.setSubDate(Format.strToDate(Format.getNowtime()));
			reformBack.setSubUser(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
			//更新整改记录状态
			Reform reform= systemService.findEntityById(reformBack.getReformId(), Reform.class);
			reform.setFlag("1");
			
			UserInfo userinfo = (UserInfo)request.getSession().getAttribute("UserInfo");
			
			
			String companycode = AllMeritCollection.getcompanyByobject(userinfo.getOrgCode());
			
			//设置第二级审核人员
			if(Integer.parseInt((companycode.split("\\.")[2])) >= 20){
				//区县
				reformBack.setZrStaff(UnitDao.getComanyAudit(companycode));
			}else{
				//市局
				reformBack.setZrStaff(UnitDao.getCityComanyAudit(userinfo.getOrgCode()));
			}
			
			systemService.saveEntity(reform);
			
			String tempfilename = Util.getName();
			File f = new File(Util.getfileCfg().get("uploadfilepath")+tempfilename+".doc");
			if (!f.getParentFile().exists())
				f.getParentFile().mkdirs();
			WordUtils.produceWord(Util.getfileCfg().get("uploadfilepath")+tempfilename+".doc", Util.getFilepath("wordtemplate/reformtemplate.doc"), getCommentMap(reformBack,reform));
			reformBack.setReformFile(tempfilename);
			
			
			systemService.addEntity(reformBack);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	public Map<String, String> getCommentMap(ReformBack reformback,Reform reform){
		Map<String, String> map = new HashMap<String, String>();
		Calendar c_f = Calendar.getInstance();
		c_f.setTime(reform.getXdDate());
		reform.getXdzgOrgcode();
		reform.getClOrgcode();
		map.put("zerenorg", CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",reform.getClOrgcode()));
		map.put("jianchaorg", CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",reform.getXdzgOrgcode()));
		map.put("content", reform.getMemo());
	
		map.put("y0", c_f.get(Calendar.YEAR)+"");
		map.put("m0", (c_f.get(Calendar.MONTH)+1)+"");
		map.put("d0", c_f.get(Calendar.DAY_OF_MONTH)+"");
		map.put("reasonanalyer", reformback.getReasonAnalyer());
		map.put("zerenorg1", CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname",reform.getClOrgcode()));
		Calendar c_f_1 = Calendar.getInstance();
		c_f_1.setTime(new Date());
		map.put("y1", c_f.get(Calendar.YEAR)+"");
		map.put("m1", (c_f.get(Calendar.MONTH)+1)+"");
		map.put("d1", c_f.get(Calendar.DAY_OF_MONTH)+"");
		map.put("preventive_measure", reformback.getPreMeasure());
		map.put("s_zd", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",reformback.getZdStaff()));
		Calendar c_f_2 = Calendar.getInstance();
		c_f_2.setTime(reformback.getZdDate());
		map.put("y2", c_f.get(Calendar.YEAR)+"");
		map.put("m2", (c_f.get(Calendar.MONTH)+1)+"");
		map.put("d2", c_f.get(Calendar.DAY_OF_MONTH)+"");
		map.put("s_pz", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",reformback.getPzStaff()));
		Calendar c_f_3 = Calendar.getInstance();
		c_f_3.setTime(reformback.getPzDateDate());
		map.put("y3", c_f.get(Calendar.YEAR)+"");
		map.put("m3", (c_f.get(Calendar.MONTH)+1)+"");
		map.put("d3", c_f.get(Calendar.DAY_OF_MONTH)+"");
		map.put("complete_res", reformback.getMemo());
		
		
		
		map.put("zerenstaff",  CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",reformback.getZrStaff()));
		Calendar c_f_4 = Calendar.getInstance();
		c_f_4.setTime(new Date());
		map.put("y4", c_f.get(Calendar.YEAR)+"");
		map.put("m4", (c_f.get(Calendar.MONTH)+1)+"");
		map.put("d4", c_f.get(Calendar.DAY_OF_MONTH)+"");
		if(null!=reformback.getYanzheng()&&!reformback.getYanzheng().equals("")){
			map.put("complete_yzres", reformback.getYanzheng());
		}
		if(null!=reformback.getYzStaff()&&!reformback.getYzStaff().equals("")){
			map.put("yzstaff", CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname",reformback.getYzStaff()));
		}
		if(null!=reformback.getZrDateDate()){
			Calendar c_f_5 = Calendar.getInstance();
			c_f_5.setTime(reformback.getZrDateDate());
			map.put("y5", c_f.get(Calendar.YEAR)+"");
			map.put("m5", (c_f.get(Calendar.MONTH)+1)+"");
			map.put("d5", c_f.get(Calendar.DAY_OF_MONTH)+"");
		}
	
		return map;
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "reformback_management/reformbacklist";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(ReformBack reformBack, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<ReformBack> cq = new QueryDescriptor<ReformBack>(ReformBack.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<ReformBack> tqBuilder = QueryUtils.getTQBuilder(reformBack, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
