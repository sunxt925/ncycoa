package edu.cqu.ncycoa.target.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.target.domain.ObjIndexArchUser;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.domain.TargetResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/datainput")
public class DataInputController {
	@Resource(name="systemService")
	SystemService systemService;

	@RequestMapping(params="planinput")
	public ModelAndView planInput(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		System.out.println("data");
		//items筛选出公司类体系
		mav.setViewName("targetdatamanage/data_input/plan_data_input");		
		return mav;
	}
	@RequestMapping(params="completeinput")
	public ModelAndView compelteInput(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		System.out.println("data");
		//items筛选出公司类体系
		mav.setViewName("targetdatamanage/data_input/complete_data_input");		
		return mav;
	}
	/**
	 * 选择指标体系
	 * */
	@RequestMapping(params="getArch")
	public ModelAndView getArchList(HttpServletRequest request, HttpServletResponse response){
		String classT=request.getParameter("class");
		
		
		//System.out.println("datassada");
		String jpql="FROM ObjIndexItem as o where o.IsParent='1' and o.IndexCode LIKE '"+classT+"%' and o.IsLast='1'";
		List<ObjIndexItem> items=systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("archList", items);
		mav.setViewName("targetdatamanage/selectarch");
		return mav;
	}
	/**
	 * 查询指标
	 * */
	//选择年份，季度，指标体系之后：
		@RequestMapping(params="getplanTarget")
		public ModelAndView getTableList(String archcode,HttpServletRequest request, HttpServletResponse response){
//			String year=request.getParameter("year");
//			String season=request.getParameter("season");
//			String archcode=request.getParameter("archcode");
			System.out.println(archcode);
			String jpql="FROM ObjIndexItem as o where o.IsParent='0' and o.IndexCode LIKE '"+archcode.substring(0, 7)+"%' and o.IsLast='1'";
			List<ObjIndexItem> items=systemService.readEntitiesByJPQL(jpql, ObjIndexItem.class);
			
			String jpql2="FROM ObjIndexArchUser as o where o.IndexArchCode='"+archcode.substring(0, 7)+"'";
			List<ObjIndexArchUser> objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			
			//List<String> objcodes=ResultService.getObjCodesByArch(archcode);
			//System.out.println(objcodes);
			ModelAndView mav = new ModelAndView();
			mav.addObject("indexList", items);
			mav.addObject("objList",objs);
			mav.addObject("archcode",archcode);
			//mav.addObject("resultList",results);
			System.out.println(items.size());
			if(archcode.startsWith("C"))
				mav.setViewName("targetdatamanage/data_input/plan_data_input");
			else if (archcode.startsWith("D")) {
				mav.setViewName("targetdatamanage/departmenttargetdata");
			}else{
				mav.setViewName("targetdatamanage/personaltargetdata");
			}
			return mav;
		}
		/**
		 * 查询计划对象
		 * @throws IOException 
		 * */
		@RequestMapping(params="getplanobj")
		public void getplanObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String indexname=request.getParameter("indexname");
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			if(indexname.contains("月度")){
				jsonObject.put("objtype", "月度");
				jsonArray.add(jsonObject);
			}else if(indexname.contains("季度")){
				jsonObject.put("objtype", "M");
				jsonArray.add(jsonObject);
				
			}else if(indexname.contains("半年")){
				jsonObject.put("objtype", "半年");
				jsonArray.add(jsonObject);
				
			}else if(indexname.contains("一年")){
				jsonObject.put("objtype", "一年");
				jsonArray.add(jsonObject);
				
			}else{
				jsonObject.put("objtype", "随机");
				jsonArray.add(jsonObject);
			}
			String archcode=request.getParameter("archcode");
			String jpql2="FROM ObjIndexArchUser as o where o.IndexArchCode='"+archcode.substring(0, 7)+"'";
			List<ObjIndexArchUser> objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			int count=0;
			for (ObjIndexArchUser item : objs) {
				 jsonObject=new JSONObject();
				jsonObject.put("obj", item.getObjectcode());
				jsonArray.add(jsonObject);
				count++;
			}
			jsonObject.put("count", count);
			jsonArray.add(jsonObject);
			PrintWriter out=response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		/*	ModelAndView mav = new ModelAndView();
			mav.addObject("objList", objs);
			mav.setViewName("targetdatamanage/selectarch");
			return mav;*/
			return;
		}
		/**
		 * 保存计划值
		 * */
		 
			@RequestMapping(params="saveplanobj")
			public ModelAndView saveplanObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
//				TargetResult 
				//List<TargetResult> TargetResults = new ArrayList();
				String message="";
				String indexCode=request.getParameter("indexCode");
				String archCode=request.getParameter("archCode");
				String[] objectCode=request.getParameterValues("objcode");
				String[] time=request.getParameterValues("time");
				String[] planValue = request.getParameterValues("plannumber");
				int count=0;
				for(int i=0;i<time.length;i++){
					for(int j=0;j<objectCode.length ;j++){
						TargetResult targetResult = new TargetResult();
						targetResult.setArchCode(archCode);
						targetResult.setIndexCode(indexCode);
						targetResult.setSeason(time[i]);
						targetResult.setObjectCode(objectCode[j]);
						targetResult.setPlanValue(planValue[count++]);
						try {
							//保存到数据库中
						systemService.saveEntity(targetResult);
						 message = "更新成功";
						System.out.println(message);
						}catch (Exception e) {
							 message = "更新失败";
							System.out.println(message);
						}
						//TargetResults.add(targetResult);
						
					}
				}
				
				ModelAndView mav = new ModelAndView();
				mav.addObject("message", message);	
				mav.setViewName("targetdatamanage/data_input/plan_data_input");
				return mav;
				
			}
		/**
		 * 查询完成对象
		 * @throws IOException 
		 * */
		@RequestMapping(params="getcompleteobjbytype")
		public void getcompleteobjbyType(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String indexname=request.getParameter("indexname");
			String type=request.getParameter("type");
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			String archcode=request.getParameter("archcode");
			List<ObjIndexArchUser> objs = null;
			if(type=="M"){
				String jpql2="FROM ObjIndexArchUser as o where o.objecttype LIKE 'M' and  o.IndexArchCode='"+archcode.substring(0, 7)+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type=="S"){
				String jpql2="FROM ObjIndexArchUser as o where o.objecttype LIKE 'S' and  o.IndexArchCode='"+archcode.substring(0, 7)+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type=="H"){
				String jpql2="FROM ObjIndexArchUser as o where o.objecttype LIKE 'H' and  o.IndexArchCode='"+archcode.substring(0, 7)+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type=="Y"){
				String jpql2="FROM ObjIndexArchUser as o where o.objecttype LIKE 'Y' and  o.IndexArchCode='"+archcode.substring(0, 7)+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type=="D"){
				String jpql2="FROM ObjIndexArchUser as o where o.objecttype LIKE 'D' and  o.IndexArchCode='"+archcode.substring(0, 7)+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}
			int count=0;
			for (ObjIndexArchUser item : objs) {
				 jsonObject=new JSONObject();
				jsonObject.put("obj", item.getObjectcode());
				jsonArray.add(jsonObject);
				count++;
			}
			jsonObject.put("count", count);
			jsonArray.add(jsonObject);
			PrintWriter out=response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		/*	ModelAndView mav = new ModelAndView();
			mav.addObject("objList", objs);
			mav.setViewName("targetdatamanage/selectarch");
			return mav;*/
			return;
		}
}
