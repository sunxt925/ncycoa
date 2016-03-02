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
	//是否为最新版本
	@Column(name="ISLAST")  //是为1，不是为0
	private String IsLast = "";
	//是否重要指标
	@Column(name="ISIMPORTANT")  //是为1，不是为0
	private String IsImportant = "";
	
	@Column(name="ISPARENT")  //体系为1，指标为0
	private String IsParent = "";
	@Column(name="PARENT_INDEX_CODE")  //体系为-1，指标为S01.V01
	private String ParentIndexCode = "";
	@Column(name="VALUE_FUNC") //计算函数
	private String ValueFunc = "";
	@Column(name="VALUE_UNIT") //单位 没用
	private String ValueUnit = ""; 
	@Column(name="SCORE_FUNC") //得分函数 没用
	private String ScoreFunc = "";
	@Column(name="SCORE_PERIOD")  //周期、类别：月度，半年，季度，年度
	private String ScorePeriod = "";
	  //默认得分 没用
	@Column(name="SCORE_DEFAULT")
	private double ScoreDefault;
	  //最小分 没用
	@Column(name="SCORE_SUMLOW")
	private double ScoreSumLow;  
	  //最大分 没用
	@Column(name="SCORE_SUMMAX")
	private double ScoreSumMax;
	  //权重 没用
	@Column(name="UPPER_SUMWEIGHT")
	private double UpperSumWeight;
	  //标准分 没用
	@Column(name="STANDARD_SCORE")
	private double standardscore;
	  //开始时间 没用
	@Column(name="VALID_BEGINDATE")
	private Date ValidBeginDate;
	  //结束时间 没用
	@Column(name="VALID_ENDDATE")
	private Date ValidEndDate;
	//指标是否可用 没用
	@Column(name="ENABLED")
	private int Enabled; //?????
	//指标排序 没用
	@Column(name="INDEX_ORDER")
	private String indexorder;
	//备注
	@Column(name="MEMO")
	private String Memo = "";
	
	@Column(name="MINLINE")
	private String minline = "";
	
	@Column(name="VERSION")
	private String version; //版本号
	@Column(name="OBJ_INDEXNO")
	private String objIndexNo;  //流水号 ：体系code+version S01.V01 之后可能用于存单独S01

	
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


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getObjIndexNo() {
		return objIndexNo;
	}


	public void setObjIndexNo(String objIndexNo) {
		this.objIndexNo = objIndexNo;
	}


	public String getIsLast() {
		return IsLast;
	}


	public void setIsLast(String isLast) {
		IsLast = isLast;
	}


	public String getIsImportant() {
		return IsImportant;
	}


	public void setIsImportant(String isImportant) {
		IsImportant = isImportant;
	}


	public String getMinline() {
		return minline;
	}


	public void setMinline(String minline) {
		this.minline = minline;
	}
	

}
