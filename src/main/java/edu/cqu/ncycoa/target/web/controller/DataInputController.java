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
import edu.cqu.ncycoa.target.service.TargetService;
import edu.cqu.ncycoa.util.MyBeanUtils;
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
	@RequestMapping(params="scoreinput")
	public ModelAndView scoreinput(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		System.out.println("data");
		//items筛选出公司类体系
		mav.setViewName("targetdatamanage/data_input/score_data_input");		
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
	
		@RequestMapping(params="getplanobj",produces="text/html;charset=UTF-8")
		public void getplanObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("utf-8");
			String indexname=request.getParameter("indexname");
			String season;
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			if(indexname.contains("月度")){
				jsonObject.put("objtype", "M");
				season="M";
				jsonArray.add(jsonObject);
			}else if(indexname.contains("季度")){
				jsonObject.put("objtype", "S");
				season="S";
				jsonArray.add(jsonObject);
				
			}else if(indexname.contains("半年")){
				jsonObject.put("objtype", "H");
				season="H";
				jsonArray.add(jsonObject);
				
			}else if(indexname.contains("一年")){
				jsonObject.put("objtype", "Y");
				jsonArray.add(jsonObject);
				season="Y";
				
			}else{
				jsonObject.put("objtype", "D");
				jsonArray.add(jsonObject);
				season="D";
			}
			String archcode=request.getParameter("archcode");
			String jpql2="FROM ObjIndexArchUser as o where o.IndexArchCode='"+archcode.substring(0, 7)+"' order by o.objectcode";
			List<ObjIndexArchUser> objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			int count=0;
			//查询是否有记录
			String jpql3="FROM TargetResult as o where o.season LIKE '"+season+"%' and  o.ArchCode='"+archcode.substring(0, 7)+"' order by o.season,o.objectCode";
			List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
			if(objs_res!=null){
				 jsonObject=new JSONObject();
				jsonObject.put("obj_count",objs_res.size());
				jsonArray.add(jsonObject);
				 jsonObject=new JSONObject();
				jsonObject.put("obj_res", objs_res);
				jsonArray.add(jsonObject);
			
			}else {
				 jsonObject=new JSONObject();
				jsonObject.put("obj_count",0);
				jsonArray.add(jsonObject);
			}
			 jsonObject=new JSONObject();
		
				jsonObject.put("obj",objs);
				jsonArray.add(jsonObject);
				
			
			 jsonObject=new JSONObject();
			jsonObject.put("count", objs.size());
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
				/*String jpql3="FROM TargetResult as o  where  o.ArchCode='"+archCode.substring(0, 7)+"'";
				List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
				*/int count=0;
				TargetResult t = new TargetResult();
				System.out.println("start");
				for(int i=0;i<time.length;i++){
					for(int j=0;j<objectCode.length ;j++){
						TargetResult targetResult = new TargetResult();
						targetResult.setArchCode(archCode.trim());
						targetResult.setIndexCode(indexCode.trim());
						targetResult.setSeason(time[i].trim());
						targetResult.setObjectCode(objectCode[j].trim());
						long id=TargetService.getResultIDByObj(targetResult);
						targetResult.setPlanValue(planValue[count++]);
						System.out.println("id="+id);
						if(id!=-1){
							targetResult.setID(id);
							 //t = systemService.findEntityById(id, TargetResult.class);
							// targetResult.setPlanValue(t.getPlanValue());
							//	MyBeanUtils.copyBeanNotNull2Bean(targetResult, t);
						}
						
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
			response.setCharacterEncoding("utf-8");
		//	String indexname=request.getParameter("indexname");
			String type=request.getParameter("type");
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			String archcode=request.getParameter("archcode").trim();
			List<ObjIndexItem> indexs = null;
			List<ObjIndexArchUser> objs = null;
			if(type.contains("M")){
				String jpql2="FROM ObjIndexItem as o where o.ScorePeriod LIKE 'M%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("S")){
				String jpql2="FROM ObjIndexItem as o where o.ScorePeriod LIKE 'S%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("H")){
				String jpql2="FROM ObjIndexItem as o where o.ScorePeriod LIKE 'H%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("Y")){
				String jpql2="FROM ObjIndexItem as o where o.ScorePeriod LIKE 'Y%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("D")){
				String jpql2="FROM ObjIndexItem as o where o.ScorePeriod LIKE 'D%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}
			//int count=0;
			/*for (ObjIndexArchUser item : objs) {*/
			 jsonObject=new JSONObject();
			jsonObject.put("obj_count", objs.size());
			jsonArray.add(jsonObject);
				 jsonObject=new JSONObject();
				jsonObject.put("objs", objs);
				jsonArray.add(jsonObject);
				//count++;
				 jsonObject=new JSONObject();
					jsonObject.put("index_count", indexs.size());
					jsonArray.add(jsonObject);
			
			 jsonObject=new JSONObject();
				jsonObject.put("indexs", indexs);
				jsonArray.add(jsonObject);
				//查询是否有记录
				String jpql3="FROM TargetResult as o where o.season = '"+type+"' and o.ArchCode='"+archcode+"' order by o.season,o.objectCode";
				List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
				if(objs_res!=null){
					 jsonObject=new JSONObject();
					jsonObject.put("res_count",objs_res.size());
					jsonArray.add(jsonObject);
					 jsonObject=new JSONObject();
					jsonObject.put("obj_res", objs_res);
					jsonArray.add(jsonObject);
				
				}else {
					 jsonObject=new JSONObject();
					jsonObject.put("res_count",0);
					jsonArray.add(jsonObject);
				}
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
		@RequestMapping(params="savecomplateobj")
		public ModelAndView savecomplateObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String message="";
			String season=request.getParameter("season");
			String archCode=request.getParameter("archCode");
			String[] objectCode=request.getParameterValues("objectcode");
			String[] indexcode=request.getParameterValues("indexcode");
			String[] completeValue = request.getParameterValues("completenumber");
		/*	String jpql3="FROM TargetResult as o where o.season = '"+season+"' and o.ArchCode='"+archCode.substring(0, 7)+"'";
			List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
			*/TargetResult t = new TargetResult();
			int count=0;
			for(int i=0;i<indexcode.length;i++){
				for(int j=0;j<objectCode.length ;j++){
					TargetResult targetResult = new TargetResult();
					targetResult.setArchCode(archCode.trim());
					targetResult.setSeason(season.trim());
					targetResult.setIndexCode(indexcode[i].trim());
					targetResult.setObjectCode(objectCode[j].trim());
					long id=TargetService.getResultIDByObj(targetResult);
					targetResult.setRealValue(completeValue[count++]);
					if(id!=-1){
						targetResult.setID(id);
						 t = systemService.findEntityById(id, TargetResult.class);
						 targetResult.setPlanValue(t.getPlanValue());
						//MyBeanUtils.copyBeanNotNull2Bean(targetResult, t);
						//targetResult.setPlanValue(planValue);
					}
					
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
			mav.setViewName("targetdatamanage/data_input/complete_data_input");
			return mav;
			
		}
		
		/**
		 * 查询得分对象
		 * @throws IOException 
		 * */
		@RequestMapping(params="getscoreobjbytype")
		public void getscoreobjbyType(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("utf-8");
		//	String indexname=request.getParameter("indexname");
			String type=request.getParameter("type");
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			String archcode=request.getParameter("archcode").trim();
			List<ObjIndexItem> indexs = null;
			List<ObjIndexArchUser> objs = null;
			if(type.contains("M")){
				String jpql2="FROM ObjIndexItem as o where  o.examFlag='1' and o.ScorePeriod LIKE 'M%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("S")){
				String jpql2="FROM ObjIndexItem as o where  o.examFlag='1' and o.ScorePeriod LIKE 'S%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("H")){
				String jpql2="FROM ObjIndexItem as o where o.examFlag='1' and o.ScorePeriod LIKE 'H%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("Y")){
				String jpql2="FROM ObjIndexItem as o where  o.examFlag='1' and o.ScorePeriod LIKE 'Y%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}else if(type.contains("D")){
				String jpql2="FROM ObjIndexItem as o where  o.examFlag='1' and o.ScorePeriod LIKE 'D%' and  o.ParentIndexCode='"+archcode+"'" ;
				indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
				 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
				 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			
			}
			//int count=0;
			/*for (ObjIndexArchUser item : objs) {*/
			 jsonObject=new JSONObject();
			jsonObject.put("obj_count", objs.size());
			jsonArray.add(jsonObject);
				 jsonObject=new JSONObject();
				jsonObject.put("objs", objs);
				jsonArray.add(jsonObject);
				//count++;
				 jsonObject=new JSONObject();
					jsonObject.put("index_count", indexs.size());
					jsonArray.add(jsonObject);
			
			 jsonObject=new JSONObject();
				jsonObject.put("indexs", indexs);
				jsonArray.add(jsonObject);
				//查询是否有记录
				String jpql3="FROM TargetResult as o where o.season = '"+type+"' and o.ArchCode='"+archcode+"' order by o.season,o.objectCode";
				List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
				if(objs_res!=null){
					 jsonObject=new JSONObject();
					jsonObject.put("res_count",objs_res.size());
					jsonArray.add(jsonObject);
					 jsonObject=new JSONObject();
					jsonObject.put("obj_res", objs_res);
					jsonArray.add(jsonObject);
				
				}else {
					 jsonObject=new JSONObject();
					jsonObject.put("res_count",0);
					jsonArray.add(jsonObject);
				}
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
		@RequestMapping(params="savescoreobj")
		public ModelAndView savescoreObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String message="";
			String season=request.getParameter("season");
			String archCode=request.getParameter("archCode");
			String[] objectCode=request.getParameterValues("objectcode");
			String[] indexcode=request.getParameterValues("indexcode");
			String[] scoreValue = request.getParameterValues("scorenumber");
		/*	String jpql3="FROM TargetResult as o where o.season = '"+season+"' and o.ArchCode='"+archCode.substring(0, 7)+"'";
			List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
			*/TargetResult t = new TargetResult();
			int count=0;
			for(int i=0;i<indexcode.length;i++){
				for(int j=0;j<objectCode.length ;j++){
					TargetResult targetResult = new TargetResult();
					targetResult.setArchCode(archCode.trim());
					targetResult.setSeason(season.trim());
					targetResult.setIndexCode(indexcode[i].trim());
					targetResult.setObjectCode(objectCode[j].trim());
					long id=TargetService.getResultIDByObj(targetResult);
					targetResult.setScore(scoreValue[count++]);
					if(id!=-1){
						targetResult.setID(id);
						 t = systemService.findEntityById(id, TargetResult.class);
						 targetResult.setPlanValue(t.getPlanValue());
						 targetResult.setRealValue(t.getRealValue());
						//MyBeanUtils.copyBeanNotNull2Bean(targetResult, t);
						//targetResult.setPlanValue(planValue);
					}
					
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
			mav.setViewName("targetdatamanage/data_input/score_data_input");
			return mav;
			
		}
		
}
