package com.entity.index;

import java.util.Date;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class ChangeScoreLog {

	private String recno;
	private String changeobjectcode;
	private String staffallmeritrecno;
	private String operatorcode;
	private Date operatedate;
	private double operate;
	public ChangeScoreLog(){
		
	}
	@SuppressWarnings("null")
	public ChangeScoreLog(String recno){
		try{
			String sql="select * from tbm_changescorelog where recno='"+recno+"'";
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow row=null;
				this.recno=recno;
				this.changeobjectcode=row.getString("changeobjectcode");
				this.staffallmeritrecno=row.getString("staffallmeritrecno");
				this.operatorcode=row.getString("operatorcode");
				this.operatedate=Format.strToDate(row.getString("operatedate"));
				this.operate=Double.parseDouble(row.getString("operate"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public boolean insert(ChangeScoreLog changescorelog){
		try{
			String sql="insert into tbm_changescorelog(recno,changeobjectcode,staffallmeritrecno,operatorcode,operate,operatedate) values(?,?,?,?,?,"+"to_date('"+Format.dateToStr(changescorelog.getOperatedate())+"','yyyy-mm-dd')"+")";
		    DBObject db=new DBObject();
		    Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
		    	   new Parameter.String(changescorelog.getRecno()),
		    	   new Parameter.String(changescorelog.getChangeobjectcode()),
		    	   new Parameter.String(changescorelog.getStaffallmeritrecno()),
		    	   new Parameter.String(changescorelog.getOperatorcode()),
		    	   new Parameter.Double(changescorelog.getOperate())
		    };
		   //s System.out.println(Format.dateToStr(changescorelog.getOperatedate()));
		    return db.run(sql, pp);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean writeLog(String recno,String changeobjectcode,String staffallmeritrecno,String operatorcode,double changescore){
		try{
			ChangeScoreLog changescorelog=new ChangeScoreLog();
			changescorelog.setRecno(recno);
			changescorelog.setChangeobjectcode(changeobjectcode);
			changescorelog.setStaffallmeritrecno(staffallmeritrecno);
			changescorelog.setOperatorcode(operatorcode);
			changescorelog.setOperatedate(Format.strToDate(Format.getNowtime2()));
			changescorelog.setOperate(changescore);
			return insert(changescorelog);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public String getRecno() {
		return recno;
	}
	public void setRecno(String recno) {
		this.recno = recno;
	}
	public String getChangeobjectcode() {
		return changeobjectcode;
	}
	public void setChangeobjectcode(String changeobjectcode) {
		this.changeobjectcode = changeobjectcode;
	}
	public String getStaffallmeritrecno() {
		return staffallmeritrecno;
	}
	public void setStaffallmeritrecno(String staffallmeritrecno) {
		this.staffallmeritrecno = staffallmeritrecno;
	}
	public String getOperatorcode() {
		return operatorcode;
	}
	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}
	public Date getOperatedate() {
		return operatedate;
	}
	public void setOperatedate(Date operatedate) {
		this.operatedate = operatedate;
	}
	public double getOperate() {
		return operate;
	}
	public void setOperate(double operate) {
		this.operate = operate;
	}
	
	
}
