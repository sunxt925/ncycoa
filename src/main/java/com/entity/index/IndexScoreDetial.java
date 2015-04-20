package com.entity.index;


public class IndexScoreDetial {

	private String ObjectCode;
	private Indexitem indexitem;
	private float indexvalue;
	private float scorevalue;
	private String year;
	private String month;
	public String getObjectCode() {
		return ObjectCode;
	}
	public void setObjectCode(String objectCode) {
		ObjectCode = objectCode;
	}
	public Indexitem getIndexitem() {
		return indexitem;
	}
	public void setIndexitem(Indexitem indexitem) {
		this.indexitem = indexitem;
	}
	public float getIndexvalue() {
		return indexvalue;
	}
	public void setIndexvalue(float indexvalue) {
		this.indexvalue = indexvalue;
	}
	public float getScorevalue() {
		return scorevalue;
	}
	public void setScorevalue(float scorevalue) {
		this.scorevalue = scorevalue;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
}
