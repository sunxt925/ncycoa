package edu.cqu.ncycoa.target.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.sql.visitor.functions.If;

import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.target.domain.ObjIndexArchUser;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.domain.TargetResult;
import edu.cqu.ncycoa.target.service.TargetService;
import edu.cqu.ncycoa.util.JavaScript;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/datainput")
public class DataInputController {
	@Resource(name="systemService")
	SystemService systemService;

	@RequestMapping(params="planinput")
	public ModelAndView planInput(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String type = request.getParameter("class");
		mav.addObject("type", type);
		System.out.println(type);
		//items筛选出公司类体系
		mav.setViewName("targetdatamanage/data_input/plan_data_input");		
		return mav;
	}
	@RequestMapping(params="completeinput")
	public ModelAndView compelteInput(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String type = request.getParameter("class");
		mav.addObject("type", type);
		System.out.println("data");
		//items筛选出公司类体系
		mav.setViewName("targetdatamanage/data_input/complete_data_input");		
		return mav;
	}
	@RequestMapping(params="scoreinput")
	public ModelAndView scoreinput(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String type = request.getParameter("class");
		mav.addObject("type", type);
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
			
				mav.setViewName("targetdatamanage/data_input/plan_data_input");
			
			return mav;
		}
		/**
		 * 查询指标
		 * */
		//选择年份，季度，指标体系之后：
			@RequestMapping(params="getcompleteTarget")
			public ModelAndView getCompleteList(String archcode,HttpServletRequest request, HttpServletResponse response){
//				String year=request.getParameter("year");
//				String season=request.getParameter("season");
//				String archcode=request.getParameter("archcode");
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
					mav.setViewName("targetdatamanage/data_input/complete_data_input");
				
				return mav;
			}
		/**
		 * 查询计划对象
		 * @throws IOException 
		 * */
	
		@RequestMapping(params="getplanobj")
		public void getplanObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
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
		 * 查询计划对象
		 * @throws IOException 
		 * */
	
		@RequestMapping(params="getplanobj1")
		public void getplanObj1(HttpServletRequest request, HttpServletResponse response) throws IOException{
			//response.setCharacterEncoding("gb2312");
			//需要从数据库里面读取的数据
			//需要对象名,对象名只跟体系有关
			 
			 String archcode=request.getParameter("archcode").trim();
			 String jpql2="FROM ObjIndexArchUser as o where o.IndexArchCode='"+archcode.substring(0, 7)+"' order by o.objectcode";
		     List<ObjIndexArchUser> objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
		     //需要历史数据
		     String indexname=request.getParameter("indexname");
		     
		     String indexCode=request.getParameter("indexCode");
		  //   System.out.println("=============="+indexCode);
		     String season = "";
		    
		     int timecol=0;
		     if(indexname.contains("月度")){season="M"; timecol=12;}
		     else if(indexname.contains("季度")){season="S"; timecol=4;}
		     else if(indexname.contains("半年")){season="H"; timecol=2;}
		     else if(indexname.contains("一年")){season="Y";timecol=2;}
		     else{season="D";}
		     String jpql3="FROM TargetResult as o where o.season LIKE '"+season+"%' and  o.ArchCode='"+archcode.substring(0, 7)+"' order by o.season,o.objectCode";
		      List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);  
		     //拼凑列
		      String colum="[[{field:'itemid',title:'时间段',width:80},";
		      int count=0;
		      for (ObjIndexArchUser item:objs) {
		          if(count==objs.size()-1){
		            colum+=" {field:'"+count+"',title:'"+item.getUniIndexCode()+""
		            		+ "<input type=\"hidden\" name=\"objcode\" value=\""+item.getObjectcode()+"\">"
		            				+ "<input type=\"hidden\" name=\"indexCode\" value=\""+indexCode+"\">"
		            						+ "<input type=\"hidden\" name=\"archCode\" value=\""+archcode+"\">',width:150}]]"; 
		          }
		          else{
		            colum+="{field:'"+count+"',title:'"+item.getUniIndexCode()+""
		            		+ "<input type=\"hidden\" name=\"objcode\" value=\""+item.getObjectcode()+"\">',width:150},";
		          }
		          count++;
		        }
		      	JSONArray jsonArray=new JSONArray();
		      	JSONObject jsonObject=new JSONObject();
		        jsonObject=new JSONObject();
		        jsonObject.put("colum", colum);
		        jsonArray.add(jsonObject);
			//填充数据
		        String url="{'total':";
			     String time=""; 
			     url+=timecol+",'rows':[";
		          for(int i=1,k=0;i<=timecol;i++){
		        	 
		        	  if(timecol==12){
		        		  //时间段列
		        		  if(i<10){
		                      time="M0"+i;
		                      url+="{'itemid':'<input type=\"hidden\" name=\"time\" value=\""+time+"\">"+time+"',";
		                    }
		                    else{
		                      time="M"+i;
		                      url+="{'itemid':'<input type=\"hidden\" name=\"time\" value=\""+time+"\">"+time+"',";
		                    }
		        	  }else if(timecol==4){
		        		//时间段列
		        		
		                      time="S0"+i;
		                      url+="{'itemid':'<input  type=\"hidden\" name=\"time\" value=\""+time+"\">"+time+"',";
		                    
		                   
		        	  }
		        		  //数据列
		        		  for(int j=0;j<count;j++){
		        			  if(j!=count-1){
				        			  if(objs_res.size()!=0){
				        				  String str=objs_res.get(k).getPlanValue();
				        				  if(str == null || str.equals(""))
				        					  str="";
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\""+str+"\">',";
				        				  k++;
				        			  }else {
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"\">',";
				        			  }
		        			  }else{
		        				  if(i==12&&timecol==12){
		        					  if(objs_res.size()!=0){
		        						  String str=objs_res.get(k).getPlanValue();
				        				  if(str == null || str.equals(""))
				        					  str="";
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\""+str+"\">'}";
				        				  k++;
				        			  }else {
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"\">'}";
				        			  }
		        				  }else{
		        					  if(objs_res.size()!=0){
		        						  String str=objs_res.get(k).getPlanValue();
				        				  if(str == null || str.equals(""))
				        					  str="";
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\""+str+"\">'},";
				        				  k++;
				        			  }else {
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"\">'},";
				        			  }
		        				  }
		        				  
		        			  }
		        		  }
		        	  }
		          
		          url+="]}";
		          jsonObject=new JSONObject();
		          jsonObject.put("url", url);
		          jsonArray.add(jsonObject);
		          response.setCharacterEncoding("gb2312");

					response.setContentType("text/plain;charset=gb2312");
					response.setHeader("Cache-Control", "no-store");
		        PrintWriter out=response.getWriter();
		        out.print(jsonArray);
		        out.flush();
		        out.close();
		        
		        
			
		}
		@RequestMapping(params="getplanobj2")
		public void getplanObj2(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//需要的数据；
			 String archcode=request.getParameter("archcode").trim();
			 String jpql2="FROM ObjIndexArchUser as o where o.IndexArchCode='"+archcode.substring(0, 7)+"' order by o.objectcode";
		     List<ObjIndexArchUser> objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
		     //需要历史数据
		     String season="";
		     int timecol=0;
		     String indexname=request.getParameter("indexname");
		     String indexCode=request.getParameter("indexCode");
		     if(indexname.contains("月度")){season="M"; timecol=12;}
		     else if(indexname.contains("季度")){season="S"; timecol=4;}
		     else if(indexname.contains("半年度")){season="H"; timecol=2;}
		     else if(indexname.contains("年度")){season="Y";timecol=2;}
		     else{season="D";}
		     //历史数据
		     String jpql3="FROM TargetResult as o where o.season LIKE '"+season+"%' and  o.ArchCode='"+archcode.substring(0, 7)+"' order by o.season,o.objectCode";
		     List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);  
		     
		   //拼列
			 StringBuffer sb = new StringBuffer();
			sb.append("<table id=\"plan_tb\" class=\"easyui-datagrid\" style=\"width:700px;height:250px;\" data-options=\"singleSelect:true,onClickRow:onClickRow\">");
			sb.append("<thead data-options=\"frozen:true\"><tr>");
			sb.append("<th data-options=\"field:'time'\">时间段</th></tr></thead>");
			sb.append("<thead><tr>");
			//sb.append("<th data-options=\"field:'objcode',hidden:true\"></th>");
			for (ObjIndexArchUser para : objs) {
				sb.append("<th data-options=\"field:'para_").append(para.getObjectcode()).append("',width:160,align:'right',editor:{type:'numberbox',options:{precision:2}}\">");
				sb.append(para.getUniIndexCode()).append("</th>");
			}
			sb.append("</tr></head><tbody>");
			//拼行
			String[] months={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
			String[] seasons={"一季度","二季度","三季度","四季度"};
			String[] houryear={"上半年","下半年"};
			int count=0;
			if(season.equals("M")){
				for(int i=0;i<12;i++){
					sb.append("<tr><td> <input  type=\"hidden\" name=\"time\" value=\"M"+i+"\" >"+months[i]+"</td>");
					
					for(ObjIndexArchUser para : objs){
						if(objs_res.size()!=0) {
							sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\""+objs_res.get(count++).getPlanValue()+"\"></td>");
						}else {
							sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"\"></td>");

						}
					}
					sb.append("</tr>");
					
				}
			}else if(season.equals("S")) {
				for(int i=0;i<4;i++){
					sb.append("<tr><td> <input  type=\"hidden\" name=\"time\" value=\"S"+i+"\">"+seasons[i]+"</td>");
					
					for(ObjIndexArchUser para : objs){
						if(objs_res.size()!=0) {
							sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\""+objs_res.get(count++).getPlanValue()+"\"></td>");
						}else {
							sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"\"></td>");

						}
					}
					sb.append("</tr>");
					
				}

			}else if(season.equals("H")) {
				for(int i=0;i<2;i++){
					sb.append("<tr><td> <input  type=\"hidden\" name=\"time\" value=\"S"+i+"\" >"+houryear[i]+"</td>");
					
					for(ObjIndexArchUser para : objs){
						if(objs_res.size()!=0) {
							sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\""+objs_res.get(count++).getPlanValue()+"\"></td>");
						}else {
							sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"\"></td>");

						}
					}
					sb.append("</tr>");
					
				}

			}else if(season.equals("Y")) {
				sb.append("<tr><td> <input  type=\"hidden\" name=\"time\" value=\"Y01\" >全年</td>");
				
				for(ObjIndexArchUser para : objs){
					if(objs_res.size()!=0) {
						sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\""+objs_res.get(count++).getPlanValue()+"\"></td>");
					}else {
						sb.append("<td><input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"\"></td>");

					}
				}
				sb.append("</tr>");

			}else {

			}
			sb.append("</tbody></table><div>");
			for (ObjIndexArchUser para : objs) {
				sb.append("<input  type=\"hidden\" value=\""+para.getObjectcode()+"\" name=\"objcode\">");
				//sb.append("<input  type=\"hidden\" value=\""+para.getUniIndexCode()+"\" name=\"objectname\">");
				}
			
				sb.append("<input  type=\"hidden\" value=\""+indexCode+"\" name=\"indexCode\">");
				sb.append("<input  type=\"hidden\" value=\""+indexname+"\" name=\"indexName\">");
			
			sb.append("</div>");
			sb.append("<div style=\"margin-left:235px;margin-top:20px;\">"
					+ "<input  type=\"hidden\" value=\""+archcode+"\" name=\"archCode\">"
					+ "<input id=\"submit\" type=\"button\" value=\"提交\" class=\"btn1\" onclick=\"saveplan();\" ></div>");
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();   
			 jsonObject=new JSONObject();
			jsonObject.put("table",sb.toString());
			jsonArray.add(jsonObject);
			
			response.setCharacterEncoding("gb2312");
			response.setContentType("text/plain;charset=gb2312");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out=response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
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
				for(int i=0;i<time.length/2;i++){
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
				//mav.setViewName("datainput.htm?getplanTarget");
				mav.setViewName("targetdatamanage/data_input/plan_data_input");
				return mav;
				
			}
		/**
		 * 查询完成对象
		 * @throws IOException 
		 * */
		@RequestMapping(params="getcompleteobjbytype")
		public void getcompleteobjbyType(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("gb2312");
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
		@SuppressWarnings("null")
		@RequestMapping(params="getcompleteobjbytype1")
		public void getcompleteobjbyType1(HttpServletRequest request, HttpServletResponse response) throws IOException{
			//response.setCharacterEncoding("gb2312");
			//需要的数据
			String types=request.getParameter("type");
			String type=types.substring(0, 1);
			String archcode=request.getParameter("archcode").trim();
			List<ObjIndexItem> indexs = null;
			List<ObjIndexArchUser> objs = null;
			List<TargetResult> tr = null;
		
			String jpql2="FROM ObjIndexItem as o where o.ScorePeriod LIKE '"+type+"%' and  o.ParentIndexCode='"+archcode+"'" ;
			//指标，行
			indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);
			 jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
			 //对象，列
			 objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);
			 //历史数据
			 jpql2="FROM TargetResult as o where o.season = '"+types+"' and o.ArchCode='"+archcode+"' order by o.season,o.objectCode";
			 tr=systemService.readEntitiesByJPQL(jpql2, TargetResult.class);
			 
		
			//拼列
			 StringBuffer sb = new StringBuffer();
			sb.append("<table id=\"compl_tb\" class=\"easyui-datagrid\" style=\"width:700px;height:250px;\" data-options=\"singleSelect:true,onClickRow:onClickRow\">");
			sb.append("<thead data-options=\"frozen:true\"><tr>");
			sb.append("<th data-options=\"field:'indexname'\">指标</th></tr></thead>");
			sb.append("<thead><tr>");
			//sb.append("<th data-options=\"field:'objcode',hidden:true\"></th>");
			for (ObjIndexArchUser para : objs) {
				sb.append("<th data-options=\"field:'para_").append(para.getObjectcode()).append("',width:160,align:'right',editor:{type:'numberbox',options:{precision:2}}\">");
				sb.append(para.getUniIndexCode()).append("</th>");
			}
			sb.append("</tr></thead><tbody>");
			for(ObjIndexItem item:indexs){
				sb.append("<tr><td><input  type=\"hidden\" value=\""+item.getValueFunc()+"\" name=\"valuefunc\"><input  type=\"hidden\" value=\""+item.getExamFlag()+"\" name=\"examflag\"><input  type=\"hidden\" value=\""+item.getExamTime()+"\" name=\"examtime\">"+item.getIndexName()+"</td>");
				for(ObjIndexArchUser para : objs){
					//sb.append("<td>"++"</td>");
					//if(para.getObjectcode()==)
					sb.append("<td><span style=\"width:50px;\">计划值</span>");
					int m=0;
					for(int k=0;k<tr.size();k++){
						if(tr.get(k).getIndexCode().equals(item.getIndexCode()) && tr.get(k).getObjectCode().equals(para.getObjectcode())){
							m=1;
							if("".equals(tr.get(k).getPlanValue()) || tr.get(k).getPlanValue()==null)
								sb.append(" <input type=\"text\" name=\"planvalue\" style=\"width:100px;\"  value=\"没有计划值\">");
							else {
								sb.append(" <input type=\"text\" name=\"planvalue\" style=\"width:100px;\"  value=\""+tr.get(k).getPlanValue()+"\">");
								
							}
							if("".equals(tr.get(k).getRealValue()) || tr.get(k).getRealValue()==null)
								sb.append("</div><div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\" name=\"completenumber\" value=\"\" style=\"width:100px;\"></div></td>");	
							
							else{
								sb.append("</div><div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\" name=\"completenumber\" value=\""+tr.get(k).getRealValue()+"\" style=\"width:100px;\"></div></td>");	
								
							}
							
						}
						
						
					}
					if(m==0){
						sb.append(" <input type=\"text\" name=\"planvalue\" style=\"width:100px;\" value=\" 没有计划值\">");
						sb.append("</div><div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\" name=\"completenumber\" style=\"width:100px;\"></div></td>");	
						
					}
					
				}
				sb.append("</tr>");
				
				
			}
			sb.append("</tbody></table><div>");
			for (ObjIndexArchUser para : objs) {
				sb.append("<input  type=\"hidden\" value=\""+para.getObjectcode()+"\" name=\"objectcode\">");
				sb.append("<input  type=\"hidden\" value=\""+para.getUniIndexCode()+"\" name=\"objectname\">");
				}
			for(ObjIndexItem item:indexs){
				sb.append("<input  type=\"hidden\" value=\""+item.getIndexCode()+"\" name=\"indexCode\">");
				sb.append("<input  type=\"hidden\" value=\""+item.getIndexName()+"\" name=\"indexName\">");
			}
			sb.append("</div>");
			sb.append("<div style=\"margin-left:235px;margin-top:20px;\"><input  type=\"hidden\" value=\""+types+"\" name=\"season\">"
					+ "<input  type=\"hidden\" value=\""+archcode+"\" name=\"archCode\">"
					+ "<input id=\"submit\" type=\"button\" value=\"提交\" class=\"btn1\" onclick=\"savecompete();\" ></div>");
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();   
			 jsonObject=new JSONObject();
			jsonObject.put("table",sb.toString());
			jsonArray.add(jsonObject);
			
			response.setCharacterEncoding("gb2312");
			response.setContentType("text/plain;charset=gb2312");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out=response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		}
	/**
	 * 保存完成值
	 * */
		@RequestMapping(params="savecomplateobj")
		public ModelAndView savecomplateObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			//request.setCharacterEncoding("gb2312");
			
			
		//	String nameString = new String(request.getParameter("name").getBytes("ISO-8859-1"),"gb2312");
			
			String message="";
			String season=request.getParameter("season");
			String archCode=request.getParameter("archCode");
			String[] objectCode=request.getParameterValues("objectcode");
			String[] objectName=request.getParameterValues("objectname");
			
			String[] examflag=request.getParameterValues("examflag");
			String[] examtime=request.getParameterValues("examtime");
			String[] indexcode=request.getParameterValues("indexCode");
			String[] indexname=request.getParameterValues("indexName");
			String[] completeValue = request.getParameterValues("completenumber");
			String[] planValue = request.getParameterValues("planvalue");
			String[] valueFunc = request.getParameterValues("valuefunc");
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
					targetResult.setIndexName(new String(indexname[i].getBytes("ISO-8859-1"),"gb2312"));
					targetResult.setObjectCode(objectCode[j].trim());
					targetResult.setObjName(new String(objectName[j].getBytes("ISO-8859-1"),"gb2312"));
					if(examtime[i]!=null&&examtime[i].trim().equals(season.trim()))
						targetResult.setExamFlag(examflag[i].trim());
					else {
						targetResult.setExamFlag("0");
					}
					long id=TargetService.getResultIDByObj(targetResult);
					targetResult.setRealValue(completeValue[count++]);
					//判断是否报警 by SXT
					if(valueFunc[count-1]!=null&&valueFunc[count-1]!=""){
						if(JavaScript.isTrueOrFalse(valueFunc[count-1],planValue[count-1],completeValue[count-1])){
						//if(JavaScript.isTrueOrFalse("P-R>5","10","4")){
							System.out.println("该指标报警");
							targetResult.setAlarmFlag("1");
						}else{
							targetResult.setAlarmFlag("0");
						}
					}
					//
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
			//response.setCharacterEncoding("gb2312");
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
		
		/**
		 * 查询得分对象
		 * @throws IOException 
		 * */
		@RequestMapping(params="getscoreobjbytype1")
		public void getscoreobjbyType1(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("gb2312");
			//在数据库查询历史数据
			//  String types=request.getParameter("type");
			  String archcode=request.getParameter("archcode").trim();
			//  String type=types.substring(0,1);
			  List<ObjIndexItem> indexs = null;
		      List<ObjIndexArchUser> objs = null;
		     
		      String jpql2="FROM ObjIndexItem as o where o.examFlag='1' and   o.ParentIndexCode='"+archcode+"'" ;
		      indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);//指标名
	          jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
	          objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);//对象名
	        //查询是否有记录
	          String jpql3="FROM TargetResult as o where o.examFlag='1' and  o.ArchCode='"+archcode+"' order by o.season,o.objectCode";
	          List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
			  //拼表格，拼列
			  StringBuffer sb = new StringBuffer();
		      sb.append("<table id=\"score_tb\" class=\"easyui-datagrid\" style=\"width:700px;height:250px;\" data-options=\"singleSelect:true,onClickRow:onClickRow\">");
		      sb.append("<thead data-options=\"frozen:true\"><tr>");
		      sb.append("<th data-options=\"field:'indexname'\">指标</th>"
		      		+ "<th data-options=\"field:'time'\">时间段</th></tr></thead>");
		      sb.append("<thead><tr>");
		      //sb.append("<th data-options=\"field:'objcode',hidden:true\"></th>");
		      for (ObjIndexArchUser para : objs) {
		        sb.append("<th data-options=\"field:'para_").append(para.getObjectcode()).append("',width:160,align:'right',editor:{type:'numberbox',options:{precision:2}}\">");
		        sb.append(para.getUniIndexCode()).append("</th>");
		      }
		      sb.append("</tr></thead><tbody>");
		      for (ObjIndexItem item:indexs) {
		    	  if(item.getExamTime().contains("00")){ 

		    	  }else {
		    		  sb.append("<tr><td><input  type=\"hidden\" value=\""+item.getIndexCode()+"\" name=\"indexCode\">"+item.getIndexName()+"</td>");
		    		  for(TargetResult tr:objs_res){
		    			  if(tr.getIndexCode().equals(item.getIndexCode()) && tr.getSeason().equals(item.getExamTime())){
		    				  sb.append("<td><input  type=\"hidden\" value=\""+item.getExamTime()+"\" name=\"indexCode\">"+item.getExamTime()+"</td>");
		    				  for(ObjIndexArchUser para:objs){
		    				  sb.append("<td><div align=\"right\"><span>计划值</span><input type=\"text\" value=\""+tr.getPlanValue()+"\" style=\"width:100px;\"></div></td>");
		    				  sb.append("<div align=\"right\"><span>完成值</span><input type=\"text\" value=\""+tr.getRealValue()+"\" style=\"width:100px;\"></div>");
		    				  sb.append("<div align=\"right\"><span>得分</span><input type=\"text\" name=\"score\" value=\"\" style=\"width:100px;\"></div></td>"); 
		    				  }	
		    			  }
		    		  }

		    	  }
		    	  sb.append("</tr>");  	 
		      }
		    	 /* for(ObjIndexArchUser para : objs){
		    		  int m=0;
		    		  sb.append("<td><div><span style=\"width:50px;\">计划值</span>");
		    		  for(Comlpetedata cd : cdlist){
		    			  if(cd.getIndexcode().equals(item.getIndexCode()) && cd.getObjcode().equals(para.getObjectcode())){
		    				  sb.append(" <input type=\"text\" style=\"width:100px;\"  value=\""+cd.getPlanvalue()+"\"></div>");
		    				  sb.append("<div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\"  value=\""+cd.getCompletevalue()+"\" style=\"width:100px;\"></div>"); 
			    			   sb.append("<div><span style=\"width:50px;\"> 得分</span> <input type=\"text\" name=\"scorenumber\" value=\"\" style=\"width:100px;\"></div></td>"); 
			    		  m=1;
		    			  }
		    		  }
		    		if(m==0){
		    			  sb.append(" <input type=\"text\" style=\"width:100px;\"  value=\"\"></div>");
	    				  sb.append("<div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\" value=\"\" style=\"width:100px;\"></div>"); 
		    			   sb.append("<div><span style=\"width:50px;\"> 得分</span> <input type=\"text\" name=\"scorenumber\" value=\"\" style=\"width:100px;\"></div></td>"); 
		    		
		    		}  
		    	  }*/
		    	     	  
		    	
		    	   
		    	   
		      
		      sb.append("</tbody></table>");
		     /* for (Comlpetedata para : cdlist) {
		          sb.append("<input  type=\"hidden\" value=\""+para.getObjcode()+"\" name=\"objectcode\">");
		        }
		        sb.append("</div>");
		        sb.append("<div style=\"margin-left:235px;margin-top:20px;\">"
		            + "<input  type=\"hidden\" value=\""+archcode+"\" name=\"archCode\">"
		            + "<input id=\"submit\" type=\"submit\" value=\"提交\" class=\"btn1\"></div>");*/
		        JSONArray jsonArray=new JSONArray();
		        JSONObject jsonObject=new JSONObject();   
		         jsonObject=new JSONObject();
		        jsonObject.put("table",sb.toString());
		        jsonArray.add(jsonObject);
		        PrintWriter out=response.getWriter();
		        out.print(jsonArray.toString());
		        out.flush();
		        out.close();
			return;
		}
		@RequestMapping(params="getscoreobjbytype2")
		public void getscoreobjbyType2(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("gb2312");
			  String archcode=request.getParameter("archcode").trim();
			 String jpql3="FROM TargetResult as o where o.examFlag='1' and  o.ArchCode='"+archcode+"' order by o.season,o.objectCode";
	         List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
	       //拼表格，拼列
			  StringBuffer sb = new StringBuffer();
		      sb.append("<table id=\"score_tb\"  style=\"width:700px;height:250px;\" data-options=\"singleSelect:true,onClickRow:onClickRow\">");
		      sb.append("<thead data-options=\"frozen:true\"><tr>");
		      sb.append("<th data-options=\"field:'indexname'\">指标</th>"
		      		+ "<th data-options=\"field:'objs'\">对象名</th>"
		      		+ "<th data-options=\"field:'time'\">时间段</th>"
		      		+ "<th data-options=\"field:'planvalue'\">计划值</th>"
		      		+ "<th data-options=\"field:'completevalue'\">完成值</th>"
		      		+ "<th data-options=\"field:'score'\">得分</th></tr></thead>");
		      sb.append("<tbody>");
		      for(TargetResult tr:objs_res){
		    	  if(tr.getExamFlag().equals("1")){
		    		  sb.append("<tr><td> "+tr.getIndexName()+"</td>");
		    		  sb.append("<td>"+tr.getObjName()+"</td>");
		    		  sb.append("<td>"+tr.getSeason()+"</td>");
		    		  sb.append("<td>"+tr.getPlanValue()+"</td>");
		    		  sb.append("<td>"+tr.getRealValue()+"</td>");
		    		  if(tr.getScore()!=null){
		    		  sb.append("<td><input type=\"text\" name=\"score\" style=\"width:100px;\" value=\""+tr.getScore()+"\"></td></tr>");
		    		  }else{
		    			  sb.append("<td><input type=\"text\" name=\"score\" style=\"width:100px;\" value=\"\"></td></tr>");
				    		   
		    		  }
		    	  }
		      }
		      sb.append("</tbody></table><div>");
		      
		      for(TargetResult tr:objs_res){
		    	  sb.append("<input  type=\"hidden\" value=\""+tr.getID()+"\" name=\"id\">");
		    	  sb.append("<input  type=\"hidden\" value=\""+tr.getIndexCode()+"\" name=\"indexCode\">");
		    	  sb.append("<input  type=\"hidden\" value=\""+tr.getObjectCode()+"\" name=\"objCode\">");
		    	  sb.append("<input  type=\"hidden\" value=\""+tr.getSeason()+"\" name=\"season\">");
		    	
			       
		      }
		      sb.append("</div><div style=\"margin-left:235px;margin-top:20px;\">"
			            + "<input  type=\"hidden\" value=\""+archcode+"\" name=\"archCode\">"
			            + "<input id=\"submit\" type=\"button\" value=\"提交\" class=\"btn1\" onclick=\"savescore();\"></div>");
		      JSONArray jsonArray=new JSONArray();
		      JSONObject jsonObject=new JSONObject();   
		      jsonObject=new JSONObject();
		      jsonObject.put("table",sb.toString());
		      jsonArray.add(jsonObject);
		      response.setCharacterEncoding("gb2312");
				response.setContentType("text/plain;charset=gb2312");
				response.setHeader("Cache-Control", "no-store");
		      PrintWriter out=response.getWriter();
		      
		      out.print(jsonArray.toString());
		      out.flush();
		      out.close();
		      return;
		     
			
		}
		@RequestMapping(params="savescoreobj")
		public ModelAndView savescoreObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String message="";
			//String[] season=request.getParameterValues("season");
			String[] id=request.getParameterValues("id");
			//String archCode=request.getParameter("archCode");
			//String[] objectCode=request.getParameterValues("objCode");
			//String[] indexcode=request.getParameterValues("indexCode");
			String[] scoreValue = request.getParameterValues("score");
		/*	String jpql3="FROM TargetResult as o where o.season = '"+season+"' and o.ArchCode='"+archCode.substring(0, 7)+"'";
			List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
			*/TargetResult t = new TargetResult();
			int count=0;
			for(int i=0;i<id.length;i++){
				TargetResult targetResult = new TargetResult();
				targetResult=systemService.findEntityById(Long.parseLong(id[i]), TargetResult.class);
				targetResult.setScore(scoreValue[i]);
				/*for(int j=0;j<objectCode.length ;j++){*/
					/*TargetResult targetResult = new TargetResult();
					targetResult.setArchCode(archCode.trim());
					targetResult.setSeason(season[i].trim());
					targetResult.setIndexCode(indexcode[i].trim());
					targetResult.setObjectCode(objectCode[i].trim());
					long id=TargetService.getResultIDByObj(targetResult);
					targetResult.setScore(scoreValue[count++]);
					targetResult.setExamFlag("1");
					if(id!=-1){
						targetResult.setID(id);
						 t = systemService.findEntityById(id, TargetResult.class);
						 targetResult.setPlanValue(t.getPlanValue());
						 targetResult.setRealValue(t.getRealValue());
						//MyBeanUtils.copyBeanNotNull2Bean(targetResult, t);
						//targetResult.setPlanValue(planValue);
					}*/
					
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
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", message);	
			mav.setViewName("targetdatamanage/data_input/score_data_input");
			return mav;
			
		}
		
}
