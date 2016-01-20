package edu.cqu.ncycoa.target.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.entity.index.Indexitem;
@Entity
@Table(name="TBM_ObjIndex")
public class ObjIndexItem implements Comparable<ObjIndexItem>{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INDEX_CODE")
	private String IndexCode = "";
	
	@Column(name="INDEX_NAME")
	private String IndexName = "";
	@Column(name="INDEX_DESC")
	private String IndexDesc = "";
	@Column(name="ARCH_CODE")
	private String ArchCode = "";
	@Column(name="ISPARENT")
	private String IsParent = "";
	@Column(name="PARENT_INDEX_CODE")
	private String ParentIndexCode = "";
	@Column(name="VALUE_FUNC")
	private String ValueFunc = "";
	@Column(name="VALUE_UNIT")
	private String ValueUnit = "";
	@Column(name="SCORE_FUNC")
	private String ScoreFunc = "";
	@Column(name="SCORE_PERIOD")
	private String ScorePeriod = "";
	@Column(name="SCORE_DEFAULT")
	private double ScoreDefault;
	@Column(name="SCORE_SUMLOW")
	private double ScoreSumLow;
	@Column(name="SCORE_SUMMAX")
	private double ScoreSumMax;
	@Column(name="UPPER_SUMWEIGHT")
	private double UpperSumWeight;
	@Column(name="STANDARD_SCORE")
	private double standardscore;
	@Column(name="VALID_BEGINDATE")
	private Date ValidBeginDate;
	@Column(name="VALID_ENDDATE")
	private Date ValidEndDate;
	@Column(name="ENABLED")
	private int Enabled;
	@Column(name="INDEX_ORDER")
	private String indexorder;
	@Column(name="MEMO")
	private String Memo = "";
	@Column(name="VERSION")
	private String version; //版本号
	@Column(name="OBJ_INDEXNO")
	private String objIndexNo;  //流水号 ：体系code+version S01.V01

	
//	public ObjIndexItem parent;
//	public List<ObjIndexItem> children = new ArrayList<ObjIndexItem>();

	public int compareTo(ObjIndexItem o) {
		if (StringUtils.isBlank(this.indexorder) || StringUtils.isBlank(o.indexorder)) {
			return this.IndexCode.compareTo(o.IndexCode);
		} else {
			return this.indexorder.compareTo(o.indexorder);
		}
	}


	public String getIndexCode() {
		return IndexCode;
	}


	public void setIndexCode(String indexCode) {
		IndexCode = indexCode;
	}


	public String getIndexName() {
		return IndexName;
	}


	public void setIndexName(String indexName) {
		IndexName = indexName;
	}


	public String getIndexDesc() {
		return IndexDesc;
	}


	public void setIndexDesc(String indexDesc) {
		IndexDesc = indexDesc;
	}


	public String getArchCode() {
		return ArchCode;
	}


	public void setArchCode(String archCode) {
		ArchCode = archCode;
	}


	public String getIsParent() {
		return IsParent;
	}


	public void setIsParent(String isParent) {
		IsParent = isParent;
	}


	public String getParentIndexCode() {
		return ParentIndexCode;
	}


	public void setParentIndexCode(String parentIndexCode) {
		ParentIndexCode = parentIndexCode;
	}


	public String getValueFunc() {
		return ValueFunc;
	}


	public void setValueFunc(String valueFunc) {
		ValueFunc = valueFunc;
	}


	public String getValueUnit() {
		return ValueUnit;
	}


	public void setValueUnit(String valueUnit) {
		ValueUnit = valueUnit;
	}


	public String getScoreFunc() {
		return ScoreFunc;
	}


	public void setScoreFunc(String scoreFunc) {
		ScoreFunc = scoreFunc;
	}


	public String getScorePeriod() {
		return ScorePeriod;
	}


	public void setScorePeriod(String scorePeriod) {
		ScorePeriod = scorePeriod;
	}


	public double getScoreDefault() {
		return ScoreDefault;
	}


	public void setScoreDefault(double scoreDefault) {
		ScoreDefault = scoreDefault;
	}


	public double getScoreSumLow() {
		return ScoreSumLow;
	}


	public void setScoreSumLow(double scoreSumLow) {
		ScoreSumLow = scoreSumLow;
	}


	public double getScoreSumMax() {
		return ScoreSumMax;
	}


	public void setScoreSumMax(double scoreSumMax) {
		ScoreSumMax = scoreSumMax;
	}


	public double getUpperSumWeight() {
		return UpperSumWeight;
	}


	public void setUpperSumWeight(double upperSumWeight) {
		UpperSumWeight = upperSumWeight;
	}


	public double getStandardscore() {
		return standardscore;
	}


	public void setStandardscore(double standardscore) {
		this.standardscore = standardscore;
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


	public int getEnabled() {
		return Enabled;
	}


	public void setEnabled(int enabled) {
		Enabled = enabled;
	}


	public String getIndexorder() {
		return indexorder;
	}


	public void setIndexorder(String indexorder) {
		this.indexorder = indexorder;
	}


	public String getMemo() {
		return Memo;
	}


	public void setMemo(String memo) {
		Memo = memo;
	}

	
}
