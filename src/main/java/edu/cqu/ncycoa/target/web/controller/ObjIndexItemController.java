package edu.cqu.ncycoa.target.web.controller;

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
	
	//indexrootlist_c  ��ʾָ����ϵ��
	@RequestMapping(params="indexrootlist_c")
	public ModelAndView indexrootList_c(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		//List<ObjIndexItem> items =TargetService.getArchByClass("C");
		//List<ObjIndexItem> items =new TargetService().getArchEntidies(new String("C"));
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//itemsɸѡ����˾����ϵ
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
		//itemsɸѡ����˾����ϵ
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		mav.addObject("classT","D");
		return mav;
	}
	
	@RequestMapping(params="indexrootlist_s") //֮��Ҫ�� �ֹ�˾�����ţ�����
	public ModelAndView indexrootList_s(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("id"); 
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'S%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//itemsɸѡ����˾����ϵ
		mav.setViewName("targetmanage/indexrootlist");
		mav.addObject("items",items);
		mav.addObject("classT","S");
		return mav;
	}
	
	//��ʾָ����ĸ��б�
	@RequestMapping(params="indexitemmanage_c") //֮��Ҫ�� ֻ�������°汾�ĸ��ڵ�,ɸѡ����˾��
	public ModelAndView indexrootlist_c(HttpServletRequest request, HttpServletResponse response) {
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%' and o.IsLast='1'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexitemmanage");
		mav.addObject("items",items);
		return mav;
	}
	
	@RequestMapping(params="indexitemmanage_d") //֮��Ҫ�� ֻ�������°汾�ĸ��ڵ�
	public ModelAndView indexrootlist_d(HttpServletRequest request, HttpServletResponse response) {
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'D%' and o.IsLast='1'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexitemmanage");
		mav.addObject("items",items);
		return mav;
	}
	
	@RequestMapping(params="indexitemmanage_s") //֮��Ҫ�� ֻ�������°汾�ĸ��ڵ�
	public ModelAndView indexrootlist_s(HttpServletRequest request, HttpServletResponse response) {
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", "-1", ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'S%' and o.IsLast='1'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("targetmanage/indexitemmanage");
		mav.addObject("items",items);
		return mav;
	}
	
	
	@RequestMapping(params="indexlist_c")  //�鿴���°汾��ָ����
	public ModelAndView indexlist_c(HttpServletRequest request, HttpServletResponse response){
		String ccm = request.getParameter("ccm"); 
		//List<ObjIndexItem> items = systemService.findEntitiesByProperty("ParentIndexCode", ccm, ObjIndexItem.class);
		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='"+ccm+"' and o.IsLast='1'";
		System.out.println(jpql);
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//itemsɸѡ����˾����ϵ
		mav.setViewName("targetmanage/indexlist");
		mav.addObject("items",items);
		mav.addObject("pcode",ccm);
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
		ModelAndView mav = new ModelAndView();
		//itemsɸѡ����˾����ϵ
		mav.setViewName("targetmanage/indexadd");
		mav.addObject("pcode",ccm);
		return mav;
	}
	//ɾ��������ϵ
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
			
			message = "���ݲ��Ϸ�";
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
		
		
		//ɾ������ϵ�µ�����ָ����
		
		String jpql="FROM ObjIndexItem as o where o.IndexCode LIKE '"+id+"%'";
		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		for(ObjIndexItem i:items){
			systemService.removeEntityById(i.getIndexCode(), ObjIndexItem.class);
		}
		message = "ɾ���ɹ�";
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
			
			message = "���ݲ��Ϸ�";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		systemService.removeEntities(ids, ObjIndexItem.class);
		message = "ɾ���ɹ�";
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
		mav.setViewName("targetmanage/indexadd");
		mav.addObject("item",item);
		//mav.addObject("orgname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", notice.getDepart()));
		return mav;
	}
	
	@RequestMapping(params = "versionAdd") //�����汾��
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
		message = "�汾��ӳɹ�";
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
			message = "���³ɹ�";
			ObjIndexItem t = systemService.findEntityById(item.getIndexCode(), ObjIndexItem.class);
			if(t==null){
				message = "��ӳɹ�";
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
				message = "����ʧ��";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			}
			} else {
			message = "��ӳɹ�";
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
			message = "���³ɹ�";
			ObjIndexItem t = systemService.findEntityById(item.getIndexCode(), ObjIndexItem.class);
			if(t==null){
				System.out.println(pcode);
				message = "��ӳɹ�";
				item.setEnabled(1);
				item.setIsLast("1");
				item.setIsParent("0");
				//item.setParentIndexCode(pcode);
				//item.setVersion(pcode.substring(pcode.lastIndexOf('V'), pcode.lastIndexOf('V')+2));
				item.setIndexCode(TargetService.getNextIndexCode(pcode));
				systemService.addEntity(item);
			}else{
			try {
				item.setEnabled(1);
				item.setIsParent("0");
				item.setParentIndexCode("-1");
				MyBeanUtils.copyBeanNotNull2Bean(item, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "����ʧ��";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			}
			} else {
			message = "��ӳɹ�";
			//item.setVersion("V01");
			item.setIndexCode(TargetService.getNextIndexCode(pcode));
			item.setEnabled(1);
			item.setIsLast("1");
			item.setIsParent("0");
			item.setParentIndexCode("-1");
			systemService.addEntity(item);
		}
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
}
