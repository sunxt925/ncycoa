package com.performance;

import com.alibaba.fastjson.JSONObject;

public class ReviewEntity {
	private Object objReviewed;
	private String objType;
    private IndexResult indexValue;
	private String period;
	private String year;
	private String eventNo;
	public ReviewEntity(Object obj, String objtype, IndexResult arch){
		this.objReviewed = obj;
		this.objType = objtype;
		this.indexValue = arch;
	}
	
	public void review(JSONObject para) throws ReviewException{
		try{
			indexValue.getScore(indexValue.getValue(para));
		} catch(ReviewException e) {
			throw new ReviewException("异常！考核对象: " + objReviewed.toString() + ", 类型: " + objType, e);
		}
	}
	
	public IndexResult getIndexScore(){
		return indexValue;
	}
	
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	
	public Object getObjReviewed() {
		return objReviewed;
	}
	public void setObjReviewed(Object objReviewed) {
		this.objReviewed = objReviewed;
	}

	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getEventNo() {
		return eventNo;
	}
	public void setEventNo(String eventNo) {
		this.eventNo = eventNo;
	}
}
