package edu.cqu.ncycoa.web.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.Staff;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SupplierServiceImpl;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.EvaluDefine;
import edu.cqu.ncycoa.domain.EvaluResult;
import edu.cqu.ncycoa.domain.GoodsUsed;
import edu.cqu.ncycoa.domain.Supplier;
import edu.cqu.ncycoa.domain.SupplierExit;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;


@Controller
@RequestMapping("/supplier")
public class SupplierController {
	public static boolean isStartEvalu=false;
	
	@Resource(name="systemService")
	SystemService systemService;
	
	@Resource(name="systemService")
	CommonService commonService;

	//测试可用
	@RequestMapping(params="action=add")
	public void addt(HttpServletRequest request, HttpServletResponse response) {
		GoodsUsed s=new GoodsUsed();//由ajax传入值
		s.setUsedDepart("oracle");
		s.setUsedGoods("food");
		systemService.addEntity(s);
		System.out.println("供应商入库成功");
	}
	//一.供应商操作 
	//添加供应商 测试成功
	@RequestMapping(params="addi")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("supplier/supplier_add");
		String getSelect=SupplierServiceImpl.getGoodsType();
		String getDepart=SupplierServiceImpl.getDepart();
		mav.addObject("goodsType", getSelect);
		mav.addObject("manageDepart", getDepart);
		//request.setAttribute("goodsType", getSelect);
		return mav;
	}
	@RequestMapping(params="add")
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response) {
		Supplier s=new Supplier();//由ajax传入值
		String message;
		AjaxResultJson j = new AjaxResultJson();
		//将s传入
		systemService.addEntity(s);
		message = "供应商入库成功";
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
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
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "数据不合法";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		systemService.removeEntities(ids, Supplier.class);
		message = "供应商出库成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
	public ModelAndView update(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("###id##"+id);
		Supplier t = systemService.findEntityById(Long.parseLong(id), Supplier.class);
		//Supplier t=new Supplier();
		ModelAndView mav=new ModelAndView("supplier/supplier_add");
	
		mav.addObject("supplier", t);
		//request.setAttribute("goodsType", getSelect);
		return mav;
	}
	//更新某个供应商（通过id）测试成功
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(Supplier supplier, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		
		if(supplier.getAddress()=="")
			supplier.setAddress(" ");
		if(supplier.getAgent()=="")
			supplier.setAgent(" ");
		if(supplier.getManager()=="")
			supplier.setManager(" ");
		if(supplier.getRange()=="")
			supplier.setRange(" ");
		if(supplier.getRegistMoney()=="")
			supplier.setRegistMoney(" ");
		if(supplier.getScale()=="")
			supplier.setScale(" ");
		if(supplier.getTel()==""){
			supplier.setTel(" ");
		}
		if(supplier.getValid()==null)
			supplier.setValid("");
		if (supplier.getId() != null) {
			message = "供应商更新成功";
			Supplier t = systemService.findEntityById(supplier.getId(), Supplier.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(supplier, t);
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				message = "供应商更新失败";
			}
		} else {
			
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
		return "supplier/supplierlist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(Supplier supplier, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Supplier> cq = new QueryDescriptor<Supplier>(Supplier.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<Supplier> tqBuilder = QueryUtils.getTQBuilder(supplier, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	//分类查询供应商列表。  dgview已经实现分类查询
	
	//直接导入供应商列表
	
	//二.以下为物资使用记录操作：
	//添加使用记录
	@RequestMapping(params="used_addi")
	public String usedAdd(HttpServletRequest request) {
		return "supplier/used_add";
	}
	@RequestMapping(params = "used_save")
	@ResponseBody
	public void usedsave(GoodsUsed goodsUsed, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (goodsUsed.getId() != null) {
			message = "物资使用记录更新成功";
			GoodsUsed t = systemService.findEntityById(goodsUsed.getId(), GoodsUsed.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(goodsUsed, t);
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				message = "物资使用记录更新失败";
			}
		} else {
			//System.out.println(goodsUsed.getName()+"  "+goodsUsed.getGoodsType()+"  "+goodsUsed.getManageDepart());
			message = "供应商添加成功";
			systemService.addEntity(goodsUsed);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	//更新使用记录   params=used_update
	@RequestMapping(params="used_update")
	public ModelAndView usedUpdate(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("###id##"+id);
		GoodsUsed t = systemService.findEntityById(Long.parseLong(id), GoodsUsed.class);
		ModelAndView mav=new ModelAndView("supplier/used_add");
		mav.addObject("goodsUsed", t);
		return mav;
	}
	
	//查询物资使用记录
	@RequestMapping(params="useddgview")
	public String useddgView(HttpServletRequest request) {
		return "supplier/usedlist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="useddgdata")
	@ResponseBody
	public void useddgData(GoodsUsed goodsUsed, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<GoodsUsed> cq = new QueryDescriptor<GoodsUsed>(GoodsUsed.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<GoodsUsed> tqBuilder = QueryUtils.getTQBuilder(goodsUsed, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	//三.以下为评价操作
	//（判断是否为科长）科长发起评价工作———>消息提醒表
	@RequestMapping(params="evalu")
	public ModelAndView evaluStart(HttpServletRequest request) {
		DBObject db = new DBObject();
		String sql = "select * from NCYCOA_SUPPLIER_FLAG where FLAG_PARA=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String("isStartEvalu") };
        
		try {
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null) {
				DataRow r = dt.get(0);
				//System.out.println(r.getString("FLAG_VALUE"));
				if(r.getString("FLAG_VALUE").equals("T")){
					//System.out.println("good");
					isStartEvalu=true;
				    }
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!isStartEvalu){
			Staff staff=new Staff(SystemUtils.getSessionUser().getStaffcode(),"01.0000.40");
			if(staff.getPositioncode()==null){
				System.out.println("不是企管科长"+staff.getStaffname()+ staff.getPositioncode());
				ModelAndView mav=new ModelAndView("supplier/evaluclosed");
				return mav;
			}
			if(staff.getPositioncode().equals("01.0000.40")){
				System.out.println("是企管科长"+staff.getStaffname());
				ModelAndView mav=new ModelAndView("supplier/open_evalu"); 
				return mav;
			}
			else {
				System.out.println("不是企管科长"+staff.getStaffname()+ staff.getPositioncode());
			    //页面显示评价未开启
				ModelAndView mav=new ModelAndView("supplier/evaluclosed"); 
				return mav;
			}
		}

		ModelAndView mav=new ModelAndView("supplier/start_evalu");
		Staff staff=new Staff(SystemUtils.getSessionUser().getStaffcode(),"01.0000.40");
		if(staff.getPositioncode()==null){
			mav.addObject("isFlag", "false");
		}
		else if(staff.getPositioncode().equals("01.0000.40"))
		{
			mav.addObject("isFlag", "true");
		}else{
			mav.addObject("isFlag", "false");
		}
		return mav; 
	}
	
	@RequestMapping(params="openevalu")
	public ModelAndView openevalu(HttpServletRequest request) {
		//对isStartEvalu操作；
		SupplierServiceImpl.startEvalu();
		//update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE='F' where FLAG_PARA='isStartEvalu'
		isStartEvalu=true;
		ModelAndView mav=new ModelAndView("supplier/start_evalu");
		mav.addObject("isFlag", "true");
		return mav; 
	}
	
	@RequestMapping(params="closeevalu")
	public ModelAndView cloaseevalu(HttpServletRequest request) {
		//对isStartEvalu操作；
		
		SupplierServiceImpl.closeEvalu();
		//update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE='F' where FLAG_PARA='isStartEvalu'
		isStartEvalu=false;
		ModelAndView mav=new ModelAndView("supplier/open_evalu");
		mav.addObject("isFlag", "true");
		return mav; 
	}
	//录入评价标准表格
	//评价指标定义 add_index
	@RequestMapping(params="add_index")
	public String indexAdd(HttpServletRequest request) {
		return "supplier/evalu_index_add";
	}
	@RequestMapping(params = "evalu_index_save")
	@ResponseBody
	public void evaluIndexSave(EvaluDefine evaluDefine, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
        String code=evaluDefine.getIndexCode();
		String[] parentCode=code.split("\\.");
		if(parentCode.length>1)
			evaluDefine.setIndexParentCode(parentCode[0]);
		message = "评价成功";
		systemService.addEntity(evaluDefine);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	//评价录入（需要改）
	@RequestMapping(params="evalu_addi")
	public ModelAndView evaluAdd(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("supplier/evalu_add");
		int year=Calendar.getInstance().get(Calendar.YEAR);	
		String content=SupplierServiceImpl.getEvaluContent(String.valueOf(year));
		mav.addObject("evaluPanel", content);
		mav.addObject("count", SupplierServiceImpl.getContentCount());
		String getDepart=SupplierServiceImpl.getDepart();
		String getSupplier=SupplierServiceImpl.getSupplier();
		//System.out.println(SupplierDao.getContentCount());
		mav.addObject("evaluDepart", getDepart);
		mav.addObject("evaluSupplier", getSupplier);
		return mav;
	}
	@RequestMapping(params = "evalu_save")
	@ResponseBody
	@Scope("prototype")
	public void evaluSave(EvaluResult evaluResult, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		int year=Calendar.getInstance().get(Calendar.YEAR);		
		evaluResult.setEvaluYear(String.valueOf(year));
		if(SupplierServiceImpl.isEvalued(evaluResult.getEvaluDepart(),evaluResult.getEvaluSupplier(),evaluResult.getEvaluYear())==true){
			String message="You has evlued before!";
			j.setMsg(message);
			SystemUtils.jsonResponse(response, j);
		}else{
			String message;
			int score=evaluResult.getScore();
			if(score>=90&&score<=100)
				evaluResult.setLevel((short)0);
			else if(score>=80&&score<90)
				evaluResult.setLevel((short)1);
			else if(score>=60&&score<80)
				evaluResult.setLevel((short)2);
			else if(score<60){
				evaluResult.setLevel((short)3);
				SupplierExit se=new SupplierExit();
				se.setName(evaluResult.getEvaluSupplier());
				se.setReason((short)2);
				se.setExitTime(new Date());
				systemService.addEntity(se);
				SupplierServiceImpl.removeByName(evaluResult.getEvaluSupplier());
			}
			//System.out.println(goodsUsed.getName()+"  "+goodsUsed.getGoodsType()+"  "+goodsUsed.getManageDepart());
			message = "评价成功";
			//supplierEvaluation.setEvaluTime(new Date());
			systemService.addEntity(evaluResult);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			j.setMsg(message);
			SystemUtils.jsonResponse(response, j);
		}
	}
	//各部门添加对每个有关联供货商的评价（添加附件）
	//评价结果查询
	@RequestMapping(params="show")
	public String evaluShow(HttpServletRequest request) {
		return "supplier/show_evalu";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="evaludgdata")
	@ResponseBody
	public void evaluDgData(EvaluResult evaluResult, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<EvaluResult> cq = new QueryDescriptor<EvaluResult>(EvaluResult.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<EvaluResult> tqBuilder = QueryUtils.getTQBuilder(evaluResult, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	//四.供应商维护
	//供应商更新（查看更新表）
	
	//供应商退出（添加退出原因）
	@RequestMapping(params="mainten_exit")
	public ModelAndView maintenExit(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("supplier/mainten_exit");
		String getSupplier=SupplierServiceImpl.getAllSupplier();
		mav.addObject("suppliers", getSupplier);
		return mav;
	}
	
	@RequestMapping(params = "maintenexit_save")
	@ResponseBody
	public void maintenExitSave(SupplierExit supplierExit, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		message = "供应商退出成功";
		supplierExit.setExitTime(new Date());
		systemService.addEntity(supplierExit);
		SupplierServiceImpl.removeByName(supplierExit.getName());
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	@RequestMapping(params="exit_dgview")
	public String maintenExitdgView(HttpServletRequest request) {
		return "supplier/mainten_exitlist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="exit_dgdata")
	@ResponseBody
	public void maintenExitdgData(SupplierExit supplierExit, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<SupplierExit> cq = new QueryDescriptor<SupplierExit>(SupplierExit.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		
		//查询条件组装器
		TypedQueryBuilder<SupplierExit> tqBuilder = QueryUtils.getTQBuilder(supplierExit, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
