package com.review;

import java.util.List;
import java.util.Map;

import com.entity.index.Indexitem;
import com.entity.system.UserInfo;
import com.performance.ReviewDate;
import com.performance.ReviewableObj;

public class ReviewTask {
	
	public Map<ReviewableObj, ReviewResults> review(Map<ReviewableObj, IndexParaValues> paraValues){
		return null;
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
	
	public List<ReviewableObj> getObj() {
		return obj;
	}
	public void setObj(List<ReviewableObj> obj) {
		this.obj = obj;
	}
	
	public Indexitem getIndex() {
		return index;
	}
	public void setIndex(Indexitem index) {
		this.index = index;
	}
	
	private UserInfo user;
	private ReviewDate date;
	private List<ReviewableObj> obj;
	private Indexitem index;
}
