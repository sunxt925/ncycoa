package com.performance;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.index.Indexitem;
import com.entity.system.Org;
import com.entity.system.StaffInfo;
import com.entity.system.UserInfo;

public class Review {
	private UserInfo user;
	public Review(UserInfo user){
		this.user = user;
	}
	
	public String getjson(ReviewDate date, String type){
		List<ReviewTask> tasks = getReviewTasks(date, type);
		if(tasks.size() == 0){
			return "ѡ����ʱ���ڲ����ڿ��˶���";
		}
		
		java.util.Collections.sort(tasks, new Comparator<ReviewTask>(){
			public int compare(ReviewTask o1, ReviewTask o2) {
				return o1.getIndexArch().compareTo(o2.getIndexArch());
			}
		});
		StringBuffer sb = new StringBuffer();
		// �������ݱ���ͷ
		sb.append("<table id=\"index_sel_dg\" class=\"easyui-datagrid\" data-options=\"fit:true,rownumbers:true,singleSelect:true,idField:'indexcode'\">");
		sb.append("<thead><tr>");
		sb.append("<th data-options=\"field:'indexname',width:200\">ָ����ϵ����</th>");
		sb.append("<th data-options=\"field:'indexcode',hidden:true\"></th>");
		sb.append("<th data-options=\"field:'objlist'\">���˶���</th>");
		sb.append("</tr></thead><tbody>");
		Iterator<ReviewTask> iter = tasks.iterator();
		while(iter.hasNext()){
			ReviewTask task = iter.next();
			sb.append("<tr>");
			sb.append("<td>").append(task.getIndexArch().getIndexName()).append("</td>");
			sb.append("<td>").append(task.getIndexArch().getIndexCode()).append("</td>");
			sb.append("<td>");
			List<ReviewableObj> objs = task.getReviewees();
			java.util.Collections.sort(objs, new Comparator<ReviewableObj>(){
				public int compare(ReviewableObj o1, ReviewableObj o2) {
					return o1.getCode().compareTo(o2.getCode());
				}
			});
			Iterator<ReviewableObj> iterObj = objs.iterator();
			while(iterObj.hasNext()){
				ReviewableObj obj = iterObj.next();
				sb.append(obj.getName()).append(",");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append("</td></tr>");
		}
		sb.append("</tbody></table>");
		return sb.toString();
	}
	
	private String en2cn(String type){
		if("staff".equals(type)){
			return "����";
		} else if("depart".equals(type)){
			return "����";
		} else {
			return "��˾";
		}
	}
	
	public String getindexjson() {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>$('#dg').treegrid({");
			sb.append("idField:'indexcode'");
			sb.append(",treeField:'indexname'");
			sb.append(",rownumbers:true");
			sb.append(",fit:true");
			sb.append(",border:false");
			sb.append(",onClickRow:onClickRow");
			sb.append(",columns:[[");
			sb.append("{title:'ָ������',field:'indexname',width:200}");
			sb.append(",{title:'���˶���',field:'objlist'}");
			sb.append("]]");
			sb.append(",data:");
			sb.append("[");
			Map<String, List<ReviewTask>> tasks = getReviewTasks();
			
			List<String> arches = new ArrayList<String>();
			try {
				DBObject db = new DBObject();
				String sql = "select  distinct a.* from  (select * from tbm_indexitem where length(indexcode)=3 order by indexcode ) a,tbm_indexitempara b where substr(b.indexcode,1,3)=a.indexcode and b.paravaluemode='ҵ������'";
				DataTable dt = db.runSelectQuery(sql);
				if (dt != null) {
					for (int i = 0; i < dt.getRowsCount(); i++) {
						DataRow r = dt.get(i);
						arches.add(r.getString("indexcode"));
					}
				}
			} catch (Exception e) {
			}
			
			for(String type: tasks.keySet()){
				Iterator<ReviewTask> iter = tasks.get(type).iterator();
				while(iter.hasNext()){
					ReviewTask tmp = iter.next();
					if(!arches.contains(tmp.getIndexArch().getIndexCode())){
						iter.remove();
					}
				}
			}
			
			for (String type : tasks.keySet()) {
				if(tasks.get(type).size() == 0){
					continue;
				}
				sb.append("{");
				sb.append("\"indexcode\":").append("\""+ type +"\"");
				sb.append(",\"indexname\":").append("\"" + en2cn(type) + "\"");
				sb.append(",\"objlist\":").append("\"\"");
				sb.append(",\"children\":[");
				Iterator<ReviewTask> iter = tasks.get(type).iterator();
				while(iter.hasNext()){
					ReviewTask tmp = iter.next();
					
					sb.append("{");
					sb.append("\"indexcode\":").append("\"" + tmp.getIndexArch().getIndexCode() + "\"");
					sb.append(",\"indexname\":").append("\"" + tmp.getIndexArch().getIndexName() + "\"");
					sb.append(",\"objlist\":").append("\"");
					List<ReviewableObj> objs = tmp.getReviewees();
					for(int i=0; i<objs.size(); i++){
						sb.append(objs.get(i).getName()).append(",");
					}
					sb.delete(sb.length() - 1, sb.length());
					sb.append("\"},");
				}
				if(tasks.get(type).size()>0){
					sb.delete(sb.length() - 1, sb.length());
				}
				sb.append("]},");
			}
			if(sb.length() > 1){
				sb.delete(sb.length() - 1, sb.length());
			}
			sb.append("]");
			sb.append("});</script>");
			return sb.toString();
	}
	
	public List<ReviewTask> getReviewTasks(String type){
		try {
			Map<String, List<ReviewableObj>> maps = new HashMap<String, List<ReviewableObj>>();
			DBObject db = new DBObject();
			String sql = "select indexarchcode,objectcode,objecttype from tbm_indexarchuser where objecttype='" + type + "'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					String archcode = r.getString("indexarchcode");
					String objcode = r.getString("objectcode");
					String objType = r.getString("objecttype");
					ReviewableObj obj = null;
					if ("staff".equals(objType)) {
						obj = new StaffInfo(objcode);
					} else {
						obj = new Org(objcode);
						obj.setType(objType);
					}
					
					List<ReviewableObj> tmp = null;
					if(maps.containsKey(archcode)){
						tmp = maps.get(archcode);
					} else {
						tmp = new ArrayList<ReviewableObj>();
						maps.put(archcode, tmp);
					}
					tmp.add(obj);
				}
			}
			
			List<ReviewTask> returnValue = new ArrayList<ReviewTask>();
			for(String key : maps.keySet()){
				List<ReviewableObj> tmp = maps.get(key);
				tmp = filter(tmp);
				if(tmp.size() > 0){
					ReviewTask task = new ReviewTask(user, type);
					task.setReviewees(tmp);
					task.setIndexArch(new Indexitem(key));
					returnValue.add(task);
				}
			}
			
			if(returnValue.size() == 0){
				return java.util.Collections.emptyList();
			} else {
				return returnValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return java.util.Collections.emptyList();
	}
	
	public Map<String, List<ReviewTask>> getReviewTasks(){
		Map<String, List<ReviewTask>> returnValue = new HashMap<String, List<ReviewTask>>();
		returnValue.put("staff", null);
		returnValue.put("company", null);
		returnValue.put("depart", null);
		
		for(String type : returnValue.keySet()){
			List<ReviewTask> tmp = getReviewTasks(type);
			returnValue.put(type, tmp);
		}
		
		return returnValue;
	}
	
	
	
	/**
	 * ��ȡ���������б�һ��ָ����ϵ��Ӧһ����������
	 * 
	 * @param date ��������
	 * @param type �����������ͣ�staff��depart�� company��
	 * @return
	 */
	public List<ReviewTask> getReviewTasks(ReviewDate date, String type){
		List<ReviewTask> returnValue = new ArrayList<ReviewTask>();
		Map<String, List<ReviewableObj>> maps = new HashMap<String, List<ReviewableObj>>();
		try {
			Calendar dateSpec = Calendar.getInstance();
			dateSpec.set(date.getYear(), date.getPeriod() - 1, 1);
			
			DBObject db = new DBObject();
			String sql = "select indexarchcode,objectcode,objecttype,startdate,enddate from tbm_indexarchuser where objecttype='" + type + "'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					Calendar startdate = Calendar.getInstance();
					startdate.setTime(DateFormat.getDateInstance().parse(r.getString("startdate"))); 
					startdate.add(Calendar.DAY_OF_MONTH, -startdate.get(Calendar.DAY_OF_MONTH) + 1);
					
					Calendar enddate = Calendar.getInstance(); 
					enddate.setTime(DateFormat.getDateInstance().parse(r.getString("enddate")));
					enddate.add(Calendar.DAY_OF_MONTH, -enddate.get(Calendar.DAY_OF_MONTH) + 1);
					enddate.add(Calendar.MONTH, 1);
					
					if(startdate.getTimeInMillis() <= dateSpec.getTimeInMillis() && dateSpec.getTimeInMillis() <= enddate.getTimeInMillis()){
						String archcode = r.getString("indexarchcode");
						String objcode = r.getString("objectcode");
						String objType = r.getString("objecttype");
						ReviewableObj obj = null;
						if ("staff".equals(objType)) {
							obj = new StaffInfo(objcode);
						} else {
							obj = new Org(objcode);
							obj.setType(objType);
						}
						
						List<ReviewableObj> tmp = null;
						if(maps.containsKey(archcode)){
							tmp = maps.get(archcode);
						} else {
							tmp = new ArrayList<ReviewableObj>();
							maps.put(archcode, tmp);
						}
						tmp.add(obj);
					}
				}
			}
			
			for(String key : maps.keySet()){
				List<ReviewableObj> tmp = maps.get(key);
				tmp = filter(tmp);
				if(tmp.size() > 0){
					ReviewTask task = new ReviewTask(user, type);
					task.setDate(date);
					task.setReviewees(tmp);
					task.setIndexArch(new Indexitem(key));
					
					returnValue.add(task);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	
	/**
	 * ����ָ�����ͣ�staff��company��depart���Ŀ�������
	 * @param type ��������
	 * @return user�и������͵Ŀ�������Ļ�����true��û�з���false
	 */
	public boolean hasReviewTask(String type){
		try {
			DBObject db = new DBObject();
			String sql = "select * from tbm_meritinputtaskperson where staffcode='" + user.getStaffcode() + "' and tasktype='"+type+"'";
			DataTable dt = db.runSelectQuery(sql);
			if(dt != null && dt.getRowsCount() > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * ��ȡ��������
	 * 
	 * @param date ����ʱ��
	 * @param archcode ������ϵ����
	 * @param type �����������ͣ�staff��depart�� company��
	 * @return
	 */
	public ReviewTask getReviewTask(ReviewDate date, String archcode, String type){
		ReviewTask returnTask = ReviewTask.INVALID_TASK;
		List<ReviewableObj> returnValue = new ArrayList<ReviewableObj>();
		try {
			Calendar dateMin = Calendar.getInstance();
			dateMin.set(date.getYear(), date.getMin() - 1, 1);
			
			Calendar dateMax = Calendar.getInstance();
			dateMax.set(date.getYear(), date.getMax() - 1, 1);
			
			DBObject db = new DBObject();
			String sql = "select objectcode,objecttype,startdate,enddate from tbm_indexarchuser where indexarchcode='" + archcode + "' and objecttype='" + type + "'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					Calendar startdate = Calendar.getInstance();
					startdate.setTime(DateFormat.getDateInstance().parse(r.getString("startdate"))); 
					startdate.add(Calendar.DAY_OF_MONTH, -startdate.get(Calendar.DAY_OF_MONTH) + 1);
					
					Calendar enddate = Calendar.getInstance(); 
					enddate.setTime(DateFormat.getDateInstance().parse(r.getString("enddate")));
					enddate.add(Calendar.DAY_OF_MONTH, -enddate.get(Calendar.DAY_OF_MONTH) + 1);
					enddate.add(Calendar.MONTH, 1);
					
					if(dateMin.getTimeInMillis() > enddate.getTimeInMillis() || dateMax.getTimeInMillis() < startdate.getTimeInMillis()){
						continue;
					}
					
					String objcode = r.getString("objectcode");
					String objType = r.getString("objecttype");
					ReviewableObj obj = null;
					if ("staff".equals(objType)) {
						obj = new StaffInfo(objcode);
					} else {
						obj = new Org(objcode);
						obj.setType(objType);
					}
					returnValue.add(obj);
				}
			}
			
			returnValue = filter(returnValue);
			if(returnValue.size() > 0){
				returnTask = new ReviewTask(user, type);
				returnTask.setDate(date);
				returnTask.setReviewees(returnValue);
				returnTask.setIndexArch(new Indexitem(archcode));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnTask;
	}
	/**
	 * �Ը�Ա�����п��˵Ŀ�������Ŀǰֻ֧��һ��Ա����Ӧһ��ָ����ϵ
	 * @param staffcode
	 * @param date
	 * @return
	 */
	public ReviewTask getReviewTask(String staffcode, ReviewDate date){
		ReviewTask returnTask = ReviewTask.INVALID_TASK;
		List<ReviewableObj> returnValue = new ArrayList<ReviewableObj>();
		String archcode = "";
		try {
			Calendar dateMin = Calendar.getInstance();
			dateMin.set(date.getYear(), date.getMin() - 1, 1);
			
			Calendar dateMax = Calendar.getInstance();
			dateMax.set(date.getYear(), date.getMax() - 1, 1);
			
			DBObject db = new DBObject();
			String sql = "select * from tbm_indexarchuser where objectcode='" + staffcode + "' and objecttype='staff'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					Calendar startdate = Calendar.getInstance();
					startdate.setTime(DateFormat.getDateInstance().parse(r.getString("startdate"))); 
					startdate.add(Calendar.DAY_OF_MONTH, -startdate.get(Calendar.DAY_OF_MONTH) + 1);
					
					Calendar enddate = Calendar.getInstance(); 
					enddate.setTime(DateFormat.getDateInstance().parse(r.getString("enddate")));
					enddate.add(Calendar.DAY_OF_MONTH, -enddate.get(Calendar.DAY_OF_MONTH) + 1);
					enddate.add(Calendar.MONTH, 1);
					
					if(dateMin.getTimeInMillis() > enddate.getTimeInMillis() || dateMax.getTimeInMillis() < startdate.getTimeInMillis()){
						continue;
					}
					
					String objcode = r.getString("objectcode");
					archcode = r.getString("indexarchcode");
					ReviewableObj obj = null;
					obj = new StaffInfo(objcode);
					returnValue.add(obj);
				}
			}
			
			if(returnValue.size() > 0){
				returnTask = new ReviewTask(null, "staff");
				returnTask.setDate(date);
				returnTask.setReviewees(returnValue);
				returnTask.setIndexArch(new Indexitem(archcode));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnTask;
	}
	public ReviewTask getReviewTask(ReviewDate date, String archcode){
		return getReviewTask(date, archcode, getTaskType(archcode));
	}
	
	private String getTaskType(String indexcode){
		if(indexcode.startsWith("S")) return "staff";
		if(indexcode.startsWith("D")) return "depart";
		if(indexcode.startsWith("C")) return "company";
		return "";
	}
	
	/**
	 * ����user�Ŀ���Ȩ�޹��˿��˶���
	 * 
	 * @param objs �����˶����б�
	 * @return
	 */
	private List<ReviewableObj> filter(List<ReviewableObj> objs){
		Map<String, ReviewableObj> oldValues = new HashMap<String, ReviewableObj>();
		Iterator<ReviewableObj> iter = objs.iterator();
		while(iter.hasNext()){
			ReviewableObj tmp = iter.next();
			oldValues.put(tmp.getCode(), tmp);
		}
		
		List<ReviewableObj> returnValue = new ArrayList<ReviewableObj>();
		try {
			DBObject db = new DBObject();
			String sql = "select * from tbm_meritinputtaskperson where staffcode='" + user.getStaffcode() + "'";
			DataTable dt = db.runSelectQuery(sql);
			if(dt != null){
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					String orgcode = r.getString("orgcode");
					String tasktype = r.getString("tasktype");
					if(tasktype.equals("company")){
						if(oldValues.containsKey(orgcode)){
							returnValue.add(oldValues.get(orgcode));
						}
					} else if(tasktype.equals("depart")){
						sql = "select orgcode from base_org where orgcode like '" + orgcode + "%'";
						DataTable dt2 = db.runSelectQuery(sql);
						if(dt2 != null){
							for(int j=0; j<dt2.getRowsCount(); j++){
								String tmp = dt2.get(j).getString("orgcode");
								if(oldValues.containsKey(tmp)){
									returnValue.add(oldValues.get(tmp));
								}
							}
						}
					} else if(tasktype.equals("staff")){
						sql = "select distinct staffcode from base_orgmember where orgcode like '" + orgcode + "%'";
						DataTable dt2 = db.runSelectQuery(sql);
						if(dt2 != null){
							for(int j=0; j<dt2.getRowsCount(); j++){
								String tmp = dt2.get(j).getString("staffcode");
								if(oldValues.containsKey(tmp)){
									returnValue.add(oldValues.get(tmp));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
}
