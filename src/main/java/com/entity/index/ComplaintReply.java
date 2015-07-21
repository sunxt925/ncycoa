package com.entity.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.CodeDictionary;
import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class ComplaintReply {

	private String replyno;
	private String comlaintno;
	private String replypersoncode;
	private String replycontent;
	private String replyfile;
	private Date   replydate;
	private int enabledflag;
	private String memo;
public ComplaintReply(){
		
	}
	public ComplaintReply(String replyno){
		try{
			String sql="select * from tbm_complaintreply where replyno='"+replyno+"'";
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()==1){
				DataRow row=dt.get(0);
				this.replyno=replyno;
				this.comlaintno=row.getString("complaintno");
				this.replypersoncode=row.getString("replypersoncode");
				this.replycontent=row.getString("replycontent");
				this.replyfile=row.getString("replyfile");
				this.replydate=Format.strToDate(row.getString("replydate"));
				this.enabledflag=Integer.parseInt(row.getString("enabledflag"));
				this.memo=row.getString("memo");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public String getComplaintreplyJson(String staffcode){
		try {
			List<ComplaintReply> complaintreplylist=getComplaintreplyList(staffcode);
			StringBuilder sbuilder=new StringBuilder();
			sbuilder.append("[");
			for(ComplaintReply complaintreply:complaintreplylist){
				sbuilder.append("{");
				sbuilder.append("\"replyno\":").append("\""+complaintreply.getReplyno()+"\"").append(",");
				sbuilder.append("\"complaintno\":").append("\""+complaintreply.getComlaintno()+"\"").append(",");
				sbuilder.append("\"replypersonname\":").append("\""+CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", complaintreply.getReplypersoncode())+"\"").append(",");
				sbuilder.append("\"replypersoncode\":").append("\""+complaintreply.getReplypersoncode()+"\"").append(",");
				sbuilder.append("\"replycontent\":").append("\""+complaintreply.getReplycontent()+"\"").append(",");
				sbuilder.append("\"replyfile\":").append("\"<a href='../fileupload/downweb.jsp?filename="+complaintreply.getReplyfile()+"'>"+complaintreply.getReplyfile()+"</a>\"").append(",");
				sbuilder.append("\"replydate\":").append("\""+Format.dateToStr(complaintreply.getReplydate())+"\"").append(",");
				sbuilder.append("\"enabledflag\":").append("\""+complaintreply.getEnabledflag()+"\"").append(",");
				sbuilder.append("\"memo\":").append("\""+complaintreply.getMemo()+"\"");
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
	public List<ComplaintReply> getComplaintreplyList(String staffcode){
		try{
			String sql="select * from tbm_complaintreply";
			DBObject db=new DBObject();
			DataTable dt=db.runSelectQuery(sql);
			Map<String,String> map=new Complaint().getComplaintlistBystaff(staffcode);
			//首先查询请求者有无申诉记录，当map为null时表示没有记录，直接返回null即可
			if(map==null){
				return null;
			}
			List<ComplaintReply> complaintreplylist=new ArrayList<ComplaintReply>();
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					if(map.get(row.getString("complaintno")).equals(staffcode)){
						ComplaintReply complaintreply=new ComplaintReply();
						complaintreply.setReplyno(row.getString("replyno"));
						complaintreply.setComlaintno(row.getString("complaintno"));
						complaintreply.setReplypersoncode(row.getString("replypersoncode"));
						complaintreply.setReplycontent(row.getString("replycontent"));
						complaintreply.setReplyfile(row.getString("replyfile"));
						complaintreply.setReplydate(Format.strToDate(row.getString("replydate")));
						complaintreply.setEnabledflag(Integer.parseInt(row.getString("enabledflag")));
						complaintreply.setMemo(row.getString("memo"));
						complaintreplylist.add(complaintreply);
					}
					
				}
				
			}
			return complaintreplylist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public String getReplyno() {
		return replyno;
	}
	public void setReplyno(String replyno) {
		this.replyno = replyno;
	}
	public String getComlaintno() {
		return comlaintno;
	}
	public void setComlaintno(String comlaintno) {
		this.comlaintno = comlaintno;
	}
	public String getReplypersoncode() {
		return replypersoncode;
	}
	public void setReplypersoncode(String replypersoncode) {
		this.replypersoncode = replypersoncode;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public String getReplyfile() {
		return replyfile;
	}
	public void setReplyfile(String replyfile) {
		this.replyfile = replyfile;
	}
	public Date getReplydate() {
		return replydate;
	}
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	public int getEnabledflag() {
		return enabledflag;
	}
	public void setEnabledflag(int enabledflag) {
		this.enabledflag = enabledflag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
