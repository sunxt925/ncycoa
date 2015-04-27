package com.entity.index;

import java.util.ArrayList;
import java.util.List;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class IndexScore {

	public static Double getScorevalue(String objectcode,String indexcode,String scoreyear,String scoreperiod){
		try {
			DBObject db=new DBObject();
			String sql="select scorevalue from tbm_indexscoredetail where objectcode=? and indexcode=? and scoreyear=? and scoreperiod=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
					{ new Parameter.String(objectcode),
					  new Parameter.String(indexcode),
					  new Parameter.String(scoreyear),
					  new Parameter.String(scoreperiod)
					};
			DataTable dt=db.runSelectQuery(sql, pp);
			double scorevalue=0;
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow row=dt.get(0);
				scorevalue=Double.parseDouble(row.getString("scorevalue"));
				return scorevalue;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}
	}
	public static List<Double> getLeaveScore(String objectcode,String indexcode,String scoreyear,String scoreperiod){
		try {
			DBObject db=new DBObject();
			List<Double> list=new ArrayList<Double>();
			String sql="select scorevalue from tbm_indexscoredetail where objectcode=? and  indexcode like ?   and  length(indexcode)=7    and scoreyear=? and scoreperiod=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
					{ new Parameter.String(objectcode),
					  new Parameter.String(indexcode+"%"),
					  new Parameter.String(scoreyear),
					  new Parameter.String(scoreperiod)
					};
			DataTable dt=db.runSelectQuery(sql, pp);
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					list.add(Double.parseDouble(row.getString("scorevalue")));
				}
				if(list.size()<4){
					for(int i=list.size();i<4;i++){
						
						list.add(0.0);
					}
				}
			}else{
				for(int i=0;i<4;i++){
					
					list.add(0.0);
				}
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}
}
