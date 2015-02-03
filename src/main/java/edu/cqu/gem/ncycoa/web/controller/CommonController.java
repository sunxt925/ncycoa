package edu.cqu.gem.ncycoa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.cqu.gem.common.dto.DataGrid;
import edu.cqu.gem.common.dto.QueryDescriptor;
import edu.cqu.gem.common.tag.TagUtil;
import edu.cqu.gem.common.util.dao.QueryUtils;
import edu.cqu.gem.common.util.dao.TQOrder;
import edu.cqu.gem.common.util.dao.TypedQueryBuilder;
import edu.cqu.gem.ncycoa.domain.DataDictionary;
import edu.cqu.gem.ncycoa.service.CommonService;
import edu.cqu.gem.ncycoa.util.SystemUtils;

@Controller
public class CommonController {
	
	@RequestMapping("/**")
	public String dispatcher(HttpServletRequest request) {
		String url = request.getRequestURI();
		url = url.substring(SystemUtils.getContextPath().length(), url.lastIndexOf("."));
		return url;
	}
	
	@RequestMapping("/dgtest")
	public String dgTest(HttpServletRequest request) {
		return "listtest";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/dgdata")
	@ResponseBody
	public void dgData(DataDictionary dict, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<DataDictionary> cq = new QueryDescriptor<DataDictionary>(DataDictionary.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<DataDictionary> tqBuilder = QueryUtils.getTQBuilder(dict, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
}
