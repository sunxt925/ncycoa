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

	//���Կ���
	@RequestMapping(params="action=add")
	public void addt(HttpServletRequest request, HttpServletResponse response) {
		GoodsUsed s=new GoodsUsed();//��ajax����ֵ
		s.setUsedDepart("oracle");
		s.setUsedGoods("food");
		systemService.addEntity(s);
		System.out.println("��Ӧ�����ɹ�");
	}
	//һ.��Ӧ�̲��� 
	//��ӹ�Ӧ�� ���Գɹ�
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
		Supplier s=new Supplier();//��ajax����ֵ
		String message;
		AjaxResultJson j = new AjaxResultJson();
		//��s����
		systemService.addEntity(s);
		message = "��Ӧ�����ɹ�";
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	//ɾ����Ӧ�̣����⣩ ���Գɹ�
	@RequestMapping(params="del")
	@ResponseBody
	public void del(HttpServletRequest request, HttpServletResponse response) {
		//Supplier s=new Supplier();//��ajax����ֵ
		String message;
		AjaxResultJson j = new AjaxResultJson();
		String id = request.getParameter("id");
		//s.setGoodsType(request.getParameter("type"));
		//��s����
		if(StringUtils.isEmpty(id)) {
			return;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "���ݲ��Ϸ�";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		systemService.removeEntities(ids, Supplier.class);
		message = "��Ӧ�̳���ɹ�";
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
	//����ĳ����Ӧ�̣�ͨ��id�����Գɹ�
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
			message = "��Ӧ�̸��³ɹ�";
			Supplier t = systemService.findEntityById(supplier.getId(), Supplier.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(supplier, t);
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				message = "��Ӧ�̸���ʧ��";
			}
		} else {
			
			message = "��Ӧ����ӳɹ�";
			//supplier.setInputTime(new Date());
			systemService.addEntity(supplier);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	//��ѯ���й�Ӧ���б� findAllEntities ������dgData(������daData��showallҲ������)
	@RequestMapping(params="showall")
	@ResponseBody
	public void showAll(HttpServletRequest request, HttpServletResponse response) {
		//AjaxResultJson j = new AjaxResultJson();
		java.util.List<Supplier> resultList=commonService.findAllEntities(Supplier.class);
		//��resultList����ǰ��
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
		
		//��ѯ������װ��
		TypedQueryBuilder<Supplier> tqBuilder = QueryUtils.getTQBuilder(supplier, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	//�����ѯ��Ӧ���б�  dgview�Ѿ�ʵ�ַ����ѯ
	
	//ֱ�ӵ��빩Ӧ���б�
	
	//��.����Ϊ����ʹ�ü�¼������
	//���ʹ�ü�¼
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
			message = "����ʹ�ü�¼���³ɹ�";
			GoodsUsed t = systemService.findEntityById(goodsUsed.getId(), GoodsUsed.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(goodsUsed, t);
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				message = "����ʹ�ü�¼����ʧ��";
			}
		} else {
			//System.out.println(goodsUsed.getName()+"  "+goodsUsed.getGoodsType()+"  "+goodsUsed.getManageDepart());
			message = "��Ӧ����ӳɹ�";
			systemService.addEntity(goodsUsed);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	//����ʹ�ü�¼   params=used_update
	@RequestMapping(params="used_update")
	public ModelAndView usedUpdate(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("###id##"+id);
		GoodsUsed t = systemService.findEntityById(Long.parseLong(id), GoodsUsed.class);
		ModelAndView mav=new ModelAndView("supplier/used_add");
		mav.addObject("goodsUsed", t);
		return mav;
	}
	
	//��ѯ����ʹ�ü�¼
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
		
		//��ѯ������װ��
		TypedQueryBuilder<GoodsUsed> tqBuilder = QueryUtils.getTQBuilder(goodsUsed, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	//��.����Ϊ���۲���
	//���ж��Ƿ�Ϊ�Ƴ����Ƴ��������۹���������>��Ϣ���ѱ�
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
				System.out.println("������ܿƳ�"+staff.getStaffname()+ staff.getPositioncode());
				ModelAndView mav=new ModelAndView("supplier/evaluclosed");
				return mav;
			}
			if(staff.getPositioncode().equals("01.0000.40")){
				System.out.println("����ܿƳ�"+staff.getStaffname());
				ModelAndView mav=new ModelAndView("supplier/open_evalu"); 
				return mav;
			}
			else {
				System.out.println("������ܿƳ�"+staff.getStaffname()+ staff.getPositioncode());
			    //ҳ����ʾ����δ����
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
		//��isStartEvalu������
		SupplierServiceImpl.startEvalu();
		//update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE='F' where FLAG_PARA='isStartEvalu'
		isStartEvalu=true;
		ModelAndView mav=new ModelAndView("supplier/start_evalu");
		mav.addObject("isFlag", "true");
		return mav; 
	}
	
	@RequestMapping(params="closeevalu")
	public ModelAndView cloaseevalu(HttpServletRequest request) {
		//��isStartEvalu������
		
		SupplierServiceImpl.closeEvalu();
		//update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE='F' where FLAG_PARA='isStartEvalu'
		isStartEvalu=false;
		ModelAndView mav=new ModelAndView("supplier/open_evalu");
		mav.addObject("isFlag", "true");
		return mav; 
	}
	//¼�����۱�׼���
	//����ָ�궨�� add_index
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
		message = "���۳ɹ�";
		systemService.addEntity(evaluDefine);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	//����¼�루��Ҫ�ģ�
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
			message = "���۳ɹ�";
			//supplierEvaluation.setEvaluTime(new Date());
			systemService.addEntity(evaluResult);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			j.setMsg(message);
			SystemUtils.jsonResponse(response, j);
		}
	}
	//��������Ӷ�ÿ���й��������̵����ۣ���Ӹ�����
	//���۽����ѯ
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
		
		//��ѯ������װ��
		TypedQueryBuilder<EvaluResult> tqBuilder = QueryUtils.getTQBuilder(evaluResult, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	//��.��Ӧ��ά��
	//��Ӧ�̸��£��鿴���±�
	
	//��Ӧ���˳�������˳�ԭ��
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
		message = "��Ӧ���˳��ɹ�";
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
		
		//��ѯ������װ��
		TypedQueryBuilder<SupplierExit> tqBuilder = QueryUtils.getTQBuilder(supplierExit, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
