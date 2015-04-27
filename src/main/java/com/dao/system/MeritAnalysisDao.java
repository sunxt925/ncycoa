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

public class MeritAnalysisDao {

	Logger logger = Logger.getLogger(MeritAnalysisDao.class);
	/**
	 * �����۷���������
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
			sBuilder.append("�޲�ѯ�������");
		}else{
			sBuilder.append("<table class=\"easyui-datagrid\" data-options=\"fitColumns:true,singleSelect:true,collapsible:true\">");
			sBuilder.append("<thead><tr>");
		    sBuilder.append("<th data-options=\"field:'staffname',width:100\">����</th>");
		    sBuilder.append("<th data-options=\"field:'scorevalue',width:100\">�÷�</th>");
		    
		    sBuilder.append("<th data-options=\"field:'standardscore',width:100\">��׼��ֵ</th>");
		    sBuilder.append("</tr></thead>");
		    if(indexScoreDetials != null){
		    	sBuilder.append("<tbody>");
		 	    for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
		 	    	sBuilder.append("<tr>");
		 	    	sBuilder.append("<td>").append(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", indexScoreDetial.getObjectCode())).append("</td>");
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
		    sBuilder.append("<span style=\"font-size: 13px\">ָ���"+indexScoreDetials.get(0).getIndexitem().getIndexName()+"�����۷�������"+count+" ��</span>");
		}
		
		return sBuilder.toString();
	}
	/**
	 * ����ȽϽ��
	 * @param indexcode
	 * @param year
	 * @param month
	 * @return
	 */
	public String getCompareRes(String indexcode,String year,String month){
		
		List<IndexScoreDetial> indexScoreDetials = getIndexscoreDetials(getCompareDb(indexcode, year, month));
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("");
		if(getCompareDb(indexcode, year, month).getRowsCount() == 0){
			sBuilder.append("�޲�ѯ�������");
		}else{
			sBuilder.append("<span style=\"font-size: 13px\"> ƽ���÷֣�"+getAverageScore(indexScoreDetials)+"</span>").append("<br>");
			sBuilder.append("<table class=\"easyui-datagrid\" data-options=\"fitColumns:true,singleSelect:true,collapsible:true\">");
			sBuilder.append("<thead><tr>");
		    sBuilder.append("<th data-options=\"field:'staffname',width:100\">����</th>");
		    sBuilder.append("<th data-options=\"field:'scorevalue',width:100\">�÷�</th>");
		    
		    sBuilder.append("<th data-options=\"field:'standardscore',width:100\">��׼��ֵ</th>");
		    sBuilder.append("</tr></thead>");
		    if(indexScoreDetials != null){
		    	sBuilder.append("<tbody>");
		 	    for(IndexScoreDetial indexScoreDetial : indexScoreDetials){
		 	    	sBuilder.append("<tr>");
		 	    	sBuilder.append("<td>").append(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", indexScoreDetial.getObjectCode())).append("</td>");
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
		try {
			String sql = "select * from tbm_indexscoredetail where indexcode=? and scoreyear=? and scoreperiod=? order by objectcode";
			Parameter.SqlParameter[] pp = new  Parameter.SqlParameter[]{
				new Parameter.String(indexcode),
				new Parameter.String(year),
				new Parameter.String(month)
			};
			DBObject dbObject = new DBObject();
			return  dbObject.runSelectQuery(sql, pp);
			
		} catch (Exception e) {
			logger.error("��ѯ�÷���ϸ����");
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