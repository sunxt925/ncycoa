package edu.cqu.gem.ncycoa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "NCYCOA_LOG")
public class Log implements Serializable {
	
	private static final long serialVersionUID = 8523524519882183312L;
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name ="LOG_ID", nullable=false, length=32)
	private String id;

	@Column(name="OPER_NAME")
	private String operatorName;
	
	@Column(name="OPER_CODE")
	private String operatorCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPER_TIME")
	private Date operateTime;
	
	@Column(name="OPER_TYPE")
	private Short operateType;
	
	@Column(name="LOG_CONTENT")
	private String logContent;
	
	@Column(name="LOG_LVL")
	private Short logLevel;
	
	@Column(name="BROSER")
	private String broswer;
	
	@Column(name="NOTE")
	private String note;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Short getOperateType() {
		return operateType;
	}

	public void setOperateType(Short operateType) {
		this.operateType = operateType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public Short getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Short logLevel) {
		this.logLevel = logLevel;
	}

	public String getBroswer() {
		return broswer;
	}

	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}