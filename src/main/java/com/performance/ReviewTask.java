package com.performance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.index.Indexitem;
import com.entity.system.UserInfo;
import com.performance.ReviewDate;
import com.performance.ReviewableObj;

public class ReviewTask {
	public final static ReviewTask INVALID_TASK = new ReviewTask(null, "");
	
	public ReviewTask(UserInfo user, String taskType){
		this.user = user;
		this.taskType = taskType;
	}
	
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	public ReviewDate getDate() {
		return date;
	}
	public void setDate(ReviewDate date) {
		this.date = date;
	}
	
	//paixu
	public List<ReviewableObj> getReviewees() {
		return objs;
	}
	public void setReviewees(List<ReviewableObj> objs) {
		this.objs = objs;
	}
	
	public String getTaskType(){
		return taskType;
	}
	
	private boolean isParsed = false;
	public boolean isIndexArchParsed(){
		return isParsed;
	}
	
	
	/**
	 * 无论是否已构建指标树，都强制重新构建
	 */
	public void forceToParseIndexTree(){
		if(arch == null) return;
		
		List<Indexitem> indexlist = new ArrayList<Indexitem>();
		indexlist.add(arch);
		try {
			String sql = "select * from tbm_indexitem where indexcode like '" + arch.getIndexCode() + "%' order by indexcode";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					if(arch.getIndexCode().equals(r.getString("indexcode"))){
						continue;
					}
					Indexitem indexitem = new Indexitem(r);
					indexlist.add(indexitem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(leafIndices != null && !leafIndices.isEmpty()) leafIndices.clear();
		leafIndices = new ArrayList<Indexitem>();
		Queue<Indexitem> q = new LinkedList<Indexitem>();
		q.offer(arch);
		while (q.peek() != null) {
			Indexitem parent = q.remove();
			for (int i = 0; i < indexlist.size(); i++) {
				Indexitem item = indexlist.get(i);
				if (item.getParentIndexCode().equals(parent.getIndexCode())) {
					parent.children.add(item);
					item.parent = parent;
					if (item.getIsParent().equals("1")) {
						q.offer(item);
					} else {
						leafIndices.add(item);
					}
				}
			}
		}
		java.util.Collections.sort(leafIndices, new Comparator<Indexitem>(){
			public int compare(Indexitem o1, Indexitem o2) {
				return o1.getIndexCode().compareTo(o2.getIndexCode());
			}
		});
		isParsed = true;
	}
	
	/**
	 * 解析指标树，如果之前已成功构建指标树，则不做任何事
	 */
	public void parseIndexTree(){
		if(isParsed) return;
		forceToParseIndexTree();
	}
	
	public Indexitem getIndexArch() {
		return arch;
	}
	public void setIndexArch(Indexitem arch) {
		this.arch = arch;
	}
	
	/**
	 * 如果指标树已构建，则返回该指标体系下的所有页指标，否则返回空集合
	 * @return
	 */
	public List<Indexitem> getLeafIndices(){
		if(!isParsed){
			return java.util.Collections.emptyList();
		} else {
			return leafIndices;
		}
	}
	
	private UserInfo user;
	private ReviewDate date;
	private String taskType;
	private List<ReviewableObj> objs;
	private Indexitem arch;
	private List<Indexitem> leafIndices;
}
