package edu.cqu.ncycoa.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entity.system.BaseStaff;
import com.entity.system.Staff;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.Supplier;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;


@Controller
@RequestMapping("/staff")
public class StaffController {
	public static boolean isStartEvalu=false;
	
	@Resource(name="systemService")
	SystemService systemService;
	
	@Resource(name="systemService")
	CommonService commonService;

	//一.供应商操作 
	//添加供应商 测试成功
	@RequestMapping(params="add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("xtwh/staff/staff_add");
//		String getSelect=SupplierServiceImpl.getGoodsType();
//		String getDepart=SupplierServiceImpl.getDepart();
//		mav.addObject("goodsType", getSelect);
//		mav.addObject("manageDepart", getDepart);
		//request.setAttribute("goodsType", getSelect);
		return mav;
	}
	
	//删除供应商（出库） 测试成功
	@RequestMapping(params="del")
	@ResponseBody
	public void del(HttpServletRequest request, HttpServletResponse response) {
		//Supplier s=new Supplier();//由ajax传入值
		String message;
		AjaxResultJson j = new AjaxResultJson();
		String id = request.getParameter("id");
		//s.setGoodsType(request.getParameter("type"));
		//将s传入
		if(StringUtils.isEmpty(id)) {
			return;
		}
		
		try {
			String ids=id;
		} catch (Exception e) {
			message = "数据不合法";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		systemService.removeEntityById(id, BaseStaff.class);
		message = "删除成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
	public ModelAndView update(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("###id##"+id);
		BaseStaff t = systemService.findEntityById(id, BaseStaff.class);
		//Supplier t=new Supplier();
		ModelAndView mav=new ModelAndView("xtwh/staff/staff_add");
	
		mav.addObject("staff", t);
		//request.setAttribute("goodsType", getSelect);
		return mav;
	}
	
	//add
		@RequestMapping(params = "save_add")
		@ResponseBody
		public void saveAdd(BaseStaff supplier, HttpServletRequest request, HttpServletResponse response) {
			AjaxResultJson j = new AjaxResultJson();
			String message;
			
		
		
				message = "添加成功";
				//supplier.setInputTime(new Date());
				systemService.addEntity(supplier);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			
			
			j.setMsg(message);
			SystemUtils.jsonResponse(response, j);
		}
	
	//更新某个供应商（通过id）测试成功
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(BaseStaff supplier, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
	
	
		if (id != null&&id!="") {
			System.out.println(id);
			message = "供应商更新成功";
			BaseStaff t = systemService.findEntityById(supplier.getIdcard(), BaseStaff.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(supplier, t);
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				message = "供应商更新失败";
			}
		} else {
			System.out.println("this");
			message = "供应商添加成功";
			//supplier.setInputTime(new Date());
			systemService.addEntity(supplier);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	//查询所有供应商列表 findAllEntities 还是用dgData(最终用daData，showall也可以用)
	@RequestMapping(params="showall")
	@ResponseBody
	public void showAll(HttpServletRequest request, HttpServletResponse response) {
		//AjaxResultJson j = new AjaxResultJson();
		java.util.List<Supplier> resultList=commonService.findAllEntities(Supplier.class);
		//将resultList反给前端
		for(Supplier r:resultList)
		{
			System.out.println(r.getName()+"  "+r.getGoodsType()+"  "+r.getManageDepart());
		}
		
		//j.setMsg(message);
		//SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		return "xtwh/staff/stafflist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(BaseStaff supplier, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<BaseStaff> cq = new QueryDescriptor<BaseStaff>(BaseStaff.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<BaseStaff> tqBuilder = QueryUtils.getTQBuilder(supplier, request.getParameterMap());
		//只能看自己部门的供应商
		//tqBuilder.addRestriction(new TQRestriction( "manage_depart", "like", "%"+SupplierDao.getOneDepart()+"%"));
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="addorgmember")
	@ResponseBody
	public void addorgmember(String obj,HttpServletRequest request, HttpServletResponse response){
		AjaxResultJson j = new AjaxResultJson();
		String message = "";
		JSONArray json = new JSONArray();
		JSONObject jsonObject = json.parseObject(obj);
		Staff staff = new Staff();
		staff.setStaffcode(jsonObject.get("staffcode").toString());
		staff.setStaffname(jsonObject.get("staffname").toString());
		staff.setOrgcode(jsonObject.get("orgcode").toString());
		staff.setOrgname(jsonObject.get("orgname").toString());
		staff.setPositioncode(jsonObject.get("positioncode").toString());
		staff.setPositionname(jsonObject.get("positionname").toString());
		staff.setGender(jsonObject.get("gender").toString());
		staff.setIdcard(jsonObject.get("idcard").toString());
		if(staff.Insert()){
			message="1";
		}else{
			message="0";
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
		
	}
}
