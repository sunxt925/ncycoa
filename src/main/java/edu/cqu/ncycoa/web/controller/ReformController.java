package edu.cqu.ncycoa.web.controller;

import java.util.Arrays;

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
import com.dao.system.UnitDao;
import com.entity.system.UserInfo;

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
import edu.cqu.ncycoa.domain.Reform;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;


@Controller
@RequestMapping("/reform_management")
public class ReformController {

	@Resource(name="systemService")
	SystemService systemService;
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "reform_management/reform";
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
			message = "���ݲ��Ϸ�";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		systemService.removeEntities(ids, Reform.class);
		message = "��������ɾ���ɹ�";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		Reform reform = systemService.findEntityById(Long.parseLong(id), Reform.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reform_management/reform");
		mav.addObject("reform",reform);
		mav.addObject("ClOrgname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", reform.getClOrgcode()));
		mav.addObject("XdzgOrgname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", reform.getXdzgOrgcode()));
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(Reform reform, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (reform.getId() != null) {
			message = "����������³ɹ�";
			Reform t = systemService.findEntityById(reform.getId(), Reform.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(reform, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "�����������ʧ��";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "���������´�ɹ�";
			reform.setHandler(((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
			reform.setXdDate(Format.strToDate(Format.getNowtime()));
			reform.setFlag("0");
			systemService.addEntity(reform);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		
		return "reform_management/reformlist";
		
	}
	
	@RequestMapping(params="dgviewquery")
	public String dgViewQuery(HttpServletRequest request) {
		
		return "reform_management/reformquerylist";
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(Reform reform, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Reform> cq = new QueryDescriptor<Reform>(Reform.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//��ѯ������װ��
		TypedQueryBuilder<Reform> tqBuilder = QueryUtils.getTQBuilder(reform, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdataquery")
	@ResponseBody
	public void dgDataQuery(Reform reform, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Reform> cq = new QueryDescriptor<Reform>(Reform.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//��ѯ������װ��
		TypedQueryBuilder<Reform> tqBuilder = QueryUtils.getTQBuilder(reform, request.getParameterMap());
		UserInfo u = (UserInfo)request.getSession().getAttribute("UserInfo");
		tqBuilder.addRestriction(new TQRestriction( "clOrgcode", "in", UnitDao.getOrgListByStaffcode(u.getStaffcode())));
		tqBuilder.addRestriction(new TQRestriction( "flag", "in", Arrays.asList("0")));
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
