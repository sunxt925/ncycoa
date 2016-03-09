package edu.cqu.ncycoa.target.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.target.domain.ObjIndexArchUser;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.domain.ResultModel;
import edu.cqu.ncycoa.target.domain.TargetResult;
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
	
	@RequestMapping(params="view_c") //��˾ҳ��
	public ModelAndView indexrootList_c(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
//		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//itemsɸѡ����˾����ϵ
		mav.setViewName("targetdatamanage/companytargetdata");
		mav.addObject("class","C");
		return mav;
	}
	
	@RequestMapping(params="view_d") //����ҳ��
	public ModelAndView indexrootList_d(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
//		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//itemsɸѡ����˾����ϵ
		mav.setViewName("targetdatamanage/departmenttargetdata");
		mav.addObject("class","D");
		return mav;
	}
	
	@RequestMapping(params="view_s") //��˾ҳ��
	public ModelAndView indexrootList_s(HttpServletRequest request, HttpServletResponse response){
		
//		String jpql="FROM ObjIndexItem as o where o.ParentIndexCode='-1' and o.IndexCode LIKE 'C%'";
//		List<ObjIndexItem> items = systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		ModelAndView mav = new ModelAndView();
		//itemsɸѡ����˾����ϵ
		mav.addObject("class","S");
		mav.setViewName("targetdatamanage/personaltargetdata");
		return mav;
	}
	
	//���ѡ��ָ����ϵ��
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
	//ѡ����ݣ����ȣ�ָ����ϵ֮��
	@RequestMapping(params="getTable")
	public ModelAndView getTableList(String indexcode,HttpServletRequest request, HttpServletResponse response){
//		String year=request.getParameter("year");
//		String season=request.getParameter("season");
//		String archcode=request.getParameter("archcode");
		String archcode=indexcode;
		String jpql="FROM ObjIndexItem as o where o.IsParent='0' and o.IndexCode LIKE '"+archcode.substring(0, 7)+"%' and o.IsLast='1'";
		List<ObjIndexItem> items=systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		
		String jpql2="FROM ObjIndexArchUser as o where o.IndexArchCode='"+archcode.substring(0, 7)+"'";
		List<ObjIndexArchUser> objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
		//List<String> objcodes=ResultService.getObjCodesByArch(archcode);
		//System.out.println(objcodes);
		ModelAndView mav = new ModelAndView();
		mav.addObject("indexList", items);
		mav.addObject("objList",objs);
		System.out.println(items.size());
		mav.setViewName("targetdatamanage/companytargetdata");
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save_item(ResultModel model, HttpServletRequest request, HttpServletResponse response) {
	//	String classT=request.getParameter("class");
		System.out.println(JSONArray.toJSON(model));
		AjaxResultJson j = new AjaxResultJson();
		String message;
		for(TargetResult item:model.getResult()){
			
			if (item.getID() != null) {
				message = "���³ɹ�";
				TargetResult t = systemService.findEntityById(item.getID(), TargetResult.class);
				if(t==null){
					message = "��ӳɹ�";
					systemService.addEntity(item);
				}else{
					try {
						MyBeanUtils.copyBeanNotNull2Bean(item, t);
						systemService.saveEntity(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

					} catch (Exception e) {
						message = "����ʧ��";
					}
				}
			} else {
				message = "��ӳɹ�";
				String[] objcodes=item.getObjectCode().split(",");
				String[] planvs=item.getPlanValue().split(",");
				String[] realvs=item.getRealValue().split(",");
				String[] scores=item.getScore().split(",");
				for(int i=0;i<objcodes.length;i++){
					TargetResult tr=new TargetResult();
					tr.setIndexCode(item.getIndexCode().split(",")[0]);
					tr.setObjectCode(objcodes[i]);
					tr.setPlanValue(planvs[i]);
					tr.setRealValue(realvs[i]);
					tr.setScore(scores[i]);
					systemService.addEntity(tr);
				}
			}
		}
		/*if (item.getID() != null) {
			message = "���³ɹ�";
			TargetResult t = systemService.findEntityById(item.getID(), TargetResult.class);
			if(t==null){
				message = "��ӳɹ�";
				systemService.addEntity(item);
			}else{
				try {
					MyBeanUtils.copyBeanNotNull2Bean(item, t);
					systemService.saveEntity(t);
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

				} catch (Exception e) {
					message = "����ʧ��";
				}
			}
		} else {
			message = "��ӳɹ�";
			systemService.addEntity(item);
		}*/
		//j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
}
