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

import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.target.domain.ObjIndexArchUser;
import edu.cqu.ncycoa.target.domain.ObjIndexItem;
import edu.cqu.ncycoa.target.domain.TargetResult;
import edu.cqu.ncycoa.target.service.TargetService;

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
				if(archcode.startsWith("C"))
					mav.setViewName("targetdatamanage/data_input/complete_data_input");
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
		 * 查询计划对象
		 * @throws IOException 
		 * */
	
		@RequestMapping(params="getplanobj1",produces="text/html;charset=UTF-8")
		public void getplanObj1(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("utf-8");
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
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\" "+str+" \">',";
				        				  k++;
				        			  }else {
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"  \">',";
				        			  }
		        			  }else{
		        				  if(i==12&&timecol==12){
		        					  if(objs_res.size()!=0){
		        						  String str=objs_res.get(k).getPlanValue();
				        				  if(str == null || str.equals(""))
				        					  str="";
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\" "+str+" \">'}";
				        				  k++;
				        			  }else {
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"  \">'}";
				        			  }
		        				  }else{
		        					  if(objs_res.size()!=0){
		        						  String str=objs_res.get(k).getPlanValue();
				        				  if(str == null || str.equals(""))
				        					  str="";
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\" "+str+" \">'},";
				        				  k++;
				        			  }else {
				        				  url+="'"+j+"':'<input name=\"plannumber\" style=\"width:90px;\" type=\"text\" value=\"  \">'},";
				        			  }
		        				  }
		        				  
		        			  }
		        		  }
		        	  }
		          
		          url+="]}";
		          jsonObject=new JSONObject();
		          jsonObject.put("url", url);
		          jsonArray.add(jsonObject);
		        PrintWriter out=response.getWriter();
		        out.print(jsonArray);
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
		@SuppressWarnings("null")
		@RequestMapping(params="getcompleteobjbytype1")
		public void getcompleteobjbyType1(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("utf-8");
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
				sb.append("<tr><td><input  type=\"hidden\" value=\""+item.getIndexCode()+"\" name=\"indexCode\">"+item.getIndexName()+"</td>");
				for(ObjIndexArchUser para : objs){
					//sb.append("<td>"++"</td>");
					//if(para.getObjectcode()==)
					sb.append("<td><span style=\"width:50px;\">计划值</span>");
					int m=0;
					for(int k=0;k<tr.size();k++){
						if(tr.get(k).getIndexCode().equals(item.getIndexCode()) && tr.get(k).getObjectCode().equals(para.getObjectcode())){
							m=1;
							if("".equals(tr.get(k).getPlanValue()) || tr.get(k).getPlanValue()==null)
								sb.append(" <input type=\"text\" style=\"width:100px;\"  value=\"没有计划值\">");
							else {
								sb.append(" <input type=\"text\" style=\"width:100px;\"  value=\""+tr.get(k).getPlanValue()+"\">");
								
							}
							if("".equals(tr.get(k).getRealValue()) || tr.get(k).getRealValue()==null)
								sb.append("</div><div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\" name=\"completenumber\" value=\"\" style=\"width:100px;\"></div></td>");	
							
							else{
								sb.append("</div><div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\" name=\"completenumber\" value=\""+tr.get(k).getRealValue()+"\" style=\"width:100px;\"></div></td>");	
								
							}
							
						}
						
						
					}
					if(m==0){
						sb.append(" <input type=\"text\" style=\"width:100px;\" value=\" 没有计划值\">");
						sb.append("</div><div><span style=\"width:50px;\"> 完成值</span> <input type=\"text\" name=\"completenumber\" style=\"width:100px;\"></div></td>");	
						
					}
					
				}
				sb.append("</tr>");
				
				
			}
			sb.append("</tbody></table><div>");
			for (ObjIndexArchUser para : objs) {
				sb.append("<input  type=\"hidden\" value=\""+para.getObjectcode()+"\" name=\"objectcode\">");
			}
			sb.append("</div>");
			sb.append("<div style=\"margin-left:235px;margin-top:20px;\"><input  type=\"hidden\" value=\""+types+"\" name=\"season\">"
					+ "<input  type=\"hidden\" value=\""+archcode+"\" name=\"archCode\">"
					+ "<input id=\"submit\" type=\"submit\" value=\"提交\" class=\"btn1\"></div>");
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();   
			 jsonObject=new JSONObject();
			jsonObject.put("table",sb.toString());
			jsonArray.add(jsonObject);
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
			String message="";
			String season=request.getParameter("season");
			String archCode=request.getParameter("archCode");
			String[] objectCode=request.getParameterValues("objectcode");
			String[] indexcode=request.getParameterValues("indexCode");
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
		
		/**
		 * 查询得分对象
		 * @throws IOException 
		 * */
		@RequestMapping(params="getscoreobjbytype1")
		public void getscoreobjbyType1(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("utf-8");
			//在数据库查询历史数据
			//  String types=request.getParameter("type");
			  String archcode=request.getParameter("archcode").trim();
			//  String type=types.substring(0,1);
			  List<ObjIndexItem> indexs = null;
		      List<ObjIndexArchUser> objs = null;
		      ArrayList<Comlpetedata> cdlist = new ArrayList<Comlpetedata>();
		      String jpql2="FROM ObjIndexItem as o where   o.ParentIndexCode='"+archcode+"'" ;
		      indexs=systemService.readEntitiesByJPQL(jpql2, ObjIndexItem.class);//指标名
	          jpql2="FROM ObjIndexArchUser as o where  o.IndexArchCode='"+archcode+"'" ;
	          objs=systemService.readEntitiesByJPQL(jpql2, ObjIndexArchUser.class);//对象名
	        //查询是否有记录
	          String jpql3="FROM TargetResult as o where  o.ArchCode='"+archcode+"' order by o.season,o.objectCode";
	          List<TargetResult> objs_res=systemService.readEntitiesByJPQL(jpql3, TargetResult.class);
			 for(TargetResult item:objs_res){
				  Comlpetedata cd = new Comlpetedata();
				  cd.setIndexcode(item.getIndexCode());
				  cd.setObjcode(item.getObjectCode());
				  for(ObjIndexItem para:indexs){
					  if(para.getIndexCode().equals(cd.getIndexcode())){
						  cd.setIndexname(para.getIndexName());
					  }
				  }
				  for(ObjIndexArchUser para:objs){
					  if(para.getObjectcode().equals(cd.getObjcode())){
						  cd.setObjname(para.getUniIndexCode());
					  }
				  }
				  cd.setPlanvalue(item.getPlanValue());
				  cd.setCompletevalue(item.getRealValue());
				  cd.setTime(item.getSeason());
				  cdlist.add(cd);
			  }
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
		    	  sb.append("<tr><td><input  type=\"hidden\" value=\""+item.getIndexCode()+"\" name=\"indexCode\">"+item.getIndexName()+"</td>");
		    	  for (Comlpetedata para : cdlist) {
		    		 if(para.getIndexcode().equals(item.getIndexCode())){
		    			 sb.append("<td><input  type=\"hidden\" value=\""+para.getTime()+"\" name=\"season\">"+para.getTime()+"</td>");
				    	 
		    		 }
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
		    	     	  
		    	  sb.append("</tr>");  	 
		    	   
		    	   
		      }
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
		@RequestMapping(params="savescoreobj")
		public ModelAndView savescoreObj(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String message="";
			String[] season=request.getParameterValues("season");
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
				//	targetResult.setSeason(season.trim());
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
