package com.entity.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.common.CodeDictionary;
import com.common.Format;
import com.dao.system.StaffDao;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class Complaint {

	private static Logger logger=Logger.getLogger(Complaint.class);
	private String complaintno;
	private String complaintrtcode;
	private String complainttitle;
	private String complaintcategory;
	private String complaintreson;
	private Date complaintdate;
	private String attachedfile;
	private int enableflag;
	private String memo;
	public Complaint(){
		
	}
	public Complaint(String complaintno){
		try{
			String sql="select * from tbm_complaint where complaintno='"+complaintno+"'";
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow row=dt.get(0);
				this.complaintno=complaintno;
				this.complaintrtcode=row.getString("complaintcode");
				this.complainttitle=row.getString("complainttitle");
				this.complaintcategory=row.getString("complaintcategory");
				this.complaintreson=row.getString("complaintreson");
				this.complaintdate=Format.strToDate(row.getString("complaintdate"));
				this.attachedfile=row.getString("attachedfile");
				this.enableflag=Integer.parseInt(row.getString("enabledflag"));
				this.memo=row.getString("memo");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 构建所有人员申诉列表
	 * @return
	 */
	public String getComplaintJson(String flag,String handler){
		return getComplaintJson("",flag,handler);
	}
	/**
	 * 根据staffcode构建个人申诉列表
	 * @param staffcode
	 * @return
	 */
	public String getComplaintJson(String staffcode,String flag,String handlercode){
		try {
			List<Complaint> complaintlist=getComplaintList(staffcode,flag,handlercode);
			StringBuilder sbuilder=new StringBuilder();
			sbuilder.append("[");
			for(Complaint complaint:complaintlist){
				sbuilder.append("{");
				sbuilder.append("\"complaintno\":").append("\""+complaint.getComplaintno()+"\"").append(",");
				sbuilder.append("\"complaintercode\":").append("\""+complaint.getComplaintrtcode()+"\"").append(",");
				sbuilder.append("\"name\":").append("\""+CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", complaint.getComplaintrtcode())+"\"").append(",");
				sbuilder.append("\"complainttitle\":").append("\""+complaint.getComplainttitle()+"\"").append(",");
				sbuilder.append("\"complaintcategory\":").append("\""+complaint.getComplaintcategory()+"\"").append(",");
				sbuilder.append("\"complaintreason\":").append("\""+complaint.getComplaintreson()+"\"").append(",");
				sbuilder.append("\"complaintdate\":").append("\""+Format.dateToStr(complaint.getComplaintdate())+"\"").append(",");
				sbuilder.append("\"attachedfile\":").append("\"<a href='../fileupload/downweb.jsp?filename="+complaint.getAttachedfile()+"'>"+complaint.getAttachedfile()+"</a>\"").append(",");
				sbuilder.append("\"enabledflag\":").append("\""+complaint.getEnableflag()+"\"").append(",");
				sbuilder.append("\"memo\":").append("\""+complaint.getMemo()+"\"");
				
				sbuilder.append("}");
				sbuilder.append(",");
			}
			sbuilder.delete(sbuilder.length()-1, sbuilder.length());
			sbuilder.append("]");
			return sbuilder.toString();
		} catch (Exception e) {
			return "";
			
		}
	}
	/**
	 * 获取申诉数据
	 * @param staffcode
	 * @return
	 */
	public List<Complaint> getComplaintList(String staffcode,String flag,String handler){
		try{
			String queryCondition="";
			List<String> staffcodes = null; 
			if(staffcode.equals("")){
				queryCondition="1=1";
				staffcodes = StaffDao.getStaffByOrg(handler);
			}else{
				queryCondition="complaintercode='"+staffcode+"'";
				staffcodes = StaffDao.getStaffByOrg(staffcode);
			}
			
			String sql="select * from tbm_complaint where "+queryCondition;
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			List<Complaint> complaintlist=new ArrayList<Complaint>();
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					Complaint complaint=new Complaint();
					complaint.setComplaintno(row.getString("complaintno"));
					complaint.setComplaintrtcode(row.getString("complaintercode"));
					complaint.setComplainttitle(row.getString("complainttitle"));
					complaint.setComplaintcategory(row.getString("complaintcategory"));
					complaint.setComplaintreson(row.getString("complaintreason"));
					complaint.setComplaintdate(Format.strToDate(row.getString("complaintdate")));
					complaint.setAttachedfile(row.getString("attachedfile"));
					complaint.setEnableflag(Integer.parseInt(row.getString("enabledflag")));
					complaint.setMemo(row.getString("memo"));
					//flag区分申诉类别，0为区县，1为市局
					if(flag.equals("0")){
						
						if(!(row.getString("complaintcategory")).equals("company")){
							if(staffcodes.contains(row.getString("complaintercode")))
							complaintlist.add(complaint);
						}
					}else{
						complaintlist.add(complaint);
					}
					
				}
				
			}
			return complaintlist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * 根据staffcode构建申诉列表，返回map数据
	 * @param staffcode
	 * @return
	 */
	public Map<String, String> getComplaintlistBystaff(String staffcode){
		try {
			String sql="select complaintno from tbm_complaint where complaintercode=?";
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
				new Parameter.String(staffcode)	
			};
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql, pp);
			Map<String, String> map=new HashMap<String, String>();
			if(dt !=null && dt.getRowsCount()>=1){
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					map.put(row.getString("complaintno"), staffcode);
				}	
				return map;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 申诉回复，修改申诉状态
	 * @param complaintno
	 * @return
	 */
	public boolean modifyComplaint(String complaintno){
		try {
			String sql="update tbm_complaint set enabledflag=1 where complaintno='"+complaintno+"'";
			DBObject db=new DBObject();
			return db.run(sql);
		} catch (Exception e) {
			logger.info("修改申诉状态失败");
			return false;
		}
		
	}
	public String getComplaintno() {
		return complaintno;
	}
	public void setComplaintno(String complaintno) {
		this.complaintno = complaintno;
	}
	public String getComplaintrtcode() {
		return complaintrtcode;
	}
	public void setComplaintrtcode(String complaintrtcode) {
		this.complaintrtcode = complaintrtcode;
	}
	public String getComplainttitle() {
		return complainttitle;
	}
	public void setComplainttitle(String complainttitle) {
		this.complainttitle = complainttitle;
	}
	public String getComplaintcategory() {
		return complaintcategory;
	}
	public void setComplaintcategory(String complaintcategory) {
		this.complaintcategory = complaintcategory;
	}
	public String getComplaintreson() {
		return complaintreson;
	}
	public void setComplaintreson(String complaintreson) {
		this.complaintreson = complaintreson;
	}
	public Date getComplaintdate() {
		return complaintdate;
	}
	public void setComplaintdate(Date complaintdate) {
		this.complaintdate = complaintdate;
	}
	public String getAttachedfile() {
		return attachedfile;
	}
	public void setAttachedfile(String attachedfile) {
		this.attachedfile = attachedfile;
	}
	public int getEnableflag() {
		return enableflag;
	}
	public void setEnableflag(int enableflag) {
		this.enableflag = enableflag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
