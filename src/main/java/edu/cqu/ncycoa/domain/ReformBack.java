package edu.cqu.ncycoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="NCYCOA_REFORMBACK")
public class ReformBack {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFORMBACK_ID")
	private Long id;//反馈id
	
	@Column(name="REFORMBACK_NAME")
	private String backname;//反馈项目名称
	
	@Column(name="REFORMBACK_REFROMID")
	private Long reformId;//整改ID
	
	@Column(name="REFORMBACK_FILENAME")
	private String fileName;
	
	@Column(name="REFORMBACK_MEMO")
	private String memo;//完成情况
	
	@Column(name="REFORMBACK_SUBDATE")
	private Date subDate;//提交日期
	
	@Column(name="REFORMBACK_SUBUSER")
	private String subUser;//提交者
	
	@Column(name="REFORMBACK_FILE")
	private String reformFile;
	
	@Column(name="REFORMBACK_REASONANALYER")
	private String reasonAnalyer;//原因分析
	
	@Column(name="REFORMBACK_ANALYERDATE")
	private Date analyerDate;//原因分析日期
	
	@Column(name="REFORMBACK_PREMEASURE")
	private String preMeasure;//预防措施
	
	@Column(name="REFORMBACK_ZDSTAFF")
	private String zdStaff;//制定人
	
	@Column(name="REFORMBACK_ZDDATE")
	private Date zdDate;//指定日期
	
	@Column(name="REFORMBACK_PZSTAFF")
	private String pzStaff;//批准人
	
	@Column(name="REFORMBACK_PZDATE")
	private Date pzDateDate;//批准日期
	
	@Column(name="REFORMBACK_ZRSTAFF")
	private String zrStaff;//负责人
	
	@Column(name="REFORMBACK_ZRDATE")
	private Date zrDateDate;//批准日期
	
	@Column(name="REFORMBACK_YANZHENG")
	private String yanzheng;//验证结果
	
	@Column(name="REFORMBACK_YZSTAFF")
	private String yzStaff;//验证人
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBackname() {
		return backname;
	}
	public void setBackname(String backname) {
		this.backname = backname;
	}
	public Long getReformId() {
		return reformId;
	}
	public void setReformId(Long reformId) {
		this.reformId = reformId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getSubDate() {
		return subDate;
	}
	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}
	public String getSubUser() {
		return subUser;
	}
	public void setSubUser(String subUser) {
		this.subUser = subUser;
	}
	public String getReformFile() {
		return reformFile;
	}
	public void setReformFile(String reformFile) {
		this.reformFile = reformFile;
	}
	public String getReasonAnalyer() {
		return reasonAnalyer;
	}
	public void setReasonAnalyer(String reasonAnalyer) {
		this.reasonAnalyer = reasonAnalyer;
	}
	public Date getAnalyerDate() {
		return analyerDate;
	}
	public void setAnalyerDate(Date analyerDate) {
		this.analyerDate = analyerDate;
	}
	public String getPreMeasure() {
		return preMeasure;
	}
	public void setPreMeasure(String preMeasure) {
		this.preMeasure = preMeasure;
	}
	public String getZdStaff() {
		return zdStaff;
	}
	public void setZdStaff(String zdStaff) {
		this.zdStaff = zdStaff;
	}
	public Date getZdDate() {
		return zdDate;
	}
	public void setZdDate(Date zdDate) {
		this.zdDate = zdDate;
	}
	public String getPzStaff() {
		return pzStaff;
	}
	public void setPzStaff(String pzStaff) {
		this.pzStaff = pzStaff;
	}
	public Date getPzDateDate() {
		return pzDateDate;
	}
	public void setPzDateDate(Date pzDateDate) {
		this.pzDateDate = pzDateDate;
	}
	public String getZrStaff() {
		return zrStaff;
	}
	public void setZrStaff(String zrStaff) {
		this.zrStaff = zrStaff;
	}
	public Date getZrDateDate() {
		return zrDateDate;
	}
	public void setZrDateDate(Date zrDateDate) {
		this.zrDateDate = zrDateDate;
	}
	public String getYanzheng() {
		return yanzheng;
	}
	public void setYanzheng(String yanzheng) {
		this.yanzheng = yanzheng;
	}
	public String getYzStaff() {
		return yzStaff;
	}
	public void setYzStaff(String yzStaff) {
		this.yzStaff = yzStaff;
	}
	
	
}
