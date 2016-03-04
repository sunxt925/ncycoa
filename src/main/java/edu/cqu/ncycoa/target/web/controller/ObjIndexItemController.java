package edu.cqu.ncycoa.target.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.target.domain.ObjIndexDefine;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.service.TargetService;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/objindexitem")
public class ObjIndexItemController {
	@Resource(name="systemService")
	SystemService systemService;
	
	//4月新增
	@RequestMapping(params="indexdefine_c")
	public ModelAndView indexdefine_c(HttpServletRequest request, HttpServletResponse response){

		String jpql="FROM ObjIndexDefine as o where o.memo='C'";
		List<ObjIndexDefine> items = systemService.readEntitiesByJPQL(jpql, ObjIndexDefine.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexlist");
		mav.addObject("items",items);
		mav.addObject("classT","C");
		return mav;
	}
	
	@RequestMapping(params="indexdefine_d")
	public ModelAndView indexdefine_d(HttpServletRequest request, HttpServletResponse response){

		String jpql="FROM ObjIndexDefine as o where o.memo='D'";
		List<ObjIndexDefine> items = systemService.readEntitiesByJPQL(jpql, ObjIndexDefine.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexlist");
		mav.addObject("items",items);
		mav.addObject("classT","D");
		return mav;
	}
	@RequestMapping(params="indexdefine_s")
	public ModelAndView indexdefine_s(HttpServletRequest request, HttpServletResponse response){

		String jpql="FROM ObjIndexDefine as o where o.memo='S'";
		List<ObjIndexDefine> items = systemService.readEntitiesByJPQL(jpql, ObjIndexDefine.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexlist");
		mav.addObject("items",items);
		mav.addObject("classT","S");
		return mav;
	}
	
	@RequestMapping(params="add_itemdef")
	public ModelAndView add_itemdef(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexadd");
		return mav;
	}
	
	@RequestMapping(params = "save_itemdef")
	@ResponseBody
	public void save_itemdef(ObjIndexDefine item, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (item.getStdIndexCode() != null) {
			message = "更新成功";
			ObjIndexDefine t = systemService.findEntityById(item.getStdIndexCode(), ObjIndexDefine.class);
			if(t==null){
				message = "添加成功";
				systemService.addEntity(item);
			}else{
			try {
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			}
			} else {
			message = "添加成功";
			systemService.addEntity(item);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="getIndexToArch")
	public ModelAndView getArchList(HttpServletRequest request, HttpServletResponse response){
		String classT=request.getParameter("class");
		System.out.println("class="+classT);
		String jpql="FROM ObjIndexDefine as o where o.memo='"+classT+"'";
		String orgcode=SystemUtils.getSessionUser().getOrgCode();
		List<ObjIndexDefine> items=systemService.readEntitiesByJPQL(jpql, ObjIndexDefine.class);
		List<ObjIndexDefine> rmitems=new ArrayList<ObjIndexDefine>();
		for(ObjIndexDefine oid:items){
			if(oid.getPublicFlag().equals("0")&&oid.getBelongOrgcode()!=orgcode){
				rmitems.add(oid);
			}
		}
		items.removeAll(rmitems);
		ModelAndView mav = new ModelAndView();
		mav.addObject("indexList", items);
		mav.setViewName("targetmanage/selectindex");
		return mav;
	}
	
	//原来
	//indexrootlist_c  显示指标体系表  
	@RequestMapping(params="indexrootlist_c")
	public ModelAndView indexrootList_c(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		//List<ObjIndexItem> items =TargetService.getArchByClass("C");
		//List<ObjIndexItem> items =new TargetService().getArchEntidies(new String("C"));
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		mav.addObject("classT","C");
		return mav;
	}
	
	@RequestMapping(params="indexrootlist_d")
	public ModelAndView indexrootList_d(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		//List<ObjIndexItem> items =TargetService.getArchByClass("D");
		//List<ObjIndexItem> items =new TargetService().getArchEntidies("D");
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'D%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		mav.addObject("classT","D");
		return mav;
	}
	
	@RequestMapping(params="indexrootlist_s") //之后要改 分公司，部门，个人
	public ModelAndView indexrootList_s(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'S%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		mav.addObject("classT","S");
		return mav;
	}
	
	//显示指标项的根列表
	@RequestMapping(params="indexitemmanage_c") //之后要改 只返回最新版本的根节点,筛选出公司的
	public ModelAndView indexrootlist_c(HttpServletRequest request, HttpServletResponse response) {
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%' and o.IsLast='1'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexitemmanage");
		mav.addObject("items",items);
		mav.addObject("classT","C");
		return mav;
	}
	
	@RequestMapping(params="indexitemmanage_d") //之后要改 只返回最新版本的根节点
	public ModelAndView indexrootlist_d(HttpServletRequest request, HttpServletResponse response) {
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'D%' and o.IsLast='1'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexitemmanage");
		mav.addObject("items",items);
		mav.addObject("classT","D");
		return mav;
	}
	
	@RequestMapping(params="indexitemmanage_s") //之后要改 只返回最新版本的根节点
	public ModelAndView indexrootlist_s(HttpServletRequest request, HttpServletResponse response) {
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'S%' and o.IsLast='1'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexitemmanage");
		mav.addObject("items",items);
		mav.addObject("classT","S");
		return mav;
	}
	
	
	@RequestMapping(params="indexlist_c")  //查看最新版本的指标树
	public ModelAndView indexlist_c(HttpServletRequest request, HttpServletResponse response){
		String ccm = request.getParameter("ccm"); 
		String classT = request.getParameter("classT"); 
		System.out.println("classT="+classT);
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", ccm, ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='"+ccm+"' and o.IsLast='1'";
		System.out.println(jpql);
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexlistfromarch");
		mav.addObject("items",items);
		mav.addObject("pcode",ccm);
		mav.addObject("classT",classT);
		return mav;
	}
	
	@RequestMapping(params="add_arch")
	public ModelAndView add_arch(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("targetmanage/indexarchadd");
		mav.addObject("classT",request.getParameter("class"));
		return mav;
	}
	
	@RequestMapping(params="add_item")
	public ModelAndView add_item(HttpServletRequest request, HttpServletResponse response) {
		String ccm = request.getParameter("ccm"); 
		String classT = request.getParameter("classT"); 
		ModelAndView mav = new ModelAndView();
		//items筛选出公司类体系
		mav.setViewName("targetmanage/indexaddfromarch");
		mav.addObject("pcode",ccm);
		mav.addObject("classT",classT);
		return mav;
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
		String[] ids;
		try {
			ids = id.split(",");
		} catch (Exception e) {
			
			message = "数据不合法";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		for(int i=0;i<ids.length;i++){
			ObjIndexItem oii=systemService.findEntityById(ids[i], ObjIndexItem.class);
			if(oii.getIsLast().equals("1")){
				TargetService.setOldOnetoNewest(TargetService.getLastVersionCode(oii.getIndexCode()));
			}
		}
		systemService.removeEntities(ids, ObjIndexItem.class);
		
		
		//删除此体系下的所有指标项
		
		String jpql="FROM ObjIndexItem as o where o.IndexCode LIKE '"+id+"%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		for(ObjIndexItem i:items){
			systemService.removeEntityById(i.getIndexCode(), ObjIndexItem.class);
		}
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
		String[] ids;
		try {
			ids = id.split(",");
		} catch (Exception e) {
			
			message = "数据不合法";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		systemService.removeEntities(ids, ObjIndexItem.class);
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
	
	@RequestMapping(params="update_item")
    public ModelAndView update_i(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		ObjIndexItem item = systemService.findEntityById(id, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexaddfromarch");
		mav.addObject("item",item);
		//mav.addObject("orgname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", notice.getDepart()));
		return mav;
	}
	
	@RequestMapping(params="update_itemdef")
    public ModelAndView update_idef(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		ObjIndexDefine item = systemService.findEntityById(id, ObjIndexDefine.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexadd");
		mav.addObject("item",item);
		//mav.addObject("orgname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", notice.getDepart()));
		return mav;
	}
	
	@RequestMapping(params = "versionAdd") //新增版本号
	@ResponseBody
	public void versionAdd(HttpServletRequest request, HttpServletResponse response){
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id=request.getParameter("id");
		String jpql="FROM ObjIndexItem as o where o.IndexCode LIKE '"+id+"%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		String nextcode=TargetService.getNextVersionCode(id);
		String verString=nextcode.substring(nextcode.length()-3);
		
		for(ObjIndexItem i:items){
			System.out.println(i.getIndexCode());
			if(i.getIndexCode().length()>7){
				nextcode=nextcode+i.getIndexCode().substring(7);
			}
			ObjIndexItem t=new ObjIndexItem();
			System.out.println(nextcode);
			
			MyBeanUtils.copyBeanNotNull2Bean(i, t);
			t.setIndexCode(nextcode);
			t.setVersion(verString);
			if(i.getIndexCode().length()>7){
				t.setParentIndexCode(nextcode.substring(0,nextcode.length()-4));
			}
			i.setIsLast("0");
			systemService.saveEntity(t);
		}
		message = "版本添加成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
		//TargetService.addVersionById(id);
	}
	
	@RequestMapping(params = "save_arch")
	@ResponseBody
	public void save(ObjIndexItem item, HttpServletRequest request, HttpServletResponse response) {
		String classT=request.getParameter("class");
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (item.getIndexCode() != null) {
			message = "更新成功";
			ObjIndexItem t = systemService.findEntityById(item.getIndexCode(), ObjIndexItem.class);
			if(t==null){
				message = "添加成功";
				item.setEnabled(1);
				item.setIsParent("1");
				item.setIsLast("1");
				item.setParentIndexCode("-1");
				item.setVersion("V01");
				item.setIndexCode(TargetService.getNextArchCode(classT)+"."+item.getVersion());
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
			item.setVersion("V01");
			item.setIndexCode(TargetService.getNextArchCode(classT)+"."+item.getVersion());
			item.setEnabled(1);
			item.setIsLast("1");
			item.setIsParent("1");
			item.setParentIndexCode("-1");
			systemService.addEntity(item);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params = "save_item")
	@ResponseBody
	public void save_item(ObjIndexItem item, HttpServletRequest request, HttpServletResponse response) {
		String pcode = item.getParentIndexCode(); 
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (item.getIndexCode() != null) {
			message = "更新成功";
			ObjIndexItem t = systemService.findEntityById(item.getIndexCode(), ObjIndexItem.class);
			if(t==null){
				System.out.println(pcode);
				message = "添加成功";
				item.setEnabled(1);
				item.setIsLast("1");
				item.setIsParent("0");
				if(item.getScorePeriod().startsWith("M")){
					item.setMemo("月度");
				}else if(item.getScorePeriod().startsWith("S")){
					item.setMemo("季度");
				}else if(item.getScorePeriod().startsWith("H")){
					item.setMemo("半年");
				}else if(item.getScorePeriod().startsWith("Y")){
					item.setMemo("年度");
				}else if(item.getScorePeriod().startsWith("D")){
					item.setMemo("随机");
				}
				//item.setParentIndexCode(pcode);
				//item.setVersion(pcode.substring(pcode.lastIndexOf('V'), pcode.lastIndexOf('V')+2));
				item.setIndexCode(TargetService.getNextIndexCode(pcode));
				systemService.addEntity(item);
			}else{
			try {
				item.setEnabled(t.getEnabled());
				item.setIsParent("0");
			    item.setParentIndexCode(t.getParentIndexCode());
			    item.setIsLast(t.getIsLast());
			    item.setMemo(t.getMemo());
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			}
			} else {
			message = "添加成功";
			//item.setVersion("V01");
			item.setIndexCode(TargetService.getNextIndexCode(pcode));
			item.setEnabled(1);
			item.setIsLast("1");
			item.setIsParent("0");
			//item.setParentIndexCode("-1");
			systemService.addEntity(item);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
}
