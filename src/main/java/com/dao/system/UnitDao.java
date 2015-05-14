package com.dao.system;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class UnitDao {

	private  int count=0;
	public  String getUnitJson(String unitccm)
	{
		try
		{
			
			StringBuilder sbBuilder=new StringBuilder();
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from base_org where orgcode like '"+unitccm+"%' order by orgcode";
			
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			sbBuilder.append("[");
			if (dt != null && dt.getRowsCount() > 0)
			{
				
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					count++;
					DataRow r = dt.get(i);
					String ccm = r.getString("orgcode");
					String index_name = r.getString("orgname");
					sbBuilder.append("{");
					sbBuilder.append("\"id\":").append("\""+ccm+"\"").append(",");
					sbBuilder.append("\"text\":").append("\""+index_name+"\"").append(",");
					sbBuilder.append("\"children\":").append("[");
					sbBuilder.append(getChild(dt,ccm));
					sbBuilder.append("]");
					sbBuilder.append("}").append(",");
					i=count;
				}
				sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
				

			}
			sbBuilder.append("]");
			return sbBuilder.toString();
		}
		catch (Exception e)
		{
			
			return "";
		}
	}
	public   String getChild(DataTable dt,String pccm){
		try {
			StringBuilder sbBuilder=new StringBuilder();
		
			if (dt != null && dt.getRowsCount() > 0)
			{
				
				for (int i = count; i < dt.getRowsCount(); i++)
				{
					
					if(dt.get(i).getString("parentorgcode").equals(pccm)){
						count++;
						DataRow r = dt.get(i);
						String ccm = r.getString("orgcode");
						String index_name = r.getString("orgname");
						sbBuilder.append("{");
						sbBuilder.append("\"id\":").append("\""+ccm+"\"").append(",");
						sbBuilder.append("\"text\":").append("\""+index_name+"\"").append(",");
						sbBuilder.append("\"children\":").append("[");
						sbBuilder.append(getChild(dt,ccm));
						sbBuilder.append("]");
						sbBuilder.append("}").append(",");
					}
					
					
				}
				sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
				

			}
			return sbBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}
	public String getorgJson(String orgcode){
		try {
			DBObject db=new DBObject();
			String sql="select * from base_org where parentorgcode='"+orgcode+"' order by orgcode";
			DataTable dt=db.runSelectQuery(sql);
			StringBuilder sBuilder=new StringBuilder();
			sBuilder.append("[");
			if(dt!=null&&dt.getRowsCount()>=1){
				for(int i=0;i<dt.getRowsCount();i++){
					DataRow r=dt.get(i);
					sBuilder.append("{");
					sBuilder.append("\"orgcode\":").append("\""+r.getString("orgcode")+"\"").append(",");
					sBuilder.append("\"orgname\":").append("\""+r.getString("orgname")+"\"");
					sBuilder.append("},");
				}
				sBuilder.delete(sBuilder.length()-1, sBuilder.length());
				
			}
			sBuilder.append("]");
			return sBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	
	public String getpositionJson(String unitccm){
		try
		{
			
			StringBuilder sbBuilder=new StringBuilder();
			DBObject db = new DBObject();
			String sql = "";
			sql = "select * from base_orgposition where OrgCode ='"+unitccm+"'";

			
			DataTable dt = null;
			dt = db.runSelectQuery(sql);
			sbBuilder.append("[");
			if (dt != null && dt.getRowsCount() > 0)
			{
				
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					sbBuilder.append("{");
					sbBuilder.append("\"positioncode\":").append("\""+r.getString("positioncode")+"\"").append(",");
					sbBuilder.append("\"positionname\":").append("\""+r.getString("positionname")+"\"").append(",");
					sbBuilder.append("\"positionconfigcount\":").append("\""+r.getString("positionconfigcount")+"\"").append("");
					sbBuilder.append("}").append(",");
				}
				sbBuilder.delete(sbBuilder.length()-1, sbBuilder.length());
				

			}
			sbBuilder.append("]");
			return sbBuilder.toString();
		}
		catch (Exception e)
		{
			
			return "";
		}
	}
	public String getCompanytreeJson(String flag){
		return getCompanytreeJson(flag, "");
	}
	public String getCompanytreeJson(String flag,String orgcode){
		try {
			DBObject db=new DBObject();
			if(orgcode.equals("")){
				orgcode="NC.01";
			}
			String sql="select * from BASE_ORG t where orgcode like '"+orgcode+"%' and adminclass='0' order by orgcode";
			DataTable dt=db.runSelectQuery(sql);
			StringBuilder sb=new StringBuilder();
			sb.append("[");
			if(dt!=null&&dt.getRowsCount()>=1){
				if(orgcode.equals("NC.01")){
					sb.append("{");
					sb.append("\"id\":").append("\"NC.00\"").append(",");
					sb.append("\"orgcode\":").append("\"NC.00\"").append(",");
					sb.append("\"orgname\":").append("\"市局公司部门\"").append(",");
					   if(flag.equals("s")){
					      sb.append("\"text\":").append("\"市局公司部门\"").append(",");
					      sb.append("\"children\":").append(getCompanyChild("NC.00"));
					   }else {
						  sb.append("\"text\":").append("\"市局公司部门\"");
					   }
					sb.append("}");
					sb.append(",");
				}
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					sb.append("{");
					sb.append("\"id\":").append("\""+row.getString("orgcode")+"\"").append(",");
					sb.append("\"orgcode\":").append("\""+row.getString("orgcode")+"\"").append(",");
					sb.append("\"orgname\":").append("\""+row.getString("orgname")+"\"").append(",");
					   if(flag.equals("s")){
						  sb.append("\"text\":").append("\""+row.getString("orgname")+"\"").append(",");
						  sb.append("\"children\":").append(getCompanyChild(row.getString("orgcode")));
					   }else {
						  sb.append("\"text\":").append("\""+row.getString("orgname")+"\"");
					   }
					sb.append("}");
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
			}
			sb.append("]");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public String getCompanyChild(String orgcode){
		try {
			DBObject db=new DBObject();
			String sql="";
			if(orgcode.equals("NC.00"))		
			   sql="select * from BASE_ORG t where orgcode like 'NC.01%' and parentorgcode='NC.01' and  (adminclass='1' or adminclass='2') order by orgcode";
			else
			   sql="select * from BASE_ORG t where orgcode like 'NC.01%' and parentorgcode='"+orgcode+"' and ( adminclass='1' or adminclass='2')order by orgcode";
			DataTable dt=db.runSelectQuery(sql);
			StringBuilder sb=new StringBuilder();
			sb.append("[");
			if(dt!=null&&dt.getRowsCount()>=1){
				DataRow row=null;
				for(int i=0;i<dt.getRowsCount();i++){
					row=dt.get(i);
					sb.append("{");
					sb.append("\"id\":").append("\""+row.getString("orgcode")+"\"").append(",");
					sb.append("\"orgcode\":").append("\""+row.getString("orgcode")+"\"").append(",");
					sb.append("\"orgname\":").append("\""+row.getString("orgname")+"\"").append(",");
					sb.append("\"text\":").append("\""+row.getString("orgname")+"\"");
					sb.append("}");
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
			}
			sb.append("]");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}
	
	/**
	 * 获取县局局长
	 * @param orgcode
	 * @return
	 */
	public static String getComanyAudit(String orgcode){
		try {
			DBObject db=new DBObject();
			String sql="select * from base_orgmember where orgcode like '"+orgcode+"%' order by orgcode,positioncode";
			DataTable dt = db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=0){
				return dt.get(0).getString("staffcode");
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	
	}
	/**
	 * 获取市局办公室主任
	 */
	public static String getOfficeAudit(){
		try {
			DBObject db=new DBObject();
			String sql="select * from base_orgmember where orgcode='NC.01.01' and positioncode='01.0100.23'";
			DataTable dt = db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=0){
				return dt.get(0).getString("staffcode");
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 获取区县办公室主任
	 */
	public static String getQXOfficeAudit(String companycode){
		try {
			DBObject db=new DBObject();
			String sql="select * from base_orgmember where orgcode='"+companycode+".01' and positioncode='01.2X01.20'";
			DataTable dt = db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=0){
				return dt.get(0).getString("staffcode");
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 获取市局总经理
	 */
	public static String getCityAudit(){
		try {
			DBObject db=new DBObject();
			String sql="select * from base_orgmember where orgcode='NC.01.00' and positioncode='01.0000.20'";
			DataTable dt = db.runSelectQuery(sql);
			if(dt!=null&&dt.getRowsCount()>=0){
				return dt.get(0).getString("staffcode");
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
