package edu.cqu.ncycoa.target.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TBM_ObjIndexArchUser")
public class ObjIndexArchUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ARCHUSER_ID")
	private Long id; 

	@Column(name="INDEXARCH_CODE")
	private String IndexArchCode;//	指标分类编码
	@Column(name="UNIINDEX_CODE")
	private String UniIndexCode;//	指标统一编码
	@Column(name="OBJECT_CODE")  //对象编码
	private String objectcode;
	@Column(name="OBJECT_TYPE")  //单位
	private String objecttype;
	@Column(name="STAFFORG")
	private String stafforg;
	@Column(name="MULTI_INDEX_ORDER")
	private int multiindexorder;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="END_DATE")
	private Date endDate;
	@Column(name="MEMO")
	private String memo;//备注

	//开始时间
	@Column(name="VALID_BEGINDATE")
	private Date ValidBeginDate;
	//结束时间
	@Column(name="VALID_ENDDATE")
	private Date ValidEndDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIndexArchCode() {
		return IndexArchCode;
	}
	public void setIndexArchCode(String indexArchCode) {
		IndexArchCode = indexArchCode;
	}
	public String getUniIndexCode() {
		return UniIndexCode;
	}
	public void setUniIndexCode(String uniIndexCode) {
		UniIndexCode = uniIndexCode;
	}
	public String getObjectcode() {
		return objectcode;
	}
	public void setObjectcode(String objectcode) {
		this.objectcode = objectcode;
	}
	public String getObjecttype() {
		return objecttype;
	}
	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	public String getStafforg() {
		return stafforg;
	}
	public void setStafforg(String stafforg) {
		this.stafforg = stafforg;
	}
	public int getMultiindexorder() {
		return multiindexorder;
	}
	public void setMultiindexorder(int multiindexorder) {
		this.multiindexorder = multiindexorder;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getValidBeginDate() {
		return ValidBeginDate;
	}
	public void setValidBeginDate(Date validBeginDate) {
		ValidBeginDate = validBeginDate;
	}
	public Date getValidEndDate() {
		return ValidEndDate;
	}
	public void setValidEndDate(Date validEndDate) {
		ValidEndDate = validEndDate;
	}


}
