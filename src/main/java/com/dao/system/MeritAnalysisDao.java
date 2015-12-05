package com.dao.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.CodeDictionary;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.index.IndexScoreDetial;
import com.entity.index.Indexitem;
import com.entity.system.Staff;

public class MeritAnalysisDao {

	Logger logger = Logger.getLogger(MeritAnalysisDao.class);
	/**
	 * 经常扣分项结果分析
	 * @param indexcode
	 * @param year
	 * @param month
	 * @return
	 */
    public String getDeductRes(String indexcode,String year,String month){
		int count = 0;
		List<IndexScoreDetial> indexScoreDetials = getIndexscoreDetials(getCompareDb(indexcode, year, month));
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("");
		if(getCompareDb(indexcode, year, month).getRowsCount() == 0){
			sBuilder.append("无查询结果返回");
		}else{
			sBuilder.append("<table class=\"easyui-datagrid\" data-options=\"fitColumns:true,singleSelect:true,collapsible:true\">");
			sBuilder.append("<thead><tr>");
		    sBuilder.append("<th data-options=\"field:'staffname',width:100\">姓名</th>");
		    sBuilder.append("<th data-options=\"field:'orgname',width:100\">所属部门</th>");
		    sBuilder.append("<th data-options=\"field:'scorevalue',width:100\">得分</th>");
		    
		    sBuilder.append("<th data-options=\"field:'standardscore',width:100\">标准分值</th>");
		    sBuilder.append("</tr></thead>");
		    if(indexScoreDetials != null){
		    	sBuilder.append("<tbody>");
		 	    for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
		 	    	sBuilder.append("<tr>");
		 	    	sBuilder.append("<td>").append(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", indexScoreDetial.getObjectCode())).append("</td>");
		 	    	sBuilder.append("<td>").append(CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname", new Staff(indexScoreDetial.getObjectCode()).getOrgcode())).append("</td>");
		 	    	
		 	    	if(indexScoreDetial.getIndexitem().getStandardscore() > indexScoreDetial.getScorevalue()){
		 	    		count++;
		 	    		sBuilder.append("<td>").append("<span style=\"color:red\">"+indexScoreDetial.getScorevalue()+"</span>").append("</td>");
			 	    }else{
			 	    	sBuilder.append("<td>").append("<span >"+indexScoreDetial.getScorevalue()+"</span>").append("</td>");
			 	    	
			 	    }
		 	    	
		 	    	sBuilder.append("<td>").append(indexScoreDetial.getIndexitem().getStandardscore()).append("</td>");
		 	    	sBuilder.append("</tr>");
		 	    }
		 	    sBuilder.append("</tbody>");
		    }
		   
		    sBuilder.append("</table>");
		    sBuilder.append("<span style=\"font-size: 13px\">指标项【"+indexScoreDetials.get(0).getIndexitem().getIndexName()+"】被扣分人数："+count+" 人</span>");
		}
		
		return sBuilder.toString();
	}
    public String getCompareRes(String indexcode,String year,String month){
    	return getCompareRes(indexcode,year,month,null);
    }
	/**
	 * 构造比较结果
	 * @param indexcode
	 * @param year
	 * @param month
	 * @return
	 */
	public String getCompareRes(String indexcode,String year,String month,String[] obj){
		
		List<IndexScoreDetial> indexScoreDetials = getIndexscoreDetials(getCompareDb(indexcode, year, month,obj));
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("");
		if(getCompareDb(indexcode, year, month).getRowsCount() == 0){
			sBuilder.append("无查询结果返回");
		}else{
			sBuilder.append("<span style=\"font-size: 13px\"> 平均得分："+getAverageScore(indexScoreDetials)+"</span>").append("<br>");
			sBuilder.append("<table class=\"easyui-datagrid\" data-options=\"fitColumns:true,singleSelect:true,collapsible:true\">");
			sBuilder.append("<thead><tr>");
		    sBuilder.append("<th data-options=\"field:'staffname',width:100\">姓名</th>");
		    sBuilder.append("<th data-options=\"field:'orgname',width:100\">所属部门</th>");
		    sBuilder.append("<th data-options=\"field:'scorevalue',width:100\">得分</th>");
		    
		    sBuilder.append("<th data-options=\"field:'standardscore',width:100\">标准分值</th>");
		    sBuilder.append("</tr></thead>");
		    if(indexScoreDetials != null){
		    	sBuilder.append("<tbody>");
		 	    for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
		 	    	sBuilder.append("<tr>");
		 	    	sBuilder.append("<td>").append(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", indexScoreDetial.getObjectCode())).append("</td>");
		 	    	sBuilder.append("<td>").append(CodeDictionary.syscode_traslate("base_org", "orgcode", "orgname", new Staff(indexScoreDetial.getObjectCode()).getOrgcode())).append("</td>");
		 	    	
		 	    	if(indexScoreDetial.getIndexitem().getStandardscore() > getAverageScore(indexScoreDetials)){
		 	    		sBuilder.append("<td>").append("<span style=\"color:red\">"+indexScoreDetial.getScorevalue()+"</span>").append("</td>");
			 	    }else{
			 	    	sBuilder.append("<td>").append("<span >"+indexScoreDetial.getScorevalue()+"</span>").append("</td>");
			 	    	
			 	    }
		 	    	
		 	    	sBuilder.append("<td>").append(indexScoreDetial.getIndexitem().getStandardscore()).append("</td>");
		 	    	sBuilder.append("</tr>");
		 	    }
		 	    sBuilder.append("</tbody>");
		    }
		   
		    sBuilder.append("</table>");
		}
		
		return sBuilder.toString();
	}
	/**
	 * 可视化展示
	 * @param indexcode
	 * @param year
	 * @param month
	 * @return
	 */
	public String getNameAndScore(String indexcode,String year,String month){
		List<IndexScoreDetial> indexScoreDetials = getIndexscoreDetials(getCompareDb(indexcode, year, month));
		StringBuilder sBuilder = new StringBuilder();
		String result="";
		sBuilder.append("");
		if(getCompareDb(indexcode, year, month).getRowsCount() == 0){
			sBuilder.append("无查询结果返回");
		}else{
			  if(indexScoreDetials != null){
		 	    for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
		 	    	sBuilder.append(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", indexScoreDetial.getObjectCode())).append(",");
		 	    }
		 	    result=sBuilder.substring(0, sBuilder.length()-1);
		 	    result+=";";
		 	   for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
		 		  result=result+indexScoreDetial.getScorevalue()+",";
		 	   }
		 	    result=result.substring(0, result.length()-1);
		 		//sBuilder.append("<td>").append(indexScoreDetial.getIndexitem().getStandardscore()).append("</td>");
		    }
		}
		System.out.println(result);
		return  result;
	}
	public float getStandSocre(String indexcode,String year,String month){
		List<IndexScoreDetial> indexScoreDetials = getIndexscoreDetials(getCompareDb(indexcode, year, month));
		float stand=(float)indexScoreDetials.get(0).getIndexitem().getStandardscore();
		return stand;
	}
	public float getAverageScoretoFront(String indexcode,String year,String month){
		List<IndexScoreDetial> indexScoreDetials = getIndexscoreDetials(getCompareDb(indexcode, year, month));
		if(indexScoreDetials == null || indexScoreDetials.size()==0){
			return 0;
		}
		float sum = 0;
		for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
			sum += indexScoreDetial.getScorevalue();
		}
		return sum / indexScoreDetials.size();
	}
	public double getAverageScore(List<IndexScoreDetial> indexScoreDetials){
		if(indexScoreDetials == null || indexScoreDetials.size()==0){
			return 0;
		}
		double sum = 0;
		for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
			sum += indexScoreDetial.getScorevalue();
		}
		return sum / indexScoreDetials.size();
	}
	public DataTable getCompareDb(String indexcode,String year,String month){
	 return getCompareDb(indexcode,year,month,null);	
	}
	public DataTable getCompareDb(String indexcode,String year,String month,String[] obj){
		try {
			String sql="";
			if(obj==null){
				sql = "select * from tbm_indexscoredetail where indexcode=? and scoreyear=? and scoreperiod=? order by objectcode";
			}else{
				StringBuilder sbBuilder = new StringBuilder();
				sbBuilder.append("");
				
				for(int i=0;i<obj.length;i++){
					List<String> staffs = StaffDao.getstafflistByorg(obj[i]);
					for(String s: staffs){
						sbBuilder.append("'"+s+"'").append(",");
					}
				}
				sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
				sql = "select * from tbm_indexscoredetail where indexcode=? and scoreyear=? and scoreperiod=? and objectcode in ("+sbBuilder.toString()+")  order by objectcode";
			}
			Parameter.SqlParameter[] pp = new  Parameter.SqlParameter[]{
				new Parameter.String(indexcode),
				new Parameter.String(year),
				new Parameter.String(month)
			};
			DBObject dbObject = new DBObject();
			return  dbObject.runSelectQuery(sql, pp);
			
		} catch (Exception e) {
			logger.error("查询得分明细出错");
			return null;
		}
	}
	public List<IndexScoreDetial> getIndexscoreDetials(DataTable dt){
		try {
			List<IndexScoreDetial> indexScoreDetials = new ArrayList<IndexScoreDetial>();
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row = null;
				for(int i = 0;i < dt.getRowsCount();i++){
					row = dt.get(i);
					IndexScoreDetial indexScoreDetial = new IndexScoreDetial();
					Indexitem indexitem = new Indexitem(row.getString("indexcode"));
					indexScoreDetial.setIndexitem(indexitem);
					indexScoreDetial.setIndexvalue(Float.parseFloat(row.getString("indexvalue")));
					indexScoreDetial.setMonth(row.getString("scoreperiod"));
					indexScoreDetial.setObjectCode(row.getString("objectcode"));
					indexScoreDetial.setYear(row.getString("scoreyear"));
					indexScoreDetial.setScorevalue(Float.parseFloat(row.getString("scorevalue")));
					indexScoreDetials.add(indexScoreDetial);
				}
				return indexScoreDetials;
				 
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		
	}
}
