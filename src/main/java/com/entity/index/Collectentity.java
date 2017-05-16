package com.entity.index;

public class Collectentity {
private String year;
private String period;
private int needcollectobjectnum;
private int collectedobjectnum;
private int staffsumcount;//综合绩效以已汇总人数
private int staffneedcount;//综合绩效构成关联人数（需要汇总人数）
private int staffallcount;//员工总人数
private int departsumcount;//部门已汇总数
private int deparneedcount;//需要汇总部门数
private int companysumcount;//公司已汇总数
private int companyneedcount;//需要汇总公司数
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getPeriod() {
	return period;
}
public void setPeriod(String period) {
	this.period = period;
}
public int getNeedcollectobjectnum() {
	return needcollectobjectnum;
}
public void setNeedcollectobjectnum(int needcollectobjectnum) {
	this.needcollectobjectnum = needcollectobjectnum;
}
public int getCollectedobjectnum() {
	return collectedobjectnum;
}
public void setCollectedobjectnum(int collectedobjectnum) {
	this.collectedobjectnum = collectedobjectnum;
}
public int getStaffsumcount() {
	return staffsumcount;
}
public void setStaffsumcount(int staffsumcount) {
	this.staffsumcount = staffsumcount;
}
public int getStaffneedcount() {
	return staffneedcount;
}
public void setStaffneedcount(int staffneedcount) {
	this.staffneedcount = staffneedcount;
}
public int getDepartsumcount() {
	return departsumcount;
}
public void setDepartsumcount(int departsumcount) {
	this.departsumcount = departsumcount;
}
public int getDeparneedcount() {
	return deparneedcount;
}
public void setDeparneedcount(int deparneedcount) {
	this.deparneedcount = deparneedcount;
}
public int getCompanysumcount() {
	return companysumcount;
}
public void setCompanysumcount(int companysumcount) {
	this.companysumcount = companysumcount;
}
public int getCompanyneedcount() {
	return companyneedcount;
}
public void setCompanyneedcount(int companyneedcount) {
	this.companyneedcount = companyneedcount;
}
public int getStaffallcount() {
	return staffallcount;
}
public void setStaffallcount(int staffallcount) {
	this.staffallcount = staffallcount;
}


}
